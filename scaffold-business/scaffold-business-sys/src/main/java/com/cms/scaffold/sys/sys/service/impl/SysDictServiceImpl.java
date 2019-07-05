package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.sys.ao.SysDictAO;
import com.cms.scaffold.sys.sys.bo.SysDictBO;
import com.cms.scaffold.sys.sys.dao.SysDictMapper;
import com.cms.scaffold.sys.sys.domain.SysDict;
import com.cms.scaffold.sys.sys.service.SysDictService;
import com.cms.scaffold.common.asserts.Assert;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.constant_auto.BasicsConstant;
import com.cms.scaffold.common.constant_manual.RedisConstant;
import com.cms.scaffold.common.exception.BaseResultCodeEnum;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.core.I18nUtil.I18nTransformUtil;
import com.cms.scaffold.core.baseService.BaseServiceImpl;
import com.cms.scaffold.core.jedis.JedisUtils;
import com.cms.scaffold.core.util.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDictAO, SysDictBO, SysDict> implements SysDictService {


    /**
     * 根据上级pid查询字典记录
     *
     * @param pid
     * @return
     */
    @Override
    public List<SysDictBO> findListByPid(Long pid) {
        Assert.notNull(pid, "pid");
        return Builder.buildList(dao.listByPid(pid), SysDictBO.class);
    }

    /**
     * 根据id查询所有父类id
     *
     * @param id
     * @return
     */
    @Override
    public String findFatherIds(Long id) {
        StringBuilder ids = new StringBuilder();
        recursionFindPid(ids, id);
        String strIds = ids.toString().replaceFirst(",", "");
        return strIds;
    }

    /**
     * 根据nid查找字典资源
     *
     * @param nid
     * @return
     */
    @Override
    public List<SysDictBO> findByPartnerNid(String nid) {
        List<SysDict> list = new ArrayList<>();
        Object obj = JedisUtils.getObjectMapField(RedisConstant.SYS_DICT_NID, nid);
        if (obj == null) {
            list = dao.findByPartnerNid(nid);
            if (list != null && list.size() > 0) {
                JedisUtils.hset(RedisConstant.SYS_DICT_NID, nid, list);
            }
        } else {
            list = (List<SysDict>) obj;
        }
        I18nTransformUtil.transFormList(list, "name");

        return Builder.buildList(list,SysDictBO.class);

    }

    /**
     * 缓存字典类
     */
    @Override
    public void loadDictIntoRedis() {
        List<SysDict> list = dao.findByPartnerNid("");
        Map<String, Object> map = new HashMap<>();
        Set<String> set = new HashSet<String>();
        for (SysDict dict : list) {
            List<SysDict> sysList;
            String nid = dict.getNid();
            set.add(nid);
            if (map.containsKey(nid)) {
                sysList = (List<SysDict>) map.get(nid);
            } else {
                sysList = new ArrayList();
            }
            sysList.add(dict);
            map.put(nid, sysList);
        }
        if(map.size()!=0){
            JedisUtils.setObjectMap(RedisConstant.SYS_DICT_NID, map, 0);
        }

        //遍历
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String nid = iterator.next();
            List<SysDict> nidList = dao.findByPartnerNid(nid);
            Map<String, String> hashMap = new HashMap<>();
            for (SysDict sysDict : nidList) {
                hashMap.put(sysDict.getValue(), sysDict.getName());
            }
            if(hashMap.size()!=0){
                JedisUtils.setMap(String.format(RedisConstant.SYS_DICT_NID_NAME, nid),
                        hashMap, 0);
            }
        }

    }

    @Override
    public List<SysDict> findByNid(String nid) {
        return dao.findByNid(nid);
    }

    /**
     * 通过上级的id递归查找所有的上级id，通过引用的方式 返回到 ids中
     *
     * @param ids 存储ids  使用引用的方式传值
     * @param id  上级id
     */
    private void recursionFindPid(StringBuilder ids, Long id) {
        if (id == 0) {
            return;
        }
        Long pid = dao.findPid(id);
        ids.append(",").append(pid);
        recursionFindPid(ids, pid);
    }

    /**
     * 根据nid和值查询字典记录
     *
     * @param nid
     * @param value
     * @return
     */
    @Override
    public SysDict selectByNidAndValue(String nid, String value) {
        SysDict temp = new SysDict();
        temp.setNid(nid);
        temp.setValue(value);

        return dao.selectOne(temp);
    }

    @Override
    public List<SysDict> findOrderPropertyList() {
        return dao.findOrderPropertyList();
    }

    @Override
    public void save(SysDictAO dict) {
        Assert.notNull(dict);
        Assert.notNull(dict.getPid(), "pid");
        SysDict partnerDict = dao.selectById(dict.getPid());

        Assert.notNull(partnerDict, "父字典");
        Assert.notEMPTY(partnerDict.getNid(), "父字典nid");

        if (BasicsConstant.BASICS_DICT_TYPE_FOLDER == dict.getType().intValue()) {
            if (StringUtil.isBlank(partnerDict.getNid())) {
                dict.setNid(dict.getNid());
            } else {
                dict.setNid(partnerDict.getNid() + "_" + dict.getNid());
            }
        } else {
            dict.setNid(partnerDict.getNid());
        }
        //保存
        if (dict.getId() == null) {
            dao.insert(Builder.build(dict, SysDict.class));
        } else {
            SysDict tempSysDict = dao.selectById(dict.getId());
            if (!tempSysDict.getPid().equals(dict.getPid())) {
                throw new BusinessException(BaseResultCodeEnum.SYSTEM_MENU_PID_NOT_CHANGE);
            }

            BeanUtils.copyPropertiesByList(dict, tempSysDict, new String[]{
                    "name", "pid", "nid", "value", "code", "javaType", "status", "sort", "remark", "type"});
            dao.updateAll(tempSysDict);
        }

        JedisUtils.mapObjectRemove(RedisConstant.SYS_DICT_NID, partnerDict.getNid());
    }
}

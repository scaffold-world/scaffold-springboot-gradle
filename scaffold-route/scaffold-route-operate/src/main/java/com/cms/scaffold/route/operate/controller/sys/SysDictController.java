package com.cms.scaffold.route.operate.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cms.scaffold.route.operate.controller.BaseController;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.code.I18nUtil.I18nTransformUtil;
import com.cms.scaffold.route.operate.request.sys.SysDictReq;
import com.cms.scaffold.route.operate.response.SysDictResp;
import com.cms.scaffold.sys.sys.ao.SysDictAO;
import com.cms.scaffold.sys.sys.bo.SysDictBO;
import com.cms.scaffold.sys.sys.service.SysDictService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 字典Controller
 */
@Controller
@RequestMapping("/sys/sysDict")
public class SysDictController extends BaseController {
    public static final String ftlPath = "/sys/sysDict/";


    @Autowired
    SysDictService sysDictService;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping("/sysDictIndex")
    public String sysDictIndex() {

        return ftlPath + "sysDictIndex";
    }


    /**
     * 根据父id查询字典记录
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/findSysDictByPid")
    @ResponseBody
    public List<SysDictResp> findSysDictByPid(Long parentId, HttpServletRequest request) {

        List<SysDictBO> sysDictBOList = sysDictService.findListByPid(parentId);
        Locale locale = LocaleContextHolder.getLocale();
        logger.info(locale.getLanguage()+"_"+locale.getCountry());
        I18nTransformUtil.transFormList(sysDictBOList, "name");
        return Builder.buildList(sysDictBOList,SysDictResp.class);
    }


    /**
     * 编辑页面
     *
     * @return
     */
    @RequestMapping("/sysDictEdit")
    public String sysDictEdit(Model model, SysDictReq dict) {
        SysDictBO tempSysDict = null;
        SysDictBO partnerDict = null;

        if (dict.getId() == null) {
            tempSysDict = Builder.build(dict, SysDictBO.class);
            if (dict.getPid() == null) {
                throw new BusinessException("需要选择上级目录");
            }
            partnerDict = sysDictService.selectById(dict.getPid());
        } else {
            tempSysDict = sysDictService.selectById(dict.getId());
            partnerDict = sysDictService.selectById(tempSysDict.getPid());
            if (StringUtil.isNotBlank(partnerDict.getNid())) {
                tempSysDict.setNid(tempSysDict.getNid().replace(partnerDict.getNid() + "_", ""));
            }
        }
        I18nTransformUtil.transForm(tempSysDict, "name");
        I18nTransformUtil.transForm(partnerDict, "name");
        model.addAttribute("sysDict", Builder.build(tempSysDict,SysDictResp.class));
        model.addAttribute("partnerDict", Builder.build(partnerDict,SysDictResp.class));
        return ftlPath + "sysDictEdit";
    }


    /**
     * 根据id查询所有父类id
     *
     * @param id
     * @return
     */
    @RequestMapping("/findFatherIds")
    @ResponseBody
    public String findFatherIds(Long id) {
        String ids = sysDictService.findFatherIds(id);

        return ids;
    }


    /**
     * 保存字典记录
     *
     * @param dict
     * @return
     */
    @RequestMapping("/sysDictSave")
    @ResponseBody
    public ResponseModel sysDictSaveValid(SysDictReq dict) {
        sysDictService.save(Builder.build(dict, SysDictAO.class));
        return doneSuccess();
    }

    @RequestMapping(value = "/findDictValues")
    @ResponseBody
    public JSONArray findDictValues(String selectName, String q) {

        JSONArray jsonArray = new JSONArray();
        if (StringUtil.isNotBlank(selectName)) {
            List<SysDictBO> sysDicts = sysDictService.findByPartnerNid(selectName);
            if (CollectionUtils.isNotEmpty(sysDicts)) {
                if (StringUtil.isNotBlank(q)) {
                    sysDicts = sysDicts.stream().filter(sysDict -> StringUtil.contains(sysDict.getName(), q)).collect(Collectors.toList());
                }
                sysDicts.forEach(sysDict -> {
                    JSONObject object = new JSONObject();
                    object.put("text", sysDict.getName());
                    object.put("value", sysDict.getValue());
                    jsonArray.add(object);
                });
            }
        }
        return jsonArray;
    }

}

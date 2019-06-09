package com.jiaheng.scaffold.route.operate.controller.sys;

import com.jiaheng.scaffold.common.base.Builder;
import com.jiaheng.scaffold.common.base.ResponseListModel;
import com.jiaheng.scaffold.common.base.ResponseModel;
import com.jiaheng.scaffold.core.util.ResponseListModelUtils;
import com.jiaheng.scaffold.route.operate.controller.BaseController;
import com.jiaheng.scaffold.route.operate.request.sys.SysI18nReq;
import com.jiaheng.scaffold.route.operate.response.sys.SysI18nResp;
import com.jiaheng.scaffold.sys.sys.ao.SysI18nAO;
import com.jiaheng.scaffold.sys.sys.bo.SysI18nBO;
import com.jiaheng.scaffold.sys.sys.service.SysI18nService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description: 国际化Controller
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 17:50
 **/
@Controller
@RequestMapping("/sys/sysI18n")
public class SysI18nController extends BaseController {


    public static final String ftlPath = "/sys/sysI18n/";

    @Resource
    private SysI18nService sysI18nService;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping("/sysI18nIndex")
    public String sysI18nIndex() {

        return ftlPath + "sysI18nIndex";
    }


    @RequestMapping("/sysI18nIndexList")
    @ResponseBody
    public ResponseListModel<SysI18nResp> sysI18nIndexList(SysI18nReq sysI18nReq) {
        SysI18nAO sysI18nAO = Builder.build(sysI18nReq,SysI18nAO.class);
        ResponseListModel<SysI18nBO> sysI18nList = sysI18nService.findPageList(sysI18nAO);

        return ResponseListModelUtils.transform(sysI18nList,SysI18nResp.class);
    }


    /**
     * 编辑页面
     *
     * @return
     */
    @RequestMapping("/sysI18nEdit")
    public String sysI18nEdit(Model model,SysI18nReq sysI18nReq) {
        SysI18nResp sysI18nResp = null;
        if(sysI18nReq.getId() == null){
            sysI18nResp = Builder.build(sysI18nReq,SysI18nResp.class);
        }else {
            sysI18nResp = Builder.build(sysI18nService.selectById(sysI18nReq.getId()),SysI18nResp.class);
        }

        model.addAttribute("sysI18n", sysI18nResp);

        return ftlPath + "sysI18nEdit";
    }

    @RequestMapping("/sysI18nSave")
    @ResponseBody
    public ResponseModel sysI18nSave(SysI18nReq sysI18nReq) {

        sysI18nService.save(Builder.build(sysI18nReq, SysI18nAO.class));

        return doneSuccess();
    }
}

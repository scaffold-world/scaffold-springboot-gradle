package com.cms.scaffold.route.operate.controller.sys;

import com.cms.scaffold.common.base.ResponseListModel;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.constant_manual.SysConstant;
import com.cms.scaffold.common.util.MD5;
import com.cms.scaffold.route.operate.controller.BaseController;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.domain.SysRole;
import com.cms.scaffold.sys.sys.domain.SysRoleOperate;
import com.cms.scaffold.sys.sys.service.SysOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sys/sysOperate")
@Api(tags = "SysOperateController", description = "操作人员管理页面")
public class SysOperateController extends BaseController {
  public static final String ftlPath = "/sys/sysOperate/";

  @Autowired private SysOperateService sysOperateService;

  @Autowired private SysRoleService sysRoleService;

  @Autowired private SysRoleOperateService sysRoleOperateService;

  @GetMapping(value = "sysOperateIndex")
  public String operateManage() {
    return ftlPath + "sysOperateIndex";
  }

  @GetMapping(value = "operateList")
  @ResponseBody
  public ResponseListModel<SysOperate> operateList(SysOperate sysOperateReq) {
    ResponseListModel<SysOperate> list = sysOperateService.queryAllOperate(sysOperateReq);

    List<SysOperate> rows = list.getData();
    ResponseListModel<SysOperate> respList = new ResponseListModel<>(rows, list.getCount());
    return respList;
  }

  @ResponseBody
  @PostMapping("/deleteOperate")
  public ResponseModel deleteOperate(Long id){
    sysOperateService.deleteById(id);
    return doneSuccess();
  }

  @GetMapping(value = "addOperateIndex")
  public String addOperatePage(Model model) {
    List<SysRole> list = sysRoleService.queryAll();
    model.addAttribute("list", list);
    return ftlPath + "addOperateIndex";
  }

  @RequestMapping(value = "saveOperate")
  @ResponseBody
  public ResponseModel saveOperateVail(SysOperate sysOperateReq) {
    SysOperate sysOperate =
        new SysOperate(
            sysOperateReq.getUserName(),
            MD5.encode(sysOperateReq.getPwd()).toUpperCase(),
            0L,
            null,
            sysOperateReq.getMobilePhone(),
            sysOperateReq.getRealName());
    if (sysOperateReq.getRoleId() != null) {
      SysRoleOperate sysRoleOperate = new SysRoleOperate();
      sysRoleOperate.setRoleId(sysOperateReq.getRoleId());
      sysRoleOperateService.saveOperateAndRole(sysRoleOperate, sysOperate);
    }
    return doneSuccess();
  }

  @RequestMapping(value = "editOperatePage")
  public String editOperate(Long operateId, Model model) {

    SysOperate sysOperate = sysOperateService.findById(operateId);
    List<SysRole> list = sysRoleService.queryAll();
    SysRoleOperate sysRoleOperate = sysRoleOperateService.selectByOperateId(sysOperate.getId());
    model.addAttribute("list", list);
    model.addAttribute("sysOperate", sysOperate);
    model.addAttribute("sysRoleOperate", sysRoleOperate);
    return ftlPath + "editOperate";
  }

  @RequestMapping(value = "updateOperate")
  @ResponseBody
  public ResponseModel updateOperate(SysOperate sysOperateModel) {
    SysRoleOperate sysRoleOperate =
        sysRoleOperateService.selectByOperateId(sysOperateModel.getId());

    sysRoleOperate.setRoleId(sysOperateModel.getRoleId());

    sysOperateService.updateSysOperateAndSysRoleOperate(sysOperateModel, sysRoleOperate);
    return doneSuccess();
  }

  @RequestMapping(value = "editOperatePwdPage")
  public String editOperatePwd(Long operateId, Model model) {
    SysOperate sysOperate = sysOperateService.findById(operateId);
    List<SysRole> list = sysRoleService.queryAll();
    SysRoleOperate sysRoleOperate = sysRoleOperateService.selectByOperateId(sysOperate.getId());
    model.addAttribute("list", list);
    model.addAttribute("sysOperate", sysOperate);
    model.addAttribute("sysRoleOperate", sysRoleOperate);
    return ftlPath + "editOperatePwd";
  }

  @RequestMapping(value = "updateOperatePwd")
  @ResponseBody
  public ResponseModel updateOperatePwd(SysOperate sysOperateModel) {
    SysOperate sysOperate = sysOperateService.findById(sysOperateModel.getId());

    sysOperate.setPwd(MD5.encode(sysOperateModel.getPwd()).toUpperCase());

    sysOperateService.updateSysOperate(sysOperate);

    return doneSuccess();
  }

  /** 重置密码 */
  @RequestMapping(value = "resetPwd")
  @ResponseBody
  public ResponseModel resetPwd(String operateId) {
    SysOperate sysOperate =
        sysOperateService.findById(Long.parseLong(operateId.replaceAll("'", "")));
    sysOperate.setPwd(MD5.encode(SysConstant.RESEVER_PASSWORD).toUpperCase());

    sysOperateService.updateSysOperate(sysOperate);

    return doneSuccess("重置成功，密码为" + SysConstant.RESEVER_PASSWORD);
  }

  /**
   * 根据条件查询操作员列表
   *
   * @param remark
   * @return
   */
  @RequestMapping("/getOperateByRole")
  @ResponseBody
  public List<SysOperate> getOperateByRole(String remark) {
    SysRole sysRoleModel = new SysRole();
    sysRoleModel.setRemark(remark);
    sysRoleModel.setStatus(1L);
    List<SysRole> roleVOS = sysRoleService.findList(sysRoleModel);
    List<SysOperate> list = new ArrayList<>();
    if (null != roleVOS && !roleVOS.isEmpty()) {
      List<SysRoleOperate> roleOperates =
          sysRoleOperateService.findListByRole(roleVOS.get(0).getId());
      roleOperates.forEach(
          roleOperate -> {
            SysOperate operateVO = sysOperateService.findById(roleOperate.getOperateId());
            if (null != operateVO) {
              list.add(operateVO);
            }
          });
    }
    return list;
  }

  @ResponseBody
  @RequestMapping("/updateStatus")
  public ResponseModel updateStatus(Long id, Long status) {
    SysOperate operate = new SysOperate();
    operate.setId(id);
    operate.setStatus(status);
    sysOperateService.update(operate);
    return doneSuccess();
  }
}

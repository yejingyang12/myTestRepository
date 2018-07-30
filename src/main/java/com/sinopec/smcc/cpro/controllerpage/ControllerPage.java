/**
* 2018. 
* @Title ControllerPage.java
* @Package com.sinopec.smcc.cpro.contaollerpage
* @Description: TODO:
* @author hanxin
* @date 2018年6月6日下午5:16:35
* @version V1.0
*/
package com.sinopec.smcc.cpro.controllerpage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.system.entity.SystemParam;

/**
 * @Title ControllerPage.java
 * @Package com.sinopec.smcc.cpro.contaollerpage
 * @Description: TODO:
 * @author hanxin
 * @date 2018年6月6日下午5:16:35
 * @version V1.0
 */
@Controller
@RequestMapping("/page")
public class ControllerPage {
  //系统定级备案测评列表
  @RequestMapping("/indexPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String indexPage(HttpServletRequest request, Model model){
    return "views/index";
  }
  
  //新建单位信息填写
  @RequestMapping("/addCompanyInfoPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String addCompanyInfoPagePage(HttpServletRequest request, Model model,
      CompanyParam companyParam,@ModelAttribute("jurisdiction")String jurisdiction){
    model.addAttribute("companyId", companyParam.getCompanyId());
    model.addAttribute("companyCode", companyParam.getCompanyCode());
    model.addAttribute("jurisdiction",jurisdiction);
    return "views/addCompanyInfoPage";
  }
  //新建系统信息填写
  @RequestMapping("/addCompanySystemPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String addCompanySystemPage(HttpServletRequest request, Model model,
      CompanyParam companyParam){
    model.addAttribute("companyId", companyParam.getCompanyId());
    model.addAttribute("systemId", companyParam.getSystemId());
    model.addAttribute("companyCode", companyParam.getCompanyCode());
    return "views/addCompanySystem";
  }
  //新建定级信息填写
  @RequestMapping("/addCompanyGradPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String addCompanyGradPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/addCompanyGrad";
  }
  //新建材料信息填写
  @RequestMapping("/addCompanyMaterialPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String addCompanyMaterialPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    return "views/addCompanyMaterial";
  }
  
  //申请变更单位信息填写
  @RequestMapping("/applicationChangePage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String applicationChangePage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    model.addAttribute("companyId", systemParam.getCompanyId());
    model.addAttribute("theLastStep", systemParam.getTheLastStep());
    return "views/applicationChange";
  }
  //申请变更系统信息填写
  @RequestMapping("/applicatuibChangSystemPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String applicatuibChangSystemPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicatuibChangSystem";
  }
  //申请变更定级信息填写
  @RequestMapping("/applicationChangeGradPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String applicationChangeGradPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    return "views/applicationChangeGrad";
  }
  //申请变更材料信息填写
  @RequestMapping("/applicationChangeMaterialPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String applicationChangeMaterialPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    return "views/applicationChangeMaterial";
  }
  
  //审核管理页面
  @RequestMapping("/auditPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String auditPage(HttpServletRequest request, Model model){
    return "views/audit";
  }
  //审核定级
  @RequestMapping("/auditGradPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String auditGradPage(HttpServletRequest request, Model model, 
      SystemParam systemParam, CheckParam checkParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    model.addAttribute("fkBusinessNode", checkParam.getFkBusinessNode());
    model.addAttribute("fkExaminStatus", checkParam.getFkExaminStatus());
    return "views/auditGrad";
  }
  //审核申请变更
  @RequestMapping("/auditChangePage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String auditChangePage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/auditChange";
  }
  //审核撤销备案
  @RequestMapping("/auditCancelPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String auditCancelPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/auditCancel";
  }
  //审核详情
  @RequestMapping("/auditDetailsPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String auditDetailsPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/auditDetails";
  }
  //备案
  @RequestMapping("/companyRecordPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String companyRecordPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/companyRecord";
  }

  //申请定级信息
  @RequestMapping("/applicationGradingPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String applicationGradingPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicationGrading";
  }
  //填写材料信息
  @RequestMapping("/applicationGradingInfoPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String applicationGradingInfoPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicationGradingInfo";
  }
  //维护单位系统信息
  @RequestMapping("/mainCompanyInfoPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String mainCompanyInfoPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("activeName", systemParam.getActiveName());
    return "views/mainCompanyInfo";
  }
  //维护单位--新建单位信息
  @RequestMapping("/newUnitInformationPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String newUnitInformationPage(HttpServletRequest request, Model model,
      @ModelAttribute("jurisdiction")String jurisdiction){
    model.addAttribute("jurisdiction",jurisdiction);
    return "views/newUnitInformation";
  }
  //维护单位--修改单位信息
  @RequestMapping("/changeUnitInformationPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String changeUnitInformationPage(HttpServletRequest request, Model model,
      CompanyParam companyParam){
    model.addAttribute("companyId", companyParam.getCompanyId());
    return "views/changeUnitInformation";
  }
  //维护单位--新建系统信息
  @RequestMapping("/newSystemInformationPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String newSystemInformationPage(HttpServletRequest request, Model model){

    return "views/newSystemInformation";
  }
  //维护单位--修改系统信息
  @RequestMapping("/changeSystemInformationPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String changeSystemInformationPage(HttpServletRequest request, Model model,
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/changeSystemInformation";
  }
  
  //自查
  @RequestMapping("/selfCheckPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String selfCheckPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/selfCheck";
  }
  //测评
  @RequestMapping("/testingPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String testingPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/testing";
  }
  //详情
  @RequestMapping("/viewDetailsPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String viewDetailsPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("companyId", systemParam.getCompanyId());
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/viewDetails";
  }
  //图表详情列表
  @RequestMapping("/showChartDataListPage")
  @RequestLog(module=SmccModuleEnum.cpro)
  public String showChartDataListPage(HttpServletRequest request, Model model, MainParam mainParam){
    //图表传入的5个参数
    model.addAttribute("sprankLevel", mainParam.getSprankLevel());
    model.addAttribute("gradingBeginTimeStamp", mainParam.getGradingBeginTimeStamp());
    model.addAttribute("gradingEndTimeStamp", mainParam.getGradingEndTimeStamp());
    model.addAttribute("systemType", mainParam.getSystemType());
    model.addAttribute("gradingShapeType", mainParam.getGradingShapeType());
    return "views/showChartDataList";
  }
}

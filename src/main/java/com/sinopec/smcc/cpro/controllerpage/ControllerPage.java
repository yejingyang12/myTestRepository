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
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinopec.smcc.cpro.company.entity.CompanyParam;
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
  public String indexPage(HttpServletRequest request, Model model){
    return "views/index";
  }
  
  //新建单位信息填写
  @RequestMapping("/addCompanyInfoPage")
  public String addCompanyInfoPagePage(HttpServletRequest request, Model model,
      CompanyParam companyParam){
    model.addAttribute("companyId", companyParam.getCompanyId());
    return "views/addCompanyInfoPage";
  }
  //新建系统信息填写
  @RequestMapping("/addCompanySystemPage")
  public String addCompanySystemPage(HttpServletRequest request, Model model,
      CompanyParam companyParam){
    model.addAttribute("companyId", companyParam.getCompanyId());
    model.addAttribute("systemId", companyParam.getSystemId());
    model.addAttribute("companyCode", companyParam.getCompanyCode());
    return "views/addCompanySystem";
  }
  //新建定级信息填写
  @RequestMapping("/addCompanyGradPage")
  public String addCompanyGradPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/addCompanyGrad";
  }
  //新建材料信息填写
  @RequestMapping("/addCompanyMaterialPage")
  public String addCompanyMaterialPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/addCompanyMaterial";
  }
  
  //申请变更单位信息填写
  @RequestMapping("/applicationChangePage")
  public String applicationChangePage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicationChange";
  }
  //申请变更系统信息填写
  @RequestMapping("/applicatuibChangSystemPage")
  public String applicatuibChangSystemPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyCode", systemParam.getFkCompanyCode());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicatuibChangSystem";
  }
  //申请变更定级信息填写
  @RequestMapping("/applicationChangeGradPage")
  public String applicationChangeGradPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/applicationChangeGrad";
  }
  //申请变更材料信息填写
  @RequestMapping("/applicationChangeMaterialPage")
  public String applicationChangeMaterialPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/applicationChangeMaterial";
  }
  
  //审核管理页面
  @RequestMapping("/auditPage")
  public String auditPage(HttpServletRequest request, Model model){
    return "views/audit";
  }
  //审核定级
  @RequestMapping("/auditGradPage")
  public String auditGradPage(HttpServletRequest request, Model model, 
      SystemParam systemParam, CheckParam checkParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    model.addAttribute("fkBusinessNode", checkParam.getFkBusinessNode());
    return "views/auditGrad";
  }
  //审核申请变更
  @RequestMapping("/auditChangePage")
  public String auditChangePage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/auditChange";
  }
  //审核撤销备案
  @RequestMapping("/auditCancelPage")
  public String auditCancelPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/auditCancel";
  }
  //审核详情
  @RequestMapping("/auditDetailsPage")
  public String auditDetailsPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/auditDetails";
  }
  //备案
  @RequestMapping("/companyRecordPage")
  public String companyRecordPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/companyRecord";
  }

  //申请定级信息
  @RequestMapping("/applicationGradingPage")
  public String applicationGradingPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicationGrading";
  }
  //填写材料信息
  @RequestMapping("/applicationGradingInfoPage")
  public String applicationGradingInfoPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/applicationGradingInfo";
  }
  //维护单位系统信息
  @RequestMapping("/mainCompanyInfoPage")
  public String mainCompanyInfoPage(HttpServletRequest request, Model model){
    return "views/mainCompanyInfo";
  }
  //维护单位--新建单位信息
  @RequestMapping("/newUnitInformationPage")
  public String newUnitInformationPage(HttpServletRequest request, Model model){
    return "views/newUnitInformation";
  }
  //维护单位--修改单位信息
  @RequestMapping("/changeUnitInformationPage")
  public String changeUnitInformationPage(HttpServletRequest request, Model model,
      CompanyParam companyParam){
    model.addAttribute("companyId", companyParam.getCompanyId());
    return "views/changeUnitInformation";
  }
  //维护单位--新建系统信息
  @RequestMapping("/newSystemInformationPage")
  public String newSystemInformationPage(HttpServletRequest request, Model model){
    //TODO:从session中获取当前用户的companyCode
    model.addAttribute("companyCode", "10010008");
    return "views/newSystemInformation";
  }
  //维护单位--修改系统信息
  @RequestMapping("/changeSystemInformationPage")
  public String changeSystemInformationPage(HttpServletRequest request, Model model,
      SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    //TODO:从session中获取当前用户的companyCode
    model.addAttribute("companyCode", "10010008");
    return "views/changeSystemInformation";
  }
  
  //自查
  @RequestMapping("/selfCheckPage")
  public String selfCheckPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/selfCheck";
  }
  //测评
  @RequestMapping("/testingPage")
  public String testingPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("systemId", systemParam.getSystemId());
    model.addAttribute("companyId", systemParam.getCompanyId());
    return "views/testing";
  }
  //详情
  @RequestMapping("/viewDetailsPage")
  public String viewDetailsPage(HttpServletRequest request, Model model, SystemParam systemParam){
    model.addAttribute("companyId", systemParam.getCompanyId());
    model.addAttribute("systemId", systemParam.getSystemId());
    return "views/viewDetails";
  }
}

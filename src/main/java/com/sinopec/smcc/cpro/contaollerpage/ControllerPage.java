/**
* 2018. 
* @Title ControllerPage.java
* @Package com.sinopec.smcc.cpro.contaollerpage
* @Description: TODO:
* @author hanxin
* @date 2018年6月6日下午5:16:35
* @version V1.0
*/
package com.sinopec.smcc.cpro.contaollerpage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.cpro.company.entity.CompanyParam;
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
public class ControllerPage {

  @RequestMapping(value="/queryCompanyListPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryCompanyListPage(HttpServletRequest request, Model model, 
      CompanyParam companyParam){
    return "";
  }
  
  @RequestMapping(value="/saveCompanyPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveCompanyPage(HttpServletRequest request, Model model, 
      CompanyParam companyParam){
    return "views/addCompanyInfoPage.html";
  }
  
  @RequestMapping(value="/queryDetailsCompanyPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryDetailsCompanyPage(HttpServletRequest request, Model model, 
      CompanyParam companyParam){
    return "";
  }
  
  @RequestMapping(value="/queryEditCompanyPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEditCompanyPage(HttpServletRequest request, Model model, 
      CompanyParam companyParam){
    return "views/applicationChange.html";
  }
  
  
  @RequestMapping(value="/queryEvaluationListPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEvaluationListPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryEditEvaluationPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEditEvaluationPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/saveEvaluationPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveEvaluationPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryDetailsEvaluationPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryDetailsEvaluationPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  
  @RequestMapping(value="/queryDetailsGradingPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryDetailsGradingPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryEditGradingPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEditGradingPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/applicationGrading.html";
  }
  //修改定级方法一样，修改材料信息
  @RequestMapping(value="/saveGradingPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveGradingPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/addCompanyGrad.html";
  }
  
  @RequestMapping(value="/queryDetailsAttachPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryDetailsAttachPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryEditAttachPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEditAttachPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/applicationChangeMaterial.html";
  }
  
  
  @RequestMapping(value="/queryMainListPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryMainListPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/index.html";
  }
  
  
  @RequestMapping(value="/saveRecordsPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveRecordsPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/companyRecord.html";
  }
  
  @RequestMapping(value="/queryRecordsPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryRecordsPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/companyRecord.html";
  }
  
  @RequestMapping(value="/editRecordsPage", method = RequestMethod.POST)
  @ResponseBody
  public String editRecordsPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/companyRecord.html";
  }
  
  @RequestMapping(value="/editRecordsForStatusPage", method = RequestMethod.POST)
  @ResponseBody
  public String editRecordsForStatusPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/companyRecord.html";
  }
  
  
  @RequestMapping(value="/querylistPage", method = RequestMethod.POST)
  @ResponseBody
  public String querylistPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/audit.html";
  }
  
  @RequestMapping(value="/saveCheckPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveCheckPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/querycheckNodeListPage", method = RequestMethod.POST)
  @ResponseBody
  public String querycheckNodeListPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryNodeAllListPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryNodeAllListPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/auditChange.html";
  }
  
  
  @RequestMapping(value="/querySelfexaminationListPage", method = RequestMethod.POST)
  @ResponseBody
  public String querySelfexaminationListPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/saveSelfexaminationPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveSelfexaminationPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryEditSelfexaminationPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEditSelfexaminationPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  
  @RequestMapping(value="/querySystemListPage", method = RequestMethod.POST)
  @ResponseBody
  public String querySystemListPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/saveSystemPage", method = RequestMethod.POST)
  @ResponseBody
  public String saveSystemPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/addCompanySystem.html";
  }
  
  @RequestMapping(value="/queryDetailsSystemPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryDetailsSystemPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "";
  }
  
  @RequestMapping(value="/queryEditSystemPage", method = RequestMethod.POST)
  @ResponseBody
  public String queryEditSystemPage(HttpServletRequest request, Model model, 
      SystemParam systemParam){
    return "views/applicatuibChangSystem.html";  
  }
}

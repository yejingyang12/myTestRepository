/**
* 2018. 
* @Title GradingController.java
* @Package com.sinopec.smcc.cpro.grading.controller
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:03:11
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsBeanParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsBeanResult;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsResult;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.grading.server.SystemMaterialsService;
import com.sinopec.smcc.cpro.node.server.NodeService;

/**
 * @Title GradingController.java
 * @Package com.sinopec.smcc.cpro.grading.controller
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:03:11
 * @version V1.0
 */
@Controller
@RequestMapping("/grading")
public class GradingController {
  
  @Autowired
  private GradingService gradingServiceImpl;
  @Autowired
  private SystemMaterialsService systemMaterialsServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;
  
  /**
   * 
   * 查询定级详情信息
   * @author hanxin
   * @date 2018年5月29日上午10:21:19
   * @param request
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryDetailsGrading", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<GradingListResult> queryDetailsGrading(HttpServletRequest request, 
      @RequestBody GradingParam gradingParam) throws BusinessException{
    GradingListResult gradingListResult = 
        this.gradingServiceImpl.queryDetailsGrading(gradingParam);

    return RetResultUtil.ok(gradingListResult);
  }
  
  /**
   * 
   * 查询定级回显信息
   * @author hanxin
   * @date 2018年5月29日上午10:21:33
   * @param request
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryEditGrading", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<GradingListResult> queryEditGrading(HttpServletRequest request,
      @RequestBody GradingParam gradingParam) throws BusinessException{
    GradingListResult gradingListResult = this.gradingServiceImpl.queryEditGrading(gradingParam);

    return RetResultUtil.ok(gradingListResult);
  }
  
  /**
   * 
   * 保存定级信息
   * @author hanxin
   * @date 2018年5月31日上午11:37:14
   * @param request
   * @param gradingParam
   * @return
   */
  @RequestMapping(value = "/saveGrading", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> saveGrading(HttpServletRequest request, @RequestBody GradingParam gradingParam)
      throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String gradingId = this.gradingServiceImpl.saveGrading(userName,gradingParam);

    return RetResultUtil.ok(gradingId);
  }
  
  /**
   * 
   * 企业提交定级信息修改定级状态
   * @author hanxin
   * @date 2018年6月8日下午5:04:03
   * @param request
   * @param gradingParam
   * @return
   */
  @RequestMapping(value = "/submitGrading", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> submitGrading(HttpServletRequest request, 
      @RequestBody GradingParam gradingParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String gradingId = this.gradingServiceImpl.submitGrading(userName, gradingParam);

    return RetResultUtil.ok(gradingId);
  }
  
  /**
   * 
   * 总部提交定级信息修改定级状态
   * @author hanxin
   * @date 2018年6月8日下午5:04:03
   * @param request
   * @param gradingParam
   * @return
   */
  @RequestMapping(value = "/submitGradingForHeadquarters", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> submitGradingForHeadquarters(HttpServletRequest request, 
      @RequestBody GradingParam gradingParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String gradingId = this.gradingServiceImpl.submitGradingForHeadquarters(userName, gradingParam);

    return RetResultUtil.ok(gradingId);
  }
  
  /**
   * (废弃)
   * 获取提交材料信息
   * @author hanxin
   * @date 2018年5月29日下午3:29:13
   * @param request
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/querySystemMaterials", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemMaterialsResult> querySystemMaterials(HttpServletRequest request,
      @RequestBody SystemMaterialsParam systemMaterialsParam) throws BusinessException{
    SystemMaterialsResult systemMaterialsResult = 
        this.systemMaterialsServiceImpl.querySystemMaterials(systemMaterialsParam);

    return RetResultUtil.ok(systemMaterialsResult);
  }
  
  /**
   * (废弃)
   * 获取材料回显信息
   * @author hanxin
   * @date 2018年5月30日上午9:04:52
   * @param request
   * @param attachMaterialsParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryEditSystemMaterials", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemMaterialsResult> queryEditSystemMaterials(HttpServletRequest request,
      @RequestBody SystemMaterialsParam systemMaterialsParam) throws BusinessException{
    SystemMaterialsResult systemMaterialsResult = 
        this.systemMaterialsServiceImpl.queryEditSystemMaterials(systemMaterialsParam);

    return RetResultUtil.ok(systemMaterialsResult);
  }
  
  /**
   * 获取材料回显信息(同一种附件有多个附件情况下的获取)
   * @author hanxin
   * @date 2018年5月30日上午9:04:52
   * @param request
   * @param attachMaterialsParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryEditSystemMaterialsInfo", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemMaterialsBeanResult> queryEditSystemMaterialsInfo(HttpServletRequest request,
      @RequestBody SystemMaterialsParam systemMaterialsParam) throws BusinessException{
    SystemMaterialsBeanResult systemMaterialsBeanResult = 
        this.systemMaterialsServiceImpl.queryEditSystemMaterialsInfo(systemMaterialsParam);

    return RetResultUtil.ok(systemMaterialsBeanResult);
  }

  /**
   * (废弃)
   * 保存材料信息
   * @author hanxin
   * @date 2018年6月8日下午5:29:31
   * @param request
   * @param attachMaterialsParam
   * @return
   */
  @RequestMapping(value = "/saveSystemMaterials", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> saveSystemMaterials(HttpServletRequest request, 
      @RequestBody SystemMaterialsParam systemMaterialsParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.systemMaterialsServiceImpl.
        saveSystemMaterials(userName,systemMaterialsParam);

    return RetResultUtil.ok(fkSystemId);
  }
  /**
   * 保存材料信息
   * @Descrption
   * @author yejingyang
   * @date 2018年6月25日上午11:59:13
   * @param request
   * @param systemMaterialsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveSystemMaterialsInfo", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> saveSystemMaterialsInfo(HttpServletRequest request, 
      @RequestBody SystemMaterialsBeanParam systemMaterialsBeanParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.systemMaterialsServiceImpl.
        saveSystemMaterialsInfo(userName,systemMaterialsBeanParam);

    return RetResultUtil.ok(fkSystemId);
  }
  
  /**
   * 
   * 企业提交材料信息修改状态
   * @author hanxin
   * @date 2018年6月8日下午6:10:39
   * @param request
   * @param gradingParam
   * @return
   */
  @RequestMapping(value = "/submitSystemMaterials", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> submitSystemMaterials(HttpServletRequest request, 
      @RequestBody  SystemMaterialsBeanParam systemMaterialsBeanParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.systemMaterialsServiceImpl.
        submitSystemMaterials(userName, systemMaterialsBeanParam);

    return RetResultUtil.ok(fkSystemId);
  }
  
  /**
   * 
   * 总部提交材料信息修改状态
   * @author hanxin
   * @date 2018年6月8日下午6:10:39
   * @param request
   * @param gradingParam
   * @return
   */
  @RequestMapping(value = "/submitSystemMaterialsForHeadquarters", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> submitSystemMaterialsForHeadquarters(HttpServletRequest request, 
      @RequestBody SystemMaterialsBeanParam systemMaterialsBeanParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.systemMaterialsServiceImpl.
        submitSystemMaterialsForHeadquarters(userName,systemMaterialsBeanParam);

    return RetResultUtil.ok(fkSystemId);
  }
}

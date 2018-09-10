/**
 * @Title JurisdictionApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:19:58
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.depends.ubs.util.UbsFeignTemplate;

/**
 * @Title JurisdictionApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年7月11日下午3:19:58
 * @version V1.0
 */
@Controller
@RequestMapping("/workFlow")
public class WorkFlowApiController {

  
  @Autowired
  private UbsFeignTemplate ubsFeignTemplate;
  @Autowired
  private WorkFlowApiService workFlowApiServiceImpl;

  @Value("${appId}")
  private String appId;

  /**
   * @Descrption 流程发起
   * @author dongxu
   * @date 2018年7月30日上午11:49:09
   * @param request
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value="/initStart", method = RequestMethod.POST)
  public ResultApi initStart(HttpServletRequest request) throws BusinessException{
    AppCallResult appCallResult = workFlowApiServiceImpl.initStart("","","","");
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(appCallResult);
    return result;
  }
  
  /**
   * @Descrption 发送待办回调
   * @author dongxu
   * @date 2018年9月3日上午11:22:31
   * @param businessId 业务ID
   * @param activityId 活动Id
   * @param activityName 活动名称
   * @param taskIdList 发送待办Id列表
   * @param executorIdList 发送待办执行人Id列表 
   * @param categoryCode 流程分类编码
   * @param result 执行结果，1：成功，0：失败
   * @param message 如执行结果为0, 返回异常信
   * @param metasList 流程字段列表
   * @param variableList 流程变量列表
   * @param appId 分配的所属应用Id
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value="/stayHleCallback", method = RequestMethod.POST)
  public ResultApi stayHleCallback(HttpServletRequest request,String businessId, 
      String activityId, String activityName,List<String> taskIdList,  List<String> executorIdList, 
      String categoryCode, Integer result, String message, List<AppMetasData> metasList, 
      List<AppVariableData> variableList,String appId) throws BusinessException{
    boolean renResult = workFlowApiServiceImpl.sendTask(businessId, activityId, activityName, 
        taskIdList, executorIdList, categoryCode, result, message, metasList, variableList, appId);
    //通过resultApi实体组成返回参数
    ResultApi resultApi = new ResultApi(EnumResult.SUCCESS);
    resultApi.setData(renResult);
    return resultApi;
  }
}

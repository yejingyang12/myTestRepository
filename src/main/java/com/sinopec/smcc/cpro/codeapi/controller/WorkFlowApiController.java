/**
 * @Title JurisdictionApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:19:58
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
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
    AppCallResult appCallResult = workFlowApiServiceImpl.initStart();
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(appCallResult);
    return result;
  }
}

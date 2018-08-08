/**
* 2018. 
* @Title ApiController.java
* @Package com.sinopec.smcc.cpro.api.controller
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:09:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;
import com.sinopec.smcc.cpro.api.service.ApiService;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.server.DiagramService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;

/**
 * @Title ApiController.java
 * @Package com.sinopec.smcc.cpro.api.controller
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:09:13
 * @version V1.0
 */
@RestController
@RequestMapping("/restful/api")
public class ApiController {

  @Autowired
  public DiagramService diagramServiceImpl;
  @Autowired
  public ApiService apiServiceImpl;
  
  /**
   * @Descrption 获取系统等保管理趋势折线图数据
   * @author dongxu
   * @date 2018年7月17日上午10:10:09
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @PostMapping(value = "/querySystemTrendByYear")
  public RetResult<List<DiagramListResult>> querySystemTrendByYear(HttpServletRequest request,
      @RequestBody DiagramParam diagramParam) throws BusinessException{
    
    // 调用service实体，获得
    List<DiagramListResult> diagramListResult = this.diagramServiceImpl.
        querySystemTrendByYear(request,diagramParam);
    return RetResultUtil.ok(diagramListResult);
  }
  
  /**
   * @Descrption 获取定级信息
   * @author dongxu
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @param systemId 系统ID
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getGradingInformation", method = RequestMethod.POST)
  public RetResult<GradingApiResult> getGradingInformation(HttpServletRequest request,
      @RequestParam("systemCode") String systemCode) throws BusinessException{
    // 调用service实体，获得
    GradingApiResult gradingApiResult = this.apiServiceImpl.getGradingInformation(systemCode);
    return RetResultUtil.ok(gradingApiResult);
  }
  
  /**
   * @Descrption 通过userId获取待办列表
   * @author dongxu
   * @date 2018年8月6日下午5:19:03
   * @param request
   * @param userId
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getStayHandle", method = RequestMethod.POST)
  public RetResult<List<CheckListResult>> getStayHandle(HttpServletRequest request,
      @RequestParam("userId") String userId) throws BusinessException{
    // 调用service实体，获得
    List<CheckListResult> list = this.apiServiceImpl.getStayHandle(userId);
    return RetResultUtil.ok(list);
  }
  
  /**
   * @Descrption 批量审批
   * @author dongxu
   * @date 2018年8月6日下午5:19:03
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/batchApproval", method = RequestMethod.POST)
  public RetResult<AppCallResult> batchApproval(HttpServletRequest request,
      List<ExecuteContext> executeContextList) throws BusinessException{
    // 调用service实体，获得
    AppCallResult appCallResult = this.apiServiceImpl.batchApproval(executeContextList);
    return RetResultUtil.ok(appCallResult);
  }
  
  /**
   * @Descrption 获取定级信息
   * @author aran
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @param systemId 系统ID
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getSystemRelationInfo", method = RequestMethod.POST)
  public RetResult<List<SystemRelationResult>> getSystemRelationInfo(HttpServletRequest request,
      @RequestBody SystemRelationParam systemRelationParam) throws BusinessException{
    // 调用service实体，获得
    List<SystemRelationResult> systemApiResult = this.apiServiceImpl.getSystemRelationInfo(systemRelationParam);
    
    return RetResultUtil.ok(systemApiResult);
  }
}

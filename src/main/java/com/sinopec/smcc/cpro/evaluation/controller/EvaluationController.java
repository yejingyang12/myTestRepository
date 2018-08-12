/**
 * @Title CompanyController.java
 * @Package com.sinopec.smcc.cpro.evaluation.controller
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日上午11:06:01
 * @version V1.0
 */
package com.sinopec.smcc.cpro.evaluation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.PageUtil;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.evaluation.server.EvaluationService;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;

/**
 * @Title CompanyController.java
 * @Package com.sinopec.smcc.cpro.evaluation.controller
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日上午11:06:01
 * @version V1.0
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

  @Autowired
  private EvaluationService evaluationServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;

  /**
   * 查询测评列表
   * @author Aran
   * @date 2018年5月25日下午1:39:10
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryEvaluationList", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<PageUtil> queryEvaluationList(
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    //调用service实体，获得测评列表数据
    PageInfo<EvaluationListResult> page = 
        this.evaluationServiceImpl.queryEvaluationList(evaluationParam);
    //通过resultApi实体组成返回参数
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setCurrentPage(page.getPageNum());
//    result.setPagesize(page.getPageSize());
//    result.setData(page.getList());
//    result.setTotal(page.getTotal());
//    result.setTotalPages(page.getPages());
    
    PageUtil pageUtil = new PageUtil(page);
    return RetResultUtil.ok(pageUtil);
  }

  /**
   * 查询修改回显信息
   * @author Aran
   * @date 2018年5月25日下午1:39:10
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryEditEvaluation", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<EvaluationResult> queryEditEvaluation(
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    EvaluationResult evaluationResult = 
        this.evaluationServiceImpl.queryEditEvaluation(evaluationParam);
    //通过resultApi实体组成返回参数
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(evaluationResult);
    return RetResultUtil.ok(evaluationResult);
  }

  /**
   * 增加或修改测评信息
   * @author Aran
   * @date 2018年5月25日下午1:39:10
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveEvaluation", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> saveEvaluation(HttpServletRequest request,
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String strId = this.evaluationServiceImpl.saveEvaluation(userName, evaluationParam);
    // 通过resultApi实体组成返回参数
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(strId);
    return RetResultUtil.ok(strId);
  }

  /**
   * 删除测评信息
   * @author Aran
   * @date 2018年5月25日下午1:39:10
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/deleteEvaluation", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Void> deleteEvaluation(HttpServletRequest request,
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    // 调用service实体，获得
    this.evaluationServiceImpl.deleteEvaluation(userName, evaluationParam);
    // 通过resultApi实体组成返回参数
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return RetResultUtil.ok();
  }
  
  /**
   * 获得测评详情信息
   * @author Aran
   * @date 2018年5月28日下午3:43:15
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryDetailsEvaluation", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<EvaluationResult> queryDetailsEvaluation( 
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    EvaluationResult evaluationResult = 
        this.evaluationServiceImpl.queryDetailsEvaluation(evaluationParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(evaluationResult);
    return RetResultUtil.ok(evaluationResult);
  }
  
  /**
   * @Descrption 首页高级查询测评单位
   * @author dongxu
   * @date 2018年6月11日下午4:19:09
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryExamOrgCompany", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<List<EvaluationListResult>> queryExamOrgCompany(
      @RequestBody RecordsParam recordsParam) throws BusinessException {
    List<EvaluationListResult> evaluationListResultList = 
        this.evaluationServiceImpl.queryExamOrgCompany(recordsParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(evaluationListResultList);
    return RetResultUtil.ok(evaluationListResultList);
  }
}

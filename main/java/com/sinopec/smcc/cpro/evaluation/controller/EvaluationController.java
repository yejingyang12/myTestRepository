/**
 * @Title CompanyController.java
 * @Package com.sinopec.smcc.cpro.evaluation.controller
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日上午11:06:01
 * @version V1.0
 */
package com.sinopec.smcc.cpro.evaluation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.evaluation.server.EvaluationService;

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
  public ResultApi queryEvaluationList(HttpServletRequest request,
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    //调用service实体，获得测评列表数据
    PageInfo<EvaluationListResult> page = 
        this.evaluationServiceImpl.queryEvaluationList(evaluationParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setCurrentPage(page.getPageNum());
    result.setPagesize(page.getPageSize());
    result.setData(page.getList());
    result.setTotal(page.getTotal());
    result.setTotalPages(page.getPages());
    return result;
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
  public ResultApi queryEditEvaluation(HttpServletRequest request,
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    EvaluationResult evaluationResult = 
        this.evaluationServiceImpl.queryEditEvaluation(evaluationParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(evaluationResult);
    return result;
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
  public ResultApi saveEvaluation(HttpServletRequest request,
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    String strId = this.evaluationServiceImpl.saveEvaluation(evaluationParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(strId);
    return result;
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
  public ResultApi deleteEvaluation(HttpServletRequest request,
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    // 调用service实体，获得
    this.evaluationServiceImpl.deleteEvaluation(evaluationParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * 
   * @author Aran
   * @date 2018年5月28日下午3:43:15
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryDetailsEvaluation", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryDetailsEvaluation(HttpServletRequest request, 
      @RequestBody EvaluationParam evaluationParam) throws BusinessException {
    EvaluationResult evaluationResult = 
        this.evaluationServiceImpl.queryDetailsEvaluation(evaluationParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(evaluationResult);
    return result;
  }
}

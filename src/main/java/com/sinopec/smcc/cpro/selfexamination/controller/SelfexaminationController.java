/**
* Copyright 2018. 
* @Title SelfexaminationController.java
* @Package com.sinopec.smcc.cpro.selfexamination.controller
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午1:19:10
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.controller;

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
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationResult;
import com.sinopec.smcc.cpro.selfexamination.server.SelfexaminationService;

/**
 * @Title SelfexaminationController.java
 * @Package com.sinopec.smcc.cpro.selfexamination.controller
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午1:19:10
 * @version V1.0
 */
@Controller
@RequestMapping("/selfexamination")
public class SelfexaminationController {
  @Autowired
  private SelfexaminationService  selfexaminationServiceImpl;
  
  /**
   * @Descrption 自查信息列表
   * @author yejingyang
   * @date 2018年5月25日下午1:42:42
   * @param request
   * @param selfexaminationParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/querySelfexaminationList", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi querySelfexaminationList(HttpServletRequest request, 
      @RequestBody SelfexaminationParam selfexaminationParam) throws BusinessException{
    PageInfo<SelfexaminationListResult> page = this.selfexaminationServiceImpl.
        querySelfexaminationList(selfexaminationParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setCurrentPage(page.getPageNum());
    result.setPagesize(page.getPageSize());
    result.setTotalPages(page.getPages());
    result.setTotal(page.getTotal());
    result.setData(page.getList());
    return result;
  }
  
  /**
   * @Descrption 添加或修改自查信息
   * @author dongxu
   * @date 2018年5月30日上午11:48:52
   * @param selfexaminationParam
   * @param model
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveSelfexamination", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi saveSelfexamination(HttpServletRequest request, 
      @RequestBody SelfexaminationParam selfexaminationParam) throws BusinessException{
    String pkId = this.selfexaminationServiceImpl.
        saveSelfexamination(selfexaminationParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(pkId);
    return result;
  }
  
  /**
   * @Descrption 查询修改回显信息
   * @author dongxu
   * @date 2018年6月2日下午11:20:08
   * @param request
   * @param selfexaminationParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryEditSelfexamination", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryEditSelfexamination(HttpServletRequest request, 
      @RequestBody SelfexaminationParam selfexaminationParam) throws BusinessException{
    SelfexaminationResult selfexaminationResult = this.selfexaminationServiceImpl.
        queryEditSelfexamination(selfexaminationParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(selfexaminationResult);
    return result;
  }
  
  /**
   * @Descrption 删除自查信息（改删除状态）
   * @author yejingyang
   * @date 2018年6月6日上午10:57:56
   * @param request
   * @param selfexaminationParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/deleteSelfexaminationBySelfexaminationId", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi deleteSelfexaminationBySelfexaminationId(HttpServletRequest request, 
      @RequestBody SelfexaminationParam selfexaminationParam) throws BusinessException{
    this.selfexaminationServiceImpl.deleteSelfexaminationBySelfexaminationId(selfexaminationParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
}

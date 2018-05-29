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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
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
   * @Descrption
   * @author yejingyang
   * @date 2018年5月25日下午1:42:42
   * @param request
   * @param selfexaminationParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/querySelfexaminationList", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi querySelfexaminationList(HttpServletRequest request, 
      SelfexaminationParam selfexaminationParam) throws BusinessException{
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
  
  
  @RequestMapping(value = "/saveSelfexamination", method = RequestMethod.GET)
  @ResponseBody
  public String saveSelfexamination(SelfexaminationParam selfexaminationParam, 
      Model model) throws BusinessException{
    String pkId = this.selfexaminationServiceImpl.
        saveOrUpdateSelfexamination(selfexaminationParam);
    return pkId;
  }
}

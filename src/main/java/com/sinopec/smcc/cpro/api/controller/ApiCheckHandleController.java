/**
 * @Title ApiCheckHandleController.java
 * @Package com.sinopec.smcc.cpro.api.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午2:41:01
 * @version V1.0
 */
package com.sinopec.smcc.cpro.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.service.ApiCheckHandleService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;

/**
 * @Title ApiCheckHandleController.java
 * @Package com.sinopec.smcc.cpro.api.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午2:41:01
 * @version V1.0
 */
@RestController
@RequestMapping("/restful/checkNHandleApi")
public class ApiCheckHandleController {

  @Autowired
  private CheckService checkServiceImpl;
  
  @Autowired
  private ApiCheckHandleService apiCheckHandleServiceImpl;
  
  @RequestMapping(value = "/queryCheckHandleApi", method = RequestMethod.POST)
  public List<CheckListResult> queryCheckHandleApi(String userId, 
      HttpServletRequest request) throws BusinessException{
    //调用service实体获得方法，CheckListResult填写返回的参数
    CheckParam checkParam = new CheckParam();
    checkParam.setHandlingState(1);
    request.getSession().setAttribute("userId", userId);
    PageInfo<CheckListResult> pageInfo = this.checkServiceImpl.queryCheckList(checkParam);
    //通过resultApi实体组成返回参数
    return pageInfo.getList();
  }
  
  @RequestMapping(value = "/batchCheckHandleApi", method = RequestMethod.POST)
  public List<String> batchCheckHandleApi(List<CheckParam> checkList, String userId, 
      HttpServletRequest request) throws BusinessException{
    //调用service实体获得方法，CheckListResult填写返回的参数
    CheckParam checkParam = new CheckParam();
    checkParam.setHandlingState(1);
    List<String> idList = this.apiCheckHandleServiceImpl.saveCheck(checkParam);
    //通过resultApi实体组成返回参数
    return idList;
  }
}

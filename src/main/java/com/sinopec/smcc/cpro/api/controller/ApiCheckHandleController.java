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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.BatchCheckHandleParam;
import com.sinopec.smcc.cpro.api.service.ApiCheckHandleService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;

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
  private ApiCheckHandleService apiCheckHandleServiceImpl;
  
  @RequestMapping(value = "/queryCheckHandleApi", method = RequestMethod.GET)
  public List<CheckListResult> queryCheckHandleApi(String userId, 
      HttpServletRequest request) throws BusinessException{
    //调用service实体获得方法，CheckListResult填写返回的参数
    List<CheckListResult> checkList = this.apiCheckHandleServiceImpl.queryCheckList(request, userId);
    //通过resultApi实体组成返回参数
    return checkList;
  }
  
  @RequestMapping(value = "/batchCheckHandleApi", method = RequestMethod.POST)
  public List<String> batchCheckHandleApi(@RequestBody BatchCheckHandleParam batchCheckHandleParam, 
      HttpServletRequest request) throws BusinessException{
    //调用service实体获得方法，CheckListResult填写返回的参数
    List<String> idList = this.apiCheckHandleServiceImpl.saveCheck(
        batchCheckHandleParam, request);
    //通过resultApi实体组成返回参数
    return idList;
  }
}

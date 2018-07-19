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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;
import com.sinopec.smcc.cpro.api.service.ApiService;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.server.DiagramService;

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
  @RequestMapping(value = "/querySystemTrendByYear", method = RequestMethod.POST)
  public ResultApi querySystemTrendByYear(HttpServletRequest request,
      @ModelAttribute("diagramParam") DiagramParam diagramParam) throws BusinessException{
    // 调用service实体，获得
    List<DiagramListResult> diagramListResult = this.diagramServiceImpl.
        querySystemTrendByYear(diagramParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(diagramListResult);
    return result;
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
  public ResultApi getGradingInformation(HttpServletRequest request,
      @RequestParam("systemId") String systemId) throws BusinessException{
    // 调用service实体，获得
    GradingApiResult gradingApiResult = this.apiServiceImpl.getGradingInformation(systemId);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(gradingApiResult);
    return result;
  }
}

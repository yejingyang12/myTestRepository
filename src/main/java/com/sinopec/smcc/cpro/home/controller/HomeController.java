/**
* 2018. 
* @Title HomeController.java
* @Package com.sinopec.smcc.cpro.home.controller
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:18:01
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.home.entity.HomeParam;
import com.sinopec.smcc.cpro.home.entity.HomeResult;
import com.sinopec.smcc.cpro.home.server.HomeService;

/**
 * @Title HomeController.java
 * @Package com.sinopec.smcc.cpro.home.controller
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:18:01
 * @version V1.0
 */
@Controller
@RequestMapping("/home")
public class HomeController {
  
  @Autowired
  public HomeService homeService;
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/homeRecordStatistics", method = RequestMethod.POST)
  public ResultApi uploadFile(HttpServletRequest request,HomeParam hParam) throws BusinessException{
    // 调用service实体，获得
    PageInfo<HomeResult> page = this.homeService.homeRecordStatisticsList(hParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setCurrentPage(page.getPageNum());
    result.setPagesize(page.getPageSize());
    result.setData(page.getList());
    result.setTotal(page.getTotal());
    result.setTotalPages(page.getPages());
    return result;
  }
  
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/homeScoreStatistics", method = RequestMethod.POST)
  public ResultApi homeScoreStatistics(HttpServletRequest request,HomeParam hParam) throws BusinessException{
    // 调用service实体，获得
    PageInfo<HomeResult> page = this.homeService.homeScoreStatisticsList(hParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setCurrentPage(page.getPageNum());
    result.setPagesize(page.getPageSize());
    result.setData(page.getList());
    result.setTotal(page.getTotal());
    result.setTotalPages(page.getPages());
    return result;
  }

}

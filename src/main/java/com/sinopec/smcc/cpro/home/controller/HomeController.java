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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
  public RetResult<PageUtil> uploadFile(HomeParam hParam) throws BusinessException{
    // 调用service实体，获得
    PageInfo<HomeResult> page = this.homeService.homeRecordStatisticsList(hParam);
    // 通过resultApi实体组成返回参数
    PageUtil pageUtil = new PageUtil(page);
    return RetResultUtil.ok(pageUtil);
  }
  
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/homeScoreStatistics", method = RequestMethod.POST)
  public RetResult<PageUtil> homeScoreStatistics(HomeParam hParam) throws BusinessException{
    // 调用service实体，获得
    PageInfo<HomeResult> page = this.homeService.homeScoreStatisticsList(hParam);
    // 通过resultApi实体组成返回参数

    PageUtil pageUtil = new PageUtil(page);
    return RetResultUtil.ok(pageUtil);
  }

}

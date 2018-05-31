/**
* @Title MainController.java
* @Package com.sinopec.smcc.cpro.main.controller
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:43:26
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.controller;

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
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;

/**
 * @Title MainController.java
 * @Package com.sinopec.smcc.cpro.main.controller
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:43:26
 * @version V1.0
 */
@Controller
@RequestMapping("/main")
public class MainController {
  
  @Autowired
  private MainService mainServiceImpl;
  
  /**
   * @Descrption 系统信息列表
   * @author eric
   * @date 2018年5月25日下午1:35:35
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryMainList", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryMainList(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException{
    //调用service实体，获得
    PageInfo<MainListResult> page = this.mainServiceImpl.queryMainList(mainParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    //当前页码
    result.setCurrentPage(page.getPageNum());
    //每页数据数量
    result.setPagesize(page.getPageSize());
    //总页数
    result.setTotalPages(page.getPages());
    //总条数
    result.setTotal(page.getTotal());
    //响应的数据
    result.setData(page.getList());
    return result;
  }
  
  /**
   * @Descrption 删除系统信息
   * @author eric
   * @date 2018年5月25日下午1:35:35
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/deleteMainBySystemId", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi deleteMainBySystemId(MainParam mainParam) throws BusinessException{
    this.mainServiceImpl.deleteMainBySystemId(mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
 
}

/**
* 2018. 
* @Title SystemController.java
* @Package com.sinopec.smcc.cpro.system.controller
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:42:32
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.system.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.system.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.server.SystemService;

/**
 * @Title SystemController.java
 * @Package com.sinopec.smcc.cpro.system.controller
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:42:32
 * @version V1.0
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private SystemService systemServiceImpl;
	
	/**
	 * 
	 * 系统信息列表
	 * @author hanxin
	 * @date 2018年5月27日下午4:26:10
	 * @param request
	 * @param systemParam
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/querySystemList", method = RequestMethod.POST)
	@ResponseBody
	public ResultApi querySystemList(
			HttpServletRequest request,SystemParam systemParam) throws BusinessException{
		//调用service实体获得方法，CompanyListResult填写返回的参数
		PageInfo<SystemListResult> page = this.systemServiceImpl.querySystemList(systemParam);
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
	 * 
	 * 添加或修改系统信息
	 * @author hanxin
	 * @date 2018年5月27日下午4:27:15
	 * @param systemListResult
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/saveSystem", method =  RequestMethod.GET)
	@ResponseBody
	public ResultApi saveSystem(HttpServletRequest request,SystemParam systemParam) throws  BusinessException{
		String systemId = this.systemServiceImpl.saveSystem(systemParam);
		ResultApi result = new ResultApi(EnumResult.SUCCESS);
		result.setData(systemId);
		return result;
	}
	
  /**
   * 查询系统代码信息
   * @author dongxu
   * @date 2018年5月27日下午1:39:46
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/querySystemCodeList", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi querySystemCodeList(SystemCodeParam systemCodeParam)
      throws BusinessException {
    List<SystemCodeListResult> systemCodeListResultList = this.systemServiceImpl.querySystemCodeList(systemCodeParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(systemCodeListResultList);
    return result;
  }
  /**
   * 
   * 查询系统信息详情
   * @author hanxin
   * @date 2018年5月28日下午5:08:18
   * @param systemParam
   * @return
   */
  @RequestMapping(value = "/queryDetailsSystem", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi queryDetailsSystem(HttpServletRequest request,SystemParam systemParam){
  	SystemListResult systemListResult = this.systemServiceImpl.queryDetailsSystem(systemParam);
  	ResultApi result = new ResultApi(EnumResult.SUCCESS);
  	result.setData(systemListResult);
  	return result;
  }
  
  /**
   * 
   * 修改系统信息查询详情
   * @author hanxin
   * @date 2018年5月28日下午5:44:39
   * @param request
   * @param systemParam
   * @return
   */
  @RequestMapping(value = "/queryEditSystem", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi queryEditSystem(HttpServletRequest request,SystemParam systemParam){
    SystemListResult systemListResult = this.systemServiceImpl.queryEditSystem(systemParam);
  	ResultApi result = new ResultApi(EnumResult.SUCCESS);
  	result.setData(systemListResult);
  	return result;
  }
  
}

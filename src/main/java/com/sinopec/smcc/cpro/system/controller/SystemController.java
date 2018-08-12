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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.PageUtil;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.system.entity.SystemEchoParam;
import com.sinopec.smcc.cpro.system.entity.SystemEchoResult;
import com.sinopec.smcc.cpro.system.entity.SystemGradingChangeResult;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
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
  @Autowired
  private NodeService nodeServiceImpl;

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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<PageUtil> querySystemList(HttpServletRequest request,
      @RequestBody SystemParam systemParam) throws BusinessException{
    //调用service实体获得方法，CompanyListResult填写返回的参数
    PageInfo<SystemListResult> page = this.systemServiceImpl.querySystemList(systemParam);
    //通过resultApi实体组成返回参数
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    //当前页码
//    result.setCurrentPage(page.getPageNum());
//    //每页数据数量
//    result.setPagesize(page.getPageSize());
//    //总页数
//    result.setTotalPages(page.getPages());
//    //总条数
//    result.setTotal(page.getTotal());
//    //响应的数据
//    result.setData(page.getList());
    PageUtil pageUtil = new PageUtil(page);
    return RetResultUtil.ok(pageUtil);
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
  @RequestMapping(value="/saveSystem", method =  RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> saveSystem(HttpServletRequest request, @RequestBody SystemParam systemParam) 
      throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String systemId = this.systemServiceImpl.saveSystem(userName, systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(systemId);
    return RetResultUtil.ok(systemId);
  }
	
  /**
   * 
   * 查询系统信息详情
   * @author hanxin
   * @date 2018年5月28日下午5:08:18
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryDetailsSystem", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemResult> queryDetailsSystem(HttpServletRequest request,
      @RequestBody SystemParam systemParam) throws BusinessException{
    SystemResult systemResult = this.systemServiceImpl.queryDetailsSystem(systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(systemResult);
    return RetResultUtil.ok(systemResult);
  }
  
  /**
   * 
   * 修改系统信息
   * @author hanxin
   * @date 2018年5月28日下午5:44:39
   * @param request
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/editSystem", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> editSystem(HttpServletRequest request,
      @RequestBody SystemParam systemParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String systemResult = this.systemServiceImpl.editSystem(userName,systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(systemResult);
    return RetResultUtil.ok(systemResult);
  }
  
  /**
   * 
   * 修改系统信息页面
   * @author hanxin
   * @date 2018年6月8日下午4:48:35
   * @param request
   * @param systemParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryEditSystem", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemResult> queryEditDetailsSystem(HttpServletRequest request,
      @RequestBody SystemParam systemParam) throws BusinessException{
    SystemResult systemResult = this.systemServiceImpl.queryEditSystem(systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(systemResult);
    return RetResultUtil.ok(systemResult);
  }
  /**
   * 
   * 系统模板导出
   * @author hanxin
   * @date 2018年6月6日上午10:11:09
   * @param request
   * @param systemParam
   * @return
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @RequestMapping(value = "/exportExcelForSystemTemplate", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<AttachResult> exportExcelForSystemTemplate(HttpServletRequest request, 
      @RequestBody  SystemParam systemParam) throws FileNotFoundException, IOException{
    AttachResult attachResult = this.systemServiceImpl.exportExcelForSystemTemplate(systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return RetResultUtil.ok(attachResult);
  }
  
  /**
   * 
   * 系统模板导入
   * @author hanxin
   * @date 2018年6月6日上午10:11:32
   * @param request
   * @return
   * @throws IOException
   * @throws BusinessException
   */
  @RequestMapping(value = "/importForSystemTemplate", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<EnumResult> importForSystemTemplate(HttpServletRequest request,@RequestParam("strFilePath")String strFilePath) 
      throws IOException, BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    Boolean IsImprotInfo=this.systemServiceImpl.importForSystemTemplate(userName,strFilePath);
//    ResultApi result=null;
    if(IsImprotInfo){
      return RetResultUtil.ok(EnumResult.SUCCESS);
//      result = new ResultApi(EnumResult.SUCCESS);
    }else{
//      result = new ResultApi(EnumResult.ERROR);
      return RetResultUtil.error(EnumResult.ERROR);
    }
  }
  
  /**
   * 
   * 下载
   * @author hanxin
   * @date 2018年6月6日下午6:14:01
   * @param request
   * @param response
   * @param strFilePath
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/exportUploadSystemInfo", method = RequestMethod.GET)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Void> exportUploadSystemInfo(HttpServletRequest request
      ,HttpServletResponse response, String strFilePath) throws BusinessException{
    this.systemServiceImpl.exportUploadSystemInfo(request,response,strFilePath);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return RetResultUtil.ok();
  } 
  
  /**
   * 
   * 系统定级变更审核
   * @author hanxin
   * @date 2018年6月12日上午10:49:23
   * @param request
   * @param systemParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryGradingEditAudit", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemGradingChangeResult> queryGradingEditAudit(HttpServletRequest request,
      @RequestBody SystemParam systemParam) throws BusinessException{
    SystemGradingChangeResult systemResult = this.systemServiceImpl.queryGradingEditAudit(systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(systemResult);
    return RetResultUtil.ok(systemResult);
  }
  
  /**
   * @Descrption 通过系统ID 获取系统信息
   * @author dongxu
   * @date 2018年6月21日上午11:22:41
   * @param request
   * @param systemParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/querySystemInformationBySystemId", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<SystemResult> querySystemInformationBySystemId(HttpServletRequest request,
      @RequestBody SystemParam systemParam) throws BusinessException{
    SystemResult systemResult = this.systemServiceImpl.querySystemInformationBySystemId(systemParam);
//    ResultApi result = new ResultApi(EnumResult.SUCCESS);
//    result.setData(systemResult);
    return RetResultUtil.ok(systemResult);
  }
  
  
  /**
   * 获取维护单位系统信息导出回显
   * @Descrption
   * @author yejingyang
   * @date 2018年6月11日下午5:02:58
   * @param request
   * @param systemParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/querySystemInfoEchoList", method = RequestMethod.POST)
  public RetResult<List<SystemEchoResult>> querySystemInfoEchoList(
      @RequestBody SystemEchoParam systemEchoParam) throws BusinessException{
    List<SystemEchoResult> systemGradingInfoResultList = this.systemServiceImpl.
        querySystemInfoEchoList(systemEchoParam);
    
    return RetResultUtil.ok(systemGradingInfoResultList);
  }
}

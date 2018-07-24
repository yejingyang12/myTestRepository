/**
* @Title MainController.java
* @Package com.sinopec.smcc.cpro.main.controller
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:43:26
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

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
  public ResultApi deleteMainBySystemId(@RequestBody MainParam mainParam) throws BusinessException{
    this.mainServiceImpl.deleteMainBySystemId(mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * 一键导出
   * @Descrption
   * @author dongxu
   * @date 2018年6月4日上午10:34:35
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/exportExcelForMain", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi exportExcelForMain(HttpServletRequest request) throws BusinessException{
    String filePath = this.mainServiceImpl.exportExcelForMain(request);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(filePath);
    return result;
  }
  
  /**
   * 下载
   * @Descrption
   * @author dongxu
   * @date 2018年6月4日上午10:34:35
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/exportUploadApplicationInfo", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi exportUploadApplicationInfo(HttpServletRequest request
      ,HttpServletResponse response,@RequestBody String strFilePath) throws BusinessException{
    this.mainServiceImpl.exportUploadApplicationInfo(request,response,strFilePath);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * 定级模板导出
   * @Descrption
   * @author dongxu
   * @date 2018年6月4日下午5:48:12
   * @param request 
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/exportExcelForGradeTemplate", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi exportExcelForGradeTemplate(HttpServletRequest request,
      HttpServletResponse response,String [] systemIds) throws BusinessException{
    this.mainServiceImpl.exportExcelForGradeTemplate(response,systemIds);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * 一键下载（表1 单位信息）
   * @Descrption
   * @author dongxu
   * @date 2018年6月7日下午12:02:12
   * @param request
   * @param mainParam 单位ID
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/tableCompany", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi tableCompany(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap= this.mainServiceImpl.tableCompany(request,mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(resultMap.get("url"));
    return result;
  }
  
  /**
   * 一键下载（表2 系统信息）
   * @Descrption
   * @author dongxu
   * @date 2018年6月7日下午12:02:12
   * @param request
   * @param mainParam 系统ID
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/tableSystem", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi tableSystem(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap = this.mainServiceImpl.tableSystem(request,mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(resultMap.get("url"));
    return result;
  }
  
  /**
   * @Descrption 一键下载（表3 定级信息）
   * @author dongxu
   * @date 2018年6月9日下午5:14:16
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/tableGrading", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi tableGrading(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap = this.mainServiceImpl.tableGrading(request,mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(resultMap.get("url"));
    return result;
  }
  
  /**
  * @Descrption 一键下载（表4 附件信息）
  * @author dongxu
  * @date 2018年6月10日上午9:09:22
  * @param request
  * @param mainParam
  * @return
  * @throws BusinessException
  */
  @RequestMapping(value = "/tableAttach", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi tableAttach(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap = this.mainServiceImpl.tableAttach(request,mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(resultMap.get("url"));
    return result;
  }
  
  /**
   * @Descrption 一键下载
   * @author dongxu
   * @date 2018年6月10日下午3:34:45
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/oneButtonDownloading", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi oneButtonDownloading(HttpServletRequest request,HttpServletResponse response,
      @RequestBody MainParam mainParam) throws BusinessException {
    String filePath = this.mainServiceImpl.oneButtonDownloading(request,response,mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(filePath);
    return result;
  }
  
  /**
   * @Descrption 高级搜索获取所有系统名称
   * @author dongxu
   * @date 2018年6月11日上午11:31:47
   * @param request
   * @param response
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/querySystemName", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi querySystemName(HttpServletRequest request
      ,@RequestBody MainParam mainParam) 
      throws BusinessException {
    List<MainListResult> mainList = this.mainServiceImpl.querySystemName(mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(mainList);
    return result;
  }
  
  /**
   * @Descrption 定级模板导入
   * @author dongxu
   * @date 2018年6月12日下午5:12:21
   * @param request
   * @param file
   * @return
   * @throws BusinessException
   */  
  @RequestMapping(value = "/importExcelForGradeTemplate", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi importExcelForGradeTemplate(HttpServletRequest request,
      @RequestBody String file) 
      throws BusinessException {
    this.mainServiceImpl.importExcelForGradeTemplate(file);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * @Descrption 添加申请变更（弹窗）
   * @author dongxu
   * @date 2018年6月13日下午5:47:37
   * @param request
   * @param file
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryApplicationChange", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryApplicationChange(HttpServletRequest request,
      @RequestBody MainParam mainParam) 
      throws BusinessException {
    String systemId = this.mainServiceImpl.queryApplicationChange(mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(systemId);
    return result;
  }
  
  /**
   * @Descrption 系统等保等级分布统计图
   * @author dongxu
   * @date 2018年6月25日上午10:03:22
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryGradingStatistics", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi gradingStatistics(HttpServletRequest request,@RequestBody MainParam mainParam) 
      throws BusinessException {
    List<MainListResult> gradingStatisticsResult = 
        this.mainServiceImpl.queryGradingStatistics(mainParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(gradingStatisticsResult);
    return result;
  }
  
  /**
   * @Descrption 获取用户信息
   * @author Aran
   * @date 2018年7月23日下午6:49:33
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi getUserInfo(HttpServletRequest request) 
      throws BusinessException {
    UserDTO userInfo = 
        this.mainServiceImpl.getUserInfo();
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(userInfo);
    return result;
  }
  
  /**
   * @Descrption 退出登录
   * @author Aran
   * @date 2018年7月23日下午6:49:50
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi logout(HttpServletRequest request) 
      throws BusinessException {
    request.getSession().invalidate(); 
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(null);
    return result;
  }
}

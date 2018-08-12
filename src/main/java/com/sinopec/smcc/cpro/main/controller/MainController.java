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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.annotation.LoginUser;
import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.interceptor.User;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.PageUtil;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.server.NodeService;

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
  @Autowired
  private NodeService nodeServiceImpl;
  
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<PageUtil> queryMainList(
      @RequestBody MainParam mainParam) throws BusinessException{
    //调用service实体，获得
    PageInfo<MainListResult> page = this.mainServiceImpl.queryMainList(mainParam);
    //通过resultApi实体组成返回参数

    PageUtil pageUtil = new PageUtil(page);
    return RetResultUtil.ok(pageUtil);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Void> deleteMainBySystemId(@RequestBody MainParam mainParam) throws BusinessException{
    this.mainServiceImpl.deleteMainBySystemId(mainParam);

    return RetResultUtil.ok();
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> exportExcelForMain(HttpServletRequest request) throws BusinessException{
    String filePath = this.mainServiceImpl.exportExcelForMain(request);

    return RetResultUtil.ok(filePath);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Void> exportUploadApplicationInfo(HttpServletRequest request
      ,HttpServletResponse response,@RequestBody String strFilePath) throws BusinessException{
    this.mainServiceImpl.exportUploadApplicationInfo(request,response,strFilePath);

    return RetResultUtil.ok();
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
  @RequestMapping(value = "/exportExcelForGradeTemplate", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<AttachResult> exportExcelForGradeTemplate(HttpServletRequest request,
      HttpServletResponse response,@RequestBody MainParam mainParam) throws BusinessException{
    AttachResult attachResult = this.mainServiceImpl.
        exportExcelForGradeTemplate(request, response,mainParam.getSystemIds());

    return RetResultUtil.ok(attachResult);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Object> tableCompany(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap= this.mainServiceImpl.tableCompany(request,mainParam);

    return RetResultUtil.ok(resultMap.get("url"));
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Object> tableSystem(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap = this.mainServiceImpl.tableSystem(request,mainParam);

    return RetResultUtil.ok(resultMap.get("url"));
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Object> tableGrading(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap = this.mainServiceImpl.tableGrading(request,mainParam);

    return RetResultUtil.ok(resultMap.get("url"));
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Object> tableAttach(HttpServletRequest request,
      @RequestBody MainParam mainParam) throws BusinessException {
    Map<String,Object> resultMap = this.mainServiceImpl.tableAttach(request,mainParam);

    return RetResultUtil.ok(resultMap.get("url"));
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> oneButtonDownloading(HttpServletRequest request,HttpServletResponse response,
      @RequestBody MainParam mainParam) throws BusinessException {
    String filePath = this.mainServiceImpl.oneButtonDownloading(request,response,mainParam);

    return RetResultUtil.ok(filePath);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<List<MainListResult>> querySystemName(HttpServletRequest request
      ,@RequestBody MainParam mainParam) 
      throws BusinessException {
    List<MainListResult> mainList = this.mainServiceImpl.querySystemName(mainParam);

    return RetResultUtil.ok(mainList);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<Void> importExcelForGradeTemplate(HttpServletRequest request,
      @RequestParam("file") MultipartFile file) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    this.mainServiceImpl.importExcelForGradeTemplate(request, file, userName);

    return RetResultUtil.ok();
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> queryApplicationChange(HttpServletRequest request,
      @RequestBody MainParam mainParam) 
      throws BusinessException {
    String systemId = this.mainServiceImpl.queryApplicationChange(mainParam);

    return RetResultUtil.ok(systemId);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<List<MainListResult>> gradingStatistics(@RequestBody MainParam mainParam) 
      throws BusinessException {
    List<MainListResult> gradingStatisticsResult = 
        this.mainServiceImpl.queryGradingStatistics(mainParam);
    System.out.println(gradingStatisticsResult);
    return RetResultUtil.ok(gradingStatisticsResult);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<User> getUserInfo(@LoginUser User loginUser,HttpServletRequest request) 
      throws BusinessException {

    return RetResultUtil.ok(loginUser);
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
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  public RetResult<String> logout(HttpServletRequest request) 
      throws BusinessException {
    request.getSession().invalidate(); 
    
    String strValue = 
        this.mainServiceImpl.logout();

    return RetResultUtil.ok(strValue);
  }
}

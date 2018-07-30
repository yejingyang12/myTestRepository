/**
 * 2018. 
 * @Title CheckController.java
 * @Package com.sinopec.smcc.cpro.review.controller
 * @Description: TODO:审核管理controller
 * @author zhouyu
 * @date 2018年5月25日下午1:25:15
 * @version V1.0
 */
package com.sinopec.smcc.cpro.review.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;

@Controller
@RequestMapping("/checkController")
public class CheckController {

  @Autowired
  private CheckService checkServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;
  
  /**
   * @Descrption获取备案信息列表数据
   * @author yejingyang
   * @date 2018年6月8日下午1:22:47
   * @param request
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryCheckList", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi queryCheckList(HttpRequest request,@RequestBody CheckParam checkParam) 
      throws BusinessException {
    // 调用service实体获得方法，CheckListResult填写返回的参数
    PageInfo<CheckListResult> pageInfo = this.checkServiceImpl.queryCheckList(checkParam);
    // 通过resultApi实体组成返回参数
    ResultApi resultApi = new ResultApi(EnumResult.SUCCESS);
    // 当前页码
    resultApi.setCurrentPage(pageInfo.getPageNum());
    // 每页数据数量
    resultApi.setPagesize(pageInfo.getPageSize());
    // 总页数
    resultApi.setTotalPages(pageInfo.getPages());
    // 总条数
    resultApi.setTotal(pageInfo.getTotal());
    // 响应的数据
    resultApi.setData(pageInfo.getList());
    
    return resultApi;
  }
  
  /**
   * @Descrption  企业管理员定级审核
   * @author yejingyang
   * @date 2018年6月8日下午12:01:06
   * @param request
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveGradCheck", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveGradCheck(HttpServletRequest request,
      @RequestBody CheckParam checkParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.checkServiceImpl.saveGradCheck(userName, checkParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(fkSystemId);
    return result;
  }
  /**
   * @Descrption  总部管理员定级审核
   * @author yejingyang
   * @date 2018年6月8日下午12:01:06
   * @param request
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveHeadGradCheck", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveHeadGradCheck(HttpServletRequest request,
      @RequestBody CheckParam checkParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.checkServiceImpl.saveHeadGradCheck(userName, checkParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(fkSystemId);
    return result;
  }
  
  /**
   * @Descrption  企业管理员定级变更审核
   * @author yejingyang
   * @date 2018年6月8日下午3:54:30
   * @param request
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveGradChangeCheck", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveGradChangeCheck(HttpServletRequest request,
      @RequestBody CheckParam checkParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.checkServiceImpl.saveGradChangeCheck(userName, checkParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(fkSystemId);
    return result;
  }
  /**
   * @Descrption  总部管理员定级变更审核
   * @author yejingyang
   * @date 2018年6月8日下午3:54:30
   * @param request
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveHeadGradChangeCheck", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveHeadGradChangeCheck(HttpServletRequest request,
      @RequestBody CheckParam checkParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.checkServiceImpl.saveHeadGradChangeCheck(userName, checkParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(fkSystemId);
    return result;
  }
  
  /**
   * @Descrption  总部管理员撤销备案审核
   * @author yejingyang
   * @date 2018年6月8日下午4:16:57
   * @param request
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveCancelRecordsCheck", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveCancelRecordsCheck(HttpServletRequest request,
      @RequestBody CheckParam checkParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.checkServiceImpl.saveCancelRecordsCheck(userName, checkParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(fkSystemId);
    return result;
  }
}

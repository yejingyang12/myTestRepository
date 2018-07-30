/**
* 2018. 
* @Title RecordsController.java
* @Package com.sinopec.smcc.cpro.records.controller
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:43:02
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsDetailResult;
import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RevokeRecordsResult;
import com.sinopec.smcc.cpro.records.server.RecordsService;

/**
 * @Title RecordsController.java
 * @Package com.sinopec.smcc.cpro.records.controller
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:43:02
 * @version V1.0
 */
@Controller
@RequestMapping("/records")
public class RecordsController {
  @Autowired
  private RecordsService recordsServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;
  
  /**
   * @Descrption 添加或修改备案信息
   * @author dongxu
   * @date 2018年5月29日下午5:41:15
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveRecords", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveRecords(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String fkSystemId = this.recordsServiceImpl.saveRecords(userName, recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(fkSystemId);
    return result;
  }
  
  /**
   * @Descrption 查询回显备案信息
   * @author dongxu
   * @date 2018年5月29日下午5:41:15
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryRecords", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi queryRecords(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    RecordsResult recordsResult = 
        this.recordsServiceImpl.queryRecordsByFkSystemId(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(recordsResult);
    return result;
  }
  
  /**
   * @Descrption 企业系统点击撤销备案，填写信息后保存
   * @author yejingyang
   * @date 2018年6月9日上午10:17:40
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveRevokeRecordsInfo", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveRevokeRecordsInfo(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    this.recordsServiceImpl.saveRevokeRecordsInfo(userName, recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  /**
   * @Descrption 总部系统点击撤销备案，填写信息后保存
   * @author yejingyang
   * @date 2018年6月9日上午10:17:40
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveHeadRevokeRecordsInfo", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi saveHeadRevokeRecordsInfo(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    this.recordsServiceImpl.saveHeadRevokeRecordsInfo(userName, recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * @Descrption 获取撤销备案回显信息
   * @author yejingyang
   * @date 2018年6月9日上午10:11:30
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryRevokeRecords", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi queryRevokeRecords(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    RevokeRecordsResult revokeRecordsResult = this.recordsServiceImpl.
        queryRevokeRecords(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(revokeRecordsResult);
    return result;
  }
  
  /**
   * @Descrption 修改备案状态
   * @author dongxu
   * @date 2018年5月30日上午11:15:50
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/editRecordsForStatus", method = RequestMethod.POST)
  @ResponseBody 
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi editRecordsStatus(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    this.recordsServiceImpl.editRecordsForStatus(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * @Descrption 查询备案详情
   * @author yejingyang
   * @date 2018年6月10日上午10:16:38
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryRecordsDetail", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi queryRecordsDetail(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException{
    RecordsDetailResult recordsDetailResult = 
        this.recordsServiceImpl.queryRecordsDetail(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(recordsDetailResult);
    return result;
  }
  
  /**
   * @Descrption 高级搜索获取受理备案单位
   * @author dongxu
   * @date 2018年6月11日下午3:38:51
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryRecordCompany", method = RequestMethod.POST)
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  public ResultApi queryRecordCompany(HttpServletRequest request,
      @RequestBody RecordsParam recordsParam) throws BusinessException {
    List<RecordsListResult> recordsListResultList = 
        this.recordsServiceImpl.queryRecordCompany(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(recordsListResultList);
    return result;
  }
}
  

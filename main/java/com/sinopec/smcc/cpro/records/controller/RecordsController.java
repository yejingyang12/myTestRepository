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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
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
  
  /**
   * @Descrption 添加或修改备案信息
   * @author dongxu
   * @date 2018年5月29日下午5:41:15
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveRecords", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi saveRecords(HttpServletRequest request,
      RecordsParam recordsParam) throws BusinessException{
    String recordsId = this.recordsServiceImpl.saveRecords(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(recordsId);
    return result;
  }
  
  /**
   * @Descrption 查询备案信息
   * @author dongxu
   * @date 2018年5月29日下午5:41:15
   * @param request
   * @param recordsParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryRecords", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi queryRecords(HttpServletRequest request,
      RecordsParam recordsParam) throws BusinessException{
    RecordsListResult recordsListResult = this.recordsServiceImpl.queryRecordsByFkSystemId(recordsParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(recordsListResult);
    return result;
  }
  
}
  

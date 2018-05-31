/**
* 2018. 
* @Title RecordsServiceImpl.java
* @Package com.sinopec.smcc.cpro.records.server.impl
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:59:54
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.server.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.mapper.RecordsMapper;
import com.sinopec.smcc.cpro.records.server.RecordsService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title RecordsServiceImpl.java
 * @Package com.sinopec.smcc.cpro.records.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:59:54
 * @version V1.0
 */
@Service
public class RecordsServiceImpl implements RecordsService{
  
  @Autowired
  private RecordsMapper recordsMapper;
  
  @Autowired
  private SystemMapper systemMapper;
  
  /**
   * 修改或添加备案信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  @Transactional
  public String saveRecords(RecordsParam recordsParam) throws BusinessException {
    if(StringUtils.isBlank(recordsParam.getRecordsId())) {
      recordsParam.setRecordsId(Utils.getUuidFor32());
      recordsParam.setCreateTime(new Date());
      recordsParam.setDeleteStatus(1);
      if(StringUtils.isBlank(recordsParam.getFkSystemId())){
        throw new BusinessException(EnumResult.LINKEDID_ERROR);
      }
      //将状态改为已完成
      recordsParam.setRecordStatus(3);
      this.editRecordsForStatus(recordsParam);
    }else{
      recordsParam.setCreateTime(new Date());
    }
    this.recordsMapper.insertRecords(recordsParam);
    return recordsParam.getRecordsId();
  }
  
  /**
   * 通过系统id查询备案信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  public RecordsResult queryRecordsByFkSystemId(RecordsParam recordsParam) throws BusinessException{
    RecordsResult records = this.recordsMapper.selectRecordsByFkSystemId(recordsParam);
    return records;
  }

 /**
  * 撤销备案
  */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  @Transactional
  public void editRecords(RecordsParam recordsParam) {
    this.recordsMapper.updateRecordsBySystemId(recordsParam);
    //将状态改为已撤销
    recordsParam.setRecordStatus(4);
    this.editRecordsForStatus(recordsParam);
  }
  
  /**
   * 修改备案状态
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  @Transactional
  public void editRecordsForStatus(RecordsParam recordsParam) {
    SystemParam systemParam = new SystemParam();
    systemParam.setRecordStatus(recordsParam.getRecordStatus());
    systemParam.setSystemId(recordsParam.getFkSystemId());
    this.systemMapper.updateSystemStatusBySystemId(systemParam);
  }
}

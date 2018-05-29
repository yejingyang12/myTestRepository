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
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.mapper.RecordsMapper;
import com.sinopec.smcc.cpro.records.server.RecordsService;
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
  
  @Override
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  @Transactional
  public String saveRecords(RecordsParam recordsParam) {
    if(StringUtils.isBlank(recordsParam.getRecordsId())) {
      recordsParam.setRecordsId(Utils.getUuidFor32());
      recordsParam.setCreateTime(new Date());
      recordsParam.setDeleteStatus(1);
    }else{
      recordsParam.setCreateTime(new Date());
    }
    recordsMapper.insertRecords(recordsParam);
    return recordsParam.getRecordsId();
  }
  
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  public RecordsListResult queryRecordsByFkSystemId(RecordsParam recordsParam) {
    RecordsListResult recordsListResult = recordsMapper.selectRecordsByFkSystemId(recordsParam);
    return recordsListResult;
  }
  
  

}

/**
* 2018. 
* @Title RecordsMapper.java
* @Package com.sinopec.smcc.cpro.records.mapper
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:58:25
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.mapper;

import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;

/**
 * @Title RecordsMapper.java
 * @Package com.sinopec.smcc.cpro.records.mapper
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:58:25
 * @version V1.0
 */
public interface RecordsMapper {

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月29日下午2:08:03
   * @param recordsParam
   */
  public void insertRecords(RecordsParam recordsParam);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月29日下午5:44:35
   * @param recordsParam
   * @return
   */
  public RecordsListResult selectRecordsByFkSystemId(RecordsParam recordsParam);

}

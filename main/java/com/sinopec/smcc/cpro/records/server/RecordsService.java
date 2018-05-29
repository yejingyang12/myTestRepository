/**
* 2018. 
* @Title RecordsService.java
* @Package com.sinopec.smcc.cpro.records.server
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:59:11
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.server;

import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;

/**
 * @Title RecordsService.java
 * @Package com.sinopec.smcc.cpro.records.server
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:59:11
 * @version V1.0
 */
public interface RecordsService {

  /**
   * @Descrption 添加或修改备案信息
   * @author dongxu
   * @date 2018年5月29日下午2:04:13
   * @param recordsParam
   * @return
   */
  String saveRecords(RecordsParam recordsParam);

  /**
   * @Descrption 通过系统ID查询备案信息
   * @author dongxu
   * @date 2018年5月29日下午5:43:10
   * @param recordsParam
   * @return
   */
  RecordsListResult queryRecordsByFkSystemId(RecordsParam recordsParam);

}

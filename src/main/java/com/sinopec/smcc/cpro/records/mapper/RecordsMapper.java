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

import java.util.List;

import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RevokeRecordsResult;

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
  public RecordsResult selectRecordsByFkSystemId(RecordsParam recordsParam);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月30日上午10:13:16
   * @param fkSystemId
   */
  public void updateRecordsBySystemId(RecordsParam recordsParam);

  /**
   * @Descrption 点击撤销备案，填写信息后保存
   * @author yejingyang
   * @date 2018年6月9日上午10:24:37
   * @param recordsParam
   */
  public void updateRecordsBySystemIdForRevokeRecords(RecordsParam recordsParam);

  /**
   * @Descrption 获取撤销备案信息
   * @author yejingyang
   * @date 2018年6月9日上午11:00:12
   * @param recordsParam
   * @return
   */
  public RevokeRecordsResult selectRecordsByFkSystemIdForRevokeRecords(RecordsParam recordsParam);

  /**
   * @Descrption 查询备案详情
   * @author yejingyang
   * @date 2018年6月10日上午10:18:59
   * @param recordsParam
   * @return
   */
  public com.sinopec.smcc.cpro.records.entity.RecordsDetailResult selectRecordsByFkSystemIdForRecordsDetail(
      RecordsParam recordsParam);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年6月11日下午3:56:12
   * @param recordsParam
   * @return
   */
  List<RecordsListResult> selectRecordCompany(RecordsParam recordsParam);
}

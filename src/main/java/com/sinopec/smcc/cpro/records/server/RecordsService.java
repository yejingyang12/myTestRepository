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

import java.util.List;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.records.entity.RecordsDetailResult;
import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RevokeRecordsResult;

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
  String saveRecords(String userName, RecordsParam recordsParam) 
      throws BusinessException;

  /**
   * @Descrption 通过系统ID查询备案信息
   * @author dongxu
   * @date 2018年5月29日下午5:43:10
   * @param recordsParam
   * @return
   */
  RecordsResult queryRecordsByFkSystemId(RecordsParam recordsParam) throws BusinessException;

  /**
   * @Descrption 点击撤销备案，填写信息后保存
   * @author yejingyang
   * @date 2018年6月9日上午10:19:08
   * @param recordsParam
   */
  void saveRevokeRecordsInfo(String userName,RecordsParam recordsParam) 
      throws BusinessException;

  /**
   * @Descrption 获取撤销备案信息
   * @author yejingyang
   * @date 2018年6月9日上午10:56:20
   * @param recordsParam
   */
  RevokeRecordsResult queryRevokeRecords(RecordsParam recordsParam) throws BusinessException;

  /**
   * @Descrption 修改备案状态
   * @author dongxu
   * @date 2018年5月30日上午11:15:08
   * @param recordsParam
   */
  void editRecordsForStatus(RecordsParam recordsParam);

  /**
   * @Descrption 查询备案详情
   * @author yejingyang
   * @date 2018年6月10日上午10:17:04
   * @param recordsParam
   * @return
   */
  RecordsDetailResult queryRecordsDetail(RecordsParam recordsParam) throws BusinessException;

  /**
   * @Descrption 高级搜索获取受理备案单位
   * @author dongxu
   * @date 2018年6月11日下午3:55:05
   * @param recordsParam
   * @return
   */
  List<RecordsListResult> queryRecordCompany(RecordsParam recordsParam) throws BusinessException;

}

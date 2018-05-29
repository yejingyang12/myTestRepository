/**
* 2018. 
* @Title MainMapper.java
* @Package com.sinopec.smcc.cpro.main.mapper
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:44:28
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;

/**
 * @Title MainMapper.java
 * @Package com.sinopec.smcc.cpro.main.mapper
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:44:28
 * @version V1.0
 */
public interface MainMapper {

  /**
   * 根据传入条件查询系统等级保护信息
   * @author eric
   * @date 2018年5月25日下午2:45:19
   * @param mainParam
   * @return
   */
  List<MainListResult> selectAllByMainParam(MainParam mainParam);

  /**
   * @Descrption 通过ID修改系统消息及单位信息删除状态
   * @author dongxu
   * @date 2018年5月27日下午2:28:13
   * @param mainParam
   */
  void updateMainDeleteStatusBySystemId(MainParam mainParam);

}

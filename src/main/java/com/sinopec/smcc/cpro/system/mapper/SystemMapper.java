/**
* 2018. 
* @Title SystemMapper.java
* @Package com.sinopec.smcc.cpro.system.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:43:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.mapper;

import java.util.List;
import java.util.Map;

import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemTemplateListResult;

/**
 * @Title SystemMapper.java
 * @Package com.sinopec.smcc.cpro.system.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:43:13
 * @version V1.0
 */
public interface SystemMapper {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月25日下午2:55:13
   * @param systemParam
   * @return
   */
  List<SystemListResult> selectAllBySystemParam(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月25日下午6:52:45
   * @param systemListResult
   */
  void insertSystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月27日下午11:19:28
   * @param systemCodeParam
   * @return
   */
//  List<SystemCodeListResult> selectSystemCodeListByParam(SystemCodeParam systemCodeParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月28日下午3:52:46
   * @param systemParam
   * @return
   */
  SystemResult selectDetailsSystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月28日下午5:42:01
   * @param systemParam
   * @return
   */
  SystemResult selectEditSystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月28日下午4:47:58
   */
  void updateKeySystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月30日下午6:21:26
   * @param standardizedCode
   * @return
   */
  String selectSystemByStandardizedCode(String standardizedCode);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月30日上午10:28:20
   * @param systemParam
   */
  void updateSystemStatusBySystemId(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月5日上午10:33:34
   * @param systemParam
   * @return
   */
  List<SystemTemplateListResult> selectSystemTemPlate(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月5日下午4:14:48
   * @return
   */
  List<SystemTemplateListResult> selectSystemCode();

  /**
   * @Descrption
   * @author hanxin
   * @param systemTemplateListResult 
   * @date 2018年6月5日下午4:46:26
   */
  void insertSystemTemplate(List<SystemTemplateListResult> systemTemplateListResult);
  
}

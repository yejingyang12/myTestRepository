/**
* 2018. 
* @Title MaterialsMapper.java
* @Package com.sinopec.smcc.cpro.grading.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日下午3:32:21
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;


/**
 * @Title MaterialsMapper.java
 * @Package com.sinopec.smcc.cpro.grading.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日下午3:32:21
 * @version V1.0
 */
public interface AttachMaterialsMapper {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午3:37:48
   * @param materialsMapper
   * @return
   */
  List<AttachMaterialsListResult> selectDetailsAttach(AttachMaterialsParam attachMaterialsParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午3:44:44
   * @param materialsMapper
   * @return
   */
  List<AttachMaterialsListResult> selectEditAttach(AttachMaterialsParam attachMaterialsParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午5:53:08
   * @param attachMaterialsParam
   */
  void insertAttach(AttachMaterialsParam attachMaterialsParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午6:22:08
   * @param attachMaterialsParam
   */
  void updateAttachStatus(AttachMaterialsParam attachMaterialsParam);


}

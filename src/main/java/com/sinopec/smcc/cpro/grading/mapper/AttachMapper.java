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

import com.sinopec.smcc.cpro.grading.entity.AttachListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachParam;


/**
 * @Title MaterialsMapper.java
 * @Package com.sinopec.smcc.cpro.grading.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日下午3:32:21
 * @version V1.0
 */
public interface AttachMapper {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午3:37:48
   * @param materialsMapper
   * @return
   */
  List<AttachListResult> selectDetailsAttach(AttachParam attachParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午3:44:44
   * @param materialsMapper
   * @return
   */
  List<AttachListResult> selectEditAttach(AttachParam attachParam);


}

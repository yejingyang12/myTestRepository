/**
* 2018. 
* @Title MaterialsService.java
* @Package com.sinopec.smcc.cpro.grading.server
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日下午3:25:00
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;


/**
 * @Title MaterialsService.java
 * @Package com.sinopec.smcc.cpro.grading.server
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日下午3:25:00
 * @version V1.0
 */
public interface AttachMaterialsService {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午4:49:07
   * @param attachMaterialsParam
   * @return
   * @throws BusinessException 
   */
  List<AttachMaterialsListResult> queryDetailsAttach(AttachMaterialsParam attachMaterialsParam) 
      throws BusinessException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午4:49:29
   * @param attachMaterialsParam
   * @return
   * @throws BusinessException 
   */
  List<AttachMaterialsListResult> queryEditAttach(AttachMaterialsParam attachMaterialsParam) 
      throws BusinessException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午5:35:24
   * @param attachMaterialsParam
   * @return
   */
  String saveAttach(String userName, AttachMaterialsParam attachMaterialsParam) 
      throws BusinessException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午6:12:08
   * @param gradingParam
   * @return
   */
  String submitAttach(String userName, AttachMaterialsParam attachMaterialsParam) 
      throws BusinessException;


}

/**
* 2018. 
* @Title GradingMapper.java
* @Package com.sinopec.smcc.cpro.grading.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:07:53
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;

/**
 * @Title GradingMapper.java
 * @Package com.sinopec.smcc.cpro.grading.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:07:53
 * @version V1.0
 */
public interface GradingMapper {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日上午10:17:52
   * @param gradingParam
   * @return
   */
  List<GradingListResult> selectDetailsGrading(GradingParam gradingParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日上午10:20:26
   * @param gradingParam
   * @return
   */
  GradingListResult selectEditGrading(GradingParam gradingParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月30日下午4:43:44
   * @param gradingParam
   */
  void insertGrading(GradingParam gradingParam);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年6月5日下午6:36:52
   * @param gradingParam
   * @return
   */
  List<GradingListResult> selectGradingBySystemIds(GradingParam gradingParam);
}

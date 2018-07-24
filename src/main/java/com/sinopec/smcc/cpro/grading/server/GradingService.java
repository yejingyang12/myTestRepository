/**
* 2018. 
* @Title GradingService.java
* @Package com.sinopec.smcc.cpro.grading.server
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:09:51
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;

/**
 * @Title GradingService.java
 * @Package com.sinopec.smcc.cpro.grading.server
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:09:51
 * @version V1.0
 */
public interface GradingService {

  /**
   * 查询定级信息
   * @author hanxin
   * @date 2018年5月29日上午10:15:19
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  GradingListResult queryDetailsGrading(GradingParam gradingParam) throws BusinessException;
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日上午10:19:23
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  GradingListResult queryEditGrading(GradingParam gradingParam) throws BusinessException;
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月30日下午4:26:31
   * @param gradingParam
   * @return
   */
  String saveGrading(String userName,GradingParam gradingParam) throws BusinessException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午1:37:25
   * @param gradingParam
   * @return
   */
  String submitGrading(String userName,GradingParam gradingParam) throws BusinessException;
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午1:37:25
   * @param gradingParam
   * @return
   */
  String submitGradingForHeadquarters(String userName,GradingParam gradingParam) 
      throws BusinessException;

  /**
   * @Descrption 根据系统ID查询定级列表
   * @author dongxu
   * @date 2018年6月5日下午6:38:03
   * @param gradingParam
   * @return
   * @throws BusinessException
   */
  List<GradingListResult> queryGradingByParam(GradingParam gradingParam)
      throws BusinessException;
}

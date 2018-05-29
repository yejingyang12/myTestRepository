/**
 * @Title QueryEvaluationService.java
 * @Package com.sinopec.smcc.cpro.evaluation.server
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午10:00:09
 * @version V1.0
 */
package com.sinopec.smcc.cpro.evaluation.server;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;

/**
 * @Title QueryEvaluationService.java
 * @Package com.sinopec.smcc.cpro.evaluation.server
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午10:00:09
 * @version V1.0
 */
public interface EvaluationService {

  /**
   * 响应测评列表数据
   * @author Aran
   * @date 2018年5月25日下午10:07:13
   * @param evaluationParam
   * @return
   */
  PageInfo<EvaluationListResult> queryEvaluationList(
      EvaluationParam evaluationParam) throws BusinessException;

  /**
   * 获得修改时需要回显的信息
   * @author Aran
   * @date 2018年5月26日下午5:31:26
   * @param evaluationParam
   * @return
   */
  EvaluationResult queryEditEvaluation(EvaluationParam evaluationParam) throws BusinessException;

  /**
   * 保存测评信息
   * @author Aran
   * @date 2018年5月26日下午5:31:39
   * @param evaluationParam
   * @return
   */
  String saveEvaluation(EvaluationParam evaluationParam) throws BusinessException;

  /**
   * 删除测评信息
   * @author Aran
   * @date 2018年5月26日下午5:41:21
   * @param evaluationParam
   * @return
   */
  void deleteEvaluation(EvaluationParam evaluationParam) throws BusinessException;

  /**
   * 获得测评详情信息
   * @author Aran
   * @date 2018年5月28日下午3:42:24
   * @param evaluationParam
   * @return
   */
  EvaluationResult queryDetailsEvaluation(EvaluationParam evaluationParam) throws BusinessException ;

}

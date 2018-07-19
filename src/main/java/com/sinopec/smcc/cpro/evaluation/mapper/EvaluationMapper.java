/**
* @Title EvaluationMapper.java
* @Package com.sinopec.smcc.cpro.evaluation.mapper
* @Description: TODO:
* @author Aran
* @date 2018年5月25日上午11:14:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.evaluation.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;

/**
 * @Title EvaluationMapper.java
 * @Package com.sinopec.smcc.cpro.evaluation.mapper
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日上午11:14:41
 * @version V1.0
 */
public interface EvaluationMapper {

  /**
   * 根据条件查询测评列表
   * @author Aran
   * @date 2018年5月25日下午2:02:33
   * @param companyParam
   * @return
   */
  List<EvaluationListResult> selectAllByEvaluationParam(EvaluationParam companyParam);

	/**
	 * 根据测评ID获得测评信息数据
	 * @author Aran
	 * @date 2018年5月26日下午6:05:13
	 * @param evaluationParam
	 * @return
	 */
	EvaluationResult selectSingleByEvaluationId(EvaluationParam evaluationParam);
	
	 /**
   * 新增或保存测评数据
   * @author Aran
   * @date 2018年5月26日下午9:10:05
   * @param evaluationParam
   */
  void saveEvaluationByEvaluationId(EvaluationParam evaluationParam);

	/**
	 * 根据测评ID删除数据（修改deleteStatus字段）
	 * @author Aran
	 * @date 2018年5月26日下午7:57:01
	 * @param evaluationParam
	 * @return
	 */
	void deleteEvaluationByEvaluationId(EvaluationParam evaluationParam);

  /**
   * 根据测评ID查询测评详情数据
   * @author eric
   * @date 2018年5月29日上午9:22:14
   * @param evaluationParam
   * @return
   */
  EvaluationResult selectSingleDetailsByEvaluationId(EvaluationParam evaluationParam);

  /**
   * @Descrption 首页高级查询测评单位
   * @author dongxu
   * @date 2018年6月11日下午4:20:58
   * @param recordsParam
   * @return
   */
  List<EvaluationListResult> selectExamOrgCompany(RecordsParam recordsParam);
  
  /**
   * @Descrption 通过系统ID查询测评数据
   * @author dongxu
   * @date 2018年7月12日下午2:52:13
   * @param companyParam
   * @return
   */
  List<EvaluationListResult> selectAllByEvaluationSystemId(EvaluationParam companyParam);
}

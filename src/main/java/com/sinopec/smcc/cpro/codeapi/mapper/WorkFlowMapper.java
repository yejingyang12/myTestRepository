/**
* @Title EvaluationMapper.java
* @Package com.sinopec.smcc.cpro.evaluation.mapper
* @Description: TODO:
* @author Aran
* @date 2018年5月25日上午11:14:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.mapper;

import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowParam;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowResult;

/**
 * @Title EvaluationMapper.java
 * @Package com.sinopec.smcc.cpro.evaluation.mapper
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日上午11:14:41
 * @version V1.0
 */
public interface WorkFlowMapper {

  /**
   * @Descrption 新增工作流信息
   * @author dongxu
   * @date 2018年9月3日下午7:40:38
   * @param workFlowParam
   */
  void insertWorkFlow(WorkFlowParam workFlowParam);
  
  /**
   * @Descrption 通过业务ID查询工作流列表
   * @author dongxu
   * @date 2018年9月3日下午7:41:29
   * @param workFlowParam
   * @return
   */
  WorkFlowResult selectWorkFlowByBusinessId (WorkFlowParam workFlowParam);
  
  /**
   * @Descrption 通过业务ID修改工作流信息
   * @author dongxu
   * @date 2018年9月4日上午10:35:48
   * @param workFlowParam
   */
  void updateWorkFlowByBusinessId(WorkFlowParam workFlowParam);
}

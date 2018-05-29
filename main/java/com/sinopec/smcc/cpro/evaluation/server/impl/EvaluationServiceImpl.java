/**
* @Title EvaluationServiceImpl.java
* @Package com.sinopec.smcc.cpro.evaluation.server.impl
* @Description: TODO:
* @author Aran
* @date 2018年5月25日下午10:00:34
* @version V1.0
*/
package com.sinopec.smcc.cpro.evaluation.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.company.utils.ConvertFiledUtil;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.evaluation.mapper.EvaluationMapper;
import com.sinopec.smcc.cpro.evaluation.server.EvaluationService;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title EvaluationServiceImpl.java
 * @Package com.sinopec.smcc.cpro.evaluation.server.impl
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午10:00:34
 * @version V1.0
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
  private EvaluationMapper evaluationMapper;
	
	@Override
	@EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_evaluation")
	public PageInfo<EvaluationListResult> queryEvaluationList(
			EvaluationParam evaluationParam) throws BusinessException{
    StringBuffer orderBy = new StringBuffer();
    if(StringUtils.isNotBlank(evaluationParam.getField())){
      orderBy.append(ConvertFiledUtil.sortField(evaluationParam.getField()));
      if(StringUtils.isNotBlank(evaluationParam.getSort())){
        orderBy.append("").append(evaluationParam.getSort());
      }
    }else{
      //默认排序
      orderBy.append("cproEvaluation.createTime DESC");
    }
    //初始化分页拦截器
    PageHelper.startPage(evaluationParam.getCurrentPage(), evaluationParam.getPageSize(), 
        orderBy.toString());
    //对实体内的对象进行处理
    evaluationParam.setDeleteStatus(1);
    //获得响应列表数据
    List<EvaluationListResult> evaluationListResultList = evaluationMapper.
    		selectAllByEvaluationParam(evaluationParam);
    //装载列表数据
    PageInfo<EvaluationListResult> pageInfo = new PageInfo<>(evaluationListResultList);
    return pageInfo;
  }

	@Override
	@EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_evaluation")
	public EvaluationResult queryEditEvaluation(EvaluationParam evaluationParam) 
	    throws BusinessException{
    if (StringUtils.isBlank(evaluationParam.getEvaluationId())) 
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    return evaluationMapper.selectSingleByEvaluationId(evaluationParam);
  }

	@Override
	@EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_evaluation")
  @Transactional
	public String saveEvaluation(EvaluationParam evaluationParam) throws BusinessException{
		if(StringUtils.isBlank(evaluationParam.getEvaluationId())) {
			evaluationParam.setEvaluationId(Utils.getUuidFor32());
			evaluationParam.setCreateTime(new Date());
		}else{
		  evaluationParam.setCreateTime(new Date());
		}
		evaluationMapper.saveEvaluationByEvaluationId(evaluationParam);
		return evaluationParam.getEvaluationId();
	}

	@Override
	@EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_evaluation")
  @Transactional
	public void deleteEvaluation(EvaluationParam evaluationParam) throws BusinessException{
		if(StringUtils.isBlank(evaluationParam.getEvaluationId()))
			throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
		evaluationMapper.deleteEvaluationByEvaluationId(evaluationParam);
  }

  @Override
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_evaluation")
  public EvaluationResult queryDetailsEvaluation(EvaluationParam evaluationParam) throws BusinessException {
    if (StringUtils.isBlank(evaluationParam.getEvaluationId())) 
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    return evaluationMapper.selectSingleDetailsByEvaluationId(evaluationParam);
  }
}

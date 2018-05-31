/**
* 2018. 
* @Title GradingServiceImpl.java
* @Package com.sinopec.smcc.cpro.grading.server.impl
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:11:00
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.mapper.GradingMapper;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title GradingServiceImpl.java
 * @Package com.sinopec.smcc.cpro.grading.server.impl
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:11:00
 * @version V1.0
 */
@Service
public class GradingServiceImpl implements GradingService{ 

  @Autowired
  private GradingMapper gradingMapper;
  
  /**
   * 查询定级信息
   * @throws BusinessException 
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public List<GradingListResult> queryDetailsGrading(GradingParam gradingParam) throws BusinessException {
    if(StringUtils.isBlank(gradingParam.getGradingId())) {
      return this.gradingMapper.selectDetailsGrading(gradingParam);
    }
    throw new BusinessException(EnumResult.ERROR);
  }

  /**
   * 查询定级信息详情
   * @throws BusinessException 
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public GradingListResult queryEditGrading(GradingParam gradingParam) throws BusinessException {
    if (StringUtils.isBlank(gradingParam.getGradingId())) {
      return this.gradingMapper.selectEditGrading(gradingParam);
    }
    throw new BusinessException(EnumResult.ERROR);
  }

  /**
   * 添加定级信息数据
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public String saveGrading(GradingParam gradingParam) {
    if(StringUtils.isBlank(gradingParam.getGradingId())) {
      gradingParam.setGradingId(Utils.getUuidFor32());
      gradingParam.setCreateTime(new Date());
      gradingParam.setFkSystemId(gradingParam.getFkSystemId());
      gradingParam.setRankTime(new Date());
      gradingParam.setCompetentIsExisting(2);
      gradingParam.setFillDate(new Date());
    }
    this.gradingMapper.insertGrading(gradingParam);
    return gradingParam.getGradingId();
  }
}

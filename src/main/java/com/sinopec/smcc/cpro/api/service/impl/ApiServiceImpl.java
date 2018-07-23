/**
* 2018. 
* @Title ApiServiceImpl.java
* @Package com.sinopec.smcc.cpro.api.service.impl
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:13:14
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;
import com.sinopec.smcc.cpro.api.service.ApiService;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.server.SystemService;

/**
 * @Title ApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.api.service.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:13:14
 * @version V1.0
 */
@Service
public class ApiServiceImpl implements ApiService{
  
  @Autowired
  private GradingService gradingServiceImpl;
  @Autowired
  private SystemService systemServiceImpl;
  
  /**
   * 获取定级信息
   */
  @Override
  public GradingApiResult getGradingInformation(String systemId) throws BusinessException {
    GradingApiResult gradingApiResult = new GradingApiResult();
    GradingParam gradingParam = new GradingParam();
    SystemParam systemParam = new SystemParam();
    gradingParam.setFkSystemId(systemId);
    systemParam.setSystemId(systemId);
    GradingListResult gradingListResult = gradingServiceImpl.queryDetailsGrading(gradingParam);
    if(gradingListResult != null){
      gradingApiResult.setBizSprankLevel(gradingListResult.getFkBizSPRankLevel());
      gradingApiResult.setBizSystemLevel(gradingListResult.getFkBizSystemLevel());
      gradingApiResult.setSpranklevel(gradingListResult.getFkSpRanklevel());
    }
    SystemResult systemResult = systemServiceImpl.querySystemInformationBySystemId(systemParam);
    if(systemResult != null){
      gradingApiResult.setGradingStatus(systemResult.getGradingStatus());
    }
    return gradingApiResult;
  }

}

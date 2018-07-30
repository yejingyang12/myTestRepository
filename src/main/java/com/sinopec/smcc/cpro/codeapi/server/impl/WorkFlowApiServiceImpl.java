/**
* 2018. 
* @Title WorkFlowApiServiceImpl.java
* @Package com.sinopec.smcc.cpro.codeapi.server.impl
* @Description: TODO:
* @author dongxu
* @date 2018年7月11日下午3:21:34
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.server.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppWorkflowData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.pcitc.ssc.dps.inte.workflow.StartContext;
import com.sinopec.smcc.base.consts.SmccConsts;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.depends.dps.util.DpsConfig;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;
import com.sinopec.smcc.depends.soap.SoapApiService;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

/**
 * @Title WorkFlowApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月11日下午3:21:34
 * @version V1.0
 */
@Service
public class WorkFlowApiServiceImpl implements WorkFlowApiService{
  @Autowired
  private DpsTemplate dpsTemplate;
  @Value("${appId}")
  private String appId;
  @Autowired
  private UserApiService userApiServiceImpl;
  @Autowired
  SoapApiService soapApiService;
  @Autowired
  private DpsConfig dpsConfig;
  
  /**
   * 流程发起
   */
  @Override
  public AppCallResult initStart() throws BusinessException {
    AppCallResult appCallResult = new AppCallResult();
    try {
      // 获取流程模板信息
      final List<AppWorkflowData> appWorkflowPublic = dpsTemplate.appWorkflowPublic(WorkFlowConsts.CATEGORY_CODE_CPRO);
      final AppWorkflowData workFlowData = appWorkflowPublic.get(0);
      //获得用户信息
      UserDTO userDTO = userApiServiceImpl.getUserInfo();
//      StartContext startContext = new StartContext();
//      startContext.setUserId(String.valueOf(userDTO.getUserId()));
//      startContext.setAppId("SMCC");
//      startContext.setUserName(userDTO.getUserName());
//      startContext.setOrganiseId("#templateorgId#");
//      startContext.setOrganiseName(userDTO.getOrgName());
//      startContext.setBusinessId(Utils.getUuidFor32());
//      startContext.setBusinessName("测试撤销备案申请流程");
//      startContext.setExecuteDate(new Date());
//      startContext.setCategoryCode(workFlowData.getCategoryCode());
//      startContext.setWorkflowId(workFlowData.getWorkflowId());
//      appCallResult = dpsTemplate.initStart(startContext);
      
      //
      
      
      //提交通过流程
//    final ExecuteContext executeContext = new ExecuteContext();
//    executeContext.setAppId(dpsConfig.getAppId());
//    executeContext.setExecutorId(String.valueOf(userDTO.getUserId()));
//    executeContext.setExecutorName(userDTO.getUserName());
//    executeContext.setTaskId(userDTO.getprocessDTO.getTaskId());
//    executeContext.setExecuteDate(new Date());
//    executeContext.setMetasList(processDTO.getDataList());
//    executeContext.setVariableList(processDTO.getVariableList());
//
//    return dpsTemplate.approveComplete(executeContext);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return appCallResult;
  }

}

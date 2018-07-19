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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.StartContext;
import com.sinopec.smcc.base.consts.SmccConsts;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.cpro.tools.Utils;
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
  
  /**
   * 流程发起
   */
  @Override
  public AppCallResult initStart() throws BusinessException {
    AppCallResult appCallResult = new AppCallResult();
    try {
      String data=soapApiService.soapGetApi("/app/workflow/SMCC/"+SmccConsts.CATEGORY_CODE_USMG+"/1");
      JSONArray jSONArray=JSONObject.parseArray(data);
      JSONObject jSONObject=(JSONObject) jSONArray.get(0);
      
      //获得用户信息
      UserDTO userDTO = userApiServiceImpl.getUserInfo();
      StartContext startContext = new StartContext();
      startContext.setUserId(String.valueOf(userDTO.getUserId()));
      startContext.setAppId("SMCC");
      startContext.setUserName(userDTO.getUserName());
      startContext.setOrganiseId("#templateorgId#");
      startContext.setOrganiseName(userDTO.getOrgName());
      startContext.setBusinessId(Utils.getUuidFor32());
      startContext.setBusinessName("撤销备案申请流程");
      startContext.setCategoryCode("usmg");
      startContext.setExecuteDate(new Date());
      startContext.setWorkflowId(jSONObject.get("workflowId").toString());
      appCallResult = dpsTemplate.initStart(startContext);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return appCallResult;
   
  }

}

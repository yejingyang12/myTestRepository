/**
 * @Title SystemApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:29:17
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.api.SystemApiClient;
import com.sinopec.smcc.cpro.codeapi.entity.SystemApiParam;
import com.sinopec.smcc.cpro.codeapi.entity.SystemApiResult;
import com.sinopec.smcc.cpro.codeapi.entity.SystemInfoList;
import com.sinopec.smcc.cpro.codeapi.entity.SystemInfoList.SystemInfo;
import com.sinopec.smcc.cpro.codeapi.server.SystemApiService;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;

/**
 * @Title SystemApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:29:17
 * @version V1.0
 */
@Service
public class SystemApiServiceImpl implements SystemApiService{
  
  @Autowired
  private SystemApiClient systemApiClient;
  @Autowired
  private SystemMapper systemMapper;

  @Override
  public List<SystemApiResult> querySystemApi(SystemApiParam systemApiParam)
      throws BusinessException {
    List<SystemApiResult> systemApiResultList = new ArrayList<SystemApiResult>();
    SystemInfoList systemInfoList = null;
    try {
      systemInfoList = JSON.parseObject(systemApiClient.querySystemList(), SystemInfoList.class);
    } catch (Exception e) {
      e.printStackTrace();
      return systemApiResultList;
    }
    List<SystemListResult> systemList = systemMapper.selectAllBySystemParam(new SystemParam());
    
    if (systemList!=null&&systemList.size()>0) {
      if (systemInfoList!=null&&systemInfoList.getData()!=null&&systemInfoList.getData().size()>0) {
        int systemListSize = systemList.size();
        int systemInfoListSize = systemInfoList.getData().size();
        outermost:
        for (int i = 0; i < systemInfoListSize; i++) {
          SystemInfo systemInfo = systemInfoList.getData().get(i);
          for (int j = 0; j < systemListSize; j++) {
            SystemListResult systemListResult = systemList.get(j);
            if (systemListResult.getSystemName().equals(systemInfo.getSystemallname())) {
              continue outermost;
            }
          }
          SystemApiResult systemApiResult = new SystemApiResult();
          systemApiResult.setSystemCode(systemInfo.getSystemcode());
          systemApiResult.setSystemName(systemInfo.getSystemallname());
          systemApiResultList.add(systemApiResult);
        }
      }
    } else {
      if (systemInfoList!=null&&systemInfoList.getData()!=null&&systemInfoList.getData().size()>0) {
        int systemInfoListSize = systemInfoList.getData().size();
        for (int i = 0; i < systemInfoListSize; i++) {
          SystemInfo systemInfo = systemInfoList.getData().get(i);
          SystemApiResult systemApiResult = new SystemApiResult();
          systemApiResult.setSystemCode(systemInfo.getSystemcode());
          systemApiResult.setSystemName(systemInfo.getSystemallname());
          systemApiResultList.add(systemApiResult);
        }
      }
    }
    
    return systemApiResultList;
  }

}

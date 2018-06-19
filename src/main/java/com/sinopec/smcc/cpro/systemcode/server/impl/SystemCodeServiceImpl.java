/**
* 2018. 
* @Title SystemCodeServiceImpl.java
* @Package com.sinopec.smcc.cpro.systemcode.server.impl
* @Description: TODO:
* @author zhouyu
* @date 2018年6月3日下午11:34:52
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.entity.SystemGradingInfoOneResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemGradingInfoThreeResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemGradingInfoTwoResult;
import com.sinopec.smcc.cpro.systemcode.mapper.SystemCodeMapper;
import com.sinopec.smcc.cpro.systemcode.server.SystemCodeService;

/**
 * @Title SystemCodeServiceImpl.java
 * @Package com.sinopec.smcc.cpro.systemcode.server.impl
 * @Description: TODO:系统常量下拉实现类
 * @author zhouyu
 * @date 2018年6月3日下午11:34:52
 * @version V1.0
 */
@Service
public class SystemCodeServiceImpl implements SystemCodeService {
  
  
  @Autowired
  public SystemCodeMapper systemCodeMapper;

  @Override
  @Transactional
  public List<SystemCodeListResult> querySystemCodeForKeySystemCode(
      SystemCodeParam systemCodeParam) throws BusinessException{
    List<SystemCodeListResult> systemCodeResultList = 
        this.systemCodeMapper.selectSystemCodeForByParamKeySystemCode(systemCodeParam);
    return systemCodeResultList;
  }
  
  @Override
  @Transactional
  public List<SystemCodeListResult> querySystemCodeForKeyCodeName(
      SystemCodeParam systemCodeParam) throws BusinessException{
    List<SystemCodeListResult> systemCodeResultList = 
        this.systemCodeMapper.selectSystemCodeForByParamKeyCodeName(systemCodeParam);
    return systemCodeResultList;
  }

  @Override
  @Transactional
  public List<SystemGradingInfoOneResult> queryGradingInfoList(SystemCodeParam systemCodeParam) 
      throws BusinessException {
    List<SystemCodeListResult> systemCodeResultList = this.systemCodeMapper.
        selectSystemCodeAndCodeNameAndSystemFatherCodeByParamKeyCodeName(systemCodeParam);
    List<SystemGradingInfoOneResult> systemGradingInfoResultList = 
        new ArrayList<SystemGradingInfoOneResult>();
    for (int i = 0; i < systemCodeResultList.size(); i++) {
      if ("0".equals(systemCodeResultList.get(i).getSystemFatherCode())) {
        //一级
        SystemGradingInfoOneResult systemGradingInfoOneResult = new SystemGradingInfoOneResult();
        systemGradingInfoOneResult.setCodeName(systemCodeResultList.get(i).getCodeName());
        systemGradingInfoOneResult.setSystemCode(systemCodeResultList.get(i).getSystemCode());
        
        List<SystemGradingInfoTwoResult> systemGradingInfoTwoResultList = 
            new ArrayList<SystemGradingInfoTwoResult>();
        for (int j = 0; j < systemCodeResultList.size(); j++) {
          if (systemCodeResultList.get(i).getSystemCode().equals(
                systemCodeResultList.get(j).getSystemFatherCode())) {
            //组装二级
            SystemGradingInfoTwoResult systemGradingInfoTwoResult = 
                new SystemGradingInfoTwoResult();
            systemGradingInfoTwoResult.setCodeName(systemCodeResultList.get(j).getCodeName());
            systemGradingInfoTwoResult.setSystemCode(systemCodeResultList.get(j).getSystemCode());
            
            List<SystemGradingInfoThreeResult> systemGradingInfoThreeResultList = 
                new ArrayList<SystemGradingInfoThreeResult>();
            for (int k = 0; k < systemCodeResultList.size(); k++) {
              if (systemCodeResultList.get(j).getSystemCode().equals(
                    systemCodeResultList.get(k).getSystemFatherCode())){
                //组装三级
                SystemGradingInfoThreeResult systemGradingInfoThreeResult = 
                    new SystemGradingInfoThreeResult();
                systemGradingInfoThreeResult.setCodeName(
                    systemCodeResultList.get(k).getCodeName());
                systemGradingInfoThreeResult.setSystemCode(
                    systemCodeResultList.get(k).getSystemCode());
                //添加三级列表
                systemGradingInfoThreeResultList.add(systemGradingInfoThreeResult);
              }
            }
            //将三级列表组装到二级数据中
            systemGradingInfoTwoResult.setSystemTwoInfo(systemGradingInfoThreeResultList);
            //添加二级列表
            systemGradingInfoTwoResultList.add(systemGradingInfoTwoResult);
          }
        }
        //将二级列表组装到一级数据中
        systemGradingInfoOneResult.setSystemOneInfo(systemGradingInfoTwoResultList);
        //添加一级列表
        systemGradingInfoResultList.add(systemGradingInfoOneResult);
      }
    }
    return systemGradingInfoResultList;
  }
}

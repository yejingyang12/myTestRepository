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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCode;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
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

  /**
   * 下拉列表 带其它录入项key和value返回中文
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system_code")
  public String getConstantByName(SystemCodeParam systemCodeParam) throws BusinessException {
    List<SystemCode> str = this.systemCodeMapper.getConstantByName(systemCodeParam);
    StringBuffer json = new StringBuffer();
    if (!str.isEmpty()) {
      json.append("{'list':[");
      for(SystemCode vo : str){
            json.append(" {'value':'"+vo.getCodeName()+"', 'key':'"+vo.getCodeName()+"'},");
      }   
      if(json.lastIndexOf(",") > -1){
        json.deleteCharAt(json.lastIndexOf(","));
      }
      json.append("]}");
    }
    System.out.println("单选下拉框数据："+json.toString().replace("'", "\""));
    return json.toString().replace("'", "\"");
  }
  /**
   * 
   */
  @Override
  public String getConstantName(Integer fkCodeType,Integer deleteStatus,String systemCode) throws BusinessException {
    SystemCodeParam systemCodeParam = new SystemCodeParam();
    systemCodeParam.setFkCodeType(fkCodeType);
    systemCodeParam.setDeleteStatus(deleteStatus);
    systemCodeParam.setSystemCode(systemCode);
    String str = this.systemCodeMapper.selectConstantName(systemCodeParam);
    return str;
  }
  /**
   * 下拉key是code；value是中文注释
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system_code")
  public String getSingleSelect(SystemCodeParam systemCodeParam) throws BusinessException {
    List<SystemCode> str = this.systemCodeMapper.getConstantByName(systemCodeParam);
    StringBuffer json = new StringBuffer();
    if (!str.isEmpty()) {
      json.append("{'list':[");
      for(SystemCode vo : str){
            json.append(" {'value':'"+vo.getCodeName()+"', 'key':'"+vo.getSystemCode()+"'},");
      }   
      if(json.lastIndexOf(",") > -1){
        json.deleteCharAt(json.lastIndexOf(","));
      }
      json.append("]}");
    }
    System.out.println("单选下拉框数据："+json.toString().replace("'", "\""));
    return json.toString().replace("'", "\"");
  }
  /**
   * 多选树下拉结构
   * 目前有以下类型可用（"11"，"",""）
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system_code")
  public String getConstantTreeByName(SystemCodeParam systemCodeParam) throws BusinessException {
    List<SystemCode> syCodes = this.systemCodeMapper.getConstantTreeByName(systemCodeParam);
    StringBuffer json = new StringBuffer();
    json.append("{'treeNodes':[");
    for(SystemCode vo : syCodes){
      json.append(" {'id':'"+vo.getSystemCode()+"', 'parentId':'"+vo.getSystemFatherCode()
          +"','name':'"+vo.getCodeName()+"'},");
    }   
    if(json.lastIndexOf(",") > -1){
      json.deleteCharAt(json.lastIndexOf(","));
    }
    json.append("]}");
    return json.toString().replace("'", "\"");
  }

}

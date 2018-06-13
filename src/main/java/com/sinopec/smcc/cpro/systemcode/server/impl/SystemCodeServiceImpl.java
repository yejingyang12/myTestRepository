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
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
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
}

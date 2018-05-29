/**
 * @Title MainServiceImpl.java
 * @Package com.sinopec.smcc.cpro.main.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:46:31
 * @version V1.0
 */
package com.sinopec.smcc.cpro.main.server.impl;

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
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.mapper.MainMapper;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.main.utils.ConvertFieldUtil;

/**
 * @Title MainServiceImpl.java
 * @Package com.sinopec.smcc.cpro.main.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:46:31
 * @version V1.0
 */
@Service
public class MainServiceImpl implements MainService{

  @Autowired
  private MainMapper mainMapper;
  
  /**
   * 响应等保列表数据
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  @Transactional
  public PageInfo<MainListResult> queryMainList(MainParam mainParam) throws BusinessException{
    //创建排序字段
    StringBuffer orderBy = new StringBuffer();
    //判断field是否有值
    if(StringUtils.isNotBlank(mainParam.getField())) {
      //如有值，则将排序字段放入orderBy对象
      orderBy.append(ConvertFieldUtil.sortField(mainParam.getField()));
      if(StringUtils.isNotBlank(mainParam.getSort())) {
        orderBy.append(" ").append(mainParam.getSort());
      }
    }else {
      //默认排序规则
      orderBy.append("system.createTime DESC");
    }
    //初始化分页拦截器
    PageHelper.startPage(mainParam.getCurrentPage(), mainParam.getPageSize(), 
        orderBy.toString());
    //获得相应列表数据
    List<MainListResult> list = mainMapper.selectAllByMainParam(mainParam);

    //装载列表数据
    PageInfo<MainListResult> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }
  
  /**
   * 通过systemId删除系统信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  @Transactional
  public void deleteMainBySystemId(MainParam mainParam) throws BusinessException{
    if(StringUtils.isBlank(mainParam.getSystemId())){
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    mainMapper.updateMainDeleteStatusBySystemId(mainParam);
  }

}

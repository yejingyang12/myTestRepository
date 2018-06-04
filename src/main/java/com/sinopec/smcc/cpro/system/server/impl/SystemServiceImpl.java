/**
* 2018. 
* @Title SystemServiceImpl.java
* @Package com.sinopec.smcc.cpro.system.server.impl
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:49:44
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.server.impl;

import java.util.ArrayList;
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
import com.sinopec.smcc.cpro.system.entity.SystemKeyProducts;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseServices;
import com.sinopec.smcc.cpro.system.mapper.SystemKeyProductsMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemUseServicesMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.system.util.ConvertFieldUtil;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title SystemServiceImpl.java
 * @Package com.sinopec.smcc.cpro.system.server.impl
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:49:44
 * @version V1.0
 */
@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SystemMapper systemMapper;
	@Autowired
	private SystemKeyProductsMapper systemKeyProductsMapper;
	@Autowired
  private SystemUseServicesMapper systemUseServicesMapper;

	/**
   * 响应系统列表数据
   */
  @Override
	@EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public PageInfo<SystemListResult> querySystemList(SystemParam systemParam) {
	//创建排序字段
    StringBuffer orderBy = new StringBuffer();
		//判断field是否有值
		if(StringUtils.isNotBlank(systemParam.getField())){
			//如有值，则将排序字段放入orderBy对象
			orderBy.append(ConvertFieldUtil.sortField(systemParam.getField()));
			if(StringUtils.isNotBlank(systemParam.getSort())){
				orderBy.append(" ").append(systemParam.getSort());
			}
		}else {
			//默认排序规则
			orderBy.append("createTime DESC");
		}
		//初始化分页拦截器
		PageHelper.startPage(systemParam.getCurrentPage(), systemParam.getPageSize(),
		    orderBy.toString());
		//获得相应列表数据
		List<SystemListResult> systemListResultlist = 
		    this.systemMapper.selectAllBySystemParam(systemParam);
		//装载列表数据
		PageInfo<SystemListResult> pageInfo = new PageInfo<>(systemListResultlist);
		return pageInfo;
	}


  /**
   * 添加或修改系统信息
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public String saveSystem(SystemParam systemParam) throws BusinessException {
    String standardizedCode = systemMapper.
        selectSystemByStandardizedCode(systemParam.getStandardizedCode());
    //判断标准化代码是否被创建，如果已创建则抛出异常不进行添加或修改
    if(StringUtils.isNotBlank(standardizedCode))
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    if(StringUtils.isBlank(systemParam.getSystemId())) {
      systemParam.setSystemId(Utils.getUuidFor32());
      systemParam.setExamineStatus(1);
      systemParam.setExaminationStatus(1);
      systemParam.setSubIsSystem(2);
      systemParam.setFkSystemType(1);
      systemParam.setFkSystemIsMerge(2);
      systemParam.setRecordStatus(1);
      systemParam.setGradingStatus(1);
      systemParam.setFkChangeMatter(5);
      systemParam.setAppIsInternet(2);
      systemParam.setFkInfoSysTypeCon(1);
      systemParam.setCreateTime(new Date());
      systemParam.setWhenInvestmentUse(new Date());
      systemParam.setEvaluationStatus(1);
      List<SystemKeyProducts> systemKeyProductsList = new ArrayList<SystemKeyProducts>();
      SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
      systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
      systemKeyProducts.setFkSystemId(systemParam.getSystemId());
      systemKeyProducts.setDeleteStatus(1);
      systemKeyProducts.setFkExaminStatus(1);
      systemKeyProducts.setCreateTime(new Date());
      systemKeyProductsList.add(systemKeyProducts);
      systemParam.setSystemKeyProducts(systemKeyProductsList);
      List<SystemUseServices> systemUseServicesList = new ArrayList<SystemUseServices>();
      SystemUseServices systemUseServices = new SystemUseServices();
      systemUseServices.setSystemUseServicesId(Utils.getUuidFor32());
      systemUseServices.setFkSystemId(systemParam.getSystemId());
      systemUseServices.setCreateTime(new Date());
      systemUseServices.setFkProductsType(1);
      systemUseServices.setServiceIsUse(2);
      systemUseServicesList.add(systemUseServices);
      systemParam.setSystemUseServices(systemUseServicesList);
    }else {
      systemParam.setCreateTime(new Date());
    }
    this.systemKeyProductsMapper.insertSystemKeyProductsBySystemKeyProductsId(
        systemParam.getSystemKeyProducts());
    this.systemUseServicesMapper.insertSystemUseServicesBySystemUseServicesId(
        systemParam.getSystemUseServices());
    this.systemMapper.insertSystem(systemParam);
    return systemParam.getSystemId();
  }

	/**
	 * 查询系统代码信息
	 */
/*	@Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system_code")
  public List<SystemCodeListResult> querySystemCodeList(SystemCodeParam systemCodeParam) 
      throws BusinessException {
	  List<SystemCodeListResult> systemCodeListResult = 
	      this.systemMapper.selectSystemCodeListByParam(systemCodeParam);
    return systemCodeListResult;
  }*/

	/**
	 * 查询系统信息详情
	 * @throws BusinessException 
	 */
	@Override
	@EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
	public SystemResult queryDetailsSystem(SystemParam systemParam) throws BusinessException {
	  if(StringUtils.isBlank(systemParam.getSystemId())) {
	    return this.systemMapper.selectDetailsSystem(systemParam);
	  }
    throw new BusinessException(EnumResult.ERROR);
	}
	
	/**
	 * 修改系统信息查询详情
	 * @throws BusinessException 
	 */
	@Override
	@EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException {
	  if(StringUtils.isBlank(systemParam.getSystemId())) {
	    return this.systemMapper.selectEditSystem(systemParam);
	  }
	  throw new BusinessException(EnumResult.ERROR);
	}
	
	/**
   * 修改系统状态
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  @Transactional
  public void editSystemStatusBySystemId(SystemParam systemParam) throws BusinessException {
    this.systemMapper.updateSystemStatusBySystemId(systemParam);
  }
}

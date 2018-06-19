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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
<<<<<<< HEAD
<<<<<<< HEAD
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.entity.CheckResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationResult;
import com.sinopec.smcc.cpro.system.constant.SystemConstant;
import com.sinopec.smcc.cpro.system.entity.SystemGradingChangeResult;
=======
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
=======
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
import com.sinopec.smcc.cpro.system.entity.SystemKeyProducts;
import com.sinopec.smcc.cpro.system.entity.SystemKeyResult;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemSubResult;
import com.sinopec.smcc.cpro.system.entity.SystemTemplateListResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseServices;
import com.sinopec.smcc.cpro.system.mapper.SystemKeyProductsMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemUseServicesMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.system.util.ConvertFieldUtil;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.JacobExcelTool;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.cpro.tools.excel.ExcelUtils;
import com.sinopec.smcc.cpro.tools.excel.bean.CellBean;
import com.sinopec.smcc.cpro.tools.excel.bean.ExcelBean;
import com.sinopec.smcc.cpro.tools.excel.bean.SheetBean;

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
	@Autowired
	private NodeService nodeServiceImpl;
	/**
   * 响应系统列表数据
   */
  @Override
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
			orderBy.append("system.createTime DESC");
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
   * 添加系统信息
   */
  @Override
  @Transactional
<<<<<<< HEAD
<<<<<<< HEAD
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public String saveSystem(String userName, SystemParam systemParam) 
      throws BusinessException {
    String fkCompanyCode = "100";
    
    List<SystemKeyProducts> keyList = systemParam.getSystemKeyProducts();
    List<SystemParam> systemCode = systemParam.getAddSystemSub();
    List<SystemKeyProducts> subKeyList = systemParam.getSystemKeyProducts();
    List<SystemUseServices> useList = systemParam.getSystemUseServices();
    List<SystemUseServices> subUseList = systemParam.getSystemUseServices();
    
    List<SystemParam> subSystemList = new ArrayList<SystemParam>();
    List<SystemKeyProducts> systemKeyProductsList = new ArrayList<SystemKeyProducts>();
    List<SystemUseServices> systemUseServicesList = new ArrayList<SystemUseServices>();
    
=======
=======
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
  public String saveSystem(SystemParam systemParam) throws BusinessException {
    String standardizedCode = systemMapper.
        selectSystemByStandardizedCode(systemParam.getStandardizedCode());
    //判断标准化代码是否被创建，如果已创建则抛出异常不进行添加或修改
    if(StringUtils.isNotBlank(standardizedCode))
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    if(StringUtils.isBlank(systemParam.getSystemId())) {
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
      systemParam.setSystemId(Utils.getUuidFor32());
      systemParam.setExamineStatus(1);
      systemParam.setExaminationStatus(1);
      systemParam.setSubIsSystem(2);
      systemParam.setFkSystemType(1);
      systemParam.setRecordStatus(1);
      systemParam.setGradingStatus(1);
      systemParam.setFkChangeMatter(5);
      systemParam.setAppIsInternet(2);
      systemParam.setCreateTime(new Date());
      systemParam.setEvaluationStatus(1);
      systemParam.setFkCompanyCode(fkCompanyCode);
      subSystemList.add(systemParam);
      
      for (int key = 0; key < keyList.size(); key++) {
        SystemKeyProducts systemKeyProductsBean = new SystemKeyProducts();
        systemKeyProductsBean.setSystemKeyProductsId(Utils.getUuidFor32());
        systemKeyProductsBean.setFkSystemId(systemParam.getSystemId());
        systemKeyProductsBean.setDeleteStatus(1);
        systemKeyProductsBean.setCreateTime(new Date());
        systemKeyProductsBean.setFkExaminStatus(keyList.get(key).getFkExaminStatus());
        systemKeyProductsBean.setProductsNumber(keyList.get(key).getProductsNumber());
        systemKeyProductsBean.setFkNationalIsProducts(keyList.get(key).getFkNationalIsProducts());
        systemKeyProductsBean.setnUseProbability(keyList.get(key).getnUseProbability());
        systemKeyProductsList.add(systemKeyProductsBean);
      }
    
      for (int use = 0; use < useList.size(); use++) {
        SystemUseServices SystemUseServicesBean = new SystemUseServices();
        SystemUseServicesBean.setSystemUseServicesId(Utils.getUuidFor32());
        SystemUseServicesBean.setFkSystemId(systemParam.getSystemId());
        SystemUseServicesBean.setCreateTime(new Date());
        SystemUseServicesBean.setServiceIsUse(useList.get(use).getServiceIsUse());
        SystemUseServicesBean.setFkProductsType(useList.get(use).getFkProductsType());
        SystemUseServicesBean.setServiceIsUse(useList.get(use).getServiceIsUse());
        SystemUseServicesBean.setFkResponsibleType(useList.get(use).getFkResponsibleType());
        systemUseServicesList.add(SystemUseServicesBean);
      }
      
   if (systemParam.getFkSystemIsMerge() == 1) {
    if(systemCode != null){
        for (int subSystem = 0; subSystem < systemCode.size(); subSystem++) {
          SystemParam systemParamSub = new SystemParam();
          systemParamSub.setSystemId(Utils.getUuidFor32());
          systemParamSub.setExamineStatus(systemParam.getExaminationStatus());
          systemParamSub.setExaminationStatus(systemParam.getExaminationStatus());
          systemParamSub.setFkSystemType(systemParam.getFkSystemType());
          systemParamSub.setRecordStatus(systemParam.getRecordStatus());
          systemParamSub.setGradingStatus(systemParam.getGradingStatus());
          systemParamSub.setFkChangeMatter(systemParam.getFkChangeMatter());
          systemParamSub.setAppIsInternet(systemParam.getAppIsInternet());
          systemParamSub.setCreateTime(systemParam.getCreateTime());
          systemParamSub.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
          systemParamSub.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
          systemParamSub.setFkSystemIsMerge(systemParam.getFkSystemIsMerge());
          systemParamSub.setAppIsInternet(systemParam.getAppIsInternet());
          systemParamSub.setGradeRecordSysName(systemParam.getGradeRecordSysName());
          systemParamSub.setSysBusSituationType(systemParam.getSysBusSituationType());
          systemParamSub.setSysBusDescription(systemParam.getSysBusDescription());
          systemParamSub.setSysServiceSitScope(systemParam.getSysServiceSitScope());
          systemParamSub.setSubIsSystem(systemParam.getSubIsSystem());
          systemParamSub.setSysServiceSitObject(systemParam.getSysServiceSitObject());
          systemParamSub.setNpCoverageRange(systemParam.getNpCoverageRange());
          systemParamSub.setNpNetworkProperties(systemParam.getNpNetworkProperties());
          systemParamSub.setFkCompanyCode(systemParam.getFkCompanyCode());
          systemParamSub.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
          systemParamSub.setExecutiveDireCon(systemParam.getExecutiveDireCon());
          systemParamSub.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
          systemParamSub.setEvaluationStatus(systemParam.getEvaluationStatus());
          systemParamSub.setSystemName(systemCode.get(subSystem).getSystemName());
          systemParamSub.setStandardizedCode(systemCode.get(subSystem).getStandardizedCode());
          systemParamSub.setFkFatherSystemId(systemParam.getSystemId());
          subSystemList.add(systemParamSub);
          
          for (int subKey = 0; subKey < subKeyList.size(); subKey++) {
            SystemKeyProducts systemKeyProductsBeanSub = new SystemKeyProducts();
            systemKeyProductsBeanSub.setSystemKeyProductsId(Utils.getUuidFor32());
            systemKeyProductsBeanSub.setFkSystemId(systemParamSub.getSystemId());
            systemKeyProductsBeanSub.setDeleteStatus(subKeyList.get(subKey).getDeleteStatus());
            systemKeyProductsBeanSub.setCreateTime(new Date());
            systemKeyProductsBeanSub.setFkExaminStatus(subKeyList.get(subKey).getFkExaminStatus());
            systemKeyProductsBeanSub.setProductsNumber(subKeyList.get(subKey).getProductsNumber());
            systemKeyProductsBeanSub.setFkNationalIsProducts(subKeyList.get(subKey).getFkNationalIsProducts());
            systemKeyProductsBeanSub.setnUseProbability(subKeyList.get(subKey).getnUseProbability());
            systemKeyProductsList.add(systemKeyProductsBeanSub);
          }
         
          for (int subUse = 0; subUse < subUseList.size(); subUse++) {
            SystemUseServices SystemUseServicesBeanSub = new SystemUseServices();
            SystemUseServicesBeanSub.setSystemUseServicesId(Utils.getUuidFor32());
            SystemUseServicesBeanSub.setFkSystemId(systemParamSub.getSystemId());
            SystemUseServicesBeanSub.setCreateTime(subUseList.get(subUse).getCreateTime());
            SystemUseServicesBeanSub.setServiceIsUse(subUseList.get(subUse).getServiceIsUse());
            SystemUseServicesBeanSub.setFkProductsType(subUseList.get(subUse).getFkProductsType());
            SystemUseServicesBeanSub.setFkResponsibleType(subUseList.get(subUse).getFkResponsibleType());
            SystemUseServicesBeanSub.setCreateTime(new Date());
            systemUseServicesList.add(SystemUseServicesBeanSub);
          }
        }
       }
      }
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(systemParam.getSystemId());
      nodeParam.setOperation("创建");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);

    this.systemKeyProductsMapper.insertSystemKeyProductsBySystemKeyProductsId(
        systemKeyProductsList);
    this.systemUseServicesMapper.insertSystemUseServicesBySystemUseServicesId(
        systemUseServicesList);
    this.systemMapper.insertBatchSystem(subSystemList);
    
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
	public SystemResult queryDetailsSystem(SystemParam systemParam) throws BusinessException {
	  
	  List<SystemKeyResult> systemKey = new ArrayList<SystemKeyResult>();
	  List<SystemUseResult> systemUse = new ArrayList<SystemUseResult>();
	  List<SystemSubResult> systemSub = new ArrayList<SystemSubResult>();
	  
    List<SystemSubResult> systemParamList = this.systemMapper.selectEditBySub(systemParam);
    SystemResult systemResult= this.systemMapper.selectDetailsSystem(systemParam);
    List<SystemKeyResult> keyParamList = this.systemMapper.selectKeyTemp(systemParam);
    List<SystemUseResult> useParamList = this.systemMapper.selectUseTemp(systemParam);
    if (systemParamList != null ){
      for (SystemSubResult systemParamSub : systemParamList) {
        SystemSubResult subSystem = new SystemSubResult();
        subSystem.setSystemName(systemParamSub.getSystemName());  
        subSystem.setStandardizedCode(systemParamSub.getStandardizedCode());
        systemSub.add(subSystem);
        systemResult.setAddSystemSub(systemSub);
      }
    }
    
    if (keyParamList != null) {
      for (SystemKeyResult systemResultKey : keyParamList) {
        SystemKeyResult keySystem = new SystemKeyResult();
        keySystem.setExaminStatusName(systemResultKey.getExaminStatusName());
        keySystem.setNationalIsProductsName(systemResultKey.getNationalIsProductsName());
        keySystem.setProductsNumber(systemResultKey.getProductsNumber());
        keySystem.setnUseProbability(systemResultKey.getnUseProbability());
        keySystem.setFkExaminStatus(systemResultKey.getFkExaminStatus());
        keySystem.setFkNationalIsProducts(systemResultKey.getFkNationalIsProducts());
        keySystem.setOtherName(systemResultKey.getOtherName());
        systemKey.add(keySystem);
        systemResult.setSystemKeyProducts(systemKey);
      }
    }
    
    if(useParamList != null){
      for (SystemUseResult systemResultUse : useParamList) {
        SystemUseResult useSystem = new SystemUseResult();
        useSystem.setProductsTypeName(systemResultUse.getProductsTypeName());
        useSystem.setUseName(systemResultUse.getUseName());
        useSystem.setResponsibleTypeName(systemResultUse.getResponsibleTypeName());
        useSystem.setFkProductsType(systemResultUse.getFkProductsType());
        useSystem.setFkResponsibleType(systemResultUse.getFkResponsibleType());
        useSystem.setServiceIsUse(systemResultUse.getServiceIsUse());
        useSystem.setOtherName(systemResultUse.getOtherName());
        systemUse.add(useSystem);
        systemResult.setSystemUseServices(systemUse);
      }
    }
    return systemResult;
	}
	
	/**
	 * 修改系统信息
	 * @throws BusinessException 
	 */
	@Override
<<<<<<< HEAD
<<<<<<< HEAD
	@Transactional
	@EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public String editSystem(String userName, SystemParam systemParam) throws BusinessException {
	  
	  List<SystemResult> subUpdateSystemSubList = new ArrayList<SystemResult>();
	  List<SystemResult> subUpdateSystemList = new ArrayList<SystemResult>();
	  List<SystemParam> addSubSystem = systemParam.getAddSystemSub();
	  List<SystemParam> systemParamAddList = new ArrayList<SystemParam>();
	  List<SystemKeyProducts> subKeyList = systemParam.getSystemKeyProducts();
	  List<SystemKeyProducts> subSystemKeyProductsList = new ArrayList<SystemKeyProducts>();
	  List<SystemUseServices> subUseList = systemParam.getSystemUseServices();
	  List<SystemUseServices> subSystemUseServicesList = new ArrayList<SystemUseServices>();
	  List<SystemResult> subSystemList = this.systemMapper.selectSubSystem(systemParam);
	  SystemResult systemFather = this.systemMapper.selectSystem(systemParam);
	  
	  //系统是否有子系统
	  if (subSystemList != null) {
	    //修改子系统数据
      for (SystemResult subSys : subSystemList) {
        subSys.getSystemId();
        subSys.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
        subSys.setFkSystemIsMerge(systemParam.getFkSystemIsMerge());
        subSys.setAppIsInternet(systemParam.getAppIsInternet());
        subSys.setGradeRecordSysName(systemParam.getGradeRecordSysName());
        subSys.setSysBusSituationType(systemParam.getSysBusSituationType());
        subSys.setSysBusDescription(systemParam.getSysBusDescription());
        subSys.setSysServiceSitScope(systemParam.getSysServiceSitScope());
        subSys.setSysServiceSitObject(systemParam.getSysServiceSitObject());
        subSys.setNpCoverageRange(systemParam.getNpCoverageRange());
        subSys.setNpNetworkProperties(systemParam.getNpNetworkProperties());
        subSys.setInterconnectionSit(systemParam.getInterconnectionSit());
        subSys.setFkCompanyCode(systemParam.getFkCompanyCode());
        subSys.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
        subSys.setExecutiveDireCon(systemParam.getExecutiveDireCon());
        subSys.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
        subSys.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
        subSys.setSubIsSystem(systemParam.getSubIsSystem());
        subUpdateSystemSubList.add(subSys);
        this.systemMapper.updateSystemSub(subUpdateSystemSubList);
        //删除子系统子表
        systemKeyProductsMapper.deleteSubKey(subSystemList);
        systemUseServicesMapper.deleteSubUse(subSystemList);
        //添加子系统子表
        for (int key = 0; key < subKeyList.size(); key++) {
          SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
          systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
          systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
          systemKeyProducts.setFkSystemId(subSys.getSystemId());
          systemKeyProducts.setDeleteStatus(subKeyList.get(key).getDeleteStatus());
          systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
          systemKeyProducts.setCreateTime(new Date());
          systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
          systemKeyProducts.setProductsNumber(subKeyList.get(key).getProductsNumber());
          systemKeyProducts.setFkNationalIsProducts(subKeyList.get(key).getFkNationalIsProducts());
          systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
          subSystemKeyProductsList.add(systemKeyProducts);
        }
        for (int use = 0; use < subUseList.size(); use++) {
          SystemUseServices SystemUseServices = new SystemUseServices();
          SystemUseServices.setSystemUseServicesId(Utils.getUuidFor32());
          SystemUseServices.setFkSystemId(subSys.getSystemId());
          SystemUseServices.setCreateTime(new Date());
          SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
          SystemUseServices.setFkProductsType(subUseList.get(use).getFkProductsType());
          SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
          SystemUseServices.setFkResponsibleType(subUseList.get(use).getFkResponsibleType());
          subSystemUseServicesList.add(SystemUseServices);
        }
      }
    }
	  //修改主系统信息
    if (systemFather != null) {
      systemFather.setSystemId(systemParam.getSystemId());
      systemFather.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
      systemFather.setFkSystemIsMerge(systemParam.getFkSystemIsMerge());
      systemFather.setAppIsInternet(systemParam.getAppIsInternet());
      systemFather.setGradeRecordSysName(systemParam.getGradeRecordSysName());
      systemFather.setSysBusSituationType(systemParam.getSysBusSituationType());
      systemFather.setSysBusDescription(systemParam.getSysBusDescription());
      systemFather.setSysServiceSitScope(systemParam.getSysServiceSitScope());
      systemFather.setSysServiceSitObject(systemParam.getSysServiceSitObject());
      systemFather.setNpCoverageRange(systemParam.getNpCoverageRange());
      systemFather.setNpNetworkProperties(systemParam.getNpNetworkProperties());
      systemFather.setInterconnectionSit(systemParam.getInterconnectionSit());
      systemFather.setFkCompanyCode(systemParam.getFkCompanyCode());
      systemFather.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
      systemFather.setExecutiveDireCon(systemParam.getExecutiveDireCon());
      systemFather.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
      systemFather.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
      systemFather.setSubIsSystem(systemParam.getSubIsSystem());
      systemFather.setSystemName(systemParam.getSystemName());
      systemFather.setStandardizedCode(systemParam.getStandardizedCode());
      subUpdateSystemList.add(systemFather);
      this.systemMapper.updateSystem(subUpdateSystemList);
    }
    
    this.systemKeyProductsMapper.deleteKey(systemParam);
    this.systemUseServicesMapper.deleteUse(systemParam);
	  //添加系统子表
	  for (int key = 0; key < subKeyList.size(); key++) {
	    SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
	    systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
	    systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
      systemKeyProducts.setFkSystemId(systemParam.getSystemId());
      systemKeyProducts.setDeleteStatus(subKeyList.get(key).getDeleteStatus());
      systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
      systemKeyProducts.setCreateTime(new Date());
      systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
      systemKeyProducts.setProductsNumber(subKeyList.get(key).getProductsNumber());
      systemKeyProducts.setFkNationalIsProducts(subKeyList.get(key).getFkNationalIsProducts());
      systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
      subSystemKeyProductsList.add(systemKeyProducts);
    }
	  for (int use = 0; use < subUseList.size(); use++) {
      SystemUseServices SystemUseServices = new SystemUseServices();
      SystemUseServices.setSystemUseServicesId(Utils.getUuidFor32());
      SystemUseServices.setFkSystemId(systemParam.getSystemId());
      SystemUseServices.setCreateTime(new Date());
      SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
      SystemUseServices.setFkProductsType(subUseList.get(use).getFkProductsType());
      SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
      SystemUseServices.setFkResponsibleType(subUseList.get(use).getFkResponsibleType());
      subSystemUseServicesList.add(SystemUseServices);
    }
	  
	  //验证系统是否为合并系统
	 if (systemParam.getFkSystemIsMerge() == 1) {
	  if (addSubSystem != null) {
	    for (int subSystem = 0; subSystem < addSubSystem.size(); subSystem++) {
	      SystemParam systemParamSub = new SystemParam();
        systemParamSub.setSystemId(Utils.getUuidFor32());
        systemParamSub.setExamineStatus(1);
        systemParamSub.setExaminationStatus(1);
        systemParamSub.setFkSystemType(1);
        systemParamSub.setRecordStatus(1);
        systemParamSub.setGradingStatus(1);
        systemParamSub.setFkChangeMatter(5);
        systemParamSub.setAppIsInternet(2);
        systemParamSub.setEvaluationStatus(1);
        systemParamSub.setCreateTime(new Date());
        systemParamSub.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
        systemParamSub.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
        systemParamSub.setFkSystemIsMerge(systemParam.getFkSystemIsMerge());
        systemParamSub.setAppIsInternet(systemParam.getAppIsInternet());
        systemParamSub.setGradeRecordSysName(systemParam.getGradeRecordSysName());
        systemParamSub.setSysBusSituationType(systemParam.getSysBusSituationType());
        systemParamSub.setSysBusDescription(systemParam.getSysBusDescription());
        systemParamSub.setSysServiceSitScope(systemParam.getSysServiceSitScope());
        systemParamSub.setSubIsSystem(systemParam.getSubIsSystem());
        systemParamSub.setSysServiceSitObject(systemParam.getSysServiceSitObject());
        systemParamSub.setNpCoverageRange(systemParam.getNpCoverageRange());
        systemParamSub.setNpNetworkProperties(systemParam.getNpNetworkProperties());
        systemParamSub.setFkCompanyCode(systemParam.getFkCompanyCode());
        systemParamSub.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
        systemParamSub.setExecutiveDireCon(systemParam.getExecutiveDireCon());
        systemParamSub.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
        systemParamSub.setSystemName(addSubSystem.get(subSystem).getSystemName());
        systemParamSub.setStandardizedCode(addSubSystem.get(subSystem).getStandardizedCode());
        systemParamSub.setFkFatherSystemId(systemParam.getSystemId());
        systemParamAddList.add(systemParamSub);
      
        for (int subKey = 0; subKey < subKeyList.size(); subKey++) {
          SystemKeyProducts systemKeyProductsBean = new SystemKeyProducts();
          systemKeyProductsBean.setSystemKeyProductsId(Utils.getUuidFor32());
          systemKeyProductsBean.setFkSystemId(systemParamSub.getSystemId());
          systemKeyProductsBean.setDeleteStatus(subKeyList.get(subKey).getDeleteStatus());
          systemKeyProductsBean.setnUseProbability(subKeyList.get(subKey).getnUseProbability());
          systemKeyProductsBean.setCreateTime(new Date());
          systemKeyProductsBean.setFkExaminStatus(subKeyList.get(subKey).getFkExaminStatus());
          systemKeyProductsBean.setProductsNumber(subKeyList.get(subKey).getProductsNumber());
          systemKeyProductsBean.setFkNationalIsProducts(subKeyList.get(subKey).getFkNationalIsProducts());
          systemKeyProductsBean.setnUseProbability(subKeyList.get(subKey).getnUseProbability());
          subSystemKeyProductsList.add(systemKeyProductsBean);
        }
        

        for (int subUse = 0; subUse < subUseList.size(); subUse++) {
          SystemUseServices SystemUseServicesBean = new SystemUseServices();
          SystemUseServicesBean.setSystemUseServicesId(Utils.getUuidFor32());
          SystemUseServicesBean.setFkSystemId(systemParamSub.getSystemId());
          SystemUseServicesBean.setCreateTime(new Date());
          SystemUseServicesBean.setServiceIsUse(subUseList.get(subUse).getServiceIsUse());
          SystemUseServicesBean.setFkProductsType(subUseList.get(subUse).getFkProductsType());
          SystemUseServicesBean.setServiceIsUse(subUseList.get(subUse).getServiceIsUse());
          SystemUseServicesBean.setFkResponsibleType(subUseList.get(subUse).getFkResponsibleType());
          subSystemUseServicesList.add(SystemUseServicesBean);
        }
        
        //添加节点状态信息
        NodeParam nodeParam = new NodeParam();
        nodeParam.setSystemId(systemParam.getSystemId());
        nodeParam.setOperation("变更");
        nodeParam.setOperationResult("已创建");
        nodeParam.setOperationOpinion("");
        nodeParam.setOperator(userName);
        this.nodeServiceImpl.addNodeInfo(nodeParam);
      }
    }
	  //获取删除子系统List
	  List<SystemParam> systemDelete = systemParam.getDeleteSystemSub();
	  if(systemDelete != null){
	   //查询父系统的数据
	  List<EvaluationResult> evaluation = this.systemMapper.selectAllByEvaluation(systemParam);
	  List<SelfexaminationResult> self = this.systemMapper.selectAllBySelf(systemParam);
	  GradingListResult grading = this.systemMapper.selectAllByGrading(systemParam);
	  RecordsResult record = this.systemMapper.selectAllByRecord(systemParam);
	  AttachMaterialsListResult material = this.systemMapper.selectAllByMaterial(systemParam);
	  CheckResult check = this.systemMapper.selectAllByCheck(systemParam);
	  List<SystemParam> update = new ArrayList<SystemParam>();
	  
	  List<EvaluationParam> evaluationList = new ArrayList<EvaluationParam>();
	  List<SelfexaminationParam> selfexaminationList = new ArrayList<SelfexaminationParam>();
	  for (SystemParam systemParamDe : systemDelete) {
	    
	    systemParamDe.setFkFatherSystemId("");
	    systemParamDe.setFkSystemType(1);
      update.add(systemParamDe);
      this.systemMapper.updateSubStat(update);
	    
    	  for (EvaluationResult evaluationParam : evaluation) {
    	    EvaluationParam evaluationTempParam = new EvaluationParam();
    	    evaluationTempParam.setEvaluationId(Utils.getUuidFor32());
    	    evaluationTempParam.setFkSystemId(systemParamDe.getSystemId());
    	    evaluationTempParam.setExamName(evaluationParam.getExamName());
    	    evaluationTempParam.setExamTime(evaluationParam.getExamTime());
    	    evaluationTempParam.setExamYear(evaluationParam.getExamYear());
    	    evaluationTempParam.setExamOrg(evaluationParam.getExamOrg());
    	    evaluationTempParam.setExamReport(evaluationParam.getExamReport());
    	    evaluationTempParam.setExamReportName(evaluationParam.getExamReportName());
    	    evaluationTempParam.setFkExamStatus(evaluationParam.getFkExamStatus());
    	    evaluationTempParam.setFkExamResult(evaluationParam.getFkExamResult());
    	    evaluationTempParam.setFkRectificationReu(evaluationParam.getFkRectificationReu());
    	    evaluationTempParam.setRectificationDate(evaluationParam.getRectificationDate());
    	    evaluationTempParam.setDeleteStatus(evaluationParam.getDeleteStatus());
    	    evaluationTempParam.setCreateUserName(evaluationParam.getCreateUserName());
    	    evaluationTempParam.setCreateTime(evaluationParam.getCreateTime());
    	    evaluationTempParam.setUpdateTime(evaluationParam.getUpdateTime());
    	    evaluationList.add(evaluationTempParam);
        }
    	  for (SelfexaminationResult selfexaminationParam : self) {
    	    SelfexaminationParam selfexaminationTemParam = new SelfexaminationParam();
    	    selfexaminationTemParam.setSelfexaminationId(Utils.getUuidFor32());
    	    selfexaminationTemParam.setFkSystemId(systemParamDe.getSystemId());
    	    selfexaminationTemParam.setFkInspectionStatus(selfexaminationParam.getFkInspectionStatus());
    	    selfexaminationTemParam.setFkInspectionReu(selfexaminationParam.getFkInspectionReu());
    	    selfexaminationTemParam.setFkRectificationReu(selfexaminationParam.getFkRectificationReu());
    	    selfexaminationTemParam.setInspectionDate(selfexaminationParam.getInspectionDate());
    	    selfexaminationTemParam.setRectificationDate(selfexaminationParam.getRectificationDate());
    	    selfexaminationTemParam.setDeleteStatus(selfexaminationParam.getDeleteStatus());
    	    selfexaminationTemParam.setCreateUserName(selfexaminationParam.getCreateUserName());
    	    selfexaminationTemParam.setCreateTime(selfexaminationParam.getCreateTime());
    	    selfexaminationTemParam.setUpdateTime(selfexaminationParam.getUpdateTime());
    	    selfexaminationTemParam.setRemark(selfexaminationParam.getRemark());
    	    selfexaminationList.add(selfexaminationTemParam);
        }
        GradingParam gradingTemParam = new GradingParam();
        gradingTemParam.setGradingId(Utils.getUuidFor32());
        gradingTemParam.setFkSystemId(systemParamDe.getSystemId());
        gradingTemParam.setFkBizSPRankDegree(grading.getFkBizSPRankDegree());
        gradingTemParam.setFkBizSPRankLevel(grading.getFkBizSPRankLevel());
        gradingTemParam.setFkBizSystemDegree(grading.getFkBizSystemDegree());
        gradingTemParam.setFkBizSystemLevel(grading.getFkBizSystemLevel());
        gradingTemParam.setFkSpRanklevel(grading.getFkSpRanklevel());
        gradingTemParam.setExpertView(grading.getExpertView());
        gradingTemParam.setRankExplainDesc(grading.getRankExplainDesc());
        gradingTemParam.setRankTime(grading.getRankTime());
        gradingTemParam.setCompetentIsExisting(grading.getCompetentIsExisting());
        gradingTemParam.setCompetentName(grading.getCompetentName());
        gradingTemParam.setCompetentView(grading.getCompetentView());
        gradingTemParam.setFiller(grading.getFiller());
        gradingTemParam.setFillDate(grading.getFillDate());
        gradingTemParam.setDeleteStatus(grading.getDeleteStatus());
        gradingTemParam.setCreateUserName(grading.getCreateUserName());
        gradingTemParam.setCreateTime(grading.getCreateTime());
        gradingTemParam.setUpdateTime(grading.getUpdateTime());
        gradingTemParam.setRemark(grading.getRemark());
        
        RecordsParam recordsParam = new RecordsParam();
        recordsParam.setRecordsId(Utils.getUuidFor32());
        recordsParam.setFkSystemId(systemParamDe.getSystemId());
        recordsParam.setFkrevokematter(record.getFkrevokematter());
        recordsParam.setRecordCode(record.getRecordCode());
        recordsParam.setRecordCompany(record.getRecordCompany());
        recordsParam.setRecordDate(record.getRecordDate());
        recordsParam.setAcceptCompany(record.getAcceptCompany());
        recordsParam.setAcceptDate(record.getAcceptDate());
        recordsParam.setAcceptReason(record.getAcceptReason());
        recordsParam.setRevokereason(record.getRevokereason());
        recordsParam.setRevokecontent(record.getRevokecontent());
        recordsParam.setDeleteStatus(record.getDeleteStatus());
        recordsParam.setCreateUserName(record.getCreateUserName());
        recordsParam.setCreateTime(record.getCreateTime());
        recordsParam.setUpdateTime(record.getUpdateTime());
        recordsParam.setRemark(record.getRemark());
        
        AttachMaterialsParam attachMaterialsParam = new AttachMaterialsParam();
        attachMaterialsParam.setAttachId(Utils.getUuidFor32());
        attachMaterialsParam.setFkSystemId(systemParamDe.getSystemId());
        attachMaterialsParam.setFkSyssonId(material.getFkSyssonId());
        attachMaterialsParam.setFkAttachType(material.getFkAttachType());
        attachMaterialsParam.setAttachName(material.getAttachName());
        attachMaterialsParam.setMongoFileId(material.getMongoFileId());
        attachMaterialsParam.setAttachPath(material.getAttachPath());
        attachMaterialsParam.setDeleteStatus(material.getDeleteStatus());
        attachMaterialsParam.setCreateUserName(material.getCreateUserName());
        attachMaterialsParam.setCreateTime(material.getCreateTime());
        attachMaterialsParam.setUpdateTime(material.getUpdateTime());
        attachMaterialsParam.setRemark(material.getRemark());
        
        CheckParam checkParam = new CheckParam();
        checkParam.setCheckId(Utils.getUuidFor32());
        checkParam.setFkSystemId(systemParamDe.getSystemId());
        checkParam.setFkExaminStatus(check.getFkExaminStatus());
        checkParam.setFkBusinessNode(check.getFkBusinessNode());
        checkParam.setInstanceName(check.getInstanceName());
        checkParam.setInitiator(check.getInitiator());
        checkParam.setPrevExecutor(check.getPrevExecutor());
        checkParam.setExecuteTime(check.getExecuteTime());
        checkParam.setDeleteStatus(check.getDeleteStatus());
        checkParam.setCreateUserName(check.getCreateUserName());
        checkParam.setCreateTime(check.getCreateTime());
        checkParam.setUpdateTime(check.getUpdateTime());
        checkParam.setRemark(check.getRemark());
        
        
        
        this.systemMapper.insertGradingTemp(gradingTemParam);
        this.systemMapper.insertRecordsTemp(recordsParam);
        this.systemMapper.insertAttachMaterialsTemp(attachMaterialsParam);
        this.systemMapper.insertCheckTemp(checkParam);
    }
	  this.systemMapper.insertBatchSystem(systemParamAddList);
	  this.systemMapper.insertEvaluationTemp(evaluationList);
	  this.systemMapper.insertSelfexaminationTemp(selfexaminationList);
	  
	  }
   }
	  
    this.systemKeyProductsMapper
        .insertSystemKeyProductsBySystemKeyProductsId(subSystemKeyProductsList);
    this.systemUseServicesMapper
        .insertSystemUseServicesBySystemUseServicesId(subSystemUseServicesList);
    
	  return systemParam.getSystemId();
=======
=======
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
  public SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException {
	  if(StringUtils.isBlank(systemParam.getSystemId())) 
	    throw new BusinessException(EnumResult.ERROR);
	  return this.systemMapper.selectEditSystem(systemParam);
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
	}
	
	/**
   * 修改系统状态
   */
  @Override
  @Transactional
  public void editSystemStatusBySystemId(SystemParam systemParam) throws BusinessException {
    this.systemMapper.updateSystemStatusBySystemId(systemParam);
  }
  
  /**
   * 系统信息模板导出
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  @Override
  public void exportExcelForSystemTemplate(SystemParam systemParam) throws FileNotFoundException, IOException {
    List<SystemTemplateListResult> systemTemPlate = 
        this.systemMapper.selectSystemTemPlate(systemParam);
//    String url="C://Users//hasee//Desktop//模板文件//信息系统导入模版 - 副本.xlsm";
//    File fi = new File(url);
//    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
//    HSSFWorkbook wb = new HSSFWorkbook(fs);
//    HSSFSheet sheet = wb.getSheetAt(0);
//    sheet.setForceFormulaRecalculation(true);
//    for (SystemTemplateListResult systemTempResult : systemTemPlate) {
//      org.apache.poi.hssf.usermodel.HSSFCell cell = sheet.getRow(8).getCell(6);
//      cell.setCellValue(systemTempResult.getWhenInvestmentUse());
//      org.apache.poi.hssf.usermodel.HSSFCell cell1 = sheet.getRow(8).getCell(7);
//      cell1.setCellValue(systemTempResult.getExecutiveOfficeName());
//    }
//    FileOutputStream out = new FileOutputStream("C://Users//hasee//Desktop//模板文件//备份1.xlsm");
//    wb.write(out);
//    out.close();
    
//    String strFilePath = "C://export/";
//    String strFileName = "xlsx"+"_"+DateUtils.getMilliseconds()+".xlsx";
//    
//    String primaryfilePth = SystemConstant.EXCEL_FILE_PATH;//原文件路径
    
    
    List<SystemUseResult> useTempPlate = 
        this.systemMapper.selectUseTemp(systemParam);
    List<SystemKeyResult> keyTempPlate = 
        this.systemMapper.selectKeyTemp(systemParam);
    
    JacobExcelTool tool = new JacobExcelTool();
    //打开excel文件
    tool.OpenExcel("C://Users//hasee//Desktop//模板文件//信息系统导入模版 - 副本.xlsm",false,false);
    for (SystemUseResult systemUseResult : useTempPlate) {
      
      //服务类型
      tool.setValue(tool.getCurrentSheet(), "AC", "value",systemUseResult.getProductsTypeName());
      //是否有此服务类型
      if (systemUseResult.getProductsTypeName().equals("等级测评")) {
        tool.setValue(tool.getCurrentSheet(), "AD5", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD5", "value","否");
      }
      if (systemUseResult.getProductsTypeName().equals("风险评估")) {
        tool.setValue(tool.getCurrentSheet(), "AD6", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD6", "value","否");
      }
      if (systemUseResult.getProductsTypeName().equals("灾难恢复")) {
        tool.setValue(tool.getCurrentSheet(), "AD7", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD7", "value","否");
      }
      if (systemUseResult.getProductsTypeName().equals("应急响应")) {
        tool.setValue(tool.getCurrentSheet(), "AD8", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD8", "value","否");
      }
      if (systemUseResult.getProductsTypeName().equals("系统集成")) {
        tool.setValue(tool.getCurrentSheet(), "AD9", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD9", "value","否");
      }
      if (systemUseResult.getProductsTypeName().equals("安全咨询")) {
        tool.setValue(tool.getCurrentSheet(), "AD10", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD10", "value","否");
      }
      if (systemUseResult.getProductsTypeName().equals("安全培训")) {
        tool.setValue(tool.getCurrentSheet(), "AD11", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD11", "value","否");
      }
      //服务责任方类型
      tool.setValue(tool.getCurrentSheet(), "AE8", "value",systemUseResult.getFkResponsibleType());
    }
    
    for (SystemKeyResult systemKeyResult : keyTempPlate) {
      //系统名称
      tool.setValue(tool.getCurrentSheet(), "Y8", "value",systemKeyResult.getExaminStatusName());
      //数量
      tool.setValue(tool.getCurrentSheet(), "Z8", "value",systemKeyResult.getProductsNumber());
      //使用情况
      tool.setValue(tool.getCurrentSheet(), "AA8", "value",systemKeyResult.getFkNationalIsProducts());
      //使用国产率
      tool.setValue(tool.getCurrentSheet(), "AB8", "value",systemKeyResult.getnUseProbability());
    }
    
    
    for (SystemTemplateListResult systemTempResult : systemTemPlate) {
      //系统名称
//      tool.setValue(tool.getCurrentSheet(), "B5", "value",systemTempResult.getSystemName());
      //系统编码
//      tool.setValue(tool.getCurrentSheet(), "C5", "value",systemTempResult.getStandardizedCode());
      //等保备案名称
//      tool.setValue(tool.getCurrentSheet(), "D5", "value",systemTempResult.getGradeRecordSysName());
      //所属单位名称
      tool.setValue(tool.getCurrentSheet(), "E5", "value",systemTempResult.getCompanyName());
      //何时投入使用
      tool.setValue(tool.getCurrentSheet(), "F5", "value",systemTempResult.getWhenInvestmentUse());
      //主管处室名称
      tool.setValue(tool.getCurrentSheet(), "G5", "value",systemTempResult.getExecutiveOfficeName());
      //主管联系人
      tool.setValue(tool.getCurrentSheet(), "H5", "value",systemTempResult.getExecutiveDireCon());
      //联系人电话
      tool.setValue(tool.getCurrentSheet(), "I5", "value",systemTempResult.getExecutiveDireConTel());
      //系统是否为分系统
      tool.setValue(tool.getCurrentSheet(), "J5", "value",systemTempResult.getSubIsSystem());
      //上级系统名称
      //tool.setValue(tool.getCurrentSheet(), "K8", "value",systemTempResult.getExecutiveDireConTel());
      //业务类型
//      tool.setValue(tool.getCurrentSheet(), "L8", "value",systemTempResult.getSysBusSituationType());
      //是否有此业务类型
      if (systemTempResult.getSysBusSituationType().equals("生产作业")) {
        tool.setValue(tool.getCurrentSheet(), "M5", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "M5", "value","否");
      }
      if(systemTempResult.getSysBusSituationType().equals("指挥调度")) {
        tool.setValue(tool.getCurrentSheet(), "M6", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "M6", "value","否");
      }
      if (systemTempResult.getSysBusSituationType().equals("管理控制")) {
        tool.setValue(tool.getCurrentSheet(), "M7", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "M7", "value","否");
      }
      if (systemTempResult.getSysBusSituationType().equals("内部办公")) {
        tool.setValue(tool.getCurrentSheet(), "M8", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "M8", "value","否");
      }
      if (systemTempResult.getSysBusSituationType().equals("公众服务")) {
        tool.setValue(tool.getCurrentSheet(), "M9", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "M9", "value","否");
      }
      if (!systemTempResult.getSysBusSituationType().equals("生产作业")
          &&systemTempResult.getSysBusSituationType().equals("指挥调度")
          &&systemTempResult.getSysBusSituationType().equals("管理控制")
          &&systemTempResult.getSysBusSituationType().equals("内部办公")
          &&systemTempResult.getSysBusSituationType().equals("公众服务")) {
        tool.setValue(tool.getCurrentSheet(), "M10", "value",systemTempResult.getSysBusSituationType());
      }
      //业务描述
      tool.setValue(tool.getCurrentSheet(), "N5", "value",systemTempResult.getSysBusDescription());
      //服务范围
      tool.setValue(tool.getCurrentSheet(), "O5", "value",systemTempResult.getSysServiceSitScope());
      //服务范围所跨地区个数
      tool.setValue(tool.getCurrentSheet(), "P5", "value",systemTempResult.getSysServiceSitScope());
      //服务对象
      tool.setValue(tool.getCurrentSheet(), "Q5", "value",systemTempResult.getSysServiceSitObject());
      //其他
      tool.setValue(tool.getCurrentSheet(), "R5", "value",systemTempResult.getSysServiceSitObject());
      //覆盖范围
//      tool.setValue(tool.getCurrentSheet(), "S8", "value",systemTempResult.getNpCoverageRange());
      //是否有此覆盖范围值
      if (systemTempResult.getNpCoverageRange().equals("局域网")) {
        tool.setValue(tool.getCurrentSheet(), "T5", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "T5", "value","否");
      }
      if (systemTempResult.getNpCoverageRange().equals("城域网")) {
        tool.setValue(tool.getCurrentSheet(), "T6", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "T6", "value","否");
      }
      if (systemTempResult.getNpCoverageRange().equals("广域网")) {
        tool.setValue(tool.getCurrentSheet(), "T7", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "T7", "value","否");
      }
      if (systemTempResult.getNpCoverageRange().equals("其他")) {
        tool.setValue(tool.getCurrentSheet(), "T10", "value",systemTempResult.getNpCoverageRange());
      }else{
        tool.setValue(tool.getCurrentSheet(), "T10", "value","否");
      }
      //网络性质
      tool.setValue(tool.getCurrentSheet(), "U8", "value",systemTempResult.getNpNetworkProperties());
      //其他
      tool.setValue(tool.getCurrentSheet(), "V8", "value",systemTempResult.getNpNetworkProperties());
      //系统互联情况
//      tool.setValue(tool.getCurrentSheet(), "W8", "value",systemTempResult.getInterconnectionSit());
      //是否有此系统互联情况值
      if (systemTempResult.getInterconnectionSit().equals("与其他行业系统连接")) {
        tool.setValue(tool.getCurrentSheet(), "X5", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "X5", "value","否");
      }
      if (systemTempResult.getInterconnectionSit().equals("与本行业其他单位系统连接")) {
        tool.setValue(tool.getCurrentSheet(), "X6", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "X6", "value","否");
      }
      if (systemTempResult.getInterconnectionSit().equals("与本单位其他系统连接")) {
        tool.setValue(tool.getCurrentSheet(), "X7", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "X7", "value","否");
      }
      if (systemTempResult.getInterconnectionSit().equals("其他")) {
        tool.setValue(tool.getCurrentSheet(), "X10", "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "T10", "value","否");
      }
    }
    FileOutputStream out = new FileOutputStream("C://Users//hasee//Desktop//模板文件//备份1.xlsm");
    out.close();
    //关闭并保存，释放对象
    tool.CloseExcel(true, true);
    
    
    
//    File creFile = new File(strFilePath);
//    if (!creFile.exists()) {
//      creFile.mkdirs();
//    }
//    // 生成excel
//    ExcelBean excelBean = new ExcelBean();
//    excelBean.setFilePath(strFilePath + strFileName);
//    
//    SheetBean sheetBean = new SheetBean();
//    
//    sheetBean.setRowFixed(2);
//    Map<Integer, Short> columnWidthMap = new HashMap<Integer, Short>();
//    columnWidthMap.put(9, (short)25);
//    sheetBean.setColumnWidthMap(columnWidthMap);
//    sheetBean.setDefaultRowHeight((short) 20);
//    sheetBean.setDefaultColumnWidth((short) 15);
//    sheetBean.setSheetName("Sheet1");
//    List<List<CellBean>> dataList = new ArrayList<List<CellBean>>();
//    List<CellBean> cellList = new ArrayList<CellBean>();
//    // 装载第一行为表头
//    cellList.add(ExcelUtils.getExportCelBean("系统名称"));
//    cellList.add(ExcelUtils.getExportCelBean("系统编码"));
//    cellList.add(ExcelUtils.getExportCelBean("等保备案系统名称"));
//    cellList.add(ExcelUtils.getExportCelBean("所属单位名称"));
//    cellList.add(ExcelUtils.getExportCelBean("何时投入使用"));
//    cellList.add(ExcelUtils.getExportCelBean("主管处室名称"));
//    cellList.add(ExcelUtils.getExportCelBean("主管联系人"));
//    cellList.add(ExcelUtils.getExportCelBean("联系人电话"));
//    cellList.add(ExcelUtils.getExportCelBean("系统是否为分系统"));
//    //cellList.add(ExcelUtils.getExportCelBean("上级系统名称"));
//    cellList.add(ExcelUtils.getExportCelBean("业务类型"));
//    cellList.add(ExcelUtils.getExportCelBean("是否有此业务类型"));
//    cellList.add(ExcelUtils.getExportCelBean("业务描述"));
//    cellList.add(ExcelUtils.getExportCelBean("服务范围"));
//    cellList.add(ExcelUtils.getExportCelBean("服务范围所跨地区个数或其他"));
//    cellList.add(ExcelUtils.getExportCelBean("服务对象"));
//    //cellList.add(ExcelUtils.getExportCelBean("其他"));
//    cellList.add(ExcelUtils.getExportCelBean("覆盖范围"));
//    cellList.add(ExcelUtils.getExportCelBean("是否有此覆盖范围值"));
//    cellList.add(ExcelUtils.getExportCelBean("网络性质"));
//    //cellList.add(ExcelUtils.getExportCelBean("其他"));
//    cellList.add(ExcelUtils.getExportCelBean("系统互联情况"));
//    cellList.add(ExcelUtils.getExportCelBean("是否有此系统互联情况值"));
//    cellList.add(ExcelUtils.getExportCelBean("产品类型"));
//    cellList.add(ExcelUtils.getExportCelBean("数量"));
//    cellList.add(ExcelUtils.getExportCelBean("使用情况"));
//    cellList.add(ExcelUtils.getExportCelBean("使用国产品率"));
//    cellList.add(ExcelUtils.getExportCelBean("服务类型"));
//    cellList.add(ExcelUtils.getExportCelBean("是否有此服务类型"));
//    cellList.add(ExcelUtils.getExportCelBean("服务责任方类型"));
//    dataList.add(cellList);
//    
//   // List<SystemTemplateListResult> systemTemPlate = new ArrayList();
//
//    if(systemTemPlate != null && systemTemPlate.size() > 0){
//      for (SystemTemplateListResult systemListResult : systemTemPlate) {
//        cellList = new ArrayList<CellBean>();
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSystemName()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getStandardizedCode()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getGradeRecordSysName()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getCompanyName()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getWhenInvestmentUse()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getExecutiveOfficeName()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getExecutiveDireCon()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getExecutiveDireConTel()));
//        //系统是否为分系统
//        if (systemListResult.getFkSystemIsMerge() == 1) {
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSysBusSituationType()));
//        //是否有此业务类型
//        if (systemListResult.getSysBusSituationType().equals("生产作业")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        } else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getSysBusSituationType().equals("指挥调度")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        } else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getSysBusSituationType().equals("管理控制")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        } else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getSysBusSituationType().equals("内部办公")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        } else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getSysBusSituationType().equals("公众服务")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        } else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getSysBusSituationType().equals("其他")) {
//          cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSysBusSituationType()));
//        } else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSysBusDescription()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSysServiceSitScope()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSysServiceSitScope()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSysServiceSitObject()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getNpCoverageRange()));
//        if (systemListResult.getNpCoverageRange().equals("局域网")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getNpCoverageRange().equals("地域网")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getNpCoverageRange().equals("广域网")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getNpCoverageRange().equals("其他")) {
//          cellList.add(ExcelUtils.getExportCelBean(systemListResult.getNpCoverageRange()));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getNpNetworkProperties()));
//        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getInterconnectionSit()));
//        if (systemListResult.getInterconnectionSit().equals("与其他行业系统连接")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getInterconnectionSit().equals("与本行业其他单位系统连接")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getInterconnectionSit().equals("与本单位其他系统连接")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        if (systemListResult.getInterconnectionSit().equals("其他")) {
//          cellList.add(ExcelUtils.getExportCelBean(systemListResult.getInterconnectionSit()));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        dataList.add(cellList);
//      }
//    }
//    
//    if (useTempPlate != null && useTempPlate.size() > 0) {
//      for (SystemUseResult systemUseResult : useTempPlate) {
//        cellList = new ArrayList<CellBean>();
//        cellList.add(ExcelUtils.getExportCelBean(systemUseResult.getProductsTypeName()));
//        if (systemUseResult.getProductsTypeName().equals("等级测评")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else if (systemUseResult.getProductsTypeName().equals("风险评估") ) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else if (systemUseResult.getProductsTypeName().equals("灾难恢复")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else if (systemUseResult.getProductsTypeName().equals("应急响应")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else if (systemUseResult.getProductsTypeName().equals("系统集成")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else if (systemUseResult.getProductsTypeName().equals("安全咨询")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }else if (systemUseResult.getProductsTypeName().equals("安全培训")) {
//          cellList.add(ExcelUtils.getExportCelBean("是"));
//        }if (systemUseResult.getProductsTypeName().equals("其他")) {
//          cellList.add(ExcelUtils.getExportCelBean(systemUseResult.getOtherName()));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("否"));
//        }
//        cellList.add(ExcelUtils.getExportCelBean(systemUseResult.getResponsibleTypeName()));
//        dataList.add(cellList);
//      }
//    }
//    
//    if (keyTempPlate != null && keyTempPlate.size() > 0) {
//      for (SystemKeyResult systemKeyResult : keyTempPlate) {
//        cellList = new ArrayList<CellBean>();
//        cellList.add(ExcelUtils.getExportCelBean(systemKeyResult.getExaminStatusName()));
//        cellList.add(ExcelUtils.getExportCelBean(systemKeyResult.getProductsNumber()));
//        cellList.add(ExcelUtils.getExportCelBean(systemKeyResult.getNationalIsProductsName()));
//        cellList.add(ExcelUtils.getExportCelBean(systemKeyResult.getnUseProbability()));
//        dataList.add(cellList);
//      }
//    }
//    
//    sheetBean.setDataList(dataList);
//    List<SheetBean> sheetBeanList = new ArrayList<SheetBean>();
//    sheetBeanList.add(sheetBean);
//    excelBean.setSheetList(sheetBeanList);
//    // 创建excel错误
//    try {
//      ExcelUtils.write(excelBean);
//    } catch (IOException e) {
//      e.printStackTrace();
//    } 
  }
  /**
   * 系统批量导入
   * @throws IOException 
   * @throws BusinessException 
   */
  @Override
  public void importForSystemTemplate() throws IOException, BusinessException {
    
    List<SystemTemplateListResult> systemCode = systemMapper.selectSystemCode();
    String strFilePath = "C://export/xlsx_1528181557754.xlsx";
    
    // 读取excel数据
    List<String[]> dataList = null;
    try {
      dataList = ExcelUtils.read(strFilePath, "sheet1");
    } catch (InvalidFormatException e) {
      e.printStackTrace();
    } 
    
    // 将excel取出的数据过滤后转成标准数据
    List<SystemTemplateListResult> systemTemplateListResult = new ArrayList<SystemTemplateListResult>();
    
    int dataListSize = dataList.size();
    if(dataListSize>5000){
      throw new BusinessException(EnumResult.ERROR);
    }
    
    for (int dataListTem = 1; dataListTem < dataListSize; dataListTem++) {
      String[] strsData = dataList.get(dataListTem);
      SystemTemplateListResult systemTemplate = new SystemTemplateListResult();
      for (int i = 0; i < systemCode.size(); i++) {
        if(strsData[1].equals(systemCode.get(i))){
          System.out.println(systemCode.get(i)+"_______________"+strsData[1]);
          throw new BusinessException(EnumResult.LINKEDID_ERROR);
        }else{
          systemTemplate.setSystemId(Utils.getUuidFor32());
          systemTemplate.setExamineStatus(1);
          systemTemplate.setExaminationStatus(1);
          systemTemplate.setSubIsSystem(2);
          systemTemplate.setFkSystemType(1);
          systemTemplate.setFkSystemIsMerge(2);
          systemTemplate.setRecordStatus(1);
          systemTemplate.setGradingStatus(1);
          systemTemplate.setFkChangeMatter(5);
          systemTemplate.setAppIsInternet(2);
          systemTemplate.setCreateTime(new Date());
          systemTemplate.setWhenInvestmentUse(new Date());
          systemTemplate.setEvaluationStatus(1);
          systemTemplate.setFkInfoSysTypeCon(1);
          systemTemplate.setSystemName(strsData[0]);
          systemTemplate.setStandardizedCode(strsData[1]);
          systemTemplate.setExecutiveOfficeName(strsData[2]);
        }
      }
      systemTemplateListResult.add(systemTemplate);
    }
    // 处理数据
    // 将数据放入数据库
    if (systemTemplateListResult.size() > 0) {
      // 将数据放入数据库
      this.systemMapper.insertSystemTemplate(systemTemplateListResult);
    }else {
      throw new BusinessException(EnumResult.ERROR);
    }
  }


  /**
   * 下载
   */
  @Override
  public void exportUploadSystemInfo(
      HttpServletRequest request, HttpServletResponse response,
      String strFilePath){
    String strFilePaths = "C://export/xlsx_1528181557754.xlsx";
    String filePath = strFilePaths.substring(0, strFilePaths.lastIndexOf("/")+1);
    String fileName = strFilePaths.substring(strFilePaths.lastIndexOf("/")+1,strFilePaths.length());
    // 下载文件
    try {
      com.sinopec.smcc.cpro.tools.FileOperateUtil.download(request, response, filePath,
          fileName, "UTF-8", "ISO8859-1", 102400);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改系统信息回显
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException {
    List<SystemKeyResult> systemKey = new ArrayList<SystemKeyResult>();
    List<SystemUseResult> systemUse = new ArrayList<SystemUseResult>();
    List<SystemSubResult> systemSub = new ArrayList<SystemSubResult>();
    
    List<SystemSubResult> systemParamList = this.systemMapper.selectEditBySub(systemParam);
    SystemResult systemResult= this.systemMapper.selectEditSystem(systemParam);
    List<SystemKeyResult> keyParamList = this.systemMapper.selectKeyTemp(systemParam);
    List<SystemUseResult> useParamList = this.systemMapper.selectUseTemp(systemParam);
    if (systemParamList != null ){
      for (SystemSubResult systemParamSub : systemParamList) {
        SystemSubResult subSystem = new SystemSubResult();
        subSystem.setSystemName(systemParamSub.getSystemName());  
        subSystem.setStandardizedCode(systemParamSub.getStandardizedCode());
        systemSub.add(subSystem);
        systemResult.setAddSystemSub(systemSub);
      }
    }
    
    if (keyParamList != null) {
      for (SystemKeyResult systemResultKey : keyParamList) {
        SystemKeyResult keySystem = new SystemKeyResult();
        keySystem.setExaminStatusName(systemResultKey.getExaminStatusName());
        keySystem.setNationalIsProductsName(systemResultKey.getNationalIsProductsName());
        keySystem.setProductsNumber(systemResultKey.getProductsNumber());
        keySystem.setnUseProbability(systemResultKey.getnUseProbability());
        systemKey.add(keySystem);
        systemResult.setSystemKeyProducts(systemKey);
      }
    }
    
    if(useParamList != null){
      for (SystemUseResult systemResultUse : useParamList) {
        SystemUseResult useSystem = new SystemUseResult();
        useSystem.setProductsTypeName(systemResultUse.getProductsTypeName());
        useSystem.setUseName(systemResultUse.getUseName());
        useSystem.setResponsibleTypeName(systemResultUse.getResponsibleTypeName());
        systemUse.add(useSystem);
        systemResult.setSystemUseServices(systemUse);
      }
    }
    return systemResult;
  }

  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public SystemGradingChangeResult queryGradingEditAudit(SystemParam systemParam) {
    return this.systemMapper.selectgradingEditAudit(systemParam);
  }
}

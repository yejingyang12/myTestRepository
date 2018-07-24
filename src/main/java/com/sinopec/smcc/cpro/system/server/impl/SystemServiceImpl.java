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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.system.entity.SystemGradingChangeResult;
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
import com.sinopec.smcc.cpro.tools.JacobExcelTool;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.cpro.tools.excel.ExcelUtils;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

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
	@Autowired
	private CheckService checkServiceImpl;
	@Autowired
	private MainService mainServiceImpl;
	@Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
	@Autowired
  private UserApiService userApiServiceImpl;
	
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
		
		//获得相应列表数据
		List<SystemListResult> systemListResultlist = new ArrayList<SystemListResult>();
		
		//权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return new PageInfo<>();
    }else{
     //初始化分页拦截器
  		PageHelper.startPage(systemParam.getCurrentPage(), systemParam.getPageSize(),
  		    orderBy.toString());
    	
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        systemListResultlist = 
            this.systemMapper.selectAllBySystemParam(systemParam);
        break;
      case "2":
        systemParam.setPlateList(organizationApiResult.getNameList());
        systemListResultlist =  
            this.systemMapper.selectAllBySystemParam(systemParam);
        break;
      case "3":
        systemParam.setCompanyList(organizationApiResult.getCodeList());
        systemListResultlist =  
            this.systemMapper.selectAllBySystemParam(systemParam);
        break;

      default:
        break;
      }
    }
		//装载列表数据
		PageInfo<SystemListResult> pageInfo = new PageInfo<>(systemListResultlist);
		return pageInfo;
	}
  
  /**
   * 添加系统信息
   */
  @Override
  @Transactional
  public String saveSystem(String userName, SystemParam systemParam) 
      throws BusinessException {
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    String fkCompanyCode = "";
    String[] orgCodes = userDTO.getOrgCode().split(",");
    for (String strCode : orgCodes) {
      if (strCode.trim().length()>8) {
        fkCompanyCode = strCode.trim().substring(0, 8);
      }else {
        fkCompanyCode = strCode.trim();
      }
      break;
    }
    
    List<SystemKeyProducts> keyList = systemParam.getSystemKeyProducts();
    List<SystemParam> systemCode = systemParam.getAddSystemSub();
    List<SystemKeyProducts> subKeyList = systemParam.getSystemKeyProducts();
    List<SystemUseServices> useList = systemParam.getSystemUseServices();
    List<SystemUseServices> subUseList = systemParam.getSystemUseServices();
    
    List<SystemParam> subSystemList = new ArrayList<SystemParam>();
    List<SystemKeyProducts> systemKeyProductsList = new ArrayList<SystemKeyProducts>();
    List<SystemUseServices> systemUseServicesList = new ArrayList<SystemUseServices>();
      systemParam.setSystemId(Utils.getUuidFor32());
      systemParam.setExamineStatus(1);
      systemParam.setExaminationStatus(1);
      systemParam.setSubIsSystem(2);
      if (systemParam.getFkSystemIsMerge() == 1) {
        systemParam.setFkSystemType(2);
      }else if(systemParam.getFkSystemIsMerge() == 2){
        systemParam.setFkSystemType(1);
      }
      systemParam.setRecordStatus(1);
      systemParam.setGradingStatus(1);
      systemParam.setFkChangeMatter(5);
      systemParam.setAppIsInternet(2);
      systemParam.setCreateTime(new Date());
      systemParam.setEvaluationStatus(1);
      if(systemParam.getFkComCode() == 2){
        systemParam.setFkCompanyCode(fkCompanyCode);
      }
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
        systemKeyProductsBean.setOtherName(keyList.get(key).getOtherName());
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
        SystemUseServicesBean.setOtherName(useList.get(use).getOtherName());
        systemUseServicesList.add(SystemUseServicesBean);
      }
      
   if (systemParam.getFkSystemIsMerge() == 1) {
    if(systemCode != null){
        for (int subSystem = 0; subSystem < systemCode.size(); subSystem++) {
          SystemParam systemParamSub = new SystemParam();
          systemParamSub.setSystemId(Utils.getUuidFor32());
          systemParamSub.setExamineStatus(systemParam.getExaminationStatus());
          systemParamSub.setExaminationStatus(systemParam.getExaminationStatus());
          systemParamSub.setFkSystemType(3);
          systemParamSub.setRecordStatus(systemParam.getRecordStatus());
          systemParamSub.setGradingStatus(systemParam.getGradingStatus());
          systemParamSub.setFkChangeMatter(systemParam.getFkChangeMatter());
          systemParamSub.setAppIsInternet(systemParam.getAppIsInternet());
          systemParamSub.setCreateTime(systemParam.getCreateTime());
          systemParamSub.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
          systemParamSub.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
          systemParamSub.setFkSystemIsMerge(2);
          systemParamSub.setAppIsInternet(systemParam.getAppIsInternet());
          systemParamSub.setGradeRecordSysName(systemParam.getGradeRecordSysName());
          systemParamSub.setSysBusSituationType(systemParam.getSysBusSituationType());
          systemParamSub.setSysBusDescription(systemParam.getSysBusDescription());
          systemParamSub.setSysServiceSitScope(systemParam.getSysServiceSitScope());
          systemParamSub.setSubIsSystem(1);
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
          systemParamSub.setCompanyName(systemParam.getCompanyName());
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
            systemKeyProductsBeanSub.setOtherName(subKeyList.get(subKey).getOtherName());
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
            SystemUseServicesBeanSub.setOtherName(subUseList.get(subUse).getOtherName());
            systemUseServicesList.add(SystemUseServicesBeanSub);
          }
        }
       }
      }
   /*
   //添加节点状态信息
   NodeParam nodeParam = new NodeParam();
   nodeParam.setSystemId(systemParam.getSystemId());
   nodeParam.setOperation("创建");
   nodeParam.setOperationResult("已创建");
   nodeParam.setOperationOpinion("");
   nodeParam.setOperator(userName);
   this.nodeServiceImpl.addNodeInfo(nodeParam);*/

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
	  
    SystemResult systemResult= this.systemMapper.selectDetailsSystem(systemParam);
    if(systemResult.getFkSystemIsMerge()==1){
      List<SystemSubResult> systemParamList = this.systemMapper.selectEditBySub(systemParam);
      if (systemParamList != null ){
        systemResult.setAddSystemSub(systemParamList);
      }
    }
    List<SystemKeyResult> keyParamList = this.systemMapper.selectKeyTemp(systemParam);
    List<SystemUseResult> useParamList = this.systemMapper.selectUseTemp(systemParam);
    
    if (keyParamList != null) {
      systemResult.setSystemKeyProducts(keyParamList);
    }
    if(useParamList != null){
      systemResult.setSystemUseServices(useParamList);
    }
    return systemResult;
	}
	
	/**
	 * 修改系统信息
	 * @throws BusinessException 
	 */
	@Override
	@Transactional
  public String editSystem(String userName, SystemParam systemParam) throws BusinessException {
	  
//	  List<SystemResult> subUpdateSystemSubList = new ArrayList<SystemResult>();
//	  List<SystemResult> subUpdateSystemList = new ArrayList<SystemResult>();
//	  List<SystemParam> addSubSystem = systemParam.getAddSystemSub();
	  
	  
	  List<SystemParam> systemParamAddList = new ArrayList<SystemParam>();
	  List<SystemKeyProducts> subKeyList = systemParam.getSystemKeyProducts();
	  List<SystemKeyProducts> subSystemKeyProductsList = new ArrayList<SystemKeyProducts>();
	  List<SystemUseServices> subUseList = systemParam.getSystemUseServices();
	  List<SystemUseServices> subSystemUseServicesList = new ArrayList<SystemUseServices>();
	  
//	  SystemResult systemFather = this.systemMapper.selectSystem(systemParam);
	  
	  //判断是否为合并系统
	  if(systemParam.getFkSystemIsMerge()==1){
	    //查询子表信息
	    List<SystemSubResult> subSystemList = this.systemMapper.selectEditBySub(systemParam);
//	     List<SystemParam> subSystemList = systemParam.getAddSystemSub();

	    for (SystemSubResult systemSubParam : subSystemList) {
	      SystemParam systemTempParam = new SystemParam();
	      systemTempParam.addSystemParam(systemParam);
	      systemTempParam.setSystemId(systemSubParam.getSystemId());
	      systemParamAddList.add(systemTempParam);
	      this.systemMapper.updateSystemEdit(systemTempParam);
      }
	    systemParamAddList.add(systemParam);
	    this.systemMapper.updateSystemEdit(systemParam);
	    
	    for (SystemParam systemSonParam : systemParamAddList) {
	      //添加系统子表
	      for (int key = 0; key < subKeyList.size(); key++) {
	        SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
	        systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
	        systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
	        systemKeyProducts.setFkSystemId(systemSonParam.getSystemId());
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
	        SystemUseServices.setFkSystemId(systemSonParam.getSystemId());
	        SystemUseServices.setCreateTime(new Date());
	        SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
	        SystemUseServices.setFkProductsType(subUseList.get(use).getFkProductsType());
	        SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
	        SystemUseServices.setFkResponsibleType(subUseList.get(use).getFkResponsibleType());
	        subSystemUseServicesList.add(SystemUseServices);
	      }
      }
	    
	  }else{
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
        systemKeyProducts.setOtherName(subKeyList.get(key).getOtherName());
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
        SystemUseServices.setOtherName(subUseList.get(use).getOtherName());
        subSystemUseServicesList.add(SystemUseServices);
      }
	    systemParamAddList.add(systemParam);
	    this.systemMapper.updateSystemEdit(systemParam);
	  }
	  
	  //删除系统子表
	  this.systemKeyProductsMapper.deleteSubKeyInfo(systemParamAddList);
	  this.systemUseServicesMapper.deleteSubUseInfo(systemParamAddList);
	  //修改
//    this.systemMapper.updateSystemInfo(systemParamAddList);
    this.systemKeyProductsMapper
        .insertSystemKeyProductsBySystemKeyProductsId(subSystemKeyProductsList);
    this.systemUseServicesMapper
        .insertSystemUseServicesBySystemUseServicesId(subSystemUseServicesList);
	  

    
    
    
    
    
    
    
    
//	  //系统是否有子系统
//	  if (subSystemList != null) {
//	    //修改子系统数据
//      for (SystemResult subSys : subSystemList) {
//        subSys.getSystemId();
//        subSys.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
//        subSys.setAppIsInternet(systemParam.getAppIsInternet());
//        subSys.setGradeRecordSysName(systemParam.getGradeRecordSysName());
//        subSys.setSysBusSituationType(systemParam.getSysBusSituationType());
//        subSys.setSysBusDescription(systemParam.getSysBusDescription());
//        subSys.setSysServiceSitScope(systemParam.getSysServiceSitScope());
//        subSys.setSysServiceSitObject(systemParam.getSysServiceSitObject());
//        subSys.setNpCoverageRange(systemParam.getNpCoverageRange());
//        subSys.setNpNetworkProperties(systemParam.getNpNetworkProperties());
//        subSys.setInterconnectionSit(systemParam.getInterconnectionSit());
//        subSys.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
//        subSys.setExecutiveDireCon(systemParam.getExecutiveDireCon());
//        subSys.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
//        subSys.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
//        subSys.setSubIsSystem(systemParam.getSubIsSystem());
//        subUpdateSystemSubList.add(subSys);
//        this.systemMapper.updateSystemSub(subUpdateSystemSubList);
//        
//        //添加子系统子表
//        for (int key = 0; key < subKeyList.size(); key++) {
//          SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
//          systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
//          systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
//          systemKeyProducts.setFkSystemId(subSys.getSystemId());
//          systemKeyProducts.setDeleteStatus(subKeyList.get(key).getDeleteStatus());
//          systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
//          systemKeyProducts.setCreateTime(new Date());
//          systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
//          systemKeyProducts.setProductsNumber(subKeyList.get(key).getProductsNumber());
//          systemKeyProducts.setFkNationalIsProducts(subKeyList.get(key).getFkNationalIsProducts());
//          systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
//          subSystemKeyProductsList.add(systemKeyProducts);
//        }
//        for (int use = 0; use < subUseList.size(); use++) {
//          SystemUseServices SystemUseServices = new SystemUseServices();
//          SystemUseServices.setSystemUseServicesId(Utils.getUuidFor32());
//          SystemUseServices.setFkSystemId(subSys.getSystemId());
//          SystemUseServices.setCreateTime(new Date());
//          SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
//          SystemUseServices.setFkProductsType(subUseList.get(use).getFkProductsType());
//          SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
//          SystemUseServices.setFkResponsibleType(subUseList.get(use).getFkResponsibleType());
//          subSystemUseServicesList.add(SystemUseServices);
//        }
//      }
//    }
//	  //修改主系统信息
//    if (systemFather != null) {
//      systemFather.setSystemId(systemParam.getSystemId());
//      systemFather.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
//      systemFather.setAppIsInternet(systemParam.getAppIsInternet());
//      systemFather.setGradeRecordSysName(systemParam.getGradeRecordSysName());
//      systemFather.setSysBusSituationType(systemParam.getSysBusSituationType());
//      systemFather.setSysBusDescription(systemParam.getSysBusDescription());
//      systemFather.setSysServiceSitScope(systemParam.getSysServiceSitScope());
//      systemFather.setSysServiceSitObject(systemParam.getSysServiceSitObject());
//      systemFather.setNpCoverageRange(systemParam.getNpCoverageRange());
//      systemFather.setNpNetworkProperties(systemParam.getNpNetworkProperties());
//      systemFather.setInterconnectionSit(systemParam.getInterconnectionSit());
//      systemFather.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
//      systemFather.setExecutiveDireCon(systemParam.getExecutiveDireCon());
//      systemFather.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
//      systemFather.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
//      systemFather.setSubIsSystem(systemParam.getSubIsSystem());
//      systemFather.setSystemName(systemParam.getSystemName());
//      systemFather.setCompanyName(systemParam.getCompanyName());
//      subUpdateSystemList.add(systemFather);
//      this.systemMapper.updateSystem(subUpdateSystemList);
//    }
//    
//    this.systemKeyProductsMapper.deleteKey(systemParam);
//    this.systemUseServicesMapper.deleteUse(systemParam);
//	  //添加系统子表
//	  for (int key = 0; key < subKeyList.size(); key++) {
//	    SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
//	    systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
//	    systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
//      systemKeyProducts.setFkSystemId(systemParam.getSystemId());
//      systemKeyProducts.setDeleteStatus(subKeyList.get(key).getDeleteStatus());
//      systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
//      systemKeyProducts.setCreateTime(new Date());
//      systemKeyProducts.setFkExaminStatus(subKeyList.get(key).getFkExaminStatus());
//      systemKeyProducts.setProductsNumber(subKeyList.get(key).getProductsNumber());
//      systemKeyProducts.setFkNationalIsProducts(subKeyList.get(key).getFkNationalIsProducts());
//      systemKeyProducts.setnUseProbability(subKeyList.get(key).getnUseProbability());
//      subSystemKeyProductsList.add(systemKeyProducts);
//    }
//	  for (int use = 0; use < subUseList.size(); use++) {
//      SystemUseServices SystemUseServices = new SystemUseServices();
//      SystemUseServices.setSystemUseServicesId(Utils.getUuidFor32());
//      SystemUseServices.setFkSystemId(systemParam.getSystemId());
//      SystemUseServices.setCreateTime(new Date());
//      SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
//      SystemUseServices.setFkProductsType(subUseList.get(use).getFkProductsType());
//      SystemUseServices.setServiceIsUse(subUseList.get(use).getServiceIsUse());
//      SystemUseServices.setFkResponsibleType(subUseList.get(use).getFkResponsibleType());
//      subSystemUseServicesList.add(SystemUseServices);
//    }
//	  
//	  //验证系统是否为合并系统
//	 if (systemParam.getFkSystemIsMerge() == 1) {
//	  if (addSubSystem != null) {
//	    for (int subSystem = 0; subSystem < addSubSystem.size(); subSystem++) {
//	      SystemParam systemParamSub = new SystemParam();
//        systemParamSub.setSystemId(Utils.getUuidFor32());
//        systemParamSub.setExamineStatus(1);
//        systemParamSub.setExaminationStatus(1);
//        systemParamSub.setFkSystemType(3);
//        systemParamSub.setRecordStatus(1);
//        systemParamSub.setGradingStatus(1);
//        systemParamSub.setFkChangeMatter(5);
//        systemParamSub.setAppIsInternet(2);
//        systemParamSub.setEvaluationStatus(1);
//        systemParamSub.setCreateTime(new Date());
//        systemParamSub.setFkSystemIsMerge(systemParam.getFkSystemIsMerge());
//        systemParamSub.setWhenInvestmentUse(systemParam.getWhenInvestmentUse());
//        systemParamSub.setFkInfoSysTypeCon(systemParam.getFkInfoSysTypeCon());
//        systemParamSub.setAppIsInternet(systemParam.getAppIsInternet());
//        systemParamSub.setGradeRecordSysName(systemParam.getGradeRecordSysName());
//        systemParamSub.setSysBusSituationType(systemParam.getSysBusSituationType());
//        systemParamSub.setSysBusDescription(systemParam.getSysBusDescription());
//        systemParamSub.setSysServiceSitScope(systemParam.getSysServiceSitScope());
//        systemParamSub.setSubIsSystem(systemParam.getSubIsSystem());
//        systemParamSub.setSysServiceSitObject(systemParam.getSysServiceSitObject());
//        systemParamSub.setNpCoverageRange(systemParam.getNpCoverageRange());
//        systemParamSub.setNpNetworkProperties(systemParam.getNpNetworkProperties());
//        systemParamSub.setFkCompanyCode(systemParam.getFkCompanyCode());
//        systemParamSub.setExecutiveOfficeName(systemParam.getExecutiveOfficeName());
//        systemParamSub.setExecutiveDireCon(systemParam.getExecutiveDireCon());
//        systemParamSub.setExecutiveDireConTel(systemParam.getExecutiveDireConTel());
//        systemParamSub.setSystemName(addSubSystem.get(subSystem).getSystemName());
//        systemParamSub.setStandardizedCode(addSubSystem.get(subSystem).getStandardizedCode());
//        systemParamSub.setFkFatherSystemId(systemParam.getSystemId());
//        systemParamSub.setCompanyName(systemParam.getCompanyName());
//        systemParamAddList.add(systemParamSub);
//      
//        for (int subKey = 0; subKey < subKeyList.size(); subKey++) {
//          SystemKeyProducts systemKeyProductsBean = new SystemKeyProducts();
//          systemKeyProductsBean.setSystemKeyProductsId(Utils.getUuidFor32());
//          systemKeyProductsBean.setFkSystemId(systemParamSub.getSystemId());
//          systemKeyProductsBean.setDeleteStatus(subKeyList.get(subKey).getDeleteStatus());
//          systemKeyProductsBean.setnUseProbability(subKeyList.get(subKey).getnUseProbability());
//          systemKeyProductsBean.setCreateTime(new Date());
//          systemKeyProductsBean.setFkExaminStatus(subKeyList.get(subKey).getFkExaminStatus());
//          systemKeyProductsBean.setProductsNumber(subKeyList.get(subKey).getProductsNumber());
//          systemKeyProductsBean.setFkNationalIsProducts(subKeyList.get(subKey).getFkNationalIsProducts());
//          systemKeyProductsBean.setnUseProbability(subKeyList.get(subKey).getnUseProbability());
//          subSystemKeyProductsList.add(systemKeyProductsBean);
//        }
//        
//
//        for (int subUse = 0; subUse < subUseList.size(); subUse++) {
//          SystemUseServices SystemUseServicesBean = new SystemUseServices();
//          SystemUseServicesBean.setSystemUseServicesId(Utils.getUuidFor32());
//          SystemUseServicesBean.setFkSystemId(systemParamSub.getSystemId());
//          SystemUseServicesBean.setCreateTime(new Date());
//          SystemUseServicesBean.setServiceIsUse(subUseList.get(subUse).getServiceIsUse());
//          SystemUseServicesBean.setFkProductsType(subUseList.get(subUse).getFkProductsType());
//          SystemUseServicesBean.setServiceIsUse(subUseList.get(subUse).getServiceIsUse());
//          SystemUseServicesBean.setFkResponsibleType(subUseList.get(subUse).getFkResponsibleType());
//          subSystemUseServicesList.add(SystemUseServicesBean);
//        }
//      }
//    }
//	  //获取删除子系统List
//	  List<SystemParam> systemDelete = systemParam.getDeleteSystemSub();
//	  if(systemDelete != null){
//	   //查询父系统的数据
//	  List<EvaluationResult> evaluation = this.systemMapper.selectAllByEvaluation(systemParam);
//	  List<SelfexaminationResult> self = this.systemMapper.selectAllBySelf(systemParam);
//	  GradingListResult grading = this.systemMapper.selectAllByGrading(systemParam);
//	  RecordsResult record = this.systemMapper.selectAllByRecord(systemParam);
//	  AttachMaterialsListResult material = this.systemMapper.selectAllByMaterial(systemParam);
//	  CheckResult check = this.systemMapper.selectAllByCheck(systemParam);
//	  List<SystemParam> update = new ArrayList<SystemParam>();
//	  
//	  List<EvaluationParam> evaluationList = new ArrayList<EvaluationParam>();
//	  List<SelfexaminationParam> selfexaminationList = new ArrayList<SelfexaminationParam>();
//	  for (SystemParam systemParamDe : systemDelete) {
//	    
//	    systemParamDe.setFkFatherSystemId("");
//	    systemParamDe.setFkSystemType(1);
//      update.add(systemParamDe);
//      this.systemMapper.updateSubStat(update);
//	    
//    	  for (EvaluationResult evaluationParam : evaluation) {
//    	    EvaluationParam evaluationTempParam = new EvaluationParam();
//    	    evaluationTempParam.setEvaluationId(Utils.getUuidFor32());
//    	    evaluationTempParam.setFkSystemId(systemParamDe.getSystemId());
//    	    evaluationTempParam.setExamName(evaluationParam.getExamName());
//    	    evaluationTempParam.setExamTime(evaluationParam.getExamTime());
//    	    evaluationTempParam.setExamYear(evaluationParam.getExamYear());
//    	    evaluationTempParam.setExamOrg(evaluationParam.getExamOrg());
//    	    evaluationTempParam.setExamReport(evaluationParam.getExamReport());
//    	    evaluationTempParam.setExamReportName(evaluationParam.getExamReportName());
//    	    evaluationTempParam.setFkExamStatus(evaluationParam.getFkExamStatus());
//    	    evaluationTempParam.setFkExamResult(evaluationParam.getFkExamResult());
//    	    evaluationTempParam.setFkRectificationReu(evaluationParam.getFkRectificationReu());
//    	    evaluationTempParam.setRectificationDate(evaluationParam.getRectificationDate());
//    	    evaluationTempParam.setDeleteStatus(evaluationParam.getDeleteStatus());
//    	    evaluationTempParam.setCreateUserName(evaluationParam.getCreateUserName());
//    	    evaluationTempParam.setCreateTime(evaluationParam.getCreateTime());
//    	    evaluationTempParam.setUpdateTime(evaluationParam.getUpdateTime());
//    	    evaluationList.add(evaluationTempParam);
//        }
//    	  for (SelfexaminationResult selfexaminationParam : self) {
//    	    SelfexaminationParam selfexaminationTemParam = new SelfexaminationParam();
//    	    selfexaminationTemParam.setSelfexaminationId(Utils.getUuidFor32());
//    	    selfexaminationTemParam.setFkSystemId(systemParamDe.getSystemId());
//    	    selfexaminationTemParam.setFkInspectionStatus(selfexaminationParam.getFkInspectionStatus());
//    	    selfexaminationTemParam.setFkInspectionReu(selfexaminationParam.getFkInspectionReu());
//    	    selfexaminationTemParam.setFkRectificationReu(selfexaminationParam.getFkRectificationReu());
//    	    selfexaminationTemParam.setInspectionDate(selfexaminationParam.getInspectionDate());
//    	    selfexaminationTemParam.setRectificationDate(selfexaminationParam.getRectificationDate());
//    	    selfexaminationTemParam.setDeleteStatus(selfexaminationParam.getDeleteStatus());
//    	    selfexaminationTemParam.setCreateUserName(selfexaminationParam.getCreateUserName());
//    	    selfexaminationTemParam.setCreateTime(selfexaminationParam.getCreateTime());
//    	    selfexaminationTemParam.setUpdateTime(selfexaminationParam.getUpdateTime());
//    	    selfexaminationTemParam.setRemark(selfexaminationParam.getRemark());
//    	    selfexaminationList.add(selfexaminationTemParam);
//        }
//        GradingParam gradingTemParam = new GradingParam();
//        gradingTemParam.setGradingId(Utils.getUuidFor32());
//        gradingTemParam.setFkSystemId(systemParamDe.getSystemId());
//        gradingTemParam.setFkBizSPRankDegree(grading.getFkBizSPRankDegree());
//        gradingTemParam.setFkBizSPRankLevel(grading.getFkBizSPRankLevel());
//        gradingTemParam.setFkBizSystemDegree(grading.getFkBizSystemDegree());
//        gradingTemParam.setFkBizSystemLevel(grading.getFkBizSystemLevel());
//        gradingTemParam.setFkSpRanklevel(grading.getFkSpRanklevel());
//        gradingTemParam.setExpertView(grading.getExpertView());
//        gradingTemParam.setRankExplainDesc(grading.getRankExplainDesc());
//        gradingTemParam.setRankTime(grading.getRankTime());
//        gradingTemParam.setCompetentIsExisting(grading.getCompetentIsExisting());
//        gradingTemParam.setCompetentName(grading.getCompetentName());
//        gradingTemParam.setCompetentView(grading.getCompetentView());
//        gradingTemParam.setFiller(grading.getFiller());
//        gradingTemParam.setFillDate(grading.getFillDate());
//        gradingTemParam.setDeleteStatus(grading.getDeleteStatus());
//        gradingTemParam.setCreateUserName(grading.getCreateUserName());
//        gradingTemParam.setCreateTime(grading.getCreateTime());
//        gradingTemParam.setUpdateTime(grading.getUpdateTime());
//        gradingTemParam.setRemark(grading.getRemark());
//        
//        RecordsParam recordsParam = new RecordsParam();
//        recordsParam.setRecordsId(Utils.getUuidFor32());
//        recordsParam.setFkSystemId(systemParamDe.getSystemId());
//        recordsParam.setFkrevokematter(record.getFkrevokematter());
//        recordsParam.setRecordCode(record.getRecordCode());
//        recordsParam.setRecordCompany(record.getRecordCompany());
//        recordsParam.setRecordDate(record.getRecordDate());
//        recordsParam.setAcceptCompany(record.getAcceptCompany());
//        recordsParam.setAcceptDate(record.getAcceptDate());
//        recordsParam.setAcceptReason(record.getAcceptReason());
//        recordsParam.setRevokereason(record.getRevokereason());
//        recordsParam.setRevokecontent(record.getRevokecontent());
//        recordsParam.setDeleteStatus(record.getDeleteStatus());
//        recordsParam.setCreateUserName(record.getCreateUserName());
//        recordsParam.setCreateTime(record.getCreateTime());
//        recordsParam.setUpdateTime(record.getUpdateTime());
//        recordsParam.setRemark(record.getRemark());
//        
//        AttachMaterialsParam attachMaterialsParam = new AttachMaterialsParam();
//        attachMaterialsParam.setAttachId(Utils.getUuidFor32());
//        attachMaterialsParam.setFkSystemId(systemParamDe.getSystemId());
//        attachMaterialsParam.setFkSyssonId(material.getFkSyssonId());
//        attachMaterialsParam.setFkAttachType(material.getFkAttachType());
//        attachMaterialsParam.setAttachName(material.getAttachName());
//        attachMaterialsParam.setMongoFileId(material.getMongoFileId());
//        attachMaterialsParam.setAttachPath(material.getAttachPath());
//        attachMaterialsParam.setDeleteStatus(material.getDeleteStatus());
//        attachMaterialsParam.setCreateUserName(material.getCreateUserName());
//        attachMaterialsParam.setCreateTime(material.getCreateTime());
//        attachMaterialsParam.setUpdateTime(material.getUpdateTime());
//        attachMaterialsParam.setRemark(material.getRemark());
//        
//        CheckParam checkParam = new CheckParam();
//        checkParam.setCheckId(Utils.getUuidFor32());
//        checkParam.setFkSystemId(systemParamDe.getSystemId());
//        checkParam.setFkExaminStatus(check.getFkExaminStatus());
//        checkParam.setFkBusinessNode(check.getFkBusinessNode());
//        checkParam.setInstanceName(check.getInstanceName());
//        checkParam.setInitiator(check.getInitiator());
//        checkParam.setPrevExecutor(check.getPrevExecutor());
//        checkParam.setExecuteTime(check.getExecuteTime());
//        checkParam.setDeleteStatus(check.getDeleteStatus());
//        checkParam.setCreateUserName(check.getCreateUserName());
//        checkParam.setCreateTime(check.getCreateTime());
//        checkParam.setUpdateTime(check.getUpdateTime());
//        checkParam.setRemark(check.getRemark());
//        
//
//        
//        this.systemMapper.insertGradingTemp(gradingTemParam);
//        this.systemMapper.insertRecordsTemp(recordsParam);
//        this.systemMapper.insertAttachMaterialsTemp(attachMaterialsParam);
//        this.systemMapper.insertCheckTemp(checkParam);
//    }
//	  
//	  this.systemMapper.insertEvaluationTemp(evaluationList);
//	  this.systemMapper.insertSelfexaminationTemp(selfexaminationList);
//	  
//	  }
//   }
//	  this.systemMapper.insertBatchSystem(systemParamAddList);
//    this.systemKeyProductsMapper
//        .insertSystemKeyProductsBySystemKeyProductsId(subSystemKeyProductsList);
//    this.systemUseServicesMapper
//        .insertSystemUseServicesBySystemUseServicesId(subSystemUseServicesList);
    
    if ("1".equals(systemParam.getChangeType())) {
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(systemParam.getSystemId());
      nodeParam.setOperation("申请变更");
      nodeParam.setOperationResult("已修改");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      NodeResult nodeResult = this.nodeServiceImpl.selectSingleNode(nodeParam);
      if (nodeResult == null) {
        this.nodeServiceImpl.addNodeInfo(nodeParam);
      }else{
        nodeParam.setNodeId(nodeResult.getNodeId());
        this.nodeServiceImpl.editNodeInfo(nodeParam);
      }
    }
	  return systemParam.getSystemId();
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
    
    List<SystemTemplateListResult> systemTemPlate = 
        this.systemMapper.selectSystemTemPlate(systemParam);
    List<SystemUseResult> useTempPlate = 
        this.systemMapper.selectUseTemp(systemParam);
    List<SystemKeyResult> keyTempPlate = 
        this.systemMapper.selectKeyTemp(systemParam);
    JacobExcelTool tool = new JacobExcelTool();
    
    //打开excel文件
    tool.OpenExcel("C://Users//hasee//Desktop//模板文件//信息模板.xlsm",false,false);
    for (int i = 0; i < systemTemPlate.size(); i++) {
        //系统名称
        tool.setValue(tool.getCurrentSheet(), "B"+(5+8*i), "value",systemTemPlate.get(i).getSystemName());
        //系统编码
        tool.setValue(tool.getCurrentSheet(), "C"+(5+8*i), "value",systemTemPlate.get(i).getStandardizedCode());
        //等保备案名称
        tool.setValue(tool.getCurrentSheet(), "D"+(5+8*i), "value",systemTemPlate.get(i).getGradeRecordSysName());
        //所属单位名称 
        tool.setValue(tool.getCurrentSheet(), "E"+(5+8*i), "value",systemTemPlate.get(i).getCompanyName());
        //何时投入使用
        tool.setValue(tool.getCurrentSheet(), "F"+(5+8*i), "value",systemTemPlate.get(i).getWhenInvestmentUse());
        //主管处室名称
        tool.setValue(tool.getCurrentSheet(), "G"+(5+8*i), "value",systemTemPlate.get(i).getExecutiveOfficeName());
        //主管联系人
        tool.setValue(tool.getCurrentSheet(), "H"+(5+8*i), "value",systemTemPlate.get(i).getExecutiveDireCon());
        //联系人电话
        tool.setValue(tool.getCurrentSheet(), "I"+(5+8*i), "value",systemTemPlate.get(i).getExecutiveDireConTel());
        //系统是否为分系统
        tool.setValue(tool.getCurrentSheet(), "J"+(5+8*i), "value",systemTemPlate.get(i).getSubIsSystem());
        //上级系统名称
        tool.setValue(tool.getCurrentSheet(), "K"+(5+8*i), "value",systemTemPlate.get(i).getExecutiveDireConTel());
        //业务类型
//        tool.setValue(tool.getCurrentSheet(), "L8", "value",systemTemPlate.getSysBusSituationType());
        //是否有此业务类型
        if (systemTemPlate.get(i).getSysBusSituationType().equals("生产作业")) {
          tool.setValue(tool.getCurrentSheet(), "M"+(5+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "M"+(5+8*i), "value","否");
        }
        if(systemTemPlate.get(i).getSysBusSituationType().equals("指挥调度")) {
          tool.setValue(tool.getCurrentSheet(), "M"+(6+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "M"+(6+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getSysBusSituationType().equals("管理控制")) {
          tool.setValue(tool.getCurrentSheet(), "M"+(7+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "M"+(7+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getSysBusSituationType().equals("内部办公")) {
          tool.setValue(tool.getCurrentSheet(), "M"+(8+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "M"+(8+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getSysBusSituationType().equals("公众服务")) {
          tool.setValue(tool.getCurrentSheet(), "M"+(9+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "M"+(9+8*i), "value","否");
        }
        if (!systemTemPlate.get(i).getSysBusSituationType().equals("生产作业")
            &&!systemTemPlate.get(i).getSysBusSituationType().equals("指挥调度")
            &&!systemTemPlate.get(i).getSysBusSituationType().equals("管理控制")
            &&!systemTemPlate.get(i).getSysBusSituationType().equals("内部办公")
            &&!systemTemPlate.get(i).getSysBusSituationType().equals("公众服务")) {
          tool.setValue(tool.getCurrentSheet(), "M"+(10+8*i), "value",systemTemPlate.get(i).getSysBusSituationType());
        }
        //业务描述
        tool.setValue(tool.getCurrentSheet(), "N"+(5+8*i), "value",systemTemPlate.get(i).getSysBusDescription());
        //服务范围
        tool.setValue(tool.getCurrentSheet(), "O"+(5+8*i), "value",systemTemPlate.get(i).getSysServiceSitScope());
        //服务范围所跨地区个数
        tool.setValue(tool.getCurrentSheet(), "P"+(5+8*i), "value",systemTemPlate.get(i).getSysServiceSitScope());
        //服务对象
        tool.setValue(tool.getCurrentSheet(), "Q"+(5+8*i), "value",systemTemPlate.get(i).getSysServiceSitObject());
        //其他
        tool.setValue(tool.getCurrentSheet(), "R"+(5+8*i), "value",systemTemPlate.get(i).getSysServiceSitObject());
        //覆盖范围
//        tool.setValue(tool.getCurrentSheet(), "S8", "value",systemTemPlate.getNpCoverageRange());
        //是否有此覆盖范围值
        if (systemTemPlate.get(i).getNpCoverageRange().equals("局域网")) {
          tool.setValue(tool.getCurrentSheet(), "T"+(5+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "T"+(5+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getNpCoverageRange().equals("城域网")) {
          tool.setValue(tool.getCurrentSheet(), "T"+(6+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "T"+(6+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getNpCoverageRange().equals("广域网")) {
          tool.setValue(tool.getCurrentSheet(), "T"+(7+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "T"+(7+8*i), "value","否");
        }
        if (!systemTemPlate.get(i).getNpCoverageRange().equals("局域网")
            &&!systemTemPlate.get(i).getNpCoverageRange().equals("城域网")
            &&!systemTemPlate.get(i).getNpCoverageRange().equals("广域网")
            ) {
          tool.setValue(tool.getCurrentSheet(), "T"+(8+8*i), "value",systemTemPlate.get(i).getNpCoverageRange());
        }
        //网络性质
        tool.setValue(tool.getCurrentSheet(), "U"+(5+8*i), "value",systemTemPlate.get(i).getNpNetworkProperties());
        //其他
        tool.setValue(tool.getCurrentSheet(), "V"+(5+8*i), "value",systemTemPlate.get(i).getNpNetworkProperties());
        //系统互联情况
//        tool.setValue(tool.getCurrentSheet(), "W8", "value",systemTemPlate.getInterconnectionSit());
        //是否有此系统互联情况值
        if (systemTemPlate.get(i).getInterconnectionSit().equals("与其他行业系统连接")) {
          tool.setValue(tool.getCurrentSheet(), "X"+(5+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "X"+(5+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getInterconnectionSit().equals("与本行业其他单位系统连接")) {
          tool.setValue(tool.getCurrentSheet(), "X"+(6+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "X"+(6+8*i), "value","否");
        }
        if (systemTemPlate.get(i).getInterconnectionSit().equals("与本单位其他系统连接")) {
          tool.setValue(tool.getCurrentSheet(), "X"+(7+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "X"+(7+8*i), "value","否");
        }
        if (!systemTemPlate.get(i).getInterconnectionSit().equals("与其他行业系统连接") 
          &&!systemTemPlate.get(i).getInterconnectionSit().equals("与本行业其他单位系统连接")
          &&!systemTemPlate.get(i).getInterconnectionSit().equals("与本单位其他系统连接")){
          tool.setValue(tool.getCurrentSheet(), "X"+(8+8*i), "value",systemTemPlate.get(i).getInterconnectionSit());
        }
    } 
      
    for (int useExcel = 0; useExcel < useTempPlate.size(); useExcel++) {
      //服务类型
//      tool.setValue(tool.getCurrentSheet(), "AC", "value",systemUseResult.getProductsTypeName());
      //是否有此服务类型
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("等级测评")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(5+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(5+8*useExcel), "value","否");
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("风险评估")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(6+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(6+8*useExcel), "value","否");
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("灾难恢复")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(7+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(7+8*useExcel), "value","否");
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("应急响应")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(8+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(8+8*useExcel), "value","否");
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("系统集成")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(9+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(9+8*useExcel), "value","否");
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("安全咨询")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(10+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(10+8*useExcel), "value","否");
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("安全培训")) {
        tool.setValue(tool.getCurrentSheet(), "AD"+(11+8*useExcel), "value","是");
      }else{
        tool.setValue(tool.getCurrentSheet(), "AD"+(11+8*useExcel), "value","否");
      }
      //服务责任方类型
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("等级测评")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(5+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("风险评估")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(6+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("灾难恢复")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(7+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("应急响应")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(8+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("系统集成")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(9+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("安全咨询")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(10+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
      if (useTempPlate.get(useExcel).getProductsTypeName().equals("安全培训")) {
        tool.setValue(tool.getCurrentSheet(), "AE"+(11+8*useExcel), "value",useTempPlate.get(useExcel).getResponsibleTypeName());
      }
    }

      
      for (int keyExcel = 0; keyExcel < keyTempPlate.size(); keyExcel++) {
      //数量
        if(keyTempPlate.get(keyExcel).getExaminStatusName().trim().equals("安全专用产品")){
          tool.setValue(tool.getCurrentSheet(), "Z"+(5+8*keyExcel), "value",keyTempPlate.get(keyExcel).getProductsNumber());
        }
        if(keyTempPlate.get(keyExcel).getExaminStatusName().equals("网络产品")){
          tool.setValue(tool.getCurrentSheet(), "Z"+(6+8*keyExcel), "value",keyTempPlate.get(keyExcel).getProductsNumber());
        }
        if(keyTempPlate.get(keyExcel).getExaminStatusName().equals("操作系统")){
          tool.setValue(tool.getCurrentSheet(), "Z"+(7+8*keyExcel), "value",keyTempPlate.get(keyExcel).getProductsNumber());
        }
        if(keyTempPlate.get(keyExcel).getExaminStatusName().equals("数据库")){
          tool.setValue(tool.getCurrentSheet(), "Z"+(8+8*keyExcel), "value",keyTempPlate.get(keyExcel).getProductsNumber());
        }
        if(keyTempPlate.get(keyExcel).getExaminStatusName().equals("服务器")){
          tool.setValue(tool.getCurrentSheet(), "Z"+(9+8*keyExcel), "value",keyTempPlate.get(keyExcel).getProductsNumber());
        }
        //使用情况
        if (keyTempPlate.get(keyExcel).getExaminStatusName().trim().equals("安全专用产品")) {
          tool.setValue(tool.getCurrentSheet(), "AA"+(5+8*keyExcel), "value",keyTempPlate.get(keyExcel).getNationalIsProductsName());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("网络产品")) {
          tool.setValue(tool.getCurrentSheet(), "AA"+(6+8*keyExcel), "value",keyTempPlate.get(keyExcel).getNationalIsProductsName());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("操作系统")) {
          tool.setValue(tool.getCurrentSheet(), "AA"+(7+8*keyExcel), "value",keyTempPlate.get(keyExcel).getNationalIsProductsName());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("数据库")) {
          tool.setValue(tool.getCurrentSheet(), "AA"+(8+8*keyExcel), "value",keyTempPlate.get(keyExcel).getNationalIsProductsName());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("服务器")) {
          tool.setValue(tool.getCurrentSheet(), "AA"+(9+8*keyExcel), "value",keyTempPlate.get(keyExcel).getNationalIsProductsName());
        }
        //使用国产率
        if (keyTempPlate.get(keyExcel).getExaminStatusName().trim().equals("安全专用产品")) {
          tool.setValue(tool.getCurrentSheet(), "AB"+(5+8*keyExcel), "value",keyTempPlate.get(keyExcel).getnUseProbability());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("网络产品")) {
          tool.setValue(tool.getCurrentSheet(), "AB"+(6+8*keyExcel), "value",keyTempPlate.get(keyExcel).getnUseProbability());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("操作系统")) {
          tool.setValue(tool.getCurrentSheet(), "AB"+(7+8*keyExcel), "value",keyTempPlate.get(keyExcel).getnUseProbability());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("数据库")) {
          tool.setValue(tool.getCurrentSheet(), "AB"+(8+8*keyExcel), "value",keyTempPlate.get(keyExcel).getnUseProbability());
        }
        if (keyTempPlate.get(keyExcel).getExaminStatusName().equals("服务器")) {
          tool.setValue(tool.getCurrentSheet(), "AB"+(9+8*keyExcel), "value",keyTempPlate.get(keyExcel).getnUseProbability());
        }
      }
        

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
  public SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException {
    
    SystemResult systemResult= this.systemMapper.selectEditSystem(systemParam);
    if(systemResult.getFkSystemIsMerge()==1){
      List<SystemSubResult> systemParamList = this.systemMapper.selectEditBySub(systemParam);
      if (systemParamList != null ){
        systemResult.setAddSystemSub(systemParamList);
      }
    }
    
    List<SystemKeyResult> keyParamList = this.systemMapper.selectKeyTemp(systemParam);
    List<SystemUseResult> useParamList = this.systemMapper.selectUseTemp(systemParam);
    
    if (keyParamList != null) {
      systemResult.setSystemKeyProducts(keyParamList);
    }
    
    if(useParamList != null){
      systemResult.setSystemUseServices(useParamList);
    }
    return systemResult;
  }

  @Override
  public SystemGradingChangeResult queryGradingEditAudit(SystemParam systemParam) {
    return this.systemMapper.selectgradingEditAudit(systemParam);
  }

  /**
   * 通过系统Id获取系统信息
   */
  @Override
  public SystemResult querySystemInformationBySystemId(SystemParam systemParam) {
    return this.systemMapper.selectSystem(systemParam);
  }
}

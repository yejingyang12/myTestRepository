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
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.codeapi.api.SystemApiClient;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.entity.SystemInfoList;
import com.sinopec.smcc.cpro.codeapi.entity.SystemInfoList.SystemInfo;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.main.constant.MainConstant;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.system.constant.SystemConstant;
import com.sinopec.smcc.cpro.system.entity.SystemAllInfoResult;
import com.sinopec.smcc.cpro.system.entity.SystemEchoParam;
import com.sinopec.smcc.cpro.system.entity.SystemEchoResult;
import com.sinopec.smcc.cpro.system.entity.SystemGradingChangeResult;
import com.sinopec.smcc.cpro.system.entity.SystemKeyProducts;
import com.sinopec.smcc.cpro.system.entity.SystemKeyResult;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemSubResult;
import com.sinopec.smcc.cpro.system.entity.SystemTemplateListResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseServices;
import com.sinopec.smcc.cpro.system.mapper.SystemKeyProductsMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemRelationMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemUseServicesMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.system.util.SystemInfoUtil;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.JacobExcelTool;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.cpro.tools.excel.ExcelCopy;
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
	@Autowired
  private SystemApiClient systemApiClient;
  @Autowired
  private SystemRelationMapper systemRelationMapper;
	
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
			orderBy.append(SystemInfoUtil.sortField(systemParam.getField()));
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
    List<SystemRelationParam> systemRelationList = new ArrayList<SystemRelationParam>();
    //组装合并父系统
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
    
    //不是合并系统
    if(systemParam.getFkSystemIsMerge() == 2){
      //组装不是合并系统的系统关联表信息
      SystemRelationParam systemRelationParam = new SystemRelationParam();
      systemRelationParam.setSystemRelationId(Utils.getUuidFor32());
      systemRelationParam.setFkSystemId(systemParam.getSystemId());
      systemRelationParam.setFkCompanyCode(systemParam.getFkCompanyCode());
      systemRelationParam.setSystemIsMerge("0");
      systemRelationParam.setSystemName(systemParam.getSystemName());
      systemRelationParam.setSystemSmccCode(SystemInfoUtil.getSmccId("0"));
      systemRelationParam.setStandardizedName(systemParam.getSystemName());
      systemRelationParam.setStandardizedCode(systemParam.getStandardizedCode());
      systemRelationParam.setCreateTime(new Date());
      systemRelationParam.setCreateUserName(userName);
      systemRelationList.add(systemRelationParam);
    }
    
    //组装合并父系统的关键产品信息列表
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
    //组装合并父系统的使用服务信息列表
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
    
    //是合并系统
    if (systemParam.getFkSystemIsMerge() == 1) {
      if(systemCode != null){
        //循环组装合并子系统信息、系统关联表信息
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
          systemParamSub.setFatherSystemName(systemParam.getSystemName());
          systemParamSub.setStandardizedCode(systemCode.get(subSystem).getStandardizedCode());
          systemParamSub.setFkFatherSystemId(systemParam.getSystemId());
          systemParamSub.setCompanyName(systemParam.getCompanyName());
          systemParamSub.setFatherCompanyName(systemParam.getCompanyName());
          subSystemList.add(systemParamSub);
          
          //组装合并父系统的系统关联表信息
          SystemRelationParam systemRelationParam = new SystemRelationParam();
          systemRelationParam.setSystemRelationId(Utils.getUuidFor32());
          systemRelationParam.setFkSystemId(systemParam.getSystemId());
          systemRelationParam.setFkCompanyCode(systemParam.getFkCompanyCode());
          systemRelationParam.setSystemIsMerge("1");
          systemRelationParam.setSystemName(systemParam.getSystemName());
          systemRelationParam.setSystemSmccCode(SystemInfoUtil.getSmccId("1"));
          systemRelationParam.setStandardizedName(systemParamSub.getSystemName());
          systemRelationParam.setStandardizedCode(systemParamSub.getStandardizedCode());
          systemRelationParam.setCreateTime(new Date());
          systemRelationParam.setCreateUserName(userName);
          systemRelationList.add(systemRelationParam);
          
          //组装合并子系统的关键产品信息
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
          //组装合并子系统的服务使用信息
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
    
    /*//添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(systemParam.getSystemId());
    nodeParam.setOperation("创建");
    nodeParam.setOperationResult("已创建");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);*/
    //批量添加关键产品信息
    this.systemKeyProductsMapper.insertSystemKeyProductsBySystemKeyProductsId(
        systemKeyProductsList);
    //批量添加使用服务信息
    this.systemUseServicesMapper.insertSystemUseServicesBySystemUseServicesId(
        systemUseServicesList);
    //批量添加系统信息（包括合并父系统、合并子系统）
    this.systemMapper.insertBatchSystem(subSystemList);
    //批量添加系统关联表信息
    this.systemRelationMapper.insertBatchSystemPelation(systemRelationList);
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
	  //添加新的关联表信息的list
	  List<SystemRelationParam> systemRelationParamList = new ArrayList<SystemRelationParam>();
	  
	  //判断是否为合并系统
	  if(systemParam.getFkSystemIsMerge()==1){
	    //查询子表信息
	    List<SystemSubResult> subSystemList = this.systemMapper.selectEditBySub(systemParam);
	    int sum = 0;//计数器
	    //修改原子系统的信息
	    for (SystemSubResult systemSubParam : subSystemList) {
	      boolean sonBoo = false;
	      SystemParam systemTempParam = new SystemParam();
	      //修改系统关联表信息
	      systemTempParam.addSystemParam(systemParam);
	      systemTempParam.setSystemId(systemSubParam.getSystemId());
	      systemParamAddList.add(systemTempParam);
	      for(SystemParam systemParamSon : systemParam.getAddSystemSub()){
	        //如果已有子系统与新提交的子系统Code相同，则sonBoo为true，不进行删除
	        if(systemParamSon.getStandardizedCode().equals(systemSubParam.getStandardizedCode())){
	          sonBoo = true;
	          break;
	        }
	      }
	      if(!sonBoo){
	        systemTempParam.setDeleteStatus(2);
	      }
	      this.systemMapper.updateSystemEdit(systemTempParam);
      }
      
      //准备添加新子系统信息
      for (SystemSubResult systemSubParam : subSystemList) {
        SystemParam systemTempParam = new SystemParam();
        systemTempParam.addSystemParam(systemParam);
        systemTempParam.setSystemId(systemSubParam.getSystemId());
        //如果是新添加子系统
        if(sum == 0){
          if(systemParam.getAddSystemSub().size() > subSystemList.size()){
            for (int i = systemParam.getAddSystemSub().size(); i >= 0; i--) {
              if(i < subSystemList.size()){
                systemParam.getAddSystemSub().remove(i);
              }
            }
            this.saveSonSystem(systemParam);
          }
          sum = 1;
        }
        this.systemMapper.updateSystemEdit(systemTempParam);
      }
	    
	    //获取系统所有已有的未删除的关联表信息
	    SystemRelationParam querySystemRelationListParam = new SystemRelationParam();
	    querySystemRelationListParam.setFkSystemId(systemParam.getSystemId());
	    List<SystemRelationResult> systemRelationResultList = this.systemRelationMapper.
	        selectSystemRelationListBySystemId(querySystemRelationListParam);
	    //循环原关联表信息，将从前台传过来没有的信息拆出来
	    for (SystemRelationResult systemRelationTempResult : systemRelationResultList) {
	      boolean deleteSon = true;
	      for(SystemParam systemParamSon : systemParam.getAddSystemSub()){
          //如果已有子系统与新提交的子系统Code相同，则sonBoo为true，不进行删除
          if(systemParamSon.getStandardizedCode().equals(systemRelationTempResult.getStandardizedCode())){
            deleteSon = false;
            break;
          }
        }
	      if (deleteSon) {
	        //删除的子系统的对应关联信息拆分出来
	        SystemRelationParam systemRelationTempParam = new SystemRelationParam();
	        //条件
	        SystemRelationParam systemRelationBean = new SystemRelationParam();
	        systemRelationBean.setFkSystemId(systemRelationTempResult.getSystemId());
	        systemRelationBean.setStandardizedName(
	            systemRelationTempResult.getStandardizedName());
	        systemRelationTempParam.setSystemRelationParam(systemRelationBean);
	        //修改内容
	        /*for (SystemSubResult systemSubParam : subSystemList) {
	          //找到拆分出来的系统的fkSystemId
	          if(systemRelationTempResult.getStandardizedCode().equals(
	              systemSubParam.getStandardizedCode())){
	            systemRelationTempParam.setFkSystemId(systemSubParam.getSystemId());
	            break;
	          }
	        }*/
	        systemRelationTempParam.setSystemIsMerge("0");
	        systemRelationTempParam.setSystemName(systemRelationTempResult.getStandardizedName());
	        //systemRelationTempParam.setSystemSmccCode(SystemInfoUtil.getSmccId("0"));
	        this.systemRelationMapper.updateSystemRelationBySystemIdAndStandardizedName(
	            systemRelationTempParam);
	      }
      }
	    //循环子系统信息，将未添加到关联表的信息添加上
	    for (int i = 0; i < systemParam.getAddSystemSub().size(); i++) {
	      SystemParam systemNewSonParam = systemParam.getAddSystemSub().get(i);
	      boolean newSon = true;
	      for (SystemRelationResult systemRelationTempResult : systemRelationResultList) {
	        if(systemNewSonParam.getStandardizedCode().equals(
	            systemRelationTempResult.getStandardizedCode())){
	          newSon = false;
            break;
          }
        }
	      //如果是新子系统
	      if (newSon) {
	        SystemRelationParam systemRelationParam = new SystemRelationParam();
          systemRelationParam.setSystemRelationId(Utils.getUuidFor32());
          systemRelationParam.setFkSystemId(systemParam.getSystemId());
          systemRelationParam.setFkCompanyCode(systemParam.getFkCompanyCode());
          systemRelationParam.setSystemIsMerge("1");
          systemRelationParam.setSystemName(systemParam.getSystemName());
          systemRelationParam.setSystemSmccCode(SystemInfoUtil.getSmccId("1"));
          systemRelationParam.setStandardizedName(systemNewSonParam.getSystemName());
          systemRelationParam.setStandardizedCode(systemNewSonParam.getStandardizedCode());
          systemRelationParam.setCreateTime(new Date());
          systemRelationParam.setCreateUserName(userName);
          systemRelationParamList.add(systemRelationParam);
        }
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
	  //如果有新子系统，添加新子系统相关信息
    if (systemRelationParamList.size() > 0) {
      this.systemRelationMapper.insertBatchSystemPelation(systemRelationParamList);
    }
    
    
    
    
    
    
    
    
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
  public AttachResult exportExcelForSystemTemplate(SystemParam systemParam) throws BusinessException {
    //没系统id时抛出异常
    if(systemParam.getSystemIds() == null || systemParam.getSystemIds().length < 1){
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    AttachResult attachResult = null;
    List<SystemAllInfoResult> systemAllInfoResultList = this.systemMapper.
        selectSystemAllInfoBySystemParam(systemParam);
    
    
    String tempPath = MainConstant.TEMPORARY_EXCEL_FILE_PATH;//模板文件路径
    //String tempName = MainConstant.SYSTEM_TEMP_NAME;//系统模板导出名
    String tempName = "systemExportTempCopy.xls";//系统模板导出名
    JacobExcelTool tool = new JacobExcelTool();
    try {
      
      //获取模板文件
      File tempFile = new File(tempPath+tempName);
      String tempAbsolutePath = tempFile.getAbsolutePath();
      //复制模板文件（导出文件）路径
      //String temporaryCopyPath = MainConstant.TEMPORARY_COPY_EXCEL_FILE_PATH;
      String temporaryCopyPath = MainConstant.TEMPORARY_FILE_PATH;
      
      //每次新生成一个Excel，并将模板内容复制给新生成的文件
      String exportUrl = "systemExportTemp"+"_"+DateUtils.getMilliseconds()+".xls";
      String exportName = "系统导出模板"+".xls";
      String newFile = temporaryCopyPath + exportUrl;
      File exportFile = new File(newFile);
      String exportAbsolutePath = exportFile.getAbsolutePath();
      /*FileInputStream ins = new FileInputStream(tempFile);
      FileOutputStream out = new FileOutputStream(exportFile);
      byte[] b = new byte[1024];
      int count=0;
      while((count=ins.read(b))!=-1){
        out.write(b, 0, count);
      }
      ins.close();
      out.close();*/
      
      //复制系统格式
      InputStream is = new FileInputStream(tempAbsolutePath);
      OutputStream os = new FileOutputStream(new File(exportAbsolutePath));
      HSSFWorkbook wb = new HSSFWorkbook(is);
      HSSFSheet sheet = wb.getSheetAt(0);
      for (int i = 1; i < systemAllInfoResultList.size(); i++) {
        ExcelCopy.copySelfRows(5, 12, 4+8*i, sheet);
      }
      wb.write(os);
      wb.close();
      os.close();
      is.close();
      
      //打开excel文件
      tool.OpenExcel(exportAbsolutePath,false,false);
      for (int i = 0; i < systemAllInfoResultList.size(); i++) {
        //获取当前系统信息
        SystemAllInfoResult systemAllInfoResult = systemAllInfoResultList.get(i);
        //获取关键产品
        List<SystemKeyProducts> systemKeyProductsList = systemAllInfoResult.getSystemKeyProductsList();
        //获取采用服务
        List<SystemUseServices> systemUseServicesList = systemAllInfoResult.getSystemUseServicesList();
        //获取子系统
        //List<SystemSubResult> systemSubResultList = systemAllInfoResult.getSystemSubResultList();
        
        //准备塞值
        //序号
        tool.setValue(tool.getCurrentSheet(), "A"+(5+8*i), "value",i+1);
        //系统名称
        String strSystemName = systemAllInfoResult.getSystemName()==null
            ?"":systemAllInfoResult.getSystemName();
        tool.setValue(tool.getCurrentSheet(), "B"+(5+8*i), "value",strSystemName);
        //系统标准化代码
        String strStandardizedCode = systemAllInfoResult.getStandardizedCode()==null
            ?"":systemAllInfoResult.getStandardizedCode();
        tool.setValue(tool.getCurrentSheet(), "C"+(5+8*i), "value",strStandardizedCode);
        //等保备案名称
        String strGradeRecordSysName = systemAllInfoResult.getGradeRecordSysName()==null
            ?"":systemAllInfoResult.getGradeRecordSysName();
        tool.setValue(tool.getCurrentSheet(), "D"+(5+8*i), "value",strGradeRecordSysName);
        //所属单位名称 
        String strCompanyName = systemAllInfoResult.getCompanyName()==null
            ?"":systemAllInfoResult.getCompanyName();
        tool.setValue(tool.getCurrentSheet(), "E"+(5+8*i), "value",strCompanyName);
        //何时投入使用
        if(systemAllInfoResult.getWhenInvestmentUse() != null){
          String strWhenInvestmentUse = DateUtils.getDate("yyyy-MM-dd",systemAllInfoResult.getWhenInvestmentUse());
          tool.setValue(tool.getCurrentSheet(), "F"+(5+8*i), "value",strWhenInvestmentUse);
        }
        //主管处室名称
        String strExecutiveOfficeName = systemAllInfoResult.getExecutiveOfficeName()==null
            ?"":systemAllInfoResult.getExecutiveOfficeName();
        tool.setValue(tool.getCurrentSheet(), "G"+(5+8*i), "value",strExecutiveOfficeName);
        //主管联系人
        String strExecutiveDireCon = systemAllInfoResult.getExecutiveDireCon()==null
            ?"":systemAllInfoResult.getExecutiveDireCon();
        tool.setValue(tool.getCurrentSheet(), "H"+(5+8*i), "value",strExecutiveDireCon);
        //联系人电话
        String strExecutiveDireConTel = systemAllInfoResult.getExecutiveDireConTel()==null
            ?"":systemAllInfoResult.getExecutiveDireConTel();
        tool.setValue(tool.getCurrentSheet(), "I"+(5+8*i), "value",strExecutiveDireConTel);
        //系统是否为分系统
        String strSubIsSystem = "";
        if(systemAllInfoResult.getSubIsSystem() == 1){
          strSubIsSystem = "是";
        }else if(systemAllInfoResult.getSubIsSystem() == 2){
          strSubIsSystem = "否";
        }
        tool.setValue(tool.getCurrentSheet(), "J"+(5+8*i), "value",strSubIsSystem);
        //上级系统名称
        String strFatherSystemName = systemAllInfoResult.getFatherSystemName()==null
            ?"":systemAllInfoResult.getFatherSystemName();
        tool.setValue(tool.getCurrentSheet(), "K"+(5+8*i), "value",strFatherSystemName);
        
        //系统承载业务情况—业务类型
        String strSysBusSituationType = systemAllInfoResult.getSysBusSituationType()==null
            ?"":systemAllInfoResult.getSysBusSituationType();
        tool.setValue(tool.getCurrentSheet(), "M"+(5+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "M"+(6+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "M"+(7+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "M"+(8+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "M"+(9+8*i), "value","否");
        if("生产作业".equals(strSysBusSituationType)){
          tool.setValue(tool.getCurrentSheet(), "M"+(5+8*i), "value","是");
        }else if("指挥调度".equals(strSysBusSituationType)){
          tool.setValue(tool.getCurrentSheet(), "M"+(6+8*i), "value","是");
        }else if("管理控制".equals(strSysBusSituationType)){
          tool.setValue(tool.getCurrentSheet(), "M"+(7+8*i), "value","是");
        }else if("内部办公".equals(strSysBusSituationType)){
          tool.setValue(tool.getCurrentSheet(), "M"+(8+8*i), "value","是");
        }else if("公众服务".equals(strSysBusSituationType)){
          tool.setValue(tool.getCurrentSheet(), "M"+(9+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "M"+(10+8*i), "value",strSysBusSituationType);
        }
        //系统承受业务情况—业务描述
        String strSysBusDescription = systemAllInfoResult.getSysBusDescription()==null
            ?"":systemAllInfoResult.getSysBusDescription();
        tool.setValue(tool.getCurrentSheet(), "N"+(5+8*i), "value",strSysBusDescription);
        
        //系统服务情况—服务范围
        String strSysServiceSitScope = systemAllInfoResult.getSysServiceSitScope()==null
            ?"":systemAllInfoResult.getSysServiceSitScope();
        String[] strSysServiceSitScopes = strSysServiceSitScope.split("^");
        if((strSysServiceSitScopes.length != 2 
              && !"全国".equals(strSysServiceSitScopes[0])
              && !"全省（区、市）".equals(strSysServiceSitScopes[0])
              && !"地（区、市）".equals(strSysServiceSitScopes[0]))
            ||(strSysServiceSitScopes.length == 2 
              && !StringUtils.isNumeric(strSysServiceSitScopes[1]))){
          //其他
          tool.setValue(tool.getCurrentSheet(), "O"+(5+8*i), "value","其他");
          tool.setValue(tool.getCurrentSheet(), "P"+(5+8*i), "value",strSysServiceSitScope);
        }else{
          if(strSysServiceSitScopes.length == 2){
            tool.setValue(tool.getCurrentSheet(), "O"+(5+8*i), "value",strSysServiceSitScopes[0]);
            tool.setValue(tool.getCurrentSheet(), "P"+(5+8*i), "value",strSysServiceSitScopes[1]);
          }else{
            tool.setValue(tool.getCurrentSheet(), "O"+(5+8*i), "value",strSysServiceSitScope);
          }
        }
        
        //系统服务情况—服务对象
        String strSysServiceSitObject = systemAllInfoResult.getSysServiceSitObject()==null
            ?"":systemAllInfoResult.getSysServiceSitObject();
        if("单位内部人员".equals(strSysServiceSitObject) 
            || "社会公众人员".equals(strSysServiceSitObject) 
            || "两者均包括".equals(strSysServiceSitObject)){
          tool.setValue(tool.getCurrentSheet(), "Q"+(5+8*i), "value",strSysServiceSitObject);
        }else{
          tool.setValue(tool.getCurrentSheet(), "R"+(5+8*i), "value",strSysServiceSitObject);
        }
        
        //系统网络平台--覆盖范围
        String strNpCoverageRange = systemAllInfoResult.getNpCoverageRange()==null
            ?"":systemAllInfoResult.getNpCoverageRange();
        tool.setValue(tool.getCurrentSheet(), "T"+(5+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "T"+(6+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "T"+(7+8*i), "value","否");
        if("局域网".equals(strNpCoverageRange)){
          tool.setValue(tool.getCurrentSheet(), "T"+(5+8*i), "value","是");
        }else if("地域网".equals(strNpCoverageRange)){
          tool.setValue(tool.getCurrentSheet(), "T"+(6+8*i), "value","是");
        }else if("广域网".equals(strNpCoverageRange)){
          tool.setValue(tool.getCurrentSheet(), "T"+(7+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "T"+(8+8*i), "value",strNpCoverageRange);
        }
        //系统网络平台--网络性质
        String strNpNetworkProperties = systemAllInfoResult.getNpNetworkProperties()==null
            ?"":systemAllInfoResult.getNpNetworkProperties();
        if("业务专网".equals(strNpNetworkProperties) || "互联网".equals(strNpNetworkProperties)){
          tool.setValue(tool.getCurrentSheet(), "U"+(5+8*i), "value",strNpNetworkProperties);
        }else{
          tool.setValue(tool.getCurrentSheet(), "V"+(5+8*i), "value",strNpNetworkProperties);
        }
        
        //系统互联情况
        String strInterconnectionSit = systemAllInfoResult.getInterconnectionSit()==null
            ?"":systemAllInfoResult.getInterconnectionSit();
        tool.setValue(tool.getCurrentSheet(), "X"+(5+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "X"+(6+8*i), "value","否");
        tool.setValue(tool.getCurrentSheet(), "X"+(7+8*i), "value","否");
        if("与其他行业系统连接".equals(strInterconnectionSit)){
          tool.setValue(tool.getCurrentSheet(), "X"+(5+8*i), "value","是");
        }else if("与本行业其他单位系统连接".equals(strInterconnectionSit)){
          tool.setValue(tool.getCurrentSheet(), "X"+(6+8*i), "value","是");
        }else if("与本单位其他系统连接".equals(strInterconnectionSit)){
          tool.setValue(tool.getCurrentSheet(), "X"+(7+8*i), "value","是");
        }else{
          tool.setValue(tool.getCurrentSheet(), "X"+(8+8*i), "value",strInterconnectionSit);
        }
        
        
        //关键产品使用
        for (SystemKeyProducts systemKeyProductsTemp : systemKeyProductsList) {
          //产品类型
          Integer examinStatus = systemKeyProductsTemp.getFkExaminStatus()==null
              ?0:systemKeyProductsTemp.getFkExaminStatus();
          //数量
          String productsNumber = systemKeyProductsTemp.getProductsNumber()==null
              ?"":systemKeyProductsTemp.getProductsNumber();
          //是否使用国产品
          Integer fkNationalIsProducts = systemKeyProductsTemp.getFkNationalIsProducts();
          String strFkNationalIsProducts = "";
          if(fkNationalIsProducts == 1){
            strFkNationalIsProducts = "全部使用";
          }else if(fkNationalIsProducts == 2){
            strFkNationalIsProducts = "全部未使用";
          }else if(fkNationalIsProducts == 3){
            strFkNationalIsProducts = "部分使用";
          }
          //国产品使用率
          Integer nUseProbability = (systemKeyProductsTemp.getnUseProbability()==null
              ?0:systemKeyProductsTemp.getnUseProbability())/100;
          //其他名称
          String otherName = systemKeyProductsTemp.getOtherName()==null
              ?"":systemKeyProductsTemp.getOtherName();
          if(examinStatus != null){
            switch (systemKeyProductsTemp.getFkExaminStatus()) {
            case 1:
              tool.setValue(tool.getCurrentSheet(), "Z"+(5+8*i), "value",productsNumber);
              tool.setValue(tool.getCurrentSheet(), "AA"+(5+8*i), "value",strFkNationalIsProducts);
              tool.setValue(tool.getCurrentSheet(), "AB"+(5+8*i), "value",nUseProbability);
              break;
            case 2:
              tool.setValue(tool.getCurrentSheet(), "Z"+(6+8*i), "value",productsNumber);
              tool.setValue(tool.getCurrentSheet(), "AA"+(6+8*i), "value",strFkNationalIsProducts);
              tool.setValue(tool.getCurrentSheet(), "AB"+(6+8*i), "value",nUseProbability);
              break;
            case 3:
              tool.setValue(tool.getCurrentSheet(), "Z"+(7+8*i), "value",productsNumber);
              tool.setValue(tool.getCurrentSheet(), "AA"+(7+8*i), "value",strFkNationalIsProducts);
              tool.setValue(tool.getCurrentSheet(), "AB"+(7+8*i), "value",nUseProbability);
              break;
            case 4:
              tool.setValue(tool.getCurrentSheet(), "Z"+(8+8*i), "value",productsNumber);
              tool.setValue(tool.getCurrentSheet(), "AA"+(8+8*i), "value",strFkNationalIsProducts);
              tool.setValue(tool.getCurrentSheet(), "AB"+(8+8*i), "value",nUseProbability);
              break;
            case 5:
              tool.setValue(tool.getCurrentSheet(), "Z"+(9+8*i), "value",productsNumber);
              tool.setValue(tool.getCurrentSheet(), "AA"+(9+8*i), "value",strFkNationalIsProducts);
              tool.setValue(tool.getCurrentSheet(), "AB"+(9+8*i), "value",nUseProbability);
              break;
            case 6:
              tool.setValue(tool.getCurrentSheet(), "Y"+(10+8*i), "value",otherName);
              tool.setValue(tool.getCurrentSheet(), "Z"+(10+8*i), "value",productsNumber);
              tool.setValue(tool.getCurrentSheet(), "AA"+(10+8*i), "value",strFkNationalIsProducts);
              tool.setValue(tool.getCurrentSheet(), "AB"+(10+8*i), "value",nUseProbability);
              break;
            default:
              break;
            }
          }
        }
        
        
        //系统采用服务情况
        for (SystemUseServices systemUseServicesTemp : systemUseServicesList) {
          //服务类型
          Integer fkProductsType = systemUseServicesTemp.getFkProductsType()==null
              ?0:systemUseServicesTemp.getFkProductsType();
          //是否采用
          Integer serviceIsUse = systemUseServicesTemp.getServiceIsUse();
          String strServiceIsUse = "";
          if(1 == serviceIsUse){
            strServiceIsUse = "是";
          }else if(2 == serviceIsUse){
            strServiceIsUse = "否";
          }
          //服务责任方类型
          String fkResponsibleType = systemUseServicesTemp.getFkResponsibleType();
          String strFkResponsibleType = "";
          if("1".equals(fkResponsibleType)){
            strFkResponsibleType = "本行业（单位）";
          }else if("2".equals(fkResponsibleType)){
            strFkResponsibleType = "国内其他服务商";
          }else if("3".equals(fkResponsibleType)){
            strFkResponsibleType = "国外服务商";
          }
          //其他名称
          String otherName = systemUseServicesTemp.getOtherName()==null
              ?"":systemUseServicesTemp.getOtherName();
          switch (fkProductsType) {
          case 1:
            tool.setValue(tool.getCurrentSheet(), "AD"+(5+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(5+8*i), "value",strFkResponsibleType);
            break;
          case 2:
            tool.setValue(tool.getCurrentSheet(), "AD"+(6+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(6+8*i), "value",strFkResponsibleType);
            break;
          case 3:
            tool.setValue(tool.getCurrentSheet(), "AD"+(7+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(7+8*i), "value",strFkResponsibleType);
            break;
          case 4:
            tool.setValue(tool.getCurrentSheet(), "AD"+(8+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(8+8*i), "value",strFkResponsibleType);
            break;
          case 5:
            tool.setValue(tool.getCurrentSheet(), "AD"+(9+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(9+8*i), "value",strFkResponsibleType);
            break;
          case 6:
            tool.setValue(tool.getCurrentSheet(), "AD"+(10+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(10+8*i), "value",strFkResponsibleType);
            break;
          case 7:
            tool.setValue(tool.getCurrentSheet(), "AD"+(11+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(11+8*i), "value",strFkResponsibleType);
            break;
          case 8:
            tool.setValue(tool.getCurrentSheet(), "AC"+(12+8*i), "value",otherName);
            tool.setValue(tool.getCurrentSheet(), "AD"+(12+8*i), "value",strServiceIsUse);
            tool.setValue(tool.getCurrentSheet(), "AE"+(12+8*i), "value",strFkResponsibleType);
            break;
          default:
            break;
          }
        }//系统采用服务情况end
        
        //单条塞值完成,如果还有系统没塞值，复制一份系统样式（8行：第5-第12）
        if(i < systemAllInfoResultList.size() - 1){
          
        }
      }//系统循环end
      tool.CloseExcel(true, true);
      attachResult = new AttachResult();
      attachResult.setUploadUrl(exportUrl);
      attachResult.setAttachName(exportName);
      return attachResult;
    } catch (Exception e) {
      // TODO: handle exception
      attachResult = null;
      return attachResult;
    }
    /*for (int i = 0; i < systemTemPlate.size(); i++) {
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
    }*/ 
      
    /*for (int useExcel = 0; useExcel < useTempPlate.size(); useExcel++) {
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
    }*/

      
      /*for (int keyExcel = 0; keyExcel < keyTempPlate.size(); keyExcel++) {
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
      }*/
        

    //关闭并保存，释放对象
    //tool.CloseExcel(true, true);
    
    
    
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
	 * 
	 * @throws IOException
	 * @throws BusinessException
	 */
	@Override
	public boolean importForSystemTemplate(String userName, String strFilePath)
			throws IOException, BusinessException {
		List<SystemTemplateListResult> systemCode = systemMapper.selectSystemCode();

		// 读取excel数据
		List<String[]> dataList = null;
		try {
			dataList = ExcelUtils.read(SystemConstant.EXCEL_FILE_IMPORT_PATH+strFilePath, "信息系统模版");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		// 将excel取出的数据过滤后转成标准数据
		List<SystemParam> sysListInfo = new ArrayList<SystemParam>();

		int dataListSize = dataList.size();
		if (dataListSize > 5000) {
			throw new BusinessException(EnumResult.ERROR);
		}
		List<Map<String, String>> isService = new ArrayList<Map<String, String>>();
		Map<String, String> isNan = new HashMap<String, String>();// 系统承载业务情况
		List<Map<String, String>> isSysNetWork = new ArrayList<Map<String, String>>();
		Map<String, String> sysNetWork = new HashMap<String, String>();// 系统网络平台
		List<Map<String, String>> isSysNetSit = new ArrayList<Map<String, String>>();
		Map<String, String> sysNetSit = new HashMap<String, String>();// 系统互联情况
		List<List<String[]>> isProUseSitList = new ArrayList<List<String[]>>();
		List<String[]> isProUseSit = new ArrayList<String[]>();// 产品使用情况
		List<List<String[]>> isServiceTypeList = new ArrayList<List<String[]>>();
		List<String[]> serviceType = new ArrayList<String[]>();// 系统服务情况

		int countLeng = 0;
		String[] strsList = new String[dataListSize - 3];

		// excel行号循环
		for (int dataListTem = 3, dataCount = 1; dataListTem < dataListSize; dataListTem++, dataCount++) {
			strsList = dataList.get(dataListTem);
			if (!strsList[11].isEmpty() && !strsList[11].equals("0")) {
				isNan.put(strsList[11], strsList[12]);
			}
			if (!strsList[18].isEmpty() && !strsList[18].equals("0")) {
				sysNetWork.put(strsList[18], strsList[19]);
			}
			if (!strsList[22].isEmpty() && !strsList[22].equals("0")) {
				sysNetSit.put(strsList[22], strsList[23]);
			}
			if (!strsList[24].isEmpty() && !strsList[24].equals("0")) {
				String[] proUseSit = new String[4];
				proUseSit[0] = strsList[24];
				proUseSit[1] = strsList[25];
				proUseSit[2] = strsList[26];
				proUseSit[3] = strsList[27];
				isProUseSit.add(proUseSit);
			}
			if (!strsList[28].isEmpty() && !strsList[28].equals("0")) {
				String[] serType = new String[3];
				serType[0] = strsList[28];
				serType[1] = strsList[29];
				serType[2] = strsList[30];
				serviceType.add(serType);
			}

			if (dataCount % 8 == 0) {
				isService.add(isNan);
				isSysNetWork.add(sysNetWork);
				isSysNetSit.add(sysNetSit);
				isProUseSitList.add(isProUseSit);
				isServiceTypeList.add(serviceType);
				// 每8行一条数据的第一行
				String[] topNum = dataList.get(countLeng * 8 + 3);
				SystemParam system = new SystemParam();
				// 验证是否新建重复
				for (int j = 0; j < systemCode.size(); j++) {
					if (topNum[2].equals(systemCode.get(j))) {
						System.out.println(systemCode.get(j) + "_______________"
								+ topNum[2]);
						return false;
					}
				}
				Map<String, String> map1 = isService.get(countLeng);
				Map<String, String> map2 = isSysNetWork.get(countLeng);
				Map<String, String> map3 = isSysNetSit.get(countLeng);
				List<String[]> list1 = isProUseSitList.get(countLeng);
				List<String[]> list2 = isServiceTypeList.get(countLeng);
				// 添加数据
				system.setSysBusSituationType(this.sheetUtil(map1));// 业务类型
				system.setNpCoverageRange(this.sheetUtil(map2));// 覆盖范围
				system.setInterconnectionSit(this.sheetUtil(map3));// 系统互联情况

				List<SystemKeyProducts> keyList = new ArrayList<SystemKeyProducts>();// 关键产品使用情况
				if (list1 != null) {
					for (int p = 0; p < list1.size(); p++) {
						String[] proCount = list1.get(p);
						SystemKeyProducts keyPro = new SystemKeyProducts();
						if (!proCount[0].isEmpty() && !proCount[1].isEmpty()
								&& !proCount[2].isEmpty() && !proCount[3].isEmpty()) {
							keyPro.setFkSystemId(system.getSystemId());
							switch (proCount[0]) {// 产品类型
							case "安全专用产品":
								keyPro.setFkExaminStatus(1);
								break;
							case "网络产品":
								keyPro.setFkExaminStatus(2);
								break;
							case "操作系统":
								keyPro.setFkExaminStatus(3);
								break;
							case "数据库":
								keyPro.setFkExaminStatus(4);
								break;
							case "服务器":
								keyPro.setFkExaminStatus(5);
								break;
							default:
								keyPro.setFkExaminStatus(6);
								keyPro.setOtherName(proCount[0]);// 其他情况
								break;
							}
							keyPro.setProductsNumber(proCount[1]);// 数量
							switch (proCount[2]) {// 使用情况
							case "全部使用":
								keyPro.setFkNationalIsProducts(1);
								break;
							case "全部未使用":
								keyPro.setFkNationalIsProducts(2);
								break;
							default:
								keyPro.setFkNationalIsProducts(3);
								break;
							}
							keyPro.setnUseProbability(Integer.parseInt(proCount[3]));// 国产率
							keyList.add(keyPro);
						}
					}
					isProUseSit.clear();
				}
				system.setSystemKeyProducts(keyList);
				List<SystemUseServices> systemUseServicesList = new ArrayList<SystemUseServices>();
				if (list2 != null) {
					for (int s = 0; s < list2.size(); s++) {
						String[] serCount = list2.get(s);
						SystemUseServices SystemUseServicesBean = new SystemUseServices();
						if (!serCount[0].isEmpty() && !serCount[1].isEmpty()
								&& !serCount[2].isEmpty()) {
							SystemUseServicesBean.setFkSystemId(system.getSystemId());
							switch (serCount[0]) {// 服务类型
							case "等级测评":
								SystemUseServicesBean.setFkProductsType(1);
								break;
							case "风险评估":
								SystemUseServicesBean.setFkProductsType(2);
								break;
							case "灾难恢复":
								SystemUseServicesBean.setFkProductsType(3);
								break;
							case "应急响应":
								SystemUseServicesBean.setFkProductsType(4);
								break;
							case "系统集成":
								SystemUseServicesBean.setFkProductsType(5);
								break;
							case "安全咨询":
								SystemUseServicesBean.setFkProductsType(6);
								break;
							case "安全培训":
								SystemUseServicesBean.setFkProductsType(7);
								break;
							default:
								SystemUseServicesBean.setServiceIsUse(8);
								SystemUseServicesBean.setOtherName(serCount[0]);// 其他
								break;
							}
							switch (serCount[1]) {// 是否采用
							case "是":
								SystemUseServicesBean.setServiceIsUse(1);
								break;
							case "否":
								SystemUseServicesBean.setServiceIsUse(2);
								break;
							default:
								SystemUseServicesBean.setServiceIsUse(2);
								break;
							}
							switch (serCount[2]) {// 服务责任方类型
							case "国外服务商":
								SystemUseServicesBean.setFkResponsibleType("3");
								break;
							case "国内其他服务商":
								SystemUseServicesBean.setFkResponsibleType("2");
								break;
							case "本行业（单位）":
								SystemUseServicesBean.setFkResponsibleType("1");
								break;
							default:
								SystemUseServicesBean.setFkResponsibleType("0");
								break;
							}
							systemUseServicesList.add(SystemUseServicesBean);
						}
					}
					serviceType.clear();
				}
				system.setSystemUseServices(systemUseServicesList);
				system.setFkInfoSysTypeCon(1);// 信息系统建设类型
				system.setFkSystemIsMerge(2);// 是否为合并系统
				system.setFkSystemType(1);// 系统类型
				system.setSystemName(topNum[1]);// 系统名称
				system.setStandardizedCode(topNum[2]);// 标准化代码
				system.setGradeRecordSysName(topNum[3]);// 等保备案系统名称

				if (StringUtils.isNotBlank(topNum[4])) {
					system.setCompanyName(topNum[4]);// 所属单位名称
					String comCode = this.systemMapper.selectSystemByComCode(system
							.getCompanyName());
					if (comCode != null) {
						system.setFkComCode(2);
						system.setFkCompanyCode(comCode);
					} else {
						system.setFkComCode(1);
					}

				} else {
					return false;
				}
				if (StringUtils.isNotBlank(topNum[5])) {
					try {
						system.setWhenInvestmentUse(new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").parse(topNum[5]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					return false;
				}

				system.setExecutiveOfficeName(topNum[6]);// 主管处室名称
				system.setExecutiveDireCon(topNum[7]);// 主管联系人
				system.setExecutiveDireConTel(topNum[8]);// 联系人电话
				if (StringUtils.isNotBlank(topNum[9])) {
					if (topNum[9].equals("是")) {// 系统是否为分系统
						system.setSubIsSystem(1);
					} else {
						system.setSubIsSystem(2);
					}
				} else {
					return false;
				}
				system.setFatherSystemName(topNum[10]);// 上级系统名称
				if (StringUtils.isNotBlank(topNum[9])) {
					system.setSysBusDescription(topNum[13]);// 业务描述
				} else {
					return false;
				}

				if (StringUtils.isNotBlank(topNum[14])) {
					if (strsList[14].equals("其他")) {// 服务范围
						system.setSysServiceSitScope(topNum[15]);
					} else {
						system.setSysServiceSitScope(topNum[14] + "^" + topNum[15]);
					}
				} else {
					return false;
				}

				if (StringUtils.isNotBlank(topNum[16])) {
					if (topNum[16].equals("其他")) {// 服务对象
						system.setSysServiceSitObject(topNum[17]);
					} else {
						system.setSysServiceSitObject(topNum[16]);
					}
				} else {
					return false;
				}

				if (StringUtils.isNotBlank(topNum[20])) {
					if (strsList[20].equals("其他")) {// 网络性质
						system.setNpNetworkProperties(topNum[21]);
					} else {
						system.setNpNetworkProperties(topNum[20]);
					}
				} else {
					return false;
				}

				sysListInfo.add(system);
				countLeng++;
			}
		}

		// 处理数据
		// 将数据放入数据库
		if (sysListInfo.size() > 0) {
			// 将数据放入数据库
			for (SystemParam sys : sysListInfo) {
				this.saveSystem(userName, sys);
			}
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * (其他)合并单元格
	 */
	public String sheetUtil(Map<String, String> map) {
		for (String key : map.keySet()) {
			if (!map.get(key).isEmpty()) {
				if (StringUtils.isNotBlank(map.get("其他")) && map.get(key).equals("是")) {
					throw new BusinessException("错误");
				} else {
					if (map.get(key).equals("是")) {
						return key;
					}
					if (StringUtils.isNotBlank(map.get("其他"))) {
						return map.get("其他");// 其他
					}
				}
			}
		}
		throw new BusinessException("Map为空");
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
  
  /**
   * 添加子系统信息
   */
  public void saveSonSystem(SystemParam systemParam) {
    
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
      systemParam.setFkCompanyCode(fkCompanyCode);
    List<SystemParam> systemCode = systemParam.getAddSystemSub();
    List<SystemKeyProducts> subKeyList = systemParam.getSystemKeyProducts();
    List<SystemUseServices> subUseList = systemParam.getSystemUseServices();
    List<SystemParam> subSystemList = new ArrayList<SystemParam>();
    List<SystemKeyProducts> systemKeyProductsList = new ArrayList<SystemKeyProducts>();
    List<SystemUseServices> systemUseServicesList = new ArrayList<SystemUseServices>();
    if(systemParam.getAddSystemSub() != null){
        for (int subSystem = 0; subSystem < systemParam.getAddSystemSub().size(); subSystem++) {
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
          systemParamSub.setFatherCompanyName(systemParam.getCompanyName());
          systemParamSub.setFatherSystemName(systemParam.getSystemName());
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
    this.systemKeyProductsMapper.insertSystemKeyProductsBySystemKeyProductsId(
        systemKeyProductsList);
    this.systemUseServicesMapper.insertSystemUseServicesBySystemUseServicesId(
        systemUseServicesList);
    this.systemMapper.insertBatchSystem(subSystemList);
  }
  
  /**
   * 通过系统ID查询审核列表系统信息
   */
  @Override
  public SystemResult querySystemByCheck(SystemParam systemParam) {
    return systemMapper.selectSystemByCheck(systemParam);
  }
  
  /**
   * 获取维护单位系统信息导出回显
   */
  @Override
  public List<SystemEchoResult> querySystemInfoEchoList(
      SystemEchoParam systemEchoParam) {
    
    List<SystemEchoResult> systemEchoResultList = new ArrayList<SystemEchoResult>();
    
    //获取系统信息
    SystemParam systemParam = new SystemParam();
    systemParam.setSystemName(systemEchoParam.getSearchSystem());
    List<SystemListResult> systemList = new ArrayList<SystemListResult>();
    
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return systemEchoResultList;
    }else{
     
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        systemList = 
            systemMapper.selectAllBySystemParam(systemParam);
        break;
      case "2":
        systemParam.setPlateList(organizationApiResult.getNameList());
        systemList =  
            this.systemMapper.selectAllBySystemParam(systemParam);
        break;
      case "3":
        systemParam.setCompanyList(organizationApiResult.getCodeList());
        systemList =  
            this.systemMapper.selectAllBySystemParam(systemParam);
        break;

      default:
        break;
      }
    }
    
    SystemInfoList systemInfoList = null;
    try {
      systemInfoList = JSON.parseObject(systemApiClient.querySystemList(), SystemInfoList.class);
    } catch (Exception e) {
      e.printStackTrace();
      return systemEchoResultList;
    }
    
    if (systemList!=null&&systemList.size()>0) {
      if (systemInfoList!=null&&systemInfoList.getData()!=null&&systemInfoList.getData().size()>0) {
        int systemListSize = systemList.size();
        int systemInfoListSize = systemInfoList.getData().size();
        outermost:
        for (int i = 0; i < systemListSize; i++) {
          SystemListResult systemListResult = systemList.get(i);
          if("3".equals(systemListResult.getFkSystemType())){
            continue;
          }
          
          for (int j = 0; j < systemInfoListSize; j++) {
            SystemInfo systemInfo = systemInfoList.getData().get(j);
            if (systemListResult.getStandardizedCode().equals(systemInfo.getSystemcode())) {
              SystemEchoResult systemEchoResult = new SystemEchoResult();
              systemEchoResult.setSystemId(systemListResult.getSystemId());
              systemEchoResult.setSystemName(systemListResult.getSystemName());
              systemEchoResult.setSystemCode(systemListResult.getStandardizedCode());
              systemEchoResult.setSystemAlias(systemInfo.getIsbaSysalias());
              systemEchoResult.setAppRangeName(systemInfo.getIsbaRangename());
              systemEchoResult.setSystemBranch(systemInfo.getIsbaSysbranch());
              systemEchoResult.setSystemGroup(systemInfo.getIsbaGroup());
              systemEchoResult.setExecutiveOffice(systemInfo.getBcdName());
              systemEchoResultList.add(systemEchoResult);
              continue outermost;
            }
          }
          SystemEchoResult systemEchoResult = new SystemEchoResult();
          systemEchoResult.setSystemId(systemListResult.getSystemId());
          systemEchoResult.setSystemName(systemListResult.getSystemName());
          systemEchoResult.setSystemCode((int)((Math.random()*9+1)*100000)+"");
          systemEchoResult.setSystemAlias("");
          systemEchoResult.setAppRangeName("");
          systemEchoResult.setSystemBranch("");
          systemEchoResult.setSystemGroup("");
          systemEchoResult.setExecutiveOffice(systemListResult.getExecutiveOffice());
          systemEchoResultList.add(systemEchoResult);
        }
      }
    }
    return systemEchoResultList;
  }
  /**
   * @Descrption 通过systemCode查询系统信息
   * @author dongxu
   * @date 2018年8月6日下午7:31:59
   * @param systemParam
   * @return
   */
  @Override
  public SystemResult querySystemBysystemCode(SystemParam systemParam){
    return systemMapper.selectSystemBysystemCode(systemParam);
  }
}

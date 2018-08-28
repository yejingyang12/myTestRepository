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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.codeapi.api.SystemApiClient;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiParam;
import com.sinopec.smcc.cpro.codeapi.entity.SystemInfoList;
import com.sinopec.smcc.cpro.codeapi.entity.SystemInfoList.SystemInfo;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.OrganizationApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.mapper.EvaluationMapper;
import com.sinopec.smcc.cpro.evaluation.server.EvaluationService;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.file.mapper.AttachMapper;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsBeanParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsBeanResult;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsParam;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.grading.server.SystemMaterialsService;
import com.sinopec.smcc.cpro.main.constant.MainConstant;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.mapper.NodeMapper;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RevokeRecordsResult;
import com.sinopec.smcc.cpro.records.mapper.RecordsMapper;
import com.sinopec.smcc.cpro.records.server.RecordsService;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.mapper.SelfexaminationMapper;
import com.sinopec.smcc.cpro.selfexamination.server.SelfexaminationService;
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
	@Autowired
  private SystemApiClient systemApiClient;
  @Autowired
  private SystemRelationMapper systemRelationMapper;
  @Autowired
  private OrganizationApiService organizationApiServiceImpl;
  @Autowired
  private GradingService gradingServiceImpl;
  @Autowired
  private AttachMapper attachMapper;
  @Autowired
  private SystemMaterialsService systemMaterialsServiceImpl;
  @Autowired
  private RecordsService recordsServiceImpl;
  @Autowired
  private EvaluationMapper evaluationMapperImpl;
  @Autowired
  private EvaluationService evaluationServiceImpl;
  @Autowired
  private SelfexaminationMapper selfexaminationMapperImpl;
  @Autowired
  private SelfexaminationService selfexaminationServiceImpl;
  @Autowired
  private NodeMapper nodeMapperImpl;
  @Autowired
  private RecordsMapper recordsMapperImpl;
  @Autowired
  private SystemRelationMapper systemRelationMapperImpl;
  
  
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
        systemParam.setCompanyList(organizationApiResult.getNameList());
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
//    UserDTO userDTO = userApiServiceImpl.getUserInfo();
//    String fkCompanyCode = "";
//    String[] orgCodes = userDTO.getOrgCode().split(",");
//    for (String strCode : orgCodes) {
//      if (strCode.trim().length()>8) {
//        fkCompanyCode = strCode.trim().substring(0, 8);
//      }else {
//        fkCompanyCode = strCode.trim();
//      }
//      break;
//    }
    
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
    systemParam.setUpdateUserName(userName);
    systemParam.setExamineStatus(1);
    systemParam.setExaminationStatus(1);
//   systemParam.setSubIsSystem(2);
    if (systemParam.getFkSystemIsMerge() == 1) {
      systemParam.setFkSystemType(2);
    }else if(systemParam.getFkSystemIsMerge() == 2){
      systemParam.setFkSystemType(1);
    }
    systemParam.setRecordStatus(1);
    systemParam.setGradingStatus(1);
    systemParam.setFkChangeMatter(5);
//    systemParam.setAppIsInternet(2);
    systemParam.setCreateTime(new Date());
    systemParam.setEvaluationStatus(1);
//    if(systemParam.getFkComCode() == 2){
//      systemParam.setFkCompanyCode(fkCompanyCode);
//    }
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
      systemRelationParam.setUpdateUserName(userName);
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
          systemParamSub.setUpdateUserName(userName);
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
          systemRelationParam.setUpdateUserName(userName);
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
  public String editSystem(String userName, SystemParam systemParam) throws BusinessException {
    
//  List<SystemResult> subUpdateSystemSubList = new ArrayList<SystemResult>();
//  List<SystemResult> subUpdateSystemList = new ArrayList<SystemResult>();
//  List<SystemParam> addSubSystem = systemParam.getAddSystemSub();
  
  
  List<SystemParam> systemParamAddList = new ArrayList<SystemParam>();
  List<SystemKeyProducts> subKeyList = systemParam.getSystemKeyProducts();
  List<SystemKeyProducts> subSystemKeyProductsList = new ArrayList<SystemKeyProducts>();
  List<SystemUseServices> subUseList = systemParam.getSystemUseServices();
  List<SystemUseServices> subSystemUseServicesList = new ArrayList<SystemUseServices>();
  
  SystemResult systemFather = this.systemMapper.selectSystem(systemParam);
  systemParam.setUpdateUserName(userName);
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
      systemTempParam.setUpdateUserName(userName);
      systemParamAddList.add(systemTempParam);
      for(SystemParam systemParamSon : systemParam.getAddSystemSub()){
        //如果已有子系统与新提交的子系统Code相同，则sonBoo为true，不进行删除
        if(systemParamSon.getStandardizedCode().equals(systemSubParam.getStandardizedCode())){
          sonBoo = true;
          break;
        }
      }
      if(!sonBoo){
        //创建子系统定级信息
        GradingParam gradingParam = new GradingParam();
        gradingParam.setFkSystemId(systemParam.getSystemId());
        GradingListResult gradingListResult = this.gradingServiceImpl.
            queryDetailsGrading(gradingParam);
        if(gradingListResult != null){
          GradingParam gradingParamSon = new GradingParam();
          gradingParamSon.setFkSystemId(systemSubParam.getSystemId());
          gradingParamSon.setFkBizSPRankDegree(gradingListResult.getFkBizSPRankDegree());
          gradingParamSon.setFkBizSPRankLevel(gradingListResult.getFkBizSPRankLevel());
          gradingParamSon.setFkBizSystemDegree(gradingListResult.getFkBizSystemDegree());
          gradingParamSon.setFkBizSystemLevel(gradingListResult.getFkBizSystemLevel());
          gradingParamSon.setFkSpRanklevel(gradingListResult.getFkSpRanklevel());
          gradingParamSon.setExpertView(gradingListResult.getExpertView());
          gradingParamSon.setRankExplainDesc(gradingListResult.getRankExplainDesc());
          gradingParamSon.setRankTime(gradingListResult.getRankTime());
          gradingParamSon.setCompetentIsExisting(gradingListResult.getCompetentIsExisting());
          if(gradingListResult.getCompetentIsExisting() == 1){
            gradingParamSon.setCompetentName(gradingListResult.getCompetentName());
            gradingParamSon.setCompetentView(gradingListResult.getCompetentView());
          }
          if(StringUtils.isNotBlank(gradingListResult.getFiller())){
            gradingParamSon.setFiller(gradingListResult.getFiller());
          }
          if(gradingListResult.getFillDate() != null){
            gradingParamSon.setFillDate(gradingListResult.getFillDate());
          }
          this.gradingServiceImpl.saveGrading(userName,gradingParamSon);
          //查询定级ID
          GradingParam grading = new GradingParam();
          grading.setFkSystemId(systemSubParam.getSystemId());
          GradingListResult gradingResu = gradingServiceImpl.queryDetailsGrading(grading);
          //定级报告
          if(StringUtils.isNotBlank(gradingListResult.getGradingReportId())){
            AttachParam attachParam = new AttachParam();
            attachParam.setFileId(gradingListResult.getGradingReportId());
            AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
            //创建附件
            AttachParam attach = new AttachParam();
            attach.setSystemId(systemSubParam.getSystemId());
            attach.setAttachName(attachResult.getAttachName());
            attach.setMongoFileId(attachResult.getMongoFileId());
            attach.setAttachType(attachResult.getAttachType());
            attach.setSyssonId(gradingResu.getGradingId());
            attach.setFileId(Utils.getUuidFor32());
            attach.setCreateTime(new Date());
            this.attachMapper.insertAttach(attach);
          }
          //专家评审报告
          if(StringUtils.isNotBlank(gradingListResult.getExpertReviewId())){
            AttachParam attachParam = new AttachParam();
            attachParam.setFileId(gradingListResult.getExpertReviewId());
            AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
            //创建附件
            AttachParam attach = new AttachParam();
            attach.setSystemId(systemSubParam.getSystemId());
            attach.setAttachName(attachResult.getAttachName());
            attach.setMongoFileId(attachResult.getMongoFileId());
            attach.setAttachType(attachResult.getAttachType());
            attach.setSyssonId(gradingResu.getGradingId());
            attach.setFileId(Utils.getUuidFor32());
            attach.setCreateTime(new Date());
            this.attachMapper.insertAttach(attach);
          }
          //上级主管部门审批意见
          if(StringUtils.isNotBlank(gradingListResult.getDirectorOpinionId())){
            AttachParam attachParam = new AttachParam();
            attachParam.setFileId(gradingListResult.getDirectorOpinionId());
            AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
            //创建附件
            AttachParam attach = new AttachParam();
            attach.setSystemId(systemSubParam.getSystemId());
            attach.setAttachName(attachResult.getAttachName());
            attach.setMongoFileId(attachResult.getMongoFileId());
            attach.setAttachType(attachResult.getAttachType());
            attach.setSyssonId(gradingResu.getGradingId());
            attach.setFileId(Utils.getUuidFor32());
            attach.setCreateTime(new Date());
            this.attachMapper.insertAttach(attach);
          }
        }
        //创建子系统表4
        SystemMaterialsParam systemMaterialsParam = new SystemMaterialsParam();
        systemMaterialsParam.setFkSystemId(systemParam.getSystemId());
        SystemMaterialsBeanResult systemMaterialsBeanResult = 
            this.systemMaterialsServiceImpl.queryEditSystemMaterialsInfo(systemMaterialsParam);
        if(systemMaterialsBeanResult != null){
          SystemMaterialsBeanParam systemMaterialsBeanParam = new SystemMaterialsBeanParam();
          systemMaterialsBeanParam.setFkSystemId(systemSubParam.getSystemId());
          this.systemMaterialsServiceImpl.saveSystemMaterialsInfo(userName,
              systemMaterialsBeanParam);
          
          SystemMaterialsParam systemMaterials = new SystemMaterialsParam();
          systemMaterials.setFkSystemId(systemSubParam.getSystemId());
          SystemMaterialsBeanResult systemMaterialsBean = 
              this.systemMaterialsServiceImpl.queryEditSystemMaterialsInfo(systemMaterials);
          
          //系统拓扑结构及说明
          if(!ObjectUtils.isEmpty(systemMaterialsBeanResult.getTopologyDescriptionList())){
            for(AttachResult attachRen:systemMaterialsBeanResult.getTopologyDescriptionList()){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(attachRen.getFileId());
              AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(systemMaterialsBean.getSystemMaterialsId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
          }
          //系统安全组织机构及管理制度
          if(!ObjectUtils.isEmpty(systemMaterialsBeanResult.getOrganizationManagementList())){
            for(AttachResult attachRen : systemMaterialsBeanResult.
                getOrganizationManagementList()){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(attachRen.getFileId());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(systemMaterialsBean.getSystemMaterialsId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
          }
          //系统安全保护设施设计;实施方案或改建实施方案
          if(!ObjectUtils.isEmpty(systemMaterialsBeanResult.getImplementationPlanList())){
            for(AttachResult attachRen :systemMaterialsBeanResult.getImplementationPlanList()){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(attachRen.getFileId());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(systemMaterialsBean.getSystemMaterialsId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
          }
          //系统使用的安全产品清单及认证、销售许可证明
          if(!ObjectUtils.isEmpty(systemMaterialsBeanResult.getLicenseCertificateList())){
            for(AttachResult attachRen :systemMaterialsBeanResult.getLicenseCertificateList()){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(attachRen.getFileId());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(systemMaterialsBean.getSystemMaterialsId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
          }
        }
        
        //创建子备案信息
        RecordsParam recordsParam = new RecordsParam();
        recordsParam.setFkSystemId(systemParam.getSystemId());
        RecordsResult recordsResult = this.recordsServiceImpl.queryRecordsByFkSystemId(
            recordsParam);
        String recordsId = "";
        if(recordsResult != null){
          RecordsParam records = new RecordsParam();
          records.setFkSystemId(systemSubParam.getSystemId());
          records.setRecordCode(recordsResult.getRecordCode());
          records.setRecordCompany(recordsResult.getRecordCompany());
          records.setRecordDate(recordsResult.getRecordDate());
          records.setAcceptCompany(recordsResult.getAcceptCompany());
          records.setAcceptDate(recordsResult.getAcceptDate());
          records.setAcceptReason(recordsResult.getAcceptReason());
          this.recordsServiceImpl.saveRecords(userName,records);
          
          RecordsParam record = new RecordsParam();
          record.setFkSystemId(systemSubParam.getSystemId());
          RecordsResult recordResult = this.recordsServiceImpl.queryRecordsByFkSystemId(record);
          recordsId = recordResult.getRecordsId();
          if(StringUtils.isNotBlank(recordsResult.getRecordReportId())){
            AttachParam attachParam = new AttachParam();
            attachParam.setFileId(recordsResult.getRecordReportId());
            AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
            //创建附件
            AttachParam attach = new AttachParam();
            attach.setSystemId(systemSubParam.getSystemId());
            attach.setAttachName(attachResult.getAttachName());
            attach.setMongoFileId(attachResult.getMongoFileId());
            attach.setAttachType(attachResult.getAttachType());
            attach.setSyssonId(recordsId);
            attach.setFileId(Utils.getUuidFor32());
            attach.setCreateTime(new Date());
            this.attachMapper.insertAttach(attach);
            records.setRecordReportPath(recordsResult.getRecordReportId());
          }
        }
        //创建子撤销备案信息
        RecordsParam revokeParam = new RecordsParam();
        revokeParam.setFkSystemId(systemParam.getSystemId());
        RevokeRecordsResult revokeRecordsResult = 
            recordsServiceImpl.queryRevokeRecords(revokeParam);
        if(revokeRecordsResult != null) {
          if(StringUtils.isNotBlank(recordsId) && 
              StringUtils.isNotBlank(revokeRecordsResult.getRevokeRecordsId())){
            AttachParam attachParam = new AttachParam();
            attachParam.setFileId(revokeRecordsResult.getRevokeRecordsId());
            AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
            //创建附件
            AttachParam attach = new AttachParam();
            attach.setSystemId(systemSubParam.getSystemId());
            attach.setAttachName(attachResult.getAttachName());
            attach.setMongoFileId(attachResult.getMongoFileId());
            attach.setAttachType(attachResult.getAttachType());
            attach.setSyssonId(recordsId);
            attach.setFileId(Utils.getUuidFor32());
            attach.setCreateTime(new Date());
            this.attachMapper.insertAttach(attach);
          }
          RecordsParam revoke = new RecordsParam();
          revoke.setFkSystemId(systemSubParam.getSystemId());
          revoke.setRevokereason(revokeRecordsResult.getRevokereason());
          revoke.setFkrevokematter(Integer.parseInt(revokeRecordsResult.getFkrevokematter()));
          revoke.setUpdateUserName(userName);
          this.recordsMapperImpl.updateRecordsBySystemIdForRevokeRecords(revoke);
        }
        //创建子测评信息
        EvaluationParam evaluationParam = new EvaluationParam();
        evaluationParam.setFkSystemId(systemParam.getSystemId());
        List<EvaluationListResult> evaluationListResultList = 
            this.evaluationMapperImpl.selectAllByEvaluationSystemId(evaluationParam);
        if(!ObjectUtils.isEmpty(evaluationListResultList)){
          for(EvaluationListResult evaluationListResult : evaluationListResultList){
            EvaluationParam evaluation = new EvaluationParam();
            evaluation.setEvaluationId(Utils.getUuidFor32());
            evaluation.setFkSystemId(systemSubParam.getSystemId());
            evaluation.setExamName(evaluationListResult.getExamName());
            evaluation.setExamYear(evaluationListResult.getExamYear());
            evaluation.setExamOrg(evaluationListResult.getExamOrg());
            evaluation.setExamTime(evaluationListResult.getExamTime());
            evaluation.setFkExamStatus(evaluationListResult.getFkExamStatus());
            evaluation.setCreateTime(evaluationListResult.getCreateTime());
            if(evaluationListResult.getFkExamResult() != null){
              evaluation.setFkExamResult(evaluationListResult.getFkExamResult());
            }
            if(evaluationListResult.getFkRectificationReu() != null){
              evaluation.setFkRectificationReu(evaluationListResult.getFkRectificationReu());
            }
            if(evaluationListResult.getRectificationDate() != null){
              evaluation.setRectificationDate(evaluationListResult.getRectificationDate());
            }
            if(StringUtils.isNotBlank(evaluationListResult.getExamName())){
              evaluation.setExamName(evaluationListResult.getExamName());
            }
            evaluation.setUpdateUserName(userName);
            this.evaluationMapperImpl.saveEvaluationByEvaluationId(evaluation);
            
            if(StringUtils.isNotBlank(evaluationListResult.getExamReport())){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(evaluationListResult.getExamReport());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(evaluation.getEvaluationId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
            if(StringUtils.isNotBlank(evaluationListResult.getRectificationReport())){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(evaluationListResult.getRectificationReport());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(evaluation.getEvaluationId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
          }
        }
        //创建子自查信息
        SelfexaminationParam selfexaminationParam = new SelfexaminationParam();
        selfexaminationParam.setFkSystemId(systemParam.getSystemId());
        List<SelfexaminationListResult> selfexaminationListResultList = 
            this.selfexaminationMapperImpl.selectSelfBySystemId(selfexaminationParam);
        if(!ObjectUtils.isEmpty(selfexaminationListResultList)){
          for(SelfexaminationListResult selfexaminationListResult: selfexaminationListResultList){
            SelfexaminationParam selfexamination = new SelfexaminationParam();
            selfexamination.setSelfexaminationId(Utils.getUuidFor32());
            selfexamination.setCreateTime(selfexaminationListResult.getCreateTime());
            selfexamination.setDeleteStatus(1);
            selfexamination.setCreateUserName(userName);
            selfexamination.setUpdateUserName(userName);
            selfexamination.setFkSystemId(systemSubParam.getSystemId());
            selfexamination.setInspectionDate(selfexaminationListResult.getInspectionDate());
            selfexamination.setFkInspectionStatus(
                selfexaminationListResult.getFkInspectionStatus());
            if(selfexaminationListResult.getFkInspectionStatus() != null){
              selfexamination.setFkInspectionReu(
                  selfexaminationListResult.getFkInspectionStatus());
            }
            if(selfexaminationListResult.getFkRectificationReu() != null){
              selfexamination.setFkRectificationReu(
                  selfexaminationListResult.getFkRectificationReu() );
            }
            if(selfexaminationListResult.getRectificationDate() != null){
              selfexamination.setRectificationDate(
                  selfexaminationListResult.getRectificationDate());
            }
            this.selfexaminationMapperImpl.insertOrUpdateSelfexamination(selfexamination);
            
            if(StringUtils.isNotBlank(selfexaminationListResult.getExaminationReportId())){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(selfexaminationListResult.getExaminationReportId());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(selfexamination.getSelfexaminationId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
            if(StringUtils.isNotBlank(
                selfexaminationListResult.getExaminationRectificationReportId())){
              AttachParam attachParam = new AttachParam();
              attachParam.setFileId(selfexaminationListResult.getExaminationRectificationReportId());
              AttachResult attachResult = 
                  this.attachMapper.selectSingleAttachByFileId(attachParam);
              //创建附件
              AttachParam attach = new AttachParam();
              attach.setSystemId(systemSubParam.getSystemId());
              attach.setAttachName(attachResult.getAttachName());
              attach.setMongoFileId(attachResult.getMongoFileId());
              attach.setAttachType(attachResult.getAttachType());
              attach.setSyssonId(selfexamination.getSelfexaminationId());
              attach.setFileId(Utils.getUuidFor32());
              attach.setCreateTime(new Date());
              this.attachMapper.insertAttach(attach);
            }
          }
        }
//        //创建节点信息数据
//        NodeParam nodeParam = new NodeParam();
//        nodeParam.setSystemId(systemParam.getSystemId());
//        List<NodeResult> nodeResultList = this.nodeMapperImpl.selectSingleNodeBySystemId(
//            nodeParam);
//        if(!ObjectUtils.isEmpty(nodeResultList)){
//          for (NodeResult nodeResult : nodeResultList) {
//            //添加节点状态信息
//            NodeParam node = new NodeParam();
//            node.setSystemId(systemSubParam.getSystemId());
//            node.setOperation(nodeResult.getOperation());
//            node.setOperationResult(nodeResult.getOperationResult());
//            if(StringUtils.isNotBlank(nodeResult.getOperationOpinion())){
//              node.setOperationOpinion(nodeResult.getOperationOpinion());
//            }else{
//              node.setOperationOpinion("");
//            }
//            node.setOperator(userName);
//            this.nodeServiceImpl.addNodeInfo(node);
//          }
//        }
        
        //修改关联表
        SystemRelationParam systemRelationParam = new SystemRelationParam();
        systemRelationParam.setSystemSmccCode(systemParam.getSystemId());
        List<SystemRelationResult> systemRelationResultList = 
            systemRelationMapperImpl.querySystemRelationInfo(systemRelationParam);
        if(systemRelationResultList != null && systemRelationResultList.size() >=2){
          for(SystemRelationResult systemRelationResult : systemRelationResultList){
            if(systemRelationResult.getSystemIsMerge().equals("1")){
              SystemRelationParam sysRelationParam = new SystemRelationParam();
              sysRelationParam.setSystemName(systemRelationResult.getSystemName());
              sysRelationParam.setSystemIsMerge("0");
              sysRelationParam.setFkSystemId(systemParam.getSystemId());
              sysRelationParam.setStandardizedName(systemRelationResult.getStandardizedName());
              systemRelationMapperImpl.updateSystemRelationBySystemIdAndStandardizedName(
                  sysRelationParam);
            }
          }
        }

        List<SystemParam> systemDelete = new ArrayList<SystemParam>();
        SystemParam systemParamDel = new SystemParam();
        systemParamDel.setSystemId(systemSubParam.getSystemId());
        systemParamDel.setFkFatherSystemId("");
        systemParamDel.setFkSystemType(1);
        systemParamDel.setUpdateUserName(userName);
        systemDelete.add(systemParamDel);
        this.systemMapper.updateSubStat(systemDelete);
        //修改状态
        SystemParam systemParamUp= new SystemParam();
        systemParamUp.setSystemId(systemSubParam.getSystemId());
        systemParamUp.setGradingStatus(systemFather.getGradingStatus());
        systemParamUp.setExamineStatus(3);
        systemParamUp.setRecordStatus(systemFather.getRecordStatus());
        systemParamUp.setEvaluationStatus(systemFather.getEvaluationStatus());
        systemParamUp.setExaminationStatus(systemFather.getExaminationStatus());
        systemParamUp.setUpdateUserName(userName);
        //拆分出来的不是合并系统
        systemParamUp.setFkSystemIsMerge(2);
        this.systemMapper.updateSystemStatusBySystemId(systemParamUp);
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
          systemRelationParam.setUpdateUserName(userName);
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
	  }else{
	    //查询子表信息
	    List<SystemSubResult> subSystemList = this.systemMapper.selectEditBySub(systemParam);
	    //删除子表信息
      if(!ObjectUtils.isEmpty(subSystemList)){
        this.systemMapper.deleteRelationSonSystem(subSystemList);
      }
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
    
//    if ("1".equals(systemParam.getChangeType())) {
//      //添加节点状态信息
//      NodeParam nodeParam = new NodeParam();
//      nodeParam.setSystemId(systemParam.getSystemId());
//      nodeParam.setOperation("申请变更");
//      nodeParam.setOperationResult("已修改");
//      nodeParam.setOperationOpinion("");
//      nodeParam.setOperator(userName);
//      NodeResult nodeResult = this.nodeServiceImpl.selectSingleNode(nodeParam);
//      if (nodeResult == null) {
//        this.nodeServiceImpl.addNodeInfo(nodeParam);
//      }else{
//        nodeParam.setNodeId(nodeResult.getNodeId());
//        this.nodeServiceImpl.editNodeInfo(nodeParam);
//      }
//    }
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
    
    
    /*String tempPath = MainConstant.TEMPORARY_EXCEL_FILE_PATH;//模板文件路径
    //String tempName = MainConstant.SYSTEM_TEMP_NAME;//系统模板导出名
    String tempName = "systemExportTempCopy.xls";//系统模板导出名 
*/    
    //显示的导出表的标题
    String title = "信息系统模版(黄色为必选项)";
    //导出表的列名
    String[] rowName = new String[]{"序号","系统名称","系统编号","等保备案系统名称","所属单位名称",
        "何时投入使用","主管处室名称","主管联系人","联系人电话","系统是否为分系统",
        "上级系统名称","业务类型","是否有此业务类型","业务描述","服务范围",
        "服务范围所跨地区个数或其他","服务对象","其他","覆盖范围","是否有此覆盖范围值",
        "网络性质","其他","系统互联情况","是否有此系统互联情况值","产品类型",
        "数量","使用情况","使用国产品率","服务类型","是否有此服务类型",
        "服务责任方类型"};
    //合并列名
    String[] rowName2 = new String[]{
        "","系统基本信息","系统承载业务情况","系统服务情况","系统网络平台",
        "系统互联情况","关键产品使用情况","系统采用服务情况"};
    
    //有要导出的对象时
    if(systemAllInfoResultList != null && systemAllInfoResultList.size() > 0){
      try {
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建工作薄
        HSSFSheet sheet = workbook.createSheet("信息系统模版");// 创建工作表
        //是否保护模板
        //sheet.protectSheet("system");
        HSSFSheet hidden = workbook.createSheet("hidden");
        HSSFSheet hidden2 = workbook.createSheet("hidden2");
        // 数据源sheet页不显示
        workbook.setSheetHidden(1, true);
        workbook.setSheetHidden(2, true);
        
        // 产生表格标题行
        HSSFRow rowm = sheet.createRow(0);
        HSSFCell cellTitle = rowm.createCell(0);
        
        // sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面 - 可扩展】
        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
        
        HSSFCellStyle style = this.getStyle(workbook);// 单元格样式对象
        HSSFCellStyle style2 = this.getStyleWarn(workbook);// 警告单元格样式对象
        HSSFCellStyle style3 = this.getStyleTimeWarn(workbook);// 时间警告单元格样式对象
        HSSFCellStyle style4 = this.getStyleWarn25(workbook);// 25列警告单元格样式对象
        HSSFCellStyle style5 = this.getStyleWarn27(workbook);// 27列警告单元格样式对象
        
        // 合并列
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 10));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 11, 13));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 14, 17));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 18, 21));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 22, 23));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 24, 27));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 28, 30));
        
        // 冻结单元格区域
        sheet.createFreezePane(4, 4);
        
        // 合并行
        for (int j = 0; j < systemAllInfoResultList.size(); j++) {
          for (int i = 0; i < rowName.length; i++) {
            if (i == 11 || i == 12 || i == 24 || i == 25 || i == 26 || i == 27) {
              sheet.addMergedRegion(new CellRangeAddress(9 + j * 8, 11 + j * 8, i, i));
            } else if (i == 18 || i == 19) {
              sheet.addMergedRegion(new CellRangeAddress(7 + j * 8, 11 + j * 8, i, i));
            } else if (i == 22 || i == 23) {
              sheet.addMergedRegion(new CellRangeAddress(7 + j * 8, 11 + j * 8, i, i));
            } else if (i == 28 || i == 29 || i == 30) {
              //sheet.addMergedRegion(new CellRangeAddress(11 + j * 8, 11 + j * 8, i, i));
            } else {
              sheet.addMergedRegion(new CellRangeAddress(4 + j * 8, 11 + j * 8, i, i));
            }
          }
        }
        
        Name namedCell = workbook.createName();
        Name namedCell2 = workbook.createName();
        // 下拉列
        for (int j = 0; j < systemAllInfoResultList.size(); j++) {
          for (int i = 0; i < rowName.length; i++) {
            CellRangeAddressList regions = null;
            DVConstraint constraint = null;
            if (i == 9) {
              //  是否分为系统
              regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "是", "否" });// 生成下拉框内容
            } else if (i == 4) {
              // 生成下拉框内容(所属单位名称)
              /*if(this.getUnitInfoList() != null && this.getUnitInfoList().length>0){
                regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
                constraint = DVConstraint.createExplicitListConstraint(this.getUnitInfoList());
              }*/
              if(this.getUnitInfoList() != null && this.getUnitInfoList().length>0){
                regions = new CellRangeAddressList(4 + j * 8, 8 + j * 8, i, i); // 创建所要下拉的区域
                constraint = this.getUnitNameList(workbook, hidden2, this.getUnitInfoList(), namedCell2);// 生成下拉框内容
              }
            } else if (i == 10) {
              //  上级系统名称
              if(this.getUpSystemList() != null && this.getUpSystemList().length>0){
                regions = new CellRangeAddressList(4 + j * 8, 8 + j * 8, i, i); // 创建所要下拉的区域
                constraint = this.getDownList(workbook, hidden, this.getUpSystemList(), namedCell);// 生成下拉框内容
              }
            } else if (i == 12) {
              //  是否有此业务类型
              regions = new CellRangeAddressList(4 + j * 8, 8 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "是", "否" });// 生成下拉框内容
            } else if (i == 14) {
              //  服务范围
              regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "全国", "全省（区、市）", "跨省（区、市）", "跨地（市、区）", "地（市、区）内 ", "其他" });// 生成下拉框内容
            } else if (i == 16) {
              //  服务对象
              regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "单位内部人员 ", "社会公众人员", "两者均包括" });// 生成下拉框内容
            } else if (i == 19) {
              //  是否有此覆盖范围值
              regions = new CellRangeAddressList(4 + j * 8, 6 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "是", "否" });// 生成下拉框内容
            } else if (i == 20) {
              //  网络性质
              regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "业务专网", "互联网" });// 生成下拉框内容
            } else if (i == 23) {
              //  是否有此系统互联情况
              regions = new CellRangeAddressList(4 + j * 8, 6 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "是", "否" });// 生成下拉框内容
            } else if (i == 26) {
              //  使用情况
              regions = new CellRangeAddressList(4 + j * 8, 9 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "全部使用", "全部未使用", "部分使用" });// 生成下拉框内容
            } else if (i == 29) {
              //  是否有此服务类型
              regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "是", "否" });// 生成下拉框内容
            } else if (i == 30) {
              //  服务责任方类型
              regions = new CellRangeAddressList(4 + j * 8, 11 + j * 8, i, i); // 创建所要下拉的区域
              constraint = DVConstraint.createExplicitListConstraint(
                  new String[] { "本行业（单位）", "国内其他服务商", "国外服务商" });// 生成下拉框内容
            }
            
            if (regions != null && constraint != null) {
              HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);// 绑定下拉框和作用区域
              sheet.addValidationData(data_validation);// 对sheet页生效
            }
          }
        }

        cellTitle.setCellStyle(columnTopStyle);
        cellTitle.setCellValue(title);
        
        int columnNum = rowName.length; // 定义所需列数
        HSSFRow rowRowName = sheet.createRow(3);// 在索引2的位置创建行(最顶端的行开始的第二行)

        // 将列头设置到sheet的单元格中
        for (int n = 0; n < columnNum; n++) {
          HSSFCell cellRowName = rowRowName.createCell(n);// 创建列头对应个数的单元格
          //cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
          HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
          cellRowName.setCellValue(text);// 设置列头单元格的值
          cellRowName.setCellStyle(columnTopStyle);// 设置列头单元格样式
        }

        HSSFRow rowRowName2 = sheet.createRow(2);

        HSSFCell cellRowName = rowRowName2.createCell(0);// 创建列头对应个数的单元格
        //cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text = new HSSFRichTextString(rowName2[0]);
        cellRowName.setCellValue(text);// 设置列头单元格的值
        cellRowName.setCellStyle(columnTopStyle);// 设置列头单元格样式

        HSSFCell cellRowName2 = rowRowName2.createCell(1);// 创建列头对应个数的单元格
        //cellRowName2.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text2 = new HSSFRichTextString(rowName2[1]);
        cellRowName2.setCellValue(text2);// 设置列头单元格的值
        cellRowName2.setCellStyle(columnTopStyle);// 设置列头单元格样式
        for (int k = 2; k < 11; k++) {
          HSSFCell cellRow = rowRowName2.createCell(k);
          cellRow.setCellStyle(columnTopStyle);
        }

        HSSFCell cellRowName3 = rowRowName2.createCell(11);// 创建列头对应个数的单元格
        //cellRowName3.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text3 = new HSSFRichTextString(rowName2[2]);
        cellRowName3.setCellValue(text3);// 设置列头单元格的值
        cellRowName3.setCellStyle(columnTopStyle);// 设置列头单元格样式
        for (int k = 12; k < 14; k++) {
          HSSFCell cellRow = rowRowName2.createCell(k);
          cellRow.setCellStyle(columnTopStyle);
        }

        HSSFCell cellRowName4 = rowRowName2.createCell(14);// 创建列头对应个数的单元格
        //cellRowName4.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text4 = new HSSFRichTextString(rowName2[3]);
        cellRowName4.setCellValue(text4);// 设置列头单元格的值
        cellRowName4.setCellStyle(columnTopStyle);// 设置列头单元格样式
        for (int k = 15; k < 18; k++) {
          HSSFCell cellRow = rowRowName2.createCell(k);
          cellRow.setCellStyle(columnTopStyle);
        }

        HSSFCell cellRowName5 = rowRowName2.createCell(18);// 创建列头对应个数的单元格
        //cellRowName5.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text5 = new HSSFRichTextString(rowName2[4]);
        cellRowName5.setCellValue(text5);// 设置列头单元格的值
        cellRowName5.setCellStyle(columnTopStyle);// 设置列头单元格样式
        for (int k = 19; k < 22; k++) {
          HSSFCell cellRow = rowRowName2.createCell(k);
          cellRow.setCellStyle(columnTopStyle);
        }

        HSSFCell cellRowName6 = rowRowName2.createCell(22);// 创建列头对应个数的单元格
        //cellRowName6.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text6 = new HSSFRichTextString(rowName2[5]);
        cellRowName6.setCellValue(text6);// 设置列头单元格的值
        cellRowName6.setCellStyle(columnTopStyle);// 设置列头单元格样式
        HSSFCell cellRowC = rowRowName2.createCell(23);
        cellRowC.setCellStyle(columnTopStyle);

        HSSFCell cellRowName7 = rowRowName2.createCell(24);// 创建列头对应个数的单元格
        //cellRowName7.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text7 = new HSSFRichTextString(rowName2[6]);
        cellRowName7.setCellValue(text7);// 设置列头单元格的值
        cellRowName7.setCellStyle(columnTopStyle);// 设置列头单元格样式
        for (int k = 25; k < 28; k++) {
          HSSFCell cellRow = rowRowName2.createCell(k);
          cellRow.setCellStyle(columnTopStyle);
        }

        HSSFCell cellRowName8 = rowRowName2.createCell(28);// 创建列头对应个数的单元格
        //cellRowName8.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列头单元格的数据类型
        HSSFRichTextString text8 = new HSSFRichTextString(rowName2[7]);
        cellRowName8.setCellValue(text8);// 设置列头单元格的值
        cellRowName8.setCellStyle(columnTopStyle);// 设置列头单元格样式
        for (int k = 29; k < 31; k++) {
          HSSFCell cellRow = rowRowName2.createCell(k);
          cellRow.setCellStyle(columnTopStyle);
        }

        // 将查询出的数据设置到sheet对应的单元格中
        for (int i = 0; i < systemAllInfoResultList.size(); i++) {
          SystemAllInfoResult systemInfo = systemAllInfoResultList.get(i);// 遍历每个对象
          //获取关键产品
          List<SystemKeyProducts> systemKeyProductsList = systemInfo.getSystemKeyProductsList();
          //获取采用服务
          List<SystemUseServices> systemUseServicesList = systemInfo.getSystemUseServicesList();
          //获取子系统
          //List<SystemSubResult> systemSubResultList = systemAllInfoResult.getSystemSubResultList();
          
          HSSFRow row = sheet.createRow(i * 8 + 4);// 创建所需的行数
          HSSFRow row3 = sheet.createRow(i * 8 + 5);
          HSSFRow row4 = sheet.createRow(i * 8 + 6);
          HSSFRow row5 = sheet.createRow(i * 8 + 7);
          HSSFRow row6 = sheet.createRow(i * 8 + 8);
          HSSFRow row7 = sheet.createRow(i * 8 + 9);
          HSSFRow row8 = sheet.createRow(i * 8 + 10);
          HSSFRow row9 = sheet.createRow(i * 8 + 11);
          
          for (int j = 0; j < 31; j++) {
            HSSFCell cell = null;// 设置单元格的数据类型
            if (j == 0) {
            //序号
              cell = row.createCell(j);
              cell.setCellValue(i + 1);
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 1) {
            //系统名称
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getSystemName());
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 2) {
            //系统标准化代码
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getStandardizedCode());
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 3) {
            //等保备案名称
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getGradeRecordSysName());
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 4) {
            //所属单位名称
              cell = row.createCell(j);
              // 设置格式
              cell.setCellStyle(style2);
              cell.setCellValue(systemInfo.getCompanyName());
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 5) {
            //何时投入使用
              cell = row.createCell(j);
              HSSFPatriarch p = sheet.createDrawingPatriarch();// 创建绘图对象
              //TODO:放入时间
              // 获得批注对象（(int dx1, int dy1, int dx2, int dy2, short
              // col1, int row1, short col2, int
              // row2)前四个参数是坐标点,后四个参数是编辑和显示批注时的大小）
              HSSFComment comment = p.createComment(
                  new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
              comment.setString(new HSSFRichTextString("请输入格式为：20XX-XX-XX"));
              cell.setCellComment(comment);// 将批注添加都单元格对象中
              Date whenInvestmentUse = systemInfo.getWhenInvestmentUse();
              if (whenInvestmentUse != null) {
                String strWhenInvestmentUse = DateUtils.getDate("yyyy-MM-dd", whenInvestmentUse);
                cell.setCellValue(strWhenInvestmentUse);
              }
              cell.setCellStyle(style3);

              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style3);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style3);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style3);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style3);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style3);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style3);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style3);
            } else if(j == 6){
            //主管处室名称
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getExecutiveOfficeName());
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if(j == 7){
            //主管联系人
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getExecutiveDireCon());
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if(j == 8){
            //联系人电话
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getExecutiveDireConTel());
              cell.setCellStyle(columnTopStyle);// 设置列头单元格样式
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(columnTopStyle);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(columnTopStyle);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(columnTopStyle);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 9) {
            //系统是否为分系统
              String strSubIsSystem = "";
              if(systemInfo.getSubIsSystem() == 1){
                strSubIsSystem = "是";
              }else if(systemInfo.getSubIsSystem() == 2){
                strSubIsSystem = "否";
              }
              cell = row.createCell(j);
              cell.setCellValue(strSubIsSystem);
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 10) {
            //上级系统名称
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getFatherSystemName());
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 11) {//业务类型
              HSSFCell cell2a = row.createCell(j);// 设置单元格的数据类型
              cell2a.setCellValue("生产作业");// 设置单元格的值
              cell2a.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3a = row3.createCell(j);// 设置单元格的数据类型
              cell3a.setCellValue("指挥调度");// 设置单元格的值
              cell3a.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell4a = row4.createCell(j);// 设置单元格的数据类型
              cell4a.setCellValue("管理控制");// 设置单元格的值
              cell4a.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell5a = row5.createCell(j);// 设置单元格的数据类型
              cell5a.setCellValue("内部办公");// 设置单元格的值
              cell5a.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell6a = row6.createCell(j);// 设置单元格的数据类型
              cell6a.setCellValue("公众服务");// 设置单元格的值
              cell6a.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell7a = row7.createCell(j);// 设置单元格的数据类型
              cell7a.setCellValue("其他");// 设置单元格的值
              cell7a.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 12) {
            //是否有此业务类型
              boolean flag = true;
              String strSysBusSituationType = systemInfo.getSysBusSituationType();
              cell = row.createCell(j);
              if("生产作业".equals(strSysBusSituationType)){
                cell.setCellValue("是");
                flag = false;
              }else{
                cell.setCellValue("否");
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              if("指挥调度".equals(strSysBusSituationType)){
                cell3.setCellValue("是");
                flag = false;
              }else{
                cell3.setCellValue("否");
              }
              cell3.setCellStyle(style2);
              
              HSSFCell cell4 = row4.createCell(j);
              if("管理控制".equals(strSysBusSituationType)){
                cell4.setCellValue("是");
                flag = false;
              }else{
                cell4.setCellValue("否");
              }
              cell4.setCellStyle(style2);
              
              HSSFCell cell5 = row5.createCell(j);
              if("内部办公".equals(strSysBusSituationType)){
                cell5.setCellValue("是");
                flag = false;
              }else{
                cell5.setCellValue("否");
              }
              cell5.setCellStyle(style2);
              
              HSSFCell cell6 = row6.createCell(j);
              if("公众服务".equals(strSysBusSituationType)){
                cell6.setCellValue("是");
                flag = false;
              }else{
                cell6.setCellValue("否");
              }
              cell6.setCellStyle(style2);
              
              HSSFCell cell7 = row7.createCell(j);
              if (flag) {
                cell7.setCellValue(strSysBusSituationType);
              }
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 13) {
            //业务描述
              cell = row.createCell(j);
              cell.setCellValue(systemInfo.getSysBusDescription());
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 14) {
            //服务范围
              String strSysServiceSitScope = systemInfo.getSysServiceSitScope()==null
                  ?"":systemInfo.getSysServiceSitScope();
              String[] strSysServiceSitScopes = strSysServiceSitScope.split("\\^");
              cell = row.createCell(j);
              if((strSysServiceSitScopes.length != 2 
                  && !"全国".equals(strSysServiceSitScopes[0])
                  && !"全省（区、市）".equals(strSysServiceSitScopes[0])
                  && !"地（区、市）".equals(strSysServiceSitScopes[0]))
                  ||(strSysServiceSitScopes.length == 2 
                  && !StringUtils.isNumeric(strSysServiceSitScopes[1]))){
                cell.setCellValue("其他");
              }else{
                cell.setCellValue(strSysServiceSitScopes[0]);
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 15) {
            //服务范围所跨地区个数或其他
              String strSysServiceSitScope = systemInfo.getSysServiceSitScope()==null
                  ?"":systemInfo.getSysServiceSitScope();
              //分割时用"^"字符来分割，但在split方法中要用"\\^"来充当"^"来使用
              String[] strSysServiceSitScopes = strSysServiceSitScope.split("\\^");
              cell = row.createCell(j);
              if((strSysServiceSitScopes.length != 2 
                  && !"全国".equals(strSysServiceSitScopes[0])
                  && !"全省（区、市）".equals(strSysServiceSitScopes[0])
                  && !"地（区、市）".equals(strSysServiceSitScopes[0]))
                  ||(strSysServiceSitScopes.length == 2 
                  && !StringUtils.isNumeric(strSysServiceSitScopes[1]))){
                cell.setCellValue(strSysServiceSitScope);
              }else{
                if (strSysServiceSitScopes.length == 2 
                    && StringUtils.isNumeric(strSysServiceSitScopes[1])) {
                  cell.setCellValue(strSysServiceSitScopes[1]);
                }else{
                  cell.setCellValue("");
                }
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 16) {
            //服务对象
              String strSysServiceSitObject = systemInfo.getSysServiceSitObject();
              cell = row.createCell(j);
              if("单位内部人员".equals(strSysServiceSitObject) 
                  || "社会公众人员".equals(strSysServiceSitObject) 
                  || "两者均包括".equals(strSysServiceSitObject)){
                cell.setCellValue(strSysServiceSitObject);
              }/*else{
                cell.setCellValue("其他");
              }*/
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 17) {
            //服务对象其他
              String strSysServiceSitObject = systemInfo.getSysServiceSitObject();
              cell = row.createCell(j);
              if("单位内部人员".equals(strSysServiceSitObject) 
                  || "社会公众人员".equals(strSysServiceSitObject) 
                  || "两者均包括".equals(strSysServiceSitObject)){
                //cell.setCellValue("");
              }else{
                cell.setCellValue(strSysServiceSitObject);
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 18) {//
              HSSFCell cell2b = row.createCell(j);// 设置单元格的数据类型
              cell2b.setCellValue("局域网");// 设置单元格的值
              cell2b.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3b = row3.createCell(j);// 设置单元格的数据类型
              cell3b.setCellValue("城域网");// 设置单元格的值
              cell3b.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell4b = row4.createCell(j);// 设置单元格的数据类型
              cell4b.setCellValue("广域网");// 设置单元格的值
              cell4b.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell5b = row5.createCell(j);// 设置单元格的数据类型
              cell5b.setCellValue("其他");// 设置单元格的值
              cell5b.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 19) {
            //是否有此覆盖范围
              boolean flag = true;
              String strNpCoverageRange = systemInfo.getNpCoverageRange();
              cell = row.createCell(j);
              if("局域网".equals(strNpCoverageRange)){
                cell.setCellValue("是");
                flag = false;
              }else{
                cell.setCellValue("否");
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              if("城域网".equals(strNpCoverageRange)){
                cell3.setCellValue("是");
                flag = false;
              }else{
                cell3.setCellValue("否");
              }
              cell3.setCellStyle(style2);
              
              HSSFCell cell4 = row4.createCell(j);
              if("广域网".equals(strNpCoverageRange)){
                cell4.setCellValue("是");
                flag = false;
              }else{
                cell4.setCellValue("否");
              }
              cell4.setCellStyle(style2);
              
              HSSFCell cell5 = row5.createCell(j);
              if (flag) {
                cell5.setCellValue(strNpCoverageRange);
              }else{
                //cell5.setCellValue("");
              }
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 20) {
            //网络性质
              String strNpNetworkProperties = systemInfo.getNpNetworkProperties();
              cell = row.createCell(j);
              if("业务专网".equals(strNpNetworkProperties) || "互联网".equals(strNpNetworkProperties)){
                cell.setCellValue(strNpNetworkProperties);
              }else{
                //cell.setCellValue("");
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 21) {
            //网络性质其他
              String strNpNetworkProperties = systemInfo.getNpNetworkProperties();
              cell = row.createCell(j);
              if("业务专网".equals(strNpNetworkProperties) || "互联网".equals(strNpNetworkProperties)){
                //cell.setCellValue(strNpNetworkProperties);
              }else{
                cell.setCellValue(strNpNetworkProperties);
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 22) {
              HSSFCell cell2c = row.createCell(j);// 设置单元格的数据类型
              cell2c.setCellValue("与其他行业系统连接 ");// 设置单元格的值
              cell2c.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3c = row3.createCell(j);// 设置单元格的数据类型
              cell3c.setCellValue("与本行业其他单位系统连接");// 设置单元格的值
              cell3c.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell4c = row4.createCell(j);// 设置单元格的数据类型
              cell4c.setCellValue("与本单位其他系统连接");// 设置单元格的值
              cell4c.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell5c = row5.createCell(j);// 设置单元格的数据类型
              cell5c.setCellValue("其他");// 设置单元格的值
              cell5c.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(columnTopStyle);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(columnTopStyle);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 23) {
            //系统互联情况
              boolean flag = true;
              String strInterconnectionSit = systemInfo.getInterconnectionSit();
              cell = row.createCell(j);
              if ("与其他行业系统连接".equals(strInterconnectionSit)) {
                cell.setCellValue("是");
                flag = false;
              }else{
                cell.setCellValue("否");
              }
              cell.setCellStyle(style2);
              
              HSSFCell cell3 = row3.createCell(j);
              if ("与本行业其他单位系统连接".equals(strInterconnectionSit)) {
                cell3.setCellValue("是");
                flag = false;
              }else{
                cell3.setCellValue("否");
              }
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              if ("与本单位其他系统连接".equals(strInterconnectionSit)) {
                cell4.setCellValue("是");
                flag = false;
              }else{
                cell4.setCellValue("否");
              }
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              if (flag) {
                cell5.setCellValue(strInterconnectionSit);
              }
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
            } else if (j == 24) {
              HSSFCell cell2d = row.createCell(j);// 设置单元格的数据类型
              cell2d.setCellValue("安全专用产品");// 设置单元格的值
              cell2d.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3d = row3.createCell(j);// 设置单元格的数据类型
              cell3d.setCellValue("网络产品");// 设置单元格的值
              cell3d.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell4d = row4.createCell(j);// 设置单元格的数据类型
              cell4d.setCellValue("操作系统");// 设置单元格的值
              cell4d.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell5d = row5.createCell(j);// 设置单元格的数据类型
              cell5d.setCellValue("数据库");// 设置单元格的值
              cell5d.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell6d = row6.createCell(j);// 设置单元格的数据类型
              cell6d.setCellValue("服务器");// 设置单元格的值
              cell6d.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell7d = row7.createCell(j);// 设置单元格的数据类型

              HSSFPatriarch p = sheet.createDrawingPatriarch();// 创建绘图对象
              // 获得批注对象（(int dx1, int dy1, int dx2, int dy2, short
              // col1, int row1, short col2, int
              // row2)前四个参数是坐标点,后四个参数是编辑和显示批注时的大小）
              HSSFComment comment = p
                  .createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
              comment.setString(new HSSFRichTextString("请输入其他产品类型"));
              cell7d.setCellComment(comment);// 将批注添加都单元格对象中
              
              //其他的关键产品名放入数据
              for (SystemKeyProducts systemKeyProductsTemp : systemKeyProductsList) {
                if(systemKeyProductsTemp.getFkExaminStatus() == 6){
                  if(systemKeyProductsTemp.getOtherName() != null){
                    cell7d.setCellValue(systemKeyProductsTemp.getOtherName());
                  }
                }
              }
              cell7d.setCellStyle(style);// 设置列头单元格样式
              
              //数据验证
              HSSFDataValidation validate = setValidate((short) (i * 8 + 9),  
                          (short) j, (short) (i * 8 + 9), (short) j, 20);  
                  // 设定规则  
                  sheet.addValidationData(validate); 

              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(columnTopStyle);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(columnTopStyle);
            } else if (j == 25) {
            //数量  
              HSSFCell cell2d = row.createCell(j);// 设置单元格的数据类型
              cell2d.setCellStyle(style4);// 设置列头单元格样式

              HSSFCell cell3d = row3.createCell(j);// 设置单元格的数据类型
              cell3d.setCellStyle(style4);// 设置列头单元格样式

              HSSFCell cell4d = row4.createCell(j);// 设置单元格的数据类型
              cell4d.setCellStyle(style4);// 设置列头单元格样式

              HSSFCell cell5d = row5.createCell(j);// 设置单元格的数据类型
              cell5d.setCellStyle(style4);// 设置列头单元格样式

              HSSFCell cell6d = row6.createCell(j);// 设置单元格的数据类型
              cell6d.setCellStyle(style4);// 设置列头单元格样式

              HSSFCell cell7d = row7.createCell(j);// 设置单元格的数据类型
              cell7d.setCellStyle(style4);// 设置列头单元格样式

              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style4);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style4);
              //放入数量
              for (SystemKeyProducts systemKeyProductsTemp : systemKeyProductsList) {
                switch (systemKeyProductsTemp.getFkExaminStatus()) {
                case 1:
                  if(systemKeyProductsTemp.getProductsNumber() != null){
                    cell2d.setCellValue(systemKeyProductsTemp.getProductsNumber());
                  }
                  break;
                case 2:
                  if(systemKeyProductsTemp.getProductsNumber() != null){
                    cell3d.setCellValue(systemKeyProductsTemp.getProductsNumber());
                  }
                  break;
                case 3:
                  if(systemKeyProductsTemp.getProductsNumber() != null){
                    cell4d.setCellValue(systemKeyProductsTemp.getProductsNumber());
                  }
                  break;
                case 4:
                  if(systemKeyProductsTemp.getProductsNumber() != null){
                    cell5d.setCellValue(systemKeyProductsTemp.getProductsNumber());
                  }
                  break;
                case 5:
                  if(systemKeyProductsTemp.getProductsNumber() != null){
                    cell6d.setCellValue(systemKeyProductsTemp.getProductsNumber());
                  }
                  break;
                case 6:
                  if(systemKeyProductsTemp.getProductsNumber() != null){
                    cell7d.setCellValue(systemKeyProductsTemp.getProductsNumber());
                  }
                  break;
                default:
                  break;
                }
              }
            } else if (j == 26) {
            //使用情况
              cell = row.createCell(j);
              cell.setCellStyle(style2);
              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style2);
              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style2);
              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style2);
              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style2);
              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style2);
              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style2);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style2);
              //放入使用情况
              for (SystemKeyProducts systemKeyProductsTemp : systemKeyProductsList) {
                Integer fkNationalIsProducts = systemKeyProductsTemp.getFkNationalIsProducts();
                String strFkNationalIsProducts = null;
                if(fkNationalIsProducts == 1){
                  strFkNationalIsProducts = "全部使用";
                }else if(fkNationalIsProducts == 2){
                  strFkNationalIsProducts = "全部未使用";
                }else if(fkNationalIsProducts == 3){
                  strFkNationalIsProducts = "部分使用";
                }
                switch (systemKeyProductsTemp.getFkExaminStatus()) {
                case 1:
                  if(strFkNationalIsProducts != null){
                    cell.setCellValue(strFkNationalIsProducts);
                  }
                  break;
                case 2:
                  if(strFkNationalIsProducts != null){
                    cell3.setCellValue(strFkNationalIsProducts);
                  }
                  break;
                case 3:
                  if(strFkNationalIsProducts != null){
                    cell4.setCellValue(strFkNationalIsProducts);
                  }
                  break;
                case 4:
                  if(strFkNationalIsProducts != null){
                    cell5.setCellValue(strFkNationalIsProducts);
                  }
                  break;
                case 5:
                  if(strFkNationalIsProducts != null){
                    cell6.setCellValue(strFkNationalIsProducts);
                  }
                  break;
                case 6:
                  if(strFkNationalIsProducts != null){
                    cell7.setCellValue(strFkNationalIsProducts);
                  }
                  break;
                default:
                  break;
                }
              }
            } else if (j == 27) {
            //国产品使用率
              HSSFCell cell2d = row.createCell(j);// 设置单元格的数据类型
              cell2d.setCellStyle(style5);// 设置列头单元格样式

              HSSFCell cell3d = row3.createCell(j);// 设置单元格的数据类型
              cell3d.setCellStyle(style5);// 设置列头单元格样式

              HSSFCell cell4d = row4.createCell(j);// 设置单元格的数据类型
              cell4d.setCellStyle(style5);// 设置列头单元格样式

              HSSFCell cell5d = row5.createCell(j);// 设置单元格的数据类型
              cell5d.setCellStyle(style5);// 设置列头单元格样式

              HSSFCell cell6d = row6.createCell(j);// 设置单元格的数据类型
              cell6d.setCellStyle(style5);// 设置列头单元格样式

              HSSFCell cell7d = row7.createCell(j);// 设置单元格的数据类型
              cell7d.setCellStyle(style5);// 设置列头单元格样式

              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style5);
              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style5);
              //放入国产品使用率
              for (SystemKeyProducts systemKeyProductsTemp : systemKeyProductsList) {
                double nUseProbability = (systemKeyProductsTemp.getnUseProbability()==null
                    ?0:systemKeyProductsTemp.getnUseProbability())/100.0;
                Integer fkNationalIsProducts = systemKeyProductsTemp.getFkNationalIsProducts();
                String strFkNationalIsProducts = null;
                if(fkNationalIsProducts == 1){
                  strFkNationalIsProducts = "全部使用";
                }else if(fkNationalIsProducts == 2){
                  strFkNationalIsProducts = "全部未使用";
                }else if(fkNationalIsProducts == 3){
                  strFkNationalIsProducts = "部分使用";
                }
                switch (systemKeyProductsTemp.getFkExaminStatus()) {
                case 1:
                  if (strFkNationalIsProducts != null) {
                    cell2d.setCellValue(nUseProbability);
                  }
                  break;
                case 2:
                  if (strFkNationalIsProducts != null) {
                    cell3d.setCellValue(nUseProbability);
                  }
                  break;
                case 3:
                  if (strFkNationalIsProducts != null) {
                    cell4d.setCellValue(nUseProbability);
                  }
                  break;
                case 4:
                  if (strFkNationalIsProducts != null) {
                    cell5d.setCellValue(nUseProbability);
                  }
                  break;
                case 5:
                  if (strFkNationalIsProducts != null) {
                    cell6d.setCellValue(nUseProbability);
                  }
                  break;
                case 6:
                  if (strFkNationalIsProducts != null) {
                    cell7d.setCellValue(nUseProbability);
                  }
                  break;
                default:
                  break;
                }
              }
            } else if (j == 28) {
              HSSFCell cell2e = row.createCell(j);// 设置单元格的数据类型
              cell2e.setCellValue("等级测评");// 设置单元格的值
              cell2e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell3e = row3.createCell(j);// 设置单元格的数据类型
              cell3e.setCellValue("风险评估");// 设置单元格的值
              cell3e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell4e = row4.createCell(j);// 设置单元格的数据类型
              cell4e.setCellValue("灾难恢复");// 设置单元格的值
              cell4e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell5e = row5.createCell(j);// 设置单元格的数据类型
              cell5e.setCellValue("应急响应");// 设置单元格的值
              cell5e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell6e = row6.createCell(j);// 设置单元格的数据类型
              cell6e.setCellValue("系统集成");// 设置单元格的值
              cell6e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell7e = row7.createCell(j);// 设置单元格的数据类型
              cell7e.setCellValue("安全咨询");// 设置单元格的值
              cell7e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell8e = row8.createCell(j);// 设置单元格的数据类型
              cell8e.setCellValue("安全培训");// 设置单元格的值
              cell8e.setCellStyle(columnTopStyle);// 设置列头单元格样式

              HSSFCell cell9e = row9.createCell(j);// 设置单元格的数据类型

              HSSFPatriarch p = sheet.createDrawingPatriarch();// 创建绘图对象
              // 获得批注对象（(int dx1, int dy1, int dx2, int dy2, short
              // col1, int row1, short col2, int
              // row2)前四个参数是坐标点,后四个参数是编辑和显示批注时的大小）
              HSSFComment comment = p
                  .createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
              comment.setString(new HSSFRichTextString("请输入其他服务类型"));
              cell9e.setCellComment(comment);// 将批注添加都单元格对象中
              //放入其他的使用服务
              for (SystemUseServices systemUseServicesTemp : systemUseServicesList) {
                if (systemUseServicesTemp.getFkProductsType() == 8) {
                  if (systemUseServicesTemp.getOtherName() != null) {
                    cell9e.setCellValue(systemUseServicesTemp.getOtherName());
                  }
                }
              }
              cell9e.setCellStyle(style);// 设置列头单元格样式
              
              //数据验证
              HSSFDataValidation validate = setValidate((short) (i * 8 + 11),  
                          (short) j, (short) (i * 8 + 11), (short) j, 20);  
                  // 设定规则  
                  sheet.addValidationData(validate); 
            } else if(j == 29) {
            //是否有此服务类型
              cell = row.createCell(j);
              cell.setCellStyle(style);

              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style);

              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style);

              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style);

              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style);

              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style);

              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style);

              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style);
              
              //系统采用服务情况 是否有此服务类型
              for (SystemUseServices systemUseServicesTemp : systemUseServicesList) {
                //服务类型
                Integer fkProductsType = systemUseServicesTemp.getFkProductsType()==null
                    ?0:systemUseServicesTemp.getFkProductsType();
                //是否采用
                Integer serviceIsUse = systemUseServicesTemp.getServiceIsUse();
                String strServiceIsUse = null;
                if(serviceIsUse == 1){
                  strServiceIsUse = "是";
                }else if(serviceIsUse == 2){
                  strServiceIsUse = "否";
                }
                switch (fkProductsType) {
                case 1:
                  if(strServiceIsUse != null){
                    cell.setCellValue(strServiceIsUse);
                  }
                  break;
                case 2:
                  if(strServiceIsUse != null){
                    cell3.setCellValue(strServiceIsUse);
                  }
                  break;
                case 3:
                  if(strServiceIsUse != null){
                    cell4.setCellValue(strServiceIsUse);
                  }
                  break;
                case 4:
                  if(strServiceIsUse != null){
                    cell5.setCellValue(strServiceIsUse);
                  }
                  break;
                case 5:
                  if(strServiceIsUse != null){
                    cell6.setCellValue(strServiceIsUse);
                  }
                  break;
                case 6:
                  if(strServiceIsUse != null){
                    cell7.setCellValue(strServiceIsUse);
                  }
                  break;
                case 7:
                  if(strServiceIsUse != null){
                    cell8.setCellValue(strServiceIsUse);
                  }
                  break;
                case 8:
                  if(strServiceIsUse != null){
                    cell9.setCellValue(strServiceIsUse);
                  }
                  break;
                default:
                  break;
                }
              }//系统采用服务情况end
              //数据验证
              HSSFDataValidation validate = setValidate((short) (i * 8 + 4),  
                          (short) j, (short) (i * 8 + 11), (short) j, 30);  
                  // 设定规则  
                  sheet.addValidationData(validate);
            } else if(j == 30) {
            //服务责任方类型
              cell = row.createCell(j);
              cell.setCellStyle(style);

              HSSFCell cell3 = row3.createCell(j);
              cell3.setCellStyle(style);

              HSSFCell cell4 = row4.createCell(j);
              cell4.setCellStyle(style);

              HSSFCell cell5 = row5.createCell(j);
              cell5.setCellStyle(style);

              HSSFCell cell6 = row6.createCell(j);
              cell6.setCellStyle(style);

              HSSFCell cell7 = row7.createCell(j);
              cell7.setCellStyle(style);

              HSSFCell cell8 = row8.createCell(j);
              cell8.setCellStyle(style);

              HSSFCell cell9 = row9.createCell(j);
              cell9.setCellStyle(style);
              
              //系统采用服务情况 服务责任方类型
              for (SystemUseServices systemUseServicesTemp : systemUseServicesList) {
                //服务类型
                Integer fkProductsType = systemUseServicesTemp.getFkProductsType()==null
                    ?0:systemUseServicesTemp.getFkProductsType();
                //服务责任方类型
                String fkResponsibleType = systemUseServicesTemp.getFkResponsibleType();
                String strFkResponsibleType = null;
                if("1".equals(fkResponsibleType)){
                  strFkResponsibleType = "本行业（单位）";
                }else if("2".equals(fkResponsibleType)){
                  strFkResponsibleType = "国内其他服务商";
                }else if("3".equals(fkResponsibleType)){
                  strFkResponsibleType = "国外服务商";
                }
                switch (fkProductsType) {
                case 1:
                  if(strFkResponsibleType != null){
                    cell.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 2:
                  if(strFkResponsibleType != null){
                    cell3.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 3:
                  if(strFkResponsibleType != null){
                    cell4.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 4:
                  if(strFkResponsibleType != null){
                    cell5.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 5:
                  if(strFkResponsibleType != null){
                    cell6.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 6:
                  if(strFkResponsibleType != null){
                    cell7.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 7:
                  if(strFkResponsibleType != null){
                    cell8.setCellValue(strFkResponsibleType);
                  }
                  break;
                case 8:
                  if(strFkResponsibleType != null){
                    cell9.setCellValue(strFkResponsibleType);
                  }
                  break;
                default:
                  break;
                }
              }//系统采用服务情况end
            }
            
          }
        }

        // 让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
          int columnWidth = sheet.getColumnWidth(colNum) / 256;
          for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
            HSSFRow currentRow;
            // 当前行未被使用过
            if (sheet.getRow(rowNum) == null) {
              currentRow = sheet.createRow(rowNum);
            } else {
              currentRow = sheet.getRow(rowNum);
            }
            if (currentRow.getCell(colNum) != null) {
              HSSFCell currentCell = currentRow.getCell(colNum);
              if (currentCell.getCellTypeEnum() == CellType.STRING) {
                int length = currentCell.getStringCellValue().getBytes().length;
                if (columnWidth < length) {
                  columnWidth = length;
                }
              }
            }
          }
          if (colNum == 0) {
            sheet.setColumnWidth(colNum, 8 * 256);
          } else if (colNum == 1) {
            sheet.setColumnWidth(colNum, 15 * 256);
          } else if (colNum == 3) {
            sheet.setColumnWidth(colNum, 25 * 256);
          } else {
            sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
          }
        }
        
        
        if (workbook != null) {
          try {
            /*SimpleDateFormat sdm = new SimpleDateFormat("yyyyMMdd");
            String fileName = "信息系统导入模版-" + sdm.format(new Date()) + ".xls";
            response.setContentType("APPLICATION/OCTET-STREAM");
            
            response.reset();
            String agent = request.getHeader("User-Agent");
            boolean isMSIE = (agent != null && agent.toLowerCase().matches(".*(msie\\s|trident.*rv:)([\\w.]+).*"));
            if (isMSIE) {
              response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF8") + "\"");
            } else {
              response.setHeader("Content-Disposition", "attachment; filename=\"" + new String((fileName).getBytes(), "ISO8859-1") + "\"");
            }
            
            
            OutputStream out = response.getOutputStream();*/
            
            String temporaryCopyPath = MainConstant.TEMPORARY_FILE_PATH;
            String exportUrl = "systemExportTemp"+"_"+DateUtils.getMilliseconds()+".xls";
            String exportName = "系统导出模板"+".xls";
            String newFile = temporaryCopyPath + exportUrl;
            File exportFile = new File(newFile);
            String exportAbsolutePath = exportFile.getAbsolutePath();
            OutputStream out = new FileOutputStream(new File(exportAbsolutePath));
            workbook.write(out);
            out.flush();
            out.close();
            
            attachResult = new AttachResult();
            attachResult.setUploadUrl(exportUrl);
            attachResult.setAttachName(exportName);
          } catch (Exception e) {
            /*logger.error(e);
            throw new Exception(getText("ExportExcelException"), e);*/
            e.printStackTrace();
            attachResult = null;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        attachResult = null;
      }
    }
    return attachResult;
    
    /*JacobExcelTool tool = new JacobExcelTool();
    InputStream is = null;
    OutputStream os = null;
    HSSFWorkbook wb = null;
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
      FileInputStream ins = new FileInputStream(tempFile);
      FileOutputStream out = new FileOutputStream(exportFile);
      byte[] b = new byte[1024];
      int count=0;
      while((count=ins.read(b))!=-1){
        out.write(b, 0, count);
      }
      ins.close();
      out.close();
      
      //复制系统格式
      is = new FileInputStream(tempAbsolutePath);
      os = new FileOutputStream(new File(exportAbsolutePath));
      wb = new HSSFWorkbook(is);
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
        String[] strSysServiceSitScopes = strSysServiceSitScope.split("\\^");
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
      attachResult = null;
      return attachResult;
    } finally {
      try {
        if (wb != null) {
          wb.close();
        }
        if (os != null) {
          os.close();
        }
        if (is != null) {
          is.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }*/
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
   * @Descrption  数据长度的验证
   * @author yejingyang
   * @date 2018年8月9日下午7:07:10
   * @param beginRow
   * @param beginCol
   * @param endRow
   * @param endCol
   * @param length
   * @return
   */
  private HSSFDataValidation setValidate(short beginRow,  
          short beginCol, short endRow, short endCol, Integer length) {  
      DVConstraint constraint = DVConstraint.createNumericConstraint(  
              DVConstraint.ValidationType.TEXT_LENGTH,  
              DVConstraint.OperatorType.BETWEEN, "1", length.toString());  
      // 设定在哪个单元格生效  
      CellRangeAddressList regions = new CellRangeAddressList(beginRow,  
          endRow, beginCol, endCol);  
      // 创建规则对象  
      HSSFDataValidation ret = new HSSFDataValidation(regions, constraint); 
      ret.createErrorBox("错误", "请输入" + length + "以内的字符！");
      return ret;
  }

  /**
   * @Descrption  因下拉列表过大，所以如此解决
   * @author yejingyang
   * @date 2018年8月9日下午4:03:03
   * @param workbook
   * @param hidden
   * @param datas
   * @param namedCell
   * @return
   */
  private DVConstraint getDownList(HSSFWorkbook workbook, HSSFSheet hidden, String[] datas,
      Name namedCell) {
    CellStyle style = workbook.createCellStyle();
    // style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
    
    //style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setAlignment(HorizontalAlignment.CENTER);
    //style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    
    HSSFRow row = null;
    HSSFCell cell = null;
    for (int i = 0, length = datas.length; i < length; i++) {
      row = hidden.createRow(i);
      cell = row.createCell(0);
      cell.setCellValue(datas[i]);
    }
    if(datas != null && datas.length>0){
      namedCell.setNameName("hidden");
      namedCell.setRefersToFormula("hidden!$A$1:$A$" + datas.length);
    }
    
    DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden");
    return constraint;
  }

  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年8月9日下午4:00:23
   * @return
   */
  private String[] getUpSystemList() {
    try {
      /*List<InfosysSituation> list = infoSystemService.getgetInfoSystemExceptId(getLoginUser().getUserID(), null);
      String[] array = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
        array[i] = list.get(i).getSystemName();
      }
      return array;*/
      String[] array = new String[]{};
      List<SystemListResult> list = this.systemMapper.selectAllBySystemParam(new SystemParam());
      if (list != null && list.size() > 0) {
        array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
          array[i] = list.get(i).getSystemName();
        }
      }
      return array;
    } catch (Exception e) {
      /*logger.error(e);
      throw new Exception(getText("common.msg.server.error"), e);*/
      return new String[]{};
    }
  }

  /**
   * @Descrption  因单位列表下拉框过大
   * @author yejingyang
   * @date 2018年8月9日下午3:57:04
   * @param workbook
   * @param hidden
   * @param datas
   * @param namedCell
   * @return
   */
  private DVConstraint getUnitNameList(HSSFWorkbook workbook, HSSFSheet hidden, 
      String[] datas, Name namedCell) {
    CellStyle style = workbook.createCellStyle();
    // style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
    //style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setAlignment(HorizontalAlignment.CENTER);
    //style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    HSSFRow row = null;
    HSSFCell cell = null;
    for (int i = 0, length = datas.length; i < length; i++) {
      row = hidden.createRow(i);
      cell = row.createCell(0);
      cell.setCellValue(datas[i]);
    }
    if(datas != null && datas.length>0){
      namedCell.setNameName("unitName");
      namedCell.setRefersToFormula("hidden2!$A$1:$A$" + datas.length);
    }
    
    DVConstraint constraint = DVConstraint.createFormulaListConstraint("unitName");
    return constraint;
  }

  /**
   * @Descrption  根据登录者id获取所属单位信息
   * @author yejingyang
   * @date 2018年8月9日下午3:27:57
   * @return
   */
  private String[] getUnitInfoList() {
    try {
      //List<LpUnitInfo> list = unitInfoService.getRightUnitInfo(getLoginUser().getUserID());
      //List<SnpViewNode> list = unitInfoService.getAllLeafViewNode();
      /*paramSearchMap.put("userid", getLoginUser().getUserID());
      paramSearchMap.put("orderCol", "");
      paramSearchMap.put("isFilter", "yes");
      pageManage.setRows(Integer.MAX_VALUE);
      List<LpUnitInfo> list = unitInfoService.listUnitInfo(paramSearchMap, pageManage);
      String[] array = new String[list.size()];
      if(CharUtil.isNotEmpty(list) && list.size()>0){
        for (int i = 0; i < list.size(); i++) {
          array[i] = list.get(i).getUnitName();
        }
      }
      return array;*/
      List<OrganizationApi> organizationApi = this.organizationApiServiceImpl.
          queryOrgForKeyOrganizationCode(new OrganizationApiParam());
      String[] array = new String[]{};
      if (organizationApi != null && organizationApi.size() > 0) {
        array = new String[organizationApi.size()];
        for (int i = 0; i < array.length; i++) {
          array[i] = organizationApi.get(i).getOrgName();
        }
      }
      return array;
    } catch (Exception e) {
      return new String[]{};
    }
  }

  /**
   * @Descrption  27列警告单元格样式对象
   * @author yejingyang
   * @date 2018年8月9日下午3:19:10
   * @param workbook
   * @return
   */
  @SuppressWarnings("deprecation")
  private HSSFCellStyle getStyleWarn27(HSSFWorkbook workbook) {
    // 设置字体
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);// 设置字体大小
    font.setBold(true);//weight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
    font.setFontName("Courier New");// 设置字体名称
    // 设置样式
    HSSFCellStyle style2 = workbook.createCellStyle();
    //style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 设置底边框
    style2.setBorderBottom(BorderStyle.THIN);// 设置底边框
    style2.setBottomBorderColor(HSSFColor.BLACK.index);// 设置底边框颜色
    //style2.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置左边框;
    style2.setBorderLeft(BorderStyle.THIN); // 设置左边框;
    style2.setLeftBorderColor(HSSFColor.BLACK.index);// 设置左边框颜色;
    //style2.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置右边框;
    style2.setBorderRight(BorderStyle.THIN); // 设置右边框;
    style2.setRightBorderColor(HSSFColor.BLACK.index); // 设置右边框颜色;
    //style2.setBorderTop(HSSFColor.BLACK.index);// 设置顶边框;
    style2.setBorderTop(BorderStyle.THIN);// 设置顶边框;
    style2.setTopBorderColor(HSSFColor.BLACK.index); // 设置顶边框颜色;
    style2.setFillForegroundColor(HSSFColor.YELLOW.index); // 设置单元格背景色;
    //style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 设置单元格背景色;
    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 设置单元格背景色;
    style2.setFont(font);// 在样式用应用设置的字体;
    style2.setWrapText(false); // 设置自动换行;
    //style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置水平对齐的样式为居中对齐;
    style2.setAlignment(HorizontalAlignment.CENTER);// 设置水平对齐的样式为居中对齐;
    //style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直对齐的样式为居中对齐;
    style2.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直对齐的样式为居中对齐;
    style2.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));// 设置百分数样式
    style2.setLocked(false);// 是否锁定
    return style2;
  }

  /**
   * @Descrption  25列警告单元格样式对象
   * @author yejingyang
   * @date 2018年8月9日下午3:18:25
   * @param workbook
   * @return
   */
  @SuppressWarnings("deprecation")
  private HSSFCellStyle getStyleWarn25(HSSFWorkbook workbook) {
    // 设置字体
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);// 设置字体大小
    font.setFontName("Courier New");// 设置字体名称

    // 设置样式
    HSSFCellStyle style2 = workbook.createCellStyle();
    //style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 设置底边框
    style2.setBorderBottom(BorderStyle.THIN);// 设置底边框
    style2.setBottomBorderColor(HSSFColor.BLACK.index);// 设置底边框颜色
    //style2.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置左边框;
    style2.setBorderLeft(BorderStyle.THIN); // 设置左边框;
    style2.setLeftBorderColor(HSSFColor.BLACK.index);// 设置左边框颜色;
    //style2.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置右边框;
    style2.setBorderRight(BorderStyle.THIN); // 设置右边框;
    style2.setRightBorderColor(HSSFColor.BLACK.index); // 设置右边框颜色;
    //style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 设置顶边框;
    style2.setBorderTop(BorderStyle.THIN);// 设置顶边框;
    style2.setTopBorderColor(HSSFColor.BLACK.index); // 设置顶边框颜色;
    style2.setFillForegroundColor(HSSFColor.YELLOW.index); // 设置单元格背景色;
    //style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 设置单元格背景色;
    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 设置单元格背景色;
    style2.setFont(font);// 在样式用应用设置的字体;
    style2.setWrapText(false); // 设置自动换行;
    //style2.setAlignment(HSSFCellStyle.CENTER);// 设置水平对齐的样式为居中对齐;
    style2.setAlignment(HorizontalAlignment.CENTER);// 设置水平对齐的样式为居中对齐;
    //style2.setVerticalAlignment(HSSFCellStyle.CENTER); // 设置垂直对齐的样式为居中对齐;
    style2.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直对齐的样式为居中对齐;
    style2.setLocked(false);
    return style2;
  }

  /**
   * @Descrption  时间警告单元格样式对象
   * @author yejingyang
   * @date 2018年8月9日下午3:14:06
   * @param workbook
   * @return
   */
  @SuppressWarnings("deprecation")
  private HSSFCellStyle getStyleTimeWarn(HSSFWorkbook workbook) {
    // 设置字体
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);// 设置字体大小
    font.setFontName("Courier New");// 设置字体名称

    // 设置样式
    HSSFCellStyle style2 = workbook.createCellStyle();
    style2.setBorderBottom(BorderStyle.THIN);// 设置底边框
    style2.setBottomBorderColor(HSSFColor.BLACK.index);// 设置底边框颜色
    style2.setBorderLeft(BorderStyle.THIN); // 设置左边框;
    style2.setLeftBorderColor(HSSFColor.BLACK.index);// 设置左边框颜色;
    style2.setBorderRight(BorderStyle.THIN); // 设置右边框;
    style2.setRightBorderColor(HSSFColor.BLACK.index); // 设置右边框颜色;
    style2.setBorderTop(BorderStyle.THIN);// 设置顶边框;
    style2.setTopBorderColor(HSSFColor.BLACK.index); // 设置顶边框颜色;
    style2.setFillForegroundColor(HSSFColor.YELLOW.index); // 设置单元格背景色;
    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 设置单元格背景色;
    style2.setFont(font);// 在样式用应用设置的字体;
    style2.setWrapText(false); // 设置自动换行;
    style2.setAlignment(HorizontalAlignment.CENTER);// 设置水平对齐的样式为居中对齐;
    style2.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直对齐的样式为居中对齐;
    style2.setLocked(false);
    // 设置日期格式
    HSSFDataFormat format = workbook.createDataFormat();
    style2.setDataFormat(format.getFormat("yyyy-mm-dd"));
    return style2;
  }

  /**
   * @Descrption  警告单元格样式对象
   * @author yejingyang
   * @date 2018年8月9日下午3:13:18
   * @param workbook
   * @return
   */
  @SuppressWarnings("deprecation")
  private HSSFCellStyle getStyleWarn(HSSFWorkbook workbook) {
    // 设置字体
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);// 设置字体大小
    font.setFontName("Courier New");// 设置字体名称

    // 设置样式
    HSSFCellStyle style2 = workbook.createCellStyle();
    style2.setBorderBottom(BorderStyle.THIN);// 设置底边框
    style2.setBottomBorderColor(HSSFColor.BLACK.index);// 设置底边框颜色
    style2.setBorderLeft(BorderStyle.THIN); // 设置左边框;
    style2.setLeftBorderColor(HSSFColor.BLACK.index);// 设置左边框颜色;
    style2.setBorderRight(BorderStyle.THIN); // 设置右边框;
    style2.setRightBorderColor(HSSFColor.BLACK.index); // 设置右边框颜色;
    style2.setBorderTop(BorderStyle.THIN);// 设置顶边框;
    style2.setTopBorderColor(HSSFColor.BLACK.index); // 设置顶边框颜色;
    style2.setFillForegroundColor(HSSFColor.YELLOW.index); // 设置单元格背景色;
    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 设置单元格背景色;
    style2.setFont(font);// 在样式用应用设置的字体;
    style2.setWrapText(false); // 设置自动换行;
    style2.setAlignment(HorizontalAlignment.CENTER);// 设置水平对齐的样式为居中对齐;
    style2.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直对齐的样式为居中对齐;
    style2.setLocked(false);
    return style2;
  }

  /**
   * @Descrption  列数据信息单元格样式
   * @author yejingyang
   * @date 2018年8月9日下午3:11:29
   * @param workbook
   * @return
   */
  @SuppressWarnings("deprecation")
  private HSSFCellStyle getStyle(HSSFWorkbook workbook) {
    // 设置字体
    HSSFFont font = workbook.createFont();
    // font.setFontHeightInPoints((short)10); //设置字体大小
    // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
    font.setFontName("Courier New");// 设置字体名字

    // 设置样式;
    HSSFCellStyle style = workbook.createCellStyle();
    style.setBorderBottom(BorderStyle.THIN);// 设置底边框;
    style.setBottomBorderColor(HSSFColor.BLACK.index);// 设置底边框颜色;
    style.setBorderLeft(BorderStyle.THIN);// 设置左边框;
    style.setLeftBorderColor(HSSFColor.BLACK.index); // 设置左边框颜色;
    style.setBorderRight(BorderStyle.THIN);// 设置右边框;
    style.setRightBorderColor(HSSFColor.BLACK.index);// 设置右边框颜色;
    style.setBorderTop(BorderStyle.THIN); // 设置顶边框;
    style.setTopBorderColor(HSSFColor.BLACK.index);// 设置顶边框颜色;
    style.setFont(font); // 在样式用应用设置的字体;
    style.setWrapText(false);// 设置自动换行;
    style.setAlignment(HorizontalAlignment.CENTER); // 设置水平对齐的样式为居中对齐;
    style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直对齐的样式为居中对齐;
    style.setLocked(false);
    return style;
  }

  /**
   * @Descrption  获取列头样式对象
   * @author yejingyang
   * @date 2018年8月9日下午3:10:44
   * @param workbook
   * @return
   */
  @SuppressWarnings("deprecation")
  private HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
    // 设置字体
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);// 设置字体大小
    font.setBold(true);//weight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
    font.setFontName("Courier New");// 设置字体名称

    // 设置样式
    HSSFCellStyle style = workbook.createCellStyle();
    style.setBorderBottom(BorderStyle.THIN);// 设置底边框
    style.setBottomBorderColor(HSSFColor.BLACK.index);// 设置底边框颜色
    style.setBorderLeft(BorderStyle.THIN); // 设置左边框;
    style.setLeftBorderColor(HSSFColor.BLACK.index);// 设置左边框颜色;
    style.setBorderRight(BorderStyle.THIN); // 设置右边框;
    style.setRightBorderColor(HSSFColor.BLACK.index); // 设置右边框颜色;
    style.setBorderTop(BorderStyle.THIN);// 设置顶边框;
    style.setTopBorderColor(HSSFColor.BLACK.index); // 设置顶边框颜色;
    style.setFont(font);// 在样式用应用设置的字体;
    style.setWrapText(false); // 设置自动换行;
    style.setAlignment(HorizontalAlignment.CENTER);// 设置水平对齐的样式为居中对齐;
    style.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直对齐的样式为居中对齐;
    style.setLocked(true);
    return style;
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
      return false;
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
      if(strsList.length==31){
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
            if (comCode != null || StringUtils.isNotBlank(comCode)) {
              system.setFkComCode(2);
              system.setFkCompanyCode(comCode);
            } else {
              return false;
            }

          } else {
            return false;
          }
          if (StringUtils.isNotBlank(topNum[5])) {
            try {
              system.setWhenInvestmentUse(new SimpleDateFormat(
                  "yyyy-MM-dd").parse(topNum[5].toString()));
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
      }else{
        return false;
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
        systemParam.setCompanyList(organizationApiResult.getNameList());
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

  @Override
  public SystemAllInfoResult queryChange(SystemParam systemParam) {
    String[] systemIds = {systemParam.getSystemId()};
    systemParam.setSystemIds(systemIds);
    List<SystemAllInfoResult> systemAllInfoResults = systemMapper.selectSystemAllInfoBySystemParam(systemParam);
    SystemAllInfoResult systemAllInfoResult = null;
    if(systemAllInfoResults!=null){
      systemAllInfoResult = systemAllInfoResults.get(0);
    }
    return systemAllInfoResult;
  }


}

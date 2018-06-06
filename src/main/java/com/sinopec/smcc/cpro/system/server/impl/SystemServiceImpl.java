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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import com.sinopec.smcc.cpro.system.entity.SystemTemplateListResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseServices;
import com.sinopec.smcc.cpro.system.mapper.SystemKeyProductsMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.system.mapper.SystemUseServicesMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.system.util.ConvertFieldUtil;
import com.sinopec.smcc.cpro.tools.DateUtils;
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
      systemParam.setCreateTime(new Date());
      systemParam.setWhenInvestmentUse(new Date());
      systemParam.setEvaluationStatus(1);
      List<SystemKeyProducts> systemKeyProductsList = new ArrayList<SystemKeyProducts>();
      SystemKeyProducts systemKeyProducts = new SystemKeyProducts();
      systemKeyProducts.setSystemKeyProductsId(Utils.getUuidFor32());
      systemKeyProducts.setFkSystemId(systemParam.getSystemId());
      systemKeyProducts.setDeleteStatus(1);
      systemKeyProducts.setnUseProbability(20);
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
	  if(StringUtils.isBlank(systemParam.getSystemId())) 
	    throw new BusinessException(EnumResult.ERROR);
	  return this.systemMapper.selectDetailsSystem(systemParam);   
	}
	
	/**
	 * 修改系统信息查询详情
	 * @throws BusinessException 
	 */
	@Override
	@EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException {
	  if(StringUtils.isBlank(systemParam.getSystemId())) 
	    throw new BusinessException(EnumResult.ERROR);
	  return this.systemMapper.selectEditSystem(systemParam);
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
  
  /**
   * 系统信息模板导出
   */
  @Override
  public void exportExcelForSystemTemplate(SystemParam systemParam) {
    String strFilePath = "C://export/";
    String strFileName = "xlsx"+"_"+DateUtils.getMilliseconds()+".xlsx";
    
    File file = new File(strFilePath);
    if (!file.exists()) {
      file.mkdirs();
    }
    // 生成excel
    ExcelBean excelBean = new ExcelBean();
    excelBean.setFilePath(strFilePath + strFileName);
    
    SheetBean sheetBean = new SheetBean();
    sheetBean.setRowFixed(1);
    Map<Integer, Short> columnWidthMap = new HashMap<Integer, Short>();
    columnWidthMap.put(9, (short)25);
    sheetBean.setColumnWidthMap(columnWidthMap);
    sheetBean.setDefaultRowHeight((short) 20);
    sheetBean.setDefaultColumnWidth((short) 15);
    sheetBean.setSheetName("Sheet1");
    List<List<CellBean>> dataList = new ArrayList<List<CellBean>>();
    List<CellBean> cellList = new ArrayList<CellBean>();
    // 装载第一行为表头
    cellList.add(ExcelUtils.getExportCelBean("系统名称"));
    cellList.add(ExcelUtils.getExportCelBean("系统编码"));
    cellList.add(ExcelUtils.getExportCelBean("系统别名"));
    cellList.add(ExcelUtils.getExportCelBean("应用范围"));
    cellList.add(ExcelUtils.getExportCelBean("所属信息系统分类"));
    cellList.add(ExcelUtils.getExportCelBean("所属系统群"));
    cellList.add(ExcelUtils.getExportCelBean("业务主管部门"));
    dataList.add(cellList);
    
   // List<SystemTemplateListResult> systemTemPlate = new ArrayList();
    List<SystemTemplateListResult> systemTemPlate = 
        this.systemMapper.selectSystemTemPlate(systemParam);
    
    if(systemTemPlate != null && systemTemPlate.size() > 0){
      for (SystemTemplateListResult systemListResult : systemTemPlate) {
        cellList = new ArrayList<CellBean>();
        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getSystemName()));
        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getStandardizedCode()));
        cellList.add(ExcelUtils.getExportCelBean(systemListResult.getExecutiveOfficeName()));
        dataList.add(cellList);
      }
    }
    sheetBean.setDataList(dataList);
    List<SheetBean> sheetBeanList = new ArrayList<SheetBean>();
    sheetBeanList.add(sheetBean);
    excelBean.setSheetList(sheetBeanList);
    // 创建excel错误
    try {
      ExcelUtils.write(excelBean);
    } catch (IOException e) {
      e.printStackTrace();
    } 
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
}

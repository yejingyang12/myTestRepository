/**
 * @Title MainServiceImpl.java
 * @Package com.sinopec.smcc.cpro.main.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:46:31
 * @version V1.0
 */
package com.sinopec.smcc.cpro.main.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.excel.ExcelUtils;
import com.sinopec.smcc.cpro.tools.excel.bean.CellBean;
import com.sinopec.smcc.cpro.tools.excel.bean.ExcelBean;
import com.sinopec.smcc.cpro.tools.excel.bean.SheetBean;

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
    List<MainListResult> list = this.mainMapper.selectAllByMainParam(mainParam);

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
    this.mainMapper.updateMainDeleteStatusBySystemId(mainParam);
  }

  /**
   * 导出excel
   */
  @Override
  public void exportExcelForMain() throws BusinessException {    
    String strFilePath = "E://export/";
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
    cellList.add(ExcelUtils.getExportCelBean("所属单位"));
    cellList.add(ExcelUtils.getExportCelBean("板块"));
    cellList.add(ExcelUtils.getExportCelBean("建设类型"));
    cellList.add(ExcelUtils.getExportCelBean("等保级别"));
    cellList.add(ExcelUtils.getExportCelBean("是否为互联网应用"));
    cellList.add(ExcelUtils.getExportCelBean("定级状态"));
    cellList.add(ExcelUtils.getExportCelBean("审核状态"));
    cellList.add(ExcelUtils.getExportCelBean("备案状态"));
    cellList.add(ExcelUtils.getExportCelBean("测评状态"));
    cellList.add(ExcelUtils.getExportCelBean("自查状态"));
    
    dataList.add(cellList);

    List<MainListResult> mainListResultList = new ArrayList<>();
    try {
      mainListResultList = this.mainMapper.selectAllByMainParam(new MainParam());
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(mainListResultList != null && mainListResultList.size() > 0){
      for (MainListResult mainResult : mainListResultList) {
        cellList = new ArrayList<CellBean>();
        cellList.add(ExcelUtils.getExportCelBean(mainResult.getSystemName()));
        cellList.add(ExcelUtils.getExportCelBean(mainResult.getCompanyName()));
        cellList.add(ExcelUtils.getExportCelBean(mainResult.getPlateType()));
        cellList.add(ExcelUtils.getExportCelBean(mainResult.getInfoSysTypeConstruction()));
        cellList.add(ExcelUtils.getExportCelBean(mainResult.getSprankLevel()));
        //是否为互联网应用
        if(mainResult.getAppIsInternet() == 1){
          cellList.add(ExcelUtils.getExportCelBean("是"));
        }else{
          cellList.add(ExcelUtils.getExportCelBean("否"));
        }
        //定级状态
        if(mainResult.getGradingStatus() == 1){
          cellList.add(ExcelUtils.getExportCelBean("未进行"));
        }else if(mainResult.getGradingStatus() == 2){
          cellList.add(ExcelUtils.getExportCelBean("进行中"));
        }else if(mainResult.getGradingStatus() == 3){
          cellList.add(ExcelUtils.getExportCelBean("已完成"));
        }else{
          cellList.add(ExcelUtils.getExportCelBean("已撤销"));
        }
        //审核状态
        if(mainResult.getExamineStatus() == 1){
          cellList.add(ExcelUtils.getExportCelBean("未进行"));
        }else if(mainResult.getExamineStatus() == 2){
          cellList.add(ExcelUtils.getExportCelBean("进行中"));
        }else if(mainResult.getExamineStatus() == 3){
          cellList.add(ExcelUtils.getExportCelBean("已完成"));
        }else{
          cellList.add(ExcelUtils.getExportCelBean("已撤销"));
        }
        //备案状态
        if(mainResult.getRecordStatus() == 1){
          cellList.add(ExcelUtils.getExportCelBean("未进行"));
        }else if(mainResult.getRecordStatus() == 2){
          cellList.add(ExcelUtils.getExportCelBean("进行中"));
        }else if(mainResult.getRecordStatus() == 3){
          cellList.add(ExcelUtils.getExportCelBean("已完成"));
        }else{
          cellList.add(ExcelUtils.getExportCelBean("已撤销"));
        }
        //测评状态
        if(mainResult.getEvaluationStatus() == 1){
          cellList.add(ExcelUtils.getExportCelBean("未进行"));
        }else if(mainResult.getEvaluationStatus() == 2){
          cellList.add(ExcelUtils.getExportCelBean("进行中"));
        }else if(mainResult.getEvaluationStatus() == 3){
          cellList.add(ExcelUtils.getExportCelBean("已完成"));
        }else{
          cellList.add(ExcelUtils.getExportCelBean("已撤销"));
        }
        //自查状态
        if(mainResult.getExaminationStatus() == 1){
          cellList.add(ExcelUtils.getExportCelBean("未进行"));
        }else if(mainResult.getExaminationStatus() == 2){
          cellList.add(ExcelUtils.getExportCelBean("进行中"));
        }else if(mainResult.getExaminationStatus() == 3){
          cellList.add(ExcelUtils.getExportCelBean("已完成"));
        }else{
          cellList.add(ExcelUtils.getExportCelBean("已撤销"));
        }
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
   * 下载
   */
  @Override
  public void exportUploadApplicationInfo(
      HttpServletRequest request, HttpServletResponse response,
      String strFilePath)  throws BusinessException{

    String filePath = strFilePath.substring(0, strFilePath.lastIndexOf("/")+1);
    String fileName = strFilePath.substring(strFilePath.lastIndexOf("/")+1,strFilePath.length());
    // 下载文件
    try {
      com.sinopec.smcc.cpro.tools.FileOperateUtil.download(request, response, filePath,
          fileName, "application/x-xls",
          "DATA_INFO_" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx",
          "UTF-8", "ISO8859-1", 102400);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 定级模板导出
   */
  @Override
  public void exportExcelForGradeTemplate() throws BusinessException {
    File f = new File("F:\\桌面应用\\测试\\exportExcelModel.xlsm");
    InputStream inputStream = null;
    HSSFWorkbook xssfWorkbook = null;
    try {
      xssfWorkbook = new HSSFWorkbook(inputStream);
      inputStream = new FileInputStream(f);
    } catch (IOException e) {
      e.printStackTrace();
    }
    HSSFSheet sheet1 = xssfWorkbook.getSheetAt(0);
    for(Row row : sheet1){
      for(Cell hssfCell : row){
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            hssfCell.setCellValue("nihao");
        } else if(hssfCell.getCellType() == Cell.CELL_TYPE_STRING){
          String str = hssfCell.getStringCellValue();
          if(str.equals("Fail")){
              hssfCell.setCellValue("Yes");
          }
          else if(str.equals("Block")){
              hssfCell.setCellValue("NA");
          }else if(str.equals("Warn")){
              hssfCell.setCellValue("NO");
          }
        }
      }
    }
    
  }
}

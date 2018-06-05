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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jacob.com.Dispatch;
import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.mapper.GradingMapper;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.mapper.MainMapper;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.main.utils.ConvertFieldUtil;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.FileOperateUtil;
import com.sinopec.smcc.cpro.tools.JacobExcelTool;
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
  
  @Autowired
  private GradingService gradingServiceImpl;
  
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
  public void exportExcelForGradeTemplate(HttpServletResponse response,String [] systemIds) 
      throws BusinessException {
    File file = new File("F:\\桌面应用\\测试\\exportExcelModel.xlsm");//原文件
    String primaryfilePth = "F:\\桌面应用\\测试\\exportExcelModel.xlsm";//原文件路径
    String filePath = "F:\\桌面应用\\压缩文件\\";//文件生成路径
    String fileCopyPath = "F:\\桌面应用\\复制\\";//复制路径
    String expName = "定级模板"+"_"+DateUtils.getMilliseconds();
    GradingParam gradingParam = new GradingParam();
    gradingParam.setFkSystemIds(systemIds);
    List<GradingListResult> gradingListResultList = 
        this.gradingServiceImpl.queryGradingByParam(gradingParam);
    List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
    File fileCopy = null;
    //Excel宏设置
    JacobExcelTool tool = new JacobExcelTool();
    //打开
    tool.OpenExcel(primaryfilePth,false,false);
    try {
      for (GradingListResult gradingListResult : gradingListResultList) {
        //如果业务信息级别与宏名相同则选中
        if(StringUtils.isNotBlank(gradingListResult.getFkBizSPRankLevel())){
          if(gradingListResult.getFkBizSPRankLevel().equals("101")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox11_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("102")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox12_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("103")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox13_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("104")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox14_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("105")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox15_Click");
          }
        }
        //如果系统服务级别与宏名相同则选中
        if(StringUtils.isNotBlank(gradingListResult.getFkBizSPRankLevel())){
          if(gradingListResult.getFkBizSPRankLevel().equals("201")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox21_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("202")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox22_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("203")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox23_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("204")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox24_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("205")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox25_Click");
          }
        }

        
        //关闭并保存，释放对象
        tool.CloseExcel(true, true);
        //复制
        fileCopy = new File(fileCopyPath + gradingListResult.getSystemName()+"_"+
            DateUtils.getMilliseconds()+".xlsm");
        FileInputStream ins = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream(fileCopy);
        byte[] b = new byte[1024];
        int count=0;
        while((count=ins.read(b))!=-1){
          out.write(b, 0, count);
        }
        srcfile.add(fileCopy);
        ins.close();
        out.close();
      }
      // 将复制后的excel压缩
      FileOperateUtil.createRar(response, filePath, srcfile, expName);
      //压缩后清除复制的excel
      FileOperateUtil.deleteAllFile(fileCopyPath);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}

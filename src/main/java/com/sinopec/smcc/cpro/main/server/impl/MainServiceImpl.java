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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jacob.com.Dispatch;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;
import com.sinopec.smcc.cpro.company.server.CompanyService;
import com.sinopec.smcc.cpro.file.constant.FileConstant;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.mapper.GradingMapper;
import com.sinopec.smcc.cpro.grading.server.AttachMaterialsService;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.main.constant.MainConstant;
import com.sinopec.smcc.cpro.main.constant.WordConstant;
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.mapper.MainMapper;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.main.utils.ConvertFieldUtil;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.server.impl.NodeServiceImpl;
import com.sinopec.smcc.cpro.records.entity.RecordsDetailResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.server.RecordsService;
import com.sinopec.smcc.cpro.system.entity.SystemKeyResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseResult;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.server.SystemCodeService;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.FileOperateUtil;
import com.sinopec.smcc.cpro.tools.JacobExcelTool;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.cpro.tools.excel.ExcelUtils;
import com.sinopec.smcc.cpro.tools.excel.bean.CellBean;
import com.sinopec.smcc.cpro.tools.excel.bean.ExcelBean;
import com.sinopec.smcc.cpro.tools.excel.bean.SheetBean;
import com.sinopec.smcc.cpro.tools.word.WordUtils;

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
  
  @Autowired
  private CompanyService companyServiceImpl;
  
  @Autowired
  private SystemCodeService systemCodeServiceImpl;
  
  @Autowired
  private  SystemService  systemServiceImpl;
 
  @Autowired
  private  SystemMapper  systemMapper;
  
  @Autowired
  private  AttachMaterialsService attachMaterialsServiceImpl;
  
  @Autowired
  private  RecordsService recordsServiceImpl;
  
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  
  @Autowired
  private NodeServiceImpl nodeServiceImpl;
  @Autowired
  private GradingMapper gradingMapper;
  
  
  /**
   * 响应等保列表数据
   */
  @Override
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
   
    //处理高级查询状态
    if(mainParam.getStatus() != null){
      this.handleStatus(mainParam);
    }
    //获得相应列表数据
    List<MainListResult> list = new ArrayList<MainListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null){
      return new PageInfo<>();
    }else{
    	 //初始化分页拦截器
      PageHelper.startPage(mainParam.getCurrentPage(), mainParam.getPageSize(), 
          orderBy.toString());
      
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        list = 
            this.mainMapper.selectAllByMainParam(mainParam);
        break;
      case "2":
        mainParam.setPlateList(organizationApiResult.getNameList());
        list =  
            this.mainMapper.selectAllByMainParam(mainParam);
        break;
      case "3":
        mainParam.setCompanyList(organizationApiResult.getCodeList());
        list =  
            this.mainMapper.selectAllByMainParam(mainParam);
        break;

      default:
        break;
      }
    }
    //装载列表数据
    PageInfo<MainListResult> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }
  
  /**
   * 通过systemId删除系统信息
   */
  @Override
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
  public String exportExcelForMain(HttpServletRequest request) throws BusinessException {    
    String strFilePath = MainConstant.TEMPORARY_FILE_PATH;
    String strFileName = "系统信息导出"+"_"+DateUtils.getStringDateShort()+".xlsx";
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
//    cellList.add(ExcelUtils.getExportCelBean("定级备案信息系统名称"));
//    cellList.add(ExcelUtils.getExportCelBean("合并定级包含的信息系统名称"));
//    cellList.add(ExcelUtils.getExportCelBean("单位名称"));
//    cellList.add(ExcelUtils.getExportCelBean("等保级别"));
//    cellList.add(ExcelUtils.getExportCelBean("定级备案状态"));
//    cellList.add(ExcelUtils.getExportCelBean("定级时间"));
//    cellList.add(ExcelUtils.getExportCelBean("备案时间"));
//    cellList.add(ExcelUtils.getExportCelBean("备案编号"));
//    cellList.add(ExcelUtils.getExportCelBean("受理备案单位"));
//    cellList.add(ExcelUtils.getExportCelBean("变更记录"));
//    cellList.add(ExcelUtils.getExportCelBean("测评时间"));
//    cellList.add(ExcelUtils.getExportCelBean("测评结果"));
//    cellList.add(ExcelUtils.getExportCelBean("自查时间"));
//    cellList.add(ExcelUtils.getExportCelBean("自查结果"));
    
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
//        //系统名称
//        cellList.add(ExcelUtils.getExportCelBean(mainResult.getSystemName()));
//        //如果是合并系统
//        if(mainResult.getFkSystemIsMerge() == 1){
//          //查询子表信息
//          SystemParam systemParam = new SystemParam();
//          systemParam.setSystemId(mainResult.getSystemId());
//          List<SystemSubResult> subSystemList = this.systemMapper.selectEditBySub(systemParam);
//          if(!ObjectUtils.isEmpty(subSystemList)){
//            StringBuffer sb = new StringBuffer ();
//            for(SystemSubResult systemSubResult : subSystemList){
//               sb.append(systemSubResult.getSystemName()+"、");
//            }
//            String str  = sb.toString();
//            //合并定级包含的信息系统名称
//            cellList.add(ExcelUtils.getExportCelBean(str.substring(0,str.length() - 1)));
//          }else{
//            cellList.add(ExcelUtils.getExportCelBean(""));
//          }
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean(""));
//        }
//        //单位名称
//        cellList.add(ExcelUtils.getExportCelBean(mainResult.getCompanyName()));
//        //等保级别
//        cellList.add(ExcelUtils.getExportCelBean(mainResult.getSprankLevel()));
//        //备案状态
//        if(mainResult.getRecordStatus() == 1){
//          cellList.add(ExcelUtils.getExportCelBean("未进行"));
//        }else if(mainResult.getRecordStatus() == 2){
//          cellList.add(ExcelUtils.getExportCelBean("进行中"));
//        }else if(mainResult.getRecordStatus() == 3){
//          cellList.add(ExcelUtils.getExportCelBean("已完成"));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean("已撤销"));
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        //定级时间
//        if(mainResult.getScoreCreateTime() != null){
//          cellList.add(ExcelUtils.getExportCelBean(simpleDateFormat.format(mainResult.getScoreCreateTime())));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean(""));
//        }
//        //备案时间
//        if(mainResult.getRecordCreateTime() != null){
//          cellList.add(ExcelUtils.getExportCelBean(simpleDateFormat.format(mainResult.getRecordCreateTime())));
//        }else{
//          cellList.add(ExcelUtils.getExportCelBean(""));
//        }
//        //单位编码
//        cellList.add(ExcelUtils.getExportCelBean(mainResult.getRecordCode()));
//        //受理备案单位
//        cellList.add(ExcelUtils.getExportCelBean(mainResult.getAcceptCompany()));
//        //变更记录
//        cellList.add(ExcelUtils.getExportCelBean(""));
//        //测评时间
//        cellList.add(ExcelUtils.getExportCelBean(""));
//        //测评结果
//        cellList.add(ExcelUtils.getExportCelBean(""));
//        //自查时间
//        cellList.add(ExcelUtils.getExportCelBean(""));
//        //自查结果
//        cellList.add(ExcelUtils.getExportCelBean(""));
        
        
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
//    try {
//      strFileName = java.net.URLDecoder.decode(strFileName,"UTF-8");
//    } catch (UnsupportedEncodingException e) {
//      e.printStackTrace();
//    } 
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      try {
        strFileName = URLEncoder.encode(strFileName, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
    } 
    return strFilePath + strFileName;
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
          fileName,"UTF-8", "ISO8859-1", 102400);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 定级模板导出
   */
  @Override
  public AttachResult exportExcelForGradeTemplate(HttpServletRequest request,
      HttpServletResponse response,String [] systemIds) throws BusinessException {
    String tempPath = MainConstant.TEMPORARY_EXCEL_FILE_PATH;//模板文件路径
    String filePath = MainConstant.TEMPORARY_FILE_PATH;//文件生成路径
    String temporaryCopyPath = MainConstant.TEMPORARY_COPY_EXCEL_FILE_PATH;//复制模板文件（导出文件）路径
    String expName = "gradingTemp"+"_"+DateUtils.getMilliseconds();
    GradingParam gradingParam = new GradingParam();
    gradingParam.setFkSystemIds(systemIds);
    List<GradingListResult> gradingListResultList = 
        this.gradingServiceImpl.queryGradingByParam(gradingParam);
    List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
    File fileCopy = null;
    try {
      //获取模板文件
      File tempFile = new File(tempPath+MainConstant.GRADING_TEMP_NAME);
      //Excel宏设置
      JacobExcelTool tool = new JacobExcelTool();
      //循环生成选择的系统
      for (int i = 0; i < gradingListResultList.size(); i++) {
        GradingListResult gradingListResult = gradingListResultList.get(i);
        //每次新生成一个Excel，并将模板内容复制给新生成的文件
        String newFile = temporaryCopyPath + gradingListResult.getSystemName()+"_"+
            DateUtils.getMilliseconds()+".xlsm";
        fileCopy = new File(newFile);
        FileInputStream ins = new FileInputStream(tempFile);
        FileOutputStream out = new FileOutputStream(fileCopy);
        byte[] b = new byte[1024];
        int count=0;
        while((count=ins.read(b))!=-1){
          out.write(b, 0, count);
        }
        ins.close();
        out.close();
        
        //打开excel文件，将需要填写的地方填写上
        tool.OpenExcel(fileCopy.getAbsolutePath(),false,false);
        tool.setValue(tool.getCurrentSheet(), "B1", "value",gradingListResult.getSystemName());
        tool.setValue(tool.getCurrentSheet(), "D1", "value",gradingListResult.getStandardizedCode());
        //关闭excel文件
        tool.CloseExcel(true, true);
        //将新生成的文件放入数组，便于打包使用
        srcfile.add(fileCopy);
      }
      /*for (GradingListResult gradingListResult : gradingListResultList) {
        //如果业务信息级别与宏名相同则选中
        if(StringUtils.isNotBlank(gradingListResult.getFkBizSPRankLevel())){
          if(gradingListResult.getFkBizSPRankLevel().equals("1")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox11_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("2")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox12_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("3")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox13_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("4")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox14_Click");
          }
          if(gradingListResult.getFkBizSPRankLevel().equals("5")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox15_Click");
          }
        }
        //如果系统服务级别与宏名相同则选中
        if(StringUtils.isNotBlank(gradingListResult.getFkBizSystemLevel())){
          if(gradingListResult.getFkBizSystemLevel().equals("1")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox21_Click");
          }
          if(gradingListResult.getFkBizSystemLevel().equals("2")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox22_Click");
          }
          if(gradingListResult.getFkBizSystemLevel().equals("3")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox23_Click");
          }
          if(gradingListResult.getFkBizSystemLevel().equals("4")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox24_Click");
          }
          if(gradingListResult.getFkBizSystemLevel().equals("5")){
            tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox25_Click");
          }
        }
        //专家评审情况
        if(gradingListResult.getExpertView() == 1){
          tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox31_Click");
        }
        if(gradingListResult.getExpertView() == 2){
          tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox32_Click");
        }
        //是否有主管部门
        if(gradingListResult.getCompetentIsExisting() == 1){
          tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox32_Click");
        }
        if(gradingListResult.getCompetentIsExisting() == 2){
          tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox33_Click");
        }
        //主管部门审批定级情况
        if(gradingListResult.getCompetentView() == 1){
          tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox31_Click");
        }
        if(gradingListResult.getCompetentView() == 2){
          tool.callMacro("exportExcelModel.xlsm!Sheet2.RedioBox30_Click");
        }
        //是否已上传定级报告(待添加)

        //打开excel文件
        tool.OpenExcel(primaryfilePth,false,false);
        //系统名称
        tool.setValue(tool.getCurrentSheet(), "A6", "value",gradingListResult.getSystemName());
        //系统名称
        tool.setValue(tool.getCurrentSheet(), "A16", "value",gradingListResult.getSystemName());
        //定级时间
        tool.setValue(tool.getCurrentSheet(), "B22", "value",gradingListResult.getRankTime());
        //主管部门名称
        tool.setValue(tool.getCurrentSheet(), "B23", "value",gradingListResult.getCompetentName());
        //主管部门名称
        tool.setValue(tool.getCurrentSheet(), "B25", "value",gradingListResult.getFiller());
        //主管部门名称
        tool.setValue(tool.getCurrentSheet(), "D25", "value",gradingListResult.getFillDate());
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
      }*/
      // 将复制后的excel压缩
      FileOperateUtil.createRar(response, filePath, srcfile, expName);
      //压缩后清除复制的excel
      FileOperateUtil.deleteAllFile(temporaryCopyPath);
      //下载压缩包
      /*FileOperateUtil.download(request, response, 
          filePath+expName+".rar", expName+".rar", "UTF-8", "ISO8859-1", 20480);*/
    }catch (Exception e){
      e.printStackTrace();
    }
    //生成一个附件对象存放生成的文件信息，便于返回前台用于下载
    AttachResult attachResult = new AttachResult();
    attachResult.setUploadUrl(expName+".rar");
    attachResult.setAttachName(expName+".rar");
    return attachResult;
  }
  
  /**
   * 一键下载（表1 单位信息）
   */
  @Override
  public Map<String,Object> tableCompany(HttpServletRequest request,MainParam mainParam) throws BusinessException{
    Map<String,Object> dataMap=new HashMap<String,Object>();
    CompanyParam companyParam = new CompanyParam();
    companyParam.setCompanyId(mainParam.getCompanyId());
    //通过单位ID查询单位信息
    CompanyResult companyResult = companyServiceImpl.queryDetailsCompany(companyParam);
    if(companyResult != null){
      //单位名称
      if(StringUtils.isNotBlank(companyResult.getCompanyName())){
        dataMap.put("companyName", companyResult.getCompanyName());
      }else{
        dataMap.put("companyName","");
      }
      //所属省份
      if(StringUtils.isNotBlank(companyResult.getFkSubordinatePro())){
        SystemCodeParam systemCodeParam = new SystemCodeParam();
        systemCodeParam.setCodeType("21");
        systemCodeParam.setSystemCode(companyResult.getFkSubordinatePro());
        List<SystemCodeListResult> systemCodeListResultList = 
            systemCodeServiceImpl.querySystemCodeForKeySystemCode(systemCodeParam);
        if(!ObjectUtils.isEmpty(systemCodeListResultList)){
          dataMap.put("province", systemCodeListResultList.get(0).getCodeName());
        }
      }else{
        dataMap.put("province", "");
      }
      //邮政编码
      Integer postalCode = companyResult.getPostalCode();
      if(postalCode != null){
        //转为int数组
        String str = String.valueOf(postalCode);
        int[] array = new int[str.length()];
        if(str.length() == 6){
          for(int i=0; i<str.length(); i++){
            array[i] = Integer.parseInt(str.charAt(i) + "");
          }
          for (int i = 0; i < array.length; i++) {
            int j = i+1; 
            dataMap.put("post"+j, array[i]);
          }
        }else{
          dataMap.put("post1", "");dataMap.put("post2", "");dataMap.put("post3", ""); 
          dataMap.put("post4", "");dataMap.put("post5", "");dataMap.put("post6", "");
        }
        
      }else{
        dataMap.put("post1", "");dataMap.put("post2", "");dataMap.put("post3", ""); 
        dataMap.put("post4", "");dataMap.put("post5", "");dataMap.put("post6", "");
      }
      //行政区划代码
      Integer administrativeNum = companyResult.getAdministrativeNum();
      if(administrativeNum != null){
        //转为int数组
        String str = String.valueOf(administrativeNum);
        if(str.length() == 6){
          int[] array = new int[str.length()];
          for(int i=0; i<str.length(); i++){
            array[i] = Integer.parseInt(str.charAt(i) + "");
          }
          for (int i = 0; i < array.length; i++) {
            int j = i+1; 
            dataMap.put("code"+j, array[i]);
          }
        }else{
          dataMap.put("code1","");dataMap.put("code2","");dataMap.put("code3","");
          dataMap.put("code4","");dataMap.put("code5","");dataMap.put("code6","");
        }
      }else{
        dataMap.put("code1","");dataMap.put("code2","");dataMap.put("code3","");
        dataMap.put("code4","");dataMap.put("code5","");dataMap.put("code6","");
      }
      //单位负责人姓名
      if(StringUtils.isNotBlank(companyResult.getCompPrincipalName())){
        dataMap.put("compPrincipalName", companyResult.getCompPrincipalName());
      }else{
        dataMap.put("compPrincipalName", "");
      }
      //单位负责人职务
      if(StringUtils.isNotBlank(companyResult.getCompPrincipalPost())){
        dataMap.put("compPrincipalPost", companyResult.getCompPrincipalPost());
      }else{
        dataMap.put("compPrincipalPost", "");
      }
      //单位负责人办公电话
      if(StringUtils.isNotBlank(companyResult.getCompPrincipalWorkTel())){
        dataMap.put("compPrincipalWorkTel", companyResult.getCompPrincipalWorkTel());
      }else{
        dataMap.put("compPrincipalWorkTel", "");
      }
      //单位负责人电子邮件
      if(StringUtils.isNotBlank(companyResult.getCompPrincipalEm())){
        dataMap.put("compPrincipalEm", companyResult.getCompPrincipalEm());
      }else{
        dataMap.put("compPrincipalEm", "");
      }
      //责任部门联系人姓名
      if(StringUtils.isNotBlank(companyResult.getLdContactName())){
        dataMap.put("ldContactName", companyResult.getLdContactName());
      }else{
        dataMap.put("ldContactName", "");
      }
      //责任部门联系人职务
      if(StringUtils.isNotBlank(companyResult.getLdContactPost())){
        dataMap.put("ldContactPost", companyResult.getLdContactPost());
      }else{
        dataMap.put("ldContactPost", "");
      }
      //责任部门联系人办公电话
      if(StringUtils.isNotBlank(companyResult.getLdContactWorkTel())){
        dataMap.put("ldContactWorkTel", companyResult.getLdContactWorkTel());
      }else{
        dataMap.put("ldContactWorkTel", "");
      }
      //责任部门联系人移动电话
      if(StringUtils.isNotBlank(companyResult.getLdContactPhone())){
        dataMap.put("ldContactPhone", companyResult.getLdContactPhone());
      }else{
        dataMap.put("ldContactPhone", "");
      }
      //责任部门联系人电子邮件
      if(StringUtils.isNotBlank(companyResult.getLdContactEmail())){
        dataMap.put("ldContactEmail", companyResult.getLdContactEmail());
      }else{
        dataMap.put("ldContactEmail", "");
      }
      //责任部门
      if(StringUtils.isNotBlank(companyResult.getrDepartment())){
        dataMap.put("rDepartment", companyResult.getrDepartment());
      }else{
        dataMap.put("rDepartment", "");
      }
      //隶属关系
      String affType = "";
      if(StringUtils.isNotBlank(companyResult.getFkAffiliation())){
        if(companyResult.getFkAffiliation().equals(WordConstant.STR_CENTER)){
          dataMap.put("aff1", "✓");
          affType = "aff1";
        }else if(companyResult.getFkAffiliation().equals(WordConstant.STR_PROVINCE)){
          dataMap.put("aff2", "✓");
          affType = "aff2";
        }else if(companyResult.getFkAffiliation().equals(WordConstant.STR_CITY)){
          dataMap.put("aff3", "✓");
          affType = "aff3";
        }
        else if(companyResult.getFkAffiliation().equals(WordConstant.STR_AREA)){
          dataMap.put("aff4", "✓");
          affType = "aff4";
        }else{
          dataMap.put("aff9", "✓");
          dataMap.put("aff", companyResult.getFkAffiliation());
          affType = "aff9";
        }
      }
      //单位类型
      String companyType = "";
      if(StringUtils.isNotBlank(companyResult.getFkCompanyType())){
        if(companyResult.getFkCompanyType().equals(WordConstant.STR_PARTY)){
          dataMap.put("type1", "✓");
          companyType = "type1";
        }else if(companyResult.getFkCompanyType().equals(WordConstant.STR_GOVERNMENT)){
          dataMap.put("type2", "✓");
          companyType = "type2";
        }else if(companyResult.getFkCompanyType().equals(WordConstant.STR_CAUSE)){
          dataMap.put("type3", "✓");
          companyType = "type3";
        }
        else if(companyResult.getFkCompanyType().equals(WordConstant.STR_ENTERPRISE)){
          dataMap.put("type4", "✓");
          companyType = "type4";
        }else{
          dataMap.put("type9", "✓");
          dataMap.put("type", companyResult.getFkCompanyType());
          companyType = "type9";
        }
      }
      //行业类别
      String indType = "";
      if(StringUtils.isNotBlank(companyResult.getFkIndustryCategory())){
        if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_TELECOM)){
          dataMap.put("ind11", "✓");
          indType = "ind11";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_RADIO)){
          dataMap.put("ind12", "✓");
          indType = "ind12";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_INTERNET)){
          dataMap.put("ind13", "✓");
          indType = "ind13";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_RAILWAY)){
          dataMap.put("ind21", "✓");
          indType = "ind21";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_BANK)){
          dataMap.put("ind22", "✓");
          indType = "ind22";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_CUSTOMS)){
          dataMap.put("ind23", "✓");
          indType = "ind23";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_TAX)){
          dataMap.put("ind24", "✓");
          indType = "ind24";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_CIVIL)){
          dataMap.put("ind25", "✓");
          indType = "ind25";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_POWER)){
          dataMap.put("ind26", "✓");
          indType = "ind26";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_SECURITIES)){
          dataMap.put("ind27", "✓");
          indType = "ind27";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_INSURANCE)){
          dataMap.put("ind28", "✓");
          indType = "ind28";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_NATIONAL)){
          dataMap.put("ind31", "✓");
          indType = "ind31";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_SECURITY)){
          dataMap.put("ind32", "✓");
          indType = "ind32";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_PERSONNEL)){
          dataMap.put("ind33", "✓");
          indType = "ind33";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_FINANCE)){
          dataMap.put("ind34", "✓");
          indType = "ind34";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_AUDIT)){
          dataMap.put("ind35", "✓");
          indType = "ind35";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_TRADE)){
          dataMap.put("ind36", "✓");
          indType = "ind36";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_RESOURCES)){
          dataMap.put("ind37", "✓");
          indType = "ind37";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_ENERGY)){
          dataMap.put("ind38", "✓");
          indType = "ind38";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_TRAFFIC)){
          dataMap.put("ind39", "✓");
          indType = "ind39";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_STATISTICS)){
          dataMap.put("ind40", "✓");
          indType = "ind40";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_BUSINESS)){
          dataMap.put("ind41", "✓");
          indType = "ind41";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_POSTOFFICE)){
          dataMap.put("ind42", "✓");
          indType = "ind42";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_EDUCATION)){
          dataMap.put("ind43", "✓");
          indType = "ind43";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_CULTURE)){
          dataMap.put("ind44", "✓");
          indType = "ind44";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_HYGIENE)){
          dataMap.put("ind45", "✓");
          indType = "ind45";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_AGRICULTURE)){
          dataMap.put("ind46", "✓");
          indType = "ind46";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_CONSERVANCY)){
          dataMap.put("ind47", "✓");
          indType = "ind47";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_INTERNATIONAL)){
          dataMap.put("ind48", "✓");
          indType = "ind48";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_REFORM)){
          dataMap.put("ind49", "✓");
          indType = "ind49";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_SCIENCE)){
          dataMap.put("ind50", "✓");
          indType = "ind50";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_PROPAGANDA)){
          dataMap.put("ind51", "✓");
          indType = "ind51";
        }else if(companyResult.getFkIndustryCategory().equals(WordConstant.STR_SUPERVISE)){
          dataMap.put("ind52", "✓");
          indType = "ind52";
        }else{
          dataMap.put("ind99", "✓");
          indType = "ind99";
          dataMap.put("ind", companyResult.getFkIndustryCategory());
        }
      }
 
      //将隶属关系未选中的值替换为空字符串
      String [] affTypeArray = {"aff1","aff2","aff3","aff4","aff9"};
      for (int i = 0; i < affTypeArray.length; i++) {
        if(StringUtils.isBlank(affType)){
          dataMap.put("aff", "");
        }
        if(!affTypeArray[i].equals(affType)){
          dataMap.put(affTypeArray[i], "□");
        }else{
          if(!affTypeArray[i].equals("aff9")){
            dataMap.put("aff", "");
          }
        }
      }
      //将单位类型未选中的值替换为空字符串
      String [] companyTypeArray = {"type1","type2","type3","type4","type9"};
      for (int i = 0; i < companyTypeArray.length; i++) {
        if(StringUtils.isBlank(companyType)){
          dataMap.put("type", "");
        }
        if(!companyTypeArray[i].equals(companyType)){
          dataMap.put(companyTypeArray[i], "□");
        }else{
          if(!companyTypeArray[i].equals("type9")){
            dataMap.put("type", "");
          }
        }
      }
      //将行业类别未选中的值替换为空字符串
      String [] indTypeArray = {"ind11","ind12","ind13","ind21","ind22","ind23","ind24","ind25",
                                "ind26","ind27","ind28","ind31","ind32","ind33","ind34","ind35",
                                "ind36","ind37","ind38","ind39","ind40","ind41","ind42","ind43",
                                "ind44","ind45","ind46","ind47","ind48","ind48","ind49","ind50",
                                "ind51","ind52","ind99"};
      for (int i = 0; i < indTypeArray.length; i++) {
        if(StringUtils.isBlank(indType)){
          dataMap.put("ind", "");
        }
        if(!indTypeArray[i].equals(indType)){
          dataMap.put(indTypeArray[i], "□");
        }else{
          if(!indTypeArray[i].equals("ind99")){
            dataMap.put("ind", "");
          }
        }
      }
      //信息系统总数
      List<MainListResult> mainListResultList = 
          this.mainMapper.selectAllByMainParam(new MainParam());
      dataMap.put("sn", mainListResultList.size());
      //定级信息系统
      int twoLevel = 0;
      int threeLevel = 0;
      int fourLevel = 0;
      int fiveLevel = 0;
      for(MainListResult mainListResult : mainListResultList){
        if(StringUtils.isNotBlank(mainListResult.getFkSpRanklevel())){
          if(mainListResult.getFkSpRanklevel().equals("302")){
            twoLevel++;
          }
          if(mainListResult.getFkSpRanklevel().equals("303")){
            threeLevel++;
          }
          if(mainListResult.getFkSpRanklevel().equals("304")){
            fourLevel++;
          }
          if(mainListResult.getFkSpRanklevel().equals("305")){
            fiveLevel++;
          }
        }
      }
      dataMap.put("sy2", twoLevel);
      dataMap.put("sy3", threeLevel);
      dataMap.put("sy4", fourLevel);
      dataMap.put("sy5", fiveLevel);
    }else{
      dataMap.put("companyName", "");dataMap.put("province", "");
      dataMap.put("compPrincipalWorkTel", "");dataMap.put("compPrincipalName", "");
      dataMap.put("compPrincipalPost", "");dataMap.put("compPrincipalEm", "");
      dataMap.put("rDepartment", ""); dataMap.put("ldContactName", "");
      dataMap.put("ldContactPost", "");dataMap.put("ldContactWorkTel", "");
      dataMap.put("ldContactPhone", "");dataMap.put("ldContactEmail", "");
      dataMap.put("aff1", "□"); dataMap.put("aff2", "□");
      dataMap.put("aff3", "□"); dataMap.put("aff4", "□");
      dataMap.put("aff9", "□");dataMap.put("aff", "");
      dataMap.put("type1", "□");dataMap.put("type2", "□");
      dataMap.put("type4", "□");dataMap.put("type9", "□");
      dataMap.put("type3", "□");dataMap.put("ind11", "□");
      dataMap.put("ind12", "□");dataMap.put("ind13", "□");
      dataMap.put("ind21", "□");dataMap.put("ind22", "□");
      dataMap.put("ind23", "□");dataMap.put("ind24", "□");
      dataMap.put("ind25", "□");dataMap.put("ind26", "□");
      dataMap.put("ind27", "□");dataMap.put("ind28", "□");
      dataMap.put("ind31", "□");dataMap.put("ind32", "□");
      dataMap.put("ind33", "□");dataMap.put("ind34", "□");
      dataMap.put("ind35", "□");dataMap.put("ind36", "□");
      dataMap.put("ind37", "□");dataMap.put("ind38", "□");
      dataMap.put("ind39", "□");dataMap.put("ind40", "□");
      dataMap.put("ind41", "□");dataMap.put("ind42", "□");
      dataMap.put("ind43", "□");dataMap.put("ind44", "□");
      dataMap.put("ind45", "□");dataMap.put("ind46", "□");
      dataMap.put("ind47", "□");dataMap.put("ind48", "□");
      dataMap.put("ind49", "□");dataMap.put("ind50", "□");
      dataMap.put("ind51", "□");dataMap.put("ind52", "□");
      dataMap.put("ind99", "□");dataMap.put("ind", "");
      dataMap.put("sn", "");dataMap.put("sy2", "");
      dataMap.put("sy3", "");dataMap.put("sy4", "");
      dataMap.put("sy5", "");dataMap.put("post1", ""); 
      dataMap.put("post2", "");dataMap.put("post3", ""); 
      dataMap.put("post4", "");dataMap.put("post5", ""); 
      dataMap.put("post6", "");dataMap.put("code1", ""); 
      dataMap.put("code2", "");dataMap.put("code3", ""); 
      dataMap.put("code4", "");dataMap.put("code5", ""); 
      dataMap.put("code6", ""); dataMap.put("type", ""); 
    }
    Map<String,Object> result = new HashMap<>(); 
    String url = WordUtils.createWord(dataMap,"company.ftl","单位信息");
    String fileName = url.substring(url.lastIndexOf("/")+1,url.length());
    String fileUrl = url.substring(0,url.lastIndexOf("/")+1);
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      try {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
    }
    result.put("url", fileUrl+fileName);
    result.put("tableCompanyResult", dataMap);
  
    
    return result;
  }

  /**
   * 一键下载（表2 系统信息）
   */
  @Override
  public Map<String,Object> tableSystem(HttpServletRequest request,MainParam mainParam) throws BusinessException{
    Map<String,Object> dataMap=new HashMap<String,Object>();
    //查询系统信息
    SystemParam systemParam = new SystemParam();
    systemParam.setSystemId(mainParam.getSystemId());
    SystemResult systemResult = systemServiceImpl.queryDetailsSystem(systemParam);
    if(systemResult != null){
      //系统名称
      if(StringUtils.isNotBlank(systemResult.getGradeRecordSysName())){
        dataMap.put("systemName", systemResult.getGradeRecordSysName());
      }else if(StringUtils.isNotBlank(systemResult.getSystemName())){
        dataMap.put("systemName", systemResult.getSystemName());
      }else{
        dataMap.put("systemName", "");
      }
      //系统编号
      if(StringUtils.isNotBlank(systemResult.getStandardizedCode())){
        dataMap.put("sysCode", systemResult.getStandardizedCode());
      }else{
        dataMap.put("sysCode", "");
      }
      //业务类型
      String busType = "";
      if(StringUtils.isNotBlank(systemResult.getSysBusSituationType())){
        if(systemResult.getSysBusSituationType().equals("生产作业")){
          dataMap.put("bus1", "✓");
          busType = "bus1";
        }else if(systemResult.getSysBusSituationType().equals("指挥调度")){
          dataMap.put("bus2", "✓");
          busType = "bus2";
        }else if(systemResult.getSysBusSituationType().equals("管理控制")){
          dataMap.put("bus3", "✓");
          busType = "bus3";
        }else if(systemResult.getSysBusSituationType().equals("内部办公")){
          dataMap.put("bus4", "✓");
          busType = "bus4";
        }else if(systemResult.getSysBusSituationType().equals("公众服务")){
          dataMap.put("bus5", "✓");
          busType = "bus5";
        }else{
          dataMap.put("bus9", "✓");
          busType = "bus9";
          dataMap.put("bus", systemResult.getSysBusSituationType());
        }
      }
      //业务描述
      if(StringUtils.isNotBlank(systemResult.getSysBusDescription())){
        dataMap.put("description", systemResult.getSysBusDescription());
      }else{
        dataMap.put("description", "");
      }
      //服务范围
      String ranType = "";
      String cope[] = systemResult.getSysServiceSitScope().split("\\^");
      if(StringUtils.isNotBlank(systemResult.getSysServiceSitScope())){
        if(systemResult.getSysServiceSitScope().equals("全国")){
          dataMap.put("ran1", "✓");
          ranType = "ran1";
        }else if(cope[0].equals("跨省（区、市）跨个")){
          dataMap.put("ran2", "✓");
          dataMap.put("rvl2", cope[1]);
          ranType = "ran2";
        }else if(systemResult.getSysServiceSitScope().equals("全省（区、市）")){
          dataMap.put("ran3", "✓");
          ranType = "ran3";
        }else if(cope[0].equals("跨地（区、市）跨个")){
          dataMap.put("ran4", "✓");
          dataMap.put("rvl4", cope[1]);
          ranType = "ran4";
        }else if(systemResult.getSysServiceSitScope().equals("地（市、区）内")){
          dataMap.put("ran5", "✓");
          ranType = "ran5";
        }else{
          dataMap.put("ran99", "✓");
          dataMap.put("ran", systemResult.getSysServiceSitScope());
          ranType = "ran99";
        }
      }
      //服务对象
      String objType = "";
      if(StringUtils.isNotBlank(systemResult.getSysServiceSitObject())){
        if(systemResult.getSysServiceSitObject().equals("单位内部人员")){
          dataMap.put("obj1", "✓");
          objType = "obj1";
        }else if(systemResult.getSysServiceSitObject().equals("社会公众人员")){
          dataMap.put("obj2", "✓");
          objType = "obj2";
        }else if(systemResult.getSysServiceSitObject().equals("两者均包括")){
          dataMap.put("obj3", "✓");
          objType = "obj3";
        }else{
          dataMap.put("obj9", "✓");
          objType = "obj9";
          dataMap.put("obj", systemResult.getSysServiceSitObject());
        }
      }
      //覆盖范围
      String coverType = "";
      if(StringUtils.isNotBlank(systemResult.getNpCoverageRange())){
        if(systemResult.getNpCoverageRange().equals("局域网")){
          dataMap.put("cover1", "✓");
          coverType = "cover1";
        }else if(systemResult.getNpCoverageRange().equals("城域网")){
          dataMap.put("cover2", "✓");
          coverType = "cover2";
        }else if(systemResult.getNpCoverageRange().equals("广域网")){
          dataMap.put("cover3", "✓");
          coverType = "cover3";
        }else{
          dataMap.put("cover9", "✓");
          coverType = "cover9";
          dataMap.put("cover", systemResult.getNpCoverageRange());
        }
      }
      //网络性质
      String natType = "";
      if(StringUtils.isNotBlank(systemResult.getNpNetworkProperties())){
        if(systemResult.getNpNetworkProperties().equals("局域网")){
          dataMap.put("nat1", "✓");
          natType = "nat1";
        }else if(systemResult.getNpNetworkProperties().equals("互联网")){
          dataMap.put("nat2", "✓");
          natType = "nat2";
        }else{
          dataMap.put("nat9", "✓");
          natType = "nat9";
          dataMap.put("nat", systemResult.getNpNetworkProperties());
        }
      }
      //系统互联情况
      String ionType = "";
      if(StringUtils.isNotBlank(systemResult.getInterconnectionSit())){
        if(systemResult.getInterconnectionSit().equals("与其他行业系统连接")){
          dataMap.put("ion1", "✓");
          ionType = "ion1";
        }else if(systemResult.getInterconnectionSit().equals("与本行业其他单位系统连接")){
          dataMap.put("ion2", "✓");
          ionType = "ion2";
        }else if(systemResult.getInterconnectionSit().equals("与本单位其他系统连接")){
          dataMap.put("ion3", "✓");
          ionType = "ion3";
        }else{
          dataMap.put("ion9", "✓");
          ionType = "ion9";
          dataMap.put("ion", systemResult.getInterconnectionSit());
        }
      }
      //关键产品使用情况
      int securityCount = 0;
      int networkCount = 0;
      int systemCount = 0;
      int baseCount = 0;
      int serverCount = 0;
      int productOtherCount = 0;
      if(!ObjectUtils.isEmpty(systemResult.getSystemKeyProducts())){
        for(SystemKeyResult systemRes : systemResult.getSystemKeyProducts()){
          if(systemRes.getFkExaminStatus() != null){
            //安全专用产品
            if(systemRes.getFkExaminStatus() == 1){
              securityCount = 1;
              //数量
              if(StringUtils.isNotBlank(systemRes.getProductsNumber())){
                dataMap.put("nu1", systemRes.getProductsNumber());
              }else{
                dataMap.put("nu1", "");
              }
              //是否使用国产品
              if(StringUtils.isNotBlank(systemRes.getFkNationalIsProducts())){
                if(systemRes.getFkNationalIsProducts().equals("1")){
                  dataMap.put("nan1","✓");
                  dataMap.put("nan2","□");
                  dataMap.put("nan3","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("2")){
                  dataMap.put("nan2","✓");
                  dataMap.put("nan1","□");
                  dataMap.put("nan3","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("3")){
                  dataMap.put("nan3","✓");
                  dataMap.put("nan2","□");
                  dataMap.put("nan1","□");
                }
              }else{
                  dataMap.put("nan1","□");
                  dataMap.put("nan2","□");
                  dataMap.put("nan3","□");
              }
              //使用率
              if(systemRes.getnUseProbability() != null){
                  dataMap.put("nvl1",systemRes.getnUseProbability());
              }else{
                dataMap.put("nvl1","");
              }
            }else if(systemRes.getFkExaminStatus() == 2){//网络产品
              networkCount = 1;
              //数量
              if(StringUtils.isNotBlank(systemRes.getProductsNumber())){
                dataMap.put("nu2", systemRes.getProductsNumber());
              }else{
                dataMap.put("nu2", "");
              }
              //是否使用国产品
              if(StringUtils.isNotBlank(systemRes.getFkNationalIsProducts())){
                if(systemRes.getFkNationalIsProducts().equals("1")){
                  dataMap.put("nan4","✓");
                  dataMap.put("nan5","□");
                  dataMap.put("nan6","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("2")){
                  dataMap.put("nan5","✓");
                  dataMap.put("nan4","□");
                  dataMap.put("nan6","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("3")){
                  dataMap.put("nan6","✓");
                  dataMap.put("nan5","□");
                  dataMap.put("nan4","□");
                }
              }else{
                  dataMap.put("nan4","□");
                  dataMap.put("nan5","□");
                  dataMap.put("nan6","□");
              }
              //使用率
              if(systemRes.getnUseProbability() != null){
                  dataMap.put("nvl2",systemRes.getnUseProbability());
              }else{
                dataMap.put("nvl2","");
              }
            }else if(systemRes.getFkExaminStatus() == 3){//操作系统
              systemCount = 1;
              //数量
              if(StringUtils.isNotBlank(systemRes.getProductsNumber())){
                dataMap.put("nu3", systemRes.getProductsNumber());
              }else{
                dataMap.put("nu3", "");
              }
              //是否使用国产品
              if(StringUtils.isNotBlank(systemRes.getFkNationalIsProducts())){
                if(systemRes.getFkNationalIsProducts().equals("1")){
                  dataMap.put("nan7","✓");
                  dataMap.put("nan8","□");
                  dataMap.put("nan9","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("2")){
                  dataMap.put("nan8","✓");
                  dataMap.put("nan7","□");
                  dataMap.put("nan9","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("3")){
                  dataMap.put("nan9","✓");
                  dataMap.put("nan8","□");
                  dataMap.put("nan7","□");
                }
              }else{
                  dataMap.put("nan7","□");
                  dataMap.put("nan8","□");
                  dataMap.put("nan9","□");
              }
              //使用率
              if(systemRes.getnUseProbability() != null){
                  dataMap.put("nvl3",systemRes.getnUseProbability());
              }else{
                dataMap.put("nvl3","");
              }
            }else if(systemRes.getFkExaminStatus() == 4){//数据库
              baseCount = 1;
              //数量
              if(StringUtils.isNotBlank(systemRes.getProductsNumber())){
                dataMap.put("nu4", systemRes.getProductsNumber());
              }else{
                dataMap.put("nu4", "");
              }
              //是否使用国产品
              if(StringUtils.isNotBlank(systemRes.getFkNationalIsProducts())){
                if(systemRes.getFkNationalIsProducts().equals("1")){
                  dataMap.put("nan10","✓");
                  dataMap.put("nan11","□");
                  dataMap.put("nan12","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("2")){
                  dataMap.put("nan11","✓");
                  dataMap.put("nan10","□");
                  dataMap.put("nan12","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("3")){
                  dataMap.put("nan12","✓");
                  dataMap.put("nan11","□");
                  dataMap.put("nan10","□");
                }
              }else{
                  dataMap.put("nan10","□");
                  dataMap.put("nan11","□");
                  dataMap.put("nan12","□");
              }
              //使用率
              if(systemRes.getnUseProbability() != null){
                  dataMap.put("nvl4",systemRes.getnUseProbability());
              }else{
                dataMap.put("nvl4","");
              }
            }else if(systemRes.getFkExaminStatus() == 5){ //服务器
              serverCount = 1;
              //数量
              if(StringUtils.isNotBlank(systemRes.getProductsNumber())){
                dataMap.put("nu5", systemRes.getProductsNumber());
              }else{
                dataMap.put("nu5", "");
              }
              //是否使用国产品
              if(StringUtils.isNotBlank(systemRes.getFkNationalIsProducts())){
                if(systemRes.getFkNationalIsProducts().equals("1")){
                  dataMap.put("nan13","✓");
                  dataMap.put("nan14","□");
                  dataMap.put("nan15","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("2")){
                  dataMap.put("nan14","✓");
                  dataMap.put("nan13","□");
                  dataMap.put("nan15","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("3")){
                  dataMap.put("nan15","✓");
                  dataMap.put("nan14","□");
                  dataMap.put("nan15","□");
                }
              }else{
                  dataMap.put("nan13","□");
                  dataMap.put("nan14","□");
                  dataMap.put("nan15","□");
              }
              //使用率
              if(systemRes.getnUseProbability() != null){
                  dataMap.put("nvl5",systemRes.getnUseProbability());
              }else{
                dataMap.put("nvl5","");
              }
            }else{ //其他
              productOtherCount = 1;
              dataMap.put("nu", systemRes.getOtherName());
              //数量
              if(StringUtils.isNotBlank(systemRes.getProductsNumber())){
                dataMap.put("nu6", systemRes.getProductsNumber());
              }else{
                dataMap.put("nu6","");
              }
              //是否使用国产品
              if(StringUtils.isNotBlank(systemRes.getFkNationalIsProducts())){
                if(systemRes.getFkNationalIsProducts().equals("1")){
                  dataMap.put("nan16","✓");
                  dataMap.put("nan17","□");
                  dataMap.put("nan18","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("2")){
                  dataMap.put("nan17","✓");
                  dataMap.put("nan16","□");
                  dataMap.put("nan18","□");
                }
                if(systemRes.getFkNationalIsProducts().equals("3")){
                  dataMap.put("nan18","✓");
                  dataMap.put("nan17","□");
                  dataMap.put("nan16","□");
                }
              }else{
                  dataMap.put("nan16","□");
                  dataMap.put("nan17","□");
                  dataMap.put("nan18","□");
              }
              //使用率
              if(systemRes.getnUseProbability() != null){
                  dataMap.put("nvl6",systemRes.getnUseProbability());
              }else{
                dataMap.put("nvl6", "");
              }
            }
          }
        }
      }
      
      
      //系统采用服务情况
      int gradeCount = 0;
      int riskCount = 0;
      int disasterCount = 0;
      int emergencyCount = 0;
      int tntegrateCount = 0;
      int consultationCount = 0;
      int trainCount = 0;
      int serviceOtherCount = 0;
      if(!ObjectUtils.isEmpty(systemResult.getSystemUseServices())){
        for(SystemUseResult systemRes : systemResult.getSystemUseServices()){
          //系统采用服务情况开始
            if(systemRes.getFkProductsType() != null){
              if(systemRes.getFkProductsType() == 1){//等级测评
                gradeCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a1","✓");
                    dataMap.put("a2","□");
                  }else{
                    dataMap.put("a2","✓");
                    dataMap.put("a1","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b1","✓");
                    dataMap.put("b2","□");
                    dataMap.put("b3","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b2","✓");
                    dataMap.put("b1","□");
                    dataMap.put("b3","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b3","✓");
                    dataMap.put("b2","□");
                    dataMap.put("b1","□");
                  }else{
                    dataMap.put("b3","□");
                    dataMap.put("b2","□");
                    dataMap.put("b1","□");
                  }
                }
              }else if(systemRes.getFkProductsType() == 2){
                riskCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a3","✓");
                    dataMap.put("a4","□");
                  }else{
                    dataMap.put("a4","✓");
                    dataMap.put("a3","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b4","✓");
                    dataMap.put("b5","□");
                    dataMap.put("b6","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b5","✓");
                    dataMap.put("b4","□");
                    dataMap.put("b6","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b6","✓");
                    dataMap.put("b5","□");
                    dataMap.put("b4","□");
                  }else{
                    dataMap.put("b6","□");
                    dataMap.put("b5","□");
                    dataMap.put("b4","□");
                  }
                }
              }else if(systemRes.getFkProductsType() == 3){//灾难恢复
                disasterCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse() == 1){
                    dataMap.put("a5","✓");
                    dataMap.put("a6","□");
                  }else{
                    dataMap.put("a6","✓");
                    dataMap.put("a5","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b7","✓");
                    dataMap.put("b8","□");
                    dataMap.put("b9","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b8","✓");
                    dataMap.put("b7","□");
                    dataMap.put("b9","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b9","✓");
                    dataMap.put("b8","□");
                    dataMap.put("b7","□");
                  }else{
                    dataMap.put("b9","□");
                    dataMap.put("b8","□");
                    dataMap.put("b7","□");
                  }
                }
              }else if(systemRes.getFkProductsType() == 4){//应急响应
                emergencyCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a7","✓");
                    dataMap.put("a8","□");
                  }else{
                    dataMap.put("a8","✓");
                    dataMap.put("a7","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b10","✓");
                    dataMap.put("b11","□");
                    dataMap.put("b12","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b11","✓");
                    dataMap.put("b10","□");
                    dataMap.put("b12","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b12","✓");
                    dataMap.put("b11","□");
                    dataMap.put("b10","□");
                  }else{
                    dataMap.put("b12","□");
                    dataMap.put("b11","□");
                    dataMap.put("b10","□");
                  }
                }
              }else if(systemRes.getFkProductsType() == 5){//系统集成
                tntegrateCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a9","✓");
                    dataMap.put("a10","□");
                  }else{
                    dataMap.put("a10","✓");
                    dataMap.put("a9","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b13","✓");
                    dataMap.put("b15","□");
                    dataMap.put("b14","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b14","✓");
                    dataMap.put("b13","□");
                    dataMap.put("b15","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b15","✓");
                    dataMap.put("b14","□");
                    dataMap.put("b13","□");
                  }else{
                    dataMap.put("b15","□");
                    dataMap.put("b14","□");
                    dataMap.put("b13","□");
                  }
                }
              }else if(systemRes.getFkProductsType() == 6){//安全咨询
                consultationCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a11","✓");
                    dataMap.put("a12","□");
                  }else{
                    dataMap.put("a12","✓");
                    dataMap.put("a11","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b16","✓");
                    dataMap.put("b17","□");
                    dataMap.put("b18","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b17","✓");
                    dataMap.put("b18","□");
                    dataMap.put("b16","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b18","✓");
                    dataMap.put("b17","□");
                    dataMap.put("b16","□");
                  }else{
                    dataMap.put("b17","□");
                    dataMap.put("b18","□");
                    dataMap.put("b16","□");
                  }
                }
              }else if(systemRes.getFkProductsType() == 7){//安全培训
                trainCount = 1;
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a13","✓");
                    dataMap.put("a14","□");
                  }else{
                    dataMap.put("a14","✓");
                    dataMap.put("a13","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b19","✓");
                    dataMap.put("b20","□");
                    dataMap.put("b21","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b20","✓");
                    dataMap.put("b19","□");
                    dataMap.put("b21","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b21","✓");
                    dataMap.put("b20","□");
                    dataMap.put("b19","□");
                  }else{
                    dataMap.put("b21","□");
                    dataMap.put("b20","□");
                    dataMap.put("b19","□");
                  }
                }
              }else{
                serviceOtherCount = 1;
                dataMap.put("a",systemRes.getOtherName());
                //是否采用
                if(systemRes.getServiceIsUse() != null){
                  if(systemRes.getServiceIsUse().equals("1")){
                    dataMap.put("a15","✓");
                    dataMap.put("a16","□");
                  }else{
                    dataMap.put("a16","✓");
                    dataMap.put("a15","□");
                  }
                }
                //服务责任方类型
                if(systemRes.getFkResponsibleType() != null){
                  if(systemRes.getFkResponsibleType() == 1){
                    dataMap.put("b22","✓");
                    dataMap.put("b23","□");
                    dataMap.put("b24","□");
                  }else if(systemRes.getFkResponsibleType() == 2){
                    dataMap.put("b23","✓");
                    dataMap.put("b22","□");
                    dataMap.put("b24","□");
                  }else if(systemRes.getFkResponsibleType() == 3){
                    dataMap.put("b24","✓");
                    dataMap.put("b23","□");
                    dataMap.put("b22","□");
                  }else{
                    dataMap.put("b24","□");
                    dataMap.put("b23","□");
                    dataMap.put("b22","□");
                  }
                }
              }
            }
          }
      }
 
     
      //等级测评单位名称
      if(StringUtils.isNotBlank(systemResult.getStandardizedCode())){
        dataMap.put("companyName", systemResult.getStandardizedCode());
      }else{
        dataMap.put("companyName","");
      }
      //何时投入运行使用
      if(systemResult.getWhenInvestmentUse() != null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String date = simpleDateFormat.format(systemResult.getWhenInvestmentUse());
        dataMap.put("date", date);
      }else{
        dataMap.put("date", "");
      }
      //系统是否是分系统
      if(systemResult.getSubIsSystem() != null){
        if(systemResult.getSubIsSystem().equals("1")){
          dataMap.put("bra1", "✓");
          dataMap.put("bra2", "□");
        }else{
          dataMap.put("bra2", "✓");
          dataMap.put("bra1", "□");
        } 
      }else{
        dataMap.put("bra2", "□");
        dataMap.put("bra1", "□");
      }
      dataMap.put("superiorSystemName", "");
      dataMap.put("companySystemName", "");
      
      if(securityCount != 1){
        dataMap.put("nu1", "");
        dataMap.put("nan1", "□");
        dataMap.put("nan2", "□");
        dataMap.put("nan3", "□");
        dataMap.put("nvl1", "");
      }
      if(networkCount != 1){
        dataMap.put("nu2", "");
        dataMap.put("nan4", "□");
        dataMap.put("nan5", "□");
        dataMap.put("nan6", "□");
        dataMap.put("nvl2", "");
      }
      if(systemCount != 1){
        dataMap.put("nu3", "");
        dataMap.put("nan7", "□");
        dataMap.put("nan8", "□");
        dataMap.put("nan9", "□");
        dataMap.put("nvl3", "");
      }
      if(baseCount != 1){
        dataMap.put("nu4", "");
        dataMap.put("nan10", "□");
        dataMap.put("nan11", "□");
        dataMap.put("nan12", "□");
        dataMap.put("nvl4", "");
      }
      if(serverCount != 1){
        dataMap.put("nu5", "");
        dataMap.put("nan13", "□");
        dataMap.put("nan14", "□");
        dataMap.put("nan15", "□");
        dataMap.put("nvl5", "");
      }
      if(productOtherCount != 1){
        dataMap.put("nu6", "");
        dataMap.put("nu", "");
        dataMap.put("nan16", "□");
        dataMap.put("nan17", "□");
        dataMap.put("nan18", "□");
        dataMap.put("nvl6", "");
      }
      if(gradeCount != 1){
        dataMap.put("a1", "□");
        dataMap.put("a2", "□");
        dataMap.put("b1", "□");
        dataMap.put("b2", "□");
        dataMap.put("b3", "□");
      }
      if(riskCount  != 1){
        dataMap.put("a3", "□");
        dataMap.put("a4", "□");
        dataMap.put("b4", "□");
        dataMap.put("b5", "□");
        dataMap.put("b6", "□");
      }
      if(disasterCount  != 1){
        dataMap.put("a5", "□");
        dataMap.put("a6", "□");
        dataMap.put("b7", "□");
        dataMap.put("b8", "□");
        dataMap.put("b9", "□");
      }
      if(emergencyCount  != 1){
        dataMap.put("a7", "□");
        dataMap.put("a8", "□");
        dataMap.put("b10", "□");
        dataMap.put("b11", "□");
        dataMap.put("b12", "□");
      }
      if(tntegrateCount  != 1){
        dataMap.put("a9", "□");
        dataMap.put("a10", "□");
        dataMap.put("b13", "□");
        dataMap.put("b14", "□");
        dataMap.put("b15", "□");
      }
      if(consultationCount  != 1){
        dataMap.put("a11", "□");
        dataMap.put("a12", "□");
        dataMap.put("b16", "□");
        dataMap.put("b17", "□");
        dataMap.put("b18", "□");
      }
      if(trainCount  != 1){
        dataMap.put("a13", "□");
        dataMap.put("a14", "□");
        dataMap.put("b19", "□");
        dataMap.put("b20", "□");
        dataMap.put("b21", "□");
      }
      if(serviceOtherCount  != 1){
        dataMap.put("a15", "□");
        dataMap.put("a16", "□");
        dataMap.put("b22", "□");
        dataMap.put("b23", "□");
        dataMap.put("b24", "□");
        dataMap.put("a", "");
      }
      //将业务类型未选中的值替换为空字符串
      String [] busTypeArray = {"bus1","bus2","bus3","bus4","bus5","bus9"};
      for (int i = 0; i < busTypeArray.length; i++) {
        if(StringUtils.isBlank(busType)){
          dataMap.put("bus", "");
        }
        if(!busTypeArray[i].equals(busType)){
          dataMap.put(busTypeArray[i], "□");
        }else{
          if(!busTypeArray[i].equals("bus9")){
            dataMap.put("bus", "");
          }
        }
      }
      //将服务范围未选中的值替换为空字符串
      String [] ranTypeArray = {"ran1","ran2","ran3","ran4","ran5","ran99"};
      for (int i = 0; i < ranTypeArray.length; i++) {
        if(StringUtils.isBlank(ranType)){
          dataMap.put("ran", "");
          dataMap.put("rvl2", "");
          dataMap.put("rvl4", "");
        }
        if(!ranTypeArray[i].equals(ranType)){
          dataMap.put(ranTypeArray[i], "□");
        }else{
          if(!ranTypeArray[i].equals("ran9")){
            dataMap.put("ran", "");
          }
          if(!ranTypeArray[i].equals("ran2")){
            dataMap.put("rvl2", "");
          }
          if(!ranTypeArray[i].equals("ran4")){
            dataMap.put("rvl4", "");
          }
        }
      }
      //将服务对象未选中的值替换为空字符串
      String [] objTypeArray = {"obj1","obj2","obj3","obj9"};
      for (int i = 0; i < objTypeArray.length; i++) {
        if(StringUtils.isBlank(objType)){
          dataMap.put("obj", "");
        }
        if(!objTypeArray[i].equals(objType)){
          dataMap.put(objTypeArray[i], "□");
        }else{
          if(!objTypeArray[i].equals("obj9")){
            dataMap.put("obj", "");
          }
        }
      }
      //将覆盖范围未选中的值替换为空字符串
      String [] coberTypeArray = {"cover1","cover2","cover3","cover9"};
      for (int i = 0; i < coberTypeArray.length; i++) {
        if(StringUtils.isBlank(coverType)){
          dataMap.put("cover", "");
        }
        if(!coberTypeArray[i].equals(coverType)){
          dataMap.put(coberTypeArray[i], "□");
        }else{
          if(!coberTypeArray[i].equals("cover9")){
            dataMap.put("cover", "");
          }
        }
      }
      //将网络性质未选中的值替换为空字符串
      String [] natTypeArray = {"nat1","nat2","nat9"};
      for (int i = 0; i < natTypeArray.length; i++) {
        if(StringUtils.isBlank(natType)){
          dataMap.put("nat", "");
        }
        if(!natTypeArray[i].equals(natType)){
          dataMap.put(natTypeArray[i], "□");
        }else{
          if(!natTypeArray[i].equals("nat9")){
            dataMap.put("nat", "");
          }
        }
      }
      //将系统互联情况未选中的值替换为空字符串
      String [] ionTypeArray = {"ion1","ion2","ion3","ion9"};
      for (int i = 0; i < ionTypeArray.length; i++) {
        if(StringUtils.isBlank(ionType)){
          dataMap.put("ion", "");
        }
        if(!ionTypeArray[i].equals(ionType)){
          dataMap.put(ionTypeArray[i], "□");
        }else{
          if(!ionTypeArray[i].equals("ion9")){
            dataMap.put("ion", "");
          }
        }
      }
    }else{
      dataMap.put("systemName", "");dataMap.put("sysCode", "");dataMap.put("bus1", "□");
      dataMap.put("bus2", "□");dataMap.put("bus3", "□");dataMap.put("bus4", "□");
      dataMap.put("bus5", "□");dataMap.put("bus9", "□");dataMap.put("bus", "");
      dataMap.put("description", "");dataMap.put("ran1", "□");dataMap.put("ran2", "□");
      dataMap.put("rvl2", "");dataMap.put("ran3", "□"); dataMap.put("ran4", "□");
      dataMap.put("rvl4", "");dataMap.put("ran5", "□");dataMap.put("ran99", "□");
      dataMap.put("ran", "");dataMap.put("obj1", "□");dataMap.put("obj2", "□");
      dataMap.put("obj3", "□");dataMap.put("obj9", "□");dataMap.put("obj", "");
      dataMap.put("cover1", "□");dataMap.put("cover2", "□");dataMap.put("cover3", "□");
      dataMap.put("cover9", "□");dataMap.put("cover", "");dataMap.put("nat1", "□");
      dataMap.put("nat2", "□");dataMap.put("nat9", "□");dataMap.put("ion1", "□");
      dataMap.put("ion2", "□");dataMap.put("ion3", "□");dataMap.put("ion9", "□");
      dataMap.put("ion", "");dataMap.put("nu1", "");dataMap.put("nu2", "");
      dataMap.put("nu3", "");dataMap.put("nu4", "");dataMap.put("nu5", "");
      dataMap.put("nu6", "");dataMap.put("nu", "");dataMap.put("nan1", "□");
      dataMap.put("nan2", "□");dataMap.put("nan3", "□");dataMap.put("nan4", "□");
      dataMap.put("nan5", "□");dataMap.put("nan6", "□");dataMap.put("nan7", "□");
      dataMap.put("nan8", "□");dataMap.put("nan9", "□");dataMap.put("nan10", "□");
      dataMap.put("nan11", "□");dataMap.put("nan12", "□");dataMap.put("nan13", "□");
      dataMap.put("nan14", "□");dataMap.put("nan15", "□");dataMap.put("nan16", "□");
      dataMap.put("nan17", "□");dataMap.put("nan18", "□");dataMap.put("nvl1", "");
      dataMap.put("nvl2", "");dataMap.put("nvl3", "");dataMap.put("nvl4", "");
      dataMap.put("nvl5", "");dataMap.put("nvl6", "");dataMap.put("a1", "□");
      dataMap.put("a2", "□");dataMap.put("a3", "□");dataMap.put("a4", "□");
      dataMap.put("a5", "□");dataMap.put("a6", "□");dataMap.put("a7", "□");
      dataMap.put("a8", "□");dataMap.put("a9", "□");dataMap.put("a10", "□");
      dataMap.put("a11", "□");dataMap.put("a12", "□");dataMap.put("a13", "□");
      dataMap.put("a14", "□");dataMap.put("a15", "□");dataMap.put("a16", "□");
      dataMap.put("b1", "□");dataMap.put("b2", "□");dataMap.put("b3", "□");
      dataMap.put("b4", "□"); dataMap.put("b5", "□");dataMap.put("b6", "□");
      dataMap.put("b7", "□");dataMap.put("b8", "□");dataMap.put("b9", "□");
      dataMap.put("b10", "□");dataMap.put("b11", "□");dataMap.put("b12", "□");
      dataMap.put("b13", "□");dataMap.put("b14", "□"); dataMap.put("b15", "□");
      dataMap.put("b16", "□");dataMap.put("b17", "□");dataMap.put("b18", "□");
      dataMap.put("b19", "□");dataMap.put("b20", "□");dataMap.put("b21", "□");
      dataMap.put("b22", "□");dataMap.put("b23", "□");dataMap.put("b24", "□");
      dataMap.put("companyName", "");dataMap.put("date", "");dataMap.put("bra1", "□");
      dataMap.put("bra2", "□");dataMap.put("superiorSystemName", "");
      dataMap.put("companySystemName", "");
    }
    Map<String,Object> result = new HashMap<>();
    String url = WordUtils.createWord(dataMap,"system.ftl","系统信息");
    String fileName = url.substring(url.lastIndexOf("/")+1,url.length());
    String fileUrl = url.substring(0,url.lastIndexOf("/")+1);
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      try {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
    }
    result.put("url", fileUrl+fileName);
    result.put("tableSystemResult", dataMap);
    return result;
  }

  /**
   * 一键下载（表3 定级信息）
   */
  @Override
  public Map<String,Object> tableGrading(HttpServletRequest request,MainParam mainParam) throws BusinessException {
    Map<String,Object> dataMap=new HashMap<String,Object>();
    //查询定级信息
    GradingParam gradingParam = new GradingParam();
    gradingParam.setFkSystemId(mainParam.getSystemId());
    GradingListResult gradingListResult = gradingServiceImpl.queryEditGrading(gradingParam);
    String selected = "";
    if(gradingListResult != null){
      //确定业务信息安全保护等级
      if(StringUtils.isNotBlank(gradingListResult.getFkBizSPRankLevel())){
        if(gradingListResult.getFkBizSPRankLevel().equals("101")){
          selected = "v1";
        }
        if(gradingListResult.getFkBizSPRankLevel().equals("102")){
          selected = "v2";
        }
        if(gradingListResult.getFkBizSPRankLevel().equals("103")){
          selected = "v3";
        }
        if(gradingListResult.getFkBizSPRankLevel().equals("104")){
          selected = "v4";
        }
        if(gradingListResult.getFkBizSPRankLevel().equals("105")){
          selected = "v5";
        }
      }
      //确定系统服务安全保护等级
      String select = "";
      if(StringUtils.isNotBlank(gradingListResult.getFkBizSystemLevel())){
        if(gradingListResult.getFkBizSystemLevel().equals("201")){
          select = "s1";
        }
        if(gradingListResult.getFkBizSystemLevel().equals("202")){
          select = "s2";
        }
        if(gradingListResult.getFkBizSystemLevel().equals("203")){
          select = "s3";
        }
        if(gradingListResult.getFkBizSystemLevel().equals("204")){
          select = "s4";
        }
        if(gradingListResult.getFkBizSystemLevel().equals("205")){
          select = "s5";
        }
      }
      //信息系统安全保护等级
      String selectType = "";
      if(StringUtils.isNotBlank(gradingListResult.getFkSpRanklevel())){
        if(gradingListResult.getFkSpRanklevel().equals("301")){
          dataMap.put("c1", "✓");
          selectType = "c1";
        }
        if(gradingListResult.getFkSpRanklevel().equals("302")){
          dataMap.put("c2", "✓");
          selectType = "c2";
        }
        if(gradingListResult.getFkSpRanklevel().equals("303")){
          dataMap.put("c3", "✓");
          selectType = "c3";
        }
        if(gradingListResult.getFkSpRanklevel().equals("304")){
          dataMap.put("c4", "✓");
          selectType = "c4";
        }
        if(gradingListResult.getFkSpRanklevel().equals("305")){
          dataMap.put("c5", "✓");
          selectType = "c5";
        }
      }
      //定级时间
      if(gradingListResult.getRankTime() != null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String date = simpleDateFormat.format(gradingListResult.getRankTime());
        dataMap.put("gradingDate", date);
      }else{
        dataMap.put("gradingDate", "");
      }
      //专家评审情况
      if(gradingListResult.getExpertView() != null){
        if(gradingListResult.getExpertView() == 1){
          dataMap.put("review1", "✓");
          dataMap.put("review2", " □");
        }else{
          dataMap.put("review2", "✓");
          dataMap.put("review1", "□");
        }
      }else{
        dataMap.put("review2", "□");
        dataMap.put("review1", "□");
      }
      //是否有主管部门
      if(gradingListResult.getCompetentIsExisting() != null){
        //如果有主管部门则添加主管部门名称和主管部门审批定级情况则填入值，如没有主管部门名称和主管部门审批定级情况为空
        if(gradingListResult.getCompetentIsExisting() == 1){
          dataMap.put("director1", "✓");
          dataMap.put("director2", "□");
          if(StringUtils.isNotBlank(gradingListResult.getCompetentName())){
            dataMap.put("competentName", gradingListResult.getCompetentName());
          }
          if(gradingListResult.getCompetentView() != null){
            if(gradingListResult.getCompetentView() == 1){
              dataMap.put("approval", "✓");
              dataMap.put("approva2", "□");
            }else{
              dataMap.put("approva2", "✓");
              dataMap.put("approval", "□");
            }
          }
        }else{
          dataMap.put("director2", "✓");
          dataMap.put("director1", "□");
          dataMap.put("competentName", " ");
          dataMap.put("approval", "□");
          dataMap.put("approva2", "□");
        }
      }else{
        dataMap.put("director2", "□");
        dataMap.put("director1", "□");
        dataMap.put("competentName", " ");
        dataMap.put("approval", "□");
        dataMap.put("approva2", "□");
      }
      //系统定级报告
      if(StringUtils.isNotBlank(gradingListResult.getGradingReportName())){
        dataMap.put("grading1", "✓");
        dataMap.put("grading2", "□");
        dataMap.put("enc", gradingListResult.getGradingReportName());
      }else{
        dataMap.put("grading2", "✓");
        dataMap.put("grading1", "□");
        dataMap.put("enc", "");
      }
      //填表人
      if(StringUtils.isNotBlank(gradingListResult.getFiller())){
        dataMap.put("addTableName", gradingListResult.getFiller());
      }else{
        dataMap.put("addTableName", "");
      }
      //填日期
      if(gradingListResult.getFillDate() != null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String date = simpleDateFormat.format(gradingListResult.getFillDate());
        dataMap.put("tableDate", date);
      }else{
        dataMap.put("tableDate","");
      }

      dataMap.put("v1", "□");
      dataMap.put("v2", "□");
      dataMap.put("v3", "□");
      dataMap.put("v4", "□");
      dataMap.put("v5", "□");
      dataMap.put("s1", "□");
      dataMap.put("s2", "□");
      dataMap.put("s3", "□");
      dataMap.put("s4", "□");
      dataMap.put("s5", "□");
      
      dataMap.put("snu1", "□");
      dataMap.put("snu2", "□");
      dataMap.put("snu3", "□");
      dataMap.put("snu4", "□");
      dataMap.put("snu5", "□");
      dataMap.put("snu6", "□");
      dataMap.put("snu7", "□");
      dataMap.put("snu8", "□");
      dataMap.put("snu9", "□");
      
      dataMap.put("snb1", "□");
      dataMap.put("snb2", "□");
      dataMap.put("snb3", "□");
      dataMap.put("snb4", "□");
      dataMap.put("snb5", "□");
      dataMap.put("snb6", "□");
      dataMap.put("snb7", "□");
      dataMap.put("snb8", "□");
      dataMap.put("snb9", "□");
      //将业务信息选中的值替换为✓
      if(selected.equals("v1")){
        dataMap.put("v1", "✓");
        dataMap.put("snu1", "✓");
      }else if(selected.equals("v2")){
        dataMap.put("v2", "✓");
        dataMap.put("snu2", "✓");
        dataMap.put("snu3", "✓");
      }else if(selected.equals("v3")){
        dataMap.put("v3", "✓");
        dataMap.put("snu4", "✓");
        dataMap.put("snu5", "✓");
        dataMap.put("snu6", "✓");
      }else if(selected.equals("v4")){
        dataMap.put("v4", "✓");
        dataMap.put("snu7", "✓");
        dataMap.put("snu8", "✓");
      }else{
        dataMap.put("v5", "✓");
        dataMap.put("snu9", "✓");
      }
      //将系统服务信息选中的值替换为✓
      if(select.equals("s1")){
        dataMap.put("s1", "✓");
        dataMap.put("snb1", "✓");
      }else if(select.equals("s2")){
        dataMap.put("s2", "✓");
        dataMap.put("snb2", "✓");
        dataMap.put("snb3", "✓");
      }else if(select.equals("s3")){
        dataMap.put("s3", "✓");
        dataMap.put("snb4", "✓");
        dataMap.put("snb5", "✓");
        dataMap.put("snb6", "✓");
      }else if(select.equals("s4")){
        dataMap.put("s4", "✓");
        dataMap.put("snb7", "✓");
        dataMap.put("snb8", "✓");
      }else{
        dataMap.put("s5", "✓");
        dataMap.put("snb9", "✓");
      }  
      //将业务类型未选中的值替换为空字符串
      String [] selectTypeArray = {"c1","c2","c3","c4","c5"};
      for (int i = 0; i < selectTypeArray.length; i++) {
        if(!selectTypeArray[i].equals(selectType)){
          dataMap.put(selectTypeArray[i], "□");
        }
      }
    }else{
      dataMap.put("snu1", "□");dataMap.put("snu2", "□");dataMap.put("snu3", "□");
      dataMap.put("snu4", "□");dataMap.put("snu5", "□");dataMap.put("snu6", "□");
      dataMap.put("snu7", "□");dataMap.put("snu8", "□");dataMap.put("snu9", "□");
      dataMap.put("snb1", "□");dataMap.put("snb2", "□");dataMap.put("snb3", "□");
      dataMap.put("snb4", "□");dataMap.put("snb5", "□");dataMap.put("snb6", "□");
      dataMap.put("snb7", "□");dataMap.put("snb8", "□");dataMap.put("snb9", "□");
      dataMap.put("v1", "□");dataMap.put("v2", "□");dataMap.put("v3", "□");
      dataMap.put("v4", "□");dataMap.put("v5", "□");dataMap.put("s1", "□");
      dataMap.put("s2", "□");dataMap.put("s3", "□");dataMap.put("s4", "□");
      dataMap.put("s5", "□");dataMap.put("c1", "□");dataMap.put("c2", "□");
      dataMap.put("c3", "□");dataMap.put("c4", "□");dataMap.put("c5", "□");
      dataMap.put("gradingDate", "");dataMap.put("review1", "□");dataMap.put("review2", "□");
      dataMap.put("director1", "□");dataMap.put("director2", "□");dataMap.put("competentName", "");
      dataMap.put("approval", "□");dataMap.put("approva2", "□");dataMap.put("grading1", "□");
      dataMap.put("grading2", "□");dataMap.put("enc", "");dataMap.put("addTableName", "");
      dataMap.put("tableDate", "");
    }
    Map<String,Object> result = new HashMap<>();
    String url = WordUtils.createWord(dataMap,"grading.ftl","定级信息");
    String fileName = url.substring(url.lastIndexOf("/")+1,url.length());
    String fileUrl = url.substring(0,url.lastIndexOf("/")+1);
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      try {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
    }
    result.put("url", fileUrl+fileName);
    result.put("tableGradingResult", dataMap);
    return result;
  }

  /**
   *  一键下载（表4 附件信息）
   */
  @Override
  public Map<String,Object> tableAttach(HttpServletRequest request,MainParam mainParam) throws BusinessException {
    Map<String,Object> dataMap=new HashMap<String,Object>();
    //查询系统信息
    AttachMaterialsParam attachMaterialsParam = new AttachMaterialsParam();
    attachMaterialsParam.setFkSystemId(mainParam.getSystemId());
    List<AttachMaterialsListResult> attachMaterialsListResultList 
      = attachMaterialsServiceImpl.queryEditAttach(attachMaterialsParam);
    if(!ObjectUtils.isEmpty(attachMaterialsListResultList)){
      //计数器
      int topologyCount = 0;
      int organizationCount = 0;
      int implementationCount = 0;
      int licenseCount = 0;
      int evaluationCount = 0;
      int expertCount = 0;
      int directorCount = 0;
      for(AttachMaterialsListResult attachMaterialsListResult : attachMaterialsListResultList){
        //系统拓扑结构及说明
        if(topologyCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("topologyDescription")){
            dataMap.put("has1", "✓");
            dataMap.put("has2", "□");
            dataMap.put("name1", attachMaterialsListResult.getAttachName());
            topologyCount = 1;
          }else{
            dataMap.put("has2", "✓");
            dataMap.put("has1", "□");
            dataMap.put("name1", " ");
          }
        }
        //系统安全组织机构及管理制度
        if(organizationCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("organizationManagement")){
            dataMap.put("has3", "✓");
            dataMap.put("has4", "□");
            dataMap.put("name2", attachMaterialsListResult.getAttachName());
            organizationCount = 1;
          }else{
            dataMap.put("has4", "✓");
            dataMap.put("has3", "□");
            dataMap.put("name2", " ");
          }
        }
        //系统安全保护设施设计实施方案或改建实施方案
        if(implementationCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("implementationPlan")){
            dataMap.put("has5", "✓");
            dataMap.put("has6", "□");
            dataMap.put("name3", attachMaterialsListResult.getAttachName());
            implementationCount = 1;
          }else{
            dataMap.put("has6", "✓");
            dataMap.put("has5", "□");
            dataMap.put("name3", " ");
          }
        }
        //系统使用的安全产品清单及认证、销售许可证明
        if(licenseCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("licenseCertificate")){
            dataMap.put("has7", "✓");
            dataMap.put("has8", "□");
            dataMap.put("name4", attachMaterialsListResult.getAttachName());
            licenseCount = 1;
          }else{
            dataMap.put("has8", "✓");
            dataMap.put("has7", "□");
            dataMap.put("name4", " ");
          }
        }
        //系统等级测评报告
        if(evaluationCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("evaluationPresentation")){
            dataMap.put("has9", "✓");
            dataMap.put("has10", "□");
            dataMap.put("name5", attachMaterialsListResult.getAttachName());
            evaluationCount = 1;
          }else{
            dataMap.put("has10", "✓");
            dataMap.put("has9", "□");
            dataMap.put("name5", " ");
          }
        }
        //专家评审情况
        if(expertCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("expertReview")){
            dataMap.put("has11", "✓");
            dataMap.put("has12", "□");
            dataMap.put("name6", attachMaterialsListResult.getAttachName());
            expertCount = 1;
          }else{
            dataMap.put("has12", "✓");
            dataMap.put("has11", "□");
            dataMap.put("name6", " ");
          }
        }
        //上级主管部门审批意见
        if(directorCount != 1){
          if(attachMaterialsListResult.getFkAttachType().equals("directorOpinion")){
            dataMap.put("has13", "✓");
            dataMap.put("has14", "□");
            dataMap.put("name7", attachMaterialsListResult.getAttachName());
            directorCount = 1;
          }else{
            dataMap.put("has14", "✓");
            dataMap.put("has13", "□");
            dataMap.put("name7", " ");
          }
        }
      }
    }else{
      dataMap.put("has1", "□");dataMap.put("has2", "□");dataMap.put("has3", "□");
      dataMap.put("has4", "□");dataMap.put("has5", "□");dataMap.put("has6", "□");
      dataMap.put("has7", "□");dataMap.put("has8", "□");dataMap.put("has9", "□");
      dataMap.put("has10", "□");dataMap.put("has11", "□");dataMap.put("has12", "□");
      dataMap.put("has13", "□");dataMap.put("has14", "□");dataMap.put("name1", " ");
      dataMap.put("name2", " ");dataMap.put("name3", " ");dataMap.put("name4", " ");
      dataMap.put("name5", " ");dataMap.put("name6", " ");dataMap.put("name7", " ");
    }
    Map<String,Object> result = new HashMap<>();
    String url = WordUtils.createWord(dataMap,"attach.ftl","附件信息");
    String fileName = url.substring(url.lastIndexOf("/")+1,url.length());
    String fileUrl = url.substring(0,url.lastIndexOf("/")+1);
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      try {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
    }
    result.put("url", fileUrl+fileName);
    result.put("tableAttachResult", dataMap);
    return result;
  }
  
  /**
   *  备案表表头相关信息
   */
  @Override
  public Map<String,Object> tableRecord(MainParam mainParam) throws BusinessException {
    Map<String,Object> dataMap=new HashMap<String,Object>();
    //查询备案信息
    RecordsParam recordsParam = new RecordsParam();
    recordsParam.setFkSystemId(mainParam.getSystemId());
    RecordsDetailResult recordsDetailResult = recordsServiceImpl.queryRecordsDetail(recordsParam);
    if(!ObjectUtils.isEmpty(recordsDetailResult)){
      //备案表编号
      if(StringUtils.isNotBlank(recordsDetailResult.getRecordCode())){
        String recordCode = recordsDetailResult.getRecordCode();
        //转为int数组
        int[] array = new int[recordCode.length()];
        for(int i=0; i<recordCode.length(); i++){
          array[i] = Integer.parseInt(recordCode.charAt(i) + "");
        }
        for (int i = 0; i < array.length; i++) {
          int j = i+1; 
          dataMap.put("i"+j, array[i]);
        }
      }else{
        dataMap.put("i1", "");
        dataMap.put("i2", "");
        dataMap.put("i3", "");
        dataMap.put("i4", "");
        dataMap.put("i5", "");
        dataMap.put("i6", "");
        dataMap.put("i7", "");
        dataMap.put("i8", "");
        dataMap.put("i9", "");
        dataMap.put("i10", "");
        dataMap.put("i11", "");
      }
      //备 案 日 期
      if(recordsDetailResult.getRecordDate() != null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String date = simpleDateFormat.format(recordsDetailResult.getRecordDate());
        dataMap.put("recordDate", date);
      }else{
        dataMap.put("recordDate", "");
      }
      //受 理 日 期
      if(recordsDetailResult.getAcceptDate() != null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String date = simpleDateFormat.format(recordsDetailResult.getAcceptDate());
        dataMap.put("ptanceDate", date);
      }else{
        dataMap.put("ptanceDate", "");
      }
    }else{
      dataMap.put("i1", "");
      dataMap.put("i2", "");
      dataMap.put("i3", "");
      dataMap.put("i4", "");
      dataMap.put("i5", "");
      dataMap.put("i6", "");
      dataMap.put("i7", "");
      dataMap.put("i8", "");
      dataMap.put("i9", "");
      dataMap.put("i10", "");
      dataMap.put("i11", "");
      dataMap.put("recordDate", "");
      dataMap.put("ptanceDate", "");
    }
    return dataMap;
  }

  /**
   * 一键下载
   */
  @Override
  public String oneButtonDownloading(HttpServletRequest request,HttpServletResponse response,
      MainParam mainParam) throws BusinessException{
    //表1 单位信息
    Map<String, Object> tableCompanyFilePath = tableCompany(request,mainParam);
    //表2 系统信息
    Map<String, Object> tableSystemFilePath = tableSystem(request,mainParam);
    //表3 定级信息
    Map<String, Object> tableGradingFilePath = tableGrading(request,mainParam);
    //表4 附件信息
    Map<String, Object> tableAttachFilePath = tableAttach(request,mainParam);
    List<File> srcfile = new ArrayList<File>();// 文件list
    File companyFile = null;
    File systemFile = null;
    File gradingFile = null;
    File attachFile = null;
    if(tableCompanyFilePath !=null){
      companyFile = new File(tableCompanyFilePath.get("url").toString());
      srcfile.add(companyFile);
    }
    if(tableSystemFilePath != null){
      systemFile = new File(tableSystemFilePath.get("url").toString());
      srcfile.add(systemFile);
    }
    if(tableGradingFilePath != null){
      gradingFile = new File(tableGradingFilePath.get("url").toString());
      srcfile.add(gradingFile);
    }
    if(tableAttachFilePath != null){
      attachFile = new File(tableAttachFilePath.get("url").toString());
      srcfile.add(attachFile);
    }
    // 压缩文件
    String fileName = "备案表"+"_"+DateUtils.getStringDateShort();
    String filePath = MainConstant.TEMPORARY_FILE_PATH;
    FileOperateUtil.createRar(response, filePath, srcfile,fileName);
    //删除
    if(companyFile != null){
      companyFile.delete();
    }
    if(systemFile != null){
      systemFile.delete();
    }
    if(gradingFile != null){
      gradingFile.delete();
    }
    if(attachFile != null){
      attachFile.delete();
    }
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      try {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
    }
    return  filePath+fileName+ ".rar";
  }

  /**
   * 高级搜索获取系统名称
   */
  @Override
  public List<MainListResult> querySystemName(MainParam mainParam) throws BusinessException {
    
  //获得相应列表数据
    List<MainListResult> list = new ArrayList<MainListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return list;
    }else{
      
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        list = 
            this.mainMapper.selectSystemName(mainParam);
        break;
      case "2":
        mainParam.setPlateList(organizationApiResult.getNameList());
        list =  
            this.mainMapper.selectSystemName(mainParam);
        break;
      case "3":
        mainParam.setCompanyList(organizationApiResult.getCodeList());
        list =  
            this.mainMapper.selectSystemName(mainParam);
        break;

      default:
        break;
      }
    }
    return list;
  }
  
  /**
   *  处理高级查询状态
   */
  @Override
  public void handleStatus(MainParam mainParam) throws BusinessException {
    if(mainParam.getStatus() == 1){
      mainParam.setSystemCodeType("23");
      mainParam.setGradingStatus("1");
    }
    if(mainParam.getStatus() == 2){
      mainParam.setSystemCodeType("23");
      mainParam.setGradingStatus("2");
    }
    if(mainParam.getStatus() == 3){
      mainParam.setSystemCodeType("23");
      mainParam.setGradingStatus("3");
    }
    //状态为未审核，则查询未定级
    if(mainParam.getStatus() == 4 ){
      mainParam.setSystemCodeType("23");
      mainParam.setGradingStatus("1");
    }
    //状态为待审核，查询审核状态为：
    //1：待企业安全员管理审核；
    //2：待总部安全管理员审核；
    if(mainParam.getStatus() == 5){
      mainParam.setFkExaminStatus(1);
    }
    //状态为已审核，查询审核状态为归档
    if(mainParam.getStatus() == 6){
      mainParam.setFkExaminStatus(2);
    }
    //状态为审核未通过，查询审核状态为
    //3：企业安全员管理审核未通过；
    //4：总部安全管理员审核未通过；
    if(mainParam.getStatus() == 7){
      mainParam.setFkExaminStatus(3);
    }
    if(mainParam.getStatus() == 8){
      mainParam.setSystemCodeType("25");
      mainParam.setRecordStatus("1");
    }
    if(mainParam.getStatus() == 9){
      mainParam.setSystemCodeType("25");
      mainParam.setRecordStatus("3");
    }
    if(mainParam.getStatus() == 10){
      mainParam.setSystemCodeType("25");
      mainParam.setRecordStatus("4");
    }
    if( mainParam.getStatus() == 11){
      mainParam.setSystemCodeType("26");
      mainParam.setEvaluationStatus("1");
    }
    if( mainParam.getStatus() == 12){
      mainParam.setSystemCodeType("26");
      mainParam.setEvaluationStatus("3");
    }
    if( mainParam.getStatus() == 13 || mainParam.getStatus() == 14){
      mainParam.setSystemCodeType("27");
      mainParam.setExaminationStatus("1");
    }
    if(mainParam.getStatus() == 14){
      mainParam.setSystemCodeType("27");
      mainParam.setExaminationStatus("3");
    }
  }

  /**
   * 定级模板导入
   */
  @Override
  public void importExcelForGradeTemplate(HttpServletRequest request, 
      MultipartFile file, String userName) throws BusinessException {
    if (file==null|file.getSize()<=1) {
      throw new BusinessException(EnumResult.UNKONW_REQUEST_OBJ_ERROR);
    }
    //控制fileId
    String fileId = Utils.getUuidFor32();
    //初始化文件路径
    String filePath = FileConstant.TEMPORARY_FILE_PATH;
    //获得真实文件名称
    String fileName = file.getOriginalFilename();
    //获取文件后缀
    //String strExtensionName = fileName.substring(fileName.lastIndexOf("."));
    //全路径
    StringBuffer allfilePath = new StringBuffer();
    try {
      FileOperateUtil.uploadFile(file.getBytes(), filePath, 
          fileId+fileName.substring(fileName.lastIndexOf(".")));
      allfilePath.append(filePath).append(fileId).
        append(fileName.substring(fileName.lastIndexOf(".")));
    } catch (IOException e) {
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    //读取导入文件
    List<String[]> dataList = new ArrayList<String[]>();
    try {
      dataList.addAll(ExcelUtils.read(allfilePath.toString(), "sheet1"));
    } catch (Exception e) {
      e.printStackTrace();
      dataList = new ArrayList<String[]>();
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    if(dataList.size()<1){
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    for (String[] strings : dataList) {
      for (int i = 0; i < strings.length; i++) {
        System.out.print("第"+i+"="+strings[i]+" ");
      }
      System.out.println();
    }
    //获取系统信息用于核对信息
    SystemParam systemParam = new SystemParam();
    //如果第一行数据不足4个，即导入模板错误
    if(dataList.get(0).length < 4){
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    String systemName = dataList.get(0)[1];
    systemParam.setSystemName(systemName);
    String standardizedCode = dataList.get(0)[3];
    systemParam.setStandardizedCode(standardizedCode);
    //如果系统名称或标准化代码没填写，导入失败
    if(StringUtils.isBlank(systemName) || StringUtils.isBlank(standardizedCode)){
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    //根据系统名称和标准化代码查询系统信息
    SystemResult systemResult = this.systemMapper.
        selectSystemBySystemNameAndStandardizedCode(systemParam);
    //查不出系统时导入失败
    if(systemResult == null){
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }else{
      //开始查询定级信息
      GradingParam gradingParam = new GradingParam();
      gradingParam.setFkSystemId(systemResult.getSystemId());
      GradingListResult gradingResult = this.gradingMapper.selectDetailsGrading(gradingParam);
      //没有定级信息则添加，有则修改
      gradingParam.setCreateTime(new Date());
      boolean existGradingId = true;
      if(gradingResult == null){
        gradingParam.setGradingId(Utils.getUuidFor32());
        gradingParam.setCreateUserName(userName);
        existGradingId = false;
      }else{
        gradingParam.setGradingId(gradingResult.getGradingId());
      }
      //业务信息-损害客体及损害程度,系统代码表Code多选用‘，’隔开
      String fkBizSPRankDegree = "";
      if("1".equals(dataList.get(2)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10101,";
      }
      if("1".equals(dataList.get(3)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10201,";
      }
      if("1".equals(dataList.get(4)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10202,";
      }
      if("1".equals(dataList.get(5)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10301,";
      }
      if("1".equals(dataList.get(6)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10302,";
      }
      if("1".equals(dataList.get(7)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10303,";
      }
      if("1".equals(dataList.get(8)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10401,";
      }
      if("1".equals(dataList.get(9)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10402,";
      }
      if("1".equals(dataList.get(10)[1])){
        fkBizSPRankDegree = fkBizSPRankDegree + "10501,";
      }
      gradingParam.setFkBizSPRankDegree(fkBizSPRankDegree);
      //业务信息--级别
      String fkBizSPRankLevel = "";
      switch (dataList.get(2)[4]) {
      case "1":
        fkBizSPRankLevel = "101";
        break;
      case "2":
        fkBizSPRankLevel = "102";
        break;
      case "3":
        fkBizSPRankLevel = "103";
        break;
      case "4":
        fkBizSPRankLevel = "104";
        break;
      case "5":
        fkBizSPRankLevel = "105";
        break;
      default:
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }
      gradingParam.setFkBizSPRankLevel(fkBizSPRankLevel);
      //系统服务-损害客体及损害程，系统代码表Code多选用‘，’隔开
      String fkBizSystemDegree = "";
      if("1".equals(dataList.get(12)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20101,";
      }
      if("1".equals(dataList.get(13)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20201,";
      }
      if("1".equals(dataList.get(14)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20202,";
      }
      if("1".equals(dataList.get(15)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20301,";
      }
      if("1".equals(dataList.get(16)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20302,";
      }
      if("1".equals(dataList.get(17)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20303,";
      }
      if("1".equals(dataList.get(18)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20401,";
      }
      if("1".equals(dataList.get(19)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20402,";
      }
      if("1".equals(dataList.get(20)[1])){
        fkBizSystemDegree = fkBizSystemDegree + "20501,";
      }
      gradingParam.setFkBizSystemDegree(fkBizSystemDegree);
      //系统服务--级别
      String fkBizSystemLevel = "";
      switch (dataList.get(11)[4]) {
      case "1":
        fkBizSystemLevel = "201";
        break;
      case "2":
        fkBizSystemLevel = "202";
        break;
      case "3":
        fkBizSystemLevel = "203";
        break;
      case "4":
        fkBizSystemLevel = "204";
        break;
      case "5":
        fkBizSystemLevel = "205";
        break;
      default:
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }
      gradingParam.setFkBizSystemLevel(fkBizSystemLevel);
      //信息系统安全保护等级
      Integer intSPRankLevel = Integer.parseInt(dataList.get(2)[4]);
      Integer intSystemLevel = Integer.parseInt(dataList.get(11)[4]);
      String fkSpRanklevel = intSPRankLevel > intSystemLevel?dataList.get(2)[4]:dataList.get(11)[4];
      switch (fkSpRanklevel) {
      case "1":
        fkSpRanklevel = "301";
        break;
      case "2":
        fkSpRanklevel = "302";
        break;
      case "3":
        fkSpRanklevel = "303";
        break;
      case "4":
        fkSpRanklevel = "304";
        break;
      case "5":
        fkSpRanklevel = "305";
        break;
      default:
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }
      gradingParam.setFkSpRanklevel(fkSpRanklevel);
      //TODO:定级说明描述,模板中没有，故此默认空字符串
      String rankExplainDesc = "";
      gradingParam.setRankExplainDesc(rankExplainDesc);
      //专家评审情况
      String strExpertView = dataList.get(23)[1];
      Integer expertView = Integer.parseInt(strExpertView);
      gradingParam.setExpertView(expertView);
      //定级时间
      String strRankTime = dataList.get(21)[1];
      Date rankTime;
        try {
          rankTime = DateUtils.getDate("yyyy-MM-dd HH:mm:ss", strRankTime);
        } catch (ParseException e) {
          e.printStackTrace();
          throw new BusinessException(EnumResult.UNKONW_ERROR);
        }
      gradingParam.setRankTime(rankTime);
      //是否有主管部门
      String strCompetentIsExisting = dataList.get(21)[3];
      Integer competentIsExisting = Integer.parseInt(strCompetentIsExisting);
      gradingParam.setCompetentIsExisting(competentIsExisting);
      //主管部门名称
      String competentName = dataList.get(22)[1];
      gradingParam.setCompetentName(competentName);
      //主管审批定级情况
      String strCompetentView = dataList.get(23)[1];
      Integer competentView = Integer.parseInt(strCompetentView);
      gradingParam.setCompetentView(competentView);
      //填写人
      String filler = dataList.get(24)[1];
      gradingParam.setFiller(filler);
      //填写时间
      String strFillDate = dataList.get(24)[3];
      Date fillDate;
      try {
        fillDate = DateUtils.getDate("yyyy-MM-dd HH:mm:ss", strFillDate);
      } catch (ParseException e) {
        e.printStackTrace();
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }
      gradingParam.setFillDate(fillDate);
      
      //准备节点信息
      if (existGradingId) {
        //添加节点状态信息
        NodeParam nodeParam = new NodeParam();
        nodeParam.setSystemId(gradingParam.getFkSystemId());
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
      } else {
        //添加节点状态信息
        NodeParam nodeParam = new NodeParam();
        nodeParam.setSystemId(gradingParam.getFkSystemId());
        nodeParam.setOperation("创建");
        nodeParam.setOperationResult("已创建");
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
      //导入数据获取成功，准备保存定级信息
      this.gradingMapper.insertGrading(gradingParam);
    }
  }

  /**
   * 修改申请变更（弹窗）
   */
  @Override
  @Transactional
  public String queryApplicationChange(MainParam mainParam) throws BusinessException{
    if(StringUtils.isBlank(mainParam.getSystemId()))
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    mainMapper.updateApplicationChangeBySystemId(mainParam);
    return mainParam.getSystemId();
  }
  
  /**
   * 修改所有状态
   */
  @Override
  @Transactional
  public void editSystemStatusBySystemId(MainParam mainParam) throws BusinessException{
    if(StringUtils.isBlank(mainParam.getSystemId()))
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    mainMapper.updateSystemStatusBySystemId(mainParam);
  }

  /**
   * 系统等保等级分布统计图
   */
  @Override
  public List<MainListResult> queryGradingStatistics(MainParam mainParam) throws BusinessException {
    //获得相应图表数据
    List<MainListResult> list = new ArrayList<MainListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null || organizationApiResult.getCodeList().size() ==0){
      return null;
    }else{
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        list = 
            this.mainMapper.selectGradingStatistics(mainParam);
        break;
      case "2":
        mainParam.setPlateList(organizationApiResult.getNameList());
        list =  
            this.mainMapper.selectGradingStatistics(mainParam);
        break;
      case "3":
        mainParam.setCompanyList(organizationApiResult.getCodeList());
        list =  
            this.mainMapper.selectGradingStatistics(mainParam);
        break;

      default:
        break;
      }
    }
    return list;
  }

  /**
   * 备案单位数量 统计图
   */
  @Override
  public List<MainListResult> queryRecordsCompanyNum(MainParam mainParam) throws BusinessException {
    return mainMapper.selectRecordsCompanyNum(mainParam);
  }
  /**
   * 退出登陆
   */
  @Override
  public String logout() throws BusinessException {
    
    return "";
  }
}
/**
* Copyright 2017 zsy.com Inc. All Rights Reserved. 
* @Title ExcelUtils.java
* @Package com.anxinyiheng.tools.poi
* @Description: TODO:
* @author eric
* @date 2017年6月22日下午4:54:25
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sinopec.smcc.cpro.tools.excel.bean.CellBean;
import com.sinopec.smcc.cpro.tools.excel.bean.ExcelBean;
import com.sinopec.smcc.cpro.tools.excel.bean.SheetBean;

/**
 * @Title ExcelUtils.java
 * @Package com.anxinyiheng.tools.poi
 * @Description: TODO:
 * @author eric
 * @date 2017年6月22日下午4:54:25
 * @version V1.0
 */
public class ExcelUtils {
  
  /**
   * 读excel文件
   * @author eric
   * @date 2017年6月27日下午5:49:00
   * @param filePath excel文件绝对路径
   * @param sheetName 需要读取excel文件的sheet名
   * @return List<String[]> list为行，数组为列
   * @throws IOException 文件处理失败
   * @throws InvalidFormatException xlsx文件处理失败
   */
  public static List<String[]> read(String filePath,
      String sheetName) throws IOException, InvalidFormatException {
    //验证文件名是否为xls格式
    File file = new File(filePath);
    String fileName = file.getName();
    //验证文件格式
    switch (getFileExtensionName(fileName)) {
    case "xls":
      return openHSSFWorkbook(filePath,sheetName);
    case "xlsx":
      return openXSSFWorkbook(filePath,sheetName);
    default:
      throw new IOException("file is not a excel format!");
    }
  }
  
  /**
   * 创建并写一个excel文件，支持excel xls与xlsx格式
   * @author eric
   * @date 2017年6月27日下午2:03:15
   * @param excelBean excel参数的bean
   * @throws IOException 文件操作错误的抛错
   */
  public static void write(ExcelBean excelBean) throws IOException{
    //验证文件名是否为xls格式
    File file = new File(excelBean.getFilePath());
    String fileName = file.getName();
    //验证文件格式
    switch (getFileExtensionName(fileName)) {
    case "xls":
      createHSSFWorkbook(excelBean);
      return;
    case "xlsx":
      createXSSFWorkbook(excelBean);
      return;
    default:
      throw new IOException("file is not a excel format!");
    }
  }
  
  /**
   * 创建97-03excel工作簿
   * @author eric
   * @date 2017年6月27日下午2:02:32
   * @param excelBean excel参数的bean
   * @throws IOException 文件操作错误的抛错
   */
  private static void createHSSFWorkbook(ExcelBean excelBean) throws IOException {
    List<SheetBean> sheetList = excelBean.getSheetList();
    if (sheetList==null||sheetList.size()==0) {
      throw new IOException("create file failed,sheetList is null or is empty!");
    }
    int sheetListSize = sheetList.size();
    //创建workBook
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
    SheetBean sheetBean;
    HSSFSheet hssfSheet;
    
    for (int i = 0; i < sheetListSize; i++) {
      //创建sheet
      sheetBean = sheetList.get(i);
      if (sheetBean.getSheetName()==null) {
        sheetBean.setSheetName("Sheet"+(i+1));
      }
      hssfSheet = hssfWorkbook.createSheet(sheetBean.getSheetName());

      //创建头信息
      if (sheetBean.getHeaderBean()!=null) {
        
      }
      
      //设置固定行列
      if ((sheetBean.getRowFixed()!=null&&sheetBean.getRowFixed()>0)
          ||(sheetBean.getColumnFixed()!=null&&sheetBean.getColumnFixed()>0)) {
        //列，行初始化
        if (sheetBean.getRowFixed()==null) {
          sheetBean.setRowFixed(0);
        }
        if (sheetBean.getColumnFixed()==null) {
          sheetBean.setColumnFixed(0);
        }
        hssfSheet.createFreezePane(sheetBean.getColumnFixed(), sheetBean.getRowFixed());
      }
      
      //设置行高默认值
      if (sheetBean.getDefaultRowHeight()!=null&&sheetBean.getDefaultRowHeight()>0) {
        hssfSheet.setDefaultRowHeight((short)(20*sheetBean.getDefaultRowHeight()));
      }
      
      //设置默认列宽
      if (sheetBean.getDefaultColumnWidth()!=null&&sheetBean.getDefaultColumnWidth()>0) {
        hssfSheet.setDefaultColumnWidth((short)(sheetBean.getDefaultColumnWidth()));
      }
      
      //设置是否显示网格
      if (sheetBean.isDisplayGridlines()) {
        hssfSheet.setDisplayGridlines(sheetBean.isDisplayGridlines());
      }
      
      //设置合并单元格
      if (sheetBean.getMergedRegionList()!=null&&sheetBean.getMergedRegionList().size()>0) {
        List<Integer[]> mergedRegionList = sheetBean.getMergedRegionList();
        int mergedRegionListSize = mergedRegionList.size();
        Integer[] isMergedRegion  = null;
        for (int j = 0; j < mergedRegionListSize; j++) {
          isMergedRegion = mergedRegionList.get(j);
          if (isMergedRegion !=null&&isMergedRegion.length==4) {
            hssfSheet.addMergedRegion(new CellRangeAddress(
                isMergedRegion[0], isMergedRegion[1], isMergedRegion[2], isMergedRegion[3]));
          }
        }
      }
      
      //填充单元格数据
      List<List<CellBean>> dataList = sheetBean.getDataList();
      if (dataList!=null&&dataList.size()>0) {
        int dataListSize = dataList.size();
        HSSFRow row;
        
        CellBean cellBean;
        CellStyle cellStyle;
        Short color = null;
        HSSFFont hssfFont;
        Cell cell;
        
        DataFormat format;
        for (int j = 0; j < dataListSize; j++) {
          List<CellBean> cellList = dataList.get(j);
          //建立行
          row = hssfSheet.createRow(j);
          if (cellList!=null&&cellList.size()>0) {
            int cellListSize = cellList.size();
            for (int k = 0; k < cellListSize; k++) {
              cellBean = cellList.get(k);
              cellStyle = hssfWorkbook.createCellStyle();
              //设置边线样式
              //上边线
              BorderStyle borderTop;
              if (cellBean.getBorderTop()!=null
                  &&( borderTop = getHssfBorderStyle(cellBean.getBorderTop()))!=null) {
                cellStyle.setBorderTop(borderTop);
              }
              //下边线
              BorderStyle borderBottom;
              if (cellBean.getBorderBottom()!=null
                  &&( borderBottom = getHssfBorderStyle(cellBean.getBorderBottom()))!=null) {
                cellStyle.setBorderBottom(borderBottom);
              }
              //左边线
              BorderStyle borderLeft;
              if (cellBean.getBorderLeft()!=null
                  &&( borderLeft = getHssfBorderStyle(cellBean.getBorderLeft()))!=null) {
                cellStyle.setBorderLeft(borderLeft);
              }
              //右边线
              BorderStyle borderRight;
              if (cellBean.getBorderRight()!=null
                  &&( borderRight = getHssfBorderStyle(cellBean.getBorderRight()))!=null) {
                cellStyle.setBorderRight(borderRight);
              }
              //设置边线颜色
              //上边线
              color = null;
              if (cellBean.getBorderTopColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderTopColor()))!=null) {
                cellStyle.setTopBorderColor(color);
              }
              color = null;
              //下边线
              if (cellBean.getBorderBottomColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderBottomColor()))!=null) {
                cellStyle.setBottomBorderColor(color);
              }
              //左边线
              color = null;
              if (cellBean.getBorderLeftColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderLeftColor()))!=null) {
                cellStyle.setLeftBorderColor(color);
              }
              //右边线
              color = null;
              if (cellBean.getBorderRightColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderRightColor()))!=null) {
                cellStyle.setRightBorderColor(color);
              }
              
              //设置单元格背景
              //设置样式
              FillPatternType fillPatternType;
              if (cellBean.getFillPattern()!=null
                  &&(fillPatternType=getFillPatternType(cellBean.getFillPattern()))!=null) {
                cellStyle.setFillPattern(fillPatternType);
              }
              //设置前景颜色
              color = null;
              if (cellBean.getFillForegroundColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getFillForegroundColor()))!=null) {
                cellStyle.setFillForegroundColor(color);
              }
              //设置背景颜色
              color = null;
              if (cellBean.getFillBackgroundColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getFillBackgroundColor()))!=null) {
                cellStyle.setFillBackgroundColor(color);
              }
              
              //设置文字
              hssfFont = hssfWorkbook.createFont();
              //是否为斜体
              if (cellBean.isItalic()) {
                hssfFont.setItalic(cellBean.isItalic());
              }
              //是否为粗体
              if (cellBean.isBold()) {
                hssfFont.setBold(cellBean.isBold());
              }
              //字体名（宋体、楷体等）
              if (cellBean.getFontName()!=null) {
                hssfFont.setFontName(cellBean.getFontName());
              }
              //文字大小（字号）
              if (cellBean.getFontHeightInPoints()!=null&&cellBean.getFontHeightInPoints()>0) {
                hssfFont.setFontHeightInPoints(cellBean.getFontHeightInPoints());
              }
              //文字颜色
              color = null;
              if (cellBean.getFontColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getFontColor()))!=null) {
                hssfFont.setColor(color);
              }
              //删除线
              if (cellBean.isStrikeout()) {
                hssfFont.setStrikeout(cellBean.isStrikeout());
              }
              //下划线
              Byte underline;
              if (cellBean.getUnderline()!=null
                  &&(underline=getHssfUnderline(cellBean.getUnderline()))!=null) {
                hssfFont.setUnderline(underline);
              }
              //文字对齐方式
              Short typeOffset;
              if (cellBean.getTypeOffset()!=null
                  &&(typeOffset=getHssfTypeOffset(cellBean.getTypeOffset()))!=null) {
                hssfFont.setTypeOffset(typeOffset);
              }
              //装载字体
              cellStyle.setFont(hssfFont);
              
              //单元格对齐方式
              //左右对齐
              HorizontalAlignment alignment;
              if (cellBean.getAlignment()!=null
                  &&(alignment=getHssfHorizontalAlignment(cellBean.getAlignment()))!=null) {
                cellStyle.setAlignment(alignment);
              }
              //上下对齐
              VerticalAlignment verticalAlignment;
              if (cellBean.getVerticalAlignment()!=null
                  &&(verticalAlignment=getHssfVerticalAlignment(
                      cellBean.getVerticalAlignment()))!=null) {
                cellStyle.setVerticalAlignment(verticalAlignment);
              }
              
              //是否自动换行
              if (cellBean.isWrapText()) {
                cellStyle.setWrapText(cellBean.isWrapText());
              }
              
              //格式化
              if (cellBean.getDataFormat()!=null) {
                format = hssfWorkbook.createDataFormat();
                cellStyle.setDataFormat(format.getFormat("#,##0.00"));
              }
              
              //创建单元格
              cell = row.createCell(k);
              cell.setCellStyle(cellStyle);
              //将数据装载到cell内
              if (cellBean.getValue()!=null&&!"".equals(cellBean.getValue())) {
                setCellValueForParamValue(cell,cellBean.getValue());
              }
              
              //清空数据
              cellBean=null;
              cellStyle=null;
              color=null;
              fillPatternType=null;
              hssfFont=null;
              verticalAlignment=null;
              alignment=null;
              typeOffset=null;
              underline=null;
              borderRight=null;
              borderLeft=null;
              borderBottom=null;
              borderTop=null;
              format=null;
              cell=null;
            }
          }
          //清理数据
          row = null;
        }
      }
      
      //设置行距
      Map<Integer, Short> rowHeightMap = sheetBean.getRowHeightMap();
      Short rowValue;
      for (Integer row : rowHeightMap.keySet()) {
        rowValue = rowHeightMap.get(row);
        if (row!=null&&row>=0&&rowValue!=null&&rowValue>0) {
          if (hssfSheet.getRow(row)==null) {
            hssfSheet.createRow(row);
          }
          hssfSheet.getRow(row).setHeight((short)(20 * rowValue));
        }
      }
      
      //设置宽距
      Map<Integer, Short> columnWidthMap = sheetBean.getColumnWidthMap();
      Short cellValue;
      for (Integer cell : columnWidthMap.keySet()) {
        cellValue = columnWidthMap.get(cell);
        if (cell!=null&&cell>=0&&cellValue!=null&&cellValue>0) {
          hssfSheet.setColumnWidth(cell, cellValue * 256+184);
        }
      }
      //清理数据
      sheetBean = null;
      hssfSheet = null;
    }
    
    //保存为Excel文件
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(excelBean.getFilePath());
      hssfWorkbook.write(out);
    } catch (IOException e) {
      throw new IOException(e);
    } finally {
      try {
        if (out != null) {
          out.close();
        }
        if (hssfWorkbook != null) {
          hssfWorkbook.close();
        }
      } catch (IOException e) {
        throw new IOException(e);
      }
    }
  }
  
  /**
   * 创建07-13excel工作簿
   * @author eric
   * @date 2017年6月27日下午4:19:29
   * @param excelBean excel参数的bean
   * @throws IOException 文件操作错误的抛错
   */
  private static void createXSSFWorkbook(ExcelBean excelBean) throws IOException {
    List<SheetBean> sheetList = excelBean.getSheetList();
    if (sheetList==null||sheetList.size()==0) {
      throw new IOException("create file failed,sheetList is null or is empty!");
    }
    int sheetListSize = sheetList.size();
    //创建workBook
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
    SheetBean sheetBean;
    XSSFSheet xssfSheet;
    
    for (int i = 0; i < sheetListSize; i++) {
      //创建sheet
      sheetBean = sheetList.get(i);
      if (sheetBean.getSheetName()==null) {
        sheetBean.setSheetName("Sheet"+(i+1));
      }
      xssfSheet = xssfWorkbook.createSheet(sheetBean.getSheetName());

      //创建头信息
      if (sheetBean.getHeaderBean()!=null) {
        
      }
      
      //设置固定行列
      if ((sheetBean.getRowFixed()!=null&&sheetBean.getRowFixed()>0)
          ||(sheetBean.getColumnFixed()!=null&&sheetBean.getColumnFixed()>0)) {
        //列，行初始化
        if (sheetBean.getRowFixed()==null) {
          sheetBean.setRowFixed(0);
        }
        if (sheetBean.getColumnFixed()==null) {
          sheetBean.setColumnFixed(0);
        }
        xssfSheet.createFreezePane(sheetBean.getColumnFixed(), sheetBean.getRowFixed());
      }
      
      //设置行高默认值
      if (sheetBean.getDefaultRowHeight()!=null&&sheetBean.getDefaultRowHeight()>0) {
        xssfSheet.setDefaultRowHeight((short)(20*sheetBean.getDefaultRowHeight()));
      }
      
      //设置默认列宽
      if (sheetBean.getDefaultColumnWidth()!=null&&sheetBean.getDefaultColumnWidth()>0) {
//        sheet.setDefaultColumnWidth((short)(184+256*sheetBean.getDefaultColumnWidth()));
        xssfSheet.setDefaultColumnWidth((short)(sheetBean.getDefaultColumnWidth()));
      }
      
      //设置是否显示网格
      if (sheetBean.isDisplayGridlines()) {
        xssfSheet.setDisplayGridlines(sheetBean.isDisplayGridlines());
      }
      
      //设置合并单元格
      if (sheetBean.getMergedRegionList()!=null&&sheetBean.getMergedRegionList().size()>0) {
        List<Integer[]> mergedRegionList = sheetBean.getMergedRegionList();
        int mergedRegionListSize = mergedRegionList.size();
        Integer[] isMergedRegion  = null;
        for (int j = 0; j < mergedRegionListSize; j++) {
          isMergedRegion = mergedRegionList.get(j);
          if (isMergedRegion !=null&&isMergedRegion.length==4) {
            xssfSheet.addMergedRegion(new CellRangeAddress(
                isMergedRegion[0], isMergedRegion[1], isMergedRegion[2], isMergedRegion[3]));
          }
        }
      }
      
      //填充单元格数据
      List<List<CellBean>> dataList = sheetBean.getDataList();
      if (dataList!=null&&dataList.size()>0) {
        int dataListSize = dataList.size();
        XSSFRow row;
        
        CellBean cellBean;
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        CellStyle cellStyleTemp = xssfWorkbook.createCellStyle();
        Short color = null;
        XSSFFont hssfFont;
        Cell cell;
        
        DataFormat format;
        for (int j = 0; j < dataListSize; j++) {
          List<CellBean> cellList = dataList.get(j);
          //建立行
          row = xssfSheet.createRow(j);
          if (cellList!=null&&cellList.size()>0) {
            int cellListSize = cellList.size();
            for (int k = 0; k < cellListSize; k++) {
              cellBean = cellList.get(k);
              cellStyle.cloneStyleFrom(cellStyleTemp);
              //设置边线样式
              //上边线
              BorderStyle borderTop;
              if (cellBean.getBorderTop()!=null
                  &&( borderTop = getHssfBorderStyle(cellBean.getBorderTop()))!=null) {
                cellStyle.setBorderTop(borderTop);
              }
              //下边线
              BorderStyle borderBottom;
              if (cellBean.getBorderBottom()!=null
                  &&( borderBottom = getHssfBorderStyle(cellBean.getBorderBottom()))!=null) {
                cellStyle.setBorderBottom(borderBottom);
              }
              //左边线
              BorderStyle borderLeft;
              if (cellBean.getBorderLeft()!=null
                  &&( borderLeft = getHssfBorderStyle(cellBean.getBorderLeft()))!=null) {
                cellStyle.setBorderLeft(borderLeft);
              }
              //右边线
              BorderStyle borderRight;
              if (cellBean.getBorderRight()!=null
                  &&( borderRight = getHssfBorderStyle(cellBean.getBorderRight()))!=null) {
                cellStyle.setBorderRight(borderRight);
              }
              //设置边线颜色
              //上边线
              color = null;
              if (cellBean.getBorderTopColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderTopColor()))!=null) {
                cellStyle.setTopBorderColor(color);
              }
              color = null;
              //下边线
              if (cellBean.getBorderBottomColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderBottomColor()))!=null) {
                cellStyle.setBottomBorderColor(color);
              }
              //左边线
              color = null;
              if (cellBean.getBorderLeftColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderLeftColor()))!=null) {
                cellStyle.setLeftBorderColor(color);
              }
              //右边线
              color = null;
              if (cellBean.getBorderRightColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getBorderRightColor()))!=null) {
                cellStyle.setRightBorderColor(color);
              }
              
              //设置单元格背景
              //设置样式
              FillPatternType fillPatternType;
              if (cellBean.getFillPattern()!=null
                  &&(fillPatternType=getFillPatternType(cellBean.getFillPattern()))!=null) {
                cellStyle.setFillPattern(fillPatternType);
              }
              //设置前景颜色
              color = null;
              if (cellBean.getFillForegroundColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getFillForegroundColor()))!=null) {
                cellStyle.setFillForegroundColor(color);
              }
              //设置背景颜色
              color = null;
              if (cellBean.getFillBackgroundColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getFillBackgroundColor()))!=null) {
                cellStyle.setFillBackgroundColor(color);
              }
              
              //设置文字
              hssfFont = xssfWorkbook.createFont();
              //是否为斜体
              if (cellBean.isItalic()) {
                hssfFont.setItalic(cellBean.isItalic());
              }
              //是否为粗体
              if (cellBean.isBold()) {
                hssfFont.setBold(cellBean.isBold());
              }
              //字体名（宋体、楷体等）
              if (cellBean.getFontName()!=null) {
                hssfFont.setFontName(cellBean.getFontName());
              }
              //文字大小（字号）
              if (cellBean.getFontHeightInPoints()!=null&&cellBean.getFontHeightInPoints()>0) {
                hssfFont.setFontHeightInPoints(cellBean.getFontHeightInPoints());
              }
              //文字颜色
              color = null;
              if (cellBean.getFontColor()!=null
                  &&(color = getHssfIndexedColors(cellBean.getFontColor()))!=null) {
                hssfFont.setColor(color);
              }
              //删除线
              if (cellBean.isStrikeout()) {
                hssfFont.setStrikeout(cellBean.isStrikeout());
              }
              //下划线
              Byte underline;
              if (cellBean.getUnderline()!=null
                  &&(underline=getHssfUnderline(cellBean.getUnderline()))!=null) {
                hssfFont.setUnderline(underline);
              }
              //文字对齐方式
              Short typeOffset;
              if (cellBean.getTypeOffset()!=null
                  &&(typeOffset=getHssfTypeOffset(cellBean.getTypeOffset()))!=null) {
                hssfFont.setTypeOffset(typeOffset);
              }
              //装载字体
              cellStyle.setFont(hssfFont);
              
              //单元格对齐方式
              //左右对齐
              HorizontalAlignment alignment;
              if (cellBean.getAlignment()!=null
                  &&(alignment=getHssfHorizontalAlignment(cellBean.getAlignment()))!=null) {
                cellStyle.setAlignment(alignment);
              }
              //上下对齐
              VerticalAlignment verticalAlignment;
              if (cellBean.getVerticalAlignment()!=null
                  &&(verticalAlignment=getHssfVerticalAlignment(
                      cellBean.getVerticalAlignment()))!=null) {
                cellStyle.setVerticalAlignment(verticalAlignment);
              }
              
              //是否自动换行
              if (cellBean.isWrapText()) {
                cellStyle.setWrapText(cellBean.isWrapText());
              }
              
              //格式化
              if (cellBean.getDataFormat()!=null) {
                format = xssfWorkbook.createDataFormat();
                cellStyle.setDataFormat(format.getFormat(cellBean.getDataFormat()));
              }
              
              //创建单元格
              cell = row.createCell(k);
              cell.setCellStyle(cellStyle);
              //将数据装载到cell内
              if (cellBean.getValue()!=null&&!"".equals(cellBean.getValue())) {
                setCellValueForParamValue(cell,cellBean.getValue());
              }
              
              //清空数据
              cellBean=null;
              color=null;
              fillPatternType=null;
              hssfFont=null;
              verticalAlignment=null;
              alignment=null;
              typeOffset=null;
              underline=null;
              borderRight=null;
              borderLeft=null;
              borderBottom=null;
              borderTop=null;
              format=null;
              cell=null;
            }
          }
          //清理数据
          row = null;
        }
      }
      
      //设置行距
      Map<Integer, Short> rowHeightMap = sheetBean.getRowHeightMap();
      Short rowValue;
      for (Integer row : rowHeightMap.keySet()) {
        rowValue = rowHeightMap.get(row);
        if (row!=null&&row>=0&&rowValue!=null&&rowValue>0) {
          if (xssfSheet.getRow(row)==null) {
            xssfSheet.createRow(row);
          }
          xssfSheet.getRow(row).setHeight((short)(20 * rowValue));
        }
      }
      
      Map<Integer, Short> columnWidthMap = sheetBean.getColumnWidthMap();
      Short cellValue;
      for (Integer cell : columnWidthMap.keySet()) {
        cellValue = columnWidthMap.get(cell);
        if (cell!=null&&cell>=0&&cellValue!=null&&cellValue>0) {
          xssfSheet.setColumnWidth(cell, cellValue * 256+184);
        }
      }
      //清理数据
      sheetBean = null;
      xssfSheet = null;
    }
    
    //保存为Excel文件
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(excelBean.getFilePath());
      xssfWorkbook.write(out);
    } catch (IOException e) {
      throw new IOException(e);
    } finally {
      try {
        if (out != null) {
          out.close();
        }
        if (xssfWorkbook != null) {
          xssfWorkbook.close();
        }
      } catch (IOException e) {
        throw new IOException(e);
      }
    }
  }
  
  /**
   * 获得xls格式excel其中一个sheet内的数据
   * @author eric
   * @date 2017年6月27日下午5:12:25
   * @param filePath excel文件绝对路径
   * @param sheetName sheet的名字
   * @return List<String[]> list为行，数组为列
   * @throws IOException 文件处理失败的抛错
   */
  private static List<String[]> openHSSFWorkbook(String filePath,
      String sheetName) throws IOException {
    POIFSFileSystem pfs = null;
    HSSFWorkbook hssfWorkbook = null;
    try {
      pfs = new POIFSFileSystem(new FileInputStream(filePath));
      hssfWorkbook = new HSSFWorkbook(pfs);
      HSSFSheet hssfSheet = hssfWorkbook.getSheet(sheetName);
      
      if (hssfSheet == null) {
        hssfSheet = hssfWorkbook.getSheetAt(1);
      }
      
      List<String[]> dataList = new ArrayList<String[]>();
      //获得sheet的行数
      int rows = hssfSheet.getLastRowNum()+1;
      //初始化列数
      int cells = 0;
      
      DecimalFormat df = new DecimalFormat("#0.################");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
      
      HSSFRow row;
      HSSFCell cell;
      
      String[] datas = null;
      for (int i = 0; i < rows; i++) {
        row = hssfSheet.getRow(i);
        if (row!=null) {
          cells = row.getLastCellNum();
          datas = new String[cells];
          for (int j = 0; j < cells; j++) {
            cell = row.getCell(j);
            if (cell!=null) {
              switch (cell.getCellTypeEnum()) {
              case FORMULA:
                break;
              case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                  datas[j] = sdf.format(cell.getDateCellValue());
                } else {
                  datas[j] = df.format(cell.getNumericCellValue());
                }
                break;
              case STRING:
                datas[j] = cell.getStringCellValue();
                break;
              default:
                datas[j] = String.valueOf(cell.getStringCellValue());
                break;
              }
            }
          }
          dataList.add(datas);
          //清理数据
          cell = null;
          cells = 0;
        }
        row = null;
      }
      return dataList;
    } catch (IOException e) {
      throw new IOException(e);
    } finally {
      if (hssfWorkbook!=null) {
        try {
          hssfWorkbook.close();
        } catch (IOException e) {
          throw new IOException(e);
        }
      }
      if (pfs!=null) {
        try {
          pfs.close();
        } catch (IOException e) {
          throw new IOException(e);
        }
      }
    }
  }
  
  /**
   * 获得xlsx格式excel其中一个sheet内的数据
   * @author eric
   * @date 2017年6月27日下午5:42:28
   * @param filePath excel文件绝对路径
   * @param sheetName sheet的名字
   * @return List<String[]> list为行，数组为列
   * @throws IOException 文件处理失败的抛错
   * @throws InvalidFormatException 
   */
  private static List<String[]> openXSSFWorkbook(String filePath,
      String sheetName) throws IOException, InvalidFormatException {
    XSSFWorkbook xssfWorkbook = null;
    List<String[]> dataList;
    try {
      xssfWorkbook = new XSSFWorkbook(new File(filePath));
      XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
      
      if (xssfSheet == null) {
        xssfSheet = xssfWorkbook.getSheetAt(0);
      }
      
      dataList = new ArrayList<String[]>();
      //获得sheet的行数
      int rows = xssfSheet.getLastRowNum()+1;
      //初始化列数
      int cells = 0;
      
      DecimalFormat df = new DecimalFormat("#0.################");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

      XSSFRow row;
      XSSFCell cell;
      
      String[] datas = null;
      for (int i = 0; i < rows; i++) {
        row = xssfSheet.getRow(i);
        if (row!=null) {
          cells = row.getLastCellNum();
          datas = new String[cells];
          for (int j = 0; j < cells; j++) {
            cell = row.getCell(j);
            if (cell!=null) {
              switch (cell.getCellTypeEnum()) {
              case FORMULA:
                break;
              case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                  datas[j] = sdf.format(cell.getDateCellValue());
                } else {
                  datas[j] = df.format(cell.getNumericCellValue());
                }
                break;
              case STRING:
                datas[j] = cell.getStringCellValue();
                break;
              default:
                datas[j] = String.valueOf(cell.getStringCellValue());
                break;
              }
            }
          }
          dataList.add(datas);
          //清理数据
          cell = null;
          cells = 0;
        }
        row = null;
      }
    } catch (InvalidFormatException e) {
      throw new InvalidFormatException("create XSSFWorkbook failed",e);
    } catch (IOException e) {
      throw new IOException(e);
    } finally {
      if (xssfWorkbook != null) {
        try {
          xssfWorkbook.close();
        } catch (IOException e) {
          throw new IOException(e);
        }
      }
    }
    
    return dataList;
  }
  
  /**
   * 验证文件扩展名是否正确
   * @author eric
   * @date 2017年6月26日上午10:44:38
   * @param fileName 需要验证扩展名的文件名
   * @param extensionName 符合的扩展名
   * @return boolean true：文件的扩展名与符合的扩展名相同
   *                false：文件的扩展名与符合的扩展名不同
   */
  private static String getFileExtensionName(String fileName){
    
    if (fileName == null||"".equals(fileName)) {
      return "";
    }
    int extensionNameBegin = fileName.lastIndexOf(".");
    if (extensionNameBegin==-1) {
      return "";
    }
    String extensionNameTemp = fileName.substring(extensionNameBegin+1);
    if (extensionNameTemp == null || "".equals(extensionNameTemp)) {
      return "";
    }
    return extensionNameTemp;
  }
  
  /**
   * 根据short值返回边线样式
   * @author eric
   * @date 2017年6月26日下午4:35:36
   * @param s 详见excelParam类
   * @return
   */
  private static BorderStyle getHssfBorderStyle(short s){
    switch (s) {
    case ExcelParam.F_S_BORDER_NONE:
      return BorderStyle.NONE;
    case ExcelParam.F_S_BORDER_THIN:
      return BorderStyle.THIN;
    case ExcelParam.F_S_BORDER_MEDIUM:
      return BorderStyle.MEDIUM;
    case ExcelParam.F_S_BORDER_DASHED:
      return BorderStyle.DASHED;
    case ExcelParam.F_S_BORDER_DOTTED:
      return BorderStyle.DOTTED;
    case ExcelParam.F_S_BORDER_THICK:
      return BorderStyle.THICK;
    case ExcelParam.F_S_BORDER_DOUBLE:
      return BorderStyle.DOUBLE;
    case ExcelParam.F_S_BORDER_HAIR:
      return BorderStyle.HAIR;
    case ExcelParam.F_S_BORDER_MEDIUM_DASHED:
      return BorderStyle.MEDIUM_DASHED;
    case ExcelParam.F_S_BORDER_DASH_DOT:
      return BorderStyle.DASH_DOT;
    case ExcelParam.F_S_BORDER_MEDIUM_DASH_DOT:
      return BorderStyle.MEDIUM_DASH_DOT;
    case ExcelParam.F_S_BORDER_DASH_DOT_DOT:
      return BorderStyle.DASH_DOT_DOT;
    case ExcelParam.F_S_BORDER_MEDIUM_DASH_DOT_DOT:
      return BorderStyle.MEDIUM_DASH_DOT_DOT;
    case ExcelParam.F_S_BORDER_SLANTED_DASH_DOT:
      return BorderStyle.SLANTED_DASH_DOT;
    default:
      return null;
    }
  }
  
  /**
   * 根据short值返回颜色值样式
   * @author eric
   * @date 2017年6月26日下午6:05:25
   * @param s 详见excelParam类
   * @return
   */
  private static Short getHssfIndexedColors(short s){
    switch (s) {
    case ExcelParam.F_S_COLOR_BLACK:
      return IndexedColors.BLACK.getIndex();
    case ExcelParam.F_S_COLOR_WHITE:
      return IndexedColors.WHITE.getIndex();
    case ExcelParam.F_S_COLOR_RED:
      return IndexedColors.RED.getIndex();
    case ExcelParam.F_S_COLOR_BRIGHT_GREEN:
      return IndexedColors.BRIGHT_GREEN.getIndex();
    case ExcelParam.F_S_COLOR_BLUE:
      return IndexedColors.BLUE.getIndex();
    case ExcelParam.F_S_COLOR_YELLOW:
      return IndexedColors.YELLOW.getIndex();
    case ExcelParam.F_S_COLOR_PINK:
      return IndexedColors.PINK.getIndex();
    case ExcelParam.F_S_COLOR_TURQUOISE:
      return IndexedColors.TURQUOISE.getIndex();
    case ExcelParam.F_S_COLOR_DARK_RED:
      return IndexedColors.DARK_RED.getIndex();
    case ExcelParam.F_S_COLOR_GREEN:
      return IndexedColors.GREEN.getIndex();
    case ExcelParam.F_S_COLOR_DARK_BLUE:
      return IndexedColors.DARK_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_DARK_YELLOW:
      return IndexedColors.DARK_YELLOW.getIndex();
    case ExcelParam.F_S_COLOR_VIOLET:
      return IndexedColors.VIOLET.getIndex();
    case ExcelParam.F_S_COLOR_TEAL:
      return IndexedColors.TEAL.getIndex();
    case ExcelParam.F_S_COLOR_GREY_25_PERCENT:
      return IndexedColors.GREY_25_PERCENT.getIndex();
    case ExcelParam.F_S_COLOR_GREY_50_PERCENT:
      return IndexedColors.GREY_50_PERCENT.getIndex();
    case ExcelParam.F_S_COLOR_CORNFLOWER_BLUE:
      return IndexedColors.CORNFLOWER_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_MAROON:
      return IndexedColors.MAROON.getIndex();
    case ExcelParam.F_S_COLOR_LEMON_CHIFFON:
      return IndexedColors.LEMON_CHIFFON.getIndex();
    case ExcelParam.F_S_COLOR_ORCHID:
      return IndexedColors.ORCHID.getIndex();
    case ExcelParam.F_S_COLOR_CORAL:
      return IndexedColors.CORAL.getIndex();
    case ExcelParam.F_S_COLOR_ROYAL_BLUE:
      return IndexedColors.ROYAL_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_LIGHT_CORNFLOWER_BLUE:
      return IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_SKY_BLUE:
      return IndexedColors.SKY_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_LIGHT_TURQUOISE:
      return IndexedColors.LIGHT_TURQUOISE.getIndex();
    case ExcelParam.F_S_COLOR_LIGHT_GREEN:
      return IndexedColors.LIGHT_GREEN.getIndex();
    case ExcelParam.F_S_COLOR_LIGHT_YELLOW:
      return IndexedColors.LIGHT_YELLOW.getIndex();
    case ExcelParam.F_S_COLOR_PALE_BLUE:
      return IndexedColors.PALE_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_ROSE:
      return IndexedColors.ROSE.getIndex();
    case ExcelParam.F_S_COLOR_LAVENDER:
      return IndexedColors.LAVENDER.getIndex();
    case ExcelParam.F_S_COLOR_TAN:
      return IndexedColors.TAN.getIndex();
    case ExcelParam.F_S_COLOR_LIGHT_BLUE:
      return IndexedColors.LIGHT_BLUE.getIndex();
    case ExcelParam.F_S_COLOR_AQUA:
      return IndexedColors.AQUA.getIndex();
    case ExcelParam.F_S_COLOR_LIME:
      return IndexedColors.LIME.getIndex();
    case ExcelParam.F_S_COLOR_GOLD:
      return IndexedColors.GOLD.getIndex();
    case ExcelParam.F_S_COLOR_LIGHT_ORANGE:
      return IndexedColors.LIGHT_ORANGE.getIndex();
    case ExcelParam.F_S_COLOR_ORANGE:
      return IndexedColors.ORANGE.getIndex();
    case ExcelParam.F_S_COLOR_BLUE_GREY:
      return IndexedColors.BLUE_GREY.getIndex();
    case ExcelParam.F_S_COLOR_GREY_40_PERCENT:
      return IndexedColors.GREY_40_PERCENT.getIndex();
    case ExcelParam.F_S_COLOR_DARK_TEAL:
      return IndexedColors.DARK_TEAL.getIndex();
    case ExcelParam.F_S_COLOR_SEA_GREEN:
      return IndexedColors.SEA_GREEN.getIndex();
    case ExcelParam.F_S_COLOR_DARK_GREEN:
      return IndexedColors.DARK_GREEN.getIndex();
    case ExcelParam.F_S_COLOR_OLIVE_GREEN:
      return IndexedColors.OLIVE_GREEN.getIndex();
    case ExcelParam.F_S_COLOR_BROWN:
      return IndexedColors.BROWN.getIndex();
    case ExcelParam.F_S_COLOR_PLUM:
      return IndexedColors.PLUM.getIndex();
    case ExcelParam.F_S_COLOR_INDIGO:
      return IndexedColors.INDIGO.getIndex();
    case ExcelParam.F_S_COLOR_GREY_80_PERCENT:
      return IndexedColors.GREY_80_PERCENT.getIndex();
    case ExcelParam.F_S_COLOR_AUTOMATIC:
      return IndexedColors.AUTOMATIC.getIndex();
    default:
      return null;
    }
  }
  
  /**
   * 获得
   * @author eric
   * @date 2017年6月27日上午11:44:02
   * @param s
   * @return
   */
  private static FillPatternType getFillPatternType(short s){
    switch (s) {
    case ExcelParam.F_S_FILL_PATTERN_NO_FILL:
      return FillPatternType.NO_FILL;
    case ExcelParam.F_S_FILL_PATTERN_SOLID_FOREGROUND:
      return FillPatternType.SOLID_FOREGROUND;
    case ExcelParam.F_S_FILL_PATTERN_FINE_DOTS:
      return FillPatternType.FINE_DOTS;
    case ExcelParam.F_S_FILL_PATTERN_ALT_BARS:
      return FillPatternType.ALT_BARS;
    case ExcelParam.F_S_FILL_PATTERN_SPARSE_DOTS:
      return FillPatternType.SPARSE_DOTS;
    case ExcelParam.F_S_FILL_PATTERN_THICK_HORZ_BANDS:
      return FillPatternType.THICK_HORZ_BANDS;
    case ExcelParam.F_S_FILL_PATTERN_THICK_VERT_BANDS:
      return FillPatternType.THICK_VERT_BANDS;
    case ExcelParam.F_S_FILL_PATTERN_THICK_BACKWARD_DIAG:
      return FillPatternType.THICK_BACKWARD_DIAG;
    case ExcelParam.F_S_FILL_PATTERN_THICK_FORWARD_DIAG:
      return FillPatternType.THICK_FORWARD_DIAG;
    case ExcelParam.F_S_FILL_PATTERN_BIG_SPOTS:
      return FillPatternType.BIG_SPOTS;
    case ExcelParam.F_S_FILL_PATTERN_BRICKS:
      return FillPatternType.BRICKS;
    case ExcelParam.F_S_FILL_PATTERN_THIN_HORZ_BANDS:
      return FillPatternType.THIN_HORZ_BANDS;
    case ExcelParam.F_S_FILL_PATTERN_THIN_VERT_BANDS:
      return FillPatternType.THIN_VERT_BANDS;
    case ExcelParam.F_S_FILL_PATTERN_THIN_BACKWARD_DIAG:
      return FillPatternType.THIN_BACKWARD_DIAG;
    case ExcelParam.F_S_FILL_PATTERN_THIN_FORWARD_DIAG:
      return FillPatternType.THIN_FORWARD_DIAG;
    case ExcelParam.F_S_FILL_PATTERN_SQUARES:
      return FillPatternType.SQUARES;
    case ExcelParam.F_S_FILL_PATTERN_DIAMONDS:
      return FillPatternType.DIAMONDS;
    case ExcelParam.F_S_FILL_PATTERN_LESS_DOTS:
      return FillPatternType.LESS_DOTS;
    case ExcelParam.F_S_FILL_PATTERN_LEAST_DOTS:
      return FillPatternType.LEAST_DOTS;
    default:
      return null;
    }
  }
  
  /**
   * 根据short值返回单元格左右居中样式
   * @author eric
   * @date 2017年6月26日下午6:10:08
   * @param s 
   * @return
   */
  private static HorizontalAlignment getHssfHorizontalAlignment(short s){
    switch (s) {
    case ExcelParam.F_S_CELL_GENERAL:
      return HorizontalAlignment.GENERAL;
    case ExcelParam.F_S_CELL_LEFT:
      return HorizontalAlignment.LEFT;
    case ExcelParam.F_S_CELL_RIGHT:
      return HorizontalAlignment.RIGHT;
    case ExcelParam.F_S_CELL_CENTER:
      return HorizontalAlignment.CENTER;
    case ExcelParam.F_S_CELL_FILL:
      return HorizontalAlignment.FILL;
    case ExcelParam.F_S_CELL_JUSTIFY:
      return HorizontalAlignment.JUSTIFY;
    case ExcelParam.F_S_CELL_CENTER_SELECTION:
      return HorizontalAlignment.CENTER_SELECTION;
    case ExcelParam.F_S_CELL_DISTRIBUTED:
      return HorizontalAlignment.DISTRIBUTED;
    default:
      return null;
    }
  }
  
  /**
   * @Descrption 根据short值返回单元格上下居中样式
   * @author eric
   * @date 2017年6月26日下午6:10:19
   * @param s
   * @return
   */
  private static VerticalAlignment getHssfVerticalAlignment(short s){
    switch (s) {
    case ExcelParam.F_S_CELL_TOP:
      return VerticalAlignment.TOP;
    case ExcelParam.F_S_CELL_BOTTOM:
      return VerticalAlignment.BOTTOM;
    case ExcelParam.F_S_CELL_CENTER:
      return VerticalAlignment.CENTER;
    case ExcelParam.F_S_CELL_JUSTIFY:
      return VerticalAlignment.JUSTIFY;
    case ExcelParam.F_S_CELL_DISTRIBUTED:
      return VerticalAlignment.DISTRIBUTED;
    default:
      return null;
    }
  }
  
  /**
   * 根据short值返回单元格是否需要下划线
   * @author eric
   * @date 2017年6月26日下午6:26:34
   * @param s
   * @return
   */
  private static Byte getHssfUnderline(short s){
    switch (s) {
    case ExcelParam.F_S_FONT_U_NONE:
      return HSSFFont.U_NONE;
    case ExcelParam.F_S_FONT_U_SINGLE:
      return HSSFFont.U_SINGLE;
    case ExcelParam.F_S_FONT_U_DOUBLE:
      return HSSFFont.U_DOUBLE;
    default:
      return null;
    }
  }
  
  /**
   * 根据short值返回单元格内的文字需要在上方或下方
   * @author eric
   * @date 2017年6月26日下午6:27:07
   * @param s
   * @return
   */
  private static Short getHssfTypeOffset(short s){
    switch (s) {
    case ExcelParam.F_S_FONT_SS_NONE:
      return HSSFFont.SS_NONE;
    case ExcelParam.F_S_FONT_SS_SUPER:
      return HSSFFont.SS_SUPER;
    case ExcelParam.F_S_FONT_SS_SUB:
      return HSSFFont.SS_SUB;
    default:
      return null;
    }
  }
  
  /**
   * 根据参数类型将值放入cell内
   * @author eric
   * @date 2017年6月27日上午11:45:26
   * @param cell 单元格对象
   * @param value 参数
   */
  private static void setCellValueForParamValue(Cell cell, Object value) {
    //获得参数的类型
    String paramType = value.getClass().getName();
    if ("java.lang.Double".equals(paramType)) {
      cell.setCellValue((double)value);
    } else if ("java.util.Date".equals(paramType)) {
      cell.setCellValue((Date)value);
    } else if ("java.util.GregorianCalendar".equals(paramType)) {
      cell.setCellValue((Calendar)value);
    } else if ("org.apache.poi.hssf.usermodel.HSSFRichTextString".equals(paramType)) {
      cell.setCellValue((RichTextString)value);
    } else if ("org.apache.poi.xssf.usermodel.XSSFRichTextString".equals(paramType)) {
      cell.setCellValue((RichTextString)value);
    } else if ("java.lang.String".equals(paramType)) {
      cell.setCellValue((String)value);
    } else if ("java.lang.Boolean".equals(paramType)) {
      cell.setCellValue((boolean)value);
    } else {
      cell.setCellValue(value.toString());
    }
  }
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年6月4日上午9:42:25
   * @param cellValue
   * @return
   */
  public static CellBean getExportCelBean(Object cellValue) {
    CellBean cellBean = new CellBean();
    cellBean.setValue(cellValue);
    cellBean.setFontName("楷体");

    cellBean.setBorderTop(ExcelParam.F_S_BORDER_THIN);
    cellBean.setBorderLeft(ExcelParam.F_S_BORDER_THIN);
    cellBean.setBorderRight(ExcelParam.F_S_BORDER_THIN);
    cellBean.setBorderBottom(ExcelParam.F_S_BORDER_THIN);

    cellBean.setBorderTopColor(ExcelParam.F_S_COLOR_BLACK);
    cellBean.setBorderLeftColor(ExcelParam.F_S_COLOR_BLACK);
    cellBean.setBorderRightColor(ExcelParam.F_S_COLOR_BLACK);
    cellBean.setBorderBottomColor(ExcelParam.F_S_COLOR_BLACK);

    cellBean.setAlignment(ExcelParam.F_S_CELL_LEFT);
    cellBean.setVerticalAlignment(ExcelParam.F_S_CELL_CENTER);
    return cellBean;
  }
}

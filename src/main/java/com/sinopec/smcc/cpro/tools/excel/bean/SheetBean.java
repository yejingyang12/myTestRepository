/**
* Copyright 2017 zsy.com Inc. All Rights Reserved. 
* @Title SheetBean.java
* @Package com.anxinyiheng.tools.poi.excel.bean
* @Description: TODO:
* @author eric
* @date 2017年6月26日下午1:29:21
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools.excel.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title SheetBean.java
 * @Package com.anxinyiheng.tools.poi.excel.bean
 * @Description: TODO:
 * @author eric
 * @date 2017年6月26日下午1:29:21
 * @version V1.0
 */
public class SheetBean {

  private String sheetName="Sheet1";
  private HeaderBean HeaderBean;
  private Integer rowFixed;
  private Integer columnFixed;
  private Short defaultRowHeight;
  private Map<Integer, Short> rowHeightMap = new HashMap<Integer, Short>();
  private Short defaultColumnWidth;
  private Map<Integer, Short> columnWidthMap = new HashMap<Integer, Short>();
  private boolean displayGridlines = true;
  private List<Integer[]> mergedRegionList;
  private List<List<CellBean>> dataList;

  public String getSheetName() {
    return sheetName;
  }
  /**
   * sheet的名字，默认为Sheet1
   * @author eric
   * @date 2017年6月27日下午2:06:55
   * @param sheetName sheet名字
   */
  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public HeaderBean getHeaderBean() {
    return HeaderBean;
  }
  /**
   * excel头信息
   * @author eric
   * @date 2017年6月27日下午2:35:37
   * @param headerBean
   */
  public void setHeaderBean(HeaderBean headerBean) {
    HeaderBean = headerBean;
  }

  public Integer getRowFixed() {
    return rowFixed;
  }
  /**
   * 固定行数量
   * @author eric
   * @date 2017年6月27日下午2:40:11
   * @param rowFixed 固定的行数量
   */
  public void setRowFixed(Integer rowFixed) {
    this.rowFixed = rowFixed;
  }

  public Integer getColumnFixed() {
    return columnFixed;
  }
  /**
   * 固定列数量
   * @author eric
   * @date 2017年6月27日下午2:42:51
   * @param columnFixed 固定的列数量
   */
  public void setColumnFixed(Integer columnFixed) {
    this.columnFixed = columnFixed;
  }

  public Short getDefaultRowHeight() {
    return defaultRowHeight;
  }
  /**
   * 默认行高
   * @author eric
   * @date 2017年6月27日下午2:43:52
   * @param defaultRowHeight 行高 short 正整数
   */
  public void setDefaultRowHeight(Short defaultRowHeight) {
    this.defaultRowHeight = defaultRowHeight;
  }

  public Map<Integer, Short> getRowHeightMap() {
    return rowHeightMap;
  }
  /**
   * 单元格行高，如map内没有此单元格则自动按照默认行高处理。
   * @author eric
   * @date 2017年6月27日下午2:45:14
   * @param rowHeightMap key为行数；value为高度大小，short 正整数；
   */
  public void setRowHeightMap(Map<Integer, Short> rowHeightMap) {
    this.rowHeightMap = rowHeightMap;
  }

  public Short getDefaultColumnWidth() {
    return defaultColumnWidth;
  }
  /**
   * 默认列宽
   * @author eric
   * @date 2017年6月27日下午2:44:28
   * @param defaultColumnWidth 列宽 short 正整数
   */
  public void setDefaultColumnWidth(Short defaultColumnWidth) {
    this.defaultColumnWidth = defaultColumnWidth;
  }

  public Map<Integer, Short> getColumnWidthMap() {
    return columnWidthMap;
  }
  /**
   * 单元格列宽，如map内没有此单元格则自动按照默认列宽处理。
   * @author eric
   * @date 2017年6月27日下午2:45:24
   * @param columnWidthMap key为列数；值为宽度大小，short 正整数；
   */
  public void setColumnWidthMap(Map<Integer, Short> columnWidthMap) {
    this.columnWidthMap = columnWidthMap;
  }

  public boolean isDisplayGridlines() {
    return displayGridlines;
  }
  /**
   * 是否显示网格
   * @author eric
   * @date 2017年6月27日下午2:48:27
   * @param displayGridlines true：显示；false：不显示。
   */
  public void setDisplayGridlines(boolean displayGridlines) {
    this.displayGridlines = displayGridlines;
  }

  public List<Integer[]> getMergedRegionList() {
    return mergedRegionList;
  }
  /**
   * 合并单元格
   * @author eric
   * @date 2017年6月27日下午2:48:57
   * @param mergedRegionList：每个下标位为一个需要合并的单元格
   *                          Integer数组：下标0：行开始；
   *                                       下标1：行结束；
   *                                       下标2：列开始；
   *                                       下标3：列结束，
   *                                       通过数字说明行、列；
   *                          以0代表第一行或第一列，数组长度必须为4，如为其他工具自动将其过滤。   
   */
  public void setMergedRegionList(List<Integer[]> mergedRegionList) {
    this.mergedRegionList = mergedRegionList;
  }

  public List<List<CellBean>> getDataList() {
    return dataList;
  }
  /**
   * 单元格数据
   * @author eric
   * @date 2017年6月27日下午2:51:36
   * @param dataList 单元格格式及数据，外list下标代表行，内list下标代表列。
   */
  public void setDataList(List<List<CellBean>> dataList) {
    this.dataList = dataList;
  }
}

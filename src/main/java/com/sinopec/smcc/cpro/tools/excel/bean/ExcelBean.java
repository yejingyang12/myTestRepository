/**
* Copyright 2017 zsy.com Inc. All Rights Reserved. 
* @Title ExcelBean.java
* @Package com.anxinyiheng.tools.poi.excel.bean
* @Description: TODO:
* @author eric
* @date 2017年6月26日下午1:28:25
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools.excel.bean;

import java.util.List;

/**
 * @Title ExcelBean.java
 * @Package com.anxinyiheng.tools.poi.excel.bean
 * @Description: TODO:
 * @author eric
 * @date 2017年6月26日下午1:28:25
 * @version V1.0
 */
public class ExcelBean implements Cloneable {

  private String filePath;
  private List<SheetBean> sheetList;
  
  public ExcelBean clone() throws CloneNotSupportedException{
    ExcelBean excelBean = (ExcelBean) super.clone();
    return excelBean;
  }
  
  public String getFilePath() {
    return filePath;
  }
  /**
   * 文件绝对路径
   * @author eric
   * @date 2017年6月27日下午2:04:24
   * @param filePath 绝对路径
   */
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  
  public List<SheetBean> getSheetList() {
    return sheetList;
  }
  /**
   * 文件sheet对象集合
   * @author eric
   * @date 2017年6月27日下午2:04:51
   * @param sheetList 对象集合
   */
  public void setSheetList(List<SheetBean> sheetList) {
    this.sheetList = sheetList;
  }
}

/**
* Copyright 2017 zsy.com Inc. All Rights Reserved. 
* @Title CellBean.java
* @Package com.anxinyiheng.tools.poi.excel.bean
* @Description: TODO:
* @author eric
* @date 2017年6月26日下午1:28:09
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools.excel.bean;

/**
 * @Title CellBean.java
 * @Package com.anxinyiheng.tools.poi.excel.bean
 * @Description: TODO:
 * @author eric
 * @date 2017年6月26日下午1:28:09
 * @version V1.0
 */
public class CellBean implements Cloneable {

  private Object value;
  private Short borderTop;
  private Short borderBottom;
  private Short borderLeft;
  private Short borderRight;
  
  private Short borderTopColor;
  private Short borderBottomColor;
  private Short borderLeftColor;
  private Short borderRightColor;
  
  private String dataFormat;
  private Short fillPattern;
  private Short fillForegroundColor;
  private Short fillBackgroundColor;
  
  private boolean italic = false;
  private boolean bold = false;
  private String fontName;
  private Short fontHeightInPoints;
  private Short fontColor;
  private boolean strikeout = false;
  private Short underline = 0;
  private Short typeOffset;
  
  private Short alignment;
  private Short verticalAlignment;
  
  private boolean wrapText = false;
  
  public CellBean clone() throws CloneNotSupportedException{
    CellBean cellBean = (CellBean) super.clone();
    return cellBean;
  }
  
  public Object getValue() {
    return value;
  }
  /**
   * 单元格内的值
   * @author eric
   * @date 2017年6月27日下午2:52:48
   * @param value 支持Date、double、Calendar、RichTextString、String、Boolean，
   *              其他格式按照toString处理。
   */
  public void setValue(Object value) {
    this.value = value;
  }

  public Short getBorderTop() {
    return borderTop;
  }
  /**
   * 单元格上部分线条样式
   * @author eric
   * @date 2017年6月27日下午2:55:45
   * @param borderTop 具体值请关注excelParam内以F_S_BORDER_开头的常量
   */
  public void setBorderTop(Short borderTop) {
    this.borderTop = borderTop;
  }

  public Short getBorderBottom() {
    return borderBottom;
  }
  /**
   * 单元格下部分线条样式
   * @author eric
   * @date 2017年6月27日下午3:06:34
   * @param borderBottom 具体值请关注excelParam内以F_S_BORDER_开头的常量
   */
  public void setBorderBottom(Short borderBottom) {
    this.borderBottom = borderBottom;
  }

  public Short getBorderLeft() {
    return borderLeft;
  }
  /**
   * 单元格左部分线条样式
   * @author eric
   * @date 2017年6月27日下午3:06:54
   * @param borderLeft 具体值请关注excelParam内以F_S_BORDER_开头的常量
   */
  public void setBorderLeft(Short borderLeft) {
    this.borderLeft = borderLeft;
  }

  public Short getBorderRight() {
    return borderRight;
  }
  /**
   * 单元格右部分线条样式
   * @author eric
   * @date 2017年6月27日下午3:07:09
   * @param borderRight 具体值请关注excelParam内以F_S_BORDER_开头的常量
   */
  public void setBorderRight(Short borderRight) {
    this.borderRight = borderRight;
  }

  public Short getBorderTopColor() {
    return borderTopColor;
  }
  /**
   * 单元格上部分线条颜色
   * @author eric
   * @date 2017年6月27日下午3:07:31
   * @param borderTopColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setBorderTopColor(Short borderTopColor) {
    this.borderTopColor = borderTopColor;
  }

  public Short getBorderBottomColor() {
    return borderBottomColor;
  }
  /**
   * 单元格下部分线条颜色
   * @author eric
   * @date 2017年6月27日下午3:08:41
   * @param borderBottomColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setBorderBottomColor(Short borderBottomColor) {
    this.borderBottomColor = borderBottomColor;
  }

  public Short getBorderLeftColor() {
    return borderLeftColor;
  }
  /**
   * 单元格左部分线条颜色
   * @author eric
   * @date 2017年6月27日下午3:08:56
   * @param borderLeftColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setBorderLeftColor(Short borderLeftColor) {
    this.borderLeftColor = borderLeftColor;
  }

  public Short getBorderRightColor() {
    return borderRightColor;
  }
  /**
   * 单元格右部分线条颜色
   * @author eric
   * @date 2017年6月27日下午3:09:02
   * @param borderRightColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setBorderRightColor(Short borderRightColor) {
    this.borderRightColor = borderRightColor;
  }

  public String getDataFormat() {
    return dataFormat;
  }
  /**
   * 单元格数据的格式化
   * @author eric
   * @date 2017年6月27日下午3:09:26
   * @param dataFormat 对数据进行格式化。
   *                   如时间格式：yyyy/mm/dd，
   *                     双精度 浮点格式：#,##0.00等
   */
  public void setDataFormat(String dataFormat) {
    this.dataFormat = dataFormat;
  }

  public Short getFillPattern() {
    return fillPattern;
  }
  /**
   * 填充图案样式
   * @author eric
   * @date 2017年6月27日下午3:12:23
   * @param fillPattern 具体值请关注excelParam内以F_S_FILL_PATTERN_开头的常量
   */
  public void setFillPattern(Short fillPattern) {
    this.fillPattern = fillPattern;
  }

  public Short getFillForegroundColor() {
    return fillForegroundColor;
  }
  /**
   * 前景颜色
   * @author eric
   * @date 2017年6月27日下午3:13:29
   * @param fillForegroundColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setFillForegroundColor(Short fillForegroundColor) {
    this.fillForegroundColor = fillForegroundColor;
  }

  public Short getFillBackgroundColor() {
    return fillBackgroundColor;
  }
  /**
   * 背景颜色
   * @author eric
   * @date 2017年6月27日下午3:13:41
   * @param fillBackgroundColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setFillBackgroundColor(Short fillBackgroundColor) {
    this.fillBackgroundColor = fillBackgroundColor;
  }

  public boolean isItalic() {
    return italic;
  }
  /**
   * 设置成斜体
   * @author eric
   * @date 2017年6月27日下午3:14:15
   * @param italic：true：斜体；false：正常
   */
  public void setItalic(boolean italic) {
    this.italic = italic;
  }

  public boolean isBold() {
    return bold;
  }
  /**
   * 设置成粗体
   * @author eric
   * @date 2017年6月27日下午3:14:48
   * @param italic：true：粗体；false：正常
   */
  public void setBold(boolean bold) {
    this.bold = bold;
  }

  public String getFontName() {
    return fontName;
  }
  /**
   * 字体
   * @author eric
   * @date 2017年6月27日下午3:31:22
   * @param fontName 填入Excel所对应的字体名称即可
   */
  public void setFontName(String fontName) {
    this.fontName = fontName;
  }

  public Short getFontHeightInPoints() {
    return fontHeightInPoints;
  }
  /**
   * 文字大小
   * @author eric
   * @date 2017年6月27日下午3:15:18
   * @param fontHeightInPoints short 正整数
   */
  public void setFontHeightInPoints(Short fontHeightInPoints) {
    this.fontHeightInPoints = fontHeightInPoints;
  }

  public Short getFontColor() {
    return fontColor;
  }
  /**
   * 文字颜色
   * @author eric
   * @date 2017年6月27日下午3:16:07
   * @param fontColor 具体值请关注excelParam内以F_S_COLOR_开头的常量
   */
  public void setFontColor(Short fontColor) {
    this.fontColor = fontColor;
  }

  public boolean isStrikeout() {
    return strikeout;
  }
  /**
   * 删除线
   * @author eric
   * @date 2017年6月27日下午3:16:47
   * @param strikeout true：有删除线；false：没有删除线。
   */
  public void setStrikeout(boolean strikeout) {
    this.strikeout = strikeout;
  }

  public Short getUnderline() {
    return underline;
  }
  /**
   * 下划线
   * @author eric
   * @date 2017年6月27日下午3:17:16
   * @param underline 具体值请关注excelParam内以F_S_FONT_开头的常量
   */
  public void setUnderline(Short underline) {
    this.underline = underline;
  }

  public Short getTypeOffset() {
    return typeOffset;
  }
  /**
   * 文字位置
   * @author eric
   * @date 2017年6月27日下午3:18:20
   * @param typeOffset 具体值请关注excelParam内以F_S_FONT_开头的常量
   */
  public void setTypeOffset(Short typeOffset) {
    this.typeOffset = typeOffset;
  }

  public Short getAlignment() {
    return alignment;
  }
  /**
   * 单元格左右对齐方式
   * @author eric
   * @date 2017年6月27日下午3:18:39
   * @param alignment 具体值请关注excelParam内以F_S_CELL_开头的常量
   */
  public void setAlignment(Short alignment) {
    this.alignment = alignment;
  }

  public Short getVerticalAlignment() {
    return verticalAlignment;
  }
  /**
   * 单元格上下对齐方式
   * @author eric
   * @date 2017年6月27日下午3:19:23
   * @param alignment 具体值请关注excelParam内以F_S_CELL_开头的常量
   */
  public void setVerticalAlignment(Short verticalAlignment) {
    this.verticalAlignment = verticalAlignment;
  }

  public boolean isWrapText() {
    return wrapText;
  }
  /**
   * 是否自动换行
   * @author eric
   * @date 2017年6月27日下午3:19:42
   * @param wrapText true：自动换行；false：非自动换行
   */
  public void setWrapText(boolean wrapText) {
    this.wrapText = wrapText;
  }
}

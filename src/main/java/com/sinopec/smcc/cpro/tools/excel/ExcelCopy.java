/**
* Copyright 2018. 
* @Title ExcelCopy.java
* @Package com.sinopec.smcc.cpro.tools.excel
* @Description: TODO:
* @author yejingyang
* @date 2018年8月2日下午4:55:37
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools.excel;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Title ExcelCopy.java
 * @Package com.sinopec.smcc.cpro.tools.excel
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年8月2日下午4:55:37
 * @version V1.0
 */
public class ExcelCopy {
  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年8月2日下午5:03:19
   * @param startRow
   *          想要复制的起始行行标（Excel左边数字）
   * @param endRow
   *          想要复制的结束行行标（Excel左边数字）
   * @param pPosition
   *          复制内容后要放到指定的位置的开始下标（行标-1）
   * @param sheet
   *          要操作的sheet
   */
  @Autowired
  public static void copySelfRows(int startRow, int endRow, int pPosition, HSSFSheet sheet) {
    int pStartRow = startRow - 1;
    int pEndRow = endRow - 1;
    int targetRowFrom;
    int targetRowTo;
    int columnCount;
    CellRangeAddress region = null;
    int i;
    int j;
    if (pStartRow == -1 || pEndRow == -1) {
      return;
    }
    // 拷贝合并的单元格
    for (i = 0; i < sheet.getNumMergedRegions(); i++) {
      region = sheet.getMergedRegion(i);
      if (region == null) {
        continue;
      }
      if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
        targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
        targetRowTo = region.getLastRow() - pStartRow + pPosition;
        CellRangeAddress newRegion = region.copy();
        newRegion.setFirstRow(targetRowFrom);
        newRegion.setFirstColumn(region.getFirstColumn());
        newRegion.setLastRow(targetRowTo);
        newRegion.setLastColumn(region.getLastColumn());
        sheet.addMergedRegion(newRegion);
      }
    }
    // 设置列宽
    for (i = pStartRow; i <= pEndRow; i++) {
      //获取模板的要复制的行
      HSSFRow sourceRow = sheet.getRow(i);
      if (sourceRow == null) {
        continue;
      }
      //获取模板要复制的行的最大列数，用于循环复制列
      columnCount = sourceRow.getLastCellNum();
      //如果要复制的行有值，准备复制要复制的行
      if (sourceRow != null) {
        //创建要生成的行
        HSSFRow newRow = sheet.createRow(pPosition - pStartRow + i);
        //获取模板要复制的行高，用于设置生成的行
        short height = sourceRow.getHeight();
        newRow.setHeight(height);
        for (j = 0; j < columnCount; j++) {
          //获取模板要复制的单元格对象
          HSSFCell templateCell = sourceRow.getCell(j);
          if (templateCell != null) {
            //创建要生成的单元格对象
            HSSFCell newCell = newRow.createCell(j);
            //将模板要复制的单元格的格式和数值复制到要生成的单元格中
            copyCell(templateCell, newCell);
          }
        }//循环列设置列宽end
      }
    }//循环行设置列宽end
  }

  /**
   * @Descrption  复制单元格
   * @author yejingyang
   * @date 2018年8月3日下午1:39:33
   * @param fromCell   
   * @param toCell
   */
  @SuppressWarnings("deprecation")
  private static void copyCell(HSSFCell fromCell, HSSFCell toCell) {
    //设置单元格样式
    HSSFCellStyle cellStyle = fromCell.getCellStyle();
    toCell.setCellStyle(cellStyle);
    
    //如果该单元格有注释，将注释加入到新单元格
    if (fromCell.getCellComment() != null) {
      HSSFComment cellComment = fromCell.getCellComment();
      toCell.setCellComment(cellComment);
    }
    
    //获取单元格数值类型
    int srcCellType = fromCell.getCellType();
    toCell.setCellType(srcCellType);
    //根据单元格数值类型不同获取对应单元格数值
    if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
      if (HSSFDateUtil.isCellDateFormatted(fromCell)) {
        Date cellValue = fromCell.getDateCellValue();
        toCell.setCellValue(cellValue);
      } else {
        double cellValue = fromCell.getNumericCellValue();
        toCell.setCellValue(cellValue);
      }
    } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
      HSSFRichTextString cellValue = fromCell.getRichStringCellValue();
      toCell.setCellValue(cellValue);
      
    } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
      // nothing21
    } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
      boolean cellValue = fromCell.getBooleanCellValue();
      toCell.setCellValue(cellValue);
    } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
      byte cellValue = fromCell.getErrorCellValue();
      toCell.setCellErrorValue(cellValue);
    } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
      String cellValue = fromCell.getCellFormula();
      toCell.setCellFormula(cellValue);
    } else { // nothing29

    }
  }
}

/**
* 2018. 
* @Title JacobExcelTool.java
* @Package com.sinopec.smcc.cpro.tools
* @Description: TODO:
* @author dongxu
* @date 2018年6月5日上午9:26:03
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @Title JacobExcelTool.java
 * @Package com.sinopec.smcc.cpro.tools
 * @Description: TODO:
 * @author dongxu
 * @date 2018年6月5日上午9:26:03
 * @version V1.0
 */
public class JacobExcelTool {
  private ActiveXComponent xl = null; // Excel对象

  private Dispatch workbooks = null; // 工作簿对象

  private Dispatch workbook = null; // 具体工作簿

  private Dispatch sheets = null;// 获得sheets集合对象

  private Dispatch currentSheet = null;// 当前sheet

  public ActiveXComponent getXl() {
      return xl;
  }

  public Dispatch getWorkbooks() {
      return workbooks;
  }

  public Dispatch getWorkbook() {
      return workbook;
  }

  /**
   * 
   * 打开excel文件
   * @param filepath 文件路径名称
   * @param visible 是否显示打开
   * @param readonly 是否只读方式打开
   * 
   */

  public void OpenExcel(String filepath, boolean visible, boolean readonly) {
      try {
          initComponents(); // 清空原始变量
          ComThread.InitSTA();//仅允许同时运行一个线程，其他线程锁住
          //ComThread.InitMTA(true);//可同时运行多个
          if (xl == null)
              xl = new ActiveXComponent("Excel.Application"); // Excel对象
          xl.setProperty("Visible", new Variant(visible));// 设置是否显示打开excel
          if (workbooks == null)
              workbooks = xl.getProperty("Workbooks").toDispatch(); // 工作簿对象
          workbook = Dispatch.invoke( // 打开具体工作簿
                  workbooks, "Open", Dispatch.Method,
                  new Object[] { filepath, new Variant(false), new Variant(readonly) }, // 是否以只读方式打开
                  new int[1]).toDispatch();
      } catch (Exception e) {
          e.printStackTrace();
          releaseSource();
      }
  }

  /**
   * 
   * 工作簿另存为
   * @param filePath 另存为的路径
   * 
   */
  public void SaveAs(String filePath) {
      Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] { filePath, new Variant(44) }, new int[1]);
  }

  /**
   * 
   * 关闭excel文档
   * @param f 含义不明 （关闭是否保存？默认false）
   */

  public void CloseExcel(boolean f, boolean quitXl) {
      try {
          Dispatch.call(workbook, "Save");
          Dispatch.call(workbook, "Close", new Variant(f));
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          if (quitXl) {
              releaseSource();
          }
      }
  }

  /**
   * 
   * 释放资源
   * 
   */
  public void releaseSource() {
      if (xl != null) {
          xl.invoke("Quit", new Variant[] {});
          xl = null;
      }
      workbooks = null;
      ComThread.Release();
      System.gc();
  }

  /**
   * 
   * 添加新的工作表(sheet)，（添加后为默认为当前激活的工作表）
   * 
   */
  public Dispatch addSheet() {
      return Dispatch.get(Dispatch.get(workbook, "sheets").toDispatch(), "add").toDispatch();
  }

  /**
   * 
   * 修改当前工作表的名字
   * @param newName
   * 
   */
  public void modifyCurrentSheetName(String newName) {
      Dispatch.put(getCurrentSheet(), "name", newName);
  }

  /**
   * 
   * 得到当前工作表的名字
   * @return
   * 
   */
  public String getCurrentSheetName() {
      return Dispatch.get(getCurrentSheet(), "name").toString();
  }

  /**
   * 
   * 得到工作薄的名字
   * @return
   * 
   */
  public String getWorkbookName() {
      if (workbook == null)
          return null;
      return Dispatch.get(workbook, "name").toString();

  }

  /**
   * 
   * 得到sheets的集合对象
   * @return
   * 
   */
  public Dispatch getSheets() {
      if (sheets == null)
          sheets = Dispatch.get(workbook, "sheets").toDispatch();
      return sheets;
  }

  /**
   * 
   * 得到当前sheet
   * @return
   * 
   */
  public Dispatch getCurrentSheet() {
      currentSheet = Dispatch.get(workbook, "ActiveSheet").toDispatch();
      return currentSheet;
  }

  /**
   * 
   * 通过工作表名字得到工作表
   * @param name sheetName
   * @return
   * 
   */
  public Dispatch getSheetByName(String name) {
      return Dispatch.invoke(getSheets(), "Item", Dispatch.Get, new Object[] { name }, new int[1]).toDispatch();
  }

  /**
   * 
   * 通过工作表索引得到工作表(第一个工作簿index为1)
   * @param index
   * @return sheet对象
   * 
   */

  public Dispatch getSheetByIndex(Integer index) {
      return Dispatch.invoke(getSheets(), "Item", Dispatch.Get, new Object[] { index }, new int[1]).toDispatch();
  }

  /**
   * 
   * 得到sheet的总数
   * 
   * @return
   * 
   */

  public int getSheetCount() {
      int count = Dispatch.get(getSheets(), "count").toInt();
      return count;
  }

  /**
   * 
   * 调用excel宏
   * @param macroName 宏名
   * 
   */
  public void callMacro(String macroName) {
      Dispatch.call(xl, "Run", new Variant(macroName));
  }

  /**
   * 
   * 调用excel宏
   * @param macroName 宏名
   * @param param 传递参数
   */
  public void callMacro(String macroName,Object param) {
      Dispatch.call(xl, "Run", new Variant(macroName),new Variant(param));
  }

  /**
   * 
   * 单元格写入值
   * @param sheet 被操作的sheet
   * @param position 单元格位置，如：C1
   * @param type  值的属性 如：value
   * @param value
   * 
   */

  public void setValue(Dispatch sheet, String position, String type, Object value) {
      Dispatch cell = Dispatch.invoke(sheet, "Range", Dispatch.Get, new Object[] { position }, new int[1])
              .toDispatch();
      Dispatch.put(cell, type, value);
  }

  /**
   * 
   * 单元格读取值
   * @param position 单元格位置，如： C1
   * @param sheet
   * @return
   * 
   */
  public Variant getValue(String position, Dispatch sheet) {
      Dispatch cell = Dispatch.invoke(sheet, "Range", Dispatch.Get, new Object[] { position }, new int[1])
              .toDispatch();
      Variant value = Dispatch.get(cell, "Value");
      return value;

  }

  private void initComponents() {
      workbook = null;
      currentSheet = null;
      sheets = null;
  }

  public void read() {  
    File file = new File("F:\\桌面应用\\测试\\exportExcelModel.xlsm");  
    InputStream inputStream = null;  
    Workbook workbook = null;  
    try {  
        inputStream = new FileInputStream(file);  
        workbook = WorkbookFactory.create(inputStream);  
        inputStream.close();  
        //工作表对象  
        Sheet sheet = workbook.getSheetAt(0);  
        //总行数  
        int rowLength = sheet.getLastRowNum()+1;  
        //工作表的列  
        Row row = sheet.getRow(0);  
        //总列数  
        int colLength = row.getLastCellNum();  
        //得到指定的单元格  
        Cell cell = row.getCell(0);  
        //得到单元格样式  
        CellStyle cellStyle = cell.getCellStyle();  
        System.out.println("行数：" + rowLength + ",列数：" + colLength);  
        for (int i = 0; i < rowLength; i++) {  
            row = sheet.getRow(i);  
            for (int j = 0; j < colLength; j++) {  
                cell = row.getCell(j);  
                //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：  
                //Cannot get a STRING value from a NUMERIC cell  
                //将所有的需要读的Cell表格设置为String格式  
                if (cell != null)  
                    cell.setCellType(CellType.STRING);  

                //对Excel进行修改  
                if (i > 0 && j == 1)  
                    cell.setCellValue("1000");  
                System.out.print(cell.getStringCellValue() + "\t");  
            }  
            System.out.println();  
        }  

        //将修改好的数据保存  
        OutputStream out = new FileOutputStream(file);  
        workbook.write(out);  
        
        File file1=new File("F:\\桌面应用\\测试\\exportExcelModel1111.xlsm");
        
      
    } catch (Exception e) {  
        e.printStackTrace();  
    }  
  }  

}

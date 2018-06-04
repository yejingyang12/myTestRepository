/**
* @Title Utils.java
* @Package com.sinopec.smcc.cpro.tools
* @Description: TODO:
* @author eric
* @date 2018年5月25日下午8:05:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Title Utils.java
 * @Package com.sinopec.smcc.cpro.tools
 * @Description: TODO:
 * @author eric
 * @date 2018年5月25日下午8:05:12
 * @version V1.0
 */
public class Utils {

  /**
   * 返回32位UUID，不包含中划线
   * @author eric
   * @date 2017年6月16日下午5:38:36
   * @return 去掉中划线的uuid
   */
  public static String getUuidFor32(){
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  /**
   * 返回36位UUID，包含中划线
   * @author eric
   * @date 2017年6月16日下午5:38:11
   * @return uuid
   */
  public static String getUuidFor36(){
    return UUID.randomUUID().toString();
  }
  
  public static void main(String[] args) throws IOException {
    String fileName = "F:\\桌面应用\\测试\\exportExcelModel.xlsm";  //修改d盘的aaa.xlsx文件
    XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(fileName));
    Map<String, String> fields = new HashMap<String, String>();
    fields = getFieldMap();  //获取要修改字段的集合
    String fillStr="";    //存储aaa文件里的数据
    String[] fillSplit=null; 
    XSSFSheet xSheet = xwb.getSheetAt(0);  //获取excel表的第一个sheet
    for (int i = 0; i <= xSheet.getLastRowNum(); i++) {  //遍历所有的行
        if(xSheet.getRow(i)==null){ //这行为空执行下次循环
            continue;
        }
        
        for (int j = 0; j <=  xSheet.getRow(i).getPhysicalNumberOfCells(); j++) {  //遍历当前行的所有列
            if(xSheet.getRow(i).getCell(j)==null){//为空执行下次循环
//                System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                continue;
            }
            fillStr = (xSheet.getRow(i)).getCell(j).toString();//获取当前单元格的数据
            
//            XSSFRow xRow = xSheet.createRow(i);
//            XSSFCell xCell = xRow.createCell(j);
            XSSFCell xCell=xSheet.getRow(i).getCell(j); //获取单元格对象，这块不能向上边那两句代码那么写，不能用createXXX，用的话会只把第一列的数据改掉
            if(j==2 && i==2){
              
              i=3;
              String a= "";
            //修改数据，看数据是否和字段集合中的数据匹配，不匹配使用元数据
              xCell.setCellValue(1.0);
            }
            
        }
    }

    FileOutputStream out = new FileOutputStream(fileName);
    xwb.write(out);
    out.close();
  }
  
  private static Map<String, String> getFieldMap(){
    Map<String, String> fields = new HashMap<String, String>();
    fields.put("Study Subject ID", "研究主题");
    fields.put("Protocol ID", "协议");
//    try{
//    String fileName = "D:\\yuan.xlsx";
//    XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(fileName));
//
//    XSSFSheet xSheet = xwb.getSheetAt(3);
//    for (int i = 0; i <= xSheet.getLastRowNum(); i++) {
//        fields.put(xSheet.getRow(i).getCell(0).toString(),xSheet.getRow(i).getCell(1).toString());
////        System.out.println("---"+xSheet.getRow(i).getCell(0)+"*---"+fields.get("A1"));
//        }
//    }
//    catch(Exception e){
//        e.printStackTrace();
//    }
    return fields;
 }
  private String getValue(XSSFCell xCell) {
    if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {

        return String.valueOf(xCell.getBooleanCellValue());
    } else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

        return String.valueOf(xCell.getNumericCellValue());
    } else {

        return String.valueOf(xCell.getStringCellValue());
    }

}
}

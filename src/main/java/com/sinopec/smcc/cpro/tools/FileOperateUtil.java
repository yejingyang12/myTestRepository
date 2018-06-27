/**
* Copyright 2017 zsy.com Inc. All Rights Reserved. 
* @Title FileOperateUtil.java
* @Package com.anxinyiheng.tools.file
* @Description: TODO:
* @author eric
* @date 2017年6月16日下午12:04:39
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * @Title FileOperateUtil.java
 * @Package com.anxinyiheng.tools.file
 * @Description: TODO:
 * @author eric
 * @date 2017年6月16日下午12:04:39
 * @version V1.0
 */
public class FileOperateUtil {
  
  /**
   * 文件上传
   * @author eric
   * @date 2018年6月1日下午4:18:21
   * @param bytes MultipartFile对象
   * @param filePath 文件路径
   * @param fileName 文件名称
   * @throws IOException 异常
   */
  public static void uploadFile(byte[] file, String filePath, 
      String fileName) throws IOException {
    File targetFile = new File(filePath);  
    if(!targetFile.exists()){    
        targetFile.mkdirs();    
    }
    FileOutputStream out = new FileOutputStream(filePath+fileName);
    out.write(file);
    out.flush();
    out.close();
  }
  
  /**
   * 文件下载
   * @author eric
   * @date 2018年6月4日下午8:07:59
   * @param request 请求
   * @param response 响应
   * @param filePath 文件路径
   * @param realName 下载后的文件名
   * @param realNameCharacterSet 下载后文件名传入字符集
   * @param realNameExportCharacterSet 下载后文件本地字符集
   * @param fileSize 文件大小，单位KB
   * @throws IOException
   */
  public static void download(HttpServletRequest request,
      HttpServletResponse response, String filePath, 
      String realName, String realNameCharacterSet, String realNameExportCharacterSet, 
      int fileSize) throws IOException {
    //如果是IE浏览器，则用URLEncode解析  
    FileOperateUtil fileOperateUtil = new FileOperateUtil();
    if(fileOperateUtil.isMSBrowser(request)){  
      //realName = URLEncoder.encode(realName, "UTF-8");  
      realName = URLDecoder.decode(realName, "utf-8");
    }
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      response.setContentType("text/html;charset=UTF-8");
      request.setCharacterEncoding("UTF-8");

      bis = null;
      bos = null;
      String downLoadPath = filePath;
      long fileLength = new File(downLoadPath).length();
//      response.setHeader("Content-disposition", "attachment; filename="
//          + new String(realName.getBytes("ISO-8859-1"), "utf-8"));
      response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(realName, "UTF-8"));

      response.setHeader("Content_Length", String.valueOf(fileLength));
      bis = new BufferedInputStream(new FileInputStream(downLoadPath));
      bos = new BufferedOutputStream(response.getOutputStream());
      byte[] buff = new byte[fileSize];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        bos.write(buff, 0, bytesRead);
      }
    } catch (UnsupportedEncodingException e) {
      throw new IOException(e);
    } catch (FileNotFoundException e) {
      throw new IOException(e);
    } catch (IOException e) {
      throw new IOException(e);
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (IOException e) {
          throw new IOException(e);
        }
      }
      if (bos != null) {
        try {
          bos.close();
        } catch (IOException e) {
          throw new IOException(e);
        }
      }
    }
  }
  
  /**
   * 创建多文件压缩包
   * @param response
   * @param dir 文件路径
   * @param srcfile 文件file集合
   * @param expName 文件名
   */
  public static void createRar(HttpServletResponse response,String dir,List<File> srcfile,String expName){
     
     if(!new File(dir).exists()){//检测生成路径
       new File(dir).mkdirs();
     }
    try {
      byte[] buffer;
      File zipfile = new File(dir+"/"+expName+".rar");
      FileOperateUtil.zipFiles(srcfile, zipfile);//生成压缩文件
      FileInputStream fis = new FileInputStream(zipfile);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] b = new byte[1024];
      int n;
      while ((n = fis.read(b)) != -1)
      {
          bos.write(b, 0, n);
      }
      fis.close();
      bos.close();
      buffer = bos.toByteArray();
      FileOperateUtil.uploadFile(buffer, dir, expName+".rar");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /** 
   * 压缩文件  
   *   
   * @param srcfile File[] 需要压缩的文件列表  
   * @param zipfile File 压缩后的文件  
   * @author 
   */  
 public static void zipFiles(List<File> srcfile, File zipfile) {
   byte[] buf = new byte[30960];
   String ZIP_ENCODEING = "GBK"; 
   try {
     ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
     out.setEncoding(ZIP_ENCODEING);
     for (int i = 0; i < srcfile.size(); i++) {
       File file = srcfile.get(i);
       FileInputStream in = new FileInputStream(file);
       out.putNextEntry(new ZipEntry(file.getName()));
       int len;
       while ((len = in.read(buf)) > 0) {
         out.write(buf, 0, len);
       }
       out.closeEntry();
       in.close();
     }
     out.close();
   } catch (IOException e) {
     e.printStackTrace(); 
   }
 }
 
 /**
  * 递归删除文件夹下内容
  *
  * @param file
  */
  public static void deleteFile(File file) {
    if (file.exists()) {
      if (file.isFile()) {
        file.delete();
      } else if (file.isDirectory()) {
        File files[] = file.listFiles();
        for (int i = 0; i < files.length; i++) {
           deleteFile(files[i]);
        }
      }
       file.delete();
    } else {
      System.out.println("所删除的文件不存在！" + '\n');
    }
  }
  
  /**
   * 创建excel
   * @param headName 表头
   * @param list 数据字符串集合
   * @param expName 文件名
   * @param dir 文件路径
   * @return
   * @throws Exception
   */
  public static File createExcel(List<List<String>> list, String expName,String dir) throws Exception {
    // 格式化时间
      HSSFWorkbook workbook = new HSSFWorkbook();
      HSSFSheet sheet = workbook.createSheet("sheet1");
      // 创建表头
      HSSFRow row = sheet.createRow(0);
      HSSFCell cell = row.createCell((short) 0);
      for (int x = 0; x < list.size(); x++) {//循环数据信息
          row = sheet.createRow(x + 1);
          List<String> rowString = list.get(x);
          for (int i = 0; i < rowString.size(); i++) {
              cell = row.createCell((short) i);
              cell.setCellValue(rowString.get(i));
          }
      }
      File file = new File(dir + "/" + expName + ".xls");//生成excel文件
      if (!new File(dir).exists()) {
          new File(dir).mkdirs();
      }
      FileOutputStream fos = new FileOutputStream(file);
      workbook.write(fos);
      fos.close();
      return file;
  }
  
  /*** 
   * 删除指定文件夹下所有文件 
   *  
   * @param path 文件夹完整绝对路径 
   * @return 
   */  
  public static  boolean deleteAllFile(String path) {  
    boolean flag = false;  
    File file = new File(path);  
    if (!file.exists()) {  
      return flag;  
    }  
    if (!file.isDirectory()) {  
      return flag;  
    }  
    String[] tempList = file.list();  
    File temp = null;  
    for (int i = 0; i < tempList.length; i++) {  
      if (path.endsWith(File.separator)) {  
        temp = new File(path + tempList[i]);  
      } else {  
        temp = new File(path + File.separator + tempList[i]);  
      }  
      if (temp.isFile()) {  
        temp.delete();  
      }  
      if (temp.isDirectory()) {  
        deleteAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件  
        delFolder(path + "/" + tempList[i]);// 再删除空文件夹  
        flag = true;  
      }  
    }  
    return flag;  
  }  
    
  /*** 
   * 删除文件夹 
   *  
   * @param folderPath文件夹完整绝对路径 
   */  
  public  static void delFolder(String folderPath) {  
    try {  
      deleteAllFile(folderPath); // 删除完里面所有内容  
      String filePath = folderPath;  
      filePath = filePath.toString();  
      java.io.File myFilePath = new java.io.File(filePath);  
      myFilePath.delete(); // 删除空文件夹  
    } catch (Exception e) {  
      e.printStackTrace();  
    }  
  }  
  
  /**
   * 判断是否是IE浏览器  
   */
  public boolean isMSBrowser(HttpServletRequest request) {  
      String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};  
      String userAgent = request.getHeader("User-Agent");  
      for (String signal : IEBrowserSignals) {  
          if (userAgent.contains(signal)){  
              return true;  
          }  
      }  
      return false;  
  } 
}

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
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
   * 根据字符串路径初始化一个文件
   * @author eric
   * @date 2017年6月20日下午12:48:53
   * @param filePathName 初始化的文件路径
   * @return 初始化的文件对象
   * @throws IOException 路径不正确时抛出异常
   */
  public static File newFile(String filePathName) throws IOException {
    if (filePathName == null||"".equals(filePathName)) {
      throw new IOException("Invalid file path");
    }
    return new File(filePathName);
  }
  
  /**
   * 根据URI路径初始化一个文件
   * @author eric
   * @date 2017年6月20日下午12:56:39
   * @param uri
   * @return 初始化的文件对象
   * @throws IOException 文件或文件路径不存在
   */
  public static File newFile(URI uri) throws IOException {
    if (uri == null) {
      throw new IOException("Invalid file path");
    }
    return new File(uri);
  }
  
  /**
   * 根据一个父级路径初始化一个文件
   * @author eric
   * @date 2017年6月20日下午1:05:29
   * @param file 一个路径的file对象
   * @param fileName 文件名
   * @return 初始化的文件对象
   * @throws IOException 文件或文件路径不存在
   */
  public static File newFile(File file, String fileName) throws IOException {
    if (fileName == null||"".equals(fileName)) {
      throw new IOException("Invalid file name");
    }
    return new File(file, fileName);
  }
  
  /**
   * 根据一个父级路径初始化一个文件
   * @author eric
   * @date 2017年6月20日下午6:40:44
   * @param filePathName 初始化的文件夹路径
   * @param fileName 文件名
   * @return
   * @throws IOException 文件或文件路径不存在
   */
  public static File newFile(String filePathName, String fileName) throws IOException {
    if (fileName == null||"".equals(fileName)) {
      throw new IOException("Invalid file name");
    }
    return new File(filePathName, fileName);
  }
  
  /**
   * 判断此文件是否是可执行文件，windows下无效。
   * @author eric
   * @date 2017年6月20日下午1:07:10
   * @param file 文件对象
   * @return boolean：true：此文件是可执行文件；
   *                 false：此文件不可执行；
   * @throws IOException 文件或文件路径不存在
   */
  public static boolean canExecute(File file) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.canExecute();
  }
  
  /**
   * 判断此文件是否可读
   * @author eric
   * @date 2017年6月20日下午1:16:35
   * @param file 文件对象
   * @return boolean：true：可读
   *                 false：不可读
   * @throws IOException 文件或文件路径不存在
   */
  public static boolean canRead(File file) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.canRead();
  }
  
  /**
   * 判断此文件是否可写
   * @author eric
   * @date 2017年6月20日下午1:48:49
   * @param file 文件对象
   * @return boolean：true：可写
   *                 false：不可写
   * @throws IOException 文件或文件路径不存在
   */
  public static boolean canWrite(File file) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.canWrite();
  }
  
  /**
   * 判断两个文件的抽象路径名的字典顺序大小
   * @author eric
   * @date 2017年6月20日下午1:53:30
   * @param file 文件对象
   * @param filePathName 被比较的文件对象
   * @return  0：相等
   *         >0：file大于filePathName的位数
   *         <0：file小于filePathName的位数
   * @throws IOException 文件或文件路径不存在
   */
  public static int compareTo(File file, File filePathName) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    if (!exists(file)) {
      throw new IOException("Invalid filePathName path");
    }
    return file.compareTo(filePathName);
  }
  
  /**
   * 通过字符串路径新建一个文件，如果创建失败返回false
   * @author eric
   * @date 2017年6月20日下午2:06:48
   * @param filePathName 字符串文件路径
   * @return boolean：true：创建成功
   *                 false：创建失败，一般为已存在此文件
   * @throws IOException 创建失败，一般为路径不存在
   */
  public static boolean createNewFile(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return createNewFile(file);
  }
  
  /**
   * 通过文件对象新建这个文件，如果创建失败返回false
   * @author eric
   * @date 2017年6月20日下午2:05:12
   * @param file 文件对象
   * @return boolean：true：创建成功
   *                 false：创建失败，一般为已存在此文件
   * @throws IOException 创建失败，一般为路径不存在
   */
  public static boolean createNewFile(File file) throws IOException{
    return file.createNewFile();
  }
  
  /**
   * 创建一个文件，当文件存在时使用已存在文件，文件未存在时创建文件
   * @author eric
   * @date 2017年6月20日下午6:44:22
   * @param filePathName 字符串文件路径
   * @return boolean：true：创建成功
   *                 false：创建失败
   * @throws IOException 创建失败，一般为路径不存在
   */
  public static boolean createFile(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return createFile(file);
  }
  
  /**
   * 创建一个文件，当文件存在时使用已存在文件，文件未存在时创建文件
   * @author eric
   * @date 2017年6月20日下午6:42:38
   * @param file 文件对象
   * @return boolean：true：创建成功
   *                 false：创建失败
   * @throws IOException 创建失败，一般为路径不存在
   */
  public static boolean createFile(File file) throws IOException{
    if (exists(file)) {
      return true;
    }
    if (!exists(file.getParentFile())) {
      mkdirs(file.getParentFile().getAbsolutePath());
    }
    return createNewFile(file);
  }
  
  /**
   * 通过文件对象删除这个文件，如果创建失败返回false
   * @author eric
   * @date 2017年6月20日下午2:24:32
   * @param filePathName 字符串格式的文件路径
   * @return boolean true：删除成功
   *                false：删除失败，一般为权限不足或文件路径不存在
   */
  public static boolean deleteFile(String filePathName) {
    File file = new File(filePathName);
    return deleteFile(file);
  }
  
  /**
   * 通过文件对象删除这个文件，如果创建失败返回false
   * @author eric
   * @date 2017年6月20日下午2:10:51
   * @param file 文件对象
   * @return boolean true：删除成功
   *                false：删除失败，一般为权限不足或文件路径不存在
   */
  public static boolean deleteFile(File file){
    return file.delete();
  }
  
  /**
   * 在java虚拟机关闭时删除此文件，通过字符串格式的文件路径
   * @author eric
   * @date 2017年6月20日下午2:27:26
   * @param filePathName 字符串格式的文件路径
   */
  public static void deleteOnExit(String filePathName){
    File file = new File(filePathName);
    file.deleteOnExit();
  }
  
  /**
   * 在java虚拟机关闭时删除此文件，通过文件对象
   * @author eric
   * @date 2017年6月20日下午2:26:12
   * @param file 文件对象
   */
  public static void deleteOnExit(File file){
    file.deleteOnExit();
  }
  
  /**
   * 对比两个文件名是否相同
   * @author eric
   * @date 2017年6月20日下午2:33:56
   * @param file 文件对象
   * @param filePathName 被比较的文件对象
   * @return  0：相等
   *         >0：file大于filePathName的位数
   *         <0：file小于filePathName的位数
   * @throws IOException 文件或文件路径不存在
   */
  public static boolean equals(File file, File filePathName) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    if (!exists(file)) {
      throw new IOException("Invalid filePathName path");
    }
    return file.equals(filePathName);
  }
  
  /**
   * 判断文件是否存在
   * @author eric
   * @date 2017年6月20日下午2:34:59
   * @param file 文件对象
   * @return boolean：true：文件存在
   *                 false：文件不存在
   */
  public static boolean exists(File file){
    return file.exists();
  }
  
  /**
   * 通过字符串路径获得此文件的绝对文件路径，返回file
   * @author eric
   * @date 2017年6月20日下午2:44:41
   * @param filePathName 字符串路径
   * @return file 文件对象
   * @throws IOException 文件或文件路径不存在
   */
  public static File getAbsoluteFile(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return getAbsoluteFile(file);
  }
  
  /**
   * 通过文件路径获得此文件的绝对文件路径，返回file
   * @author eric
   * @date 2017年6月20日下午2:42:51
   * @param file 文件对象
   * @return file 绝对路径的文件对象
   */
  public static File getAbsoluteFile(File file){
    return file.getAbsoluteFile();
  }
  
  /**
   * 通过字符串路径获得此文件的绝对文件路径，返回字符串
   * @author eric
   * @date 2017年6月20日下午2:49:46
   * @param filePathName 字符串路径
   * @return String 绝对路径字符串
   * @throws IOException 文件或文件路径不存在
   */
  public static String getAbsolutePath(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return getAbsolutePath(file);
  }
  
  /**
   * 通过文件路径获得此文件的绝对文件路径，返回文件路径字符串
   * @author eric
   * @date 2017年6月20日下午2:48:24
   * @param file 文件对象
   * @return String 绝对路径字符串
   */
  public static String getAbsolutePath(File file){
    return file.getAbsolutePath();
  }
  
  /**
   * 返回此文件的路径规范
   * @author eric
   * @date 2017年6月20日下午2:54:42
   * @param filePathName 字符串路径
   * @return file 符合路径规范的绝对路径的文件对象
   * @throws IOException 文件路径不正确
   */
  public static File getCanonicalFile(String filePathName) throws IOException{
    File file = new File(filePathName);
    return getCanonicalFile(file);
  }
  
  /**
   * 返回此文件的路径规范
   * @author eric
   * @date 2017年6月20日下午2:51:30
   * @param file 文件对象
   * @return file 符合路径规范的绝对路径的文件对象
   * @throws IOException 文件路径不正确
   */
  public static File getCanonicalFile(File file) throws IOException{
    return file.getCanonicalFile();
  }
  
  /**
   * 返回此文件的路径规范
   * @author eric
   * @date 2017年6月20日下午2:55:55
   * @param filePathName 字符串路径
   * @return String 符合路径规范的绝对路径的字符串
   * @throws IOException 文件路径不正确
   */
  public static String getCanonicalPath(String filePathName) throws IOException{
    File file = new File(filePathName);
    return getCanonicalPath(file);
  }
  
  /**
   * 返回此文件的路径规范
   * @author eric
   * @date 2017年6月20日下午2:56:35
   * @param file 文件对象
   * @return String 符合路径规范的绝对路径的字符串
   * @throws IOException 文件路径不正确
   */
  public static String getCanonicalPath(File file) throws IOException{
    return file.getCanonicalPath();
  }
  
  /**
   * 文件所在磁盘剩余的硬盘空间（字节）
   * @author eric
   * @date 2017年6月20日下午3:04:02
   * @param filePathName 字符串路径
   * @return >0：文件所在硬盘剩余的硬盘空间字节大小
   *          0：文件不存在
   * @throws IOException 文件路径不正确
   */
  public static long getFreeSpace(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getFreeSpace();
  }
  
  /**
   * 文件所在磁盘剩余的硬盘空间（字节）
   * @author eric
   * @date 2017年6月20日下午2:59:52
   * @param file 文件对象
   * @return >0：文件所在硬盘剩余的硬盘空间字节大小
   *          0：文件不存在
   */
  public static long getFreeSpace(File file){
    return file.getFreeSpace();
  }
  
  /**
   * 获得此文件路径的文件名称
   * @author eric
   * @date 2017年6月20日下午3:26:03
   * @param filePathName 字符串路径
   * @return String 文件名称
   * @throws IOException 文件路径不正确
   */
  public static String getName(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getName();
  }
  
  /**
   * 获得此文件路径的文件名称
   * @author eric
   * @date 2017年6月20日下午3:25:39
   * @param file 文件对象
   * @return String 文件名称
   */
  public static String getName(File file){
    return file.getName();
  }
  
  /**
   * 获得此文件的文件绝对路径（不包含文件名）
   * @author eric
   * @date 2017年6月20日下午3:29:03
   * @param filePathName 文件对象
   * @return String  文件路径（不包含文件名）
   * @throws IOException 文件路径不正确
   */
  public static String getParent(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getParent();
  }
  
  /**
   * 获得此文件的文件绝对路径（不包含文件名）
   * @author eric
   * @date 2017年6月20日下午3:30:07
   * @param file 文件对象
   * @return String  文件路径（不包含文件名）
   */
  public static String getParent(File file){
    return file.getParent();
  }
  
  /**
   * 获得此文件的文件绝对路径（不包含文件名）
   * @author eric
   * @date 2017年6月20日下午3:32:13
   * @param filePathName 文件对象
   * @return File  文件对象（一般为传入文件的文件夹对象）
   * @throws IOException 文件路径不正确
   */
  public static File getParentFile(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getParentFile();
  }
  
  /**
   * 获得此文件的文件绝对路径（不包含文件名）
   * @author eric
   * @date 2017年6月20日下午3:33:39
   * @return File  文件对象
   * @return File  文件对象（一般为传入文件的文件夹对象）
   */
  public static File getParentFile(File file){
    return file.getParentFile();
  }
  
  /**
   * 获得初始化此文件时的文件路径，不保证是绝对路径
   * @author eric
   * @date 2017年6月20日下午3:47:08
   * @param filePathName 文件对象
   * @return String 初始化此文件时的文件路径
   * @throws IOException 文件路径不正确
   */
  public static String getPath(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getPath();
  }
  
  /**
   * 获得初始化此文件时的文件路径，不保证是绝对路径
   * @author eric
   * @date 2017年6月20日下午3:48:29
   * @return String 初始化此文件时的文件路径
   * @return File  文件对象（一般为传入文件的文件夹对象）
   */
  public static String getPath(File file){
    return file.getPath();
  }
  
  /**
   * 文件所在磁盘全部的硬盘空间（字节）
   * @author eric
   * @date 2017年6月20日下午3:52:42
   * @param filePathName 字符串路径
   * @return >0：文件所在硬盘全部的硬盘空间字节大小
   *          0：文件不存在
   * @throws IOException 文件路径不正确
   */
  public static long getTotalSpace(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getTotalSpace();
  }
  
  /**
   * 文件所在磁盘全部的硬盘空间（字节）
   * @author eric
   * @date 2017年6月20日下午3:53:05
   * @param file 文件对象
   * @return >0：文件所在硬盘全部的硬盘空间字节大小
   *          0：文件不存在
   */
  public static long getTotalSpace(File file){
    return file.getTotalSpace();
  }
  
  /**
   * 文件所在磁盘剩余的硬盘空间（字节）比getFreeSpace更准确。
   * @author eric
   * @date 2017年6月20日下午4:00:49
   * @param filePathName 字符串路径
   * @return >0：文件所在硬盘剩余的硬盘空间字节大小
   *          0：文件不存在
   * @throws IOException 文件路径不正确
   */
  public static long getUsableSpace(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.getUsableSpace();
  }
  
  /**
   * 文件所在磁盘剩余的硬盘空间（字节）比getFreeSpace更准确。
   * @author eric
   * @date 2017年6月20日下午4:01:30
   * @param file 文件对象
   * @return >0：文件所在硬盘剩余的硬盘空间字节大小
   *          0：文件不存在
   */
  public static long getUsableSpace(File file){
    return file.getUsableSpace();
  }
  
  /**
   * 检查此路径是否为绝对路径
   * @author eric
   * @date 2017年6月20日下午4:04:01
   * @param filePathName 字符串路径
   * @return boolean：true：此路径是绝对路径
   *                 false：此路径不是绝对路径
   * @throws IOException 文件路径不正确
   */
  public static boolean isAbsolute(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.isAbsolute();
  }
  
  /**
   * 检查此路径是否为绝对路径
   * @author eric
   * @date 2017年6月20日下午4:05:24
   * @param file 文件对象
   * @return boolean：true：此路径是绝对路径
   *                 false：此路径不是绝对路径
   */
  public static boolean isAbsolute(File file){
    return file.isAbsolute();
  }
  
  /**
   * 检查此路径是否存在并且是一个文件夹路径（不包含文件名）
   * @author eric
   * @date 2017年6月20日下午4:13:25
   * @param filePathName 字符串路径
   * @return boolean：true：此路径是文件夹路径并且存在
   *                 false：此路径不是文件夹路径或不存在
   * @throws IOException 文件路径不正确
   */
  public static boolean isDirectory(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.isDirectory();
  }
  
  /**
   * 检查此路径是否存在并且是一个文件夹路径（不包含文件名）
   * @author eric
   * @date 2017年6月20日下午4:14:06
   * @param file 文件对象
   * @return boolean：true：此路径是文件夹路径并且存在
   *                 false：此路径不是文件夹路径或不存在
   */
  public static boolean isDirectory(File file){
    return file.isDirectory();
  }
  
  /**
   * 检查此路径是否存在并且是一个文件路径
   * @author eric
   * @date 2017年6月20日下午4:15:37
   * @param filePathName 字符串路径
   * @return boolean：true：此路径是文件路径并且存在
   *                 false：此路径不是文件路径或不存在
   * @throws IOException 文件路径不正确
   */
  public static boolean isFile(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.isFile();
  }
  
  /**
   * 检查此路径是否存在并且是一个文件路径
   * @author eric
   * @date 2017年6月20日下午4:16:13
   * @param file 文件对象
   * @return boolean：true：此路径是文件路径并且存在
   *                 false：此路径不是文件路径或不存在
   */
  public static boolean isFile(File file){
    return file.isFile();
  }
  
  /**
   * 判断此文件是否是隐藏文件
   * @author eric
   * @date 2017年6月20日下午4:15:37
   * @param filePathName 字符串路径
   * @return boolean：true：此文件是隐藏文件
   *                 false：此文件不是一个隐藏文件
   * @throws IOException 文件路径不正确
   */
  public static boolean isHidden(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.isHidden();
  }
  
  /**
   * 判断此文件是否是隐藏文件
   * @author eric
   * @date 2017年6月20日下午4:16:13
   * @param file 文件对象
   * @return boolean：true：此文件是隐藏文件
   *                 false：此文件不是一个隐藏文件
   */
  public static boolean isHidden(File file){
    return file.isHidden();
  }
  
  /**
   * 返回此文件最后的修改时间（毫秒）
   * @author eric
   * @date 2017年6月20日下午4:19:45
   * @param filePathName 字符串路径
   * @return long：>0：最后修改时间的毫秒数
   *                0：此文件不存在
   * @throws IOException 文件路径不正确
   */
  public static long lastModified(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.lastModified();
  }
  
  /**
   * 返回此文件最后的修改时间（毫秒）
   * @author eric
   * @date 2017年6月20日下午4:20:37
   * @param file 文件对象
   * @return long：>0：最后修改时间的毫秒数
   *                0：此文件不存在
   */
  public static long lastModified(File file){
    return file.lastModified();
  }
  
  /**
   * 返回此文件的大小（字节）
   * @author eric
   * @date 2017年6月20日下午4:23:52
   * @param filePathName 字符串路径
   * @return long：-1：此文件不存在
   *              >=0：文件的字节数
   * @throws IOException 文件路径不正确
   */
  public static long length(String filePathName) throws IOException{
    File file = newFile(filePathName);
    if (exists(file)) {
      return -1;
    }
    return file.length();
  }
  
  /**
   * 返回此文件的大小（字节）
   * @author eric
   * @date 2017年6月20日下午4:25:43
   * @param file 文件对象
   * @return long：-1：此文件不存在
   *              >=0：文件的字节数
   */
  public static long length(File file){
    if (exists(file)) {
      return -1;
    }
    return file.length();
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的所有文件
   * @author eric
   * @date 2017年6月20日下午4:32:02
   * @param filePathName 字符串路径
   * @return String[]：null：此文件夹不存在
   *                   其他：此文件夹下文件名的数组
   * @throws IOException 文件路径不正确
   */
  public static String[] list(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.list();
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的所有文件
   * @author eric
   * @date 2017年6月20日下午4:34:27
   * @param file 文件对象
   * @return String[]：null：此文件夹不存在
   *                   其他：此文件夹下文件名的数组
   */
  public static String[] list(File file){
    return file.list();
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的按照条件筛选的文件
   * @author eric
   * @date 2017年6月20日下午5:14:00
   * @param filePathName 字符串文件路径
   * @param filenameFilter 筛选条件对象
   *                       可继承com.anxinyiheng.tools.file.AbsFilenameFilter抽象类
   *                       或实现java.io.FilenameFilter接口，获得accept方法，
   *                       通过accept方法实现文件的筛选
   * @return String[]：null：此文件夹不存在
   *                   其他：此文件夹下文件名的数组
   * @throws IOException 文件路径不正确
   */
  public static String[] list(String filePathName, FilenameFilter filenameFilter) throws IOException{
    File file = newFile(filePathName);
    return file.list(filenameFilter);
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的按照条件筛选的文件
   * @author eric
   * @date 2017年6月20日下午5:20:35
   * @param file 文件对象
   * @param filenameFilter 筛选条件对象
   *                       可继承com.anxinyiheng.tools.file.AbsFilenameFilter抽象类
   *                       或实现java.io.FilenameFilter接口，获得accept方法，
   *                       通过accept方法实现文件的筛选
   * @return String[]：null：此文件夹不存在
   *                   其他：此文件夹下文件名的数组
   */
  public static String[] list(File file, FilenameFilter filenameFilter){
    return file.list(filenameFilter);
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的所有文件
   * @author eric
   * @date 2017年6月20日下午5:24:11
   * @param filePathName 字符串路径
   * @return File[]：null：此文件夹不存在
   *                 其他：此文件夹下文件的数组
   * @throws IOException 文件路径不正确
   */
  public static File[] listFiles(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.listFiles();
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的所有文件
   * @author eric
   * @date 2017年6月20日下午5:24:56
   * @param file 文件对象
   * @return File[]：null：此文件夹不存在
   *                 其他：此文件夹下文件的数组
   */
  public static File[] listFiles(File file){
    return file.listFiles();
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的按照条件筛选的文件
   * @author eric
   * @date 2017年6月20日下午5:25:08
   * @param filePathName 字符串文件路径
   * @param filenameFilter 筛选条件对象
   *                       可继承com.anxinyiheng.tools.file.AbsFilenameFilter抽象类
   *                       或实现java.io.FilenameFilter接口，获得accept方法，
   *                       通过accept方法实现文件的筛选
   * @return File[]：null：此文件夹不存在
   *                 其他：此文件夹下文件的数组
   * @throws IOException 文件路径不正确
   */
  public static File[] listFiles(String filePathName, 
      FilenameFilter filenameFilter) throws IOException{
    File file = newFile(filePathName);
    return file.listFiles(filenameFilter);
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的按照条件筛选的文件
   * @author eric
   * @date 2017年6月20日下午5:25:39
   * @param file 文件对象
   * @param filenameFilter 筛选条件对象
   *                       可继承com.anxinyiheng.tools.file.AbsFilenameFilter抽象类
   *                       或实现java.io.FilenameFilter接口，获得accept方法，
   *                       通过accept方法实现文件的筛选
   * @return File[]：null：此文件夹不存在
   *                   其他：此文件夹下文件的数组
   */
  public static File[] listFiles(File file, FilenameFilter filenameFilter){
    return file.listFiles(filenameFilter);
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的按照条件筛选的文件
   * @author eric
   * @date 2017年6月20日下午5:29:35
   * @param filePathName 字符串文件路径
   * @param fileFilter 筛选条件对象
 *                     可继承com.anxinyiheng.tools.file.AbsFileFilter抽象类
   *                   或实现java.io.FileFilter接口，获得accept方法，
   *                   通过accept方法实现文件的筛选
   * @return File[]：null：此文件夹不存在
   *                 其他：此文件夹下文件的数组
   * @throws IOException 文件路径不正确
   */
  public static File[] listFiles(String filePathName, 
      FileFilter fileFilter) throws IOException{
    File file = newFile(filePathName);
    return file.listFiles(fileFilter);
  }
  
  /**
   * 返回此文件夹路径（不包含文件名称）下的按照条件筛选的文件
   * @author eric
   * @date 2017年6月20日下午5:31:59
   * @param file 文件对象
   * @param fileFilter 筛选条件对象
   *                   可继承com.anxinyiheng.tools.file.AbsFileFilter抽象类
   *                   或实现java.io.FileFilter接口，获得accept方法，
   *                   通过accept方法实现文件的筛选
   * @return File[]：null：此文件夹不存在
   *                   其他：此文件夹下文件的数组
   */
  public static File[] listFiles(File file, FileFilter fileFilter){
    return file.listFiles(fileFilter);
  }
  
  /**
   * 返回此硬盘的所有盘符的文件对象
   * @author eric
   * @date 2017年6月20日下午5:39:28
   * @return File[]:盘符对象
   */
  public static File[] length(){
    return File.listRoots();
  }
  
  /**
   * 创建此文件夹路径(不包含文件名)的文件夹
   * @author eric
   * @date 2017年6月20日下午5:47:49
   * @param filePathName 字符串路径
   * @return boolean：true：创建此文件夹路径成功
   *                 false：创建此文件夹路径失败或路径不存在
   * @throws IOException 文件路径不正确
   */
  public static boolean mkdir(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.mkdir();
  }
  
  /**
   * 创建此文件夹路径(不包含文件名)的文件夹
   * @author eric
   * @date 2017年6月20日下午5:50:19
   * @param file 文件对象
   * @return boolean：true：创建此文件夹路径成功
   *                 false：创建此文件夹路径失败或路径不存在
   */
  public static boolean mkdir(File file){
    return file.mkdir();
  }
  
  /**
   * 创建此文件夹路径(不包含文件名)的文件夹，并且如父级路径不存在则支持一直向上创建
   * @author eric
   * @date 2017年6月20日下午5:52:14
   * @param filePathName 字符串路径
   * @return boolean：true：创建此文件夹路径成功
   *                 false：创建此文件夹路径失败或路径不存在
   * @throws IOException 文件路径不正确
   */
  public static boolean mkdirs(String filePathName) throws IOException{
    File file = newFile(filePathName);
    return file.mkdirs();
  }
  
  /**
   * 创建此文件夹路径(不包含文件名)的文件夹，并且如父级路径不存在则支持一直向上创建
   * @author eric
   * @date 2017年6月20日下午5:53:41
   * @param file 文件对象
   * @return boolean：true：创建此文件夹路径成功
   *                 false：创建此文件夹路径失败或路径不存在
   */
  public static boolean mkdirs(File file){
    return file.mkdirs();
  }
  
  /**
   * 将文件filePathName更名成reFilePathName
   * @author eric
   * @date 2017年6月20日下午5:54:15
   * @param filePathName 被更改的路径字符串
   * @param reFilePathName 更改至的路径字符串
   * @return boolean：true：更名成功
   *                 false：修改此文件文件名失败
   * @throws IOException 文件路径不正确
   */
  public static boolean renameTo(String filePathName, String reFilePathName) throws IOException{
    File file = newFile(filePathName);
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    File reFile = newFile(reFilePathName);
    if (exists(reFile)) {
      throw new IOException("Exists reFile path");
    }
    return file.renameTo(reFile);
  }
  
  /**
   * 创建此文件夹路径(不包含文件名)的文件夹，并且如父级路径不存在则支持一直向上创建
   * @author eric
   * @date 2017年6月20日下午5:56:05
   * @param file 被更改的文件对象
   * @param reFile 更改至的文件对象
   * @return boolean：true：更名成功
   *                 false：修改此文件文件名失败
   * @throws IOException 文件路径不正确
   */
  public static boolean renameTo(File file, File reFile) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    if (exists(reFile)) {
      throw new IOException("Exists reFile path");
    }
    return file.renameTo(reFile);
  }
  
  /**
   * 修改文件的最后修改时间
   * @author eric
   * @date 2017年6月20日下午6:06:36
   * @param filePathName 字符串路径
   * @param date 需要修改至的时间
   * @return boolean：true：修改成功
   *                 false：修改失败
   * @throws IOException 文件路径不正确
   */
  public static boolean setLastModified(String filePathName, Date date) throws IOException{
    File file = newFile(filePathName);
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.setLastModified(date.getTime());
  }
  
  /**
   * 修改文件的最后修改时间
   * @author eric
   * @date 2017年6月20日下午6:10:00
   * @param file 文件对象
   * @param date 需要修改至的时间
   * @return boolean：true：修改成功
   *                 false：修改失败
   * @throws IOException 文件路径不正确
   */
  public static boolean setLastModified(File file, Date date) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.setLastModified(date.getTime());
  }
  
  /**
   * 设置该文件的只读权限
   * @author eric
   * @date 2017年6月20日下午6:12:35
   * @param filePathName 字符串路径
   * @return boolean：true：设置成功
   *                 false：设置失败
   * @throws IOException 文件路径不正确
   */
  public static boolean setReadOnly(String filePathName) throws IOException{
    File file = newFile(filePathName);
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.setReadOnly();
  }
  
  /**
   * 设置该文件的只读权限
   * @author eric
   * @date 2017年6月20日下午6:13:24
   * @param file 文件对象
   * @return boolean：true：设置成功
   *                 false：设置失败
   * @throws IOException 文件路径不正确
   */
  public static boolean setReadOnly(File file) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.setReadOnly();
  }
  
  /**
   * 获得文件的URI对象
   * @author eric
   * @date 2017年6月20日下午6:18:33
   * @param filePathName 字符串路径
   * @return URI 返回本文件的uri的对象
   * @throws IOException 文件路径不正确
   */
  public static URI toURI(String filePathName) throws IOException{
    File file = newFile(filePathName);
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.toURI();
  }
  
  /**
   * @Descrption
   * @author eric
   * @date 2017年6月20日下午6:20:13
   * @param file 文件对象
   * @return URI 返回本文件的uri的对象
   * @throws IOException 文件路径不正确
   */
  public static URI toURI(File file) throws IOException{
    if (!exists(file)) {
      throw new IOException("Invalid file path");
    }
    return file.toURI();
  }
  
  /**
   * @Descrption
   * @author eric
   * @date 2017年6月16日下午12:08:36
   * @param request 请求
   * @param response 响应
   * @param filePath 文件路径
   * @param storeName 本地文件名
   * @param contentType 文件的属性
   * @param realName 下载后的文件名
   * @param realNameCharacterSet 下载后文件名传入字符集
   * @param realNameExportCharacterSet 下载后文件本地字符集
   * @param fileSize 文件大小，单位KB
   * @throws IOException
   */
  public static void download(HttpServletRequest request,
      HttpServletResponse response, String filePath, String storeName, String contentType,
      String realName, String realNameCharacterSet, String realNameExportCharacterSet, 
      int fileSize) throws IOException {
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      response.setContentType("text/html;charset=UTF-8");
      request.setCharacterEncoding("UTF-8");
      bis = null;
      bos = null;
      String downLoadPath = filePath + storeName;
      long fileLength = new File(downLoadPath).length();
//    response.setContentType(contentType);
      response.setHeader("Content-disposition", "attachment; filename="
          + new String(realName.getBytes(realNameCharacterSet), realNameExportCharacterSet));
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
}

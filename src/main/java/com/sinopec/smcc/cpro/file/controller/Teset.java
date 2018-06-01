/**
* 2018. 
* @Title Teset.java
* @Package com.sinopec.smcc.cpro.file.controller
* @Description: TODO:
* @author zhouyu
* @date 2018年5月31日下午1:40:17
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinopec.smcc.cpro.file.entity.AttachFile;
import com.sinopec.smcc.cpro.file.entity.SysAttachFile;
import com.sinopec.smcc.cpro.file.server.AttachFileService;

/**
 * @Title Teset.java
 * @Package com.sinopec.smcc.cpro.file.controller
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月31日下午1:40:17
 * @version V1.0
 */
public class Teset {
  
  @Autowired
  public static AttachFileService attachFileService;

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月31日下午1:40:17
   * @param args
   */
  public static void main(String[] args) {
    // file(内存)----输入流---->【程序】----输出流---->file(内存)  
    File file = new File("d:/temp", "addfile.txt");  
    List<AttachFile> list = new ArrayList<AttachFile>();
    AttachFile sysAttachFile = new AttachFile();
    sysAttachFile.setFileName("213sadasa");
    String fileNameId = "addfile.txt";
    HttpServletRequest request = null;
//    attachFileService.SaveAttachFile(request, file, "repr", fileNameId);
//    sysAttachFile.setFiles(file);
    list.add(sysAttachFile);
    System.out.println(list.toString());
    try {  
        file.createNewFile(); // 创建文件  
    } catch (IOException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  

    // 向文件写入内容(输出流)  
    String str = "亲爱的小南瓜！";  
    byte bt[] = new byte[1024];  
    bt = str.getBytes();  
    try {  
        FileOutputStream in = new FileOutputStream(file);  
        try {  
            in.write(bt, 0, bt.length);  
            in.close();  
            // boolean success=true;  
            // System.out.println("写入文件成功");  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    } catch (FileNotFoundException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  
    try {  
        // 读取文件内容 (输入流)  
        FileInputStream out = new FileInputStream(file);  
        InputStreamReader isr = new InputStreamReader(out);  
        int ch = 0;  
        while ((ch = isr.read()) != -1) {  
            System.out.print((char) ch);  
        }  
    } catch (Exception e) {  
        // TODO: handle exception  
    }

  }

}

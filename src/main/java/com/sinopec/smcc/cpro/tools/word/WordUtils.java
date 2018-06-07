/**
* 2018. 
* @Title WordUtils.java
* @Package com.sinopec.smcc.cpro.tools.word
* @Description: TODO:
* @author dongxu
* @date 2018年6月7日下午2:18:26
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools.word;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * @Title WordUtils.java
 * @Package com.sinopec.smcc.cpro.tools.word
 * @Description: TODO:
 * @author dongxu
 * @date 2018年6月7日下午2:18:26
 * @version V1.0
 */
public class WordUtils {
  private static Configuration configuration = null;    
  
  public WordUtils(){    
    configuration = new Configuration();    
    configuration.setDefaultEncoding("UTF-8");    
  }    
  /**
   * 创建word文档
   * @Descrption dataMap里存入List
   * @author dongxu
   * @date 2018年6月7日下午9:38:59
   * @param dataMap
   */
  public static void createWord(Map<String,Object> dataMap){       
//    getData(dataMap);  
    WordUtils wordUtils = new WordUtils();
    configuration.setClassForTemplateLoading(wordUtils.getClass(), "/word/");  //FTL文件所存在的位置    
    Template t=null;    
    try {    
        t = configuration.getTemplate("company.ftl"); //文件名    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    File outFile = new File("F:\\桌面应用\\wordFtl\\测试文档.doc");  //导出文档的存放位置  
    Writer out = null;    
    try {    
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));    
    } catch (FileNotFoundException e1) {    
        e1.printStackTrace();    
    }        
    try {    
        t.process(dataMap, out);    
    } catch (TemplateException e) {    
        e.printStackTrace();    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
  }    
  
//  //填充模板参数
//  private void getData(Map<String, Object> dataMap) {    
//    dataMap.put("factoryNull", "121");    
//    dataMap.put("iNull", "1");  
//    dataMap.put("modelPara","222");  
//    dataMap.put("factorySame","55");  
//    dataMap.put("companyName","66"); 
//             
//    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();    
//    for (int i = 0; i < 10; i++) {    
//        Map<String,Object> map = new HashMap<String,Object>();    
//        map.put("number", i);    
//        map.put("content", "内容"+i);    
//        list.add(map);    
//    }     
//    dataMap.put("list", list);    
//  }    
  
  //懒汉式单例类.在第一次调用的时候实例化自己   
  public static class Singleton {  
      private Singleton() {}  
      private static Singleton single=null;  
      //静态工厂方法   
      public static Singleton getInstance() {  
           if (single == null) {    
               single = new Singleton();  
           }    
          return single;  
      }  
  }   

}

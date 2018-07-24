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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.sinopec.smcc.cpro.main.constant.MainConstant;
import com.sinopec.smcc.cpro.tools.DateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @Title WordUtils.java
 * @Package com.sinopec.smcc.cpro.tools.word
 * @Description: TODO:
 * @author dongxu
 * @date 2018年6月7日下午2:18:26
 * @version V1.0
 */
public class WordUtils {
  private static Configuration configuration;    
  
  public WordUtils(){     
    configuration = new Configuration();    
    configuration.setDefaultEncoding("UTF-8");    
  }    
  /**
   * 创建word文档
   * @Descrption dataMap里存入List
   * @author dongxu
   * @date 2018年6月7日下午9:38:59
   * @param dataMap  value
   * @param tempName 模板名称
   * @param fileName 文件名称
   * @param file     路径名
   */
  public static String createWord(Map<String,Object> dataMap,String tempName,String fileName){       
    WordUtils wordUtils = new WordUtils();
    configuration.setClassForTemplateLoading(wordUtils.getClass(), "/file/template/word/");  //FTL文件所存在的位置    
    Template t=null;    
    try {    
        t = configuration.getTemplate(tempName); //文件名    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    String filePath = MainConstant.TEMPORARY_FILE_PATH+fileName+"_"+
        DateUtils.getStringDateShort()+".doc";
    File outFile = new File(filePath);  //导出文档的存放位置  
    if(!outFile.exists()){
      outFile.getParentFile().mkdirs();          
    }
    try {
      outFile.createNewFile();
    } catch (IOException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    Writer out = null;    
    try {    
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));    
    } catch (FileNotFoundException e1) {    
        e1.printStackTrace();    
    }        
    try {    
        t.process(dataMap, out);   
        out.close();
    } catch (TemplateException e) {    
        e.printStackTrace();    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    return filePath;
  }    
}

/**
* 2018. 
* @Title AttachFile.java
* @Package com.sinopec.smcc.cpro.file.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午7:03:43
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.entity;

import java.io.File;

/**
 * @Title AttachFile.java
 * @Package com.sinopec.smcc.cpro.file.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月30日下午7:03:43
 * @version V1.0
 */
public class AttachFile {
  
  private File files;
  private String attachTypes;
  private String returnFileName;
  private String filePath;

  public File getFiles() {
    return files;
  }
  public void setFiles(File files) {
    this.files = files;
  }
  public String getAttachTypes() {
    return attachTypes;
  }
  public void setAttachTypes(String attachTypes) {
    this.attachTypes = attachTypes;
  }
  public String getReturnFileName() {
    return returnFileName;
  }
  public void setReturnFileName(String returnFileName) {
    this.returnFileName = returnFileName;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  
}

/**
* 2018. 
* @Title FileServiceImpl.java
* @Package com.sinopec.smcc.cpro.file.server.impl
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午4:32:46
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.server.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.common.mongodb.MongoService;
import com.sinopec.smcc.cpro.file.mapper.AttachMapper;
import com.sinopec.smcc.cpro.file.server.AttachFileService;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title FileServiceImpl.java
 * @Package com.sinopec.smcc.cpro.file.server.impl
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月30日下午4:32:46
 * @version V1.0
 */
@Service
public class AttachFileServiceImpl implements AttachFileService {

  @Autowired
  public AttachMapper attachMapper;
  @Autowired
  public MongoService fileMongoServicee;
  /**
   * @Descrption 新增附件到本地服务器指定位置
   * @author zhouyu
   * @date 2018年5月30日下午4:28:57
   * @param SysAttachFile
   * @return 
   * @throws Exception
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
  public String SaveAttachFile(HttpServletRequest request,MultipartFile[] files,String type,
      String fileName) throws BusinessException {
    String fileNameId= Utils.getUuidFor32();
    InputStream is = null;
    FileOutputStream fs = null;
    //判断file数组不能为空并且长度大于0  
    int count=0;
      if(files!=null&&files.length>0){  
          //循环获取file数组中得文件  
          for(int i = 0;i<files.length;i++){  
              MultipartFile file = files[i];  
              //保存 文件  
              String filePath = request.getSession().getServletContext().getRealPath("/") + "upload"+
                  File.separator+"cpro"+File.separator ; 
              fileName = file.getOriginalFilename();
              File headShowFile = new File(filePath);
              if (!headShowFile.isDirectory()) {
                headShowFile.mkdirs(); 
              }  
              //** 先存取源文件*//*
              try {
                is = file.getInputStream();
                fs = new FileOutputStream(fileNameId);
                if (file.getSize() > 0) {
                  byte[] buffer = new byte[1024 * 1024];
                  int bytesum = 0;
                  int byteread = 0;
                  while ((byteread = is.read(buffer)) != -1) {
                              bytesum += byteread;
                              fs.write(buffer, 0, byteread);
                              fs.flush();
                        }
                                  fs.close();
                                  is.close();
                     }
              } catch (IOException e) {
                e.printStackTrace();
              }
          }  
      }
    return fileName;
  }
 
  
  /**
   * @Descrption 下载附件
   * @author zhouyu
   * @date 2018年5月30日下午4:28:57
   * @param SysAttachFile
   * @return 
   * @throws Exception
   */
  /*@Override
  public List<FileEntity> findByLinkedid(String linkedid) throws BusinessException {
    return null;
  }*/
  
  /**
   * @Descrption 删除附件
   * @author zhouyu
   * @date 2018年5月30日下午4:29:33
   * @param SysAttachFile
   * @return
   * @throws Exception
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.delete, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
  public void remove(String id) throws BusinessException {
    
    

  }

  /**
   * @Descrption 删除本地上传指定附件
   * @author zhouyu
   * @date 2018年5月30日下午4:29:33
   * @return
   * @throws Exception
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.delete, module = SmccModuleEnum.security)
  public void delete(HttpServletRequest request,String fileNameId) throws BusinessException {
    
    //判断fileNameId编码是否存在，如果不存在则抛出异常不进行操作
    if(StringUtils.isBlank(fileNameId))
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    // 文件保存路径  
    String filePath = request.getSession().getServletContext().getRealPath("/") + 
        "upload"+ File.separator+"cpro"+File.separator ;
    File file = new File(filePath+"/"+fileNameId);
    deleteFolder(file);
  }

  /**
   * @Descrption 删除系统本地文件
   * @author zhouyu
   * @date 2018年5月31日下午4:25:22
   * @param file
   */
  private void deleteFolder(File file) {
     if(file !=null){
       file.delete();
     }
  }

}

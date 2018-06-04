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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.common.mongodb.MongoService;
import com.sinopec.smcc.cpro.file.entity.AttachFile;
import com.sinopec.smcc.cpro.file.entity.SysAttachFile;
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
  public AttachFile SaveAttachFile(HttpServletRequest request,MultipartFile[] files,String type,
      String fileName) throws BusinessException {
    String fileNameId= Utils.getUuidFor32();
    InputStream is = null;
    FileOutputStream fs = null;
    AttachFile attachFile = new AttachFile();
    attachFile.setReturnFileName(fileName);
    //判断file数组不能为空并且长度大于0  
    int count=0;
      if(files!=null&&files.length>0){  
          //循环获取file数组中得文件  
          for(int i = 0;i<files.length;i++){  
              MultipartFile file = files[i];  
              //保存 文件  
              String filePath = request.getSession().getServletContext().getRealPath("/") + "upload"+
                  File.separator+"cpro"+File.separator ; 
              System.out.println(filePath);
              attachFile.setFilePath(filePath+fileNameId);
//              fileName = file.getOriginalFilename();
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
    return attachFile;
  }
 
  
  /**
   * @Descrption 下载附件
   * @author zhouyu
   * @date 2018年5月30日下午4:28:57
   * @param SysAttachFile
   * @return 
   * @throws Exception
   */
  @RequestMapping(value = "/downloadFile/{fkSyssonId}", method = RequestMethod.GET)
  @ResponseBody
  public void downloadFile(@PathVariable("fkSyssonId") String fkSyssonId,
      HttpServletRequest request, HttpServletResponse response)throws BusinessException {
    byte[] result = null;
    try {
      result = this.fileMongoServicee.downloadFileUseStream(fkSyssonId);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    if(result != null){
      BufferedInputStream is = null;
      BufferedOutputStream os = null;
      try {
        os = new BufferedOutputStream(response.getOutputStream());
        int bytesRead;
        while (-1 != (bytesRead = is.read(result, 0, result.length))) {
          os.write(result, 0, bytesRead);
        }
      } catch (Exception e) {
      } finally {
        try {
          if (is != null)
            is.close();
          if (os != null)
            os.close();
        } catch (IOException e) {
        }
      }
    }
  }
  
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
  public void delete(String fkSyssonId) throws BusinessException {
  //判断fileNameId编码是否存在，如果不存在则抛出异常不进行操作
    if(StringUtils.isBlank(fkSyssonId))
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
      
       try {
         //数据库假删除附件
        this.attachMapper.deleteByAttach(fkSyssonId);
        this.fileMongoServicee.deleteByObjectId(fkSyssonId);
      } catch (Exception e) {
        e.printStackTrace();
      }

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
  public void remove(HttpServletRequest request,String fileNameId,
      String filePath) throws BusinessException {
    
    //判断fileNameId编码是否存在，如果不存在则抛出异常不进行操作
    if(StringUtils.isBlank(fileNameId))
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    // 根据文件名和附件存储路径获取文件  
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


  /**
   * @Descrption 保存系统本地文件到数据库
   * @author zhouyu
   * @date 2018年5月31日下午4:25:22
   * @param file
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
  public int SaveMongoAttachFile(String fkSystemId, String filePath, String attachName,
      String fkAttachType,String createUserName) throws BusinessException {
    
    int n = 0;
    AttachFile attachFile = new AttachFile();
    //获取附件
    File file = new File(filePath, attachName);
    if  (!file .exists()){     
        file .mkdir();   
    } 
    InputStream inputStream = null;
    try {
      // 读取文件内容 (输入流)  
      inputStream = new FileInputStream(file);
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    String fkSyssonId = null;
    try {
      //调用MongoDB接口保存文件到mongodb数据库并返回数据ID
      fkSyssonId = "je1hje1e92831w9";
//                this.fileMongoServicee.uploadFile(inputStream, attachName);
    } catch (Exception e1) {
      e1.printStackTrace();
    } 
    //保存附件到数据库附件表
    SysAttachFile vo = new SysAttachFile(); 
      vo.setAttachId(Utils.getUuidFor32());
      vo.setAttachName(attachName);
      vo.setCreateTime(new Date());
      vo.setFkSystemId(fkSystemId);
      vo.setFkAttachType(fkAttachType);
      vo.setFkSyssonId(fkSyssonId);
      vo.setDeleteStatus(1);
      vo.setCreateUserName(createUserName);
    
    try {
    //通过附件上传接口AttachFileService调用新增附件方法
            n += this.attachMapper.insertAttach(vo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return n;
  }

}

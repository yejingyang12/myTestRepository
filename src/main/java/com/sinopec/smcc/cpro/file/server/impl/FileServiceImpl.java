/**
* @Title FileServiceImpl.java
* @Package com.sinopec.smcc.cpro.file.server.impl
* @Description: TODO:
* @author eric
* @date 2018年6月4日上午10:08:17
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.common.mongodb.MongoService;
import com.sinopec.smcc.cpro.file.constant.FileConstant;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.file.mapper.AttachMapper;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.tools.FileOperateUtil;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title FileServiceImpl.java
 * @Package com.sinopec.smcc.cpro.file.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月4日上午10:08:17
 * @version V1.0
 */
@Service
public class FileServiceImpl implements FileService{
  
  @Autowired
  private AttachMapper attachMapper;
  @Autowired
  private MongoService mongoServiceImpl;

  @Override
  public AttachResult uploadFile(HttpServletRequest request, 
      MultipartFile file) throws BusinessException {
    AttachResult attachResult = new AttachResult();
    if (file==null|file.getSize()<=1) {
      throw new BusinessException(EnumResult.UNKONW_REQUEST_OBJ_ERROR);
    }
    //控制fileId
    String fileId = Utils.getUuidFor32();
    //初始化文件路径
    String filePath = FileConstant.TEMPORARY_FILE_PATH;
    //获得真实文件名称
    String fileName = file.getOriginalFilename();
    try {
      FileOperateUtil.uploadFile(file.getBytes(), filePath, 
          fileId+fileName.substring(fileName.lastIndexOf(".")));
      attachResult.setUploadUrl(fileId+fileName.substring(fileName.lastIndexOf(".")));
      attachResult.setAttachName(fileName.substring(fileName.lastIndexOf("\\")+1));
    } catch (IOException e) {
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    return attachResult;
  }
  
  @Override
  @Transactional
  public void addFile(AttachParam attachParam) throws BusinessException{
    
    if (StringUtils.isBlank(attachParam.getUploadUrl())) {
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    //根据文件信息获取文件
    File file = new File(FileConstant.TEMPORARY_FILE_PATH + attachParam.getUploadUrl());
    String fileId;
    try {
      InputStream inputStream = new FileInputStream(file);  
      fileId = this.mongoServiceImpl.uploadFile(inputStream, attachParam.getAttachName());
    } catch (Exception e) {
      throw new BusinessException(EnumResult.UNKONW_ERROR);
    }
    if (fileId == null) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    attachParam.setMongoFileId(fileId);
    attachParam.setCreateTime(new Date());
    this.attachMapper.insertAttach(attachParam);
    //删除现有文件
    file.delete();
  }
  
  @Override
  @Transactional
  public void deleteFile(AttachParam attachParam) throws BusinessException {
    if (attachParam==null) {
      return;
    }
    if (attachParam.getUploadUrl()!=null) {
      File file = new File(FileConstant.TEMPORARY_FILE_PATH + attachParam.getUploadUrl());
      file.delete();
    }
    
    if ((attachParam.getFileId()==null||StringUtils.isBlank(attachParam.getFileId()))
        &&(attachParam.getSystemId()==null||StringUtils.isBlank(attachParam.getSystemId()))
        &&(attachParam.getSyssonId()==null||StringUtils.isBlank(attachParam.getSyssonId()))
      ) {
      return;
    }
    
    List<AttachResult> attachResultList = this.attachMapper.selectAllAttachByParam(attachParam);
    
    if (attachResultList==null||attachResultList.size()==0) {
      return;
    }
    int nAttachResultListSize = attachResultList.size();
    for (int i = 0; i < nAttachResultListSize; i++) {
      String mongoFileId = attachResultList.get(0).getMongoFileId();
      if (mongoFileId==null) {
        return;
      }
      
      try {
        this.mongoServiceImpl.deleteByObjectId(mongoFileId);
      } catch (Exception e) {
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }
    }
    
    this.attachMapper.deleteAllAttachByParam(attachParam);
  }

  @Override
  public void downloadFile(HttpServletRequest request, HttpServletResponse response, 
      AttachParam attachParam) throws BusinessException {
    if (attachParam==null) {
      return;
    }
    
    if (attachParam.getUploadUrl()!=null&&StringUtils.isNotBlank(attachParam.getUploadUrl())) {
      String filePath = FileConstant.TEMPORARY_FILE_PATH+attachParam.getUploadUrl();
      try {
        FileOperateUtil.download(request, response, 
            filePath, 
            attachParam.getAttachName(), "UTF-8", "ISO8859-1", 20480);
        return;
      } catch (IOException e) {
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }finally{
        File file = new File(filePath);
        FileOperateUtil.deleteFile(file);
      }
    }
    
    if (attachParam.getFileId()!=null) {
      AttachResult attachResult = this.attachMapper.selectSingleAttachByFileId(attachParam);
      //获得文件扩展名
      String strExtensionName = attachResult.getAttachName().substring(
          attachResult.getAttachName().lastIndexOf("."));
      String filePath = FileConstant.TEMPORARY_FILE_PATH+Utils.getUuidFor32()+strExtensionName;
      try {
        this.mongoServiceImpl.downloadFile(attachResult.getMongoFileId(), filePath);
      } catch (Exception e1) {
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }
      try {
        FileOperateUtil.download(request, response, filePath, 
            attachResult.getAttachName(), "UTF-8", "ISO8859-1", 20480);
      } catch (IOException e) {
        e.printStackTrace();
        throw new BusinessException(EnumResult.UNKONW_ERROR);
      }finally{
        File file = new File(filePath);
        FileOperateUtil.deleteFile(file);
      }
    }
  }
  
}

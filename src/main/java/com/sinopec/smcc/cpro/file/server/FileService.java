/**
* @Title FileService.java
* @Package com.sinopec.smcc.cpro.file.server
* @Description: TODO:
* @author eric
* @date 2018年6月4日上午10:08:06
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.entity.AttachResult;

/**
 * @Title FileService.java
 * @Package com.sinopec.smcc.cpro.file.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月4日上午10:08:06
 * @version V1.0
 */
public interface FileService {

  /**
   * 上传文件
   * @author eric
   * @date 2018年6月4日上午10:09:15
   * @param request
   * @param files 需要处理的文件
   * @return 返回result对象：包含已上传文件的UploadUrl与上传文件的文件名称AttachName。
   */
  AttachResult uploadFile(HttpServletRequest request, 
      MultipartFile file) throws BusinessException ;
  
  /**
   * 新增文件
   * @author eric
   * @date 2018年6月4日下午9:10:19
   * @param attachResult 文件对象，除mongdbFileId外均需调用此方法的方法自行录入
   * @throws BusinessException
   */
  void addFile(AttachParam attachParam) throws BusinessException;
  
  /**
   * 删除文件
   * @author eric
   * @date 2018年6月4日下午9:16:13
   * @param attachResult 文件对象，包含需删除的对象内的参数
   *                     如包含fileId则删除fileId下的文件
   *                     如包含uploadUrl则删除uploadUrl下的文件
   *                     如包含fkSystemId则删除fkSystemId下的所有文件
   *                     如包含fkSyssonId则删除fkSyssonId下的所有文件
   *                     如包含fkAttachType则删除fkAttachType下的所有文件，fkAttachType必须搭配fkSystemId或fkSyssonId一起使用
   *                     如上述条件包含多个，则删除符合多个条件的数据
   * @throws BusinessException
   */
  void deleteFile(AttachParam attachParam) throws BusinessException;

  /**
   * 下载文件
   * @author eric
   * @param response 
   * @param request 
   * @date 2018年6月4日下午10:02:24
   * @param attachParam：下载文件时包含两种方式下载。
   *                     方式1：通过url下载，如传入uploadUrl则直接下载服务器端本地url数据文件。
   *                     方式2：通过fileId下载，如传入fileId则支持下载mongodb内的文件数据。
   *                     同一次调用支持方式1与方式2其中一种方法，首先判断uploadUrl，
   *                     如uploadUrl存在无论是否传入mongoFileId均针对uploadUrl数据进行下载 
   * @param request 
   * @param response 
   * @throws BusinessException
   */
  void downloadFile(HttpServletRequest request, HttpServletResponse response, 
      AttachParam attachParam) throws BusinessException;
  
}

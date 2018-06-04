/**
* 2018. 
* @Title FileService.java
* @Package com.sinopec.smcc.cpro.file.server
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午1:05:09
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.file.entity.AttachFile;

/**
 * @Title FileService.java
 * @Package com.sinopec.smcc.cpro.file.server
 * @Description: TODO: 附件接口
 * @author zhouyu
 * @date 2018年5月30日下午1:05:09
 * @version V1.0
 */
public interface AttachFileService {
  
  /**
   * @Descrption 删除文件
   * @author zhouyu
   * @date 2018年5月31日下午3:31:27
   * @param id
   * @throws BusinessException
   */
  void delete(String fkSyssonId) throws BusinessException;

  /**
   * @Descrption 上传附件到本地
   * @author zhouyu
   * @date 2018年5月31日上午9:45:32
   * @param vo
   * @return
   */
  AttachFile SaveAttachFile(HttpServletRequest request,MultipartFile[] file,
      String type,String fileName) throws BusinessException;
  
  /**
   * @Descrption 删除本地上传指定附件
   * @author zhouyu
   * @date 2018年5月31日下午3:31:27
   * @param id
   * @throws BusinessException
   */
  void remove(HttpServletRequest request,String fileNameId,String filePath)  throws BusinessException;
  
  /**
   * @Descrption 保存附件到数据库
   * @author zhouyu
   * @date 2018年5月31日上午9:45:32
   * @param vo
   * @return
   */
  int SaveMongoAttachFile(String fkSystemId,String filePath,
      String fileName,String attachType,String createUserName) throws BusinessException;

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年6月1日下午3:30:14
   * @param linkedid
   * @return
   * @throws BusinessException
   */
  void downloadFile(@PathVariable("fkSyssonId") String fkSyssonId,HttpServletRequest request,
      HttpServletResponse response) throws BusinessException;
}

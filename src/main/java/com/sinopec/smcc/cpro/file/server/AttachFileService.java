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

import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.common.exception.classify.BusinessException;

/**
 * @Title FileService.java
 * @Package com.sinopec.smcc.cpro.file.server
 * @Description: TODO:
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
  void remove(String id) throws BusinessException;

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月31日上午9:45:32
   * @param vo
   * @return
   */
  String SaveAttachFile(HttpServletRequest request,MultipartFile[] file,
      String type,String fileName) throws BusinessException;
  
  /**
   * @Descrption 删除本地上传指定附件
   * @author zhouyu
   * @date 2018年5月31日下午3:31:27
   * @param id
   * @throws BusinessException
   */
  void delete(HttpServletRequest request,String fileNameId)  throws BusinessException;
  

}

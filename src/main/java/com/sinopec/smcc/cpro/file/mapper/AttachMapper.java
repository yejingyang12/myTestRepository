/**
* 2018. 
* @Title AttachMapper.java
* @Package com.sinopec.smcc.cpro.file.mapper
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午3:14:40
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.file.entity.SysAttachFile;

/**
 * @Title AttachMapper.java
 * @Package com.sinopec.smcc.cpro.file.mapper
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月30日下午3:14:40
 * @version V1.0
 */
public interface AttachMapper {
  /**
   * @Descrption 新增附件
   * @author zhouyu
   * @date 2018年5月30日下午4:28:57
   * @param SysAttachFile
   * @return 
   * @throws Exception
   */
  public int insertAttach(SysAttachFile SysAttachFile) throws Exception;
  /**
   * @Descrption 删除附件
   * @author zhouyu
   * @date 2018年5月30日下午4:29:33
   * @param SysAttachFile
   * @return
   * @throws Exception
   */
  public int deleteByAttach(SysAttachFile SysAttachFile) throws Exception;

}

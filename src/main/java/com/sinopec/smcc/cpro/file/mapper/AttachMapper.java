/**
* @Title AttachMapper.java
* @Package com.sinopec.smcc.cpro.file.mapper
* @Description: TODO:
* @author eric
* @date 2018年6月4日下午8:40:59
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.entity.AttachResult;

/**
 * @Title AttachMapper.java
 * @Package com.sinopec.smcc.cpro.file.mapper
 * @Description: TODO:
 * @author eric
 * @date 2018年6月4日下午8:40:59
 * @version V1.0
 */
public interface AttachMapper {

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月4日下午8:59:37
   * @param attachParam
   */
  void insertAttach(AttachParam attachParam);

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月4日下午9:20:42
   * @param attachParam
   */
  List<AttachResult> selectAllAttachByParam(AttachParam attachParam);

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月4日下午9:30:34
   * @param attachParam
   */
  void deleteAllAttachByParam(AttachParam attachParam);

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月4日下午10:17:38
   * @param attachParam
   */
  AttachResult selectSingleAttachByFileId(AttachParam attachParam);

}

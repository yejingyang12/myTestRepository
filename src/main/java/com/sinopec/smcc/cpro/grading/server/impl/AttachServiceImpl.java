/**
* 2018. 
* @Title AttachServiceImpl.java
* @Package com.sinopec.smcc.cpro.grading.server.impl
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日下午4:50:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.cpro.grading.entity.AttachListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachParam;
import com.sinopec.smcc.cpro.grading.mapper.AttachMapper;
import com.sinopec.smcc.cpro.grading.server.AttachService;

/**
 * @Title AttachServiceImpl.java
 * @Package com.sinopec.smcc.cpro.grading.server.impl
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日下午4:50:41
 * @version V1.0
 */
@Service
public class AttachServiceImpl implements AttachService {

  @Autowired
  private AttachMapper attachMapper;
  
  /**
   * 初始化提交材料信息
   * @throws BusinessException 
   */
  @Override
  public List<AttachListResult> queryDetailsAttach(AttachParam attachParam)
      throws BusinessException {
    if(StringUtils.isBlank(attachParam.getAttachId())){
      List<AttachListResult> list = this.attachMapper.selectDetailsAttach(attachParam);
      for (int i = 0; i < list.size(); i++) {
        String name = list.get(i).getAttachName().
            substring(0, list.get(i).getAttachName().lastIndexOf("."));
        list.get(i).setAttachName(name);
      }
      return list;
    }
      throw new BusinessException(EnumResult.ERROR);
  }

  /**
   * 查询提交材料信息详情
   * @throws BusinessException 
   */
  @Override
  public List<AttachListResult> queryEditAttach(AttachParam attachParam) throws BusinessException {
    if(StringUtils.isBlank(attachParam.getAttachId())) {
      List<AttachListResult> list = this.attachMapper.selectEditAttach(attachParam);
      for (int i = 0; i < list.size(); i++) {
        String name = list.get(i).getAttachName().
            substring(0, list.get(i).getAttachName().lastIndexOf("."));
        list.get(i).setAttachName(name);
      }
      return list;
    }
    throw new BusinessException(EnumResult.ERROR);
  }
  
}

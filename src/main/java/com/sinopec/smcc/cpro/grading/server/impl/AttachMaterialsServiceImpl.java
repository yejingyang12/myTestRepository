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
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;
import com.sinopec.smcc.cpro.grading.mapper.AttachMaterialsMapper;
import com.sinopec.smcc.cpro.grading.server.AttachMaterialsService;

/**
 * @Title AttachServiceImpl.java
 * @Package com.sinopec.smcc.cpro.grading.server.impl
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日下午4:50:41
 * @version V1.0
 */
@Service
public class AttachMaterialsServiceImpl implements AttachMaterialsService {

  @Autowired
  private AttachMaterialsMapper attachMaterialsMapper;
  
  /**
   * 初始化提交材料信息
   * @throws BusinessException 
   */
  @Override
  public List<AttachMaterialsListResult> queryDetailsAttach(
      AttachMaterialsParam attachMaterialsParam) throws BusinessException {
    if(StringUtils.isBlank(attachMaterialsParam.getAttachId())){
      List<AttachMaterialsListResult> list = 
          this.attachMaterialsMapper.selectDetailsAttach(attachMaterialsParam);
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
  public List<AttachMaterialsListResult> queryEditAttach(
      AttachMaterialsParam attachMaterialsParam) throws BusinessException {
    if(StringUtils.isBlank(attachMaterialsParam.getAttachId())) {
      List<AttachMaterialsListResult> list = 
          this.attachMaterialsMapper.selectEditAttach(attachMaterialsParam);
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

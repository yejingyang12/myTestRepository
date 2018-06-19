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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;
import com.sinopec.smcc.cpro.grading.mapper.AttachMaterialsMapper;
import com.sinopec.smcc.cpro.grading.server.AttachMaterialsService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.tools.Utils;

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
  @Autowired
  private NodeService nodeServiceImpl;
  
  /**
   * 初始化提交材料信息
   * @throws BusinessException 
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
  public List<AttachMaterialsListResult> queryDetailsAttach(
      AttachMaterialsParam attachMaterialsParam) throws BusinessException {
    if(StringUtils.isNotBlank(attachMaterialsParam.getFkSystemId())){
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
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
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

  /**
   * 保存材料信息
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
  public String saveAttach(String userName, AttachMaterialsParam attachMaterialsParam) {
    if (StringUtils.isBlank(attachMaterialsParam.getAttachId())) {
      attachMaterialsParam.setAttachId(Utils.getUuidFor32());
      attachMaterialsParam.setCreateTime(new Date());
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(attachMaterialsParam.getFkSystemId());
      nodeParam.setOperation("创建");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
      this.attachMaterialsMapper.insertAttach(attachMaterialsParam);
    }else{
      attachMaterialsParam.setCreateTime(new Date());
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(attachMaterialsParam.getFkSystemId());
      nodeParam.setOperation("变更");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
      this.attachMaterialsMapper.insertAttach(attachMaterialsParam);
    }
    return attachMaterialsParam.getAttachId();
  }

  /**
   * 提交材料信息修改状态
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_attach")
  public String submitAttach(String userName, AttachMaterialsParam attachMaterialsParam) {
    if (StringUtils.isBlank(attachMaterialsParam.getAttachId())) {
      attachMaterialsParam.setAttachId(Utils.getUuidFor32());
      attachMaterialsParam.setCreateTime(new Date());
      this.attachMaterialsMapper.insertAttach(attachMaterialsParam);
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(attachMaterialsParam.getFkSystemId());
      nodeParam.setOperation("创建");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
      this.attachMaterialsMapper.insertAttach(attachMaterialsParam);
    }else{
      this.attachMaterialsMapper.updateAttachStatus(attachMaterialsParam);
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(attachMaterialsParam.getFkSystemId());
      nodeParam.setOperation("变更");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
      this.attachMaterialsMapper.insertAttach(attachMaterialsParam);
    }
    return attachMaterialsParam.getAttachId();
  }

}

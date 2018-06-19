/**
* 2018. 
* @Title RecordsServiceImpl.java
* @Package com.sinopec.smcc.cpro.records.server.impl
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:59:54
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
<<<<<<< HEAD
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsDetailResult;
import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
=======
>>>>>>> 5e3690b80fe550f38e220fe6e96c88faa5d9da21
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RevokeRecordsResult;
import com.sinopec.smcc.cpro.records.mapper.RecordsMapper;
import com.sinopec.smcc.cpro.records.server.RecordsService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title RecordsServiceImpl.java
 * @Package com.sinopec.smcc.cpro.records.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:59:54
 * @version V1.0
 */
@Service
public class RecordsServiceImpl implements RecordsService{
  
  @Autowired
  private RecordsMapper recordsMapper;
  
  @Autowired
  private SystemMapper systemMapper;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private FileService fileServiceImpl;
  
  /**
   * 修改或添加备案信息
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  public String saveRecords(String userName, RecordsParam recordsParam) 
      throws BusinessException {
    if(StringUtils.isBlank(recordsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    recordsParam.setFkrevokematter(5);
    recordsParam.setCreateTime(new Date());
    if(StringUtils.isBlank(recordsParam.getRecordsId())) {
      
      recordsParam.setRecordsId(Utils.getUuidFor32());
      
      //将系统状态改为已完成
      recordsParam.setRecordStatus(3);
      this.editRecordsForStatus(recordsParam);
      try {
        //保存附件
        if (StringUtils.isNotBlank(recordsParam.getRecordReportPath())) {
          //保存信息系统安全等级保护备案证明附件
          AttachParam attachParam = new AttachParam();
          attachParam.setFileId(Utils.getUuidFor32());
          attachParam.setSystemId(recordsParam.getFkSystemId());
          attachParam.setSyssonId(recordsParam.getRecordsId());
          attachParam.setAttachType("recordReport");
          attachParam.setAttachName(recordsParam.getRecordReportName());
          attachParam.setUploadUrl(recordsParam.getRecordReportPath());
          this.fileServiceImpl.addFile(attachParam);
        }
      } catch (Exception e) {
        //TODO: "保存附件出错";
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(recordsParam.getFkSystemId());
      nodeParam.setOperation("备案");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }else{
      try {
        //保存信息系统安全等级保护备案证明附件
        if (StringUtils.isNotBlank(recordsParam.getRecordReportPath())) {
          AttachParam attachParam = new AttachParam();
          attachParam.setSystemId(recordsParam.getFkSystemId());
          attachParam.setSyssonId(recordsParam.getRecordsId());
          attachParam.setAttachType("recordReport");
          //删除原信息系统安全等级保护备案证明附件
          this.fileServiceImpl.deleteFile(attachParam);
          attachParam.setFileId(Utils.getUuidFor32());
          attachParam.setAttachName(recordsParam.getRecordReportName());
          attachParam.setUploadUrl(recordsParam.getRecordReportPath());
          //保存新信息系统安全等级保护备案证明附件
          this.fileServiceImpl.addFile(attachParam);
        }
      } catch (Exception e) {
        //TODO: "保存附件出错";
      }
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(recordsParam.getFkSystemId());
      nodeParam.setOperation("变更");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }
    this.recordsMapper.insertRecords(recordsParam);
    return recordsParam.getFkSystemId();
  }
  
  /**
   * 通过系统id查询备案信息
   */
  @Override
  public RecordsResult queryRecordsByFkSystemId(RecordsParam recordsParam) throws BusinessException{
    if(StringUtils.isBlank(recordsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    RecordsResult records = this.recordsMapper.selectRecordsByFkSystemId(recordsParam);
    return records;
  }

  /**
   * 点击撤销备案，填写信息后保存
   * @throws BusinessException 
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  public void saveRevokeRecordsInfo(String userName,RecordsParam recordsParam) 
      throws BusinessException {
    if(StringUtils.isBlank(recordsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    this.recordsMapper.updateRecordsBySystemIdForRevokeRecords(recordsParam);
    
    if (StringUtils.isNotBlank(recordsParam.getRevokeAttachPath())) {
      try {
        //保存撤销备案附件
        AttachParam attachParam = new AttachParam();
        attachParam.setFileId(Utils.getUuidFor32());
        attachParam.setSystemId(recordsParam.getFkSystemId());
        attachParam.setSyssonId(recordsParam.getRecordsId());
        attachParam.setAttachType("revokeRecordsReport");
        attachParam.setAttachName(recordsParam.getRevokeAttachName());
        attachParam.setUploadUrl(recordsParam.getRevokeAttachPath());
        this.fileServiceImpl.addFile(attachParam);
      } catch (Exception e) {
        //TODO: 保存文件出错
      }
    }
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(recordsParam.getFkSystemId());
    nodeParam.setOperation("撤销备案");
    nodeParam.setOperationResult("已创建");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
  }

  /**
   * 获取撤销备案信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  public RevokeRecordsResult queryRevokeRecords(RecordsParam recordsParam) throws BusinessException {
    if (StringUtils.isBlank(recordsParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    RevokeRecordsResult revokeRecordsResult = this.recordsMapper.
        selectRecordsByFkSystemIdForRevokeRecords(recordsParam);
    return revokeRecordsResult;
  }

  /**
   * 修改备案状态
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_system")
  public void editRecordsForStatus(RecordsParam recordsParam) {
    SystemParam systemParam = new SystemParam();
    systemParam.setRecordStatus(recordsParam.getRecordStatus());
    systemParam.setSystemId(recordsParam.getFkSystemId());
    this.systemMapper.updateSystemStatusBySystemId(systemParam);
  }

  /**
   * 查询备案详情
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_records")
  public RecordsDetailResult queryRecordsDetail(RecordsParam recordsParam) throws BusinessException {
    if (StringUtils.isBlank(recordsParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    RecordsDetailResult RecordsDetailResult = this.recordsMapper.
        selectRecordsByFkSystemIdForRecordsDetail(recordsParam);
    return RecordsDetailResult;
  }

  /**
   * 高级搜索获取受理备案单位
   */
  @Override
  public List<RecordsListResult> queryRecordCompany(RecordsParam recordsParam) 
      throws BusinessException{
    return this.recordsMapper.selectRecordCompany(recordsParam);
  }
}

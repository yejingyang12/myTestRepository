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

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsDetailResult;
import com.sinopec.smcc.cpro.records.entity.RecordsListResult;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.records.entity.RevokeRecordsResult;
import com.sinopec.smcc.cpro.records.mapper.RecordsMapper;
import com.sinopec.smcc.cpro.records.server.RecordsService;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;
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
  @Autowired
  private MainService mainServiceImpl;
  @Autowired
  private CheckService checkServiceImpl;
  

  /**
   * 修改或添加备案信息
   */
  @Override
  @Transactional
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
      //修改备案状态为已完成
      MainParam mainParam = new MainParam();
      mainParam.setRecordStatus("3");
      mainParam.setSystemId(recordsParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
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
    }
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(recordsParam.getFkSystemId());
    nodeParam.setOperation("备案");
    nodeParam.setOperationResult("已创建");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
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
   * 企业点击撤销备案，填写信息后保存
   * @throws BusinessException 
   */
  @Override
  @Transactional
  public void saveRevokeRecordsInfo(String userName,RecordsParam recordsParam) 
      throws BusinessException {
    if(StringUtils.isBlank(recordsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    this.recordsMapper.updateRecordsBySystemIdForRevokeRecords(recordsParam);
    RecordsDetailResult selectRecordsByFkSystemIdForRecordsDetail = this.recordsMapper.
        selectRecordsByFkSystemIdForRecordsDetail(recordsParam);
    //修改审核状态
    CheckParam checkParam = new CheckParam();
    checkParam.setFkSystemId(recordsParam.getFkSystemId());
    checkParam.setFkExaminStatus("2");
    checkParam.setFkBusinessNode("2");
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkServiceImpl.editCheckStatusBySystemId(checkParam);
    
    //修改系统状态
    MainParam mainParam = new MainParam();
    mainParam.setRecordStatus("2");
    mainParam.setExamineStatus("2");
    mainParam.setSystemId(recordsParam.getFkSystemId());
    mainServiceImpl.editSystemStatusBySystemId(mainParam);
    recordsParam.setRecordsId(selectRecordsByFkSystemIdForRecordsDetail.getRecordsId());
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
    nodeParam.setOperationResult("已提交");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
  }

  /**
   * 总部点击撤销备案，填写信息后保存
   * @throws BusinessException 
   */
  @Override
  @Transactional
  public void saveHeadRevokeRecordsInfo(String userName,RecordsParam recordsParam) 
      throws BusinessException {
    if(StringUtils.isBlank(recordsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    }
    this.recordsMapper.updateRecordsBySystemIdForRevokeRecords(recordsParam);
    RecordsDetailResult selectRecordsByFkSystemIdForRecordsDetail = this.recordsMapper.
        selectRecordsByFkSystemIdForRecordsDetail(recordsParam);
    //修改审核状态
    CheckParam checkParam = new CheckParam();
    checkParam.setFkSystemId(recordsParam.getFkSystemId());
    checkParam.setFkExaminStatus("5");
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkParam.setCancelRecordsResult(1);
    checkServiceImpl.editCheckStatusBySystemId(checkParam);
    
    //修改系统状态
    MainParam mainParam = new MainParam();
    mainParam.setRecordStatus("4");
    mainParam.setExamineStatus("3");
    mainParam.setEvaluationStatus("4");
    mainParam.setExaminationStatus("4");
    mainParam.setSystemId(recordsParam.getFkSystemId());
    mainServiceImpl.editSystemStatusBySystemId(mainParam);
    recordsParam.setRecordsId(selectRecordsByFkSystemIdForRecordsDetail.getRecordsId());
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
    nodeParam.setOperationResult("");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
  }
  
  /**
   * 获取撤销备案信息
   */
  @Override
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

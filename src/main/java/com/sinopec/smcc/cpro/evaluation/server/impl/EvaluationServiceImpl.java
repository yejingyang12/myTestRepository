/**
* @Title EvaluationServiceImpl.java
* @Package com.sinopec.smcc.cpro.evaluation.server.impl
* @Description: TODO:
* @author Aran
* @date 2018年5月25日下午10:00:34
* @version V1.0
*/
package com.sinopec.smcc.cpro.evaluation.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.company.utils.ConvertFiledUtil;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationListResult;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.evaluation.mapper.EvaluationMapper;
import com.sinopec.smcc.cpro.evaluation.server.EvaluationService;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title EvaluationServiceImpl.java
 * @Package com.sinopec.smcc.cpro.evaluation.server.impl
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午10:00:34
 * @version V1.0
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
  private EvaluationMapper evaluationMapper;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private FileService fileServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
	
	@Override
	public PageInfo<EvaluationListResult> queryEvaluationList(
			EvaluationParam evaluationParam) throws BusinessException{
    StringBuffer orderBy = new StringBuffer();
    if(StringUtils.isNotBlank(evaluationParam.getField())){
      orderBy.append(ConvertFiledUtil.sortField(evaluationParam.getField()));
      if(StringUtils.isNotBlank(evaluationParam.getSort())){
        orderBy.append("").append(evaluationParam.getSort());
      }
    }else{
      //默认排序
      orderBy.append("cproEvaluation.create_date DESC");
    }
    //初始化分页拦截器
    PageHelper.startPage(evaluationParam.getCurrentPage(), evaluationParam.getPageSize(), 
        orderBy.toString());
    //对实体内的对象进行处理
    evaluationParam.setDeleteStatus(1);
    //获得响应列表数据
    List<EvaluationListResult> evaluationListResultList = 
        this.evaluationMapper.selectAllByEvaluationParam(evaluationParam);
    //装载列表数据
    PageInfo<EvaluationListResult> pageInfo = new PageInfo<>(evaluationListResultList);
    return pageInfo;
  }

	@Override
	public EvaluationResult queryEditEvaluation(EvaluationParam evaluationParam) 
	    throws BusinessException{
    if (StringUtils.isBlank(evaluationParam.getEvaluationId())) 
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    return this.evaluationMapper.selectSingleByEvaluationId(evaluationParam);
  }

	@Override
	@Transactional
	public String saveEvaluation(String userName, EvaluationParam evaluationParam) 
	    throws BusinessException{
	  //修改测评状态
	  if(evaluationParam.getFkExamStatus() == 2){
	    MainParam mainParam = new MainParam();
	    mainParam.setEvaluationStatus("3");
	    mainParam.setSystemId(evaluationParam.getFkSystemId());
	    mainServiceImpl.editSystemStatusBySystemId(mainParam);
	  }
	  evaluationParam.setUpdateUserName(userName);
		if(StringUtils.isBlank(evaluationParam.getEvaluationId())) {
			evaluationParam.setEvaluationId(Utils.getUuidFor32());
			evaluationParam.setCreateTime(new Date());
			try {
			  /*if (StringUtils.isNotBlank(evaluationParam.getExamReportPath())) {
			    AttachParam evaluationPresentation = new AttachParam();
			    evaluationPresentation.setFileId(Utils.getUuidFor32());
			    evaluationPresentation.setSystemId(evaluationParam.getFkSystemId());
			    evaluationPresentation.setSyssonId(evaluationParam.getEvaluationId());
			    evaluationPresentation.setAttachType("evaluationPresentation");
			    evaluationPresentation.setUploadUrl(evaluationParam.getExamReportPath());
			    evaluationPresentation.setAttachName(evaluationParam.getExamReportName());
			    this.fileServiceImpl.addFile(evaluationPresentation);
			  }*/
        //参数名没统一的问题
        if (StringUtils.isNotBlank(evaluationParam.getExamReportPath())) {
          AttachParam evaluationPresentation = new AttachParam();
          evaluationPresentation.setSystemId(evaluationParam.getFkSystemId());
          evaluationPresentation.setSyssonId(evaluationParam.getEvaluationId());
          evaluationPresentation.setAttachType("evaluationPresentation");
          this.fileServiceImpl.deleteFile(evaluationPresentation);
          evaluationPresentation.setFileId(Utils.getUuidFor32());
          evaluationPresentation.setUploadUrl(evaluationParam.getExamReportPath());
          evaluationPresentation.setAttachName(evaluationParam.getExamReportName());
          this.fileServiceImpl.addFile(evaluationPresentation);
        }
			  if (StringUtils.isNotBlank(evaluationParam.getRectificationReportPath())) {
			    AttachParam rectificationReport = new AttachParam();
			    rectificationReport.setFileId(Utils.getUuidFor32());
			    rectificationReport.setSystemId(evaluationParam.getFkSystemId());
			    rectificationReport.setSyssonId(evaluationParam.getEvaluationId());
			    rectificationReport.setAttachType("rectificationReport");
			    rectificationReport.setUploadUrl(evaluationParam.getRectificationReportPath());
			    rectificationReport.setAttachName(evaluationParam.getRectificationReportName());
			    this.fileServiceImpl.addFile(rectificationReport);
			  }
      } catch (Exception e) {
        //TODO: "保存附件出错";
      }
			
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(evaluationParam.getFkSystemId());
      nodeParam.setOperation("添加测评");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
		}else{
		  evaluationParam.setCreateTime(new Date());
		  try {
		    /*if (StringUtils.isNotBlank(evaluationParam.getEvaluationPresentationPath())) {
		      AttachParam evaluationPresentation = new AttachParam();
		      evaluationPresentation.setSystemId(evaluationParam.getFkSystemId());
		      evaluationPresentation.setSyssonId(evaluationParam.getEvaluationId());
		      evaluationPresentation.setAttachType("evaluationPresentation");
		      this.fileServiceImpl.deleteFile(evaluationPresentation);
		      evaluationPresentation.setFileId(Utils.getUuidFor32());
		      evaluationPresentation.setUploadUrl(evaluationParam.getEvaluationPresentationPath());
		      evaluationPresentation.setAttachName(evaluationParam.getEvaluationPresentationName());
		      this.fileServiceImpl.addFile(evaluationPresentation);
		    }*/
		    //参数名没统一的问题
		    if (StringUtils.isNotBlank(evaluationParam.getExamReportPath())) {
          AttachParam evaluationPresentation = new AttachParam();
          evaluationPresentation.setSystemId(evaluationParam.getFkSystemId());
          evaluationPresentation.setSyssonId(evaluationParam.getEvaluationId());
          evaluationPresentation.setAttachType("evaluationPresentation");
          this.fileServiceImpl.deleteFile(evaluationPresentation);
          evaluationPresentation.setFileId(Utils.getUuidFor32());
          evaluationPresentation.setUploadUrl(evaluationParam.getExamReportPath());
          evaluationPresentation.setAttachName(evaluationParam.getExamReportName());
          this.fileServiceImpl.addFile(evaluationPresentation);
        }else if(StringUtils.isBlank(evaluationParam.getExamReportName())){
          //文件名都没有，没有上传的附件，将原有附件删除掉
          AttachParam evaluationPresentation = new AttachParam();
          evaluationPresentation.setSystemId(evaluationParam.getFkSystemId());
          evaluationPresentation.setSyssonId(evaluationParam.getEvaluationId());
          evaluationPresentation.setAttachType("evaluationPresentation");
          this.fileServiceImpl.deleteFile(evaluationPresentation);
        }
		    if (StringUtils.isNotBlank(evaluationParam.getRectificationReportPath())) {
		      AttachParam rectificationReport = new AttachParam();
		      rectificationReport.setSystemId(evaluationParam.getFkSystemId());
		      rectificationReport.setSyssonId(evaluationParam.getEvaluationId());
		      rectificationReport.setAttachType("rectificationReport");
		      this.fileServiceImpl.deleteFile(rectificationReport);
		      rectificationReport.setFileId(Utils.getUuidFor32());
		      rectificationReport.setUploadUrl(evaluationParam.getRectificationReportPath());
		      rectificationReport.setAttachName(evaluationParam.getRectificationReportName());
		      this.fileServiceImpl.addFile(rectificationReport);
		    }else if(StringUtils.isBlank(evaluationParam.getRectificationReportName())){
          //文件名都没有，没有上传的附件，将原有附件删除掉
		      AttachParam rectificationReport = new AttachParam();
          rectificationReport.setSystemId(evaluationParam.getFkSystemId());
          rectificationReport.setSyssonId(evaluationParam.getEvaluationId());
          rectificationReport.setAttachType("rectificationReport");
		    }
      } catch (Exception e) {
      
      }
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(evaluationParam.getFkSystemId());
      nodeParam.setOperation("修改测评");
      nodeParam.setOperationResult("已修改");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
		}
		/*//未测评
		if (evaluationParam.getFkExamStatus() == 1) {
		  evaluationParam.setFkExamResult(0);
		  try {
		    evaluationParam.setExamTime(DateUtils.getDate("yyyy-MM-dd", "1970-01-01"));
		  } catch (ParseException e) {
		    
		  }
		}
		//未整改
		if (evaluationParam.getFkRectificationReu() == 2) {
		  try {
		    evaluationParam.setRectificationDate(DateUtils.getDate("yyyy-MM-dd", "1970-01-01"));
		  } catch (ParseException e) {
		    
		  }
		}*/
		this.evaluationMapper.saveEvaluationByEvaluationId(evaluationParam);
		return evaluationParam.getEvaluationId();
	}

	@Override
	public void deleteEvaluation(String userName, EvaluationParam evaluationParam) 
	    throws BusinessException{
		if(StringUtils.isBlank(evaluationParam.getEvaluationId()))
			throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
		this.evaluationMapper.deleteEvaluationByEvaluationId(evaluationParam);
		evaluationParam.setDeleteStatus(1);
		List<EvaluationListResult> evaluationListResultList =
		    this.evaluationMapper.selectAllByEvaluationParam(evaluationParam);

    AttachParam attachParam = new AttachParam();
    attachParam.setSystemId(evaluationParam.getFkSystemId());
    attachParam.setSyssonId(evaluationParam.getEvaluationId());
    attachParam.setAttachType("evaluationPresentation");
    this.fileServiceImpl.deleteFile(attachParam);
    attachParam.setAttachType("rectificationReport");
    this.fileServiceImpl.deleteFile(attachParam);
    
		if(ObjectUtils.isEmpty(evaluationListResultList)){
		  //修改测评状态状态为未进行
      MainParam mainParam = new MainParam();
      mainParam.setEvaluationStatus("1");
      mainParam.setSystemId(evaluationParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
		}
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(evaluationParam.getFkSystemId());
    
    nodeParam.setOperation("删除测评");
    nodeParam.setOperationResult("已删除");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
  }

  @Override
  public EvaluationResult queryDetailsEvaluation(EvaluationParam evaluationParam) throws BusinessException {
    if (StringUtils.isBlank(evaluationParam.getEvaluationId())) 
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    return this.evaluationMapper.selectSingleDetailsByEvaluationId(evaluationParam);
  }

  /**
   * 首页高级查询测评单位
   */
  @Override
  public List<EvaluationListResult> queryExamOrgCompany(RecordsParam recordsParam)
      throws BusinessException {
    return this.evaluationMapper.selectExamOrgCompany(recordsParam);
  }
}

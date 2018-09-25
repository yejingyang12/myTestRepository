/**
* 2018. 
* @Title ApiService.java
* @Package com.sinopec.smcc.cpro.api.service
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:12:45
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.service;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.result.PageUtil;
import com.sinopec.smcc.depends.region.dto.BatchApprovalInfo;
import com.sinopec.smcc.depends.region.dto.CheckParam;
import com.sinopec.smcc.depends.region.dto.CproForeignRequestParam;
import com.sinopec.smcc.depends.region.dto.CproResultParam;
import com.sinopec.smcc.depends.region.dto.SystemRelationBaseInfo;

/**
 * @Title ApiService.java
 * @Package com.sinopec.smcc.cpro.api.service
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:12:45
 * @version V1.0
 */
public interface ApiService {
  
  CproResultParam getGradingInformation(String systemId) throws BusinessException;
  
  /**
   * @Descrption 通过userId获取待办列表
   * @author dongxu
   * @date 2018年8月6日下午5:21:04
   * @param userId
   * @return
   * @throws BusinessException
   */
  PageUtil getStayHandle(CproForeignRequestParam cproForeignRequestParam) 
      throws BusinessException;
  
  /**
   * @Descrption 批量审批
   * @author dongxu
   * @date 2018年8月8日下午12:11:19
   * @param executeContextList
   * @return
   * @throws BusinessException
   */
  Integer batchApproval(BatchApprovalInfo paramBatchApprovalInfo) throws BusinessException;
  
  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月6日上午11:53:47
   * @param systemParam
   * @return
   */
  PageUtil getSystemRelationInfo(CproForeignRequestParam 
      paramCproForeignRequestParam)throws BusinessException ;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月27日上午9:42:18
   * @param systemRelationParam
   * @return
   */
  CproResultParam editGetSystemRelationInfo(
      CproForeignRequestParam paramCproForeignRequestParam)throws BusinessException;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月27日上午11:47:14
   * @param systemRelationEditParam
   */
  void editSystemRelationInfo(CproForeignRequestParam getSystemRelationResult)
      throws BusinessException;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月27日上午11:51:37
   * @param systemRelationParam
   */
  boolean deleteSystemRelationInfo(CproForeignRequestParam paramCproForeignRequestParam)
      throws BusinessException;

  /**
   * @Descrption
   * @author Aran
   * @param systemRelationParam 
   * @date 2018年8月28日下午12:56:52
   * @param systemRelationParam
   * @return
   */
  List<SystemRelationBaseInfo> getSystemRelationByGrade(String paramString)
      throws BusinessException;
  
  /**
   * @Descrption 企业管理员定级审核
   * @author yejingyang
   * @date 2018年6月8日下午12:30:56
   * @param request
   * @param checkNodeParam
   * @return
   */
  String saveGradCheckApi(String userName, CheckParam checkParam) 
      throws BusinessException;
  
  /**
   * @Descrption 总部管理员定级审核
   * @author dongxu
   * @date 2018年6月20日下午7:33:55
   * @param userName
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  String saveHeadGradCheckApi(String userName, CheckParam checkParam) throws BusinessException;
  
  /**
   * @Descrption 总部管理员撤销备案审核
   * @author yejingyang
   * @date 2018年6月8日下午4:19:30
   * @param request
   * @param checkParam
   * @return
   */
  String saveCancelRecordsCheckApi(String userName, CheckParam checkParam)
      throws BusinessException;
  
  /**
   * @Descrption 企业管理员定级变更审核
   * @author yejingyang
   * @date 2018年6月8日下午3:54:49
   * @param request
   * @param checkParam
   * @return
   */
  String saveGradChangeCheckApi(String userName, CheckParam checkParam)
      throws BusinessException;
  /**
   * @Descrption 总部管理员定级变更审核
   * @author dongxu
   * @date 2018年6月20日下午7:42:16
   * @param userName
   * @param checkParam
   * @return
   */
  public String saveHeadGradChangeCheckApi(String userName, CheckParam checkParam) 
      throws BusinessException;
  
  /**
   * @Descrption 文件下载
   * @author dongxu
   * @date 2018年9月25日下午2:41:00
   * @param fileId
   * @throws BusinessException
   */
  public void downloadFileApi(String fileId) throws BusinessException;
}

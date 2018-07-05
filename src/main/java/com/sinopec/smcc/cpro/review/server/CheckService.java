/**
* 2018. 
* @Title CheckService.java
* @Package com.sinopec.smcc.cpro.review.server
* @Description: TODO:
* @author zhouyu
* @date 2018年5月25日下午1:20:04
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.server;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.entity.CheckResult;

/**
 * @Title CheckService.java
 * @Package com.sinopec.smcc.cpro.review.server
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月25日下午1:20:04
 * @version V1.0
 */
public interface CheckService {

  /**
   * @Descrption 审核列表数据
   * @author zhouyu
   * @date 2018年5月25日下午1:50:04
   * @param checkParam 查询参数
   * @return 列表数据
   */
  PageInfo<CheckListResult> queryCheckList(CheckParam checkParam) throws BusinessException;
  
  /**
   * @Descrption 企业管理员定级审核
   * @author yejingyang
   * @date 2018年6月8日下午12:30:56
   * @param request
   * @param checkNodeParam
   * @return
   */
  String saveGradCheck(String userName, CheckParam checkParam) 
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
  String saveHeadGradCheck(String userName, CheckParam checkParam) throws BusinessException;
  /**
   * @Descrption 企业管理员定级变更审核
   * @author yejingyang
   * @date 2018年6月8日下午3:54:49
   * @param request
   * @param checkParam
   * @return
   */
  String saveGradChangeCheck(String userName, CheckParam checkParam)
      throws BusinessException;
  /**
   * @Descrption 总部管理员定级变更审核
   * @author dongxu
   * @date 2018年6月20日下午7:42:16
   * @param userName
   * @param checkParam
   * @return
   */
  public String saveHeadGradChangeCheck(String userName, CheckParam checkParam) 
      throws BusinessException;
  
  /**
   * @Descrption 总部管理员撤销备案审核
   * @author yejingyang
   * @date 2018年6月8日下午4:19:30
   * @param request
   * @param checkParam
   * @return
   */
  String saveCancelRecordsCheck(String userName, CheckParam checkParam)
      throws BusinessException;
  
  /**
   * @Descrption修改审核状态
   * @author dongxu
   * @date 2018年6月20日下午1:26:59
   * @param checkParam
   * @throws BusinessException
   */
  void editCheckStatusBySystemId(CheckParam checkParam)throws BusinessException;
  
  /**
   * @Descrption 添加审核信息
   * @author dongxu
   * @date 2018年6月20日下午1:52:39
   * @param checkParam
   * @throws BusinessException
   */
  void addCheck(CheckParam checkParam)throws BusinessException;
  
  /**
   * @Descrption 通过systemId查询审核详情
   * @author dongxu
   * @date 2018年6月20日下午6:06:33
   * @param checkParam
   * @return
   * @throws BusinessException
   */
  CheckResult queryCheckInfoBySystemId(CheckParam checkParam)throws BusinessException;
  
  /**
   * @Descrption 通过审核ID删除审核记录
   * @author dongxu
   * @date 2018年6月20日下午6:20:40
   * @param checkParam
   * @throws BusinessException
   */
  void deleteCheckByCheckId(CheckParam checkParam)throws BusinessException;
}

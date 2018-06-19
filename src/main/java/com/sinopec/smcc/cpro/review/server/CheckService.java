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
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;

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
   * @Descrption定级审核
   * @author yejingyang
   * @date 2018年6月8日下午12:30:56
   * @param request
   * @param checkNodeParam
   * @return
   */
  String saveGradCheck(String userName, CheckParam checkParam) 
      throws BusinessException;

  /**
   * @Descrption定级变更审核
   * @author yejingyang
   * @date 2018年6月8日下午3:54:49
   * @param request
   * @param checkParam
   * @return
   */
  String saveGradChangeCheck(String userName, CheckParam checkParam)
      throws BusinessException;

  /**
   * @Descrption撤销备案审核
   * @author yejingyang
   * @date 2018年6月8日下午4:19:30
   * @param request
   * @param checkParam
   * @return
   */
  String saveCancelRecordsCheck(String userName, CheckParam checkParam)
      throws BusinessException;
  

}

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
import com.sinopec.smcc.cpro.review.entity.CheckNodeListResult;
import com.sinopec.smcc.cpro.review.entity.CheckNodeParam;
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
   * @Descrption 新增审核
   * @author zhouyu
   * @date 2018年5月26日下午7:01:40
   * @param checkNodeListResult
   * @return
   */
  public String checkNodeSave(CheckNodeListResult checkNodeListResult,String fkExaminStatus,String fkbusinessNode,String checkId,String fkSystemId) throws BusinessException;

  /**
   * @Descrption 审核节点列表
   * @author zhouyu
   * @date 2018年5月28日上午9:43:16
   * @param checkNodeParam
   * @return
   */
  public PageInfo<CheckNodeListResult> queryCheckNodeList(CheckNodeParam checkNodeParam) throws BusinessException;

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月29日下午2:51:57
   * @param checkParam
   * @return
   */
  public String saveCheck(CheckParam checkParam) throws BusinessException;

}

/**
* @Title MainService.java
* @Package com.sinopec.smcc.cpro.main.server
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:45:48
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.server;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;

/**
  * @Title MainService.java
  * @Package com.sinopec.smcc.cpro.main.server
  * @Description: TODO:
  * @author dongxu
  * @date 2018年5月25日下午2:15:18
  * @version V1.0
  */
public interface MainService {

  /**
   * 响应等保列表数据
   * @author dongxu
   * @date 2018年5月25日下午1:44:30
   * @param mainParam 查询参数
   * @return 列表数据及分页数据
   */
  PageInfo<MainListResult> queryMainList(MainParam mainParam) throws BusinessException;
  
  /**
   * @Descrption 通过systemId删除系统信息
   * @author dongxu
   * @date 2018年5月27日下午2:24:54
   * @param mainParam
   */
  void deleteMainBySystemId(MainParam mainParam) throws BusinessException;
  
}

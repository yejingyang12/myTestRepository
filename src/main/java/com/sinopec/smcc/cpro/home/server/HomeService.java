/**
* 2018. 
* @Title HomeRecordService.java
* @Package com.sinopec.smcc.cpro.home.server
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:15:26
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.server;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.home.entity.HomeParam;
import com.sinopec.smcc.cpro.home.entity.HomeResult;

/**
 * @Title HomeRecordService.java
 * @Package com.sinopec.smcc.cpro.home.server
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:15:26
 * @version V1.0
 */
public interface HomeService {

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年6月6日下午1:26:08
   * @param hParam
   * @return
   */
  PageInfo<HomeResult> homeRecordStatisticsList(HomeParam hParam) throws BusinessException;

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年6月6日下午1:59:15
   * @param hParam
   * @return
   */
  PageInfo<HomeResult> homeScoreStatisticsList(HomeParam hParam) throws BusinessException;

}

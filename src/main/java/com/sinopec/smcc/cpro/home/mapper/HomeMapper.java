/**
* 2018. 
* @Title HomeMapper.java
* @Package com.sinopec.smcc.cpro.home.mapper
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:58:26
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.home.entity.HomeParam;
import com.sinopec.smcc.cpro.home.entity.HomeResult;

/**
 * @Title HomeMapper.java
 * @Package com.sinopec.smcc.cpro.home.mapper
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:58:26
 * @version V1.0
 */
public interface HomeMapper {

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年6月6日下午1:32:46
   * @param hParam
   * @return
   */
  List<HomeResult> selectHomeRecordStatistics(HomeParam hParam);

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年6月6日下午2:00:21
   * @param hParam
   * @return
   */
  List<HomeResult> selectScoreStatisticsList(HomeParam hParam);

}

/**
* 2018. 
* @Title SystemCodeService.java
* @Package com.sinopec.smcc.cpro.systemcode.server
* @Description: TODO:
* @author zhouyu
* @date 2018年6月3日下午10:55:21
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.server;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;

/**
 * @Title SystemCodeService.java
 * @Package com.sinopec.smcc.cpro.systemcode.server
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午10:55:21
 * @version V1.0
 */
public interface SystemCodeService {
  
  /**
   * @Descrption 根据参数类型和参数值查询中文
   * @author zhouyu
   * @date 2018年6月4日下午2:18:09
   * @param type
   * @return
   * @throws BusinessException
   */
  public String getConstantName(Integer fkCodeType,Integer deleteStatus,String systemCode) throws BusinessException;

  /**
   * @Descrption 单选下拉框数据
   * @author zhouyu
   * @date 2018年6月4日上午9:13:14
   * @param tParam
   * @return
   */
  public String getConstantByName(SystemCodeParam systemCodeParam) throws BusinessException;

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年6月4日上午11:40:00
   * @param systemCodeParam
   * @return
   */
  public String getSingleSelect(SystemCodeParam systemCodeParam) throws BusinessException;

  /**
   * @Descrption 多选树结构
   * @author zhouyu
   * @date 2018年6月4日下午1:39:54
   * @param systemCodeParam
   * @return
   */
  public String getConstantTreeByName(SystemCodeParam systemCodeParam) throws BusinessException;
}

/**
* 2018. 
* @Title SystemMapper.java
* @Package com.sinopec.smcc.cpro.system.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:43:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.system.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.system.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;

/**
 * @Title SystemMapper.java
 * @Package com.sinopec.smcc.cpro.system.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:43:13
 * @version V1.0
 */
public interface SystemMapper {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月25日下午2:55:13
   * @param systemParam
   * @return
   */
   List<SystemListResult> selectAllBySystemParam(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月25日下午6:52:45
   * @param systemListResult
   */
   void insertSystem(SystemParam systemParam);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月27日下午11:19:28
   * @param systemCodeParam
   * @return
   */
  List<SystemCodeListResult> selectSystemCodeListByParam(SystemCodeParam systemCodeParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月28日下午3:52:46
   * @param systemParam
   * @return
   */
  SystemListResult selectDetailsSystem(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月28日下午5:42:01
   * @param systemParam
   * @return
   */
  SystemListResult selectEditSystem(SystemParam systemParam);

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月28日下午4:47:58
   */
  public void updateKeySystem(SystemParam systemParam);
}

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

import java.util.List;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemGradingInfoOneResult;

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
   * 获得下拉列表数据
   * @author zhouyu
   * @date 2018年6月4日上午11:32:26
   * @param request
   * @param systemCodeParam：传入json格式数据，支持针对
   *                         代码类型：codeType
   *                         父级ID：systemFatherCode查询
   *                         代码名称：codeName
   *                         代码ID：pkId
   *                         代码Code：systemCode
   *                         进行查询
   * @return list：返回数据中包含{systemCode：1,codeName: 北京 }
   * @throws BusinessException
   */
  List<SystemCodeListResult> querySystemCodeForKeySystemCode(
      SystemCodeParam systemCodeParam) throws BusinessException;

  /**
   * 获得下拉列表数据
   * @author zhouyu
   * @date 2018年6月4日上午11:32:26
   * @param request
   * @param systemCodeParam：传入json格式数据，支持针对
   *                         代码类型：codeType
   *                         父级ID：systemFatherCode查询
   *                         代码名称：codeName
   *                         代码ID：pkId
   *                         代码Code：systemCode
   *                         进行查询
   * @return list：返回数据中包含{systemCode：北京,codeName: 北京 }
   * @throws BusinessException
   */
  List<SystemCodeListResult> querySystemCodeForKeyCodeName(
      SystemCodeParam systemCodeParam) throws BusinessException;

  /**
   * 获取定级页面3级数据
   * @Descrption
   * @author yejingyang
   * @date 2018年6月11日下午5:03:27
   * @param systemCodeParam
   * @return
   */
  List<SystemGradingInfoOneResult> queryGradingInfoList(
      SystemCodeParam systemCodeParam) throws BusinessException;
}

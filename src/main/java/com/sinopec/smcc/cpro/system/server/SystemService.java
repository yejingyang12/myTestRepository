/**
* 2018. 
* @Title SystemService.java
* @Package com.sinopec.smcc.cpro.system.server
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:47:32
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.system.entity.SystemEchoParam;
import com.sinopec.smcc.cpro.system.entity.SystemEchoResult;
import com.sinopec.smcc.cpro.system.entity.SystemGradingChangeResult;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;

/**
 * @Title SystemService.java
 * @Package com.sinopec.smcc.cpro.system.server
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:47:32
 * @version V1.0
 */
public interface SystemService {

  /**
   * 	响应系统列表数据
   * @author hanxin
   * @date 2018年5月25日下午2:46:48
   * @param systemParam
   * @return
   */
  PageInfo<SystemListResult> querySystemList(SystemParam systemParam);
  
  /**
   * 	添加或修改系统信息
   * @author hanxin
   * @date 2018年5月25日下午6:36:47
   * @param request
   * @param systemListResult
   * @return
   * @throws BusinessException 
   */
  String saveSystem(String userName, SystemParam systemParam) throws BusinessException;
  
  /**
   *  查询系统代码信息
   * @author dongxu
   * @date 2018年5月27日下午11:16:17
   * @param systemCodeParam
   * @return
   * @throws BusinessException
   */
//  List<SystemCodeListResult> querySystemCodeList(SystemCodeParam systemCodeParam) throws BusinessException;
  
  /**
   * 	查询系统信息详情
   * @author hanxin
   * @date 2018年5月28日下午3:49:25
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  SystemResult queryDetailsSystem(SystemParam systemParam) throws BusinessException;
  
  /**
   * 修改系统信息查询详情
   * @author hanxin
   * @date 2018年5月28日下午5:38:30
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  String editSystem(String userName, SystemParam systemParam) throws BusinessException;

  /**
   * @Descrption 修改系统状态
   * @author dongxu
   * @date 2018年5月30日上午10:26:08
   * @param systemParam
   * @return
   */
  void editSystemStatusBySystemId(SystemParam systemParam) throws BusinessException ;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月5日上午10:03:30
   * @param systemParam
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  void exportExcelForSystemTemplate(SystemParam systemParam) throws FileNotFoundException, IOException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月5日下午3:24:51
   * @throws IOException 
   * @throws BusinessException 
   */
  void importForSystemTemplate() throws IOException, BusinessException;


  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月6日下午3:36:33
   * @param request
   * @param response
   * @param strFilePath
   */
  void exportUploadSystemInfo(HttpServletRequest request,
      HttpServletResponse response, String strFilePath);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午3:05:00
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月12日上午9:37:48
   * @param systemParam
   * @return
   */
  SystemGradingChangeResult queryGradingEditAudit(SystemParam systemParam);
  
  /**
   * @Descrption 通过SystemId获取系统信息
   * @author dongxu
   * @date 2018年6月21日上午11:18:09
   * @param systemParam
   * @return
   */
  SystemResult querySystemInformationBySystemId(SystemParam systemParam);
  
  /**
   * @Descrption 通过系统ID查询审核列表系统信息
   * @author dongxu
   * @date 2018年7月31日下午5:37:00
   * @param systemParam
   * @return
   */
  SystemResult querySystemByCheck(SystemParam systemParam);
  
  /**
   * @Descrption 
   * @author Aran
   * @date 2018年7月31日下午9:43:41
   * @param systemEchoParam
   * @return
   */
  List<SystemEchoResult> querySystemInfoEchoList(SystemEchoParam systemEchoParam);
  
}

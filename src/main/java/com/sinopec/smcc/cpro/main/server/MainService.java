/**
* @Title MainService.java
* @Package com.sinopec.smcc.cpro.main.server
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:45:48
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

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

  /**
   * @Descrption 导出excel
   * @author dongxu
   * @date 2018年6月4日上午9:25:56
   */
  void exportExcelForMain()throws BusinessException;
  
  /**
   * @Descrption 下载
   * @author dongxu
   * @date 2018年6月4日下午2:59:44
   * @param request
   * @param response
   * @param strFilePath
   * @return
   * @throws BusinessException
   */
  void exportUploadApplicationInfo(
      HttpServletRequest request, HttpServletResponse response,String strFilePath)  
          throws BusinessException;

  /**
   * @Descrption 定级模板导出
   * @author dongxu
   * @date 2018年6月4日下午6:20:52
   */
  void exportExcelForGradeTemplate(HttpServletResponse response,String [] systemIds)
      throws BusinessException;
  
}

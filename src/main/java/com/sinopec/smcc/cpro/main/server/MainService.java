/**
* @Title MainService.java
* @Package com.sinopec.smcc.cpro.main.server
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:45:48
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.server;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.main.entity.MainListResult;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

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
  String exportExcelForMain(HttpServletRequest request)throws BusinessException;
  
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
  String exportExcelForGradeTemplate(HttpServletResponse response,String [] systemIds)
      throws BusinessException;

  /**
   * @Descrption 一键下载（表1 单位信息）
   * @author dongxu
   * @date 2018年6月7日下午12:01:45
   * @param mainParam
   */
  Map<String,Object> tableCompany(HttpServletRequest request,MainParam mainParam) 
      throws BusinessException;

  /**
   * @Descrption 一键下载（表2 系统信息）
   * @author dongxu
   * @date 2018年6月8日上午10:44:32
   * @param mainParam
   * @return
   */
  Map<String,Object> tableSystem(HttpServletRequest request,MainParam mainParam) throws BusinessException;

  /**
   * @Descrption 一键下载（表3 定级信息）
   * @author dongxu
   * @date 2018年6月9日下午5:13:38
   * @param mainParam
   * @return
   */
  Map<String,Object> tableGrading(HttpServletRequest request,MainParam mainParam) throws BusinessException;

  /**
   * @Descrption  一键下载（表4 附件信息）
   * @author dongxu
   * @date 2018年6月10日上午9:09:34
   * @param mainParam
   * @return
   */
  Map<String,Object> tableAttach(HttpServletRequest request,MainParam mainParam) throws BusinessException;

  /**
   * @Descrption 一键下载
   * @author dongxu
   * @date 2018年6月10日下午3:35:36
   * @param mainParam
   * @return
   */
  String oneButtonDownloading(HttpServletRequest request,
      HttpServletResponse response,MainParam mainParam) throws BusinessException;

  /**
   * @Descrption 高级搜索获取系统名称
   * @author dongxu
   * @date 2018年6月11日上午11:30:51
   * @param response
   * @param mainParam
   */
  List<MainListResult> querySystemName(MainParam mainParam) throws BusinessException;
  
  /** 
   * @Descrption 处理高级查询状态
   * @author dongxu
   * @date 2018年6月12日下午3:59:15
   * @param mainParam
   * @throws BusinessException
   */
  public void handleStatus(MainParam mainParam) throws BusinessException;

  /**
   * @Descrption 定级模板导入
   * @author dongxu
   * @date 2018年6月12日下午3:59:04
   * @param mainParam
   */
  void importExcelForGradeTemplate(String file) throws BusinessException;

  /**
   * @Descrption 修改申请变更（弹窗）
   * @author dongxu
   * @date 2018年6月13日下午5:50:36
   * @param mainParam
   * @return
   */
  String queryApplicationChange(MainParam mainParam) throws BusinessException;
  
  /**
   * @Descrption 备案表表头相关信息
   * @author dongxu
   * @date 2018年6月16日上午10:18:59
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  public Map<String,Object> tableRecord(MainParam mainParam) throws BusinessException;
  
  /**
   * @Descrption 修改所有状态
   * @author dongxu
   * @date 2018年6月18日上午2:04:09
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  public void editSystemStatusBySystemId(MainParam mainParam) throws BusinessException;

  /**
   * @Descrption 系统等保等级分布统计图
   * @author dongxu
   * @date 2018年6月25日上午10:08:31
   * @return
   */
  public List<MainListResult> queryGradingStatistics(MainParam mainParam) throws BusinessException;

  /**
   * @Descrption 备案单位数量 统计图
   * @author dongxu
   * @date 2018年6月26日下午2:42:20
   * @param mainParam
   * @return
   */
  List<MainListResult> queryRecordsCompanyNum(MainParam mainParam) throws BusinessException;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年7月23日下午6:54:42
   * @return
   */
  UserDTO getUserInfo() throws BusinessException;
}

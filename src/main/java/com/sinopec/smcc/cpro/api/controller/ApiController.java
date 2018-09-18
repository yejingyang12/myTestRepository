/**
* 2018. 
* @Title ApiController.java
* @Package com.sinopec.smcc.cpro.api.controller
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:09:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.result.PageUtil;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.api.service.ApiService;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.server.DiagramService;
import com.sinopec.smcc.depends.region.dto.BatchApprovalInfo;
import com.sinopec.smcc.depends.region.dto.CproForeignRequestParam;
import com.sinopec.smcc.depends.region.dto.CproResultParam;
import com.sinopec.smcc.depends.region.dto.SystemRelationBaseInfo;

/**
 * @Title ApiController.java
 * @Package com.sinopec.smcc.cpro.api.controller
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:09:13
 * @version V1.0
 */
@RestController
@RequestMapping("/restful/api")
public class ApiController {

  @Autowired
  public DiagramService diagramServiceImpl;
  @Autowired
  public ApiService apiServiceImpl;
  
  /**
   * @Descrption 获取系统等保管理趋势折线图数据
   * @author dongxu
   * @date 2018年7月17日上午10:10:09
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/querySystemTrendByYear", method = RequestMethod.GET)
  public RetResult<List<CproResultParam>> querySystemTrendByYear(HttpServletRequest request,
      @RequestParam("systemType") Integer paramInteger, @RequestParam("userId") String paramString1,
      @RequestParam("year") String paramString2) throws BusinessException{
    DiagramParam diagramParam = new DiagramParam();
    diagramParam.setSystemType(paramInteger);
    diagramParam.setUserId(paramString1);
    diagramParam.setYear(paramString2);
    // 调用service实体，获得
    List<CproResultParam> diagramListResult = this.diagramServiceImpl.
        queryApiSystemTrendByYear(request,diagramParam);
    return RetResultUtil.ok(diagramListResult);
  }
  
  /** 
   * @Descrption 获取定级信息
   * @author dongxu
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @param systemId 系统标准化代码
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getGradingInformation", method = RequestMethod.GET)
  public RetResult<CproResultParam> getGradingInformation(
      @RequestParam(value="systemId",required=true) String systemId) throws BusinessException{
    // 调用service实体，获得
    CproResultParam cproResultParam = this.apiServiceImpl.getGradingInformation(systemId);
    return RetResultUtil.ok(cproResultParam);
  }
  
  /**
   * @Descrption 通过userId获取待办列表
   * @author dongxu
   * @date 2018年8月6日下午5:19:03
   * @param request
   * @param userId
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getStayListByUserId", method = RequestMethod.POST)
  public RetResult<PageUtil> getStayHandle(@RequestBody CproForeignRequestParam 
      paramCproForeignRequestParam)throws BusinessException{
    // 调用service实体，获得
    PageUtil pageUtil = this.apiServiceImpl.getStayHandle(
        paramCproForeignRequestParam);
    return RetResultUtil.ok(pageUtil);
  }
  
  /**
   * @Descrption 批量审批
   * @author dongxu
   * @date 2018年8月6日下午5:19:03
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/batchApproval", method = RequestMethod.POST)
  public RetResult<Integer> batchApproval(@RequestBody BatchApprovalInfo paramBatchApprovalInfo) 
      throws BusinessException{
    // 调用service实体，获得
    Integer count = this.apiServiceImpl.batchApproval(paramBatchApprovalInfo);
    return RetResultUtil.ok(count);
  }
  
  /**
   * @Descrption 获取关联表信息列表
   * @author aran
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getSystemRelationInfo", method = RequestMethod.POST)
  public RetResult<PageUtil> getSystemRelationInfo(
      @RequestBody CproForeignRequestParam paramCproForeignRequestParam) throws BusinessException{
    // 调用service实体，获得
    PageUtil pageUtil = this.apiServiceImpl.
        getSystemRelationInfo(paramCproForeignRequestParam);
    return RetResultUtil.ok(pageUtil);
  }
  /**
   * @Descrption 获取关联表信息编辑回显
   * @author aran
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/editGetSystemRelationInfo", method = RequestMethod.POST)
  public RetResult<CproResultParam> editGetSystemRelationInfo(HttpServletRequest request,
      @RequestBody CproForeignRequestParam paramCproForeignRequestParam) throws BusinessException{
    // 调用service实体，获得
    CproResultParam cproResultParam = this.apiServiceImpl.
        editGetSystemRelationInfo(paramCproForeignRequestParam);
    return RetResultUtil.ok(cproResultParam);
  }
  
  /**
   * @Descrption 获取关联表信息编辑
   * @author aran
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/editSystemRelationInfo", method = RequestMethod.POST)
  public RetResult<String> editSystemRelationInfo(
      @RequestBody CproForeignRequestParam paramCproForeignRequestParam) throws BusinessException{
    // 调用service实体，获得
    this.apiServiceImpl.
        editSystemRelationInfo(paramCproForeignRequestParam);
    return RetResultUtil.ok();
  }
  
  /**
   * @Descrption 获取关联表信息删除
   * @author aran
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/deleteSystemRelationInfo", method = RequestMethod.POST)
  public RetResult<Boolean> deleteSystemRelationInfo(HttpServletRequest request,
      @RequestBody CproForeignRequestParam paramCproForeignRequestParam) throws BusinessException{
    // 调用service实体，获得
    boolean booValue = this.apiServiceImpl.
      deleteSystemRelationInfo(paramCproForeignRequestParam);
    return RetResultUtil.ok(booValue);
  }
  
  /**
   * @Descrption 获取关联表信息--已做等保信息
   * @author aran
   * @date 2018年7月17日上午11:10:08
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/getSystemRelationByGrade", method = RequestMethod.POST)
  public RetResult<List<SystemRelationBaseInfo>> getSystemRelationByGrade(
      @RequestBody String paramString) throws BusinessException{
    // 调用service实体，获得
    List<SystemRelationBaseInfo> systemRelationResultList = this.apiServiceImpl.
        getSystemRelationByGrade(paramString);
    return RetResultUtil.ok(systemRelationResultList);
  }
}

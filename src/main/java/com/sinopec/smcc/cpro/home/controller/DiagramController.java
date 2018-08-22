/**
* 2018. 
* @Title HomeController.java
* @Package com.sinopec.smcc.cpro.home.controller
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:18:01
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.entity.DiagramResult;
import com.sinopec.smcc.cpro.home.server.DiagramService;

/**
 * @Title HomeController.java
 * @Package com.sinopec.smcc.cpro.home.controller
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:18:01
 * @version V1.0
 */
@Controller
@RequestMapping("/diagram")
public class DiagramController {
  
  @Autowired
  public DiagramService diagramServiceImpl;
  
  /**
   * @Descrption 系统等保等级分布
   * @author yejingyang
   * @date 2018年6月10日上午11:10:49
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/querySystemLevelDiagram", method = RequestMethod.POST)
  public RetResult<DiagramResult> querySystemLevelDiagram( 
      @RequestBody DiagramParam diagramParam) throws BusinessException{
    // 调用service实体，获得
    DiagramResult diagramResult = this.diagramServiceImpl.querySystemLevelDiagram(diagramParam);
    // 通过resultApi实体组成返回参数
    return RetResultUtil.ok(diagramResult);
  }
  
  /**
   * @Descrption 不同等保级别系统在不同等保管理状态下详情
   * @author yejingyang
   * @date 2018年6月10日上午11:12:13
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/querySystemLevelBySystemType", method = RequestMethod.POST)
  public RetResult<DiagramResult> querySystemLevelBySystemType( 
      @RequestBody DiagramParam diagramParam) throws BusinessException{
    // 调用service实体，获得
    DiagramResult diagramResult = this.diagramServiceImpl.
        querySystemLevelBySystemType(diagramParam);
    // 通过resultApi实体组成返回参数

    return RetResultUtil.ok(diagramResult);
  }
  
  /**
   * @Descrption 备案单位数量Top10
   * @author yejingyang
   * @date 2018年6月10日上午11:12:23
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryRecordCompanyTop10", method = RequestMethod.POST)
  public RetResult<List<DiagramListResult>> queryRecordCompanyTop10( 
      @RequestBody DiagramParam diagramParam) throws BusinessException{
    // 调用service实体，获得
    List<DiagramListResult> diagramListResult = this.diagramServiceImpl.
        queryRecordCompanyTop10(diagramParam);
    // 通过resultApi实体组成返回参数

    return RetResultUtil.ok(diagramListResult);
  }
  
  /**
   * @Descrption 受理备案单位数量Top10
   * @author yejingyang
   * @date 2018年6月10日上午11:12:59
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryAcceptCompanyTop10", method = RequestMethod.POST)
  public RetResult<List<DiagramListResult>> queryAcceptCompanyTop10( 
      @RequestBody DiagramParam diagramParam) throws BusinessException{
    // 调用service实体，获得
    List<DiagramListResult> diagramListResult = this.diagramServiceImpl.
        queryAcceptCompanyTop10(diagramParam);
    // 通过resultApi实体组成返回参数

    return RetResultUtil.ok(diagramListResult);
  }
  
  /**
   * @Descrption 系统等保管理趋势
   * @author dongxu
   * @date 2018年6月28日下午5:02:07
   * @param request
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/querySystemTrendByYear", method = RequestMethod.POST)
  public RetResult<List<DiagramListResult>> querySystemTrendByYear(HttpServletRequest request,
      @RequestBody DiagramParam diagramParam) throws BusinessException{
    // 调用service实体，获得
    List<DiagramListResult> diagramListResult = this.diagramServiceImpl.
        querySystemTrendByYear(request,diagramParam);
    // 通过resultApi实体组成返回参数
    return RetResultUtil.ok(diagramListResult);
  }
}

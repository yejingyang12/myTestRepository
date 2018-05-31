/**
* 2018. 
* @Title GradingController.java
* @Package com.sinopec.smcc.cpro.grading.controller
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:03:11
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.grading.entity.AttachListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachParam;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.server.AttachService;
import com.sinopec.smcc.cpro.grading.server.GradingService;

/**
 * @Title GradingController.java
 * @Package com.sinopec.smcc.cpro.grading.controller
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:03:11
 * @version V1.0
 */
@Controller
@RequestMapping("/grading")
public class GradingController {
  
  @Autowired
  private GradingService gradingServiceImpl;
  @Autowired
  private AttachService AttachServiceImpl;
  
  /**
   * 
   * 查询定级信息
   * @author hanxin
   * @date 2018年5月29日上午10:21:19
   * @param request
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryDetailsGrading", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryDetailsGrading(HttpServletRequest request,GradingParam gradingParam) 
      throws BusinessException{
    List<GradingListResult> gradingListResult = 
        this.gradingServiceImpl.queryDetailsGrading(gradingParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(gradingListResult);
    return result;
  }
  
  /**
   * 
   * 查询定级信息详情
   * @author hanxin
   * @date 2018年5月29日上午10:21:33
   * @param request
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryEditGrading", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryEditGrading(HttpServletRequest request,GradingParam gradingParam) 
      throws BusinessException{
    GradingListResult gradingListResult = this.gradingServiceImpl.queryEditGrading(gradingParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(gradingListResult);
    return result;
  }
  
  /**
   * 
   * 添加定级信息
   * @author hanxin
   * @date 2018年5月31日上午11:37:14
   * @param request
   * @param gradingParam
   * @return
   */
  @RequestMapping(value = "/saveGrading", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi saveGrading(HttpServletRequest request,GradingParam gradingParam){
    String gradingId = this.gradingServiceImpl.saveGrading(gradingParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(gradingId);
    return result;
  }
  
  /**
   * 
   * 提交材料信息
   * @author hanxin
   * @date 2018年5月29日下午3:29:13
   * @param request
   * @param gradingParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryDetailsAttach", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryDetailsAttach(HttpServletRequest request,AttachParam attachParam) 
      throws BusinessException{
    List<AttachListResult> attachListResult = 
        this.AttachServiceImpl.queryDetailsAttach(attachParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(attachListResult);
    return result;
  }
  
  /**
   * 
   * 材料信息详情
   * @author hanxin
   * @date 2018年5月30日上午9:04:52
   * @param request
   * @param attachParam
   * @return
   * @throws BusinessException 
   */
  @RequestMapping(value = "/queryEditAttach", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryEditAttach(HttpServletRequest request,AttachParam attachParam) 
      throws BusinessException{
    List<AttachListResult> attachListResult = this.AttachServiceImpl.queryEditAttach(attachParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(attachListResult);
    return result;
  }
}

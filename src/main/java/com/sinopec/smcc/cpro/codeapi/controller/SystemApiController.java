/**
 * @Title SystemApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:02:33
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.controller;

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
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.codeapi.entity.SystemApiParam;
import com.sinopec.smcc.cpro.codeapi.entity.SystemApiResult;
import com.sinopec.smcc.cpro.codeapi.server.SystemApiService;

/**
 * @Title SystemApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:02:33
 * @version V1.0
 */
@Controller
@RequestMapping("systemapi")
public class SystemApiController {
  
  @Autowired
  private SystemApiService systemApiServiceImpl;

  /**
   * 获得组织机构下拉列表数据
   * @author zhouyu
   * @date 2018年6月9日下午1:11:19
   * @param request
   * @param systemCodeParam：传入json格式数据，支持针对
   * @return list：返回数据中包含[{"systemCode":"0","systemName":"系统名称0"}, {"systemCode":"1","systemName":"系统名称1"}]
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value="/querySystemApi", method = RequestMethod.POST)
  public ResultApi querySystemApi(HttpServletRequest request, 
      @RequestBody SystemApiParam systemApiParam) throws BusinessException{
    
    List<SystemApiResult> systemApiResult = 
        this.systemApiServiceImpl.querySystemApi(systemApiParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(systemApiResult);
    return result;
  }
}

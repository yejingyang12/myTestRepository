/**
 * @Title SysTemCodeController.java
 * @Package com.sinopec.smcc.cpro.systemcode.controller
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午7:59:43
 * @version V1.0
 */
package com.sinopec.smcc.cpro.systemcode.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.server.SystemCodeService;

/**
 * @Title SysTemCodeController.java
 * @Package com.sinopec.smcc.cpro.systemcode.controller
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午7:59:43
 * @version V1.0
 */
@Controller
@RequestMapping("/systemCode")
public class SystemCodeController {
  
  @Autowired
  public SystemCodeService systemCodeServiceImpl;
  
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
  @ResponseBody
  @RequestMapping(value="/querySystemCodeForKeySystemCode", method = RequestMethod.POST)
  public ResultApi querySystemCodeForKeySystemCode(HttpServletRequest request, 
      @RequestBody SystemCodeParam systemCodeParam) throws BusinessException{
    
    List<SystemCodeListResult> systemCodeResultList = 
        this.systemCodeServiceImpl.querySystemCodeForKeySystemCode(systemCodeParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(systemCodeResultList);
    return result;
  }
  
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
  @ResponseBody
  @RequestMapping(value="/querySystemCodeForKeyCodeName", method = RequestMethod.POST)
  public ResultApi querySystemCodeForKeyCodeName(HttpServletRequest request, 
      @RequestBody SystemCodeParam systemCodeParam) throws BusinessException{
    
    List<SystemCodeListResult> systemCodeResultList = 
        this.systemCodeServiceImpl.querySystemCodeForKeyCodeName(systemCodeParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(systemCodeResultList);
    return result;
  }
  
}

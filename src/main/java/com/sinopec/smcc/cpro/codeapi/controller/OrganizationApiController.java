/**
 * @Title OrganizationApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:38:11
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

import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiParam;
import com.sinopec.smcc.cpro.codeapi.server.OrganizationApiService;

/**
 * @Title OrganizationApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:38:11
 * @version V1.0
 */
@Controller
@RequestMapping("organizationapi")
public class OrganizationApiController {
  
  @Autowired
  private OrganizationApiService organizationApiServiceImpl;

  /**
   * 获得组织机构下拉列表数据
   * @author zhouyu
   * @date 2018年6月9日下午1:11:19
   * @param request
   * @param systemCodeParam：传入json格式数据，支持针对
   * @return list：返回数据中包含[{"organization": [{"orgCode": "00","orgName": "单位名称00"}],"plateName": "板块0","value": "1"}]
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  @RequestMapping(value="/queryOrganizationApi", method = RequestMethod.POST)
  public ResultApi queryOrganizationApi(HttpServletRequest request, 
      @RequestBody OrganizationApiParam organizationApiParam) throws BusinessException{
    
    List<OrganizationApiCascaderResult> organizationApiResult = 
        this.organizationApiServiceImpl.queryOrganizationApi(organizationApiParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(organizationApiResult);
    return result;
  }
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月9日下午2:46:15
   * @param request
   * @param organizationApiParam
   * @return list：返回数据中包含[{"orgCode": "0", "orgName": "单位名称0"}, {"orgCode": "1",  "orgName": "单位名称1"}]
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  @RequestMapping(value="/queryOrganizationForKeyOrganizationCode", method = RequestMethod.POST)
  public ResultApi queryOrganizationForKeyOrganizationCode(HttpServletRequest request, 
      @RequestBody OrganizationApiParam organizationApiParam) throws BusinessException{
    List<OrganizationApi> organizationApi = 
        this.organizationApiServiceImpl.queryOrgForKeyOrganizationCode(organizationApiParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(organizationApi);
    return result;
  }
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月9日下午2:46:20
   * @param request
   * @param organizationApiParam
   * @return list：返回数据中包含[{"orgCode": "单位名称0","orgName": "单位名称0"}, {"orgCode": "单位名称1", "orgName": "单位名称1"}]
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro)
  @RequestMapping(value="/queryOrganizationForKeyOrganizationName", method = RequestMethod.POST)
  public ResultApi queryOrganizationForKeyOrganizationName(HttpServletRequest request, 
      @RequestBody OrganizationApiParam organizationApiParam) throws BusinessException{
    List<OrganizationApi> organizationApi = 
        this.organizationApiServiceImpl.queryOrgForKeyOrganizationName(organizationApiParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(organizationApi);
    return result;
  }
}

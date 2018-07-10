/**
 * @Title JurisdictionApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:19:58
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.impl.UserApiServiceImpl;
import com.sinopec.smcc.depends.ubs.dto.ResourceDTO;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsFeignTemplate;

/**
 * @Title JurisdictionApiController.java
 * @Package com.sinopec.smcc.cpro.codeapi.controller
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:19:58
 * @version V1.0
 */
@Controller
@RequestMapping("/jurisdiction")
public class JurisdictionApiController {

  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  
  @Autowired
  private UserApiServiceImpl userApiServiceImpl;
  
  @Autowired
  private UbsFeignTemplate ubsFeignTemplate;

  @Value("${appId}")
  private String appId;

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
  @RequestMapping(value="/queryDataJurisdictionApi", method = RequestMethod.POST)
  public ResultApi queryDataJurisdictionApi(HttpServletRequest request) throws BusinessException{
    
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(organizationApiResult);
    return result;
  }
  
  @ResponseBody
  @RequestMapping(value="/queryMenuJurisdictionApi", method = RequestMethod.GET)
  public ResultApi queryMenuJurisdictionApi(HttpServletRequest request) throws BusinessException{
    ResultApi api = new ResultApi();
    UserDTO userDTO = this.userApiServiceImpl.getUserInfo();
    List<ResourceDTO> resourceDtoList = ubsFeignTemplate.getResourcesByUserId(userDTO.getUserId()+"");
    if (resourceDtoList != null && resourceDtoList.size()>0) {
      List<Map<String, Object>> backMenu = new ArrayList<Map<String,Object>>();
      for (ResourceDTO resourceDto : resourceDtoList) {
        if (StringUtils.isBlank(resourceDto.getParentId())) {
          Map<String, Object> map = new HashMap<String, Object>();
          convert(map, resourceDto);
          List<Map<String, Object>> childList = 
              getChild(resourceDto.getResourceId(), resourceDtoList);
          map.put("children", ((childList != null) && (childList.size() > 0)) ? childList : null);
          backMenu.add(map);
        }
      }
      api.setData(backMenu);
    }
    return api;
  }
  
  private List<Map<String, Object>> getChild(String resourceId, List<ResourceDTO> resourceDtoList)
  {
    List<Map<String, Object>> childMenu = new ArrayList<Map<String, Object>>();
    for (ResourceDTO ResourceDto : resourceDtoList) {
      if (StringUtils.isBlank(ResourceDto.getParentId()) || 
        !resourceId.equals(ResourceDto.getParentId())) {
        continue;
      }
      Map<String, Object> map = new HashMap<String, Object>();
      convert(map, ResourceDto);
      childMenu.add(map);
    }

    for (Map<String, Object> child : childMenu) {
      int hasChild = ((Integer)child.get("hasChild")).intValue();
      String childResourceId = (String)child.get("resourceId");

      if (hasChild > 0) {
        List<Map<String, Object> > childList = getChild(childResourceId, resourceDtoList);
        child.put("children", ((childList != null) && (childList.size() > 0)) ? childList : null);
      }
    }
    return childMenu;
  }

  private void convert(Map<String, Object> map, ResourceDTO resourceDto) {
    map.put("hasChild", resourceDto.getHasChild());
    map.put("resourceUrl", resourceDto.getResourceUrl());
    map.put("resourceId", resourceDto.getResourceId());
    map.put("resourceName", resourceDto.getResourceName());
    map.put("resourceType", resourceDto.getResourceType());
    map.put("resourceCode", resourceDto.getResourceCode());
    map.put("parentId", resourceDto.getParentId());
  }
}

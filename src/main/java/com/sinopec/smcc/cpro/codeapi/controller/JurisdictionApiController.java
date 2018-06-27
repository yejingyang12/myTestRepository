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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.common.ubs.util.UbsUtil;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;

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
  private UbsUtil ubsUtil;

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

    JSONObject json = this.ubsUtil.getMenuPermissions();
    if (json != null) {
      List<Map<String, Object>> backMenu = new ArrayList<Map<String,Object>>();
      boolean success = json.getBoolean("success").booleanValue();
      if (success) {
        JSONArray dataArray = JSONArray.parseArray(json.getString("data"));
        for (int i = 0; i < dataArray.size(); ++i) {
          JSONObject obj = (JSONObject)dataArray.get(i);
          String parentId = obj.getString("parentId");
          String resourceId = obj.getString("resourceId");
          if (StringUtils.isBlank(parentId)) {
            Map<String, Object> map = new HashMap<String, Object>();
            convert(map, obj);
            List<Map<String, Object>> childList = getChild(resourceId, dataArray);
            map.put("children", ((childList != null) && (childList.size() > 0)) ? childList : null);
            backMenu.add(map);
          }
        }
        api.setData(backMenu);
      } else {
        api.setMsg(json.getString("msg"));
      }
    }
    return api;
  }
  
  private List<Map<String, Object>> getChild(String resourceId, JSONArray menuData)
  {
    List<Map<String, Object>> childMenu = new ArrayList<Map<String, Object>>();
    for (int i = 0; i < menuData.size(); ++i) {
      JSONObject obj = (JSONObject)menuData.get(i);
      String parentId = obj.getString("parentId");
      if ((!(StringUtils.isNotBlank(parentId))) || 
        (!(resourceId.equals(parentId)))) continue;
      Map<String, Object> map = new HashMap<String, Object>();
      convert(map, obj);
      childMenu.add(map);
    }

    for (Map<String, Object> child : childMenu) {
      int hasChild = ((Integer)child.get("hasChild")).intValue();
      String childResourceId = (String)child.get("resourceId");

      if (hasChild > 0) {
        List<Map<String, Object> > childList = getChild(childResourceId, menuData);
        child.put("children", ((childList != null) && (childList.size() > 0)) ? childList : null);
      }
    }
    return childMenu;
  }

  private void convert(Map<String, Object> map, JSONObject obj) {
    map.put("hasChild", obj.get("hasChild"));
    map.put("resourceUrl", obj.get("resourceUrl"));
    map.put("resourceId", obj.get("resourceId"));
    map.put("resourceName", obj.get("resourceName"));
    map.put("resourceType", obj.get("resourceType"));
    map.put("resourceCode", obj.get("resourceCode"));
    map.put("parentId", obj.get("parentId"));
  }
}

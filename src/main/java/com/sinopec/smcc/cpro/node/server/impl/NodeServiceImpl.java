/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title NodeServiceImpl.java
* @Package com.sinopec.smcc.cpro.node.server.impl
* @Description: TODO:
* @author Aran
* @date 2018年6月7日下午10:43:37
* @version V1.0
*/
package com.sinopec.smcc.cpro.node.server.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.depends.ubs.client.UbsClient;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.mapper.NodeMapper;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title NodeServiceImpl.java
 * @Package com.sinopec.smcc.cpro.node.server.impl
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月7日下午10:43:37
 * @version V1.0
 */
@Service
public class NodeServiceImpl implements NodeService {

  @Autowired
  private NodeMapper nodeMapper;
  @Autowired
  UbsClient ubsClient;
  
  @Value("${appId}") 
  private String appId;
  
  /**
   * 响应单位列表数据
   */
  @Override
  public List<NodeResult> queryNodeList(NodeParam nodeParam) {
    
    // 获得响应列表数据
    List<NodeResult> nodeListResultList = 
        this.nodeMapper.selectAllBynodeParam(nodeParam);
    
    return nodeListResultList;
  }
  /**
   * 添加节点信息
   */
  @Override
  @Transactional
  public void addNodeInfo(NodeParam nodeParam) {
    
    
    nodeParam.setNodeId(Utils.getUuidFor32());
    nodeParam.setCreateTime(DateUtils.getDate());
    //nodeParam.setOperator(userName);
    // 添加节点数据
    this.nodeMapper.insertBynodeParam(nodeParam);
  }
  @Override
  public String getUserNameFromRequest(HttpServletRequest request) {
    /*HttpSession session = request.getSession();
    SSOPrincipal attribute = (SSOPrincipal)session.getAttribute(SSOPrincipal.NAME_OF_SESSION_ATTR);
    String uid = attribute.getUid();
    String userInfo = ubsClient.getUserByAccount(appId,"1", uid);
    String userName = JSONObject.parseObject(JSONObject.parseObject(userInfo).
        getString("data")).get("userName").toString();*/
    String userName = "admin";
    return userName;
  }
  @Override
  public NodeResult selectSingleNode(NodeParam nodeParam) {
    NodeResult nodeResult= this.nodeMapper.selectSingleNode(nodeParam);
    return nodeResult;
  }
  @Override
  @Transactional
  public void editNodeInfo(NodeParam nodeParam) {
    nodeParam.setCreateTime(DateUtils.getDate());
    this.nodeMapper.updateNodeByNodeId(nodeParam);
  }
}

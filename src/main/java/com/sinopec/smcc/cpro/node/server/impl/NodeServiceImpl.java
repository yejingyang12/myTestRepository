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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowParam;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowResult;
import com.sinopec.smcc.cpro.codeapi.mapper.WorkFlowMapper;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.mapper.NodeMapper;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

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
  
  @Value("${appId}") 
  private String appId;
  
  @Autowired
  private UserApiService userApiServiceImpl;
  @Autowired
  private WorkFlowMapper workFlowMapperImpl;
  @Autowired
  private UbsTemplate ubsTemplate;
  
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
   * 获取审核页签的鱼骨图列表
   */
  @Override
  public List<NodeResult> queryExamineNodeList(NodeParam nodeParam) {
    
    // 获得响应列表数据
    List<NodeResult> nodeListResultList = 
        this.nodeMapper.selectAllForExamine(nodeParam);
    
    return nodeListResultList;
  }
  /**
   * 添加节点信息
   */
  @Override
  @Transactional
  public void addNodeInfo(NodeParam nodeParam) {
    
    nodeParam.setNodeId(Utils.getUuidFor32());
    if(nodeParam.getCreateTime() == null){
      nodeParam.setCreateTime(DateUtils.getDate());
    }
    // 添加节点数据
    this.nodeMapper.insertBynodeParam(nodeParam);
  }
  @Override
  public String getUserNameFromRequest(HttpServletRequest request) {
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    return userDTO.getUserName();
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
  @Override
  public NodeResult querySingleNode(NodeParam nodeParam) {
    NodeResult nodeResult= this.nodeMapper.querySingleNode(nodeParam);
    return nodeResult;
  }
  @Override
  public NodeResult queryChangeInformation(NodeParam nodeParam) {
    NodeResult nodeResult = this.nodeMapper.queryChangeInformationBySystemId(nodeParam);
    return nodeResult;
  }
  @Override
  public NodeResult queryNextNode(NodeParam nodeParam) {
    NodeResult nodeResult = new NodeResult();
    WorkFlowParam workFlowParam = new WorkFlowParam();
    workFlowParam.setSystemId(nodeParam.getSystemId());
    WorkFlowResult workFlowResult = this.workFlowMapperImpl.
        selectWorkFlowByBusinessId(workFlowParam);
    if(workFlowResult != null){
      //下一步操作人
      String nextApproverName = "";
      List<String> strList = new ArrayList<String>();
      String strNextApprover = workFlowResult.getNextApprover();
      if(StringUtils.isNotBlank(strNextApprover)){
        String[] nextApprovers = strNextApprover.split(",");
        if(nextApprovers.length > 0){
          for (String string : nextApprovers) {
            UserDTO userDTO  = ubsTemplate.getUserByUserId(string);
            if(userDTO != null){
              strList.add(userDTO.getUserName());
            }
          }
        }
        for (int i=0;i<strList.size();i++) {
          if(i == 0){
            nextApproverName = strList.get(i);
          }else{
            nextApproverName = nextApproverName + "," + strList.get(i);
          }
        }
        nodeResult.setOperator(nextApproverName);
      }
      //操作
      Integer checkResult = workFlowResult.getCheckResult();
      if(checkResult != null){
        switch (checkResult) {
        case 0:
          nodeResult.setOperation("待企业业务审核");
          break;
        case 1:
          nodeResult.setOperation("待总部安全审核");
          break;
        case 2:
          nodeResult.setOperation("待总部安全审核");
          break;
        case 3:
          nodeResult.setOperation("");
          break;
        case 4:
          nodeResult.setOperation("");
          break;
        case 5:
          nodeResult.setOperation("");
          break;
        default:
          break;
        }
      }
      
    }
    // TODO Auto-generated method stub
    
    return nodeResult;
  }
}

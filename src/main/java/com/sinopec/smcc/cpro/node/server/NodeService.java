/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title NodeService.java
* @Package com.sinopec.smcc.cpro.node.server
* @Description: TODO:
* @author Aran
* @date 2018年6月7日下午10:34:39
* @version V1.0
*/
package com.sinopec.smcc.cpro.node.server;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;

/**
 * @Title NodeService.java
 * @Package com.sinopec.smcc.cpro.node.server
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月7日下午10:34:39
 * @version V1.0
 */
public interface NodeService {

  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月7日下午10:56:46
   * @param request
   * @param nodeParam
   * @return
   */
  List<NodeResult> queryNodeList(NodeParam nodeParam);

  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月7日下午10:56:46
   * @param request
   * @param nodeParam
   * @return
   */
  void addNodeInfo(NodeParam nodeParam);
  
  /**
   * @Descrption从request中获取用户名
   * @author yejingyang
   * @date 2018年6月10日下午3:00:43
   * @param request
   * @return
   */
  String getUserNameFromRequest(HttpServletRequest request);

  /**
   * @Descrption根据id查询节点
   * @author yejingyang
   * @date 2018年6月25日上午8:59:38
   * @param nodeParam
   * @return
   */
  NodeResult selectSingleNode(NodeParam nodeParam);

  /**
   * @Descrption修改覆盖节点
   * @author yejingyang
   * @date 2018年6月25日上午9:18:06
   * @param nodeParam
   */
  void editNodeInfo(NodeParam nodeParam);

  NodeResult querySingleNode(NodeParam nodeParam);

  NodeResult queryChangeInformation(SystemParam systemParam);

  /**
   * @Descrption获取审核页签的鱼骨图列表
   * @author yejingyang
   * @date 2018年9月4日下午12:51:42
   * @param nodeParam
   * @return
   */
  List<NodeResult> queryExamineNodeList(NodeParam nodeParam);

  /**
   * @Descrption获取下一步审核人信息
   * @author yejingyang
   * @date 2018年9月4日下午6:59:07
   * @param nodeParam
   * @return
   */
  NodeResult queryNextNode(NodeParam nodeParam);
}

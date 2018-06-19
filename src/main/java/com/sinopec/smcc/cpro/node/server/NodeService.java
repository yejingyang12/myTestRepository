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
}

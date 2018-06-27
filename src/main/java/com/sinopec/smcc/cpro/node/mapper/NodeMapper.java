/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title NodeMapper.java
* @Package com.sinopec.smcc.cpro.node.mapper
* @Description: TODO:
* @author Aran
* @date 2018年6月7日下午10:34:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.node.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;


/**
 * @Title NodeMapper.java
 * @Package com.sinopec.smcc.cpro.node.mapper
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月7日下午10:34:15
 * @version V1.0
 */
public interface NodeMapper {

  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月7日下午11:06:59
   * @param nodeParam
   * @return
   */
  List<NodeResult> selectAllBynodeParam(NodeParam nodeParam);
  
  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月7日下午11:06:59
   * @param nodeParam
   * @return
   */
  int insertBynodeParam(NodeParam nodeParam);

  /**
   * @Descrption判断是否有此节点
   * @author yejingyang
   * @date 2018年6月25日上午9:02:55
   * @param nodeParam
   * @return
   */
  NodeResult selectSingleNode(NodeParam nodeParam);

  /**
   * @Descrption根据id修改节点
   * @author yejingyang
   * @date 2018年6月25日上午9:23:44
   * @param nodeParam
   */
  void updateNodeByNodeId(NodeParam nodeParam);
}

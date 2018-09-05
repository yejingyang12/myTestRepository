/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title NodeController.java
* @Package com.sinopec.smcc.cpro.node.controller
* @Description: TODO:
* @author Aran
* @date 2018年6月7日下午10:47:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.node.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.base.result.RetResultUtil;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;

/**
 * @Title NodeController.java
 * @Package com.sinopec.smcc.cpro.node.controller
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月7日下午10:47:13
 * @version V1.0
 */
@Controller
@RequestMapping("/node")
public class NodeController {

  @Autowired
  public NodeService nodeServiceImpl;
  
  /**
   * @Descrption 鱼骨图列表
   * @author Aran
   * @date 2018年6月7日下午10:48:44
   * @param request
   * @param file
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryNodeList", method = RequestMethod.POST)
  public RetResult<List<NodeResult>> queryNodeList(
      @RequestBody NodeParam nodeParam) throws BusinessException{
    List<NodeResult> page = this.nodeServiceImpl.queryNodeList(nodeParam);
    //通过resultApi实体组成返回参数

    return RetResultUtil.ok(page);
  }
  
  /**
   * @Descrption  获取审核页签的鱼骨图列表
   * @author yejingyang
   * @date 2018年9月4日下午12:51:08
   * @param nodeParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryExamineNodeList", method = RequestMethod.POST)
  public RetResult<List<NodeResult>> queryExamineNodeList(
      @RequestBody NodeParam nodeParam) throws BusinessException{
    List<NodeResult> page = this.nodeServiceImpl.queryExamineNodeList(nodeParam);
    //通过resultApi实体组成返回参数

    return RetResultUtil.ok(page);
  }
  /**
   * @Descrption  下一步审核人节点
   * @author yejingyang
   * @date 2018年9月4日下午8:11:53
   * @param nodeParam
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryNextNode", method = RequestMethod.POST)
  public RetResult<NodeResult> queryNextNode(
      @RequestBody NodeParam nodeParam) throws BusinessException{
    NodeResult node = this.nodeServiceImpl.queryNextNode(nodeParam);
    //通过resultApi实体组成返回参数

    return RetResultUtil.ok(node);
  }
  
  
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryNode", method = RequestMethod.POST)
  public RetResult<NodeResult> queryNode(
      @RequestBody NodeParam nodeParam) throws BusinessException{
    NodeResult node = this.nodeServiceImpl.querySingleNode(nodeParam);
    //通过resultApi实体组成返回参数

    return RetResultUtil.ok(node);
  }
  
  @ResponseBody
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @RequestMapping(value = "/queryChangeInformation", method = RequestMethod.POST)
  public RetResult<NodeResult> queryChangeInformation(
      @RequestBody SystemParam systemParam) throws BusinessException{
    NodeResult node = this.nodeServiceImpl.queryChangeInformation(systemParam);
    
    return RetResultUtil.ok(node);
  }
}

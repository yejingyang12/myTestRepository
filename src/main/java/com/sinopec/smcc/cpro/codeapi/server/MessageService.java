/**
* Copyright 2018. 
* @Title MessageService.java
* @Package com.sinopec.smcc.cpro.codeapi.server
* @Description: TODO:
* @author yejingyang
* @date 2018年8月27日下午5:18:04
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.server;

/**
 * @Title MessageService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年8月27日下午5:18:04
 * @version V1.0
 */
public interface MessageService {

  
  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年8月28日下午5:20:17
   * @param toAddresses   发送对象数组
   * @param toAddresses   抄送对象数组(没有时可以为null)
   * @param checkType     审核类型：
   *                          1:定级审核
   *                          2:撤销备案审核
   *                          3:定级信息变更审核
   *                          
   * @param checkResult   审核结果，包括：
   *                          "0":企业发起审核
   *                          "1":总部发起审核
   *                          "2":企业审核通过
   *                          "3":企业审核未通过
   *                          "4":总部审核通过
   *                          "5":总部审核未通过
   *                          
   * @param checkReason   审核原因
   */
  void sendMessageForCheck(String[] toAddresses, String[] copyAddresses, Integer checkType, 
      String checkResult, String checkReason);

}

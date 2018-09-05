/**
* Copyright 2018. 
* @Title MessageServiceImpl.java
* @Package com.sinopec.smcc.cpro.codeapi.server.impl
* @Description: TODO:
* @author yejingyang
* @date 2018年8月27日下午5:18:49
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.common.rabbitmq.MessageSender;
import com.sinopec.smcc.cpro.codeapi.server.MessageService;

/**
 * @Title MessageServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年8月27日下午5:18:49
 * @version V1.0
 */
@Service
public class MessageServiceImpl implements MessageService {
  @Autowired
  private MessageSender messageSenderImpl;
  
  @Override
  public void sendMessageForCheck(String[] toAddresses, String[] copyAddresses, Integer checkType, 
      String checkResult, String checkReason) {
    String subject = "";
    String text = "";
    //对发送内容进行组装
    //发起审核
    switch (checkResult) {
    //企业发起审核
    case "0":
    //总部发起审核
    case "1":
      subject = "待审核信息";
      text = "您有一条信息待审核！";
      break;
    //企业审核通过
    case "2":
      //企业通过撤销备案直接给发起人发送审核成功邮件
      if(checkType == 2){
        subject = "审核信息";
        text = "您的审核已通过！";
      }else{
        subject = "待审核信息";
        text = "您有一条信息待审核！";
      }
      break;
    //企业审核未通过
    case "3":
      subject = "审核信息";
      text = "您的审核未通过！原因是：" + checkReason;
      break;
    //总部审核通过
    case "4":
      subject = "审核信息";
      text = "您的审核已通过！";
      break;
    //总部审核未通过
    case "5":
      subject = "审核信息";
      text = "您的审核未通过！原因是：" + checkReason;
      break;
    default:
      break;
    }
    this.messageSenderImpl.noticeSimpleMailSend(toAddresses, copyAddresses, subject, text);
  }

}

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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.common.rabbitmq.MessageSender;
import com.sinopec.smcc.cpro.codeapi.server.MessageService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.depends.ubs.dto.PageDTO;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

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
  @Autowired
  private SystemMapper systemMapperImpl;
  @Autowired
  private UbsTemplate ubsTemplate;
  
  @Override
  public void sendMessageForCheck(String[] toAddresses, String[] copyAddresses, Integer checkType, 
      String checkResult, String checkReason, String systemId) {
    String subject = "等级保护审批通知邮件";
    String text = "";
    String address = "http://cpro.smcc.sinopec.com:8082/";
    SystemParam systemParam = new SystemParam();
    systemParam.setSystemId(systemId);
    SystemResult systemResult = this.systemMapperImpl.selectSystem(systemParam);
    if(systemResult != null){
      String standardizedCode = systemResult.getStandardizedCode();
      String systemName = systemResult.getSystemName();
      //对发送内容进行组装
      //发起审核
      switch (checkResult) {
      //企业发起审核
      case "0":
        //subject = "等级保护审批通知邮件";
        text = "您好！\n"
            + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”需要您的审批，请知悉！\n"
            + "点击以下链接进入系统：\n"
            + address+"\n"
            + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        break;
        //总部发起审核
      case "1":
        //subject = "等级保护审批通知邮件";
        text = "您好！\n"
            + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”需要您的审批，请知悉！\n"
            + "点击以下链接进入系统：\n"
            + address+"\n"
            + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        break;
        //企业审核通过
      case "2":
        //企业通过撤销备案直接给发起人发送审核成功邮件
        if(checkType == 2){
          //subject = "等级保护审批通知邮件";
          text = "您好！\n"
              + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”审批通过，请知悉！\n"
              + "点击以下链接进入系统：\n"
              + address+"\n"
              + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        }else{
          //subject = "等级保护审批通知邮件";
          text = "您好！\n"
              + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”需要您的审批，请知悉！\n"
              + "点击以下链接进入系统：\n"
              + address+"\n"
              + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        }
        break;
        //企业审核未通过
      case "3":
        //subject = "等级保护审批通知邮件";
        text = "您好！\n"
            + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”审批未通过，拒绝原因："+checkReason+"。请知悉！\n"
            + "点击以下链接进入系统：\n"
            + address+"\n"
            + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        break;
        //总部审核通过
      case "4":
        //subject = "等级保护审批通知邮件";
        text = "您好！\n"
            + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”审批通过，请知悉！\n"
            + "点击以下链接进入系统：\n"
            + address+"\n"
            + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        break;
        //总部审核未通过
      case "5":
        //subject = "等级保护审批通知邮件";
        text = "您好！\n"
            + "系统检测“编号："+standardizedCode+" 信息系统名称："+systemName+"”审批未通过，拒绝原因："+checkReason+"。请知悉！\n"
            + "点击以下链接进入系统：\n"
            + address+"\n"
            + "ps:该邮件为系统自动发送的邮件，请勿回复。";
        break;
      default:
        break;
      }
      this.messageSenderImpl.noticeSimpleMailSend(toAddresses, copyAddresses, subject, text);
    }
    //系统不存在
  }

  @Override
  public List<UserDTO> getUsersByUserId(String userId) {
    UserDTO userDTOTemp  = ubsTemplate.getUserByUserId(userId);
    //获取与发起人同角色的
    PageDTO<UserDTO> pageByOrgCode = this.ubsTemplate.
        getUsersPageByOrgCode("", userDTOTemp.getOrgCode());
    List<UserDTO> root = pageByOrgCode.getRoot();
    return root;
  }
}

/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title TestConfig.java
* @Package com.sinopec.smcc.cpro.webservice.config
* @Description: TODO:
* @author Aran
* @date 2018年9月10日下午8:29:55
* @version V1.0
*/
package com.sinopec.smcc.cpro.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pcitc.ssc.dps.inte.ISFMQExecResult;
import com.sinopec.smcc.cpro.webservice.service.impl.WsMQExecResultService;


 @Configuration
 public class WebConfig {

   @Autowired
   private SpringBus spingBus;
   
   @Autowired
   private ISFMQExecResult mqexecresultservice;
   
   @Bean
   public ServletRegistrationBean dispatcherServlet() {
       return new ServletRegistrationBean(new CXFServlet(), "/service/*");
   }
   
   @Bean(name = Bus.DEFAULT_BUS_ID)
   public SpringBus springBus() {
       return new SpringBus();
   }
   @Bean
   public ISFMQExecResult mqexecresultservice() {
       return new WsMQExecResultService();
   }
   @Bean
   public Endpoint endpoint() {
       EndpointImpl endpoint = new EndpointImpl(spingBus,mqexecresultservice);
       endpoint.publish("/mqresultservice");
       return endpoint;
   }
 }
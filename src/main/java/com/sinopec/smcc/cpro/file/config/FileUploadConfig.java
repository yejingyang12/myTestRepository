/**
* Copyright 2018. 
* @Title FileUploadConfig.java
* @Package com.sinopec.smcc.cpro.file.config
* @Description: spring Boot配置
* @author yejingyang
* @date 2018年8月23日上午11:08:16
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title FileUploadConfig.java
 * @Package com.sinopec.smcc.cpro.file.config
 * @Description: spring Boot上传文件配置
 * @author yejingyang
 * @date 2018年8月23日上午11:08:16
 * @version V1.0
 */
@Configuration
public class FileUploadConfig {
  /**
   * @Descrption  文件上传配置
   * @author yejingyang
   * @date 2018年8月23日上午11:10:18
   * @param maxFileSize     单个文件最大(30*1024*1024)
   * @param maxRequestSize  设置总上传数据总大小(30*1024*1024)
   * @return
   */
  @Bean
  public MultipartConfigElement multipartConfigElement(
      @Value("31457280") String maxFileSize,
      @Value("31457280") String maxRequestSize) {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    // ${multipart.maxFileSize}单个文件最大maxFileSize
    factory.setMaxFileSize(30*1024*1024);
    // ${multipart.maxRequestSize}设置总上传数据总大小maxRequestSize
    factory.setMaxRequestSize(30*1024*1024);
    return factory.createMultipartConfig();
  }
}

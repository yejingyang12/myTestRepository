/**
* @Title FileController.java
* @Package com.sinopec.smcc.cpro.file.controller
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午1:03:37
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.entity.AttachResult;
import com.sinopec.smcc.cpro.file.server.FileService;

/**
 * @Title FileController.java
 * @Package com.sinopec.smcc.cpro.file.controller
 * @Description: TODO:附件上传下载控制
 * @author zhouyu
 * @date 2018年5月30日下午1:03:37
 * @version V1.0
 */

@Controller
@RequestMapping("/cpro")
public class FileController {
  
  @Autowired
  public FileService fileService;
  
  /**
   * 上传文件
   * @author eric
   * @date 2018年6月6日下午7:13:32
   * @param request
   * @param file
   * @return
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  public ResultApi uploadFile(HttpServletRequest request,
      @RequestParam("file") MultipartFile file) throws BusinessException{
    AttachResult attachResult = this.fileService.uploadFile(request, file);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(attachResult);
    return result;
  }
  
  @ResponseBody
  @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
  public ResultApi deleteFile(HttpServletRequest request, 
      @RequestBody AttachParam attachParam) throws BusinessException{
    this.fileService.deleteFile(attachParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
  
  /**
   * 下载文件
   * @author eric
   * @date 2018年6月6日下午6:02:59
   * @param request
   * @param response
   * @param attachParam：下载文件时包含两种方式下载。
   *                     方式1：通过url下载，如传入uploadUrl则直接下载服务器端本地url数据文件。
   *                     方式2：通过fileId下载，如传入fileId则支持下载mongodb内的文件数据。
   *                     同一次调用支持方式1与方式2其中一种方法，首先判断uploadUrl，
   *                     如uploadUrl存在无论是否传入mongoFileId均针对uploadUrl数据进行下载 
   * @return 
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
  public ResultApi downloadFile(HttpServletRequest request, HttpServletResponse response, 
      @RequestBody AttachParam attachParam) throws BusinessException{
    this.fileService.downloadFile(request, response, attachParam);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    return result;
  }
}

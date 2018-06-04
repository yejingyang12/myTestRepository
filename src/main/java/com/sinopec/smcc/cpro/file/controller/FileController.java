/**
* 2018. 
* @Title FileController.java
* @Package com.sinopec.smcc.cpro.file.controller
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午1:03:37
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.file.entity.AttachFile;
import com.sinopec.smcc.cpro.file.server.AttachFileService;

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
  public AttachFileService attachFileService;
//  @Autowired
//  public MongoService fileMongoServicee;
  
  
  @ResponseBody
  @RequestMapping(value = "/fileUpload", method = { RequestMethod.POST, RequestMethod.GET })
  public ResultApi fileUpload(HttpServletRequest request,String fkSystemId,String[] filePath,
      String fkAttachType,String[] fileName) throws BusinessException{
    String createUserName = (String) request.getSession().getAttribute("UserName");
    ArrayList itemIds = new ArrayList();
    for(int i=0;i<filePath.length;i++){
      if(!"".equals(filePath[i])){
        itemIds.add(filePath[i]);
      }
    }
    ArrayList names = new ArrayList();
    for(int j=0;j<filePath.length;j++){
      if(!"".equals(filePath[j])){
        itemIds.add(filePath[j]);
      }
    }
    int count=0;
    for(int i = 0;i<itemIds.size();i++){ 
     String filePaths = (String) itemIds.get(i);
     String filenStringName = (String) names.get(i);
     count += this.attachFileService.SaveMongoAttachFile(fkSystemId, filePaths, filenStringName, fkAttachType,
          createUserName);
    } 
//    AttachFile attachFile = attachFileService.SaveAttachFile(request, files, "type", fileName);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(count);
    return result;
  }
  
  @ResponseBody
  @RequestMapping(value = "/initFileUpload", method = { RequestMethod.POST, RequestMethod.GET })
  public ResultApi initFileUpload(HttpServletRequest request,
      @RequestParam("file") MultipartFile[] files) throws BusinessException{
    String fileName = "addfile";
    AttachFile attachFile = attachFileService.SaveAttachFile(request, files, "type", fileName);
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(attachFile);
    return result;
  }
  
  /**
   * 根据日期格式，获取不同的系统当前时间
   * @param timeFormat 日期格式 如：yyy-MM-dd HH:mm:ss
   * @return
   */
  public static String getDateTime(String timeFormat){
    Calendar calendar = Calendar.getInstance();
    java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(timeFormat);
    return df.format(calendar.getTime());
  }
  
  /**
   * 获取系统当前时间 <Timestamp>
   * 
   * @return 返回<Timestamp>类型对象
   */
  public static Timestamp getTimestampDateTime(){
    String sysTime = getDateTime("yyyy-MM-dd HH:mm:ss");
    Timestamp timestamp = Timestamp.valueOf(sysTime);
    return timestamp;
  }
  
  /**
   * @Descrption 跳转至修改单位信息，查询单位信息
   * @author dongxu
   * @date 2018年5月30日下午6:34:43
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test(HttpServletRequest request) throws BusinessException {

    return "/index";
  }
  

}

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.sinopec.smcc.cpro.file.entity.SysAttachFile;
import com.sinopec.smcc.cpro.file.entity.SysAttachFileParam;
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
  public ResultApi fileUpload(HttpServletRequest request,
      @RequestParam("file") MultipartFile[] files) throws BusinessException{
    String fileName = "addfile";
    attachFileService.SaveAttachFile(request, files, "sdweq", fileName);
    int n = 0;
    //判断file数组不能为空并且长度大于0  
/*    List<AttachFile> list= sysAttachFileParam.getFileList();
    AttachFile attachFile = new AttachFile();
    File file = new File("d:/temp", "addfile.txt");
    if  (!file .exists())     
    {     
        file .mkdir();   
    } 
    attachFile.setFiles(file);
    attachFile.setFileName("addfile");
    attachFile.setAttachTypes("report");
    list.add(attachFile);
    System.out.println(list.toString());
    if(!list.isEmpty()){  
        //循环获取file数组中得文件  
        for(int i = 0;i<list.size();i++){ 
            SysAttachFile vo = new SysAttachFile();
            //获取附件
//            File file = list.get(i).getFiles();
//            File file = new File("d:/temp", "addfile.txt");  
            try {  
                file.createNewFile(); // 创建文件  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            String attachName = list.get(i).getFileName();
            String attachTypes = list.get(i).getAttachTypes();
            InputStream inputStream = null;
            try {
              // 读取文件内容 (输入流)  
              inputStream = new FileInputStream(file);
            } catch (IOException e2) {
              e2.printStackTrace();
            }
            String fkSyssonId = null;
            try {
              //调用MongoDB接口保存文件到mongodb数据库并返回数据ID
              fkSyssonId = "je1hje1e92831w9";
//                  this.fileMongoServicee.uploadFile(inputStream, attachName);
            } catch (Exception e1) {
              e1.printStackTrace();
            } 
            vo.setAttachName(attachName);
            vo.setCreateTime(new Date());
            vo.setFkSystemId(sysAttachFileParam.getFkSystemId());
            vo.setAttachType(attachTypes);
            vo.setFkSyssonId(fkSyssonId);
            vo.setDeleteStatus(1);
            vo.setCreateUserName(sysAttachFileParam.getCreateUserName());
            
            try {
            //通过附件上传接口AttachFileService调用新增附件方法
//              n += this.attachFileService.SaveAttachFile(vo);
            } catch (Exception e) {
              e.printStackTrace();
            }
             
        }  
    } 
    */
    //通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(n);
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

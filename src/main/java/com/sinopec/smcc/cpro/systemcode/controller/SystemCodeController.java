/**
* 2018. 
* @Title SysTemCodeController.java
* @Package com.sinopec.smcc.cpro.systemcode.controller
* @Description: TODO:
* @author zhouyu
* @date 2018年6月3日下午7:59:43
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.server.SystemCodeService;

/**
 * @Title SysTemCodeController.java
 * @Package com.sinopec.smcc.cpro.systemcode.controller
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午7:59:43
 * @version V1.0
 */
@Controller
@RequestMapping("/systemCode")
public class SystemCodeController {
  
  @Autowired
  public SystemCodeService systemCodeService;
  
  /**
   * @Descrption 下拉key是code；value是中文注释
   * @author zhouyu
   * @date 2018年6月4日上午11:32:26
   * @param req {"fkCodeType":"10","deleteStatus":"1"}
   * @param systemCodeParam
   * @return {"list":[ {"value":"是", "key":"1"}, {"value":"否", "key":"2"}]}
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value="/singleSelect", method = RequestMethod.POST)
  public String getSingleSelect(HttpServletRequest req,@RequestBody SystemCodeParam systemCodeParam) throws BusinessException{
    
    String json = this.systemCodeService.getSingleSelect(systemCodeParam);
    
    return json;
  }
  
  /**
   * @Descrption 下拉key是中文注释；value是中文注释
   * @author zhouyu
   * @date 2018年6月4日上午11:36:28
   * @param req {"fkCodeType":"12","deleteStatus":"1"}
   * @param systemCodeParam
   * @return {"list":[ {"value":" 安全专用产品", "key":" 安全专用产品"}, {"value":"网络产品", "key":"网络产品"}, {"value":"操作系统", "key":"操作系统"}, {"value":"数据库", "key":"数据库"}, {"value":"服务器", "key":"服务器"}, {"value":"其它", "key":"其它"}]}
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value="/singleSelectName", method = RequestMethod.POST)
  public String getSingleSelectName(HttpServletRequest req,@RequestBody SystemCodeParam systemCodeParam) throws BusinessException{
    String json = this.systemCodeService.getConstantByName(systemCodeParam);
    return json;
  }
  
  /**
   * @Descrption 多选下拉树
   * @author zhouyu
   * @date 2018年6月4日下午2:00:08
   * @param req {"fkCodeType":"11","deleteStatus":"1"}
   * @param systemCodeParam 
   * @return {"treeNodes":[ {"id":"1", "parentId":"0","name":"业务信息"}, {"id":"102", "parentId":"1","name":"Ⅱ级"}, {"id":"202", "parentId":"2","name":"Ⅱ级"}, {"id":"302", "parentId":"3","name":"Ⅱ级"}, {"id":"10101", "parentId":"101","name":"仅对公民、法人和其他组织的合法权益造成损害"}, {"id":"20101", "parentId":"201","name":"仅对公民、法人和其他组织的合法权益造成损害"}, {"id":"20401", "parentId":"204","name":"对社会秩序和公共利益造成特别严重损害"}, {"id":"10202", "parentId":"102","name":"对社会秩序和公共利益造成损害"}, {"id":"10401", "parentId":"104","name":"对社会秩序和公共利益造成特别严重损害"}, {"id":"20202", "parentId":"202","name":"对社会秩序和公共利益造成损害"}, {"id":"10201", "parentId":"102","name":"对公民、法人和其他组织的合法权益造成严重损害"}, {"id":"20201", "parentId":"202","name":"对公民、法人和其他组织的合法权益造成严重损害"}, {"id":"10301", "parentId":"103","name":"对社会秩序和公共利益造成严重损害"}, {"id":"20301", "parentId":"203","name":"对社会秩序和公共利益造成严重损害"}, {"id":"10302", "parentId":"103","name":"对国家安全造成严重损害"}, {"id":"10402", "parentId":"104","name":"对国家安全造成严重损害"}, {"id":"20302", "parentId":"203","name":"对国家安全造成严重损害"}, {"id":"20402", "parentId":"204","name":"对国家安全造成严重损害"}, {"id":"10303", "parentId":"103","name":"对公民、法人和其他组织的合法权益造成特别严重损害"}, {"id":"20303", "parentId":"203","name":"对公民、法人和其他组织的合法权益造成特别严重损害"}, {"id":"104", "parentId":"1","name":"Ⅳ级"}, {"id":"204", "parentId":"2","name":"Ⅳ级"}, {"id":"304", "parentId":"3","name":"Ⅳ级"}, {"id":"10501", "parentId":"105","name":"对国家安全造成特别严重损害"}, {"id":"20501", "parentId":"205","name":"对国家安全造成特别严重损害"}, {"id":"103", "parentId":"1","name":"Ⅲ级"}, {"id":"203", "parentId":"2","name":"Ⅲ级"}, {"id":"303", "parentId":"3","name":"Ⅲ级"}, {"id":"105", "parentId":"1","name":"Ⅴ级"}, {"id":"205", "parentId":"2","name":"Ⅴ级"}, {"id":"305", "parentId":"3","name":"Ⅴ级"}, {"id":"2", "parentId":"0","name":"系统服务"}, {"id":"3", "parentId":"0","name":"信息系统安全保护等级"}, {"id":"101", "parentId":"1","name":"Ⅰ级"}, {"id":"201", "parentId":"2","name":"Ⅰ级"}, {"id":"301", "parentId":"3","name":"Ⅰ级"}]}
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value = "/getMultiTree" ,method = RequestMethod.POST)
  public String getMultiTree(HttpServletRequest req,@RequestBody SystemCodeParam systemCodeParam) throws BusinessException{
    
    String json = this.systemCodeService.getConstantTreeByName(systemCodeParam);
    
    return json;
  }
  
  /**
   * @Descrption 根据参数类型及参数值返回相应codeName
   * @author zhouyu
   * @date 2018年6月4日下午2:00:08
   * @param req {"fkCodeType":"13","deleteStatus":"1","systemCode":"2"}
   * @param systemCodeParam 
   * @return 与本行业其他单位系统连接
   * @throws BusinessException
   */
  @ResponseBody
  @RequestMapping(value = "/getConstantName" ,method = RequestMethod.POST)
  public String getConstantName(HttpServletRequest req,
      @RequestBody SystemCodeParam systemCodeParam) throws BusinessException{
    
    String json = this.systemCodeService.getConstantName(systemCodeParam.getFkCodeType(),
        systemCodeParam.getDeleteStatus(),systemCodeParam.getSystemCode());
    
    return json;
  }
  
}

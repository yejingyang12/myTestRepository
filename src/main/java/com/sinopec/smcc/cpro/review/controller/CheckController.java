/**
 * 2018. 
 * @Title CheckController.java
 * @Package com.sinopec.smcc.cpro.review.controller
 * @Description: TODO:审核管理controller
 * @author zhouyu
 * @date 2018年5月25日下午1:25:15
 * @version V1.0
 */
package com.sinopec.smcc.cpro.review.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckNodeParam;
import com.sinopec.smcc.cpro.review.entity.CheckNodeListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;

@Controller
@RequestMapping("/checkController")
public class CheckController {

  @Autowired
  private CheckService checkServiceImpl;
  
  /**
   * 方法描述: 审核管理列表
   * 参数:    @param param
   * 参数:    @throws BusinessException    
   * 返回:    void     
   * 创建人:  周瑜     
   * 创建时间:2018年5月26日 上午10:43:53
   * @return 
   */
  @RequestMapping(value = "/querylist", method = RequestMethod.POST)
  @ResponseBody
  public ResultApi queryCheckList(HttpRequest request,@RequestBody CheckParam checkParam,
      HttpServletResponse response) throws BusinessException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    // 调用service实体获得方法，CheckListResult填写返回的参数
    PageInfo<CheckListResult> pageInfo = this.checkServiceImpl.queryCheckList(checkParam);
    // 通过resultApi实体组成返回参数
    ResultApi resultApi = new ResultApi(EnumResult.SUCCESS);
    // 当前页码
    resultApi.setCurrentPage(pageInfo.getPageNum());
    // 每页数据数量
    resultApi.setTotalPages(pageInfo.getPageSize());
    // 总页数
    resultApi.setTotalPages(pageInfo.getPages());
    // 总条数
    resultApi.setTotal(pageInfo.getTotal());
    // 响应的数据
    resultApi.setData(pageInfo.getList());

    return resultApi;
  }
  
  /**
   * @Descrption 新增审核信息
   * @author zhouyu
   * @date 2018年5月25日下午7:30:43
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveCheck", method = RequestMethod.GET)
  @ResponseBody
  public ResultApi saveCheck(HttpRequest request,
      CheckParam checkParam) throws BusinessException {
    String checkId = this.checkServiceImpl.saveCheck(checkParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(checkId);
    return result;
  }
  
  /**
   * 方法描述: 保存审核记录
   * 参数:    @param param
   * 参数:    @throws BusinessException    
   * 返回:    void     
   * 创建人:  周瑜     
   * 创建时间:2018年5月26日 上午10:43:53
   * @return 
   */
  @ResponseBody
  @RequestMapping(value = "/checkNodeSave", method = RequestMethod.GET)
  public ResultApi checkNodeSave(CheckNodeListResult checkNodeListResult, String fkExaminStatus,
      String fkbusinessNode, String checkId, String fkSystemId) throws BusinessException {

    String checkNodeId = this.checkServiceImpl.checkNodeSave(checkNodeListResult, fkExaminStatus,
        fkbusinessNode, checkId, fkSystemId);
    // 返回实体参数
    ResultApi resultApi = new ResultApi(EnumResult.SUCCESS);
    // 返回Insert后实体ID
    resultApi.setData(checkNodeId);
    //
    return resultApi;
  }

  
  /**
   * 方法描述: 审核节点记录列表
   * 参数:    @param param
   * 参数:    @throws BusinessException    
   * 返回:    void     
   * 创建人:  周瑜     
   * 创建时间:2018年5月26日 上午10:43:53
   * @return 
   */
  @ResponseBody
  @RequestMapping(value = "/querycheckNodeList", method = RequestMethod.GET)
  public ResultApi querycheckNodeList(HttpRequest request,
      CheckNodeParam checkNodeParam) throws BusinessException {
    // 调用service实体获得方法，CheckNodeListResult填写返回的参数
    PageInfo<CheckNodeListResult> pageInfo = this.checkServiceImpl
        .queryCheckNodeList(checkNodeParam);
    // 通过resultApi实体组成返回参数
    ResultApi resultApi = new ResultApi(EnumResult.SUCCESS);
    // 当前页码
    resultApi.setCurrentPage(pageInfo.getPageNum());
    // 每页数据数量
    resultApi.setTotalPages(pageInfo.getPageSize());
    // 总页数
    resultApi.setTotalPages(pageInfo.getPages());
    // 总条数
    resultApi.setTotal(pageInfo.getTotal());
    // 响应的数据
    resultApi.setData(pageInfo.getList());

    return resultApi;
  }
  
  /**
   * 方法描述: 审核节点记录列表
   * 参数:    @param param
   * 参数:    @throws BusinessException    
   * 返回:    void     
   * 创建人:  周瑜     
   * 创建时间:2018年5月26日 上午10:43:53
   * @return 
   */
  @ResponseBody
  @RequestMapping(value = "/queryNodeAllList", method = RequestMethod.GET)
  public ResultApi queryNodeAllList(HttpRequest request,
      CheckNodeParam checkNodeParam) throws BusinessException {
    // 调用service实体获得方法，CheckNodeAllList填写返回的参数
    PageInfo<CheckNodeListResult> pageInfo = this.checkServiceImpl
        .queryNodeAllList(checkNodeParam);
    // 通过resultApi实体组成返回参数
    ResultApi resultApi = new ResultApi(EnumResult.SUCCESS);
    // 当前页码
    resultApi.setCurrentPage(pageInfo.getPageNum());
    // 每页数据数量
    resultApi.setTotalPages(pageInfo.getPageSize());
    // 总页数
    resultApi.setTotalPages(pageInfo.getPages());
    // 总条数
    resultApi.setTotal(pageInfo.getTotal());
    // 响应的数据
    resultApi.setData(pageInfo.getList());

    return resultApi;
  }

}

/**
 * 2018. 
 * @Title CompanyController.java
 * @Package com.sinopec.smcc.cpro.company.controller
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:06:01
 * @version V1.0
 */
package com.sinopec.smcc.cpro.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.consts.RequestClientEnum;
import com.sinopec.smcc.base.consts.SmccModuleEnum;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.log.RequestLog;
import com.sinopec.smcc.base.result.ResultApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;
import com.sinopec.smcc.cpro.company.server.CompanyService;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.system.server.SystemService;


@Controller
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CompanyService companyServiceImpl;
  
  @Autowired
  private SystemService systemServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;
  /**
   * @Descrption 单位系统信息列表
   * @author dongxu
   * @date 2018年5月25日下午1:39:10
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryCompanyList", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi queryCompanyList(HttpServletRequest request, 
      @RequestBody CompanyParam companyParam) throws BusinessException {
    // 调用service实体，获得
    PageInfo<CompanyListResult> page = this.companyServiceImpl.queryCompanyList(companyParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setCurrentPage(page.getPageNum());
    result.setPagesize(page.getPageSize());
    result.setData(page.getList());
    result.setTotal(page.getTotal());
    result.setTotalPages(page.getPages());
    return result;
  }

  /**
   * @Descrption 添加或修改单位信息
   * @author dongxu
   * @date 2018年5月25日下午7:30:43
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi saveCompany(HttpServletRequest request,
      @RequestBody CompanyParam companyParam) throws BusinessException {
    String userName = this.nodeServiceImpl.getUserNameFromRequest(request);
    String companyId = this.companyServiceImpl.saveCompany(userName, companyParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(companyId);
    return result;
  }

  /**
   * @Descrption 删除单位信息
   * @author dongxu
   * @date 2018年5月25日下午7:30:43
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro,requestClient=RequestClientEnum.BROWSER)
  @ResponseBody
  public ResultApi deleteCompany(HttpServletRequest request,
      @RequestBody CompanyParam companyParam) throws BusinessException {
    List<CompanyListResult> companys = this.companyServiceImpl.delelteCompany(companyParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(companys);
    return result;
  }
  
  /**
   * @Descrption 查询单位信息详情
   * @author dongxu
   * @date 2018年5月27日下午12:07:06
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryDetailsCompany", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi queryDetailsCompany(HttpServletRequest request,
      @RequestBody CompanyParam companyParam) throws BusinessException {
    CompanyResult companyResult = this.companyServiceImpl.queryDetailsCompany(companyParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(companyResult);
    return result;
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
  @RequestMapping(value = "/queryEditCompany", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi queryEditCompany(HttpServletRequest request,
      @RequestBody CompanyParam companyParam) throws BusinessException {
    CompanyResult companyResult = this.companyServiceImpl.queryEditCompany(companyParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(companyResult);
    return result;
  }
  
  /**
   * @Descrption 根据code获取单位信息
   * @author Aran
   * @date 2018年6月9日下午12:07:06
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryCompanyByCode", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi queryCompanyByCode(HttpServletRequest request,
      @RequestBody CompanyParam companyParam) throws BusinessException {
    CompanyResult companyResult = this.companyServiceImpl.queryCompanyByCode(companyParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(companyResult);
    return result;
  }
  
  /**
   * @Descrption 高级搜索获取所有单位名称
   * @author dongxu
   * @date 2018年6月11日下午12:00:23
   * @param request
   * @param mainParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryCompanyName", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi queryCompanyName(HttpServletRequest request,
      @RequestBody CompanyParam companyParam) 
      throws BusinessException {
    List<CompanyListResult> mainList = this.companyServiceImpl.queryCompanyName(companyParam);
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(mainList);
    return result;
  }
  /**
   * @Descrption 单位信息名称列表（带板块级联）
   * @author dongxu
   * @date 2018年5月25日下午1:39:10
   * @param request
   * @param companyParam
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "/queryCompanyListByName", method = RequestMethod.POST)
  @RequestLog(module=SmccModuleEnum.cpro)
  @ResponseBody
  public ResultApi queryCompanyListByName(HttpServletRequest request, 
      @RequestBody CompanyParam companyParam) throws BusinessException {
    // 调用service实体，获得
    List<OrganizationApiCascaderResult> list = this.companyServiceImpl.
        queryCompanyListByName(companyParam);
    // 通过resultApi实体组成返回参数
    ResultApi result = new ResultApi(EnumResult.SUCCESS);
    result.setData(list);
    return result;
  }
}

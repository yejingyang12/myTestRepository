/**
* 2018. 
* @Title CompanyService.java
* @Package com.sinopec.smcc.cpro.company.server
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:10:29
* @version V1.0
*/
package com.sinopec.smcc.cpro.company.server;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;

/**
 * @Title CompanyService.java
 * @Package com.sinopec.smcc.cpro.company.server
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:10:29
 * @version V1.0
 */
public interface CompanyService {

  /**
   * @Descrption 响应单位列表数据
   * @author dongxu
   * @date 2018年5月25日下午1:44:47
   * @param companyParam
   * @return
   * @throws BusinessException 
   */
  PageInfo<CompanyListResult> queryCompanyList(CompanyParam companyParam) throws BusinessException;
  
  /**
   * @Descrption 添加或修改单位信息
   * @author dongxu
   * @date 2018年5月25日下午7:15:31
   * @param companyParam
   * @return
   * @throws BusinessException 
   */
  String saveCompany(String userName,CompanyParam companyParam) throws BusinessException;
  
  /**
   * @Descrption 删除单位信息
   * @author dongxu
   * @date 2018年5月26日下午3:15:15
   * @param companyIds
   * @return
   * @throws BusinessException
   */
  List<CompanyListResult> delelteCompany(CompanyParam companyParam) throws BusinessException;

  /**
   * @Descrption 通过CompanyId查询单位详情
   * @author dongxu
   * @date 2018年5月27日下午12:05:26
   * @param companyParam
   */
  CompanyResult queryDetailsCompany(CompanyParam companyParam);

  /**
   * @Descrption 跳转至修改单位信息，查询单位信息
   * @author dongxu
   * @date 2018年5月30日下午6:35:37
   * @param companyParam
   * @return
   */
  CompanyResult queryEditCompany(CompanyParam companyParam);

  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月9日下午3:45:58
   * @param companyParam
   * @return
   */
  CompanyResult queryCompanyByCode(CompanyParam companyParam);
  
  /**
   * @Descrption 高级搜索获取单位名称
   * @author dongxu
   * @date 2018年6月11日上午11:30:51
   * @param response
   * @param mainParam
   */
  List<CompanyListResult> queryCompanyName(CompanyParam companyParam) throws BusinessException;

  /**
   * @Descrption 返回单位列表信息（带板块）
   * @author Aran
   * @date 2018年6月29日上午9:25:17
   * @param companyParam
   * @return
   */
  List<OrganizationApiCascaderResult> queryCompanyListByName(CompanyParam companyParam); 

}

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

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
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
  String saveCompany(CompanyParam companyParam) throws BusinessException;
  
  /**
   * @Descrption 删除单位信息
   * @author dongxu
   * @date 2018年5月26日下午3:15:15
   * @param companyIds
   * @return
   * @throws BusinessException
   */
  void delelteCompany(CompanyParam companyParam) throws BusinessException;

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

}

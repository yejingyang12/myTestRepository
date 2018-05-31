/**
* 2018. 
* @Title CompanyMapper.java
* @Package com.sinopec.smcc.cpro.company.mapper
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日上午11:14:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.company.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.company.entity.CompanyResult;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;

/**
 * @Title CompanyMapper.java
 * @Package com.sinopec.smcc.cpro.company.mapper
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:14:41
 * @version V1.0
 */
public interface CompanyMapper {

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月25日下午2:02:33
   * @param companyParam
   * @return
   */
  List<CompanyListResult> selectAllByCompanyParam(CompanyParam companyParam);
  
  /**
   * 
   * @Descrption
   * @author dongxu
   * @date 2018年5月25日下午7:13:53
   * @param companyParam
   * @return
   */
  void insertCompanyByCompanyId(CompanyParam companyParam);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月26日下午8:13:06
   * @param companyParam
   */
  void updateCompanyByCompanyIds(CompanyParam companyParam);


  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月26日下午8:38:51
   * @param companyCode
   * @return
   */
  String selectCompanyByCompanyCode(String companyCode);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月27日下午12:08:37
   * @param companyParam
   */
  CompanyResult selectSingleCompanyByCompanyId(CompanyParam companyParam);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月30日下午6:38:03
   * @param companyParam
   * @return
   */
  CompanyResult selectCompanyByCompanyId(CompanyParam companyParam);

}

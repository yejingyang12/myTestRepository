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

import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;

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


  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月9日下午3:52:58
   * @param companyParam
   * @return
   */
  CompanyResult selectCompanyInfoByCompanyCode(CompanyParam companyParam);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年6月11日下午7:37:49
   * @param companyParam
   * @return
   */
  List<CompanyListResult> selectCompanyName(CompanyParam companyParam);

  /**
   * @Descrption  根据companyParam查询有系统的单位列表
   * @author yejingyang
   * @date 2018年7月27日上午10:52:38
   * @param companyParam
   * @return
   */
  List<CompanyListResult> selectAllCompanyExistSysByParam(CompanyParam companyParam);

}

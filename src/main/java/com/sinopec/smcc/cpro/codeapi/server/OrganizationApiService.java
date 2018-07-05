/**
 * @Title OrganizationApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:38:35
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiParam;

/**
 * @Title OrganizationApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:38:35
 * @version V1.0
 */
public interface OrganizationApiService {

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月9日下午1:40:00
   * @param organizationParam
   * @return
   */
  List<OrganizationApiCascaderResult> queryOrganizationApi(
      OrganizationApiParam organizationApiParam) throws BusinessException ;

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月9日下午2:43:48
   * @param organizationApiParam
   * @return
   */
  List<OrganizationApi> queryOrgForKeyOrganizationCode(
      OrganizationApiParam organizationApiParam) throws BusinessException ;

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月9日下午2:43:56
   * @param organizationApiParam
   * @return
   */
  List<OrganizationApi> queryOrgForKeyOrganizationName(
      OrganizationApiParam organizationApiParam) throws BusinessException ;

}

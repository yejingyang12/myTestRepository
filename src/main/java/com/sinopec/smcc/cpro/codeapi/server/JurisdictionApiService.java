/**
 * @Title JurisdictionApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:20:52
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server;

import java.util.List;

import com.sinopec.smcc.base.controller.TreeOrganizationDTO;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

/**
 * @Title JurisdictionApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:20:52
 * @version V1.0
 */
public interface JurisdictionApiService {

  JurisdictionDataResult queryDataJurisdictionApi1();
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月20日下午3:22:33
   * @param organizationApiParam
   * @return
   */
  JurisdictionDataResult queryDataJurisdictionApi();
  /**
   * @Descrption  查询指定用户的数据权限
   * @author yejingyang
   * @date 2018年9月21日上午10:43:10
   * @param userDTO 指定的用户
   * @return
   */
  JurisdictionDataResult queryDataJurisdictionApi(UserDTO userDTO);
  
  /**
   * @Descrption 获取单位Code
   * @author dongxu
   * @date 2018年7月18日下午8:18:41
   * @return
   */
  String getCompanyCode();
  
  /**
   * @Descrption 获取树型结构图数据
   * @author dongxu
   * @date 2018年9月26日下午4:07:47
   * @return
   */
  List<TreeOrganizationDTO> getOrgTree();

}

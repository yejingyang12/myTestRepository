package com.sinopec.smcc.cpro.codeapi.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "${cpro.api.url}", name = "secp-api-service")
public interface SystemApiClient {
	@RequestMapping(value = "/restful/SystemInfo/findSystemInfoBySpOrgNumber", method = RequestMethod.GET)
	String querySystemList(@RequestParam("spOrgNumber") String spOrgNumber);

  /**
   * @Descrption
   * @author Aran
   * @date 2018年7月25日下午6:24:29
   * @return
   */
  @RequestMapping(value = "/restful/SystemInfo/findSystemInfo", method = RequestMethod.GET)
  String querySystemList();
}

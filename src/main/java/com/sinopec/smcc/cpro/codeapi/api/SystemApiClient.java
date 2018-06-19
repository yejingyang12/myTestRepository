package com.sinopec.smcc.cpro.codeapi.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "${cpro.api.url}", name = "secp-api-service")
public interface SystemApiClient {

	@RequestMapping(value = "/restful/SystemInfo/findSystemInfo", method = RequestMethod.GET)
	String querySystemList();

}

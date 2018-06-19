
package com.sinopec.smcc.cpro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinopec.smcc.common.consts.SmccConsts;

/**
 * 项目名称:smcc-cpro
 * 类描述:  安全检测模块入口类
 * 创建人:  ziv
 * 创建时间: 2018/4/11 10:25
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableFeignClients("com.sinopec.smcc")
@MapperScan(SmccConsts.BASH_PACKAGE + ".**.mapper")
@ComponentScan(value = "com.sinopec.smcc", excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.sinopec.smcc.common.config.AuthFilterConfig")})
@Controller
public class ClassProtectionApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClassProtectionApplication.class, args);
	}

	@RequestMapping("/")
	public String mainPage() {
		return "page/indexPage";
	}

	/**
	 * 方法描述: 为统一身份认证添加的配置
	 *
	 * @author ziv
	 * @date 2018/3/21 09:18
	 */
	@Bean
	public ServletContextInitializer initializer() {
		return servletContext -> servletContext.setInitParameter("spSimpleConfigFile", "classpath:/conf/sp-simple-config.properties");
	}

}


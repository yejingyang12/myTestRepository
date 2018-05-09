package com.sinopec.smcc.cpro;

import com.sinopec.smcc.common.consts.SmccConsts;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目名称:smcc-cpro
 * 类描述:  等级保护模块入口类
 * 创建人:  张黄江
 * 创建时间: 2018/5/9 11:00
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
@SpringBootApplication
@EnableFeignClients("com.sinopec.smcc")
@MapperScan(SmccConsts.BASH_PACKAGE + ".**.mapper")
@ComponentScan(value = "com.sinopec.smcc")
@Controller
public class ClassProtectionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClassProtectionApplication.class, args);
	}

	@RequestMapping("/")
	public String mainPage() {
		return "index";
	}

	/**
	 * 方法描述: 为统一身份认证添加的配置
	 *
	 * @author 张黄江
	 * @date 2018/5/9 11:00
	 */
	@Bean
	public ServletContextInitializer initializer() {
		return servletContext -> servletContext.setInitParameter("spSimpleConfigFile", "classpath:/conf/sp-simple-config.properties");
	}

}

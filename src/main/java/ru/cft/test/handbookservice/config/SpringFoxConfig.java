package ru.cft.test.handbookservice.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Configuration
@EnableSwagger2WebMvc
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("ru.cft.test.handbookservice.controller"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						Lists.newArrayList(new ResponseMessageBuilder()
										.code(500)
										.message("Internal Server Error")
										.build(),
								new ResponseMessageBuilder()
										.code(403)
										.message("Forbidden")
										.build(),
								new ResponseMessageBuilder()
										.code(412)
										.message("Precondition Failed")
										.build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo ("HANDBOOK SERVICE API FOR CFT",
				"Some custom description of API.",
				"API TOS",
				"Terms of service",
				new Contact("Lyubov Bochkareva", "", "bochkareva.lyubov.v@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}
}


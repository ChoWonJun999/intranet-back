package com.odpia.intranet.config.web.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI().info(new Info().title("ODPIA Intranet API")
		        .description("내부 인트라넷 서비스 API 문서")
		        .version("v1.0.0")
		        .contact(new Contact().name("개발팀").email("dev@odpia.com"))
		        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")));
	}

	// 필요 시 그룹 분리 (예: /api/** 만 노출)
	// @Bean
	// public GroupedOpenApi apiGroup() {
	// 	return GroupedOpenApi.builder().group("api").pathsToMatch("/api/**").build();
	// }

	// 관리자 API 따로 묶기 예시
	// @Bean
	// public GroupedOpenApi adminGroup() {
	// 	return GroupedOpenApi.builder().group("admin").pathsToMatch("/admin/**").build();
	// }
}

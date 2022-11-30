package co.com.telefonica.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * NO BORRAR
 *
 * Clase encargada de incluir SWAGGER_2.0 en la aplicación para autodescripción
 * del API.
 *
 * @version 1.1.0
 * @author COEArquitectura@telefonica.com
 * @since 27/12/2021
 */
@Component
@Configuration
public class TelcoSpringFoxConfig {

	private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(List.of("application/json"));

	@Value("${controller.properties.base-path}")
	private String uriBasePattern;

	@Autowired
	private TelcoSwaggerProperties telcoSwaggerProperties;

	@Bean
	public Docket api() {
		String regexUri = "/" + this.uriBasePattern + ".*";

		return new Docket(DocumentationType.SWAGGER_2)
				.produces(DEFAULT_PRODUCES_CONSUMES)
				.consumes(DEFAULT_PRODUCES_CONSUMES)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex(regexUri))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				this.telcoSwaggerProperties.getProjectName(),
				this.telcoSwaggerProperties.getProjectShortDescription(),
				this.telcoSwaggerProperties.getProjectTosMsg(),
				this.telcoSwaggerProperties.getProjectTosLink(),
				new Contact(
						this.telcoSwaggerProperties.getDeveloperName(),
						this.telcoSwaggerProperties.getOrganizationUrl(),
						this.telcoSwaggerProperties.getDeveloperMail()),
				this.telcoSwaggerProperties.getProjectLicenceMsg(),
				this.telcoSwaggerProperties.getProjectLicenceLink(),
				Collections.emptyList()
		);
	}
}

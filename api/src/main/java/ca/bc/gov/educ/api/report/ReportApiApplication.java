package ca.bc.gov.educ.api.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class ReportApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Configuration
	static
	class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	  /**
	   * Instantiates a new Web security configuration.
	   * This makes sure that security context is propagated to async threads as well.
	   */
	  public WebSecurityConfiguration() {
	    super();
	    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	  }
	  @Override
	  public void configure(WebSecurity web) {
		  web.ignoring().antMatchers("/api/v1/api-docs-ui.html",
				  "/api/v1/swagger-ui/**", "/api/v1/api-docs/**",
				  "/actuator/health","/actuator/prometheus", "/health");
	  }
	}
}

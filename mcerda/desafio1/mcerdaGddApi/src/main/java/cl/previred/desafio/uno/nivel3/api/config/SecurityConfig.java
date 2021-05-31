package cl.previred.desafio.uno.nivel3.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers(
            "/v2/api-docs", 
            "/swagger-resources/**",  
            "/swagger-ui.html", 
            "/webjars/**" ,
             /*Probably not needed*/ "/swagger.json")
        .permitAll();
	}

}

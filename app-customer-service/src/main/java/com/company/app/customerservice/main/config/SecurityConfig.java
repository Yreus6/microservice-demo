package com.company.app.customerservice.main.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .oauth2ResourceServer()
                    .jwt();
        // @formatter:on
    
        Okta.configureResourceServer401ResponseBody(http);
    }
}

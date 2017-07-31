package com.qa.consulting.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by Matt on 24/07/2017.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("resource");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/questions/**").permitAll()
                .antMatchers(HttpMethod.GET, "/question/**/author").permitAll()
                .antMatchers(HttpMethod.GET, "/question/**/answers").permitAll()
                .antMatchers(HttpMethod.PATCH, "/question/**/updateViewedCount").permitAll()
                .antMatchers(HttpMethod.GET, "/answers/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .anyRequest().authenticated()
        ;
    }
}

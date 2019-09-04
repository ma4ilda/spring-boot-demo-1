package com.luv2code.springboot.demo.mycoolapp.config;

import com.luv2code.springboot.demo.mycoolapp.service.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MongoUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// never create a session and re-authenticate on every request
                .and()
                .formLogin().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/error", "/actuator/**").permitAll() //authorize every request except error page 'Unauthorized'
                .anyRequest().authenticated();
    }
}

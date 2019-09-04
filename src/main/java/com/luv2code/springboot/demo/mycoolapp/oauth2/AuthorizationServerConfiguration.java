package com.luv2code.springboot.demo.mycoolapp.oauth2;

import com.luv2code.springboot.demo.mycoolapp.service.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    MongoUserService userService;

    private AuthenticationManager authenticationManager;
    // AuthenticationManager is required for the password grant type in Spring Security OAuth2
    public AuthorizationServerConfiguration(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // In spring security 5 we can not store password in plain text
        PasswordEncoder
                encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        clients.inMemory()
                .withClient("appgrid")
                .secret(encoder.encode("appgrid_secret"))
                .accessTokenValiditySeconds(600)
                .scopes("global")
                .authorizedGrantTypes("password", "refresh_token")
                .autoApprove(true);
    }

}
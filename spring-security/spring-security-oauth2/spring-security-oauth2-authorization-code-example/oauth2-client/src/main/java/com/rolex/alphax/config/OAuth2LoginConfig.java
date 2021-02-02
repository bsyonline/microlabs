package com.rolex.alphax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class OAuth2LoginConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a
                        .antMatchers("/**", "/", "/error", "/webjars/**").permitAll()
                        //.anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2Login()
                .and()
                .logout(l -> l
                        .logoutSuccessUrl("/").permitAll()
                )
                .csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                );

//        http.requestMatchers()
//                .antMatchers("/oauth/**","/login","/login-error")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated()
//                .and()
//                .formLogin().loginPage( "/login" ).failureUrl( "/login-error" );
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("alphax")
            .clientId("1a6642b4-622d-11eb-a707-00059a3c7a00")
            .clientSecret("123")
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUriTemplate("{baseUrl}/oauth/code/{registrationId}")
            .scope("read", "write")
            .authorizationUri("http://localhost:8082/oauth/authorize")
            .tokenUri("http://localhost:8082/oauth/token")
            //.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            //.userNameAttributeName(IdTokenClaimNames.SUB)
            //.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .clientName("AlphaX")
            .build();
    }
}

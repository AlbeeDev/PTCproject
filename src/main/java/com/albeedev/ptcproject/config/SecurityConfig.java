package com.albeedev.ptcproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/home").permitAll();
                    auth.requestMatchers("/access/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .oauth2Login(auth->
                        auth.defaultSuccessUrl("/access/profile", true));
        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.discordClientRegistration());
    }

    @Value("${spring.security.oauth2.client.registration.discord.client-id}")
    String discordClientId;

    @Value("${spring.security.oauth2.client.registration.discord.client-secret}")
    String discordClientSecret;

    @Value("${spring.security.oauth2.client.provider.discord.authorization-uri}")
    private String discordAuthorizationUri;

    @Value("${spring.security.oauth2.client.provider.discord.token-uri}")
    private String discordTokenUri;

    @Value("${spring.security.oauth2.client.provider.discord.user-info-uri}")
    private String discordUserInfoUri;

    @Value("${spring.security.oauth2.client.registration.discord.redirect-uri}")
    private String discordRedirectUri;

    private ClientRegistration discordClientRegistration() {
        return ClientRegistration.withRegistrationId("discord")
                .clientId(discordClientId)
                .clientSecret(discordClientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(discordRedirectUri)
                .scope("identify")
                .authorizationUri(discordAuthorizationUri)
                .tokenUri(discordTokenUri)
                .userInfoUri(discordUserInfoUri)
                .userNameAttributeName("id")
                .clientName("Discord")
                .build();
    }


}

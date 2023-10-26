package com.lunaticdevs.urlific.config.security;

import com.lunaticdevs.urlific.config.credential.GoogleCredential;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
import static org.springframework.security.oauth2.core.oidc.IdTokenClaimNames.SUB;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final GoogleCredential googleCredential;
    private final CustomSuccessHandler customSuccessHandler;
    @Value("${secret.key}")
    private String SECRET_KEY;

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/image/**", "/js/**",
                                "/register", "/link/**", "/url-checker")
                        .permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/dashboard")
                        .permitAll())
                .logout(logoutConfigurer -> logoutConfigurer
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .rememberMe(rememberMeConfigurer -> rememberMeConfigurer
                        .key(SECRET_KEY)
                        .tokenValiditySeconds(86400))
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied"))
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .successHandler(customSuccessHandler))
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId(googleCredential.clientId())
                .clientSecret(googleCredential.clientSecret())
                .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                .authorizationGrantType(AUTHORIZATION_CODE)
                .redirectUri(googleCredential.redirectUri())
                .scope(googleCredential.scope())
                .authorizationUri(googleCredential.authorizationUri())
                .tokenUri(googleCredential.tokenUri())
                .userInfoUri(googleCredential.userInfoUri())
                .userNameAttributeName(SUB)
                .jwkSetUri(googleCredential.jwkSetUri())
                .clientName("Google")
                .build();
    }
}

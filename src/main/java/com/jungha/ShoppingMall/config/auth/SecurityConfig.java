package com.jungha.ShoppingMall.config.auth;

import com.jungha.ShoppingMall.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
            new AntPathRequestMatcher("/"),
//            "/css/**", "/images/**", "/js/**", "/h2-console/**"
            new AntPathRequestMatcher("/css/**"),
            new AntPathRequestMatcher("/images/**"),
            new AntPathRequestMatcher("/js/**"),
//            new AntPathRequestMatcher("/api/v1/**"),
             new AntPathRequestMatcher("/h2-console/**"),
    };

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf().disable().
                headers().frameOptions().disable().and()
                .authorizeHttpRequests().requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers("/api/v1/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                )
//                .headers(headers -> headers.frameOptions().disable())
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
//        return http.build();
//    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().
//                headers().frameOptions().disable().and()
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//                .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//    }

//    public void configure(WebSecurity web)throws Exception{
//        web.ignoring().requestMatchers("/h2-console/**");

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

}

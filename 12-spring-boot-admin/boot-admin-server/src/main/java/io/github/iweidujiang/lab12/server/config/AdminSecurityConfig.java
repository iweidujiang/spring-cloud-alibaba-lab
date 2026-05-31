package io.github.iweidujiang.lab12.server.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/**
 * Spring Boot Admin Server 安全配置。
 *
 * @author 苏渡苇
 */
@Configuration
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServer;

    private final SecurityProperties security;

    /**
     * 构造安全配置。
     *
     * @param adminServer Admin Server 属性
     * @param security    Spring Security 属性
     */
    public AdminSecurityConfig(AdminServerProperties adminServer, SecurityProperties security) {
        this.adminServer = adminServer;
        this.security = security;
    }

    /**
     * 配置 HTTP 安全策略。
     *
     * @param http HttpSecurity
     * @throws Exception 配置异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

        http.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        .antMatchers(this.adminServer.path("/assets/**")).permitAll()
                        .antMatchers(this.adminServer.path("/actuator/info")).permitAll()
                        .antMatchers(this.adminServer.path("/actuator/health")).permitAll()
                        .antMatchers(this.adminServer.path("/login")).permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin.loginPage(this.adminServer.path("/login"))
                        .successHandler(successHandler)
        ).logout(
                logout -> logout.logoutUrl(this.adminServer.path("/logout"))
        ).httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.path("/instances"), HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"), HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))
                        ))
                .rememberMe(rememberMe -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
    }

    /**
     * 配置内存用户，供 rememberMe 等功能使用。
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception 配置异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(security.getUser().getName())
                .password("{noop}" + security.getUser().getPassword())
                .roles("USER");
    }
}

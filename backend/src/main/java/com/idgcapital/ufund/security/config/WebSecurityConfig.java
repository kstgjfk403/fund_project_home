package com.idgcapital.ufund.security.config;

import com.idgcapital.ufund.security.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by yi_zhang on 2017/12/18.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserLoginService userLoginService;


    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/css*//**").permitAll()
                .antMatchers("/js*//**").permitAll()
                .antMatchers("/img*//**").permitAll()
                .antMatchers("/docs*//**").permitAll()
                .antMatchers("/images*//**").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/static*//**").permitAll()
//                    .antMatchers("/admin*//**").hasAnyRole("ADMIN")
//                    .antMatchers("/projlist").hasAnyRole("USER")
                .antMatchers("/api*//**").permitAll()
                .antMatchers("/sys*//**").permitAll()
                .antMatchers("/projlist").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/

        http.headers().frameOptions().disable();
        http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated()
                .antMatchers("/").permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**","/docs/**","/img/**","/fonts/**","/css/**","/sound/**","/api/**","/mail/**");
    }
}

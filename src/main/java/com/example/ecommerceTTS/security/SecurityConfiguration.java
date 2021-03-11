package com.example.ecommerceTTS.security;

import com.example.ecommerceTTS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/custom.js").permitAll()
                .antMatchers("/custom.css").permitAll()
                .antMatchers("/cart").authenticated()
                .antMatchers().hasAuthority("USER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/signin").failureUrl("/signin?error=true")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                .logoutSuccessUrl("/")
                .and().exceptionHandling();
        http.headers().frameOptions().disable();
    }
}

package com.sadman.onlineshowroom.configuration;

import com.sadman.onlineshowroom.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginPage = "/login";
        String logoutPage = "/logout";

        http.
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(loginPage).permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/category/**").permitAll()
                .antMatchers("/supplier/**").permitAll()
                .antMatchers("/user/**").hasAuthority("ADMIN")
//                .antMatchers("/user/**").hasAnyAuthority("USER","ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")

                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage(loginPage).permitAll()
                .loginPage("/")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/user")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                .logoutSuccessUrl(loginPage).and().exceptionHandling();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**");
    }
}


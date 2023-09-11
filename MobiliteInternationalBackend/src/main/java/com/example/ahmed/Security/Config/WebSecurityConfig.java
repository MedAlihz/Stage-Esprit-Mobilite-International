package com.example.ahmed.Security.Config;

import com.example.ahmed.Auth.JwtAuthenticationEntryPoint;
import com.example.ahmed.Config.JwtAthFilter;
import com.example.ahmed.Entity.User;
import com.example.ahmed.Repository.UserRep;
import com.example.ahmed.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //kanit extends WebSecurityConfigurerAdapter
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtAthFilter jwtAuthFilter;
//    private AuthenticationProvider authenticationProvider;

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception {
        return new JwtAuthenticationEntryPoint();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers( "/api/v1/Auth/**", "/**").permitAll()
                .anyRequest().authenticated() .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().oauth2Login();
//      http.build();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }


        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider(){
            DaoAuthenticationProvider provider =
                    new DaoAuthenticationProvider();
            provider.setPasswordEncoder(bCryptPasswordEncoder);
            provider.setUserDetailsService(userService);
            return provider;

    }
    ////////////////for jwt
///////////////////////////////////////////////////////////////after validation spring
    @Autowired
    private UserDetailsService customUserDetailsService;



}

package com.example.ahmed.Config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;// 1:16:32
    private final JwtUtils jwtUtils;//jwtservice 3ando hoa 47:55

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException,
            IOException {


        final String authHeader = request.getHeader("Authorization");//1   42:34

        final String userEmail;//3 46:00
        final String jwtToken; // 2   7atha jwt 43:39


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
        }//44:52


        jwtToken = authHeader.substring(7);
                userEmail = jwtUtils.extractUsername(jwtToken);
//1:14:33
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//           final boolean isTokenValid;
                    if (jwtUtils.isTokenValid(jwtToken, userDetails)){

                        UsernamePasswordAuthenticationToken authToken =
                                            new UsernamePasswordAuthenticationToken(
                                                        userDetails,
                                                            null,
                                                                 userDetails.getAuthorities() );
                                 /////////////////////////
                        authToken.setDetails(
                                        new WebAuthenticationDetailsSource()
                                                .buildDetails(request));
                                    ////////////////////////////
                        SecurityContextHolder
                                .getContext()
                                     .setAuthentication(authToken);
                    }
        }
        filterChain.doFilter(request, response);
    }
}
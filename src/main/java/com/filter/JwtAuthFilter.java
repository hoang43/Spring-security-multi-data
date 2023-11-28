package com.filter;

import com.config.jwt.JwtProvider;
import com.dto.UserDto;
import com.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (!Common.isNullOrEmpty(token) && jwtProvider.validateToken(token)) {
                UserDto userDto = jwtProvider.getUserFromJWT(token);
                List<SimpleGrantedAuthority> lstRole = new ArrayList<>();
                if (!Common.isNullOrEmpty(userDto.getRole())) {
                    lstRole.addAll(Arrays.stream(userDto.getRole().split(";")).map(el -> new SimpleGrantedAuthority(el)).collect(Collectors.toList()));
                }
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDto, null, lstRole);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}

package com.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.response.BaseResponse;
import com.response.ResponseCode;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String json = new ObjectMapper().writeValueAsString(new BaseResponse(ResponseCode.ACCESS_DENIED));
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
    }
}

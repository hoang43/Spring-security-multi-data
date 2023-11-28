package com.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.response.BaseResponse;
import com.response.ResponseCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AccessDeniedEx implements AccessDeniedHandler {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String json = objectMapper.writeValueAsString(new BaseResponse(ResponseCode.ERROR_FORBIDDEN));
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
    }
}

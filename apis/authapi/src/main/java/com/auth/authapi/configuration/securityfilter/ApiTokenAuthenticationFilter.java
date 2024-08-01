package com.auth.authapi.configuration.securityfilter;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.common.helpers.GlobalProperties;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiTokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        try {
            var httpServletRequest = request;
            var apiKey = getHeader(httpServletRequest, GlobalProperties.API_KEY_HEADER);
            
            if (apiKey == null || !apiKey.equals(GlobalProperties.API_TOKEN)) {
                throw new BadCredentialsException("Unauthorized");
            }

            var apiKeyAuthentication = new ApiKeyAuthenticationToken(GlobalProperties.API_KEY_HEADER, GlobalProperties.API_TOKEN, AuthorityUtils.NO_AUTHORITIES);
            SecurityContextHolder.getContext().setAuthentication(apiKeyAuthentication);
        } catch (Exception exp) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }

        filterChain.doFilter(request, response);
    }

    private String getHeader(HttpServletRequest request, String headerKey) {
        return request.getHeader(headerKey);
    }
}

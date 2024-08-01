package com.auth.authapi.configuration.securityfilter;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final String _apiKey;
    private final String _token;

    public ApiKeyAuthenticationToken(String apiKey, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        _apiKey = apiKey;
        _token = token;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return _token;
    }

    @Override
    public Object getPrincipal() {
        return _apiKey;
    }
    
}

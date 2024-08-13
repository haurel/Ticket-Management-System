package com.tms.api.configuration;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.common.objects.models.user.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
    
    private final String _secretKey = "xxx";
    private long accessTokenValidity = 60*60*1000;

    private final JwtParser _jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil() {
        this._jwtParser = Jwts.parser().setSigningKey(_secretKey);
    }

    public String createToken(UserModel user) {
        String encodedString = Base64.getEncoder().encodeToString(_secretKey.getBytes());
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId",user.getUserId());
        claims.put("emailAddress",user.getEmailAddress());

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, encodedString)
                .compact();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private Claims parseJwtClaims(String token) {
        String encodedString = Base64.getEncoder().encodeToString(_secretKey.getBytes());
        return _jwtParser.setSigningKey(encodedString)
            .parseClaimsJws(token)
            .getBody();
    }
}

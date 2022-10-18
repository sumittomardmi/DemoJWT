package com.example.JwtDemo.Util;

import com.example.JwtDemo.Entity.Person;
import com.example.JwtDemo.Entity.User;
import com.example.JwtDemo.Exceptions.UnAuthorizedException;
import io.jsonwebtoken.*;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("CodeJava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String token) throws UnAuthorizedException {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            throw new UnAuthorizedException("Token expired");
        } catch (IllegalArgumentException ex) {
            throw new UnAuthorizedException("Token is null empty of whitespace");
        } catch (MalformedJwtException ex) {
            throw new UnAuthorizedException("Token is invalid");
        } catch (UnsupportedJwtException ex) {
            throw new UnAuthorizedException("Token is not supported");
        } catch (Exception e){
            throw new UnAuthorizedException("Validation failed");
        }
    }
}

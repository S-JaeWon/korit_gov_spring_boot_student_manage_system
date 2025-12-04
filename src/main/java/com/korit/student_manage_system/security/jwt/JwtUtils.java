package com.korit.student_manage_system.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey KEY;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateAccessToken(String id) {
        return Jwts.builder()
                .subject("AccessToken")
                .id(id)
                .expiration(new Date(new Date().getTime() + (1000L * 60L * 60L * 24L * 30L)))
                .signWith(KEY)
                .compact();
    }

    public Claims getClaims(String token) throws JwtException {
        // token 이 "Bearer xxx" 형식이면 앞에 "Bearer " 잘라줘야 함
        // token = token.replace("Bearer ", "");

        JwtParser jwtParser = Jwts.parser()
                .verifyWith((SecretKey) KEY)  // ✅ 예전 setSigningKey() 대신
                .build();

        // ✅ 서명된 토큰(JWS) 파싱할 때는 이거
        return jwtParser.parseSignedClaims(token)  // 예전 parseClaimsJws()
                .getPayload();                     // 예전 getBody()
    }

    public boolean isBearer(String token) {
        if (token == null) {
            return false;
        }

        if (!token.startsWith("Bearer ")) {
            return false;
        }
        return true;
    }

    public String removeBearer(String bearerToken){
        return bearerToken.replaceFirst("Bearer ", "");
    }
}

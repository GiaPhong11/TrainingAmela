package com.example.jwt.security.jwt;

import com.example.jwt.security.userPrinciple.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "phong.trangia@amela.vn";
    private int jwtExpiration = 86400;

    //Create Token
    //Được gọi qua login
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration* 1000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    //Check token hợp lệ
    //Gọi qua JwtTokenFilter
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature -> Message: {}",e);
        }catch (MalformedJwtException e){ //không đúng định dạng
            logger.error("Invalid format Token -> Message: {}",e);
        }catch (ExpiredJwtException e){ //Thời gian sống
            logger.error("Expired JWT Token -> Message: {}",e);
        }catch (UnsupportedJwtException e){ //Không hỗ trợ
            logger.error("Unsupported JWT Token -> Message: {}",e);
        }catch (IllegalArgumentException e){ //Ký tự trống k hợp lệ
            logger.error("JWT claims string is empty --> Message: {}",e);
        }
        return false;
    }

    //Gọi qua JwtTokenFilter
    public String getUserNameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }

}

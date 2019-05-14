package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author hftang
 * @date 2019-05-13 17:07
 * @desc
 */
public class CreateJwt {

    public static void main(String[] args) {
        JwtBuilder jwt = Jwts
                .builder()
                .setId("666")
                .setSubject("小米")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 60000))
                .signWith(SignatureAlgorithm.HS256, "itcast")
                .claim("role", "admin");
        String compact = jwt.compact();
        System.out.println(compact);


    }
}

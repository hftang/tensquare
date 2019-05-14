package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @author hftang
 * @date 2019-05-13 17:17
 * @desc 解析jwt
 */
public class ParseJwt {

    public static void main(String[] args) {

        Claims claims = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTI3ODQ3MzIyMTM5NjI3NTIwIiwic3ViIjoiaGZ0YW5nIiwiaWF0IjoxNTU3ODAyNzI5LCJyb2xlcyI6ImFkbWluIiwiZXhwIjoxNTU3ODA2MzI5fQ.l2CpyFH5L7viSSAFjDWAqPwPZe_NFlAqUz6SN6THj7M")
                .getBody();

        String id = claims.getId();

        String subject = claims.getSubject();

        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        Object role = claims.get("roles");//获取用户角色


        System.out.println(expiration);


    }
}

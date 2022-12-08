package com.jayus.jwt;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;

/**
 * @author : h zk
 * @date : 2022/11/25 17:55
 * @description :
 **/
public class JwtTest {

    // {"typ":"json","alg":"sinozo"}
    // {"sub":"12345678","admin":true}

    public static void main(String[] args) {
        String b = "B809-爱恨随意心甘情愿-（底字爱恨随意往事清零）-刘璇.ttf";
        System.out.println(b.endsWith(".ttf"));
        String a = "{\"typ\":\"json\",\"alg\":\"sinozo\"}";
        byte[] encode = Base64.getEncoder().encode(a.getBytes());
        byte[] decode = Base64.getDecoder().decode("eyJ0eXAiOiJqd3QiLCJhbGciOiJtZDUifQ".getBytes());
        System.out.println(new String(decode));

        // jwt 加密
        String compact = Jwts.builder()
                .setHeaderParam("typ", "Jwt")
                .setHeaderParam("alg", "md5")
                .claim("name", "father")
                .claim("role", "dad")
                .setSubject("sinozo")
                //.setExpiration( DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 1))
                .setId("36")
                .signWith(SignatureAlgorithm.HS256, "LXRNTXg4QxqgDF85Wi6mtkhatUNJzJD0").compact();
        //eyJ0eXAiOiJKd3QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZmF0aGVyIiwicm9sZSI6ImRhZCIsInN1YiI6InNpbm96byIsImV4cCI6MTY2OTY5MTIxMCwianRpIjoiMSJ9._q37rcvVQLsXCiq7Cp_dwDwgjbu6lfoA4F01kzGJRHM
        System.out.println(compact);
        // jwt 解密
        JwtParser parser = Jwts.parser();
        Jws<Claims> sinozo = parser.setSigningKey("LXRNTXg4QxqgDF85Wi6mtkhatUNJzJD0").parseClaimsJws("eyJ0eXAiOiJKd3QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZmF0aGVyIiwicm9sZSI6ImRhZCIsInN1YiI6InNpbm96byIsImV4cCI6MTY2OTcxMDg1NywianRpIjoibnVsbCJ9.c3vHn5LLj7rdWc8W9dkcgacKTVaSW8qG-LCfLf3CtxQ");
        Claims body = sinozo.getBody();
        System.out.println(body.getId());
        System.out.println(body.getExpiration());
    }
}

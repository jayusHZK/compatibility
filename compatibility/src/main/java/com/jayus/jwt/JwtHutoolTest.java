package com.jayus.jwt;

import cn.hutool.jwt.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2022/11/29 14:44
 * @description :
 **/
public class JwtHutoolTest {

    public static void main(String[] args) {
        Map<String,Object> headers = new HashMap<>();
        headers.put("typ", "Jwt");
        headers.put("alg", "md5");
        Map<String,Object> payload = new HashMap<>();
        payload.put("name", "father");
        payload.put("role", "dad");
        String token = JWTUtil.createToken(headers, payload, "1".getBytes());
        //eyJ0eXAiOiJKd3QiLCJhbGciOiJtZDUifQ.eyJyb2xlIjoiZGFkIiwibmFtZSI6ImZhdGhlciJ9.jopzQWMs9zQR6CTTlf8gRmZQQDNyJXijMH-A4bJo3EE
        System.out.println(token);
        System.out.println(JWTUtil.verify(token, "1".getBytes()));
    }

}

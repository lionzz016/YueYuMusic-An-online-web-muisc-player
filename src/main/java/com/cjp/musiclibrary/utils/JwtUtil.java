package com.cjp.musiclibrary.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CJP
 * @version 1.0
 */
public class JwtUtil {
    //    生成密钥
    private static final String GEN_KEY = "gH68Ks9s";
    //    密钥有效时间
    private static final long GEN_EXPIRATION = 1000 * 60 * 60 * 12;

    /**
     * JWT生成函数
     *
     * @param id 需要放入的实体类的唯一ID来生成JWT
     * @return JWT字符串(token)
     */
    public static String generate(Long id) {
        Map<String, Object> map = new HashMap<>();

        map.put("Id", id);

        return JWT.create()
                .withClaim("user", map)
                .withExpiresAt(new Date(System.currentTimeMillis() + GEN_EXPIRATION))
                .sign(Algorithm.HMAC256(GEN_KEY));
    }

    /**
     * JWT解码函数
     *
     * @param token JWT字符串(token)
     * @return 生成JWT token时放入的字段组成的map
     */
    public static Map<String, Claim> verify(String token) {
        return JWT
                .require(Algorithm.HMAC256(GEN_KEY))
                .build()
                .verify(token)
                .getClaims();
    }
}

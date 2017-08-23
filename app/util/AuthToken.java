package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/* Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-10
 */
public class AuthToken {

    private static final String RAILS_SECRET_KEY = System.getenv("HARU_RAILS_SECRET_KEY");

    public static JWT decodeToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(RAILS_SECRET_KEY))
                    .build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }
}

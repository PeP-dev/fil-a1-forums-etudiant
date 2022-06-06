package org.filmt.projetagile.auth.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


/**
 * @see <a href="https://medium.com/geekculture/implementing-json-web-token-jwt-authentication-using-spring-security-detailed-walkthrough-1ac480a8d970">JWT Auth turorial</a>
 */
@Component
public class JWTUtil {


    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
            .withSubject("User Details")
            .withClaim("username", username)
            .withIssuedAt(new Date())
            .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
            .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
            .withSubject("User Details")
            .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
            .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

}

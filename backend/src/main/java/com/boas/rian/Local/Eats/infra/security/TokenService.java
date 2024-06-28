package com.boas.rian.Local.Eats.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.boas.rian.Local.Eats.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            System.out.println(user);
            return JWT.create()
                    .withIssuer("local-eats")
                    .withSubject(user.getEmail())
                    .withClaim("userType", user.getUserType().toString())
                    .withExpiresAt(generateExpiresAt())
                    .sign(algorithm);
        } catch (
                JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT Token");
        }
    }

    public TokenWithClaim getSubjectFromJwtToken(String tokenJwt){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("local-eats")
                    .withClaimPresence("userType")
                    .build();

            DecodedJWT verify = verifier.verify(tokenJwt);
            System.out.println("claim" + verify.getClaim("userType"));

            return new TokenWithClaim(verify.getSubject(), verify.getClaim("userType").asString());
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error reading JWT Token");
        }
    }

    private Instant generateExpiresAt(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public record TokenWithClaim(String email, String userType){}
}

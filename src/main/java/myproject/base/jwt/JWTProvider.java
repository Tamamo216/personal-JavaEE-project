package myproject.base.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import myproject.base.config.SecretKeyGenerator;
import myproject.base.exception.InternalServerException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class JWTProvider {
    SecretKeyGenerator keyGenerator;
    private final Algorithm algorithm;

    @Inject
    public JWTProvider(SecretKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
        this.algorithm = Algorithm.HMAC256(this.keyGenerator.getSecretKey());
    }

    public JWTProvider() {this.algorithm = null;}
    public String generateToken(Map<String, String> payload) throws InternalServerException {
        String token;
        try {
            JWTCreator.Builder jwtBuilder = JWT.create();
            token = jwtBuilder
                    .withSubject(payload.get("email"))
                    .withPayload(payload)
                    .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 6))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new InternalServerException("Failed to generate JWT token");
        }
        return token;
    }

    public Map<String, String> validateToken(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT;
        Map<String, String> payload = new HashMap<>();
        JWTVerifier verifier = JWT.require(algorithm).build();
        decodedJWT = verifier.verify(token);
        decodedJWT.getClaims().entrySet().forEach(claim -> {
            payload.put(claim.getKey(), claim.getValue().asString());
        });
        return payload;
    }
}

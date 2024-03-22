package myproject.base.config;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Singleton
@Startup
public class SecretKeyGenerator {
    private String secretKey;

    public SecretKeyGenerator() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);
        SecretKey generatedKey = generator.generateKey();
        byte[] rawData = generatedKey.getEncoded();
        this.secretKey = Base64.getEncoder().encodeToString(rawData);
    }

    public String getSecretKey() {
        return secretKey;
    }
}

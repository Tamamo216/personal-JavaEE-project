package com.axonactive.base.config;

import org.flywaydb.core.Flyway;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
@Startup
public class FlywayMigration {
    public FlywayMigration() {
        try (InputStream ins = getClass().getClassLoader().getResourceAsStream("/flyway.properties")) {
            Properties properties = new Properties();
            properties.load(ins);

            Flyway flyway = Flyway
                    .configure()
                    .configuration(properties)
                    .load();

            flyway.migrate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.pincio.games.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${mail.service.key}")
    private String key;

    @Value("${mail.service.secret}")
    private String secret;

}

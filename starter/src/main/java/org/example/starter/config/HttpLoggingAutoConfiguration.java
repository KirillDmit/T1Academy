package org.example.starter.config;

import org.example.starter.aspect.HttpLoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HttpLoggingProperties.class)
@ConditionalOnProperty(prefix = "http.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class HttpLoggingAutoConfiguration {

    @Bean
    public HttpLoggingAspect httpLoggingAspect(HttpLoggingProperties properties) {
        return new HttpLoggingAspect(properties);
    }
}
package org.example.starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http.logging")
public class HttpLoggingProperties {

    @Value("${http.logging.enabled:true}")
    private boolean enabled;

    @Value("${http.logging.level:FULL}")
    private String level; // Возможные уровни: BASIC, HEADERS, FULL

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

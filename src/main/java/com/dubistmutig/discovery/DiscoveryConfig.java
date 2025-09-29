package com.dubistmutig.discovery;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "plugins.discovery")
@Component
public class DiscoveryConfig {
    private String soundcloudToken;

    public String getSoundcloudToken() {
        return soundcloudToken;
    }

    public void setSoundcloudToken(String soundcloudToken) {
        this.soundcloudToken = soundcloudToken;
    }
}

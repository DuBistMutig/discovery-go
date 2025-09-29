package com.dubistmutig.discovery;

import dev.arbjerg.lavalink.api.ISocketContext;
import dev.arbjerg.lavalink.api.PluginEventHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiscoveryPlugin extends PluginEventHandler {
    private static final Logger log = LoggerFactory.getLogger(DiscoveryPlugin.class);

    public DiscoveryPlugin() {
        log.info("Discovery Go plugin loaded successfully.");
    }

    @Override
    public void onWebSocketOpen(@NotNull ISocketContext context, boolean resumed) {
        String sessionId = context.getSessionId();
        if (resumed) {
            log.info("Websocket connection resumed for session ID: {}", sessionId);
        } else {
            log.info("New websocket connection established for session ID: {}", sessionId);
        }
    }
}

package com.dubistmutig.discovery;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.*;
import com.sedmelluq.discord.lavaplayer.tools.http.HttpContextFilter;
import com.sedmelluq.discord.lavaplayer.tools.io.HttpInterfaceManager;
import com.sedmelluq.discord.lavaplayer.track.AudioItem;
import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class DiscoveryAudioSourceManager extends SoundCloudAudioSourceManager {
    private static final Logger log = LoggerFactory.getLogger(DiscoveryAudioSourceManager.class);

    public DiscoveryAudioSourceManager(DiscoveryConfig config) {
        super(true, new DefaultSoundCloudDataReader(), new DefaultSoundCloudDataLoader(), new DefaultSoundCloudFormatHandler(), new DefaultSoundCloudPlaylistLoader(new DefaultSoundCloudDataLoader(), new DefaultSoundCloudDataReader(),new DefaultSoundCloudFormatHandler()));

        String token = config.getSoundcloudToken();
        if (token != null && !token.isEmpty()) {
            log.info("SoundCloud token found. Applying to HTTP client.");
            try {
                Field httpInterfaceManagerField = SoundCloudAudioSourceManager.class.getDeclaredField("httpInterfaceManager");
                httpInterfaceManagerField.setAccessible(true);
                HttpInterfaceManager httpInterfaceManager = (HttpInterfaceManager) httpInterfaceManagerField.get(this);

                httpInterfaceManager.setHttpContextFilter(new HttpContextFilter() {
                    @Override
                    public void onRequest(HttpClientContext context, HttpUriRequest request, boolean isRepetition) {
                        if (request.getURI().getHost().contains("soundcloud.com")) {
                            request.addHeader("Authorization", "OAuth " + config.getSoundcloudToken());
                        }
                    }

                    @Override
                    public boolean onRequestResponse(HttpClientContext context, HttpUriRequest request,
                            HttpResponse response) {
                        return false;
                    }

                    @Override
                    public void onContextOpen(HttpClientContext context) {

                    }

                    @Override
                    public void onContextClose(HttpClientContext context) {

                    }

                    @Override
                    public boolean onRequestException(HttpClientContext context, HttpUriRequest request,
                            Throwable error) {
                        return false;
                    }
                });
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Failed to apply SoundCloud token via reflection. SoundCloud Go+ tracks may not work.", e);
            }
        } else {
            log.warn("No SoundCloud token provided in application.yml. SoundCloud Go+ tracks will be limited to 30 second previews.");
        }
    }

    @Override
    public String getSourceName() {
        return "discovery-soundcloud";
    }

    @Override
    public AudioItem loadItem(AudioPlayerManager manager, AudioReference reference) {
        return super.loadItem(manager, reference);
    }
}

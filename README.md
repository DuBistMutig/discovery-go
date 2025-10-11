# Discovery Go Plugin for Lavalink

A simple Lavalink plugin that enables playback of SoundCloud Go+ tracks by adding an OAuth token to the requests

## Requirements

* A working Lavalink instance
* A SoundCloud Go+ account

## Accquire Soundcloud OAuth Token

1. Go to the [SoundCloud](https://soundcloud.com) website
2. Login with your SoundCloud Go+ account
3. Inspect the website and go to the **Application** tab
4. In there search the **Cookies** tab for the **oauth_token** field
5. That's your token you're gonna use

## Automatic Installation

1.  **Configure Lavalink**: Add the necessary configuration to your `application.yml` file like this for example:
```yaml
lavalink:
  plugins:
    - dependency: "com.github.dubistmutig:discovery-go:0.1.0"
      repository: "https://jitpack.io"
```

2. After that see [Configuration](#configuration)

## Manual Installation

1.  **Download the Plugin**: Grab the latest `discovery-go-plugin-0.1.0.jar` file from the [releases page](https://github.com/dubistmutig/discovery-go/releases)
2.  **Place the Plugin**: Move the downloaded `discovery-go-plugin-0.1.0.jar` file into your Lavalink `plugins` directory. If the directory doesn't exist, create it in the same folder where your `Lavalink.jar` is located
3.  **Configure Lavalink**: After that see [Configuration](#configuration)

## Configuration

After you have installed everything you need to add the SoundCloud Token to your `application.yml` file

Open your `application.yml` and add the following section:

```yml
plugins:
  discovery:
    soundcloudToken: "YOUR_SOUNDCLOUD_OAUTH_TOKEN"
```

And Replace "YOUR_SOUNDCLOUD_OAUTH_TOKEN" with your SoundCloud token you got from [Accquire Soundcloud OAuth Token](#accquire-soundcloud-oauth-token)

### Important

If you're using the [LavaSrc](https://github.com/topi314/LavaSrc) plugin, turn off the soundcloud source like this for example:

```yml
lavalink:
  server:
    sources:
      soundcloud: false
```

## Note
This plugin is provided as-is. I am not liable for any issues that may arise from its use, including but not limited to account restrictions imposed by SoundCloud. Please be aware that using your OAuth token in this manner may be against SoundCloud's Terms of Service. Use this plugin at your own risk.

This README was created with a little help from AI, so please spare me if it’s not perfect!

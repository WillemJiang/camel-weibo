package org.apache.camel.component.weibo;

import weibo4j.Timeline;
import weibo4j.Weibo;

public class WeiboConfiguration {
    /**
     * OAuth
     */
    private String accessToken;
    private String clientId;
    private String clientSecret;

    private int delay;

    public WeiboConfiguration() {
        // need to load the configuration somewhere
    }

    public void configureWeibo(Weibo client) {
        client.setToken(accessToken);
    }

    public Timeline createTimeline() {
        Timeline answer = new Timeline();
        configureWeibo(answer);
        return answer;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

}

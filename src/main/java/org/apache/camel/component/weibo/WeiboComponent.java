package org.apache.camel.component.weibo;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

/**
 * Represents the component that manages {@link WeiboEndpoint}.
 */
public class WeiboComponent extends DefaultComponent {
    private String clientId;
    private String clientSecret;
    private String accessToken;

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        WeiboConfiguration configuration = new WeiboConfiguration();
        configuration.setAccessToken(accessToken);
        configuration.setClientId(clientId);
        configuration.setClientSecret(clientSecret);
        setProperties(configuration, parameters);
        WeiboEndpoint endpoint = new WeiboEndpoint(uri, this);
        endpoint.setConfiguration(configuration);
        setProperties(endpoint, parameters);
        return endpoint;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

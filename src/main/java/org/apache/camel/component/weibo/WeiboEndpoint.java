package org.apache.camel.component.weibo;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Represents a Weibo endpoint.
 */
public class WeiboEndpoint extends DefaultEndpoint {
    private WeiboConfiguration configuration;

    public WeiboEndpoint() {
    }

    public WeiboEndpoint(String uri, WeiboComponent component) {
        super(uri, component);
    }

    public WeiboEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new WeiboProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new WeiboConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    public WeiboConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(WeiboConfiguration configuration) {
        this.configuration = configuration;
    }
}

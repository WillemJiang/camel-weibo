package org.apache.camel.component.weibo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WeiboConsumerTest extends WeiboTestSupport {
    private static final transient Logger LOG = LoggerFactory.getLogger(WeiboConsumerTest.class);

    @Test
    public void testProducerTest() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);
        mock.assertIsSatisfied();
        List<Exchange> weibo = mock.getExchanges();
            for (Exchange e : weibo) {
                LOG.info("Weibo: " + e.getIn().getBody(String.class));
            }

    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("weibo://timeline/mention/?" + getUriTokens() + "&delay=30")
                    .to("mock:result");

            }
        };
    }
}

package org.apache.camel.component.weibo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

public class WeiboProducerTest extends WeiboTestSupport {
    @Test
    public void testProducerTest() {
        MockEndpoint result = getMockEndpoint("mock:result");
        result.expectedMessageCount(1);
        template.requestBody("direct://start", "This is a test message sending from WeiboProducerTest.");
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct://start")
                    .to("weibo://timeline/?" + getUriTokens())
                        .to("mock:result");
            }
        };
    }
}

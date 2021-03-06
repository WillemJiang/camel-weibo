/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.weibo;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

/**
 * The Weibo producer which just post the status.
 */
public class WeiboProducer extends DefaultProducer {
    private static final transient Logger LOG = LoggerFactory.getLogger(WeiboProducer.class);
    private WeiboEndpoint endpoint;

    public WeiboProducer(WeiboEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {

        String s = exchange.getIn().getMandatoryBody(String.class);
        Status response = updateStatus(s);

        if (exchange.getPattern().isOutCapable()) {
            // here we just copy the header of in message to the out message
            exchange.getOut().copyFrom(exchange.getIn());
            exchange.getOut().setBody(response);
        }
    }

    private Status updateStatus(String status) throws WeiboException {
        if (status.length() > 140) {
            log.warn("Message is longer than 140 characters. Message will be truncated!");
            status = status.substring(0, 140);
        }
        Timeline timeline = endpoint.getConfiguration().createTimeline();
        return timeline.UpdateStatus(status);
    }


}

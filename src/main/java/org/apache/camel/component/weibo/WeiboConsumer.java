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
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * The Weibo consumer.
 */
public class WeiboConsumer extends ScheduledPollConsumer {
    private final WeiboEndpoint endpoint;

    public WeiboConsumer(WeiboEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        int delay = endpoint.getConfiguration().getDelay();
        setInitialDelay(1);
        setDelay(delay);
        setTimeUnit(TimeUnit.SECONDS);

    }

    @Override
    protected int poll() throws Exception {
        Timeline timeline = endpoint.getConfiguration().createTimeline();
        // TODO need to find a way to remember the last status id
        StatusWapper result = timeline.getMentions();
        Iterator<Status> i = result.getStatuses().iterator();

        int total = 0;
        while (i.hasNext()) {
            Exchange e = getEndpoint().createExchange();
            e.getIn().setBody(i.next());
            // here we don't check the exception
            getProcessor().process(e);
            total++;
        }
        System.out.println("result size is " + total);
        return total;
    }


}

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
        return total;
    }


}

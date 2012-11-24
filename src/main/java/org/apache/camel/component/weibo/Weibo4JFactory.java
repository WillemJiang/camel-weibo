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

import java.util.regex.Pattern;

import org.apache.camel.component.weibo.timeline.FriendsConsumer;
import org.apache.camel.component.weibo.timeline.HomeConsumer;
import org.apache.camel.component.weibo.timeline.MentionsConsumer;
import org.apache.camel.component.weibo.timeline.PublicConsumer;
import org.apache.camel.component.weibo.timeline.RepostConsumer;
import org.apache.camel.component.weibo.timeline.UserConsumer;
import org.apache.camel.component.weibo.type.ConsumerType;
import org.apache.camel.component.weibo.type.TimelineType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class Weibo4JFactory {
    private static final transient Logger LOG = LoggerFactory.getLogger(Weibo4JFactory.class);
    
    private Weibo4JFactory() {
        //Helper class
    }
    
    public static Weibo4JPuller getConsumer(WeiboEndpoint endpoint, String uri) {
        String[] uriSplit = splitUri(uri);
        if (uriSplit.length > 0) {
            switch (ConsumerType.fromUri(uriSplit[0])) {
            case TIMELINE:
                if (uriSplit.length > 1) {
                    switch (TimelineType.fromUri(uriSplit[1])) {
                    case FRIENDS:
                        return new FriendsConsumer(endpoint);
                    case HOME:
                        return new HomeConsumer(endpoint);
                    case USER:
                        return new UserConsumer(endpoint);
                    case REPOST:
                        return new RepostConsumer(endpoint);
                    case MENTIONS:
                        return new MentionsConsumer(endpoint);
                    default:
                        break;
                    }
                }
                break;
            default:
                break;
            }
        }
    
        LOG.warn("A consumer type was not provided (or an incorrect pairing was used).  Defaulting to Public Timeline!");
        return new PublicConsumer(endpoint);
    }
    
    private static String[] splitUri(String uri) {
        Pattern p1 = Pattern.compile("weibo:(//)*");
        Pattern p2 = Pattern.compile("\\?.*");

        uri = p1.matcher(uri).replaceAll("");
        uri = p2.matcher(uri).replaceAll("");

        return uri.split("/");
    }

}

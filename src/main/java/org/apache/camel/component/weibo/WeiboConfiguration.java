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

import weibo4j.Timeline;
import weibo4j.Weibo;

public class WeiboConfiguration {
    /**
     * OAuth
     */
    private String accessToken;

    private int delay;

    private long lastId = 1;

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

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public long getLastId() {
        return lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }

}

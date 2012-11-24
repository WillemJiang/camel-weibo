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

import java.util.List;

import weibo4j.model.Status;
import weibo4j.model.WeiboException;


public abstract class Weibo4JPuller {
    /**
     * Instance of WeiboEndpoint.
     */
    protected WeiboEndpoint endpoint;

    /**
     * The last Weibo ID received.
     */
    protected long lastId = 1;

    protected Weibo4JPuller(WeiboEndpoint we) {
        this.endpoint = we;
    }
   
    protected void checkLastId(long newId) {
        if (newId > lastId) {
            lastId = newId;
        }
    }
    
    public List<Status> pollConsume() throws WeiboException {
        
        List<Status> answer = pollStatus(lastId);
        // update the lastId
        for (Status s : answer) {
            checkLastId(new Long(s.getId()));
        }
        return answer;
    }
   
    public abstract List<Status> pollStatus(long lastId) throws WeiboException;


}

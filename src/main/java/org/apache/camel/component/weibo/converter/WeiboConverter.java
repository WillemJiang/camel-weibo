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
package org.apache.camel.component.weibo.converter;

import org.apache.camel.Converter;
import weibo4j.model.Status;

@Converter
public final class WeiboConverter {
 
    private WeiboConverter() {
        // Helper class
    }
    
    @Converter
    public static String toString(Status status) {
        StringBuilder s = new StringBuilder();
        s.append(status.getCreatedAt());
        if (status.getUser() != null) {
            s.append(" (").append(status.getUser().getScreenName()).append(") ");
            
        } // the weibo could be deleted, so we skip the user part
        s.append(status.getText());
        return s.toString();
    }

}

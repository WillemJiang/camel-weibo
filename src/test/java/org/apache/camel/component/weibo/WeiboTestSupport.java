package org.apache.camel.component.weibo;

import org.apache.camel.test.junit4.CamelTestSupport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class WeiboTestSupport extends CamelTestSupport {
    private String clientId;
    private String clientSecret;
    private String accessToken;

   public WeiboTestSupport() {
       URL url = getClass().getResource("/test-options.properties");

       InputStream inStream;
       try {
           inStream = url.openStream();
       } catch (IOException e) {
           e.printStackTrace();
           throw new IllegalAccessError("test-options.properties could not be found");
       }

       Properties properties = new Properties();
       try {
           properties.load(inStream);
       } catch (IOException e) {
           e.printStackTrace();
           throw new IllegalAccessError("test-options.properties could not be found");
       }

       clientId = properties.get("client.id").toString();
       clientSecret = properties.get("client.secret").toString();
       accessToken = properties.get("access.token").toString();
   }

    protected String getUriTokens() {
        return "clientId=" + clientId + "&clientSecret=" + clientSecret + "&accessToken="
                + accessToken;
    }

}

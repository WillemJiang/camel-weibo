camel-weibo
===========

The camel component for sina weibo, you need to checkout [weibo4j-oauth2](https://github.com/WillemJiang/weibo4j-oauth2) and build it first before you walk through this component.

Running the tests
============
1. Apply an application development from [Sina Weibo](http://open.weibo.com/apps), you should get a clientId and clientSecret for running the test
2. Find out the access token with by running the OAuth4Code example from weibo2j-oauth2
3. Add test-options.properties file into test/resources directory with below entries

```
access.token=
# The access token which you Weibo account granted for the test application
client.id=
# The client id which you apply for thest
client.secret=
# The client secret key of you test application
```
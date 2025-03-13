# Spring Demo

1. 声明 `Spring Bean`

```java

@Configuration
public class TencentApiTemplateConfig {
    @Value("${***}")
    private String key;
    @Value("${***}")
    private String sign;

    @Bean
    public HttpClientFactoryBean httpProxyClient() {
        HttpClientFactoryBean httpClientFactoryBean = new HttpClientFactoryBean();
        // httpClientFactoryBean.setProxy(new HttpHost("proxy.host.com", 1234));
        return httpClientFactoryBean;
    }

    /**
     * 签名生成器
     */
    @Bean
    public IMParamsEncoder imParamsEncoder() {
        return new IMParamsEncoder(SDK_APP_ID, SECRET_KEY);
    }

    /**
     * 腾讯接口 client
     */
    @Bean
    public TencentApiTemplate tencentApiTemplate() {
        TencentApiTemplate tencentApiTemplate = new TencentApiTemplate();
        tencentApiTemplate.setHome(HOME);
        tencentApiTemplate.setHttpClient(HttpClients.createDefault());
        return tencentApiTemplate;
    }

    /**
     * IM 会话分组接口
     */
    @Bean
    public static IMTencentContactGroupService imTencentContactGroupService(IMParamsEncoder imParamsEncoder, TencentApiTemplate tencentApiTemplate) {
        return new IMTencentContactGroupServiceImpl(tencentApiTemplate, imParamsEncoder);
    }

}
```

2. 示例代码：IM 会话分组管理

```java

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
@ActiveProfiles("dev")
public class IMTencentContactGroupServiceTest {
    @Resource
    private IMTencentContactGroupService imTencentContactGroupService;

    /**
     * 创建会话分组
     */
    @Test
    public void createContactGroup() {
        ContactGroupAddRequest request = new ContactGroupAddRequest("2b910ea8e7314f63a9176e31510eb29d");
        request.add("已邀面", "eab277bd25174dd69ef224d357a02040");
        imTencentContactGroupService.createContactGroup(request);
    }

    /**
     * 修改会话分组或者会话加入分组
     */
    @Test
    public void updateContactGroup() {
        ContactGroupUpdateRequest request = new ContactGroupUpdateRequest("2b910ea8e7314f63a9176e31510eb29d",
                ContactGroupUpdateRequest.UpdateGroup.buildUpdateGroupContact("testGroup2"));
        request.addContact("89c90d3f1b0142538ff302703d7537c5");
        imTencentContactGroupService.updateContactGroup(request);
    }

    /**
     * 获取分组
     */
    @Test
    public void getContactGroup() {
        ContactGroupGetRequest request = new ContactGroupGetRequest("a7b7142a309849f68276fe6b82297719");
        request.setStartIndex(0);
        imTencentContactGroupService.getContactGroup(request);

    }

    /**
     * 删除分组
     */
    @Test
    public void getContactGroup() {
        ContactGroupDeleteRequest request = new ContactGroupDeleteRequest("administrator", "3d8c2e5601824710972252cefc49e491");
        request.add("testGroup");
        imTencentContactGroupService.getContactGroup(request);
    }
}
```

3. `HttpClientFactoryBean`

```java
public class HttpClientFactoryBean implements FactoryBean<CloseableHttpClient>, InitializingBean, DisposableBean {
    private static final String DFT_UA = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    private int maxConnTotal = 512; // 最大支持的连接数
    private int maxConnPerRoute = 64; // 针对某个域名的最大连接数
    private int connectionRequestTimeout = 5000; // 从连接池中获取连接的超时时间
    private int connectTimeout = 6 * 1000; // 跟目标服务建立连接超时时间，根据自己的业务调整
    private int socketTimeout = 8 * 1000; // 请求的超时时间（建联后，获取response的返回等待时间）
    private String userAgent = DFT_UA;
    private HttpHost proxy;

    public void setMaxConnTotal(int maxConnTotal) {
        this.maxConnTotal = maxConnTotal;
    }

    public void setMaxConnPerRoute(int maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setProxy(HttpHost proxy) {
        this.proxy = proxy;
    }

    private CloseableHttpClient client;
    private BasicHttpClientConnectionManager connManager;

    public HttpClientFactoryBean() {

    }

    public HttpClientFactoryBean(BasicHttpClientConnectionManager connManager) {
        this.connManager = connManager;
    }

    @Override
    public void afterPropertiesSet() {
        RequestConfig reqCfg = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        HttpClientBuilder builder = HttpClients.custom().setMaxConnTotal(maxConnTotal).setMaxConnPerRoute(maxConnPerRoute)
                .evictExpiredConnections().evictIdleConnections(5, TimeUnit.MINUTES)
                .setUserAgent(userAgent).setDefaultRequestConfig(reqCfg);
        if (connManager != null) {
            builder.setConnectionManager(connManager);
        }
        if (proxy != null) {
            builder.setProxy(proxy);
        }
        client = builder.build();
    }

    @Override
    public CloseableHttpClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return CloseableHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }
}
```

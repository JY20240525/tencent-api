# tencent-api

`tencent-api` 基于腾讯云官方文档开发，是一款用于与 **腾讯云通信 IM** 交互的 **Java SDK**。  
该 SDK 设计简洁、易用，并持续更新，便于开发者快速集成和扩展。

## Maven 依赖

SDK 默认引入以下核心依赖：

| 依赖名称             | 版本              | 说明          |
|------------------|-----------------|-------------|
| `httpclient`     | **4.5.13**      | HTTP 客户端库   |
| `commons-lang3`  | **3.8.1**       | 字符串、集合等工具类  |
| `gson`           | **2.8.9**       | JSON 解析库    |
| `slf4j-api`      | **1.7.25**      | 日志框架        |
| `validation-api` | **2.0.1.Final** | 数据验证        |
| `lombok`         | **1.18.6**      | Java 代码简化工具 |
| `hutool-all`     | **5.8.27**      | 实用工具库       |

## 已功能

##### 腾讯云 IM 相关 SDK

1. IM 用户管理 [IMTencentUserService](src/main/java/com/tencent/api/module/im/service/IMTencentUserService)
2. IM
   会话分组 [IMTencentContactGroupService](src/main/java/com/tencent/api/module/im/service/IMTencentContactGroupService)

## 使用方式,如果是 Spring 可以声明 Spring Bean 例如：[Spring Demo](doc/Spring.md)
* Test 案例 `IMTencentContactGroupServiceTests`
### 1. 初始化签名器

```java
IMParamsEncoder imParamsEncoder = new IMParamsEncoder("sdkappid","secretKey");

```

### 2. HTTP 客户端

```java
TencentApiTemplate tencentApiTemplate = new TencentApiTemplate();
tencentApiTemplate.setHome("https://console.tim.qq.com/");
tencentApiTemplate.setHttpClient(HttpClients.createDefault());
```

### 3. 创建会话分组的 Service 实例

```java
IMTencentContactGroupService service=new IMTencentContactGroupServiceImpl(tencentApiTemplate(),imParamsEncoder);
```

### 4. 示例代码：IM 会话分组管理

```java
/**
 * IMTencentContactGroupService 测试类
 */
public class IMTencentContactGroupServiceTests {
    private static final String HOME = "https://console.tim.qq.com/";

    /**
     * SDK App ID（由腾讯提供）
     */
    private static final String SDK_APP_ID = "腾讯提供";

    /**
     * 密钥（请妥善保管，若泄露请尽快重新生成）
     */
    private static final String SECRET_KEY = "腾讯提供密钥";

    /**
     * 创建签名生成器
     */
    public static IMParamsEncoder imParamsEncoder() {
        return new IMParamsEncoder(SDK_APP_ID, SECRET_KEY);
    }

    /**
     * 配置腾讯接口 Client
     */
    public static TencentApiTemplate tencentApiTemplate() {
        TencentApiTemplate tencentApiTemplate = new TencentApiTemplate();
        tencentApiTemplate.setHome(HOME);
        tencentApiTemplate.setHttpClient(HttpClients.createDefault());
        return tencentApiTemplate;
    }

    /**
     * 初始化 IM 会话分组服务
     */
    public static IMTencentContactGroupService imTencentContactGroupService() {
        return new IMTencentContactGroupServiceImpl(tencentApiTemplate(), imParamsEncoder());
    }

    /**
     * 添加新的会话分组
     */
    public static void addGroup() {
        IMTencentContactGroupService imTencentContactGroupService = imTencentContactGroupService();
        ContactGroupAddRequest requestAdd = new ContactGroupAddRequest("administrator", "fromAccount");
        requestAdd.add("testGroup", "toAccount");

        IMResponse response = imTencentContactGroupService.createContactGroup(requestAdd);

        System.out.println("- ------------------------------------------------------------------");
        System.out.println("- 返回结果：");
        System.out.println('-' + SONUtil.toJsonStr(response));
        System.out.println("- ------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        addGroup();
    }
}
```

## 新增接口也很简单

新增接口只需两步：

1. 声明对应的两个类：
    - `*Request extends IMRequest<*Request>`
    - `*Response extends IMResponse`
2. 实现接口，修改对应的 `Request`、`Response` 和 `URL`。

### Java 实现示例

```java
public IMResponse accountImportRequest(AccountImportRequest request) {
    String url = "/v4/im_open_login_svc/account_import?" + paramsEncoder.encode(request.getParams(), request);
    TencentResponse response = tencentApiTemplate.post(url, request);
    
    Assert.isTrue(response.getStatusCode() == HttpStatus.SC_OK, 
        "Tencent API 调用错误，状态码: " + response.getStatusCode() + "，响应体: " + response.getBody());

    return new Gson().fromJson(response.getBody(), ContactGroupAddResponse.class);
}
```

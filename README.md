# tencent-api

`tencent-api` 基于腾讯云官方文档开发，是一款用于与 **腾讯云通信 IM** 交互的 **Java SDK**。  
该 SDK 设计简洁、易用，并持续更新，便于开发者快速集成和扩展。

## 目标

- **简单易用**：提供清晰直观的 API 设计，便于开发者上手。
- **熟悉的 CRUD 方式**：遵循标准 **增删改查（CRUD）** 模式，提高开发效率。
- **统一日志与异常管理**：标准化日志输出与异常处理，确保稳定性。
- **提升开发体验**：[例子](doc/Good.md)。

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
1. IM 用户管理 [IMTencentUserService](https://cloud.tencent.com/document/product/269/1608)
2. IM 会话分组 [IMTencentContactGroupService](https://cloud.tencent.com/document/product/269/85791)

### 1. 初始化签名器与 HTTP 客户端

```java
IMParamsEncoder imParamsEncoder = new IMParamsEncoder("sdkappid","secretKey");
TencentApiTemplate tencentApiTemplate = new TencentApiTemplate();
tencentApiTemplate.setHome("https://console.tim.qq.com/");
tencentApiTemplate.setHttpClient(HttpClients.createDefault());
```

### 2. 创建会话分组的 Service 实例

```java
IMTencentContactGroupService service = new IMTencentContactGroupServiceImpl(tencentApiTemplate(),imParamsEncoder);
```

### 3. 示例代码：IM 会话分组管理

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

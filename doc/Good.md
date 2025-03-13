# 方案参考

### 一、[更新会话分组数据](https://cloud.tencent.com/document/product/269/85793)

该接口支持两个主要功能：更新会话分组名称和将会话添加到指定分组。

#### 请求参数说明

请求的 JSON 格式较为复杂，以下是示例：

**1. 请求 JSON 示例：**

```json
{
  "From_Account": "user20",
  "UpdateType": 1,
  "UpdateGroup": {
    "UpdateGroupType": 2,
    "OldGroupName": "test1",
    "ContactUpdateItem": [
      {
        "ContactOptType": 1,
        "ContactItem": {
          "Type": 1,
          "To_Account": "user1"
        }
      }
    ]
  }
}

```

**2. Java 实现示例**

* 更新会话分组名称：

```java
    IMTencentContactGroupService imTencentContactGroupService=imTencentContactGroupService();
        ContactGroupUpdateRequest requestUpdate=new ContactGroupUpdateRequest("administrator","3d8c2e5601824710972252cefc49e491",
        ContactGroupUpdateRequest.UpdateGroup.buildUpdateGroupName("testGroup","newTestGroup"));
```

* 将会话添加到分组：

```java
    IMTencentContactGroupService imTencentContactGroupService=imTencentContactGroupService();
        ContactGroupUpdateRequest requestUpdate=new ContactGroupUpdateRequest("administrator","3d8c2e5601824710972252cefc49e491",
        ContactGroupUpdateRequest.UpdateGroup.buildUpdateGroupContact("testGroup"));
```


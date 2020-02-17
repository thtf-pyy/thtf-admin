## 使用说明

### 1. 导入依赖
```yaml
  <dependency>
      <groupId>com.thtf</groupId>
      <artifactId>thtf-common-data</artifactId>
  </dependency>
```

### 2. 自动填充配置
需要自动补全 创建人 修改人信息，需要对应实体类继承 CommonModel,
```java
@Data
@TableName(value = "sys_user")
public class SysUser extends CommonModel{

	/** 用户ID */
	private String id;
	/** 名称 */
	private String name;
	/** 用户名 */
	private String username;
        ...
}
```

### 3. 逻辑删除配置

- 使用 @TableLogic 
    ```java
        /** 删除标记 */
        @TableLogic
        private Integer deletedFlag = 0;
    ```
   
- 使用全局配置
    ```yaml
    mybatis-plus:
      global-config:
        db-config:
          logic-delete-field: flag  #全局逻辑删除字段值
    ```
  但如果实体类上有 @TableLogic 则以实体上的为准，忽略全局。 即先查找注解再查找全局，都没有则此表没有逻辑删除。


### 4. 在目标工程 application.yml 添加配置
```yaml
#mybatis-plus配置
mybatis-plus:
  mapper-locations: classpath*:/mapper/**Mapper.xml
  refresh-mapper: true
  global-config:
    db-config:
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

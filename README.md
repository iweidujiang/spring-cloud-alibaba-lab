# Spring Cloud Alibaba Lab

Spring Cloud Alibaba 系列文章配套代码，每篇文章对应一个独立模块。

| 目录 | 文章 |
|------|------|
| 01-architecture-evolution | 系统架构演进与 Spring Cloud Alibaba 简介 |
| 02-nacos | Nacos 实战及其客户端服务注册源码解析 |
| 03-sentinel-flow | Sentinel 限流配置实战 |
| 04-sentinel-degrade | Sentinel 熔断降级策略实战 |
| 05-sentinel-nacos-datasource | Sentinel 规则持久化到 Nacos |
| 06-sentinel-block-fallback | Sentinel blockHandler 与 fallback 优雅返回 |
| 07-open-feign | OpenFeign 远程接口调用 |
| 08-feign-sentinel | OpenFeign 整合 Sentinel |
| 09-gateway | Spring Cloud Gateway 网关 |
| 10-gateway-rate-limit | Spring Cloud Gateway 网关限流 |
| 11-sleuth-zipkin | Spring Cloud Sleuth 整合 Zipkin |
| 12-spring-boot-admin | Spring Boot Admin 监控 |
| 13-seata-intro | 分布式事务与 Seata 简介 |


## 环境要求

- JDK 8+
- Maven 3.6+

## 技术栈

| 组件 | 版本 |
|------|------|
| Spring Boot | 2.6.3 |
| Spring Cloud | 2021.0.1 |
| Spring Cloud Alibaba | 2021.0.1.0 |

## 快速开始

```bash
git clone https://github.com/iweidujiang/spring-cloud-alibaba-lab.git
cd spring-cloud-alibaba-lab
mvn clean package -pl 01-architecture-evolution -am
java -jar 01-architecture-evolution/target/01-architecture-evolution-1.0.0.jar
```

## 01 - 系统架构演进与 Spring Cloud Alibaba 简介

演示单体应用架构：用户与订单模块部署在同一 Spring Boot 进程中，`OrderService` 通过依赖注入直接调用 `UserService`。

```bash
# 构建
mvn clean package -pl 01-architecture-evolution -am -DskipTests

# 启动
java -jar 01-architecture-evolution/target/01-architecture-evolution-1.0.0.jar

# 验证
curl http://localhost:8080/users/1
curl http://localhost:8080/orders/1001
curl http://localhost:8080/actuator/health
```

## 02 - Nacos 实战

代码位于 `02-nacos` 目录，包含 `nacos-provider`、`nacos-consumer`、`nacos-config` 三个子模块。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`，账号密码 `nacos/nacos`）
2. 在 Nacos 配置管理 `DEFAULT_GROUP` 下创建 `test.yml`，内容如下：

```yaml
config:
  info: I am a config info
```

### 构建

```bash
mvn clean package -pl 02-nacos/nacos-provider,02-nacos/nacos-consumer,02-nacos/nacos-config -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动服务提供者（8080）
java -jar 02-nacos/nacos-provider/target/nacos-provider-1.0.0.jar

# 2. 再启动一个提供者实例（8081，验证负载均衡）
java -jar 02-nacos/nacos-provider/target/nacos-provider-1.0.0.jar --server.port=8081

# 3. 启动服务消费者（9080）
java -jar 02-nacos/nacos-consumer/target/nacos-consumer-1.0.0.jar

# 4. 启动配置中心客户端（7071）
java -jar 02-nacos/nacos-config/target/nacos-config-1.0.0.jar

# 验证服务发现与负载均衡
curl http://localhost:9080/consume

# 验证配置中心
curl http://localhost:7071/info
```

可通过环境变量 `NACOS_SERVER` 指定 Nacos 地址，例如：`NACOS_SERVER=192.168.1.100:8848`。

## 03 - Sentinel 限流配置实战

代码位于 `03-sentinel-flow` 模块，服务名 `sentinel-service`，端口 `7072`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 下载并启动 Sentinel Dashboard（示例 1.8.3）：

```bash
java -Dserver.port=8080 \
  -Dcsp.sentinel.dashboard.server=localhost:8080 \
  -Dproject.name=sentinel-dashboard \
  -Dsentinel.dashboard.auth.username=sentinel \
  -Dsentinel.dashboard.auth.password=123456 \
  -jar sentinel-dashboard-1.8.3.jar
```

> 若 8080 端口已被占用（如第 1 章示例），可将 Dashboard 端口改为 8858，并设置环境变量 `SENTINEL_DASHBOARD=localhost:8858`。

### 构建

```bash
mvn clean package -pl 03-sentinel-flow -am -DskipTests
```

### 启动与验证

```bash
java -jar 03-sentinel-flow/target/03-sentinel-flow-1.0.0.jar

# 先访问接口触发 Sentinel 懒加载
curl http://localhost:7072/test-a
curl http://localhost:7072/test-b
curl http://localhost:7072/test-c
```

随后在 Sentinel Dashboard 中配置流控规则，使用 JMeter/Postman 压测验证 QPS、并发线程数、关联、链路与 Warm Up 等场景。

## 04 - Sentinel 熔断降级策略实战

代码位于 `04-sentinel-degrade` 模块，服务名 `sentinel-degrade-service`，端口 `7072`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 启动 Sentinel Dashboard（参考第 3 章）

### 构建

```bash
mvn clean package -pl 04-sentinel-degrade -am -DskipTests
```

### 启动与验证

```bash
java -jar 04-sentinel-degrade/target/04-sentinel-degrade-1.0.0.jar

# 触发 Sentinel 懒加载
curl http://localhost:7072/testSlowRate
curl "http://localhost:7072/testExceptionRate?id=1"
curl "http://localhost:7072/testException?id=1"
```

在 Sentinel Dashboard「降级规则」中配置后，使用 JMeter 压测：

| 接口 | 熔断策略 |
|------|----------|
| `/testSlowRate` | 慢调用比例 |
| `/testExceptionRate?id=0` | 异常比例 |
| `/testException?id=0` | 异常数 |

## 05 - Sentinel 规则持久化到 Nacos

代码位于 `05-sentinel-nacos-datasource` 模块，服务名 `sentinel-nacos-service`，端口 `7072`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 启动 Sentinel Dashboard（参考第 3 章）
3. 在 Nacos 配置管理 `DEFAULT_GROUP` 下创建 `sentinelFlowRule.json`（JSON 格式），内容见 `05-sentinel-nacos-datasource/config/sentinelFlowRule.json`

### 构建

```bash
mvn clean package -pl 05-sentinel-nacos-datasource -am -DskipTests
```

### 启动与验证

```bash
java -jar 05-sentinel-nacos-datasource/target/05-sentinel-nacos-datasource-1.0.0.jar

# 触发资源注册
curl http://localhost:7072/getUser
curl http://localhost:7072/getOrder
```

快速多次刷新 `/getUser` 或 `/getOrder`，QPS 超过 2 时应被限流。重启应用后规则仍从 Nacos 加载，不会丢失。

## 06 - Sentinel blockHandler 与 fallback 优雅返回

代码位于 `06-sentinel-block-fallback` 目录，包含 `lab-common`（公共模块）和 `sentinel-hotkey-service`（示例服务）。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 启动 Sentinel Dashboard（参考第 3 章）
3. 在 Nacos `DEFAULT_GROUP` 下创建 `hotKeyRule.json`，内容见 `06-sentinel-block-fallback/config/hotKeyRule.json`

### 构建

```bash
mvn clean package -pl 06-sentinel-block-fallback/sentinel-hotkey-service -am -DskipTests
```

### 启动与验证

```bash
java -jar 06-sentinel-block-fallback/sentinel-hotkey-service/target/sentinel-hotkey-service-1.0.0.jar

# 正常访问（无热点参数，不限流）
curl "http://localhost:7072/getProduct?categoryId=1"

# 带热点参数 userId，快速刷新验证限流
curl "http://localhost:7072/getProduct?userId=1001"

# 验证 fallback（userId 为负数触发业务异常）
curl "http://localhost:7072/getProduct?userId=-1"
```

限流后返回统一 JSON：`{"code":"B0002","message":"热点参数限流","data":null}`。

## 07 - OpenFeign 远程接口调用

代码位于 `07-open-feign` 目录，包含 `feign-provider`（服务提供者）和 `open-feign-service`（Feign 消费者）。

### 前置条件

启动 Nacos Server（默认 `127.0.0.1:8848`）。

### 构建

```bash
mvn clean package -pl 07-open-feign/feign-provider,07-open-feign/open-feign-service -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动提供者（8080，注册为 nacos-provider）
java -jar 07-open-feign/feign-provider/target/feign-provider-1.0.0.jar

# 2. 可选：再启动一个实例验证负载均衡（8081）
java -jar 07-open-feign/feign-provider/target/feign-provider-1.0.0.jar --server.port=8081

# 3. 启动 Feign 消费者（6061）
java -jar 07-open-feign/open-feign-service/target/open-feign-service-1.0.0.jar

# 4. 通过 Feign 远程调用
curl http://localhost:6061/product/3

# 5. 验证超时（readTimeout=1000ms，delay=3 应超时）
curl "http://localhost:6061/product/1?delay=3"
```

## 08 - OpenFeign 整合 Sentinel

代码位于 `08-feign-sentinel` 目录，包含 `feign-sentinel-common`、`feign-sentinel-provider`、`feign-sentinel-service`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 启动 Sentinel Dashboard（参考第 3 章）
3. 在 Nacos `DEFAULT_GROUP` 下创建 `sentinelFlowRule.json`，内容见 `08-feign-sentinel/config/sentinelFlowRule.json`

### 构建

```bash
mvn clean package -pl 08-feign-sentinel/feign-sentinel-service -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动消费者（6061）
java -jar 08-feign-sentinel/feign-sentinel-service/target/feign-sentinel-service-1.0.0.jar

# 2. 验证 Feign fallback（不启动 provider）
curl http://localhost:6061/product/1
# {"code":"C0001","message":"远程调用失败","data":null}

# 3. 启动提供者后再验证正常调用与 Sentinel 限流
java -jar 08-feign-sentinel/feign-sentinel-provider/target/feign-sentinel-provider-1.0.0.jar
curl http://localhost:6061/product/3
# 快速刷新触发限流：{"code":"C0002","message":"访问资源 getProduct 被限流","data":null}
```

## 09 - Spring Cloud Gateway 网关

代码位于 `09-gateway` 目录，包含 `gateway-user-service`、`gateway-order-service`、`gateway-service`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）

### 构建

```bash
mvn clean package -pl 09-gateway -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动用户服务（8001）
java -jar 09-gateway/gateway-user-service/target/gateway-user-service-1.0.0.jar

# 2. 可选：再启动一个 user-service 实例验证负载均衡（8002）
java -jar 09-gateway/gateway-user-service/target/gateway-user-service-1.0.0.jar --server.port=8002

# 3. 启动订单服务（8003）
java -jar 09-gateway/gateway-order-service/target/gateway-order-service-1.0.0.jar

# 4. 启动网关（8000）
java -jar 09-gateway/gateway-service/target/gateway-service-1.0.0.jar

# 5. 通过网关访问用户服务（含 X-Request-Home 请求头）
curl http://localhost:8000/user/info/1

# 6. 通过网关访问订单服务
curl http://localhost:8000/order/info/1

# 7. 多次请求验证 user-service 负载均衡（观察 serverPort 在 8001/8002 间切换）
curl http://localhost:8000/user/info/1
```

## 10 - Spring Cloud Gateway 网关限流

代码位于 `10-gateway-rate-limit` 目录，包含 `gateway-rate-limit-user-service`、`gateway-rate-limit-order-service`、`gateway-rate-limit-service`。

演示两种限流方式：

1. **RequestRateLimiter + Redis**：对 `order-service` 路由按 `userId` 参数限流
2. **Sentinel 网关流控**：对 `user-service` 路由限流，规则持久化到 Nacos（`gw-flow`）

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 启动 Redis（默认 `127.0.0.1:6379`）
3. 启动 Sentinel Dashboard（参考第 3 章，默认 `localhost:8080`）
4. 在 Nacos `DEFAULT_GROUP` 下创建 `myGatewayRule.json`，内容见 `10-gateway-rate-limit/config/myGatewayRule.json`

### 构建

```bash
mvn clean package -pl 10-gateway-rate-limit/gateway-rate-limit-service,10-gateway-rate-limit/gateway-rate-limit-user-service,10-gateway-rate-limit/gateway-rate-limit-order-service -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动用户服务（8001）
java -jar 10-gateway-rate-limit/gateway-rate-limit-user-service/target/gateway-rate-limit-user-service-1.0.0.jar

# 2. 启动订单服务（8003）
java -jar 10-gateway-rate-limit/gateway-rate-limit-order-service/target/gateway-rate-limit-order-service-1.0.0.jar

# 3. 启动网关（8000）
java -jar 10-gateway-rate-limit/gateway-rate-limit-service/target/gateway-rate-limit-service-1.0.0.jar

# 4. Redis 限流：order 路由必须带 userId 参数
curl "http://localhost:8000/order/info/2?userId=198276"

# 5. 快速刷新触发 Redis 令牌桶限流（HTTP 429 Too Many Requests）

# 6. Sentinel 限流：user 路由快速刷新
curl http://localhost:8000/user/info/1
# 触发限流后返回：{"code": 429, "message": "哥们，这瓜不熟，你走吧..."}
```

## 11 - Spring Cloud Sleuth 整合 Zipkin

代码位于 `11-sleuth-zipkin` 目录，包含 `sleuth-zipkin-common`、`sleuth-zipkin-gateway-service`、`sleuth-zipkin-order-service`、`sleuth-zipkin-user-service`、`sleuth-zipkin-product-service`、`sleuth-zipkin-loyalty-service`。

调用链路：`gateway-service` → `order-service` → `user-service` / `product-service` → `loyalty-service`

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）
2. 启动 Zipkin（默认 `127.0.0.1:9411`，可用 `11-sleuth-zipkin/config/docker-compose.yml`）

```bash
docker compose -f 11-sleuth-zipkin/config/docker-compose.yml up -d
```

### 构建

```bash
mvn clean package -pl 11-sleuth-zipkin/sleuth-zipkin-gateway-service,11-sleuth-zipkin/sleuth-zipkin-order-service,11-sleuth-zipkin/sleuth-zipkin-user-service,11-sleuth-zipkin/sleuth-zipkin-product-service,11-sleuth-zipkin/sleuth-zipkin-loyalty-service -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动后端服务
java -jar 11-sleuth-zipkin/sleuth-zipkin-loyalty-service/target/sleuth-zipkin-loyalty-service-1.0.0.jar
java -jar 11-sleuth-zipkin/sleuth-zipkin-product-service/target/sleuth-zipkin-product-service-1.0.0.jar
java -jar 11-sleuth-zipkin/sleuth-zipkin-user-service/target/sleuth-zipkin-user-service-1.0.0.jar
java -jar 11-sleuth-zipkin/sleuth-zipkin-order-service/target/sleuth-zipkin-order-service-1.0.0.jar

# 2. 启动网关（8000）
java -jar 11-sleuth-zipkin/sleuth-zipkin-gateway-service/target/sleuth-zipkin-gateway-service-1.0.0.jar

# 3. 通过网关下单
curl "http://localhost:8000/order/create?userId=1&productId=1"

# 4. 查看各服务日志中的 traceId/spanId，并在 Zipkin UI 查看链路
# http://127.0.0.1:9411/zipkin/
```

## 12 - Spring Boot Admin 监控

代码位于 `12-spring-boot-admin` 目录，包含 `boot-admin-server`、`boot-admin-demo-service`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）

### 构建

```bash
mvn clean package -pl 12-spring-boot-admin/boot-admin-server,12-spring-boot-admin/boot-admin-demo-service -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动 Admin Server（8082，默认账号 admin / admin123）
java -jar 12-spring-boot-admin/boot-admin-server/target/boot-admin-server-1.0.0.jar

# 2. 启动示例微服务（8083）
java -jar 12-spring-boot-admin/boot-admin-demo-service/target/boot-admin-demo-service-1.0.0.jar

# 3. 访问 Admin 控制台
# http://localhost:8082/  （登录 admin / admin123）

# 4. 查看示例服务 Actuator
curl http://localhost:8083/actuator/health
curl http://localhost:8083/demo/info
```

## 13 - 分布式事务与 Seata 简介

本章为理论介绍，配套代码演示**未使用 Seata 时**跨服务调用可能出现的数据不一致问题。可运行 Seata 解决方案见第 15 章。

代码位于 `13-seata-intro` 目录，包含 `seata-intro-order-service`、`seata-intro-warehouse-service`。

### 前置条件

1. 启动 Nacos Server（默认 `127.0.0.1:8848`）

### 构建

```bash
mvn clean package -pl 13-seata-intro/seata-intro-order-service,13-seata-intro/seata-intro-warehouse-service -am -DskipTests
```

### 启动与验证

```bash
# 1. 启动库存服务（8041）
java -jar 13-seata-intro/seata-intro-warehouse-service/target/seata-intro-warehouse-service-1.0.0.jar

# 2. 启动订单服务（8040）
java -jar 13-seata-intro/seata-intro-order-service/target/seata-intro-order-service-1.0.0.jar

# 3. 正常下单
curl "http://localhost:8040/order/create?productId=1&quantity=1"

# 4. 模拟库存扣减失败（本地订单已创建，库存未扣减，数据不一致）
curl "http://localhost:8040/order/create?productId=1&quantity=1&simulateFail=true"
curl http://localhost:8040/order/list
curl "http://localhost:8041/warehouse/stock?productId=1"
```

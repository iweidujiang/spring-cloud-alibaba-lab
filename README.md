# Spring Cloud Alibaba Lab

Spring Cloud Alibaba 系列文章配套代码，每篇文章对应一个独立模块。

| 目录 | 文章 |
|------|------|
| 01-architecture-evolution | 系统架构演进与 Spring Cloud Alibaba 简介 |
| 02-nacos | Nacos 实战及其客户端服务注册源码解析 |
| 03-sentinel-flow | Sentinel 限流配置实战 |
| 04-sentinel-degrade | Sentinel 熔断降级策略实战 |


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

# Spring Cloud Alibaba Lab

Spring Cloud Alibaba 系列文章配套代码，每篇文章对应一个独立模块。

| 目录 | 文章 |
|------|------|
| 01-architecture-evolution | 系统架构演进与 Spring Cloud Alibaba 简介 |
| 02-nacos | Nacos 实战及其客户端服务注册源码解析 |


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

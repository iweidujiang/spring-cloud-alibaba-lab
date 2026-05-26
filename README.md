# Spring Cloud Alibaba Lab

Spring Cloud Alibaba 系列文章配套代码，每篇文章对应一个独立模块。

| 目录 | 文章 |
|------|------|
| 01-architecture-evolution | 系统架构演进与 Spring Cloud Alibaba 简介 |

## 开发规范

- Java 类与方法需编写 JavaDoc，类注释包含 `@author 苏渡苇`
- 各章节模块不单独维护 README，说明统一写在本文档
- 详细规范见 `.cursor/rules/project-conventions.mdc`

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

# csc-cloud

#### 项目介绍
spring cloud 基础架构

#### 软件结构

├─doc  项目SQL语句
│
├─cloud-eureka 服务注册发现中心
│ 
│
├─cloud-zuul-gateway 服务网关(基于zuul组件,后续计划升级spring 5 gateway)
│ 
├─test-demo 测试主要组件使用demo (ribbon,feign,hystrix,redis,rabbitMQ)
│  



 **本地开发环境**

- JDK版本:1.8
- 开发工具：idea/eclipse
- 数据库：MySQL 5.7
- Tomcat版本：8.0 (tomcat开发环境可不安装 springboot 内置tomcat以jar包方式运行)
- Redis:3.0
- rabbitMQ:3.7.7

#### 使用说明
依次启动:CloudEruekaApplication,CloudZuulGatewayApplication,TestDemoApplication (run main方法)

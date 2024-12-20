# 项目介绍

企业级一站式聚合搜索平台，基于 Vue 3 前端 + Spring Boot 后端 + Elastic Stack 的 **全栈项目**。

对用户来说，使用该平台，可以在同一个页面集中搜索出不同来源、不同类型的内容，提升用户的检索效率和搜索体验。

对企业来说，当企业内部有多个项目的数据都存在搜索需求时，无需针对每个项目单独开发搜索功能，可以直接将各项目的数据源接入搜索中台，从而提升开发效率、降低系统维护成本。

![](yisou-backend\doc\example.png)

# 技术选型

## 前端

- Vue 3
- Ant Design Vue 组件库
- 页面状态同步机制

## 后端

- Spring Boot 2.7 框架
- MySQL 数据库
- Elastic Stack
  - Elasticsearch 搜索引擎
  - Logstash 数据管道
  - Kibana 数据可视化
- 数据抓取
  - 离线和实时抓取
  - Jsoup 和 HttpClient 库
- 设计模式
  - 门面模式
  - 适配器模式
  - 注册器模式
- 数据同步（4 种方式）
  - 定时
  - 双写
  - Logstash
  - Canal
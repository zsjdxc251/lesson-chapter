# Mybatis

[Mybatis Spring]: http://www.mybatis.org/spring/	"Mybatis-Spring整合"
[Mybatis 官文]: http://www.mybatis.org/mybatis-3/
[Mybatis Generator]: http://www.mybatis.org/generator/index.html

## Mybatis Quickstart

### 生命周期

| 关联类                     | Scope                  |
| -------------------------- | ---------------------- |
| `SqlSessionFactoryBuilder` | method                 |
| `SqlSessionFactory`        | application            |
| `SqlSession`               | method/request(线程级) |
| `Mapper`                   | method                 |

### 缓存

| 缓存基本 | 关联类       | 说明     |
| -------- | ------------ | -------- |
| 一级     | `SqlSession` | 默认打开 |
| 二级     | `Mapper`     | 默认关闭 |


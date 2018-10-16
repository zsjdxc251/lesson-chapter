# Spring IOC

## FactoryBean 和 BeanFactory

* `BeanFactory` 主语是`Factory`顶级接口

  具体工程会通过策略模式去实现，不同的工厂生产不同的产品

* `FactoryBean` 主语是`Bean`，所有的工厂生产的产品都要实现该此接口。`BeanFactory`生产出的产品都是`FactoryBean`

  **大部分情况下`BeanFactory`本身也是通过`BeanFactory`创建的也就是说`BeanFactory`也是一个`FactoryBean`**


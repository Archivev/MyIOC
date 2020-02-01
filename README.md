# MyIOC
实现一个简单的 Spring IOC 容器
## 容器要实现的功能

- 可以正确的解析xml文件，并将对象的实例创建出来
- 借助 BeanUtils 可以将属性值注入到对象实例中
- 可以设置 Scope 作用域为 singleton（单例模式）、prototype（多例模式）
- 可以实现对象类型的注入 ref

## 所需技术

- dom4j 用来解析 xml 配置文件
- BeanUtils 用来帮助我们对对象注入属性
- Java反射机制

## 所需 jar 包：

- BeanUtils工具类
- dom4j解析

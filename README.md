### 实现的功能

- 登录
- 注册
- goods表CRUD
- 收藏

## 项目缺点

基本上没有任何表单提示，而且也没有错误操作跳转等。  
所以请按照数据库数据类型来认真填写。

可以设置cookie，但是没有去设置，首先是这个功能比较鸡肋，其次我比较懒。

filter放行的条件不够好，应该有别的方法可以优化，但我不知道。

jstl按道理应该可以用ajax替换，但是个人对ajax学习的也不是很精通，仔细想想传值也比较复杂，就作罢。

## 答辩总结

- 缺少前台展示
- 可以使用layUI帮助我们开发
- 答辩语言尽量简洁

## 如果想使用该项目，请看下面

### 环境

- OpenJDK11(在兼容性允许的情况下可以选择OpenJDK11以上)
- MariaDB
- Tomcat9

### 框架及目录结构

#### 框架

MVC

#### src目录结构

- entity：即domain，JavaBean的封装类，用于数据传输
- dao：jdbc链接
  - impl：dao接口的实现类
- biz：即service，调用jdbc方法
  - impl：biz接口的实现类
- web
  - servlet：调用biz
  - filter：拦截所有没有登陆的请求
- test：junit4单元测试，删除不影响整个项目运作
- util：工具类，在程序运行过程中需要的功能独立单一的方法

#### web目录结构

不重要，略过

### 数据库创建

```sql

create schema keshe;

use keshe;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET latin1 NOT NULL,
  `password` varchar(30) CHARACTER SET latin1 NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `emai` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `intro` varchar(50) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `users_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE,
  KEY `users_id` (`users_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `carts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `users_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

```

## 项目所使用的技术

- java
- jsp
- jdbc
- js的简单表单验证
- jq实现的ajax：用于注册判断用户名是否可用
- servlet：能为jsp文件分担部分java代码
- jstl&el表达式：主要用于解决jsp代码嵌入问题（然并卵，技术很老了）
- filter：过滤器
- 数据库外键、join使用



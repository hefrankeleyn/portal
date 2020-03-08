# 在linux系统上安装mysql

[toc]



## （1）第一步： 安装mysql客户端` yum install mysql`



（2）第二步：安装mysql 服务器端`yum install mysql-server`

遇到下面的问题：

```shell
[root@VM_16_9_centos ~]# yum install mysql-server
已加载插件：fastestmirror, langpacks
Loading mirror speeds from cached hostfile
没有可用软件包 mysql-server。
错误：无须任何处理
```

解决该问题：

- 安装`wget`命令：

`yum -y install wget`

- 下载mysql的repo源：

  `wget http://repo.mysql.com/mysql-community-release-el7-5.noarch.rpm `

- 下载`mysql-community-release-el7-5.noarch.rpm `包：

  ```shell
  rpm -ivh mysql-community-release-el7-5.noarch.rpm
  ```

- 安装mysql

  ```shell
  yum install mysql-server
  ```

安装`mysql-server`的时候，出现的提示：

```
警告：/var/cache/yum/x86_64/7/mysql56-community/packages/mysql-community-common-5.6.47-2.el7.x86_64.rpm: 头V3 DSA/SHA1 Signature, 密钥 ID 5072e1f5: NOKEY               ]  65 kB/s | 229 kB  00:33:56 ETA 
mysql-community-common-5.6.47-2.el7.x86_64.rpm 的公钥尚未安装

```

（3） 初始化mysql

```mysql
mysqld --initialize
```

（4） 启动mysql

```mysql
systemctl start mysqld
```

（5） 查看mysql的状态

```
systemctl status mysqld
```

（6）默认情况下 MySQL 服务器的登录密码为空

因此可以直接登录

```mysql
[root@VM_16_9_centos ~]# mysql
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 2
Server version: 5.6.47 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> 
```

（7） 创建root用户的密码：

```mysql
mysqladmin -u root password "worldpass";
```

（8） 通过下面的命令来登录：

```mysql
[root@VM_16_9_centos ~]# mysql -uroot -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 5
Server version: 5.6.47 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
+--------------------+
3 rows in set (0.00 sec)

mysql> 
```




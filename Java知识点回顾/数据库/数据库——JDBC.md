# JDBC

java程序访问数据库的基本方式就是通过JDBC.JDBC（java DataBase Connectivity,java数据库连接）技术的简称，是一种用于执行SQL语句的Java API。它是由一组用Java编程语言编写的类和接口组成。这个API由`java.sql.*,java.sql.*`包中的一些类和接口组成，它为数据库开发人员提供了一个标准的API,使它们能够用纯Java API来编写数据库的应用程序。



### JDBC原理

**JDBC为多种关系型数据库提供了统一的访问方式**，作为特定的厂商数据库访问API的一种高级抽象，它主要包含一些通用的接口类。真正的数据库访问操作实现是由各自数据库厂商提供的。通常把厂商提供的特定的用于数据库访问的API称为数据库的JDBC驱动程序。

JDBC访问数据库层次结构：

![1564309363766](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1564309363766.png)

JDBC的优势

- **Java语言访问数据库操作完全面向抽象接口编程。**
- **开发数据库应用不用限定在特定的数据库厂商的API**
- **程序可移植性大大提升**

### JDBC流程

![1564309476012](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1564309476012.png)

- 准备数据库驱动包/或者获取数据源
- 加载JDBC驱动程序

```java
Class.forName("com.mysql.jdbc.Driver");
```

- 建立数据连接

```mysql
Connection connection  = 
DriverManager.getConnection("jdbc:mysql://localhost:3306/memo? user=root&password=root&useUnicode=true&characterEncoding=UTF-8");
```

```
//Mysql数据库连接的URL参数格式如下：
jdbc:mysql://服务器地址：端口号/数据库名？参数名 = 参数值
```

- 创建操作命令(statement / PerparedStatementS)

```java
Statement statement = connection.createStatement();
```

- 执行SQL语句

```java
ResultSet resultSet = statement.executeQuery(
	 "select id,group_id,title,content,is_protected, background,is_remind,remind_time,created_time,modify_time from memo_info");
```

- 处理结果集

```java
while (resultSet.next()) {    
    int id = resultSet.getInt("id");    
    String title = resultSet.getString("title");    
    String content = resultSet.getString("content");    
    Date createTime = resultSet.getDate("created_time");    System.out.println(String.format("Memo: id=%d, title=%s, content=%s, createTime=%s", id, title, content, createTime.toString())); }
```

- 释放关闭资源（结果集，命令，连接）

```java
//关闭命令
if (resultSet != null) {    
	try {        
	resultSet.close();    
	} catch (SQLException e) {        
	e.printStackTrace();    
	} 
}
//关闭命令
if (statement != null) {    
	try {        
	statement.close();    
	} catch (
	SQLException e) {        
	e.printStackTrace();    } 
}
//关闭连接命令
if(connection != null) {
	try{
		connection.close();
	} catch(SQLException e) {
		e.printStackTrace();
	}
}
```



### Statement对象 执行命令对象

![1564310184691](C:\Users\32183\AppData\Roaming\Typora\typora-user-images\1564310184691.png)

### PreparedStatement优点

1. 参数化SQL查询
2. 占位符不能直接使用多值
3. 占位符 ： ？下标从1开始
4. 阻止常见SQL注入攻击
5. **SQL预编译**
6. 性能比Statement高

主要掌握两种常见的SQL执行方法

- executeQuery:返回执行后的的单个结果集，**select**
- executeUpdate**方法返回值是一个整数**，指示受影响的行数，通常使用update,insert,delete语句
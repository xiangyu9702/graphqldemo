package com.example.web;
/*
数据库信息
 */
public class Database {
    // JDBC 驱动名及数据库 URL
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/college?serverTimezone=GMT&useSSL=false";

    // 数据库的用户名与密码，需要根据自己的设置
    public static final String USER = "root";
    public static final String PASS = "password";
}

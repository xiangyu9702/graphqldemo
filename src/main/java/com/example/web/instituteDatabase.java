package com.example.web;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class instituteDatabase {
    public static ArrayList getInstitute() {
        ArrayList<Institute> institutes = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT instituteId,instituteName,numberOfMajor FROM institute ";
            ResultSet instituteRs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (instituteRs.next()) {
                long instituteId = instituteRs.getLong("instituteId");
                String instituteName = instituteRs.getString("instituteName");
                int numberOfMajor = instituteRs.getInt("numberOfMajor");
                Institute i = new Institute();
                i.setInstituteName(instituteName);
                i.setInstituteId((instituteId));
                i.setNumberOfMajor(numberOfMajor);
                i.setMajors(new ArrayList<>());
                institutes.add(i);
            }
            sql = "SELECT majorId,majorName,instituteId,numberOfStudent FROM major ";
            ResultSet majorRs = stmt.executeQuery(sql);
            while (majorRs.next()) {
                long majorId = majorRs.getLong("majorId");
                String majorName = majorRs.getString("majorName");
                long instituteId=majorRs.getLong("instituteId");
                int numberOfStudent = majorRs.getInt("numberOfStudent");
                Major m=new Major();
                m.setInstituteIdOfMajor(instituteId);
                m.setMajorId(majorId);
                m.setMajorName(majorName);
                m.setNumberOfStudent(numberOfStudent);
                for (Institute institute:institutes){
                    if (institute.getInstituteId()==m.getInstituteIdOfMajor()){
                        institute.getMajors().add(m);
                    }
                }
            }
            instituteRs.close();
            majorRs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return institutes;
    }
    public static Map<Long,Institute> getInstituteMap() {
        Map<Long,Institute> instituteMap=new HashMap<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT instituteId,instituteName,numberOfMajor FROM institute";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                long id = rs.getLong("instituteId");
                String name = rs.getString("instituteName");
                int age = rs.getInt("numberOfMajor");
                Institute u = new Institute();
                u.setInstituteName(name);
                u.setInstituteId((id));
                u.setNumberOfMajor(age);
                instituteMap.put(id,u);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return instituteMap;
    }

    public static void saveInstitute(Institute institute) {
        Map<Long,Institute> userMap=new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            String sql;
            sql = "insert into institute values (?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, institute.getInstituteId());
            stmt.setString(2, institute.getInstituteName());
            stmt.setInt(3, institute.getNumberOfMajor());
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void deleteInstitute(Long id) {
        Map<Long,Institute> userMap=new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            String sql;
            sql = "delete from institute where instituteId=?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

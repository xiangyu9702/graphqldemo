package com.example.web.Institute;

import com.example.web.Database;
import com.example.web.course.Course;
import com.example.web.major.Major;
import com.example.web.major.MajorDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InstituteDatabase {
    public static Map<Long,Institute> getInstituteMap() {
        Map<Long,Institute> instituteMap=new HashMap<>();
        Map<Long,Major> majorMap=new HashMap<>();
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
                i.setMajorArrayList(new ArrayList<>());
                instituteMap.put(instituteId,i);
            }
            sql = "SELECT majorId,instituteId FROM major";
            ResultSet majorRs = stmt.executeQuery(sql);
            while (majorRs.next()) {
                long majorId = majorRs.getLong("majorId");
                long instituteId = majorRs.getLong("instituteId");
                instituteMap.get(instituteId).getMajorArrayList().add(MajorDatabase.getMajorMap().get(majorId));
            }
            majorRs.close();
            instituteRs.close();
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
        return instituteMap;
    }

    public static void saveInstitute(Institute Institute) {
        Map<Long, Institute> userMap=new HashMap<>();
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
            stmt.setLong(1, Institute.getInstituteId());
            stmt.setString(2, Institute.getInstituteName());
            stmt.setInt(3, Institute.getNumberOfMajor());
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

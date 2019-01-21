package com.example.web.teacher;

import com.example.web.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeacherDatabase {
    public static Map getTeacherMap() {
        Map<Long,Teacher> teacherMap=new HashMap<>();
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
            sql = "SELECT teacherId,teacherName,teacherSex,teacher.instituteId,instituteName from teacher inner join institute where teacher.instituteId=institute.instituteId";
            ResultSet teacherRs = stmt.executeQuery(sql);
            while (teacherRs.next()) {
                long teacherId = teacherRs.getLong("teacherId");
                String teacherName = teacherRs.getString("teacherName");
                long instituteId = teacherRs.getLong("instituteId");
                String teacherSex=teacherRs.getString("teacherSex");
                String instituteName=teacherRs.getString("instituteName");
                Teacher teacher=new Teacher();
                teacher.setTeacherId(teacherId);
                teacher.setTeacherName(teacherName);
                teacher.setTeacherSex(teacherSex);
                teacher.setInstituteId(instituteId);
                teacher.setInstituteName(instituteName);
                teacherMap.put(teacherId,teacher);
            }
            teacherRs.close();
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
        return teacherMap;
    }

    public static void deleteTeacher(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            String sql;
            sql = "delete from teacher where teacherId=?";
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
    public static void saveTeacher(Teacher teacher) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            String sql;
            sql = "insert into teacher values (?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, teacher.getTeacherId());
            stmt.setString(2, teacher.getTeacherName());
            stmt.setString(3, teacher.getTeacherSex());
            stmt.setLong(4, teacher.getInstituteId());
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
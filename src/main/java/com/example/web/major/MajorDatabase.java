package com.example.web.major;

import com.example.web.Database;
import com.example.web.course.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MajorDatabase {
    public static Map<Long,Major> getMajorMap() {
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
            sql = "SELECT majorId,majorName,major.instituteId,instituteName FROM major inner join institute where major.instituteId=institute.instituteId";
            ResultSet majorRs = stmt.executeQuery(sql);
            while (majorRs.next()) {
                long majorId = majorRs.getLong("majorId");
                String majorName = majorRs.getString("majorName");
                long instituteId = majorRs.getLong("instituteId");
                String instituteName =majorRs.getString("instituteName");
                Major m = new Major();
                m.setInstituteId(instituteId);
                m.setMajorId(majorId);
                m.setMajorName(majorName);
                m.setInstituteName(instituteName);
                m.setCourseArrayList(new ArrayList<>());
                majorMap.put(majorId,m);
            }
            sql = "SELECT courseId,courseName,majorId FROM course ";
            ResultSet courserRs = stmt.executeQuery(sql);
            while (courserRs.next()) {
                long courseId = courserRs.getLong("courseId");
                String courseName = courserRs.getString("courseName");
                long majorId = courserRs.getLong("majorId");
                Course course=new Course();
                course.setCourseId(courseId);
                course.setMajorId(majorId);
                course.setCourseName(courseName);
                course.setMajorName(majorMap.get(majorId).getMajorName());
                majorMap.get(majorId).getCourseArrayList().add(course);
            }
            majorRs.close();
            courserRs.close();
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
        return majorMap;
    }

}

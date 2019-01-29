package com.example.web.publicMethod;

import com.example.web.Database;
import java.sql.*;
import java.util.ArrayList;
/*
得到有关课程的详细信息
 */
public class publicMethod {
    public static ArrayList<String> courseDetails() {
        ArrayList<String> details = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT course.courseId,courseName,course.majorId,majorName,teacher.teacherId,teacherName," +
                    "studentId,studentName from course inner join student,major,teacher " +
                    "where course.majorId=student.majorId and course.majorId=major.majorId " +
                    "and teacher.teacherId=course.teacherId";
            ResultSet courseRs = stmt.executeQuery(sql);
            while (courseRs.next()) {
                long coursetId = courseRs.getLong("courseId");
                String courseName = courseRs.getString("courseName");
                Integer teacherId = courseRs.getInt("teacherId");
                String teacherName = courseRs.getString("teacherName");
                Integer majorId = courseRs.getInt("majorId");
                String majorName = courseRs.getString("majorName");
                Integer studentId = courseRs.getInt("studentId");
                String studentName = courseRs.getString("studentName");
                String s = "课程编号:" + coursetId + "," + "    "
                        + "课程名称：" + courseName + "," +"    "
                        + "教师编号：" + teacherId + "," + "    "
                        + "教师名称：" + teacherName + "," + "    "
                        + "专业编号：" + majorId + "," + "    "
                        + "专业名称：" + majorName + "," + "    "
                        + "学生编号：" + studentId + "," + "    "
                        + "学生名称：" + studentName;
                details.add(s);
                s="";
                details.add(s);
            }
            courseRs.close();
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
        return details;
    }
}

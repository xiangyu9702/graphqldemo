package com.example.web.Service;

import com.example.web.Institute.Institute;
import com.example.web.Institute.InstituteDatabase;
import com.example.web.course.Course;
import com.example.web.course.CourseDatabase;
import com.example.web.major.Major;
import com.example.web.major.MajorDatabase;
import com.example.web.student.Student;
import com.example.web.student.StudentDatabase;
import com.example.web.teacher.Teacher;
import com.example.web.teacher.TeacherDatabase;
import java.util.Map;

public class getMap {
  public static Map<Long, Institute> instituteMap = InstituteDatabase.getInstituteMap();
  public static Map<Long, Major> majorMap = MajorDatabase.getMajorMap();
  public static Map<Long, Course> courseMap = CourseDatabase.getCourseMap();
  public static Map<Long, Student> studentMap = StudentDatabase.getStudentMap();
  public static Map<Long, Teacher> teacherMap = TeacherDatabase.getTeacherMap();

}

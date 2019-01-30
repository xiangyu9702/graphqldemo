package com.example.web.Service;

import com.example.web.Institute.Institute;
import com.example.web.Institute.InstituteDatabase;
import com.example.web.course.Course;
import com.example.web.course.CourseDatabase;
import com.example.web.major.Major;
import com.example.web.major.MajorDatabase;
import com.example.web.publicMethod.publicMethod;
import com.example.web.student.Student;
import com.example.web.student.StudentDatabase;
import com.example.web.teacher.Teacher;
import com.example.web.teacher.TeacherDatabase;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  /*
  Queries for students.
   */
  @GraphQLQuery(name = "students")//获取学生信息
  public Collection<Student> getStudents() {
    return getMap.studentMap.values();
  }

  @GraphQLQuery(name = "student")//根据Id获取学生信息
  public Student getStudentById(@GraphQLArgument(name = "studentId") Long id) {
    return getMap.studentMap.get(id);
  }

  @GraphQLMutation(name = "saveStudent")//保存学生信息
  public String saveStudent(@GraphQLArgument(name = "studentId") Long studentID,
      @GraphQLArgument(name = "studentName") String studentName,
      @GraphQLArgument(name = "studentSex") String studentSex,
      @GraphQLArgument(name = "majorId") Long majorId) {
    Student s = new Student();
    s.setStudentName(studentName);
    s.setMajorId(majorId);
    s.setStudentId(studentID);
    s.setStudentSex(studentSex);
    s.setMajorName(getMap.majorMap.get(majorId).getMajorName());
    getMap.studentMap.put(s.getStudentId(), s);
    return StudentDatabase.saveStudent(s);
  }

  @GraphQLMutation(name = "updateStudent")//保存学生信息
  public String updateStudent(@GraphQLArgument(name = "studentId") Long studentID,
      @GraphQLArgument(name = "studentName") String studentName,
      @GraphQLArgument(name = "studentSex") String studentSex,
      @GraphQLArgument(name = "majorId") Long majorId) {
    try {
      getMap.studentMap.get(studentID).setStudentName(studentName);
      getMap.studentMap.get(studentID).setMajorId(majorId);
      getMap.studentMap.get(studentID).setStudentId(studentID);
      getMap.studentMap.get(studentID).setStudentSex(studentSex);
      getMap.studentMap.get(studentID).setMajorName(getMap.majorMap.get(majorId).getMajorName());
    } catch (Exception e) {
      return "更新信息失败，请检查是否存在该id";
    }
    return StudentDatabase.updateStudent(studentID, getMap.studentMap.get(studentID));
  }

  @GraphQLMutation(name = "deleteStudent")//删除学生信息
  public String deleteStudent(@GraphQLArgument(name = "studentId") Long id) {
    getMap.studentMap.remove(id);
    return StudentDatabase.deleteStudent(id);
  }


}

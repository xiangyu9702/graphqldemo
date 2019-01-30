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
public class TeacherService {


  /*
  Queries for teachers.
   */
  @GraphQLQuery(name = "teachers")//获取教师信息
  public Collection<Teacher> getTeacher() {
    return getMap.teacherMap.values();
  }

  @GraphQLQuery(name = "teacher")//根据Id获取教师信息
  public Teacher getTeacherById(@GraphQLArgument(name = "teacherId") Long id) {
    return getMap.teacherMap.get(id);
  }

  @GraphQLMutation(name = "updateTeacher")//更新教师信息
  public String saveTeacher(@GraphQLArgument(name = "teacherId") Long teacherId,
      @GraphQLArgument(name = "teacherName") String teacherName,
      @GraphQLArgument(name = "teacherSex") String teacherSex,
      @GraphQLArgument(name = "instituteId") Long instituteId) {
    try {
      getMap.teacherMap.get(teacherId).setTeacherId(teacherId);
      getMap.teacherMap.get(teacherId).setTeacherName(teacherName);
      getMap.teacherMap.get(teacherId).setTeacherSex(teacherSex);
      getMap.teacherMap.get(teacherId).setInstituteId(instituteId);
      getMap.teacherMap.get(teacherId).setInstituteName(getMap.instituteMap.get(instituteId).getInstituteName());
    } catch (Exception e) {
      return "更新信息失败，请检查是否存在该id";
    }
    return TeacherDatabase.updateTeacher(teacherId, getMap.teacherMap.get(teacherId));
  }

  @GraphQLMutation(name = "saveTeacher")//保存教师信息
  public String updateTeacher(@GraphQLArgument(name = "teacherId") Long teacherId,
      @GraphQLArgument(name = "teacherName") String teacherName,
      @GraphQLArgument(name = "teacherSex") String teacherSex,
      @GraphQLArgument(name = "instituteId") Long instituteId) {
    Teacher teacher = new Teacher();
    teacher.setTeacherId(teacherId);
    teacher.setTeacherName(teacherName);
    teacher.setTeacherSex(teacherSex);
    teacher.setInstituteId(instituteId);
    teacher.setInstituteName(getMap.instituteMap.get(instituteId).getInstituteName());
    getMap.teacherMap.put(teacher.getTeacherId(), teacher);
    return TeacherDatabase.saveTeacher(teacher);
  }

  @GraphQLMutation(name = "deleteTeacher")//删除教师信息
  public String deleteTeacher(@GraphQLArgument(name = "teacherId") Long id) {
    getMap.teacherMap.remove(id);
    return TeacherDatabase.deleteTeacher(id);
  }



}

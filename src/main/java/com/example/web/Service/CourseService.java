package com.example.web.Service;


import com.example.web.course.Course;
import com.example.web.course.CourseDatabase;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  /*
  Queries for courses.
   */
  @GraphQLQuery(name = "courses")//获取课程信息
  public Collection<Course> getCourses() {
    return getMap.courseMap.values();
  }

  @GraphQLQuery(name = "course")//根据Id获取课程信息
  public Course getCourseById(@GraphQLArgument(name = "courseId") Long id) {
    return getMap.courseMap.get(id);
  }

  @GraphQLMutation(name = "saveCourse")//保存课程信息
  public String saveCourse(@GraphQLArgument(name = "courseId") Long courseID,
      @GraphQLArgument(name = "courseName") String courseName,
      @GraphQLArgument(name = "majorId") Long majorId,
      @GraphQLArgument(name = "teacherId") Long teacherId) {
    Course c = new Course();
    c.setCourseId(courseID);
    c.setCourseName(courseName);
    c.setMajorId(majorId);
    c.setTeacherId(teacherId);
    c.setTeacherName(getMap.teacherMap.get(teacherId).getTeacherName());
    c.setMajorName(getMap.majorMap.get(majorId).getMajorName());
    getMap.courseMap.put(c.getCourseId(), c);
    return CourseDatabase.saveCourse(c);
  }

  @GraphQLMutation(name = "updateCourse")//更新课程信息
  public String updateCourse(@GraphQLArgument(name = "courseId") Long courseID,
      @GraphQLArgument(name = "courseName") String courseName,
      @GraphQLArgument(name = "majorId") Long majorId,
      @GraphQLArgument(name = "teacherId") Long teacherId) {
    try {
      getMap.courseMap.get(courseID).setTeacherName(getMap.teacherMap.get(teacherId).getTeacherName());
      getMap.courseMap.get(courseID).setCourseName(courseName);
      getMap.courseMap.get(courseID).setMajorId(majorId);
      getMap.courseMap.get(courseID).setTeacherId(teacherId);
      getMap.courseMap.get(courseID).setMajorName(getMap.majorMap.get(majorId).getMajorName());
    } catch (Exception e) {
      return "更新信息失败，请检查是否存在该id";
    }
    return CourseDatabase.updateCourse(courseID, getMap.courseMap.get(courseID));
  }

  @GraphQLMutation(name = "deleteCourse")//删除课程信息
  public String deleteCourse(@GraphQLArgument(name = "courseId") Long id) {
    getMap.courseMap.remove(id);
    return CourseDatabase.deleteCourse(id);
  }

}

package com.example.web;

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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollegeService {

    private Map<Long,Institute> instituteMap =new HashMap<>();
    private Map<Long,Major> majorMap =new HashMap<>();
    private Map<Long,Course> courseMap=new HashMap<>();
    private Map<Long,Student> studentMap=new HashMap<>();
    private Map<Long,Teacher> teacherMap=new HashMap<>();

    public CollegeService() {
        this.majorMap =MajorDatabase.getMajorMap();
        this.instituteMap = InstituteDatabase.getInstituteMap();
        this.courseMap=CourseDatabase.getCourseMap();
        this.studentMap=StudentDatabase.getStudentMap();
        this.teacherMap=TeacherDatabase.getTeacherMap();
    }
/*
Queries for institutes
 */

    @GraphQLQuery(name = "institutes")
    public Collection<Institute> getInstitutes() {
        return InstituteDatabase.getInstituteMap().values();
    }
    @GraphQLQuery(name = "Institute")
    public Institute getInstituteById(@GraphQLArgument(name = "instituteId") Long id) {
        return instituteMap.get(id);
    }

    @GraphQLMutation(name = "saveInstitute")
    public Institute saveInstitute(@GraphQLArgument(name = "Institute") Institute Institute) {
        InstituteDatabase.saveInstitute(Institute);
        instituteMap.put(Institute.getInstituteId(), Institute);
        return Institute;
    }

    @GraphQLMutation(name = "deleteInstitute")
    public String deleteInstitute(@GraphQLArgument(name = "instituteId") Long id) {
        InstituteDatabase.deleteInstitute(id);
        instituteMap.remove(id);
        return "删除成功";
    }


/*
Queries for majors.
 */

    @GraphQLQuery(name = "majors")
    public Collection<Major> getMajors() {
        return MajorDatabase.getMajorMap().values();
    }
    @GraphQLQuery(name = "major")
    public Major getMajorById(@GraphQLArgument(name = "majorId") Long id) {
        return majorMap.get(id);
    }


    /*
    Queries for students.
     */
    @GraphQLQuery(name = "students")
    public Collection<Student> getStudents() {
        return StudentDatabase.getStudentMap().values();
    }
    @GraphQLQuery(name = "student")
    public Student getStudentById(@GraphQLArgument(name = "studentId") Long id) {
        return studentMap.get(id);
    }
    @GraphQLMutation(name = "saveStudent")
    public String saveStudent(@GraphQLArgument(name = "Student") Student student) {
        StudentDatabase.saveStudent(student);
        studentMap.put(student.getStudentId(),student);
        return "保存成功";
    }
    @GraphQLMutation(name ="deleteStudent")
    public void deleteStudent(@GraphQLArgument(name ="studentId") Long id){
        StudentDatabase.deleteStudent(id);
        studentMap.remove(id);}
/*
Queries for courses.
 */
    @GraphQLQuery(name = "courses")
    public Collection<Course> getCourses() {
    return CourseDatabase.getCourseMap().values();
}
    @GraphQLQuery(name = "course")
    public Course getCourseById(@GraphQLArgument(name = "courseId") Long id) {
        return courseMap.get(id);
    }

    /*
    Queries for teachers.
     */
    @GraphQLQuery(name = "teachers")
    public Collection<Teacher> getTeacher(){
        return teacherMap.values();}
    @GraphQLQuery(name ="teacher")
    public Teacher getTeacherById(@GraphQLArgument(name ="teacherId") Long id){return teacherMap.get(id);}
    @GraphQLMutation(name = "saveTeacher")
    public String saveTeacher(@GraphQLArgument(name = "Teacher") Teacher teacher) {
        TeacherDatabase.saveTeacher(teacher);
        teacherMap.put(teacher.getTeacherId(),teacher);
        return "保存成功";
    }
    @GraphQLMutation(name ="deleteTeacher")
    public void deleteTeacher(@GraphQLArgument(name ="teacherId") Long id){
        TeacherDatabase.deleteTeacher(id);
    teacherMap.remove(id);}


/*
Queries for pubic
 */



}



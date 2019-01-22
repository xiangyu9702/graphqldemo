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

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
public class CollegeService {

    private Map<Long, Institute> instituteMap = new HashMap<>();
    private Map<Long, Major> majorMap = new HashMap<>();
    private Map<Long, Course> courseMap = new HashMap<>();
    private Map<Long, Student> studentMap = new HashMap<>();
    private Map<Long, Teacher> teacherMap = new HashMap<>();

    public CollegeService() {
        this.majorMap = MajorDatabase.getMajorMap();
        this.instituteMap = InstituteDatabase.getInstituteMap();
        this.courseMap = CourseDatabase.getCourseMap();
        this.studentMap = StudentDatabase.getStudentMap();
        this.teacherMap = TeacherDatabase.getTeacherMap();
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
    public String saveInstitute(@GraphQLArgument(name = "institutedId") Long institutedId,
                                @GraphQLArgument(name = "instituteName") String instituteName,
                                @GraphQLArgument(name = "numberOfMajor") int numberOfMajor) {
        Institute institute = new Institute();
        institute.setInstituteId(institutedId);
        institute.setInstituteName(instituteName);
        institute.setNumberOfMajor(numberOfMajor);
        instituteMap.put(institutedId, institute);
        return InstituteDatabase.saveInstitute(institute);
    }

    @GraphQLMutation(name = "deleteInstitute")
    public String deleteInstitute(@GraphQLArgument(name = "instituteId") Long id) {
        instituteMap.remove(id);
        return InstituteDatabase.deleteInstitute(id);
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

    @GraphQLMutation(name = "saveMajor")
    public String saveMajor(@GraphQLArgument(name = "majorId") Long majorId,
                            @GraphQLArgument(name = "majorName") String majorName,
                            @GraphQLArgument(name = "institutedId") Long institutedId
    ) {
        Major major = new Major();
        major.setMajorId(majorId);
        major.setMajorName(majorName);
        major.setInstituteId(institutedId);
        major.setInstituteName(instituteMap.get(institutedId).getInstituteName());
        majorMap.put(major.getMajorId(), major);
        return MajorDatabase.saveMajor(major);
    }

    @GraphQLMutation(name = "deleteMajor")
    public String deleteMajor(@GraphQLArgument(name = "majorId") Long id) {
        majorMap.remove(id);
        return MajorDatabase.deleteMajor(id);
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
    public String saveStudent(@GraphQLArgument(name = "studentId") Long studentID,
                              @GraphQLArgument(name = "studentName") String studentName,
                              @GraphQLArgument(name = "studentSex") String studentSex,
                              @GraphQLArgument(name = "majorId") Long majorId) {
        Student s = new Student();
        s.setStudentName(studentName);
        s.setMajorId(majorId);
        s.setStudentId(studentID);
        s.setStudentSex(studentSex);
        s.setMajorName(majorMap.get(majorId).getMajorName());
        studentMap.put(s.getStudentId(), s);
        return StudentDatabase.saveStudent(s);
    }

    @GraphQLMutation(name = "deleteStudent")
    public String deleteStudent(@GraphQLArgument(name = "studentId") Long id) {
        studentMap.remove(id);
        return StudentDatabase.deleteStudent(id);
    }


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

    @GraphQLMutation(name = "saveCourse")
    public String saveCourse(@GraphQLArgument(name = "courseId") Long courseID,
                             @GraphQLArgument(name = "studentName") String courseName,
                             @GraphQLArgument(name = "majorId") Long majorId,
    @GraphQLArgument(name="teacherId") Long teacherId) {
        Course c = new Course();
        c.setCourseId(courseID);
        c.setCourseName(courseName);
        c.setMajorId(majorId);
        c.setTeacherId(teacherId);
        c.setTeacherName(teacherMap.get(teacherId).getTeacherName());
        c.setMajorName(majorMap.get(majorId).getMajorName());
        courseMap.put(c.getCourseId(), c);
        return CourseDatabase.saveCourse(c);
    }

    @GraphQLMutation(name = "deleteCourse")
    public String deleteCourse(@GraphQLArgument(name = "courseId") Long id) {
        courseMap.remove(id);
        return CourseDatabase.deleteCourse(id);
    }


    /*
    Queries for teachers.
     */
    @GraphQLQuery(name = "teachers")
    public Collection<Teacher> getTeacher() {
        return teacherMap.values();
    }

    @GraphQLQuery(name = "teacher")
    public Teacher getTeacherById(@GraphQLArgument(name = "teacherId") Long id) {
        return teacherMap.get(id);
    }

    @GraphQLMutation(name = "saveTeacher")
    public String saveTeacher(@GraphQLArgument(name = "teacherId") Long teacherId,
                              @GraphQLArgument(name = "teacherName") String teacherName,
                              @GraphQLArgument(name = "teacherSex") String teacherSex,
                              @GraphQLArgument(name = "instituteId") Long instituteId) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setTeacherSex(teacherSex);
        teacher.setInstituteId(instituteId);
        teacher.setInstituteName(instituteMap.get(instituteId).getInstituteName());
        teacherMap.put(teacher.getTeacherId(), teacher);
        return TeacherDatabase.saveTeacher(teacher);
    }

    @GraphQLMutation(name = "deleteTeacher")
    public String deleteTeacher(@GraphQLArgument(name = "teacherId") Long id) {
        teacherMap.remove(id);
        return TeacherDatabase.deleteTeacher(id);
    }


/*
Queries for pubic
 */
@GraphQLQuery(name = "courseDetails")
public ArrayList<String > getdetails() {
    return publicMethod.courseDetails();
}

}



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
import org.springframework.stereotype.Service;

import java.util.*;

/*
Graphql的Query和Mutation
 */

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

    @GraphQLQuery(name = "institutes")//获取学院信息
    public Collection<Institute> getInstitutes() {
        return InstituteDatabase.getInstituteMap().values();
    }

    @GraphQLQuery(name = "Institute")//根据Id获取学院信息
    public Institute getInstituteById(@GraphQLArgument(name = "instituteId") Long id) {
        return instituteMap.get(id);
    }

    @GraphQLMutation(name = "saveInstitute")//储存学院信息
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

    @GraphQLMutation(name = "updateInstitute")//更新学院信息
    public String updateInstitute(@GraphQLArgument(name = "instituteId") Long instituteID,
                                  @GraphQLArgument(name = "instituteName") String instituteName,
                                  @GraphQLArgument(name = "numberOfMajor") int numberOfMajor
    ) {
        try {
        instituteMap.get(instituteID).setInstituteName(instituteName);
        instituteMap.get(instituteID).setNumberOfMajor(numberOfMajor);
        } catch (Exception e) {
            return "更新信息失败，请检查是否存在该id";
        }
        return InstituteDatabase.updateInstitute(instituteID, instituteMap.get(instituteID));
    }

    @GraphQLMutation(name = "deleteInstitute")//删除学院信息
    public String deleteInstitute(@GraphQLArgument(name = "instituteId") Long id) {
        instituteMap.remove(id);
        return InstituteDatabase.deleteInstitute(id);
    }


/*
Queries for majors.
 */

    @GraphQLQuery(name = "majors")//获取专业信息
    public Collection<Major> getMajors() {
        return MajorDatabase.getMajorMap().values();
    }

    @GraphQLQuery(name = "major")//根据Id获取专业信息
    public Major getMajorById(@GraphQLArgument(name = "majorId") Long id) {
        return majorMap.get(id);
    }

    @GraphQLMutation(name = "saveMajor")//保存专业信息
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

    @GraphQLMutation(name = "updateMajor")//更新课程信息
    public String updateMajor(@GraphQLArgument(name = "majorId") Long majorID,
                              @GraphQLArgument(name = "majorName") String majorName,
                              @GraphQLArgument(name = "instituteId") Long instituteId) {
        try {
        majorMap.get(majorID).setMajorName(majorName);
        majorMap.get(majorID).setInstituteId(instituteId);
        majorMap.get(majorID).setInstituteName(instituteMap.get(instituteId).getInstituteName());
        } catch (Exception e) {
            return "更新信息失败，请检查是否存在该id";
        }
        return MajorDatabase.updateMajor(majorID, majorMap.get(majorID));
    }

    @GraphQLMutation(name = "deleteMajor")//删除专业信息
    public String deleteMajor(@GraphQLArgument(name = "majorId") Long id) {
        majorMap.remove(id);
        return MajorDatabase.deleteMajor(id);
    }

    /*
    Queries for students.
     */
    @GraphQLQuery(name = "students")//获取学生信息
    public Collection<Student> getStudents() {
        return StudentDatabase.getStudentMap().values();
    }

    @GraphQLQuery(name = "student")//根据Id获取学生信息
    public Student getStudentById(@GraphQLArgument(name = "studentId") Long id) {
        return studentMap.get(id);
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
        s.setMajorName(majorMap.get(majorId).getMajorName());
        studentMap.put(s.getStudentId(), s);
        return StudentDatabase.saveStudent(s);
    }

    @GraphQLMutation(name = "updateStudent")//保存学生信息
    public String updateStudent(@GraphQLArgument(name = "studentId") Long studentID,
                                @GraphQLArgument(name = "studentName") String studentName,
                                @GraphQLArgument(name = "studentSex") String studentSex,
                                @GraphQLArgument(name = "majorId") Long majorId) {
        try {
            studentMap.get(studentID).setStudentName(studentName);
            studentMap.get(studentID).setMajorId(majorId);
            studentMap.get(studentID).setStudentId(studentID);
            studentMap.get(studentID).setStudentSex(studentSex);
            studentMap.get(studentID).setMajorName(majorMap.get(majorId).getMajorName());
        } catch (Exception e) {
            return "更新信息失败，请检查是否存在该id";
        }
        return StudentDatabase.updateStudent(studentID, studentMap.get(studentID));
    }

    @GraphQLMutation(name = "deleteStudent")//删除学生信息
    public String deleteStudent(@GraphQLArgument(name = "studentId") Long id) {
        studentMap.remove(id);
        return StudentDatabase.deleteStudent(id);
    }


    /*
    Queries for courses.
     */
    @GraphQLQuery(name = "courses")//获取课程信息
    public Collection<Course> getCourses() {
        return CourseDatabase.getCourseMap().values();
    }

    @GraphQLQuery(name = "course")//根据Id获取课程信息
    public Course getCourseById(@GraphQLArgument(name = "courseId") Long id) {
        return courseMap.get(id);
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
        c.setTeacherName(teacherMap.get(teacherId).getTeacherName());
        c.setMajorName(majorMap.get(majorId).getMajorName());
        courseMap.put(c.getCourseId(), c);
        return CourseDatabase.saveCourse(c);
    }

    @GraphQLMutation(name = "updateCourse")//更新课程信息
    public String updateCourse(@GraphQLArgument(name = "courseId") Long courseID,
                               @GraphQLArgument(name = "courseName") String courseName,
                               @GraphQLArgument(name = "majorId") Long majorId,
                               @GraphQLArgument(name = "teacherId") Long teacherId) {
        try {
        courseMap.get(courseID).setTeacherName(teacherMap.get(teacherId).getTeacherName());
        courseMap.get(courseID).setCourseName(courseName);
        courseMap.get(courseID).setMajorId(majorId);
        courseMap.get(courseID).setTeacherId(teacherId);
        courseMap.get(courseID).setMajorName(majorMap.get(majorId).getMajorName());
        } catch (Exception e) {
            return "更新信息失败，请检查是否存在该id";
        }
        return CourseDatabase.updateCourse(courseID, courseMap.get(courseID));
    }

    @GraphQLMutation(name = "deleteCourse")//删除课程信息
    public String deleteCourse(@GraphQLArgument(name = "courseId") Long id) {
        courseMap.remove(id);
        return CourseDatabase.deleteCourse(id);
    }


    /*
    Queries for teachers.
     */
    @GraphQLQuery(name = "teachers")//获取教师信息
    public Collection<Teacher> getTeacher() {
        return teacherMap.values();
    }

    @GraphQLQuery(name = "teacher")//根据Id获取教师信息
    public Teacher getTeacherById(@GraphQLArgument(name = "teacherId") Long id) {
        return teacherMap.get(id);
    }

    @GraphQLMutation(name = "updateTeacher")//更新教师信息
    public String saveTeacher(@GraphQLArgument(name = "teacherId") Long teacherId,
                              @GraphQLArgument(name = "teacherName") String teacherName,
                              @GraphQLArgument(name = "teacherSex") String teacherSex,
                              @GraphQLArgument(name = "instituteId") Long instituteId) {
        try {
        teacherMap.get(teacherId).setTeacherId(teacherId);
        teacherMap.get(teacherId).setTeacherName(teacherName);
        teacherMap.get(teacherId).setTeacherSex(teacherSex);
        teacherMap.get(teacherId).setInstituteId(instituteId);
        teacherMap.get(teacherId).setInstituteName(instituteMap.get(instituteId).getInstituteName());
        } catch (Exception e) {
            return "更新信息失败，请检查是否存在该id";
        }
        return TeacherDatabase.updateTeacher(teacherId, teacherMap.get(teacherId));
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
        teacher.setInstituteName(instituteMap.get(instituteId).getInstituteName());
        teacherMap.put(teacher.getTeacherId(), teacher);
        return TeacherDatabase.saveTeacher(teacher);
    }

    @GraphQLMutation(name = "deleteTeacher")//删除教师信息
    public String deleteTeacher(@GraphQLArgument(name = "teacherId") Long id) {
        teacherMap.remove(id);
        return TeacherDatabase.deleteTeacher(id);
    }


    /*
    Queries for pubic
     */
    @GraphQLQuery(name = "courseDetails")
    public ArrayList<String> getDetails() {
        return publicMethod.courseDetails();
    }

}



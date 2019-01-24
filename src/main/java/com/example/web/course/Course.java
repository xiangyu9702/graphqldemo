package com.example.web.course;


import io.leangen.graphql.annotations.GraphQLQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
/*
课程的实体类
 */
@Entity
public class Course {
    @Id @GeneratedValue
    @GraphQLQuery(name = "courseId", description = "A course's courseId")
    private Long courseId;
    @GraphQLQuery(name = "courseName", description = "A course's courseName")
    private String courseName;
    @GraphQLQuery(name = "majorId", description = "A major's id")
    private Long majorId;
    @GraphQLQuery(name = "majorName", description = "A major's majorName")
    private String majorName;
    @GraphQLQuery(name = "teacherId", description = "A teacher's Id")
    private Long teacherId;
    @GraphQLQuery(name = "teacherName", description = "A teacher's Name")
    private String teacherName;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}

package com.example.web.student;


import io.leangen.graphql.annotations.GraphQLQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

/*
学生的实体类
 */
@Entity
public class Student {
    @Id @GeneratedValue
    @GraphQLQuery(name = "studentId", description = "A student's studentId")
    private Long studentId;
    @GraphQLQuery(name = "studentName", description = "A student's studentName")
    private String studentName;
    @GraphQLQuery( name = "majorId", description = "A student's major")
    private Long majorId;
    @GraphQLQuery( name = "studentSex", description = "A student's sex")
    private String studentSex;
    @GraphQLQuery(name = "majorName", description = "A major's majorName")
    private String majorName;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}

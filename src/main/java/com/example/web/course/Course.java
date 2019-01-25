package com.example.web.course;


import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
/*
课程的实体类
 */
@Entity
@Getter
@Setter
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

    

}

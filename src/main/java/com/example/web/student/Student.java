package com.example.web.student;


import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

/*
学生的实体类
 */
@Entity
@Getter
@Setter
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
}

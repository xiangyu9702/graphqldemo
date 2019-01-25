package com.example.web.teacher;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
/*
教师的实体类
 */
@Entity
@Getter
@Setter
public class Teacher {
    @Id @GeneratedValue
    @GraphQLQuery(name = "teacherId", description = "A teacher's Id")
    private Long teacherId;
    @GraphQLQuery(name = "teacherName", description = "A teacher's Name")
    private String teacherName;
    @GraphQLQuery( name = "teacherSex", description = "A teacher's sex")
    private String teacherSex;
    @GraphQLQuery(name = "instituteId", description = "A Institute's instituteId")
    private Long instituteId;
    @GraphQLQuery(name = "instituteName", description = "A Institute's instituteName")
    private String instituteName;
}

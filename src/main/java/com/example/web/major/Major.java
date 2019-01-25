package com.example.web.major;

import com.example.web.course.Course;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
/*
专业的实体类
 */
@Entity
@Getter
@Setter
public class Major {

    @Id
    @GeneratedValue
    @GraphQLQuery(name = "majorId", description = "A major's id")
    private Long majorId;
    @GraphQLQuery(name = "majorName", description = "A major's majorName")
    private String majorName;
    @GraphQLQuery(name = "instituteId", description = "A instituteId of major")
    private Long instituteId;
    @GraphQLQuery(name = "instituteName", description = "A instituteName of major")
    private String instituteName;
    @GraphQLQuery(name = "courseArrayList", description = "A list of courseArrayList")
    private ArrayList<Course> courseArrayList;
    
}
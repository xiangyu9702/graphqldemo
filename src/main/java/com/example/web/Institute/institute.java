package com.example.web.Institute;

import com.example.web.major.Major;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.ArrayList;
/*
学院的实体类
 */
@Entity
@Getter
@Setter
public class institute {
    @Id @GeneratedValue
    @GraphQLQuery(name = "instituteId", description = "A institute's instituteId")
    private Long instituteId;
    @GraphQLQuery(name = "instituteName", description = "A institute's instituteName")
    private String instituteName;
    @GraphQLQuery( name = "numberOfMajor", description = "A institute's number of major")
    private Integer numberOfMajor;
    @GraphQLQuery( name = "majorArrayList", description = "A list of major")
    private ArrayList<Major> majorArrayList;

   

}

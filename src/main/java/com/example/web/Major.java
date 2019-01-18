package com.example.web;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Major {

    @Id
    @GeneratedValue
    @GraphQLQuery(name = "majorId", description = "A major's id")
    private Long majorId;
    @GraphQLQuery(name = "majorName", description = "A major's majorName")
    private String majorName;
    @GraphQLQuery(name = "numberOfStudnet", description = "A major's number of student")
    private Integer numberOfStudent;
    @GraphQLQuery(name = "instituteIdOfMajor", description = "A instituteId of major")
    private Long instituteIdOfMajor;



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

    public Integer getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Long getInstituteIdOfMajor() {
        return instituteIdOfMajor;
    }

    public void setInstituteIdOfMajor(Long instituteIdOfMajor) {
        this.instituteIdOfMajor = instituteIdOfMajor;
    }
}
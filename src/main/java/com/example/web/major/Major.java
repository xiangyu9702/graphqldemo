package com.example.web.major;

import com.example.web.course.Course;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

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
    @GraphQLQuery(name = "instituteId", description = "A instituteId of major")
    private Long instituteId;
    @GraphQLQuery(name = "instituteName", description = "A instituteName of major")
    private String instituteName;
    @GraphQLQuery(name = "courseArrayList", description = "A list of courseArrayList")
    private ArrayList<Course> courseArrayList;

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public ArrayList<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    public Long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
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



}
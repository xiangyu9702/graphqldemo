package com.example.web.Institute;

import com.example.web.major.Major;
import io.leangen.graphql.annotations.GraphQLQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.ArrayList;
/*
学院的实体类
 */
@Entity
public class Institute {
    @Id @GeneratedValue
    @GraphQLQuery(name = "instituteId", description = "A Institute's instituteId")
    private Long instituteId;
    @GraphQLQuery(name = "instituteName", description = "A Institute's instituteName")
    private String instituteName;
    @GraphQLQuery( name = "numberOfMajor", description = "A Institute's number of major")
    private Integer numberOfMajor;
    @GraphQLQuery( name = "majorArrayList", description = "A list of major")
    private ArrayList<Major> majorArrayList;

    public ArrayList<Major> getMajorArrayList() {
        return majorArrayList;
    }

    public void setMajorArrayList(ArrayList<Major> majorArrayList) {
        this.majorArrayList = majorArrayList;
    }

    public Long getInstituteId() {
        return instituteId;
    }

    public void setNumberOfMajor(Integer numberOfMajor) {
        this.numberOfMajor = numberOfMajor;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public Integer getNumberOfMajor() {
        return numberOfMajor;
    }


}

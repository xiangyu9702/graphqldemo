package com.example.web;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@Getter@Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Institute {
    @Id @GeneratedValue
    @GraphQLQuery(name = "instituteId", description = "A institute's instituteId")
    private Long instituteId;
    @GraphQLQuery(name = "instituteName", description = "A institute's instituteName")
    private String instituteName;
    @GraphQLQuery( name = "numberOfMajor", description = "A institute's number of major")
    private Integer numberOfMajor;
    @GraphQLQuery( name = "majors", description = "A list of major")
    private ArrayList<Major> majors;

    public ArrayList<Major> getMajors() {
        return majors;
    }

    public void setMajors(ArrayList<Major> majors) {
        this.majors = majors;
    }

    public Long getInstituteId() {
        return instituteId;
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

    public void setNumberOfMajor(Integer numberOfMajor) {
        this.numberOfMajor = numberOfMajor;
    }


}

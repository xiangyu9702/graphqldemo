package com.example.web.teacher;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter@Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
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

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


}

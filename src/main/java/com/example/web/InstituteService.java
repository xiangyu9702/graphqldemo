package com.example.web;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstituteService {

    private Map<Long,Institute> instituteMap =new HashMap<>();

    public InstituteService() {
        this.instituteMap = instituteDatabase.getInstituteMap();
    }

    @GraphQLQuery(name = "institutes")
    public List<Institute> getInstitutes() {
        return instituteDatabase.getInstitute();
    }
    @GraphQLQuery(name = "institute")
    public Institute getInstituteById(@GraphQLArgument(name = "instituteId") Long id) {
        return instituteMap.get(id);
    }

    @GraphQLMutation(name = "saveInstitute")
    public Institute saveInstitute(@GraphQLArgument(name = "institute") Institute institute) {
        instituteDatabase.saveInstitute(institute);
        instituteMap.put(institute.getInstituteId(), institute);
        return institute;
    }

    public Institute importUser(Long id, Institute institute) {
        instituteMap.put(id, institute);
        return institute;
    }

    @GraphQLMutation(name = "deleteInstitute")
    public String deleteInstitute(@GraphQLArgument(name = "instituteId") Long id) {
        instituteDatabase.deleteInstitute(id);
        instituteMap.remove(id);
        return "删除成功";
    }
}

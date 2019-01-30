package com.example.web.Service;

import com.example.web.Institute.Institute;
import com.example.web.Institute.InstituteDatabase;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {
/*
Queries for institutes
 */
  @GraphQLQuery(name = "institutes")//获取学院信息
  public Collection<Institute> getInstitutes() {
    return getMap.instituteMap.values();
  }

  @GraphQLQuery(name = "Institute")//根据Id获取学院信息
  public Institute getInstituteById(@GraphQLArgument(name = "instituteId") Long id) {
    return getMap.instituteMap.get(id);
  }

  @GraphQLMutation(name = "saveInstitute")//储存学院信息
  public String saveInstitute(@GraphQLArgument(name = "institutedId") Long institutedId,
      @GraphQLArgument(name = "instituteName") String instituteName,
      @GraphQLArgument(name = "numberOfMajor") int numberOfMajor) {
    Institute institute = new Institute();
    institute.setInstituteId(institutedId);
    institute.setInstituteName(instituteName);
    institute.setNumberOfMajor(numberOfMajor);
    getMap.instituteMap.put(institutedId, institute);
    return InstituteDatabase.saveInstitute(institute);
  }

  @GraphQLMutation(name = "updateInstitute")//更新学院信息
  public String updateInstitute(@GraphQLArgument(name = "instituteId") Long instituteID,
      @GraphQLArgument(name = "instituteName") String instituteName,
      @GraphQLArgument(name = "numberOfMajor") int numberOfMajor
  ) {
    try {
      getMap.instituteMap.get(instituteID).setInstituteName(instituteName);
      getMap.instituteMap.get(instituteID).setNumberOfMajor(numberOfMajor);
    } catch (Exception e) {
      return "更新信息失败，请检查是否存在该id";
    }
    return InstituteDatabase.updateInstitute(instituteID, getMap.instituteMap.get(instituteID));
  }

  @GraphQLMutation(name = "deleteInstitute")//删除学院信息
  public String deleteInstitute(@GraphQLArgument(name = "instituteId") Long id) {
    getMap.instituteMap.remove(id);
    return InstituteDatabase.deleteInstitute(id);
  }


}
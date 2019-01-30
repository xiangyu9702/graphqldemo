package com.example.web.Service;

import com.example.web.Institute.Institute;
import com.example.web.Institute.InstituteDatabase;
import com.example.web.course.Course;
import com.example.web.course.CourseDatabase;
import com.example.web.major.Major;
import com.example.web.major.MajorDatabase;
import com.example.web.publicMethod.publicMethod;
import com.example.web.student.Student;
import com.example.web.student.StudentDatabase;
import com.example.web.teacher.Teacher;
import com.example.web.teacher.TeacherDatabase;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MajorService {


/*
Queries for majors.
 */

  @GraphQLQuery(name = "majors")//获取专业信息
  public Collection<Major> getMajors() {
    return getMap.majorMap.values();
  }

  @GraphQLQuery(name = "major")//根据Id获取专业信息
  public Major getMajorById(@GraphQLArgument(name = "majorId") Long id) {
    return getMap.majorMap.get(id);
  }

  @GraphQLMutation(name = "saveMajor")//保存专业信息
  public String saveMajor(@GraphQLArgument(name = "majorId") Long majorId,
      @GraphQLArgument(name = "majorName") String majorName,
      @GraphQLArgument(name = "institutedId") Long institutedId
  ) {
    Major major = new Major();
    major.setMajorId(majorId);
    major.setMajorName(majorName);
    major.setInstituteId(institutedId);
    major.setInstituteName(getMap.instituteMap.get(institutedId).getInstituteName());
    getMap.majorMap.put(major.getMajorId(), major);
    return MajorDatabase.saveMajor(major);
  }

  @GraphQLMutation(name = "updateMajor")//更新课程信息
  public String updateMajor(@GraphQLArgument(name = "majorId") Long majorID,
      @GraphQLArgument(name = "majorName") String majorName,
      @GraphQLArgument(name = "instituteId") Long instituteId) {
    try {
      getMap.majorMap.get(majorID).setMajorName(majorName);
      getMap.majorMap.get(majorID).setInstituteId(instituteId);
      getMap.majorMap.get(majorID).setInstituteName(getMap.instituteMap.get(instituteId).getInstituteName());
    } catch (Exception e) {
      return "更新信息失败，请检查是否存在该id";
    }
    return MajorDatabase.updateMajor(majorID, getMap.majorMap.get(majorID));
  }

  @GraphQLMutation(name = "deleteMajor")//删除专业信息
  public String deleteMajor(@GraphQLArgument(name = "majorId") Long id) {
    getMap.majorMap.remove(id);
    return MajorDatabase.deleteMajor(id);
  }


}

package com.example.controller;

import com.example.web.Service.CollegeService;
import com.example.web.Service.CourseService;
import com.example.web.Service.InstituteService;
import com.example.web.Service.MajorService;
import com.example.web.Service.StudentService;
import com.example.web.Service.TeacherService;
import com.example.web.major.Major;
import com.example.web.student.Student;
import com.example.web.teacher.Teacher;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
控制器
 */
@RestController
public class GraphQLController {

    private final GraphQL graphQL;

    public GraphQLController(CourseService courseService, InstituteService instituteService, MajorService majorService,
        StudentService studentService, TeacherService teacherService) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        //Resolve by annotations
                        new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(courseService)
                .withOperationsFromSingleton(instituteService)
                .withOperationsFromSingleton(majorService)
                .withOperationsFromSingleton(studentService)
                .withOperationsFromSingleton(teacherService)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest raw) {
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
                .query(request.get("query"))
                .operationName(request.get("operationName"))
                .context(raw)
                .build());
        return executionResult.toSpecification();
    }
}
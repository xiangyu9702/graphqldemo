# 基于springboot，graphiql的api接口
通过SpringBoot集成graphql连接MySql数据库
1、开发环境搭建
开发环境：
Java 1.8
spring-2.0.0.RELEASE
mysql-8.0.13
Maven 3.6.0

2、使用Maven构建项目
访问：http://start.spring.io/，通过SPRING INITIALIZR工具产生基础项目

解压项目包，并用IDE以Maven项目导入，菜单中选择File–>New–>Project from Existing Sources，选择解压后的项目文件夹，点击OK，点击Import project from external model并选择Maven，点击Next到底为止。
通过上面步骤完成了基础项目的创建，如上图所示，Spring Boot的基础结构共三个文件
src/main/java下的程序入口：DemoApplication
src/main/resources下的配置文件：application.properties
src/test/下的测试入口：DemoApplicationTests

3、引入依赖
  <!--SpringBoot依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<!--H2数据库依赖-->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
<!--MySQL数据库依赖-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.13</version>
</dependency>
<!--Graphql依赖-->
<dependency>
    <groupId>io.leangen.graphql</groupId>
    <artifactId>spqr</artifactId>
    <version>0.9.9</version>
</dependency>
<!--lombok依赖-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.4</version>
    <scope>provided</scope>
</dependency>

4、配置数据库信息
创建Database类，储存JDBC 驱动名及数据库 URL和数据库的用户名与密码


5、创建实体类
本例共5个实体类:Institute（学院），Major（专业），	Course（课程），Student（学生），Teacher（教师）。

6、数据库方法创建
每个实体类有四种方法，getMap：从数据库获取信息delete：从数据库删除信息，save：保存新的信息，update:更新信息。

7、Controller配置
GraphqlController类配置如下：
@RestController
public class GraphQLController {

    private final GraphQL graphQL;

    public GraphQLController(CollegeService collegeService) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        //Resolve by annotations
                        new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(collegeService)
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

8、ApplicationWebConfig配置：
ApplicationWebConfig类配置如下：
@Configuration
public class ApplicationWebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/graphiql").setViewName(
                "forward:/graphiql/index.html");
    }
}
9、CollegeService配置
CollegeService类配置Graphiql的语句，包含学院等5个实体类的Graphql语句方法配置。
5个实体类结构类似。


10、访问页面
完成上述代码，启动Spring Boot程序，访问：http://localhost:8080/graphiql.html
。就能看到下图所展示的Garphiql的页面。可根据Graphql语法获得信息。

package com.example;

import com.example.web.Institute;
import com.example.web.instituteDatabase;
import com.example.web.InstituteService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
	ApplicationRunner init(InstituteService instituteService) {
	    ArrayList<Institute> institutes =instituteDatabase.getInstitute();
		return args -> {for (Institute nnn: institutes){
			Stream.of(nnn).forEach(u -> {
				instituteService.importUser(u.getInstituteId(),u);
			});
		};
	};}
}


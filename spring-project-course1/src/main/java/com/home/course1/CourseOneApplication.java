package com.home.course1;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class CourseOneApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CourseOneApplication.class, args);
	}
	
}

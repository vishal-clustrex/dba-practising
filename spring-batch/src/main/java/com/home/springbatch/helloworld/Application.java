package com.home.springbatch.helloworld;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.home.springbatch.utils.TriggerJobService;

@SpringBootApplication
@EnableBatchProcessing
@PropertySource(value = { "/context/without-web-context.properties" })
public class Application {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		JobParameters jobParameters = new JobParametersBuilder()
				.addParameter("output", new JobParameter("My first spring boot app."))
				.toJobParameters();
		
		TriggerJobService triggerJobService = context.getBean(TriggerJobService.class);
		triggerJobService.runJob(jobParameters);
	}
	
}

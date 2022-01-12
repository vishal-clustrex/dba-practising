package com.home.springbatch.separatefiles;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.home.springbatch.helloworld.jobconfig.JobConfiguration;
import com.home.springbatch.utils.TriggerJobService;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses= { SecondApplication.class, JobConfiguration.class, TriggerJobService.class })
@EnableBatchProcessing
@EntityScan(basePackageClasses = SecondApplication.class)
@PropertySource(value = { "/context/peristent-h2-context.properties" })
public class SecondApplication {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(SecondApplication.class, args);
		JobParameters jobParameters = new JobParametersBuilder()
				.addParameter("output", new JobParameter("My first spring boot app."))
				.toJobParameters();
		
		TriggerJobService triggerJobService = context.getBean(TriggerJobService.class);
		triggerJobService.runJob(jobParameters);
	}
	
}

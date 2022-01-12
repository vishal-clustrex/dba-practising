package com.home.springbatch.helloworld;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(classes=HelloWorldTest.TestConfig.class)
public class HelloWorldTest {

	@Autowired
	JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	void test() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addParameter("output", new JobParameter("Hello world from parameter"))
				.toJobParameters();
		jobLauncherTestUtils.launchJob(jobParameters);
	}
	
	@Configuration
	@EnableBatchProcessing
	static class TestConfig {

		@Autowired
		JobBuilderFactory jobBuilderFactory;
		
		@Autowired
		StepBuilderFactory stepBuilderFactory;
		
		@Bean
		public Job helloWorldJob() {
			Step step = stepBuilderFactory.get("step").tasklet((stepContribution, chunkContext) -> {
				Map<String, Object> params = chunkContext.getStepContext().getJobParameters();
				System.out.println(params.get("output"));
				return RepeatStatus.FINISHED;
			}).build();
			return jobBuilderFactory.get("helloWorldJob").start(step).build();
		}
		
		@Bean
		public JobLauncherTestUtils jobLauncherTestUtils() {
			return new JobLauncherTestUtils();
		}
		
	}

}

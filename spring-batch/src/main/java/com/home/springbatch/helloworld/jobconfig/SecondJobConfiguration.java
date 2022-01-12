package com.home.springbatch.helloworld.jobconfig;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	public SecondJobConfiguration(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@Bean
	public Job secondJob() {
		Step step = stepBuilderFactory.get("step").tasklet((stepContribution, chunkContext) -> {
			Map<String, Object> params = chunkContext.getStepContext().getJobParameters();
			System.out.println(params.get("output"));
			return RepeatStatus.FINISHED;
		}).build();
		return jobBuilderFactory.get("helloWorldJob").start(step).build();
	}
	
}

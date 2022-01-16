package com.home.springbatch.separatefiles.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.home.springbatch.helloworld.jobconfig.FileJob;
import com.home.springbatch.separatefiles.dto.InputData;
import com.home.springbatch.separatefiles.dto.OutputData;
import com.home.springbatch.separatefiles.exception.CustomException;
import com.home.springbatch.separatefiles.processor.UpperCaseProcessor;

@Configuration
public class BatchConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final JobRepository jobRepository;
	private final StepBuilderFactory stepBuilderFactory;
	private final ItemReader<InputData> itemReader;
	private final UpperCaseProcessor upperCaseProcessor;
	
	public BatchConfig(JobBuilderFactory jobBuilderFactory, JobRepository jobRepository,
			StepBuilderFactory stepBuilderFactory, ItemReader<InputData> jsonItemReader,
			UpperCaseProcessor upperCaseProcessor) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.jobRepository = jobRepository;
		this.stepBuilderFactory = stepBuilderFactory;
		this.itemReader = jsonItemReader;
		this.upperCaseProcessor = upperCaseProcessor;
	}
	
	@Bean
	@FileJob
	public Job job() {
		return jobBuilderFactory.get("upperCaseJob")
					.start(step())
					.build();
	}
	
	@Bean
	public Step step() {
		SimpleStepBuilder<InputData, OutputData> simpleStepBuilder = stepBuilderFactory.get("jsonItemReader")
																				.repository(jobRepository)
																				.chunk(1);
		return simpleStepBuilder.reader(this.itemReader)
				.processor(this.upperCaseProcessor)
				.faultTolerant()
				.skip(CustomException.class)
				.skipLimit(1)
				.writer(writer(null))
				.build();
	}
			
	@Bean
	@StepScope
	public JsonFileItemWriter<OutputData> writer(@Value("#{jobParameters['outputPath']}") String outputDataPath) {

		Resource resource = new FileSystemResource(outputDataPath);
		
		return new JsonFileItemWriterBuilder<OutputData>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(resource)
				.name("jsonItemWriter")
				.build();
		
	}
	
}

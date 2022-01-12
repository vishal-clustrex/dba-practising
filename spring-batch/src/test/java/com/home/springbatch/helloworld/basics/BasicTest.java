package com.home.springbatch.helloworld.basics;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

@SpringBootTest(classes=BasicTest.TestConfig.class)
public class BasicTest {

	@Autowired
	JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	void test() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addParameter("inputPath", new JobParameter("classpath:files/input.json"))
				.addParameter("outputPath", new JobParameter("output/output.json"))
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
		public Step readerStep(ItemReader<Input> reader, ItemWriter<Output> writer) {
			SimpleStepBuilder<Input, Output> simpleStepBuilder = stepBuilderFactory.get("readJsonStep")
					.chunk(1);
			return simpleStepBuilder
					.reader(reader)
					.processor(processor())
					.writer(writer).build();
		}
		
		@Bean
		public Job helloWorldJob(ItemReader<Input> reader, ItemWriter<Output> writer) {
			return jobBuilderFactory.get("helloWorldJob").start(readerStep(reader, writer)).build();
		}
		
		@Bean
		@StepScope
		public JsonItemReader<Input> reader(@Value("#{jobParameters['inputPath']}") String inputPath) {
			File file = null;
			try {
				file = ResourceUtils.getFile(inputPath);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
			
			return new JsonItemReaderBuilder<Input>()
					.jsonObjectReader(new JacksonJsonObjectReader<>(Input.class))
					.resource(new FileSystemResource(file))
					.name("jsonItemReader")
					.build();
		}
		
		@Bean
		public ItemProcessor<Input, Output> processor() {
			return input -> {
				Output output = new Output();
				output.value = input.value.toUpperCase();
				return output;
			};
		}

		
		@Bean
		@StepScope
		public JsonFileItemWriter<Output> writer(@Value("#{jobParameters['outputPath']}") String outputPath) {

			Resource resource = new FileSystemResource(outputPath);
			
			return new JsonFileItemWriterBuilder<Output>()
					.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
					.resource(resource)
					.name("jsonItemWriter")
					.build();
			
		}

		
		@Bean
		public JobLauncherTestUtils jobLauncherTestUtils() {
			return new JobLauncherTestUtils();
		}
		
		public static class Input {
			public String value;
			
			@Override
			public String toString() {
				return "Input : {value="+value+"}";
			}
		}
		
		public static class Output {
			public String value;
			
			@Override
			public String toString() {
				return "Input : {value="+value+"}";
			}
		}
		
	}
	
}

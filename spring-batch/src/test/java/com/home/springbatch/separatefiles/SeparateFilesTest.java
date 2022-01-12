package com.home.springbatch.separatefiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.home.springbatch.separatefiles.config.BatchConfig;
import com.home.springbatch.separatefiles.dto.InputData;
import com.home.springbatch.separatefiles.processor.UpperCaseProcessor;

@SpringBootTest(classes= {SeparateFilesTest.TestConfig.class, BatchConfig.class, UpperCaseProcessor.class})
@EnableBatchProcessing
public class SeparateFilesTest {

	@Autowired
	Job job;
	
	@Autowired
	JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void testJob() throws Exception {
		InputData id = new InputData();
		id.value = "abc";
		TestConfig.datas.clear();
		TestConfig.datas.add(id);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addParameter("outputPath", new JobParameter("output/output.json"))
				.toJobParameters();
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		BatchStatus batchStatus = jobExecution.getStatus();
		assertEquals(batchStatus, BatchStatus.COMPLETED);
	}
	
	@Configuration
	static class TestConfig {
		
		static LinkedList<InputData> datas = new LinkedList<>();
		
		@Bean
		public JobLauncherTestUtils jobLauncherTestUtils() {
			return new JobLauncherTestUtils();
		}
		
		@Bean
		public ItemReader<InputData> itemReader() {
			return new ItemReader<InputData>() {

				@Override
				public InputData read()
						throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
					return datas.pollFirst();
				}
				
			};
		}
	}
	
}

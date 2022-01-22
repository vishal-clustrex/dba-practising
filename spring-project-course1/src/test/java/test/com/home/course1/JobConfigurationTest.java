package test.com.home.course1;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.home.course1.config.BatchConfiguration;
import com.home.course1.config.PersonJobParameters;
import com.home.course1.config.ReaderConfiguration;
import com.home.course1.listener.FileHandlingJobExecutionListener;

@SpringBootTest(classes = {JobConfigurationTest.TestConfig.class,
		BatchConfiguration.class, ReaderConfiguration.class
})
class JobConfigurationTest {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @MockBean
    FileHandlingJobExecutionListener fileHandlingJobExecutionListener;
    
    @Test
    void happyCaseTest() throws Exception {
    	JobParameters jobParameters = new JobParametersBuilder()
				.addParameter(PersonJobParameters.INPUT_PATH, new JobParameter("classpath:unitTestData/persons.json"))
				.addParameter(PersonJobParameters.OUTPUT_PATH, new JobParameter("output/output.json"))
				.toJobParameters();
		
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertTrue(jobExecution.getStatus().equals(BatchStatus.COMPLETED));
        String outputContent = Assertions.contentOf(new File("output/output.json"));
        assertTrue(outputContent.contains("Wei Lang"));
    }

    @Test
    void errorCaseTest() throws Exception {
    	assertThrows(JobParametersInvalidException.class, () ->
    		jobLauncherTestUtils.launchJob(new JobParameters()));
    }
    
    @Configuration
    @EnableBatchProcessing
    static class TestConfig {
        @Bean
        public JobLauncherTestUtils jobLauncherTestUtils() {
            return new JobLauncherTestUtils();
        }
    }
}

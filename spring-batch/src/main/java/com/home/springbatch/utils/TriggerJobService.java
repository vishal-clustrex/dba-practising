package com.home.springbatch.utils;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import com.home.springbatch.helloworld.jobconfig.HelloJob;

@Component
public class TriggerJobService {

	private final JobLauncher jobLauncher;
	private final Job job;
	
	public TriggerJobService(JobLauncher jobLauncher,
			@HelloJob Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}
	
	public void runJob(JobParameters jobParameters) throws JobExecutionAlreadyRunningException, 
								JobRestartException, 
								JobInstanceAlreadyCompleteException, 
								JobParametersInvalidException, InterruptedException {
		this.jobLauncher.run(job, jobParameters);
	}
	
	
}

package com.home.course1.utils;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import com.home.course1.config.PersonJobParameters;

@Component
public class TriggerJobService {

	private final JobLauncher jobLauncher;
	private final Job job;
	
	public TriggerJobService(JobLauncher jobLauncher,
				Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}
	
	public void runJob(JobParameters jobParameters) throws JobExecutionAlreadyRunningException, 
								JobRestartException, 
								JobInstanceAlreadyCompleteException, 
								JobParametersInvalidException, InterruptedException {
		this.jobLauncher.run(job, jobParameters);
	}
	
	public void runJob(File file) throws Exception {
		String uploadedPath = file.getAbsolutePath();
		
		String completedDir = CourseUtils.getWorkDirSubDirectory("public/completed");
		String errorDir = CourseUtils.getWorkDirSubDirectory("public/error");
		String processingDir = CourseUtils.getWorkDirSubDirectory("private/processing");
		
		String outputPath = CourseUtils.getFilePathForDifferentDirectory(file, completedDir);
		String errorPath = CourseUtils.getFilePathForDifferentDirectory(file, errorDir);
		String processingPath = CourseUtils.getFilePathForDifferentDirectory(file, processingDir);
		
		System.out.println("Processing Path : " + processingPath);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString(PersonJobParameters.INPUT_PATH, processingPath)
				.addString(PersonJobParameters.OUTPUT_PATH, outputPath)
				.addString(PersonJobParameters.ERROR_PATH, errorPath)
				.addString(PersonJobParameters.UPLOAD_PATH, uploadedPath)
				.toJobParameters();
		this.jobLauncher.run(job, jobParameters);
	}
	
	
}

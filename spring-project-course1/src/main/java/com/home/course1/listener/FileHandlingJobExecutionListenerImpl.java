package com.home.course1.listener;

import java.io.File;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

import com.home.course1.config.PersonJobParameters;
import com.home.course1.utils.CourseUtils;

@Component
public class FileHandlingJobExecutionListenerImpl implements FileHandlingJobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		JobParameters jobParameters = jobExecution.getJobParameters();
		String uploadedFile = jobParameters.getString(PersonJobParameters.UPLOAD_PATH);
		String inputFile = jobParameters.getString(PersonJobParameters.INPUT_PATH);
		System.out.println("uploadedFile File : " + uploadedFile);
		System.out.println("Input File : " + new File(inputFile).getParent());
		CourseUtils.moveFileToDirectory(new File(uploadedFile), new File(inputFile).getParent());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		JobParameters jobParameters = jobExecution.getJobParameters();
		String inputFile = jobParameters.getString(PersonJobParameters.INPUT_PATH);
		if(jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
			CourseUtils.deleteFile(inputFile);
		} else {
			String errorFile = jobParameters.getString(PersonJobParameters.ERROR_PATH);
			//CourseUtils.moveFileToDirectory(new File(inputFile), new File(errorFile).getParent());
		}
	}

}

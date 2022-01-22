package com.home.course1.config;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.DefaultJobParametersValidator;

public class PersonJobParamterValidator extends DefaultJobParametersValidator {

	private static final String[] REQUIRED_FIELDS = {
		PersonJobParameters.INPUT_PATH,
		PersonJobParameters.OUTPUT_PATH
	};
	
	private static final String[] OPTIONAL_FIELDS = {
			
	};
	
	public PersonJobParamterValidator() {
		super(REQUIRED_FIELDS, OPTIONAL_FIELDS);
	}
	
	@Override
	public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
		super.validate(jobParameters);
		String inputPath = jobParameters.getString(PersonJobParameters.INPUT_PATH);
		String extension = FilenameUtils.getExtension(inputPath);
		if(extension == null || !extension.equalsIgnoreCase("json")) {
			throw new JobParametersInvalidException("Input file should be of json.");
		}
	}
	
}

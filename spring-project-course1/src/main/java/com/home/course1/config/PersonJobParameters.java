package com.home.course1.config;

public class PersonJobParameters {

	private PersonJobParameters() {}
	
	public static final String INPUT_PATH = "inputPath";
	public static final String OUTPUT_PATH = "outputPath";
	public static final String UPLOAD_PATH = "uploadPath";
	public static final String ERROR_PATH = "errorPath";
	
	public static final String INPUT_PATH_REFERENCE = "#{jobParameters['"+INPUT_PATH+"']}";
	public static final String OUTPUT_PATH_REFERENCE = "#{jobParameters['"+OUTPUT_PATH+"']}";
	
}

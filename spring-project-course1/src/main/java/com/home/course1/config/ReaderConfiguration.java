package com.home.course1.config;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.home.course1.model.Person;
import com.home.course1.utils.CourseUtils;


@Configuration
public class ReaderConfiguration {

	private final StepBuilderFactory stepBuilderFactory;
	
	public ReaderConfiguration(StepBuilderFactory stepBuilderFactory) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	@StepScope
	public ItemStreamReader<Person> reader(@Value(PersonJobParameters.INPUT_PATH_REFERENCE) String inputDataPath) {
		Resource inputResource = CourseUtils.getFileResource(inputDataPath);
		return new JsonItemReaderBuilder<Person>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(Person.class))
				.resource(inputResource)
				.name("jsonItemReader")
				.build();
	}
	
}

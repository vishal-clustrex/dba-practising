package com.home.springbatch.separatefiles.config;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.home.springbatch.separatefiles.dto.InputData;
import com.home.springbatch.utils.Util;

@Configuration
public class ReaderConfiguration {

	private final StepBuilderFactory stepBuilderFactory;
	
	public ReaderConfiguration(StepBuilderFactory stepBuilderFactory) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	@StepScope
	public ItemStreamReader<InputData> reader(@Value("#{jobParameters['inputPath']}") String inputDataPath) {
		Resource inputResource = Util.getResource(inputDataPath);
		return new JsonItemReaderBuilder<InputData>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(InputData.class))
				.resource(inputResource)
				.name("jsonItemReader")
				.build();
	}
	
}

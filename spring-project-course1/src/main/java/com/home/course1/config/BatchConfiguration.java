package com.home.course1.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.home.course1.listener.FileHandlingJobExecutionListener;
import com.home.course1.model.Person;
import com.home.course1.processor.PersonProcessor;

@Configuration
public class BatchConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<Person> reader;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
    		ItemReader<Person> reader) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.reader = reader;
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
    	return new PersonProcessor();
    }
    
    @Bean
	@StepScope
	public JsonFileItemWriter<Person> writer(@Value(PersonJobParameters.OUTPUT_PATH_REFERENCE) String outputDataPath) {

		Resource resource = new FileSystemResource(outputDataPath);
		
		return new JsonFileItemWriterBuilder<Person>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(resource)
				.name("jsonItemWriter")
				.build();
		
	}
    
    @Bean
    public Job job(FileHandlingJobExecutionListener fileHandlingJobExecutionListener) {
        return jobBuilderFactory.get("anonymizeJob")
        		.validator(new PersonJobParamterValidator())
        		.listener(fileHandlingJobExecutionListener)
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return this.stepBuilderFactory.get("personStep")
        		.<Person, Person> chunk(10)
        		.reader(reader)
        		.processor(processor())
        		.writer(writer(null))
        		.build();
    }
	
}

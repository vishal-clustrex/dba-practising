package com.home.course1.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.home.course1.model.Person;

@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person input) throws Exception {
		if(!input.getIsCustomer()) {
			return null;
		}
		return input;
	}

}

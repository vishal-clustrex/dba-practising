package com.home.springbatch.separatefiles.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.home.springbatch.separatefiles.dto.InputData;
import com.home.springbatch.separatefiles.dto.OutputData;
import com.home.springbatch.separatefiles.exception.CustomException;

@Component
public class UpperCaseProcessor implements ItemProcessor<InputData, OutputData> {

	@Override
	public OutputData process(InputData input) throws Exception {
		if(input.skipIt) {
			throw new CustomException("Record Skipped.");
		}
		OutputData outputData = new OutputData();
		outputData.value = input.value.toUpperCase();
		return outputData;
	}

}

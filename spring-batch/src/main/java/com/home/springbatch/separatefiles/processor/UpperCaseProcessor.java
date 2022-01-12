package com.home.springbatch.separatefiles.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.home.springbatch.separatefiles.dto.InputData;
import com.home.springbatch.separatefiles.dto.OutputData;

@Component
public class UpperCaseProcessor implements ItemProcessor<InputData, OutputData> {

	@Override
	public OutputData process(InputData input) throws Exception {
		OutputData outputData = new OutputData();
		outputData.value = input.value.toUpperCase();
		return outputData;
	}

}

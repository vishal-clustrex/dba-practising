package com.home.springbatch.separatefiles.dto;

public class InputData {

	public String value;
	public boolean skipIt;
	
	@Override
	public String toString() {
		return "Input : {value="+value+", skipIt="+skipIt+"}";
	}
	
}

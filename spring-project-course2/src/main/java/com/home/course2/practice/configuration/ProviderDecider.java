package com.home.course2.practice.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import com.home.course2.config.AirlineSearchService;
import com.home.course2.model.Airport;

public class ProviderDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		JobParameters jobParameters = jobExecution.getJobParameters();
		String departureValue = jobParameters.getString(AirlineSearchService.JOB_KEY_DEPARTURE_AIRPORT);
		Airport departure = Airport.valueOf(departureValue);
		String arrivalValue = jobParameters.getString(AirlineSearchService.JOB_KEY_ARRIVAL_AIRPORT);
		Airport arrival = Airport.valueOf(arrivalValue);
		Set<Airport> flightAirports = new HashSet<>();
		flightAirports.addAll(Arrays.asList(departure, arrival));
		if(flightAirports.contains(Airport.DUBAI) && flightAirports.contains(Airport.AMSTERDAM)) {
			return new FlowExecutionStatus("DUBAI_AMSTERDUM_OFFER_ONLY");
		} else if(flightAirports.contains(Airport.NEWYORK) && flightAirports.contains(Airport.AMSTERDAM)) {
			return new FlowExecutionStatus("NEWYORK_AMSTERDUM_OFFER_ONLY");
		}
		return new FlowExecutionStatus("DEFAULT_SEARCH");
	}
}

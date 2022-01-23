package com.home.course2.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.home.course2.model.FlightSearchResult;

public class FlightOfferDto {

    public FlightOfferDto(FlightSearchResult result) {
        this.airlineName = result.getAirlineName();
        this.price = result.getPrice();
        this.arrivalTime = result.getArrivalTime();
        this.departureTime = result.getDepartureTime();
    }

    public String airlineName;
    public BigDecimal price;
    public LocalDateTime departureTime;
    public LocalDateTime arrivalTime;
}

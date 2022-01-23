package com.home.course2.utils.converters;

import org.springframework.core.convert.converter.Converter;

import com.home.course2.model.Airport;

public class StringToAirportConverter implements Converter<String, Airport> {

    @Override
    public Airport convert(String source) {
        return Airport.valueOf(source);
    }
}

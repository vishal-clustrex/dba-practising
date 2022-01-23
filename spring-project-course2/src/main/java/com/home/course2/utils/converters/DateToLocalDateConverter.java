package com.home.course2.utils.converters;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class DateToLocalDateConverter implements Converter<Date, LocalDate> {

    @Override
    public LocalDate convert(Date source) {
        return LocalDate.ofEpochDay(source.toInstant().getEpochSecond());
    }
}

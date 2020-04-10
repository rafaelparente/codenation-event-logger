package com.rafaelparente.eventlogger.converters;

import com.rafaelparente.eventlogger.models.EventLevel;
import org.springframework.core.convert.converter.Converter;

public class EventLevelConverter implements Converter<String, EventLevel> {

    @Override
    public EventLevel convert(String source) {
        return EventLevel.valueOf(source.toUpperCase());
    }

}

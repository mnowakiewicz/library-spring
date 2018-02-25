package com.example.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Component
@Primary
public class TimestampUtilityImpl implements TimestampUtility {
    private Timestamp timestamp;
    private Calendar calendar;

    public TimestampUtilityImpl() {
    }


    @Override
    public Timestamp getTimestamp() {
        calendar = Calendar.getInstance();
        timestamp = new Timestamp(calendar.getTime().getTime());
        return timestamp;
    }

    @Override
    public Date getDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }


}
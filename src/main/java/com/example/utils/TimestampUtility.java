package com.example.utils;

import java.sql.Date;
import java.sql.Timestamp;

public interface TimestampUtility {
    Timestamp getTimestamp();
    Date getDate(Timestamp timestamp);
}

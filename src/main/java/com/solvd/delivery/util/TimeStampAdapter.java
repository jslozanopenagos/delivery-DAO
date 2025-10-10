package com.solvd.delivery.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampAdapter extends XmlAdapter<String, Timestamp> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Timestamp unmarshal(String v) throws Exception {
        if (v == null || v.trim().isEmpty()) return null;
        LocalDateTime ldt = LocalDateTime.parse(v.trim(), FORMATTER);
        return Timestamp.valueOf(ldt);
    }

    @Override
    public String marshal(Timestamp v) throws Exception {
        if (v == null) return null;
        return v.toLocalDateTime().format(FORMATTER);
    }
}
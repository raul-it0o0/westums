package com.westums.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class utils {
    public static java.sql.Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        return new java.sql.Date(formatter.parse(date).getTime());
    }

    public static boolean isParseableDate (String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        try {
            java.sql.Date dateParsed = new Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

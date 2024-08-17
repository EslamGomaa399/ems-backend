package com.egomaa.ems.emsbackend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    private static final String INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";

    public static String convertToSimpleDateFormat(String inputDate) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat(INPUT_DATE_FORMAT, Locale.ENGLISH);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Set time zone to UTC
        Date date = inputFormat.parse(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.ENGLISH);
        return outputFormat.format(date);
    }

}

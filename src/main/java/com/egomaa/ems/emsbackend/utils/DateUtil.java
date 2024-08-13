package com.egomaa.ems.emsbackend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String INPUT_DATE_FORMAT = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('zzzz')'";
    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";

    public static String convertToSimpleDateFormat(String inputDate) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat(INPUT_DATE_FORMAT, Locale.ENGLISH);
        Date date = inputFormat.parse(inputDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.ENGLISH);
        return outputFormat.format(date);
    }


}

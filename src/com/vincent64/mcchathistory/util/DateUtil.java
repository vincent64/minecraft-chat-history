package com.vincent64.mcchathistory.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {
    private static final Pattern datePattern =
            Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
    private static final String dateFormat = "dd/MM/yyyy";

    public static boolean isValidFormat(String date) {
        return datePattern.matcher(date).matches();
    }

    public static boolean isValidDate(String date) {
        //Split the date and get its components (day/month/year)
        String[] dateComponents = date.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);

        //Check if each component is valid
        if (!(day >= 0 && day <= 31)) return false;
        if (!(month >= 1 && month <= 12)) return false;
        if (!(year >= 2000 && year <= 2037)) return false;

        //Check if the date is valid
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            Date testDate = simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(date, formatter);
    }
}

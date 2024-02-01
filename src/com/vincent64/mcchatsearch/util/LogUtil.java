package com.vincent64.mcchatsearch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class LogUtil {
    private static final Pattern logFileNamePattern =
            Pattern.compile("\\d{4}-\\d{2}-\\d{2}-\\d\\.log\\.gz");
    private static final String logDateFormat = "yyyy-MM-dd";

    public static boolean isValidFormat(String fileName) {
        return logFileNamePattern.matcher(fileName).matches();
    }

    public static long getLogDateMilliseconds(String fileName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(logDateFormat);
        try {
            Date date = simpleDateFormat.parse(fileName.substring(0, 10));
            return date.getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}

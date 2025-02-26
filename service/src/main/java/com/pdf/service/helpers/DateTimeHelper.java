package com.pdf.service.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {

    private DateTimeHelper() {
    }

    // default
    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Manila");

    public static String formatDateToTimestamp(Date date, String format, TimeZone timezone) {
        String timestamp = "";
        if (format.isEmpty()) {
            format = DEFAULT_DATE_FORMAT;
        }
        if (timezone == null) {
            timezone = DEFAULT_TIMEZONE;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(timezone);

            timestamp = sdf.format(date); // or if you want to save it in String str
        } catch (Exception e) {
            throw new RuntimeException( "Error @ convertDateToCalendar:String :: date=" + date);
        }

        return timestamp;
    }

    /**
     * Add value to date
     * @param date - date to add
     * @param type - Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND
     * @param value - value to add
     * @return - new date
     */
    public static Date addValueToDate(Date date, int type, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, value);
        return cal.getTime();
    }
}

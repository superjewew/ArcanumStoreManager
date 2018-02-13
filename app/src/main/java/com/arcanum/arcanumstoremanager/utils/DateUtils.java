package com.arcanum.arcanumstoremanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by norman on 31/01/18.
 */

public class DateUtils {

    public static String attendanceFormat = "HH:mm";
    public static String dateFormat = "EEE MMM dd HH:mm:ss z yyyy";

    public static String DateToStringFormatted(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(date);
    }

    public static String DateToStringFormatted(Date date) {
        return DateToStringFormatted(date, dateFormat);
    }

    public static Date StringFormattedToDate(String string) {
        return StringFormattedToDate(string);
    }

    public static Date StringFormattedToDate(String string, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.parse(string);
    }

    public static String DateInMillisToStringFormatted(Long inMillis) {
        return DateInMillisToStringFormatted(inMillis, attendanceFormat);
    }

    public static String DateInMillisToStringFormatted(Long inMillis, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(inMillis);
        return sdf.format(cal.getTime());
    }

    public static String ConvertDateStringFormat(String string, String newFormat) throws ParseException {
        return ConvertDateStringFormat(string, dateFormat, newFormat);
    }

    public static String ConvertDateStringFormat(String string, String oldFormat, String newFormat) throws ParseException {
        return DateToStringFormatted(StringFormattedToDate(string, oldFormat), newFormat);
    }

    public static Calendar MillisToCalendar(long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        return cal;
    }
}

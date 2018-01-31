package com.arcanum.arcanumstoremanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by norman on 31/01/18.
 */

public class DateUtils {

    private static String dateFormat = "EEE MMM dd HH:mm:ss z yyyy";

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

    public static String ConvertDateStringFormat(String string, String newFormat) throws ParseException {
        return ConvertDateStringFormat(string, dateFormat, newFormat);
    }

    public static String ConvertDateStringFormat(String string, String oldFormat, String newFormat) throws ParseException {
        return DateToStringFormatted(StringFormattedToDate(string, oldFormat), newFormat);
    }
}

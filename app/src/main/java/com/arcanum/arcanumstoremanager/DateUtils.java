package com.arcanum.arcanumstoremanager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by norman on 31/01/18.
 */

public class DateUtils {

    private static String dateFormat = "EEE MMM dd HH:mm:ss z yyyy";

    public static String DateToStringFormatted(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        return format.format(date);
    }
}

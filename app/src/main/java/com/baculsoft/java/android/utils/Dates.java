package com.baculsoft.java.android.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class Dates {
    private static final SimpleDateFormat EEEMMMddHHmmsszzzyyyy = new SimpleDateFormat(IConstants.IPatterns.EEEMMMddHHmmsszzzyyyy, Locale.getDefault());
    private static final SimpleDateFormat ddMMyyyyHHmmss = new SimpleDateFormat(IConstants.IPatterns.ddMMyyyyHHmmss, Locale.getDefault());

    public Date getDateTime(final String dateTime) {
        final DateFormat dateFormat = EEEMMMddHHmmsszzzyyyy;
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date;
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    public String getDateTime(final Date dateTime) {
        return ddMMyyyyHHmmss.format(dateTime);
    }
}
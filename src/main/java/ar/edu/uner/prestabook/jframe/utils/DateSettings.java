package ar.edu.uner.prestabook.jframe.utils;

import com.github.lgooddatepicker.components.DatePickerSettings;


/*
 * Create the settings and formats for a date
 */


public class DateSettings {

    public static DatePickerSettings getDatePickerSettings() {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("uuuu-MM-dd");
        return settings;
    }
}

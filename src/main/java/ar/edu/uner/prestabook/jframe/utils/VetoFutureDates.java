package ar.edu.uner.prestabook.jframe.utils;

import java.time.LocalDate;

import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

/**
 * Class that doesn't allow choose a date from the future in a DatePicker
 */
public class VetoFutureDates implements DateVetoPolicy {

    @Override
    public boolean isDateAllowed(LocalDate date) {
        return date.isEqual(LocalDate.now()) || date.isBefore(LocalDate.now());
    }
}
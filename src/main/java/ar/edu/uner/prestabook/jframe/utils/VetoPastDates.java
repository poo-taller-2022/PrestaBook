package ar.edu.uner.prestabook.jframe.utils;

import java.time.LocalDate;

import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;


/**
 * Class which don't allow choose a date from the past in a DatePicker
 *
 */
public class VetoPastDates implements DateVetoPolicy {

    @Override
    public boolean isDateAllowed(LocalDate date) {
        return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
    }

}

package ar.edu.uner.prestabook.jframe.utils;

import java.time.LocalDate;

import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

public class VetoPastDates implements DateVetoPolicy {

    @Override
    public boolean isDateAllowed(LocalDate date) {
        return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
    }

}

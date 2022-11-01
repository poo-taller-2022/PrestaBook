package ar.edu.uner.prestabook.jframe.utils;

import java.time.LocalDate;

import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

public class UnderAgeVetoPolicy implements DateVetoPolicy {

    @Override
    public boolean isDateAllowed(LocalDate date) {
        return date.isBefore(LocalDate.now().minusYears(17));
    }
}
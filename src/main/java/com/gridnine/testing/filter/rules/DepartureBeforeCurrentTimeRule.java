package com.gridnine.testing.filter.rules;

import com.gridnine.testing.filter.FlightFilterStrategy;
import com.gridnine.testing.model.Flight;

public class DepartureBeforeCurrentTimeRule implements FlightFilterStrategy {

    @Override
    public boolean isFlightValid(Flight flight) {
        return false;
    }
}

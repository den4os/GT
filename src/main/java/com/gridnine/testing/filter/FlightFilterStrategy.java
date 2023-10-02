package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

public interface FlightFilterStrategy {
    boolean isFlightValid(Flight flight);
}

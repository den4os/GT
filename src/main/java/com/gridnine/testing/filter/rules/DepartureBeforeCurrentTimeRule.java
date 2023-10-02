package com.gridnine.testing.filter.rules;

import com.gridnine.testing.filter.FlightFilterStrategy;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class DepartureBeforeCurrentTimeRule implements FlightFilterStrategy {

    @Override
    public boolean isFlightValid(Flight flight) {

        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            return false;
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Segment segment : segments) {
            LocalDateTime departureDateTime = segment.getDepartureDate();
            if (departureDateTime.isBefore(currentDateTime)){
                return false;
            }
        }

        return true;
    }
}

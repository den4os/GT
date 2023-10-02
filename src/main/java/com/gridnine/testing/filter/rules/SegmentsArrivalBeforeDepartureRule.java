package com.gridnine.testing.filter.rules;

import com.gridnine.testing.filter.FlightFilterStrategy;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;

public class SegmentsArrivalBeforeDepartureRule implements FlightFilterStrategy {

    @Override
    public boolean isFlightValid(Flight flight) {

        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            return false;
        }

        for (Segment currentSegment : segments) {
            if (currentSegment.getArrivalDate().isBefore(currentSegment.getDepartureDate())) {
                return false;
            }
        }

        return true;
    }
}

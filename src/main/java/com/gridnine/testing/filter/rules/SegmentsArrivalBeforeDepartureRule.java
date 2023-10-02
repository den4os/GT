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

        Segment previousSegment = segments.get(0);
        for (int i = 1; i < segments.size(); i++) {
            Segment currentSegment = segments.get(i);

            if (currentSegment.getDepartureDate().isBefore(previousSegment.getArrivalDate())) {
                return false;
            }

            previousSegment = currentSegment;
        }

        return true;
    }
}

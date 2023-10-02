package com.gridnine.testing.filter.rules;

import com.gridnine.testing.filter.FlightFilterStrategy;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;

public class TotalTimeOnGroundRule implements FlightFilterStrategy {

    @Override
    public boolean isFlightValid(Flight flight) {

        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            return false;
        }

        Duration totalGroundTime = Duration.ZERO;
        Segment previousSegment = segments.get(0);
        for (int i = 1; i < segments.size(); i++) {
            Segment currentSegment = segments.get(i);

            Duration timeOnGround = Duration.between(previousSegment.getArrivalDate(), currentSegment.getDepartureDate());
            totalGroundTime = timeOnGround.plus(totalGroundTime);
            previousSegment = currentSegment;

            if (totalGroundTime.toMinutes() > 120) {
                return true;
            }
        }

        return false;
    }
}

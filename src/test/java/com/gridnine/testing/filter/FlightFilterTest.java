package com.gridnine.testing.filter;

import com.gridnine.testing.filter.rules.DepartureBeforeCurrentTimeRule;
import com.gridnine.testing.filter.rules.SegmentsArrivalBeforeDepartureRule;
import com.gridnine.testing.filter.rules.TotalTimeOnGroundRule;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFilterTest {

    private List<Flight> flights;

    @Before
    public void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    public void testDepartureBeforeCurrentTimeRule() {
        FlightFilter flightFilter = new FlightFilter(new DepartureBeforeCurrentTimeRule());
        List<Flight> result = flightFilter.filterFlights(flights);
        assertEquals(5, result.size());
    }

    @Test
    public void testSegmentsArrivalBeforeDepartureRule() {
        FlightFilter flightFilter = new FlightFilter(new SegmentsArrivalBeforeDepartureRule());
        List<Flight> result = flightFilter.filterFlights(flights);
        assertEquals(5, result.size());
    }

    @Test
    public void testTotalTimeOnGroundRule() {
        FlightFilter flightFilter = new FlightFilter(new TotalTimeOnGroundRule());
        List<Flight> result = flightFilter.filterFlights(flights);
        assertEquals(2, result.size());
    }

    @Test
    public void testDbctrAndSAbdr() {
        FlightFilter flightFilter = new FlightFilter(
                new DepartureBeforeCurrentTimeRule(),
                new SegmentsArrivalBeforeDepartureRule());
        List<Flight> result = flightFilter.filterFlights(flights);
        assertEquals(4, result.size());
    }
}
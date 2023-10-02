package com.gridnine.testing.filter.rules;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegmentsArrivalBeforeDepartureRuleTest {

    private SegmentsArrivalBeforeDepartureRule rule;
    private LocalDateTime currentDateTime;

    @Before
    public void setUp() {
        rule = new SegmentsArrivalBeforeDepartureRule();
        currentDateTime = LocalDateTime.now();
    }

    @Test
    public void testValidSegment() {
        Segment validSegment = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(2));
        Flight flight = new Flight(List.of(validSegment));

        assertTrue(rule.isFlightValid(flight));
    }

    @Test
    public void testInvalidSegment() {
        Segment invalidSegment = new Segment(currentDateTime.plusHours(2), currentDateTime.plusHours(1));
        Flight flight = new Flight(List.of(invalidSegment));

        assertFalse(rule.isFlightValid(flight));
    }
}
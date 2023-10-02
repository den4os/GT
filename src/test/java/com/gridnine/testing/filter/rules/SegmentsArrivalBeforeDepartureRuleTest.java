package com.gridnine.testing.filter.rules;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegmentsArrivalBeforeDepartureRuleTest {

    private SegmentsArrivalBeforeDepartureRule rule;
    private LocalDateTime currentDateTime;
    private boolean initializeRule = true;

    @Before
    public void setUp() {
        rule = new SegmentsArrivalBeforeDepartureRule();
        if (initializeRule) {
            currentDateTime = LocalDateTime.now();
        }
    }

    @Test
    public void testValidFlight() {
        Segment validSegment1 = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(2));
        Segment validSegment2 = new Segment(currentDateTime.plusHours(3), currentDateTime.plusHours(4));
        Flight validFlight = new Flight(Arrays.asList(validSegment1, validSegment2));

        assertTrue(rule.isFlightValid(validFlight));
    }

    @Test
    public void testInvalidFlight1() {
        Segment invalidSegment1 = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(4));
        Segment invalidSegment2 = new Segment(currentDateTime.plusHours(2), currentDateTime.plusHours(6));
        Flight invalidFlight = new Flight(Arrays.asList(invalidSegment1, invalidSegment2));

        assertFalse(rule.isFlightValid(invalidFlight));
    }

    @Test
    public void testInvalidFlight2() {
        Segment invalidSegment1 = new Segment(currentDateTime.plusHours(3), currentDateTime.plusHours(4));
        Segment invalidSegment2 = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(2));
        Flight invalidFlight = new Flight(Arrays.asList(invalidSegment1, invalidSegment2));

        assertFalse(rule.isFlightValid(invalidFlight));
    }

    @Test
    public void testEmptySegments() {
        initializeRule = false;
        Flight emptyFlight = new Flight(Arrays.asList());

        assertFalse(rule.isFlightValid(emptyFlight));
    }
}
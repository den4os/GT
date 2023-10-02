package com.gridnine.testing.filter.rules;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TotalTimeOnGroundRuleTest {

    private TotalTimeOnGroundRule rule;
    private LocalDateTime currentDateTime;
    private boolean initializeRule = true;

    @Before
    public void setUp() {
        rule = new TotalTimeOnGroundRule();
        if (initializeRule) {
            currentDateTime = LocalDateTime.now();
        }
    }

    @Test
    public void testValidFlight() {
        Segment validSegment1 = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(2));
        Segment validSegment2 = new Segment(currentDateTime.plusHours(3), currentDateTime.plusHours(6));
        Segment validSegment3 = new Segment(currentDateTime.plusHours(7), currentDateTime.plusHours(8));
        Flight flight = new Flight(Arrays.asList(validSegment1, validSegment2, validSegment3));

        assertTrue(rule.isFlightValid(flight));
    }

    @Test
    public void testInvalidFlight() {
        Segment invalidSegment1 = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(2));
        Segment invalidSegment2 = new Segment(currentDateTime.plusHours(3), currentDateTime.plusHours(4));
        Flight flight = new Flight(Arrays.asList(invalidSegment1, invalidSegment2));

        assertFalse(rule.isFlightValid(flight));
    }

    @Test
    public void testEmptySegments() {
        initializeRule = false;
        Flight emptyFlight = new Flight(Arrays.asList());

        assertFalse(rule.isFlightValid(emptyFlight));
    }
}
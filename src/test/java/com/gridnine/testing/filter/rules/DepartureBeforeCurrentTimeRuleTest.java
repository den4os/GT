package com.gridnine.testing.filter.rules;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DepartureBeforeCurrentTimeRuleTest {

    private DepartureBeforeCurrentTimeRule rule;
    private LocalDateTime currentDateTime;
    private boolean initializeRule = true;

    @Before
    public void setUp() {
        rule = new DepartureBeforeCurrentTimeRule();
        if (initializeRule) {
            currentDateTime = LocalDateTime.now();
        }
    }

    @Test
    public void testValidFlight() {
        Segment validSegment = new Segment(currentDateTime.plusHours(1), currentDateTime.plusHours(2));
        Flight validFlight = new Flight(List.of(validSegment));

        assertTrue(rule.isFlightValid(validFlight));
    }

    @Test
    public void testInvalidFlight() {
        Segment invalidSegment = new Segment(currentDateTime.minusHours(1), currentDateTime.plusHours(1));
        Flight invalidFlight = new Flight(List.of(invalidSegment));

        assertFalse(rule.isFlightValid(invalidFlight));
    }

    @Test
    public void testEmptySegments() {
        initializeRule = false;
        Flight emptyFlight = new Flight(List.of());

        assertFalse(rule.isFlightValid(emptyFlight));
    }
}
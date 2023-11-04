package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightFilter {
    private List<FlightFilterStrategy> filterStrategies;

    private FlightFilter() {}

    public FlightFilter(FlightFilterStrategy... filterStrategy) {
        this.filterStrategies = new ArrayList<>(Arrays.asList(filterStrategy));
    }

    public List<Flight> filterFlights(List<Flight> flights) {

        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            boolean isValid = true;

            for (FlightFilterStrategy rule : filterStrategies) {;
                if (!rule.isFlightValid(flight)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
}

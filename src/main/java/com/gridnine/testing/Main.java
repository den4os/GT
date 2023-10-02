package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        for (Flight f : flights) {
            System.out.println(f.toString());
        }
    }
}

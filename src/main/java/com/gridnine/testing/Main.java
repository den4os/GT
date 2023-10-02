package com.gridnine.testing;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.rules.DepartureBeforeCurrentTimeRule;
import com.gridnine.testing.filter.rules.SegmentsArrivalBeforeDepartureRule;
import com.gridnine.testing.filter.rules.TotalTimeOnGroundRule;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter filter1 = new FlightFilter(new DepartureBeforeCurrentTimeRule());
        FlightFilter filter2 = new FlightFilter(new SegmentsArrivalBeforeDepartureRule());
        FlightFilter filter3 = new FlightFilter(new TotalTimeOnGroundRule());
        FlightFilter filter4 = new FlightFilter(
                new DepartureBeforeCurrentTimeRule(),
                new SegmentsArrivalBeforeDepartureRule()
        );

        List<Flight> result1 = filter1.filterFlights(flights);
        List<Flight> result2 = filter2.filterFlights(flights);
        List<Flight> result3 = filter3.filterFlights(flights);
        List<Flight> result4 = filter4.filterFlights(flights);

        System.out.format("\n%s\n", "DepartureBeforeCurrentTimeRule");
        for (Flight f : result1) {
            System.out.println(f.toString());
        }

        System.out.format("\n%s\n", "SegmentsArrivalBeforeDepartureRule");
        for (Flight f : result2) {
            System.out.println(f.toString());
        }

        System.out.format("\n%s\n", "TotalTimeOnGroundRule");
        for (Flight f : result3) {
            System.out.println(f.toString());
        }

        System.out.format("\n%s\n", "DepartureBeforeCurrentTimeRule and SegmentsArrivalBeforeDepartureRule");
        for (Flight f : result4) {
            System.out.println(f.toString());
        }
    }
}

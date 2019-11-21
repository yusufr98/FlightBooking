package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FlightRepository extends CrudRepository<Flight, Long> {
    ArrayList<Flight> findByAirlineContainingIgnoreCaseOrArrivalContainingIgnoreCaseOrDepartureContainingIgnoreCaseOrFlightNumberContainingIgnoreCase(String s, String s1, String s2, String s3);
}

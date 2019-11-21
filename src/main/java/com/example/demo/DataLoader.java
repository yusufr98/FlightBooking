package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired FlightRepository flightRepository;

    @Override
    public void run(String ... strings) throws Exception{
        Flight flight;
        flight = new Flight("Washington", "Paris", "One-way", "800", "12/1/19", "AF32", "Air France","https://www.telegraph.co.uk/content/dam/Travel/hotels/europe/france/paris/paris-cityscape-overview-guide-xlarge.jpg");
        flightRepository.save(flight);

        flight = new Flight("New York", "Delhi","One-way", "1200", "12/12/2019", "IN215", "Air India", "https://static.toiimg.com/thumb/68482857/delhi.jpg?width=748&height=499");
        flightRepository.save(flight);

        flight = new Flight("Los Angeles","New York","One-way","400","11/29/2019","AA80","Delta", "https://pix10.agoda.net/geo/city/318/1_318_02.jpg?s=1920x822");
        flightRepository.save(flight);
    }
}

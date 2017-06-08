package ua.training.dao;

import ua.training.entity.Flight;

import java.sql.Date;
import java.util.List;


public interface FlightDAO {
    /**
     * write to db new Flight
      * @param newFlight new Flight
     */
    void create(Flight newFlight);

    /**
     *
     * @param departure start town where you want to start voyage
     * @param destination you end  town
     * @param date date of start flight
     * @return list where flight  has  parameter @{departure},{@destination} and  {@date}
     */
    List<Flight> find(String departure, String destination, Date date);
    List<Flight> find(String departure, String destination);

    /**
     * find all active flight
     * @return
     */
    List<Flight> findAll();
    void update(Flight updateFlight);
    void delete(Integer id);

}

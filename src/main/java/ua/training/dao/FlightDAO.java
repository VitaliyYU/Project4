package ua.training.dao;

import ua.training.entity.Flight;

import java.sql.Date;
import java.util.List;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface FlightDAO {
    void create(Flight newFlight);
    Flight findById(Integer id);
    List<Flight> findByDepartureAndDestinationAndDate(String departure, String destination, Date date);
    List<Flight> findByDepartureAndDestination(String departure, String destination);
    List<Flight> findAll();
    Flight findByCode(String code);
    void update(Flight updateFlight);
    void delete(Integer id);

}

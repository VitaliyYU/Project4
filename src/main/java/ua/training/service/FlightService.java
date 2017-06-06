package ua.training.service;

import ua.training.dao.FlightDAO;
import ua.training.dao.TicketDAO;
import ua.training.dao.mysql.MySQLDAOFactory;
import ua.training.entity.Flight;
import ua.training.entity.Ticket;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vitaliy on 27.05.17.
 */
public class FlightService {
    private FlightDAO flightDAO;
    private TicketDAO ticketDAO;
    private static FlightService flightService=new FlightService();

    public static FlightService getInstance(){
        return flightService;
    }

    private FlightService(){
        flightDAO= MySQLDAOFactory.getMySQLFlightDAO();
        ticketDAO=MySQLDAOFactory.getMySQLTicketDAO();
    }

    public Flight findFlightById(Integer id){
        return flightDAO.findById(id);
    }

    public List<Flight> findFlightByDepartureAndDestinationAndDate(String departure,String destination,Date date){
        return  flightDAO.findByDepartureAndDestinationAndDate(departure,destination,date);
    }
    public List<Flight> findFlightByDepartureAndDestination(String departure,String destination){
        return flightDAO.findByDepartureAndDestination(departure,destination);
    }
    public List<Flight> findAll(){
       return flightDAO.findAll();
    }

    public List<Ticket> findAllTicketByUserId(Integer id){
        return ticketDAO.findByUserId(id);
    }

    public void createFlight(Flight flight){
        if(!findFlightByDepartureAndDestination(flight.getDeparture(),flight.getDestination()).isEmpty()){
            throw new RuntimeException("Dublicate flight");
        }
        flightDAO.create(flight);
        flight=flightDAO.findByDepartureAndDestinationAndDate(flight.getDeparture(),flight.getDestination(),flight.getDate()).get(0);
        ticketDAO.create(flight.getId(),flight.getTicketCount());
    }
    public List<Ticket> findAllTicketByFlightId(Integer id){
        return ticketDAO.findAllByFlightId(id);
    }
    public Double findCost(Integer flightId, Double startCost,Integer allTicketCount,Date flightDate){
        Integer avaibleTicket=ticketDAO.countAvaibleTicket(flightId);
            double buyTicketCoef=allTicketCount/avaibleTicket;
        Integer currentMonth=Calendar.getInstance().getTime().getMonth();
        Double coefDate=(flightDate.getMonth()-currentMonth)*0.05;
        return startCost+=startCost*coefDate*buyTicketCoef;
    }
    public  void deleteTicket(Integer ticketId){
        ticketDAO.delete(ticketId);
    }

    public void updateTicket(Ticket updateTicket){
        ticketDAO.update(updateTicket);
    }

}

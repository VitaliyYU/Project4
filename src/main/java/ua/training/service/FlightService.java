package ua.training.service;

import ua.training.dao.FlightDAO;
import ua.training.dao.TicketDAO;
import ua.training.dao.mysql.MySQLDAOFactory;
import ua.training.entity.Flight;
import ua.training.entity.Ticket;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


public class FlightService {
    private FlightDAO flightDAO;
    private TicketDAO ticketDAO;
    private static FlightService flightService=new FlightService();

    /**
     *
     * @return singleton instance of flight service
     */
    public static FlightService getInstance(){
        return flightService;
    }

    private FlightService(){
        flightDAO= MySQLDAOFactory.getMySQLFlightDAO();
        ticketDAO=MySQLDAOFactory.getMySQLTicketDAO();
    }

    /**
     *
     * @param departure start airplane position (town)
     * @param destination end airplane  position
     * @return all flight with  this parameter
     */
    public List<Flight> findFlightByDepartureAndDestination(String departure,String destination){
        return flightDAO.find(departure,destination);
    }

    /**
     * find all active flight
     * @return list of flight
     */
    public List<Flight> findAll(){
       return flightDAO.findAll();
    }

    /**
     *
     * @param id user  whom ticket we need to find
     * @return list of  ticket with current user_id
     */
    public List<Ticket> findAllTicketByUserId(Integer id){
        return ticketDAO.findByUserId(id);
    }

    /**
     * set new Flight in Database
     * @param flight new Flight
     */
    public void createFlight(Flight flight){
        if(!findFlightByDepartureAndDestination(flight.getDeparture(),flight.getDestination()).isEmpty()){
            throw new RuntimeException("Dublicate flight");
        }
        flightDAO.create(flight);
        flight=flightDAO.find(flight.getDeparture(),flight.getDestination(),flight.getDate()).get(0);
        ticketDAO.create(flight.getId(),flight.getTicketCount());
    }

    /**
     *
     * @param id flight  id whom ticket we need to find
     * @return list of ticket with flight  id equals {@id}
     */
    public List<Ticket> findAllTicketByFlightId(Integer id){
        return ticketDAO.findAllByFlightId(id);
    }

    /**
     *
     * @param flightId
     * @param startCost
     * @param allTicketCount Counter of all ticket
     * @param flightDate time when plane starting flying
     * @return
     */
    public Double findCost(Integer flightId, Double startCost,Integer allTicketCount,Date flightDate){
        Integer avaibleTicket=ticketDAO.countAvailableTicket(flightId);
            double buyTicketCoef=allTicketCount/avaibleTicket;
        Integer currentMonth=Calendar.getInstance().getTime().getMonth();
        Double coefDate=(flightDate.getMonth()-currentMonth)*0.05;
        return startCost+=startCost*coefDate*buyTicketCoef;
    }

    /**
     *
     * @param ticketId ticket id which  we need to delete
     */
    public  void deleteTicket(Integer ticketId){
        ticketDAO.delete(ticketId);
    }

    /**
     *
     * @param updateTicket ticket which we need update
     * @return true when ticket is update
     */
    public Boolean updateTicket(Ticket updateTicket){

        return ticketDAO.update(updateTicket);
    }

}

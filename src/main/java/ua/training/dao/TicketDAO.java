package ua.training.dao;

import ua.training.entity.Ticket;

import java.util.List;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface TicketDAO {
    void create(Ticket newTicket);

    /**
     *
     * @param flightId tickets flight id
     * @param n how  many ticket you want to generate
     */
    void create(Integer flightId,Integer n);

    /**
     * find ticket with  current {@id} user id
     * @param id user id
     * @return  all active ticket with  current {@userId}
     */
    List<Ticket> findByUserId(Integer id);

    /**
     * find ticket with current {@id}flight  id
     * @param flightId
     * @return all active ticket where flight id={@flightId}
     */
    List<Ticket> findAllByFlightId(Integer flightId);

    /**
     * counting  active ticket with  flight_id={@flightId}
     * @param flightId
     * @return count of  all avaible ticket with equals flightId
     */
    Integer countAvailableTicket(Integer flightId);
    Boolean update(Ticket updateTicket);
    void delete(Integer id);
}
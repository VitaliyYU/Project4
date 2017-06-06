package ua.training.dao;

import ua.training.entity.Ticket;

import java.util.List;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface TicketDAO {
    void create(Ticket newTicket);
    void create(Integer flightId,Integer n);
    List<Ticket> findByUserId(Integer id);
    List<Ticket> findAllByFlightId(Integer flightId);
    Integer countAvaibleTicket(Integer flightId);
    void update(Ticket updateTicket);
    void delete(Integer id);
}
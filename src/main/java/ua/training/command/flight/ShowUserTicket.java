package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.entity.Flight;
import ua.training.entity.Ticket;
import ua.training.entity.User;
import ua.training.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaliy on 06.06.17.
 */
public class ShowUserTicket implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user")==null) {
            request.setAttribute("command","toLogin");
        }
        User user=(User) request.getSession().getAttribute("user");
        FlightService flightService=FlightService.getInstance();
        List<Ticket> tickets=flightService.findAllTicketByUserId(user.getId());
        List<Flight> flights=flightService.findAll();
        request.setAttribute("tickets",tickets);
        request.setAttribute("flights",flights);
        return "userTicket";
    }
}

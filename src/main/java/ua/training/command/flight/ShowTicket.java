package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.Ticket;
import ua.training.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Created by vitaliy on 04.06.17.
 */
public class ShowTicket implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user")==null) {
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;
        }

        FlightService flightService=FlightService.getInstance();

        Integer allTicketCount=Integer.parseInt(request.getParameter("allTicketCount"));
        Double cost=Double.parseDouble(request.getParameter(Attributes.COST));
        Integer flightId=Integer.parseInt(request.getParameter("flight_id"));
        Date date=Date.valueOf(request.getParameter(Attributes.DATE));

        List<Ticket> tickets=flightService.findAllTicketByFlightId(flightId);

        request.setAttribute("tickets",tickets);
        request.setAttribute(Attributes.DATE,date);
        request.setAttribute(Attributes.COST,cost);
        request.setAttribute("allTicketCount",allTicketCount);
        request.setAttribute("command",Actions.CONFIRM);

        return Actions.TICKETS;
    }
}

package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.Flight;
import ua.training.entity.User;
import ua.training.service.FlightService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;


public class AddFlight implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute(Attributes.USERS);
        if(!user.getUserRole().getRoleName().equals(Attributes.ADMIN)){
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;
        }

        FlightService flightService = FlightService.getInstance();
        Flight flight = new Flight();
        try {
            String destination = request.getParameter(Attributes.DESTINATION);
            String departure = request.getParameter(Attributes.DEPARTURE);
            LocalDateTime date = LocalDateTime.parse(request.getParameter(Attributes.DATE));
            Double cost = Double.parseDouble(request.getParameter(Attributes.COST));
            Integer ticketsCount = Integer.parseInt(request.getParameter(Attributes.TICKETS_COUNT));


            flight.setDate(Date.valueOf(date.toLocalDate()));
            flight.setTicketCount(ticketsCount);
            flight.setStartCost(cost);
            flight.setDeparture(departure);
            flight.setDestination(destination);
            flightService.createFlight(flight);
        }catch (RuntimeException e){
            request.getSession().setAttribute(Attributes.ERROR_MESSAGE_NAME,e.getMessage());
        }

        request.setAttribute(Attributes.USER, UserService.getInstance().findAll());

        return "users";
    }
}

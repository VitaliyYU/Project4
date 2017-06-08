package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.Flight;
import ua.training.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaliy on 05.06.17.
 */
public class Search implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user")==null) {
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;
        }

        String departure=request.getParameter("from");
        String destination=request.getParameter("to");

        FlightService flightService=FlightService.getInstance();

        if(departure.equals("")||destination.equals("")){
            request.setAttribute(Attributes.ERROR_MESSAGE_NAME,"Field is empty");
            request.setAttribute(Actions.FLIGHT_COMMAND,flightService.findAll());
        }else {
            List<Flight> flights = flightService.findFlightByDepartureAndDestination(departure, destination);
            request.setAttribute(Actions.FLIGHT_COMMAND, flights);
        }
        return Actions.FLIGHT_COMMAND;

    }
}

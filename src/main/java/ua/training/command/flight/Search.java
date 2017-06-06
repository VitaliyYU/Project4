package ua.training.command.flight;

import ua.training.command.Command;
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
            request.setAttribute("command","toLogin");
        }
        String departure=request.getParameter("from");
        String destination=request.getParameter("to");
        FlightService flightService=FlightService.getInstance();

        if(departure.equals("")||destination.equals("")){
            request.setAttribute("error","Field is empty");
            request.setAttribute("flights",flightService.findAll());
        }else {
            List<Flight> flights = flightService.findFlightByDepartureAndDestination(departure, destination);
            request.setAttribute("flights", flights);
        }
        return "flights";

    }
}

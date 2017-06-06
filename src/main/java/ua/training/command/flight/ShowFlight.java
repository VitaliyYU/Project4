package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.entity.Flight;
import ua.training.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaliy on 04.06.17.
 */
public class ShowFlight implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user")==null) {
            request.setAttribute("command","toLogin");
        }
        FlightService flightService=FlightService.getInstance();
        List<Flight> flights=flightService.findAll();
        request.setAttribute("flights",flights);
        request.setAttribute("command","flights");
        return "flights";
    }
}

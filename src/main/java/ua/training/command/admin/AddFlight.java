package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.entity.Flight;
import ua.training.entity.User;
import ua.training.service.FlightService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Created by vitaliy on 05.06.17.
 */
public class AddFlight implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute("user");
        if(!user.getUserRole().getRoleName().equals("admin")){
            request.setAttribute("command","toLogin");
        }
        String destination=request.getParameter("destination");
        String departure=request.getParameter("departure");
        LocalDateTime date= LocalDateTime.parse( request.getParameter("date"));
        Double cost=Double.parseDouble(request.getParameter("cost"));
        Integer ticketsCount=Integer.parseInt(request.getParameter("ticketsCount"));
       FlightService flightService= FlightService.getInstance();
        Flight flight=new Flight();
        flight.setDate(Date.valueOf(date.toLocalDate()));
        flight.setTicketCount(ticketsCount);
        flight.setStartCost(cost);
        flight.setDeparture(departure);
        flight.setDestination(destination);
        try {
            flightService.createFlight(flight);
        }catch (RuntimeException e){
            request.getSession().setAttribute("error",e.getMessage());
        }
       request.setAttribute("users", UserService.getInstance().findAll());
       return "users";
    }
}

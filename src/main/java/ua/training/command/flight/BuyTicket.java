package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.entity.Ticket;
import ua.training.entity.User;
import ua.training.service.FlightService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 05.06.17.
 */
public class BuyTicket implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,NullPointerException {
        User user=(User)request.getSession().getAttribute("user");
        if(user==null) {
            request.setAttribute("command","toLogin");
        }
        Double cost=Double.parseDouble(request.getParameter("cost"));
        Integer ticketId=Integer.parseInt(request.getParameter("ticketId"));
        Integer place =Integer.parseInt(request.getParameter("place"));
        Integer baggage=0;
        if(!request.getParameter("baggage").equals("")){
            baggage=Integer.parseInt(request.getParameter("baggage"));
        }
        FlightService flightService=FlightService.getInstance();
        UserService userService=UserService.getInstance();

        Double result=new Double(cost);
        Ticket ticket=new Ticket();
        ticket.setId(ticketId);
        ticket.setPlace(place);

        if(request.getParameter("boarding")!=null) {
            result+=0.2*cost;
            ticket.setPriorityBoarding(true);
        }else{
            ticket.setPriorityBoarding(false);
        }
        if(request.getParameter("registration")!=null){
            result+=0.3*cost;
            ticket.setPriorityRegistration(true);
        }else{
            ticket.setPriorityRegistration(false);
        }
        if(baggage!=null){
            result+=20*baggage;
            ticket.setBaggage(baggage);
        }
        if(!user.withdraw(cost)){
            request.setAttribute("error","Not enough money!");
            request.setAttribute("cost",cost);
            request.setAttribute("command","buy");
            request.setAttribute("ticketId",ticketId);
            request.setAttribute("place",place);
            return "confirm";
        }else {
            ticket.setUserId(user.getId());

            flightService.updateTicket(ticket);
            userService.pay(user,cost);
            flightService.deleteTicket(ticketId);
        }
        request.setAttribute("flights",flightService.findAll());
        return "flights";
    }
}

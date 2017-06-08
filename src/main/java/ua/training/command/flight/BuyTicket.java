package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.Ticket;
import ua.training.entity.User;
import ua.training.service.FlightService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BuyTicket implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,NullPointerException {
        User user=(User)request.getSession().getAttribute("user");
        if(user==null) {
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;
        }
        Double cost=Double.parseDouble(request.getParameter(Attributes.COST));
        Integer ticketId=Integer.parseInt(request.getParameter("ticketId"));
        Integer place =Integer.parseInt(request.getParameter(Attributes.PLACE));
        Integer baggage=0;
        if(!request.getParameter("baggage").equals("")){
            try {
                baggage = Integer.parseInt(request.getParameter(Attributes.BAGGAGE));
            }catch (NumberFormatException e){
                request.setAttribute(Attributes.ERROR_MESSAGE_NAME,"Invalid number format");
                request.setAttribute(Attributes.COST,cost);
                request.setAttribute("command",Actions.BUY_TICKET_COMMAND);
                request.setAttribute("ticketId",ticketId);
                request.setAttribute(Attributes.PLACE,place);
                return Actions.CONFIRM;
            }
        }
        FlightService flightService=FlightService.getInstance();
        UserService userService=UserService.getInstance();

        Double result=new Double(cost);
        Ticket ticket=new Ticket();
        ticket.setId(ticketId);
        ticket.setPlace(place);

        if(request.getParameter(Attributes.BOARDING)!=null) {
            result+=Attributes.BOARDING_COEF*cost;
            ticket.setPriorityBoarding(true);
        }else{
            ticket.setPriorityBoarding(false);
        }
        if(request.getParameter("registration")!=null){
            result+=Attributes.REGISTRATRATION_COEF*cost;
            ticket.setPriorityRegistration(true);
        }else{
            ticket.setPriorityRegistration(false);
        }
        if(baggage!=null){
            result+=Attributes.BAGGAGE_COEF*cost*baggage;
            ticket.setBaggage(baggage);
        }
        if(!user.withdraw(cost)){
            request.setAttribute(Attributes.ERROR_MESSAGE_NAME,"Not enough money!");
            request.setAttribute(Attributes.COST,cost);
            request.setAttribute("command",Actions.BUY_TICKET_COMMAND);
            request.setAttribute("ticketId",ticketId);
            request.setAttribute(Attributes.PLACE,place);
            return Actions.CONFIRM;
        }else {
            ticket.setUserId(user.getId());

            if(flightService.updateTicket(ticket)) {
                userService.pay(user, result);
                flightService.deleteTicket(ticketId);
            }else {
                request.setAttribute(Attributes.ERROR_MESSAGE_NAME,"this  ticket is close!");
            }
        }
        request.setAttribute(Attributes.COST,result);

        return "ok";
    }
}

package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by vitaliy on 04.06.17.
 */
public class Confirm implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException ,NumberFormatException{
        if(request.getSession().getAttribute("user")==null) {
            request.setAttribute("command","toLogin");
        }
        Integer ticketId=Integer.parseInt(request.getParameter("ticketId"));
        Integer place= Integer.parseInt(request.getParameter("place"));
        Double startCost=Double.parseDouble(request.getParameter("cost"));
        Integer allTicketCount=Integer.parseInt(request.getParameter("allTicketCount"));
        Date date=Date.valueOf(request.getParameter("date"));
        FlightService flightService=FlightService.getInstance();
        Double cost=flightService.findCost(ticketId,startCost,allTicketCount,date);
        request.setAttribute("cost",cost);
        request.setAttribute("place",place);
        request.setAttribute("ticketId",ticketId);
        request.setAttribute("command","confirm");
        return "confirm";
    }
}

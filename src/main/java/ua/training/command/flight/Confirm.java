package ua.training.command.flight;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
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
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;
        }

        Integer ticketId=Integer.parseInt(request.getParameter("ticketId"));
        Integer place= Integer.parseInt(request.getParameter(Attributes.PLACE));
        Double startCost=Double.parseDouble(request.getParameter(Attributes.COST));
        Integer allTicketCount=Integer.parseInt(request.getParameter("allTicketCount"));
        Date date=Date.valueOf(request.getParameter(Attributes.DATE));

        FlightService flightService=FlightService.getInstance();

        Double cost=flightService.findCost(ticketId,startCost,allTicketCount,date);

        request.setAttribute(Attributes.COST,cost);
        request.setAttribute(Attributes.PLACE,place);
        request.setAttribute("ticketId",ticketId);
        request.setAttribute("command",Actions.CONFIRM);

        return Actions.CONFIRM;
    }
}

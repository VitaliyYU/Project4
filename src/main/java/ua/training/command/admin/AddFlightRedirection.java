package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 05.06.17.
 */
public class AddFlightRedirection implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute(Attributes.USERS);
        if(!user.getUserRole().getRoleName().equals(Attributes.ADMIN)){
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
        }
        return Actions.ADD_FLIGHT;
    }
}

package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut  implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession(Attributes.NO_SESSION_CREATING) != null) {
            request.getSession().invalidate();
        }
        return "login";
    }
}

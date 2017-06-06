package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowUsers implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute("user");
        if(!user.getUserRole().getRoleName().equals("admin")){
            request.setAttribute("command","toLogin");
        }
        return "users";
    }
}

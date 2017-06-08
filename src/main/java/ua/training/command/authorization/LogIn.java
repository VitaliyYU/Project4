package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Attributes;
import ua.training.entity.User;
import ua.training.service.FlightService;
import ua.training.service.UserService;
import ua.training.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogIn  implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = getUsernameFromRequest(request);
        String password = getPasswordFromRequest(request);

        UserValidator validator = new UserValidator();
        boolean  validLogin=validator.isValidLogin(username);
        boolean validPassword=validator.isValidPassword(password);

        if(!validLogin||!validPassword){
            request.setAttribute("error","Invalid  login or password");
            request.setAttribute("command","toLogin");
            return "login";
        }

        User user=null;
        user=userService.verifyUser(username,password);

        if(user==null) {
            request.setAttribute("error","Invalid  login or password");
            request.setAttribute("command","toLogin");
            return "login";
        }

        request.getSession().setAttribute(Attributes.USERS, user);

        if(user.getUserRole().getRoleName().equals("admin")){
            UserService userService=UserService.getInstance();
            List<User> users=userService.findAll();
             request.setAttribute("users",users);
             request.setAttribute("command","showUsers");
             return "users";
        }

        FlightService flightService=FlightService.getInstance();
        request.setAttribute("flights",flightService.findAll());
         return "flights";
    }

    private String getPasswordFromRequest(HttpServletRequest request) {
        return request.getParameter(Attributes.PASSWORD);
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        return request.getParameter(Attributes.LOGIN);
    }
}

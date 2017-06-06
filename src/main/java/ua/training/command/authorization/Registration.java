package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.User;
import ua.training.service.UserService;
import ua.training.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration  implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User client=new User();
        client.setLogin(request.getParameter(Attributes.LOGIN));
        client.setEmail(request.getParameter(Attributes.EMAIL));
        client.setName(request.getParameter(Attributes.NAME));
        client.setSurname(request.getParameter(Attributes.SURNAME));
        client.setPassword(request.getParameter(Attributes.PASSWORD));
        if(client==null)return Actions.REGISTRATION;
        UserValidator validator = new UserValidator();
        if (!validator.isValid(client)) {
            request.getSession().setAttribute(Attributes.ERROR_MESSAGE_NAME,"Invalid data");
            request.setAttribute("command","toRegister");
            return "registration";
        }
        try {
            userService.addUser(client);
        } catch (RuntimeException e) {
            request.getSession().setAttribute(Attributes.ERROR_MESSAGE_NAME,"Invalid data");
            request.setAttribute("command","toRegister");
            return "registration";
        }
        request.setAttribute("command","toLogin");
        return "login";
    }

}

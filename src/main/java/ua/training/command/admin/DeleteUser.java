package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DeleteUser implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute(Attributes.USERS);

        if(!user.getUserRole().getRoleName().equals(Attributes.ADMIN)){
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;

        }
        Integer id=Integer.parseInt(request.getParameter(Attributes.USER_ID));

        UserService userService=UserService.getInstance();
        userService.deleteUser(id);

        request.setAttribute("command",Actions.SHOW_USER);
        List<User> users=userService.findAll();

        request.setAttribute("users",users);

        return Attributes.USER;
    }
}

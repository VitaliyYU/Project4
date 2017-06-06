package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaliy on 05.06.17.
 */
public class DeleteUser implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute("user");
        if(!user.getUserRole().getRoleName().equals("admin")){
            request.setAttribute("command","toLogin");
        }
        Integer id=Integer.parseInt(request.getParameter("userId"));
        UserService userService=UserService.getInstance();
        userService.deleteUser(id);
        request.setAttribute("command","showUser");
        List<User> users=userService.findAll();
        request.setAttribute("users",users);
        return "users";
    }
}

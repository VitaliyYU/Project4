package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 05.06.17.
 */
public class RefillWallet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute("user");
        if(!user.getUserRole().getRoleName().equals("admin")){
            request.setAttribute("command","toLogin");
        }
        String code=request.getParameter("code");
        UserService userService=UserService.getInstance();
        userService.reffil(code,Double.MAX_VALUE);
        request.setAttribute("users",userService.findAll());
        return "users";
    }
}

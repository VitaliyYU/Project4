package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.entity.User;
import ua.training.entity.Wallet;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaliy on 05.06.17.
 */
public class RefilRedirect implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute("user");
        if(!user.getUserRole().getRoleName().equals("admin")){
            request.setAttribute("command","toLogin");
        }
        List<Wallet> wallet= UserService.getInstance().findAllWallets();
        request.setAttribute("wallets",wallet);
        return "refill";
    }
}

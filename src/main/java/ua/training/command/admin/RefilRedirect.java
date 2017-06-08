package ua.training.command.admin;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;
import ua.training.entity.User;
import ua.training.entity.Wallet;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class RefilRedirect implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =(User)request.getSession().getAttribute("user");

        if(!user.getUserRole().getRoleName().equals(Attributes.ADMIN)){
            request.setAttribute("command", Actions.LOGIN_REDIRECT);
            return Actions.LOGIN;
        }

        List<Wallet> wallet= UserService.getInstance().findAllWallets();

        request.setAttribute(Attributes.WALLETS,wallet);

        return Actions.REFILL;
    }
}

package ua.training.command.wallet;

import ua.training.command.Command;
import ua.training.entity.User;
import ua.training.entity.Wallet;
import ua.training.service.FlightService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 06.06.17.
 */
public class AddWallet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code=request.getParameter("code");
        UserService userService=UserService.getInstance();
        userService.createWallet(code);
        User user=(User)request.getSession().getAttribute("user");
        Wallet wallet=userService.findWalletByCode(code);
        user.setWallet(wallet);
        userService.update(user);
        request.getSession().setAttribute("user",user);
        request.setAttribute("flights", FlightService.getInstance().findAll());
        return "flights";
    }
}

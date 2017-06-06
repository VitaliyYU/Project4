package ua.training.command.wallet;

import ua.training.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 06.06.17.
 */
public class AddWalletRedirection implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "addWallet";
    }
}

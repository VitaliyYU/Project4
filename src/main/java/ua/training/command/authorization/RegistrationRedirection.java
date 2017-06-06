package ua.training.command.authorization;

import ua.training.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationRedirection implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "registration";
    }
}

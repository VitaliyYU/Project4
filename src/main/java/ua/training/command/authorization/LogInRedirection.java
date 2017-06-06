package ua.training.command.authorization;

import ua.training.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInRedirection implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }
}

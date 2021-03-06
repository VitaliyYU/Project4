package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInRedirection implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Actions.LOGIN;
    }
}

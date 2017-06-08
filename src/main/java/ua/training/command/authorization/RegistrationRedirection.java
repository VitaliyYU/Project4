package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationRedirection implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Actions.REGISTRATION_COMMAND;
    }
}

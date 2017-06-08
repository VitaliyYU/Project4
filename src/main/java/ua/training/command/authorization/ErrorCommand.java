package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return Attributes.ERROR_MESSAGE_NAME;
    }
}

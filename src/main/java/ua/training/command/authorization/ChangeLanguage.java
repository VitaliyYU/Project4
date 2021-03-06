package ua.training.command.authorization;

import ua.training.command.Command;
import ua.training.constant.Actions;
import ua.training.constant.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLanguage implements Command {

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String language=request.getParameter(Attributes.LANGUAGE);

        request.getSession().setAttribute(Attributes.LOCALE,language);


        return Actions.LOGIN;
    }
}

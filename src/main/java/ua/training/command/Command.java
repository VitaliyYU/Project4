package ua.training.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vitaliy on 23.05.17.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

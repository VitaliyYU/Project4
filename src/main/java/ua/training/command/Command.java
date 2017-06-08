package ua.training.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface Command {
    /**
     *
     * @param request
     * @param response
     * @return Pagename
     * @throws IOException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

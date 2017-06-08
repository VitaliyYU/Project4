package ua.training;

import ua.training.command.Command;
import ua.training.command.factory.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="airport",urlPatterns = "/airport/*")
public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException {
        requestDispatcher(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        requestDispatcher(request, response);

    }

    protected   void requestDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Command command=CommandFactory.getCommand(request);
        String viewName=command.execute(request,response);
        dispatch(request,response,viewName);

    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        String path = "/WEB-INF/views/";
        String extension = ".jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path + view + extension);
        rd.forward(request, response);
    }
}

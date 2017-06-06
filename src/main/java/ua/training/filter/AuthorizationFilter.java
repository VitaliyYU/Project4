package ua.training.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by vitaliy on 03.06.17.
 */public class AuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       String command=request.getParameter("command");
       if(command==null) {
           request.setAttribute("command","toLogin");
       }
       chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

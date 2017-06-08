package ua.training.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
public class AuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       String command=request.getParameter("command");
        if(((HttpServletRequest)request).getSession().getAttribute("user")==null && command!="toLogin"){
            ( request).getRequestDispatcher("index.jsp").forward(request,response);
        }
       chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

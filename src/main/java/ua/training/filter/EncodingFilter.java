package ua.training.filter;

import javax.servlet.*;
import java.io.IOException;


    public class EncodingFilter implements Filter {

        public static final String UTF_8 = "UTF-8";

        @Override
        public void init(FilterConfig filterConfig) throws ServletException { }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            servletRequest.setCharacterEncoding(UTF_8);
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {

        }

    }

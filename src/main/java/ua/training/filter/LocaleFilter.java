package ua.training.filter;

import ua.training.constant.Attributes;
import ua.training.i18n.ProgramLocale;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by vitaliy on 03.06.17.
 */
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ProgramLocale locale = searchLocaleFromRequest(request);
        if (Objects.isNull(locale)) {
            locale = ProgramLocale.DEFAULT_LOCALE;
        }
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.LOCALE, locale.getLocale());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private ProgramLocale searchLocaleFromRequest(ServletRequest servletRequest) {
        String localeString = servletRequest.getParameter(Attributes.LOCALE);
        if (Objects.isNull(localeString)) {
            return null;
        }
        Locale locale;
        for (ProgramLocale programLocale :ProgramLocale.values()) {
            if (programLocale.toString().equals(localeString)) {
                return programLocale;
            }
        }
        return null;
    }

    @Override
    public void destroy() { }
}

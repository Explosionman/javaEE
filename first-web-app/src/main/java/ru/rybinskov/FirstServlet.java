package ru.rybinskov;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private static final Logger logger = Logger.getLogger(FirstServlet.class);
    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("FirstServlet is initialized");
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request to FirstServlet");
        servletResponse.getWriter().println("<h1><b>Hello from servlet!</b></h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

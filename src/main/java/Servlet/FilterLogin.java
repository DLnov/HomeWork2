package Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FilterLogin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/enter";
        String regURI = req.getContextPath() + "/registry";


        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean regRequest = req.getRequestURI().equals(regURI);

        if(loggedIn || loginRequest || regRequest){
            chain.doFilter(request, response);
        }else{
            resp.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}

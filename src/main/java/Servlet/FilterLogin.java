package Servlet;

import Model.Model;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FilterLogin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if(session.getAttribute("username") == null
                && request.getAttribute("username") == null){
            request.getServletContext().getRequestDispatcher("/enter.jsp").forward(request, response);

        }else if(request.getAttribute("username") != null &&
                session.getAttribute("username") == null){
            Model model = new Model();
            String s1 = (String)request.getAttribute("username");
            String s2 = (String)request.getAttribute("password");
            if(!model.haveUser(s1, s2)){
                request.getServletContext().getRequestDispatcher("/enter.jsp").forward(request, response);
            }else{
                session.setAttribute("username", s1);
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}

package Servlet;

import Exceptions.ExceptionForUser;
import Model.LogicBack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class EnterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/enter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        boolean isFull = true;
        if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
            isFull = false;
        }
        try {
            if (isFull) {
                LogicBack logicBack;
                if ((logicBack = (LogicBack) session.getAttribute("backLogic")) == null) {
                    logicBack = new LogicBack();
                    session.setAttribute("model", logicBack);
                }
                if (logicBack.haveUser(user, pass)) {
                    session.setAttribute("username", user);
                    resp.sendRedirect(req.getContextPath() + "/home");
                    return;
                }
            }
        }catch (ExceptionForUser e){
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
        req.getServletContext().getRequestDispatcher("/jsp/enter.jsp").forward(req, resp);
    }
}

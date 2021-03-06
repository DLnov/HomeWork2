package Servlet;

import Exceptions.ExceptionForUser;
import Model.LogicBack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class RegistryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registry.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> userDatas = new HashMap<>();
        Enumeration<String> enu = req.getParameterNames();
        while (enu.hasMoreElements()) {
            String s = enu.nextElement();
            userDatas.put(s, req.getParameter(s));
        }
        HttpSession session = req.getSession();
        session.removeAttribute("error");
        try {
            LogicBack logicBack;
            if ((logicBack = (LogicBack) session.getAttribute("backLogic")) == null) {
                logicBack = new LogicBack();
                session.setAttribute("model", logicBack);
            }
            if (logicBack.addUser(userDatas)) {
                session.setAttribute("username", req.getParameter("username"));
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }else {
                session.setAttribute("error", "Enter new username or email!");
            }
        }catch (ExceptionForUser e){
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/jsp/registry.jsp").forward(req, resp);

    }
}






















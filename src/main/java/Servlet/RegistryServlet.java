package Servlet;

import Model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by INNO on 27.12.2016.
 */
public class RegistryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registry.jsp").forward(req, resp);
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
        Model model;
        if ((model = (Model) session.getAttribute("model")) == null) {
            model = new Model();
            session.setAttribute("model", model);
        }
        if(model.addUser(userDatas, session)){
            session.setAttribute("username", req.getParameter("username"));
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.getRequestDispatcher("/registry.jsp").forward(req, resp);

    }
}






















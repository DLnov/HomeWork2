package Servlet;

import Model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;


public class EnterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/enter.jsp").forward(req, resp);
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

        if (isFull) {
            Model model;
            if ((model = (Model) session.getAttribute("model")) == null) {
                model = new Model();
                session.setAttribute("model", model);
            }
            if (model.haveUser(user, pass)) {
                session.setAttribute("username", user);
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }
        req.getServletContext().getRequestDispatcher("/enter.jsp").forward(req, resp);
    }
}

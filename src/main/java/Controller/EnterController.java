package Controller;

import Exceptions.ExceptionForUser;
import Model.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SuppressWarnings("Duplicates")
@Controller
@RequestMapping("/enter")
public class EnterController {

    private LoginService loginLogic;

    @Autowired
    public void setLoginLogic(LoginService loginLogic) {
        this.loginLogic = loginLogic;
    }

    @RequestMapping(method = GET)
    protected String doGet() {
        return "enter";
    }

    @RequestMapping(method = POST)
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user1.getUsername();
        boolean isFull = true;
        if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
            isFull = false;
        }
        try {
            if (isFull) {
                if (session.getAttribute("loginLogic") != null) {
                    loginLogic = (LoginService) session.getAttribute("loginLogic");
                } else {
                    session.setAttribute("loginLogic", loginLogic);
                }
                if (loginLogic.haveUser(user, pass)) {
                    session.setAttribute("username", user);
                    response.sendRedirect("/home");
                    return "home";
                }
            }
        } catch (ExceptionForUser e) {
            return "error";
        }
        return "enter";
    }
}

package Controller;

import Exceptions.ExceptionForUser;
import Model.BackLogic;
import Model.Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SuppressWarnings("Duplicates")
@Controller
@RequestMapping("/registry")
public class RegistryController {

    private Logic backLogic;
    @Autowired
    public void setBackLogic(Logic backLogic) {
        this.backLogic = backLogic;
    }

    @RequestMapping(method = GET)
    protected String doGet() {
        return "registry";
    }

    @RequestMapping(method = POST)
    protected String doPost(HttpServletRequest request) {
        Map<String, String> userDatas = new HashMap<>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String s = enu.nextElement();
            userDatas.put(s, request.getParameter(s));
        }
        HttpSession session = request.getSession();
        session.removeAttribute("error");
        try {
            if(backLogic != session.getAttribute("backLogic")) {
                backLogic = (Logic) session.getAttribute("backLogic");
            }else {
                session.setAttribute("backLogic", backLogic);
            }
            if (backLogic.addUser(userDatas)) {
                session.setAttribute("username", request.getParameter("username"));
                return "home";
            } else {
                session.setAttribute("error", "Enter new username or email!");
            }
        } catch (ExceptionForUser e) {
            return "error";
        }
        return "registry";
    }
}

package Controller;

import Exceptions.ExceptionForUser;
import Model.*;
import POJO.Role;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private UserService userLogic;

    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String doGet(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogic") != null) {
            userLogic = (UserService) session.getAttribute("userLogic");
        }
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        try {
            Role unit = userLogic.getProfile(username);
            List<String> list = unit.getFields();
            model.addAttribute("role", list);

            JSONObject jsonObject = userLogic.getJSONObject(unit);
            //String s = jsonObject.toString().replaceAll("\"", "\'");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            model.addAttribute("arrays", jsonObject);
        } catch (ExceptionForUser exceptionForUser) {
            return "error";
        }
        return "home";
    }

    @RequestMapping("/error")
    public String doError(HttpServletRequest request){
        return "error";
    }
    @RequestMapping("/403")
    public String doDenied(){
        return "403";
    }

    @Autowired
    public void setUserLogic(UserService userLogic) {
        this.userLogic = userLogic;
    }
}

package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Model {
    public static final Logger logger = LoggerFactory.getLogger(Model.class);

    private DB db;

    public Model() {
        db = new DB();
        try {
            db.connectToDB();
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
        }
    }

    public boolean addUser(Map<String, String> userDatas, HttpSession session) {
        if(haveAccountDatas(userDatas.get("username"), userDatas.get("email"))){
            session.setAttribute("error", "Enter new username or email!");
            return false;
        }
        try {
            db.getConnection().setAutoCommit(false);
            Map<String, String> map = new HashMap<>();
            map.put("username", userDatas.get("username"));
            map.put("password", userDatas.get("password"));
            map.put("role", userDatas.get("role"));
            map.put("email", userDatas.get("email"));
            db.addNote(map, "usertable");
            map.clear();
            map.put("name", userDatas.get("name"));
            map.put("lastname", userDatas.get("lastname"));
            try (ResultSet resultSet = db.getNote
                    ("username", userDatas.get("username"), "usertable", "id")) {
                resultSet.next();
                map.put("user_id", resultSet.getString(1));
            }
            db.addNote(map, userDatas.get("role"));
            db.getConnection().commit();
            db.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
            try {
                db.getConnection().rollback();
            } catch (SQLException e1) {
                logger.warn(e.getLocalizedMessage(), e);
            }
            return false;
        }
        return true;

    }

    public boolean haveAccountDatas(String username, String email) {
        boolean haveUser = false;
        boolean haveEmail = false;
        try {
            haveUser = db.haveNote("username", username, "usertable");
            haveEmail = db.haveNote("email", email, "usertable");
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
        }
        return haveUser || haveEmail;
    }

    public boolean haveUser(String username, String password) {
        boolean result = false;
        try {
            result = db.isUser(username, password);
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
        }
        return result;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public static void main(String[] args) {
        boolean b = new Model().haveAccountDatas("ivan4", "mail4@mail.ru");
        System.out.println(b);
    }
}

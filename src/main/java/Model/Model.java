package Model;

import Exceptions.ExceptionForUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Model {
    public static final Logger logger = LoggerFactory.getLogger(Model.class);

    private DB db;

    public Model() throws ExceptionForUser {
        db = new DB();
    }

    public boolean addUser(Map<String, String> userDatas, HttpSession session) throws ExceptionForUser {
        if (haveAccountDatas(userDatas.get("username"), userDatas.get("email"))) {
            session.setAttribute("error", "Enter new username or email!");
            return false;
        }
        try (Connection connection = db.getConnection()){
            connection.setAutoCommit(false);
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
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
            try {
                db.getConnection().rollback();
            } catch (SQLException e1) {
                logger.warn(e.getLocalizedMessage(), e);
            }
            throw new ExceptionForUser();
        }finally {
            db.switchConnectionMode();
        }
        return true;

    }

    public boolean haveAccountDatas(String username, String email) throws ExceptionForUser{
        boolean haveUser = false;
        boolean haveEmail = false;
        try (Connection connection = db.getConnection()){
            haveUser = db.haveNote("username", username, "usertable");
            haveEmail = db.haveNote("email", email, "usertable");
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
            throw new ExceptionForUser();
        }finally {
            db.switchConnectionMode();
        }
        return haveUser || haveEmail;
    }

    public boolean haveUser(String username, String password) throws ExceptionForUser{
        boolean result = false;
        try {
            result = db.isUser(username, password);
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
            throw new ExceptionForUser();
        }finally {
            try {
                db.getSimlpeConnection().close();
            } catch (SQLException e) {
                logger.warn(e.getLocalizedMessage(), e);
            }
        }
        return result;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public static void main(String[] args) {

    }
}

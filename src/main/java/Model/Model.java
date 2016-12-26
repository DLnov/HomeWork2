package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;


public class Model {
    public static final Logger logger = LoggerFactory.getLogger(Model.class);

    private DB db;
    public Model(){
        db = new DB();
        try {
            db.connectToDB();
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
        }
    }

    public boolean haveUser(String username, String password){
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
}

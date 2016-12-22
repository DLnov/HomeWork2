import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DB {
    public static Logger logger = LoggerFactory.getLogger(DB.class);
    private Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String DB_URL = "jdbc:postgresql://localhost:5432/HomeWork2";

    public void connectToDB(){

    }
}

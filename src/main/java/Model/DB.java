package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DB {
    public static Logger logger = LoggerFactory.getLogger(DB.class);
    private static PreparedStatement prepIsUser;
    public static String DB_URL = "jdbc:postgresql://localhost:5432/HomeWork2";

    private Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.warn(e.getLocalizedMessage(), e);
        }
    }


    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, "postgres", "1234");
        prepIsUser = connection.prepareStatement
                ("SELECT * FROM usertable WHERE username=? and password=?");
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isUser(String name, String password) throws SQLException {
        prepIsUser.setString(1, name);
        prepIsUser.setString(2, password);
        try(ResultSet resultSet = prepIsUser.executeQuery()) {
            return resultSet.next();
        }
    }

    public static void main(String[] args) {
        DB db = new DB();

        try {
            db.connectToDB();
            System.out.println(db.isUser("ivan9", "ivan7"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

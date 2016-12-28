package Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;

public class DB {
    public static Logger logger = LoggerFactory.getLogger(DB.class);
    private static PreparedStatement prepIsUser;
    private Statement statement;
    public static String DB_URL = "jdbc:postgresql://localhost:5432/HomeWork2";

    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.warn(e.getLocalizedMessage(), e);
        }
    }


    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, "postgres", "1234");
        prepIsUser = connection.prepareStatement
                ("SELECT * FROM usertable WHERE username=? AND password=?");
        statement = connection.createStatement();

    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getNote
            (String checkField, String value, String tbName, String... resultField) throws SQLException {
        StringBuilder result = new StringBuilder();
        for(String s : resultField){
            result.append(s);
            result.append(", ");
        }
        result.delete(result.length()-2, result.length());
        String s = "SELECT " + result.toString() + " FROM " + tbName + "" +
                " WHERE " + checkField + "='" + value + "'";
        return statement.executeQuery(s);
    }

    public boolean addNote(Map<String, String> userDatas, String tbName) throws SQLException {
        StringBuilder end = new StringBuilder(") VALUES (");
        StringBuilder start = new StringBuilder("INSERT INTO ");
        start.append(tbName);
        start.append(" (");
        for (Map.Entry<String, String> me : userDatas.entrySet()) {
            start.append(me.getKey());
            start.append(", ");
            end.append("'");
            end.append(me.getValue());
            end.append("', ");
        }
        start.delete(start.length() - 2, start.length());
        end.delete(end.length() - 2, end.length());
        end.append(")");
        statement.executeUpdate(start.toString() + end.toString());
        return true;
    }

    public boolean isUser(String name, String password) throws SQLException {
        prepIsUser.setString(1, name);
        prepIsUser.setString(2, password);
        try (ResultSet resultSet = prepIsUser.executeQuery()) {
            return resultSet.next();
        }
    }

    public boolean haveNote(String param, String value, String tbName) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery
                ("SELECT * FROM " + tbName + " WHERE " + param + "='" + value + "'")) {
            return resultSet.next();
        }
    }

    public static void main(String[] args) {
        DB db = new DB();

        try {
            db.connectToDB();
            System.out.println(db.haveNote
                    ("username", "ivan9", "usertable"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

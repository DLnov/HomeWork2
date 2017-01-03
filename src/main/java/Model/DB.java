package Model;

import Exceptions.ExceptionForUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class DB {
    public static Logger logger = LoggerFactory.getLogger(DB.class);
    private DataSource ds;
    private Connection connection;
    private boolean takeNewCon = true;

    public DB() throws ExceptionForUser {
        try {
            InitialContext cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            logger.warn(e.getLocalizedMessage(), e);
            throw new ExceptionForUser();
        }
    }


    public Connection getConnection() throws SQLException {
        connection = ds.getConnection();
        takeNewCon = false;
        return connection;
    }
    public Connection getSimlpeConnection(){
        return connection;
    }

    public void switchConnectionMode(){
        takeNewCon = (takeNewCon) ? false : true;
    }

    public ResultSet getNote
            (String checkField, String value, String tbName, String... resultField) throws SQLException {
        if (takeNewCon)
            connection = ds.getConnection();
        Statement statement = connection.createStatement();
        StringBuilder result = new StringBuilder();
        for (String s : resultField) {
            result.append(s);
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        String s = "SELECT " + result.toString() + " FROM " + tbName + "" +
                " WHERE " + checkField + "='" + value + "'";
        return statement.executeQuery(s);
    }

    public boolean addNote(Map<String, String> userDatas, String tbName) throws SQLException {
        if (takeNewCon)
            connection = ds.getConnection();
        Statement statement = connection.createStatement();
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
        if (takeNewCon)
            connection = ds.getConnection();
        PreparedStatement prepIsUser = connection.prepareStatement
                ("SELECT * FROM usertable WHERE username=? AND password=?");
        prepIsUser.setString(1, name);
        prepIsUser.setString(2, password);
        try (ResultSet resultSet = prepIsUser.executeQuery()) {
            return resultSet.next();
        }
    }

    public boolean haveNote(String param, String value, String tbName) throws SQLException {
        if (takeNewCon)
            connection = ds.getConnection();
        Statement statement = connection.createStatement();
        try (ResultSet resultSet = statement.executeQuery
                ("SELECT * FROM " + tbName + " WHERE " + param + "='" + value + "'")) {
            return resultSet.next();
        }
    }

    public static void main(String[] args) {

    }

}

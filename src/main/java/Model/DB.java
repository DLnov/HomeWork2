package Model;

import Exceptions.ExceptionForUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class DB implements DataBaseSQL {
    public static Logger logger = LoggerFactory.getLogger(DB.class);
    private DataSource ds;
    private Connection connection;

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
        return connection;

    }

    public Connection getSimpleConnection() {
        return connection;
    }

    public ResultSet getNote(String expression) throws SQLException {
        ResultSet resultSet;
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(expression);

        return resultSet;
    }

    public ResultSet getNote
            (String checkField, String value, String tbName, String... resultField) throws SQLException {
        ResultSet resultSet;
        Statement statement = connection.createStatement();
        StringBuilder result = new StringBuilder();
        for (String s : resultField) {
            result.append(s);
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        String s = "SELECT " + result.toString() + " FROM " + tbName + "" +
                " WHERE " + checkField + "='" + value + "'";
        resultSet = statement.executeQuery(s);

        return resultSet;

    }

    public boolean addNote(Map<String, String> userDatas, String tbName) throws SQLException {
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
        PreparedStatement prepIsUser = connection.prepareStatement
                ("SELECT * FROM usertable WHERE username=? AND password=?");
        prepIsUser.setString(1, name);
        prepIsUser.setString(2, password);
        ResultSet resultSet = prepIsUser.executeQuery();
        return resultSet.next();
    }

    public boolean haveNote(String param, String value, String tbName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery
                ("SELECT * FROM " + tbName + " WHERE " + param + "='" + value + "'");
        return resultSet.next();


    }


    public static void main(String[] args) {

    }


}

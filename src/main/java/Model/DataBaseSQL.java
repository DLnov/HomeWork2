package Model;


import Exceptions.ExceptionForUser;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
@Component
public interface DataBaseSQL {
    Connection getConnection() throws SQLException;
    Connection getSimpleConnection();
    ResultSet getNote(String expression) throws SQLException;
    ResultSet getNote
            (String checkField, String value, String tbName, String... resultField) throws SQLException;
    boolean addNote(Map<String, String> userDatas, String tbName) throws SQLException;
    boolean isUser(String name, String password) throws SQLException;
    boolean haveNote(String param, String value, String tbName) throws SQLException;

}

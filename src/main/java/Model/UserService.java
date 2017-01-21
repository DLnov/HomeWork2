package Model;

import Exceptions.ExceptionForUser;
import POJO.Role;
import POJO.Teacher;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


public interface UserService {
    public JSONObject getJSONObject(Role role);

    public Teacher getProfileTeacher(Map<String, String> map, Connection connection) throws SQLException;

    public Role getProfile(String username) throws ExceptionForUser;

    public void setDb(DataBaseSQL db);
}

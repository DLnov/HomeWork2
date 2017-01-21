package Model;


import Exceptions.ExceptionForUser;
import POJO.Role;
import POJO.Teacher;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
@Component
public interface Logic {


    public JSONObject getJSONObject(Role role);

    public Teacher getProfileTeacher(Map<String, String> map, Connection connection) throws SQLException;

    public Role getProfile(String username) throws ExceptionForUser;

    public boolean addUser(Map<String, String> userDatas) throws ExceptionForUser;

    public boolean haveAccountDatas(String username, String email) throws ExceptionForUser;

    public boolean haveUser(String username, String password) throws ExceptionForUser;

    public void setDb(DataBaseSQL db);
}

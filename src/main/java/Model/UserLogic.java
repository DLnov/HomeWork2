package Model;

import Exceptions.ExceptionForUser;
import POJO.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserLogic implements UserService{
    public static final Logger logger = LoggerFactory.getLogger(LogicBack.class);

    private DataBaseSQL db;

    public JSONObject getJSONObject(Role role) {
        JSONObject jsonObject = new JSONObject();
        switch (role.getRoleType()) {
            case "teacher":
                Teacher teacher = (Teacher) role;
                jsonObject.put("tests", new JSONArray(teacher.getTestWorksString()));
                jsonObject.put("pupils", new JSONArray(teacher.getGroupsString()));
                break;
            case "pupil":
                break;
        }

        return jsonObject;
    }

    public Teacher getProfileTeacher(Map<String, String> map, Connection connection) throws SQLException {
        String user_id = map.get("id");
        int id = Integer.parseInt(user_id);
        ResultSet resultSet = db.getNote
                ("user_id", user_id, "teacher",
                        "name", "lastname");
        while (resultSet.next()) {
            map.put("name", resultSet.getString(1));
            map.put("lastname", resultSet.getString(2));
        }
        Teacher teacher = new Teacher(map.get("username"), map.get("name"), map.get("lastname"),
                map.get("email"), id);

        resultSet = db.getNote("teacher_id", user_id, "groups",
                "id", "groupname");
        Map<Integer, Group> groups = null;
        while (resultSet.next()) {
            groups = new HashMap<>();
            groups.put(resultSet.getInt("id"),
                    new Group(resultSet.getInt("id"),
                            id, resultSet.getString("groupname")));
            teacher.getGroups().addAll(groups.values());
        }

        if (groups != null) {
            resultSet = db.getNote("SELECT pupil.name, pupil.lastname," +
                    " pupil.group_id, usertable.email, usertable.username" +
                    " FROM usertable, pupil\n" +
                    "WHERE pupil.group_id\n" +
                    "IN (SELECT groups.id FROM groups WHERE groups.teacher_id=" + user_id + ")\n" +
                    " AND pupil.user_id=usertable.id");
            while (resultSet.next()) {
                int group_id = resultSet.getInt("group_id");
                groups.get(group_id).add(new Pupil(resultSet.getString("username"),
                        resultSet.getString("name"), resultSet.getString("lastname"),
                        resultSet.getString("email"), group_id));
            }
        }

        resultSet = db.getNote("teacher_id", user_id, "tests",
                "nametest", "theme", "datecreate", "datas");

        List<TestWork> tests;
        while (resultSet.next()) {
            tests = new ArrayList<>();
            tests.add(new TestWork(resultSet.getString("nametest"),
                    resultSet.getString("theme"), resultSet.getDate("datecreate"),
                    resultSet.getString("datas")));
            teacher.setTestWorks(tests);
        }
        return teacher;


    }

    public Role getProfile(String username) throws ExceptionForUser {
        Map<String, String> resultMap = new HashMap<>();
        try (Connection connection = db.getConnection()) {
            ResultSet resultSet = db.getNote
                    ("username", username, "usertable",
                            "id", "role", "email");
            while (resultSet.next()) {
                resultMap.put("username", username);
                resultMap.put("id", resultSet.getString(1));
                resultMap.put("role", resultSet.getString(2));
                resultMap.put("email", resultSet.getString(3));
            }
            String role = resultMap.get("role");
            Role unit = null;
            switch (role) {
                case "pupil":
                    System.out.println("Prrpp... Pupil: SOME");
                    break;
                case "teacher":
                    unit = getProfileTeacher(resultMap, connection);
                    break;
            }
            return unit;
        } catch (SQLException e) {
            logger.warn(e.getLocalizedMessage(), e);
            throw new ExceptionForUser();
        }
    }

    @Autowired
    public void setDb(DataBaseSQL db) {
        this.db = db;
    }
}

package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by INNO on 27.12.2016.
 */
public class Teacher implements Role {
    private int id;
    private String name;
    private String lastName;
    private int groupN;
    private int user_id;
    public static String INSERT_DB = "SELECT";

    private Teacher(int id, String name, String lastName, int groupN, int user_id) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.groupN = groupN;
        this.user_id = user_id;
    }



    public static Role createTeacher
            (int id, String name, String lastName, int groupN, int user_id){

        return new Teacher(id, name, lastName, groupN, user_id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGroupN() {
        return groupN;
    }

    public void setGroupN(int groupN) {
        this.groupN = groupN;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}

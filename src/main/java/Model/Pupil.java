package Model;

import java.sql.Connection;
import java.util.List;

/**
 * Created by INNO on 27.12.2016.
 */
public class Pupil implements Role {
    private int id;
    private String name;
    private String lastName;
    private int groupN;
    private String tests;
    private int user_id;


    private Pupil
            (int id, String name, String lastName, int groupN, String tests, int user_id) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.groupN = groupN;
        this.tests = tests;
        this.user_id = user_id;
    }



    public static Role createPupil
            (int id, String name, String lastName, int groupN, String tests, int user_id){

        return new Pupil(id, name, lastName, groupN, tests, user_id);
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

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}

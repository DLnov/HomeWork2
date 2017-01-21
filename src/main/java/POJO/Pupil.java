package POJO;

import java.util.List;

/**
 * Created by INNO on 27.12.2016.
 */
public class Pupil implements Role {
    private int id;
    private String username;
    private String name;
    private String lastName;
    private int groupN;
    private String tests;
    private int user_id;
    private String email;
    private final String role = "pupil";

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(username);
        stb.append(" ");
        stb.append(name);
        stb.append(" ");
        stb.append(lastName);
        stb.append(" ");
        stb.append(getGroupN());
        stb.append(" ");
        stb.append(email);
        return stb.toString();
    }

    public Pupil(){}

    public Pupil(String username, String name, String lastName, String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Pupil(String username, String name, String lastName, String email, int groupN) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.groupN = groupN;
        this.email = email;
    }

    public Pupil
            (int id, String name, String lastName, int groupN, String tests, int user_id) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.groupN = groupN;
        this.tests = tests;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    @Override
    public String getRoleType() {
        return role;
    }

    @Override
    public List<String> getFields() {
        return null;
    }
}

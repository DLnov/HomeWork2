package POJO;


import java.util.ArrayList;
import java.util.List;


public class Teacher implements Role {

    private int user_id;
    private String username;
    private String name;
    private String lastName;
    private String email;

    private List<Group> groups = new ArrayList<>();
    private List<TestWork> testWorks;
    private final String role = "teacher";

    public List<String> getTestWorksString() {
        List<String> list = new ArrayList<>();
        for (TestWork testWork : testWorks) {
            list.add(testWork.toString());
        }
        return list;
    }

    public List<String> getGroupsString() {
        List<String> list = new ArrayList<>();
        for (Group group : groups) {
            for (Pupil pupil : group.getPupils()) {
                list.add(pupil.toString());
            }
        }
        return list;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<TestWork> getTestWorks() {
        return testWorks;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public void setTestWorks(List<TestWork> testWorks) {
        this.testWorks = testWorks;
    }

    public Teacher(String username, String name, String lastName, String email, int user_id) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.user_id = user_id;
    }

    public Teacher(String username, String name, String lastName, String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Teacher() {
    }

    @Override
    public String getRoleType() {
        return role;
    }

    @Override
    public List<String> getFields() {
        List<String> list = new ArrayList<>();
        list.add(username);
        list.add(name);
        list.add(lastName);
        list.add(email);
        return list;
    }
}

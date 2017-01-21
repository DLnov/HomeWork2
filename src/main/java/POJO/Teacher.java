package POJO;


import java.util.ArrayList;
import java.util.List;

public class Teacher implements Role {
    private String username;
    private String name;
    private String lastName;
    private String email;
    private List<Group> groups = new ArrayList<>();
    private int user_id;
    private List<TestWork> testWorks;
    private final String role = "teacher";

    public List<String> getTestWorksString(){
        List<String> list = new ArrayList<>();
        for (TestWork testWork : testWorks){
            list.add(testWork.toString());
        }
        return list;
    }
    public List<String> getGroupsString(){
        List<String> list = new ArrayList<>();
        for (Group group : groups){
            for(Pupil pupil : group.getPupils()){
                list.add(pupil.toString());
            }
        }
        return list;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<TestWork> getTestWorks() {
        return testWorks;
    }

    public void setTestWorks(List<TestWork> testWorks) {
        this.testWorks = testWorks;
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

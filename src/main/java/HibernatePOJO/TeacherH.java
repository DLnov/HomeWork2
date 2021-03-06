package HibernatePOJO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usertable", schema = "public", catalog = "HomeWork2")
@SecondaryTable(name = "teacher", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class TeacherH {
    private int id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private List<GroupsH> groups;
    private List<TestsH> tests;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "teacher_id")
    public List<TestsH> getTests() {
        return tests;
    }

    public void setTests(List<TestsH> tests) {
        this.tests = tests;
    }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "teacher_id")
    public List<GroupsH> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsH> groups) {
        this.groups = groups;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 15, table = "teacher")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 25, table = "teacher")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 15)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherH teacherH = (TeacherH) o;

        if (id != teacherH.id) return false;
        if (name != null ? !name.equals(teacherH.name) : teacherH.name != null) return false;
        if (lastname != null ? !lastname.equals(teacherH.lastname) : teacherH.lastname != null) return false;
        if (username != null ? !username.equals(teacherH.username) : teacherH.username != null) return false;
        if (email != null ? !email.equals(teacherH.email) : teacherH.email != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}

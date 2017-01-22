package HibernatePOJO;

import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;


@Entity
@Table(name = "usertable", schema = "public", catalog = "HomeWork2")
@SecondaryTable(name = "pupil", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class PupilH {
    private int id;
    private String name;
    private String lastname;
    private String username;
    private String email;


    private GroupsH group;
    @ManyToOne
    @JoinColumn(name = "group_id", table = "pupil", referencedColumnName = "id")
    public GroupsH getGroup() {
        return group;
    }
    public void setGroup(GroupsH group) {
        this.group = group;
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
    @Column(name = "name", nullable = false, length = 15, table = "pupil")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 25, table = "pupil")
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

        PupilH pupilH = (PupilH) o;

        if (id != pupilH.id) return false;
        if (name != null ? !name.equals(pupilH.name) : pupilH.name != null) return false;
        if (lastname != null ? !lastname.equals(pupilH.lastname) : pupilH.lastname != null) return false;
        if (username != null ? !username.equals(pupilH.username) : pupilH.username != null) return false;
        if (email != null ? !email.equals(pupilH.email) : pupilH.email != null) return false;


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

package HibernatePOJO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "groups", schema = "public", catalog = "HomeWork2")

public class GroupsH {
    private int id;
    private String groupname;
    private List<PupilH> pupils = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    public List<PupilH> getPupils() {
        return pupils;
    }

    public void setPupils(List<PupilH> pupils) {
        this.pupils = pupils;
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
    @Column(name = "groupname", nullable = false, length = 25)
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsH groupsH = (GroupsH) o;

        if (id != groupsH.id) return false;
        if (groupname != null ? !groupname.equals(groupsH.groupname) : groupsH.groupname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        return result;
    }
}

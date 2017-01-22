package HibernatePOJO;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "tests", schema = "public", catalog = "HomeWork2")
public class TestsH {
    private int id;
    private String nametest;
    private String theme;
    private Date datecreate;
    private String datas;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nametest", nullable = false, length = 20)
    public String getNametest() {
        return nametest;
    }

    public void setNametest(String nametest) {
        this.nametest = nametest;
    }

    @Basic
    @Column(name = "theme", nullable = false, length = 20)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "datecreate", nullable = false)
    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    @Basic
    @Column(name = "datas", nullable = false, length = -1)
    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestsH testsH = (TestsH) o;

        if (id != testsH.id) return false;
        if (nametest != null ? !nametest.equals(testsH.nametest) : testsH.nametest != null) return false;
        if (theme != null ? !theme.equals(testsH.theme) : testsH.theme != null) return false;
        if (datecreate != null ? !datecreate.equals(testsH.datecreate) : testsH.datecreate != null) return false;
        if (datas != null ? !datas.equals(testsH.datas) : testsH.datas != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nametest != null ? nametest.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (datecreate != null ? datecreate.hashCode() : 0);
        result = 31 * result + (datas != null ? datas.hashCode() : 0);
        return result;
    }
}

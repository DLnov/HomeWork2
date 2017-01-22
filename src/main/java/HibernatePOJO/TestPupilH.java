package HibernatePOJO;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by INNO on 22.01.2017.
 */
@Entity
@Table(name = "test_pupil", schema = "public", catalog = "HomeWork2")
public class TestPupilH {
    private int id;
    private Date dateTesting;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_testing", nullable = false)
    public Date getDateTesting() {
        return dateTesting;
    }

    public void setDateTesting(Date dateTesting) {
        this.dateTesting = dateTesting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestPupilH that = (TestPupilH) o;

        if (id != that.id) return false;
        if (dateTesting != null ? !dateTesting.equals(that.dateTesting) : that.dateTesting != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateTesting != null ? dateTesting.hashCode() : 0);
        return result;
    }
}

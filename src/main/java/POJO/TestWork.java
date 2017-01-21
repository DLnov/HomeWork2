package POJO;

import java.util.Date;

public class TestWork {
    private int id;
    private String nameTest;
    private String theme;
    private Date dateCreate;
    private String datas;
    private int teacher_id;

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(nameTest);
        stb.append(" ");
        stb.append(theme);
        stb.append(" ");
        stb.append(dateCreate.toString());
        return stb.toString();
    }

    public TestWork(String nameTest, String theme, Date dateCreate, String datas) {
        this.nameTest = nameTest;
        this.theme = theme;
        this.dateCreate = dateCreate;
        this.datas = datas;
    }

    public TestWork(int id, Date dateCreate, String datas) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.datas = datas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }


}

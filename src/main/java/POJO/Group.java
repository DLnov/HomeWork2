package POJO;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


public class Group {

    private int group_id;
    private int teacher_id;
    private String groupName;
    private List<Pupil> pupils = new ArrayList<>();


    public Group() {
    }

    public Group(int group_id, int teacher_id, String groupName) {
        this.group_id = group_id;
        this.teacher_id = teacher_id;
        this.groupName = groupName;
    }

    public void add(Pupil pupil) {
        pupils.add(pupil);
    }

    public void remove(Pupil pupil) {
        pupils.remove(pupil);
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        this.pupils = pupils;
    }
}

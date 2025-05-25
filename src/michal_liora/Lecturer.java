package michal_liora;

import java.util.Arrays;

public class Lecturer {
    protected String name;
    protected String id;
    protected Enums.degreeLevel degreeLevel;
    protected String degreeTitle;
    protected double salary;
    protected Department department;
    protected Committee[] committees;
    protected int committeesCount;

    public Lecturer(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department) {
        setName(name);
        setId(id);
        setDegreeLevel(degreeLevel);
        setDegreeTitle(degreeTitle);
        setSalary(salary);
        setDepartment(department);
        setCommittees(new Committee[1]);
        setCommitteesCount(0);
    }

    public void setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
        }
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = Enums.degreeLevel.valueOf(degreeLevel.toUpperCase());
    }

    public void setDegreeTitle(String degreeTitle) {
        if (degreeTitle != null && !degreeTitle.isEmpty()) {
            this.degreeTitle = degreeTitle;
        }
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setCommittees(Committee[] committees) {
        this.committees = committees;
    }

    public void setCommitteesCount(int committeesCount) {
        this.committeesCount = committeesCount;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDegreeLevel() {
        return degreeLevel.toString();
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public int getCommitteesCount() {
        return committeesCount;
    }

    public String committeesNamesToString() {
        String committesStr = "[";
        int i = 0;
        for (; i < (committeesCount - 1); i++){
            committesStr += committees[i].getName() + ", " ;
        }
        if(committeesCount != 0) {
            committesStr += committees[i].getName();
        }
        committesStr += "]";
        return committesStr;
    }

    public void removeFromCommittee(Committee committee){
        boolean runOverFlag = false;
        int i = 0;
        for (; i < committeesCount - 1; i++) {
            if(committees[i].equals(committee)){
                runOverFlag = true;
            }
            if (runOverFlag){
                committees[i] = committees[i + 1];
            }
        }
        committees[i] = null;
        setCommitteesCount(committeesCount - 1);
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", id=" + id +
                ", degreeLevel=" + degreeLevel +
                ", degreeTitle=" + degreeTitle +
                ", salary=" + salary +
                ", department=" + ((department != null) ? department.getName() : "(None)") +
                ", committees=" + committeesNamesToString() +
                "}";
    }
}

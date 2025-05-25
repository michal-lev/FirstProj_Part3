package michal_liora;

public class Doctor extends LecturerWithArticles {
    public Doctor(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department) {
        super(name, id, degreeLevel, degreeTitle, salary, department);
    }

    @Override
    public String toString() {
        return "{" +
                "degreeTitle=" + degreeTitle +
                ", name=" + name +
                ", id=" + id +
                ", degreeLevel=" + degreeLevel +
                ", salary=" + salary +
                ", department=" + ((department != null) ? department.getName() : "(None)") +
                ", committees=" + committeesNamesToString() +
                ", articles=" + articlesToString() +
                "}";
    }
}

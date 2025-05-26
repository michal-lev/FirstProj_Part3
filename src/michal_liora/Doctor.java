package michal_liora;

public class Doctor extends HighRankLecturer {
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

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare == null || getClass() != toCompare.getClass() || !super.equals(toCompare))
            return false;
        Doctor thatDoctor = (Doctor) toCompare;
        return articles.equals(thatDoctor.articles);
    }
}

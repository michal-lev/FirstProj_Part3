package michal_liora;

public class Professor extends Doctor {
    private String grantingInstitution;
    public Professor(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department, String grantingInstitution) {
        super(name, id, degreeLevel, degreeTitle, salary, department);
        setGrantingInstitution(grantingInstitution);
    }

    public String getGrantingInstitution() {
        return grantingInstitution;
    }

    public void setGrantingInstitution(String grantingInstitution) {
        this.grantingInstitution = grantingInstitution;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", id=" + id +
                ", grantingInstitution=" + grantingInstitution +
                ", degreeLevel=" + degreeLevel +
                ", degreeTitle='" + degreeTitle +
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
        Professor thatProfessor = (Professor) toCompare;
        return articles.equals(thatProfessor.articles) &&
                grantingInstitution.equals(thatProfessor.grantingInstitution);
    }
}


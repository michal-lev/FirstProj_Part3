package michal_liora;

public class Professor extends LecturerWithArticles {
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
}

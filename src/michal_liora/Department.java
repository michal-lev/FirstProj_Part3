package michal_liora;

public class Department {
    private String name;
    private int studentCount;
    private Lecturer[] lecturers;
    private int lecturerCount;

    public Department(String name, int studentCount) {
        setName(name);
        setStudentCount(studentCount);
        setLecturers(new Lecturer[1]);
        setLecturerCount(0);
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void setStudentCount(int studentCount) {
        if (studentCount >= 0) {
            this.studentCount = studentCount;
        };
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public void setLecturerCount(int lecturerCount) {
        this.lecturerCount = lecturerCount;
    }

    public String getName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getLecturerCount() {
        return lecturerCount;
    }
}
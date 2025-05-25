package michal_liora;

import java.util.Arrays;
import java.util.Objects;

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
    public String lecturerNamesToString() {
        String lecturersStr = "[";
        int i = 0;
        for (; i < (lecturerCount - 1); i++) {
            lecturersStr += lecturers[i].getName() + ", ";
        }
        if (lecturerCount != 0) {
            lecturersStr += lecturers[i].getName();
        }
        lecturersStr += "]";
        return lecturersStr;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", studentCount=" + studentCount +
                ", lecturers=" + lecturerNamesToString() +
                "}";
    }

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare == null || getClass() != toCompare.getClass()) return false;
        Department thatDepartment = (Department) toCompare;
        return studentCount == thatDepartment.studentCount &&
                lecturerCount == thatDepartment.lecturerCount &&
                name.equals(thatDepartment.name) &&
                Objects.deepEquals(lecturers, thatDepartment.lecturers);
    }
}
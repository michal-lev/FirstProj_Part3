package michal_liora;

public class Enums {
    enum degreeLevel {
        BACHELOR,
        MASTER,
        DOCTORATE,
        PROFESSOR
    }
    enum errorMessage {
        LECTURER_NOT_EXIST("Lecturer does not exist"),
        COMMITTEE_NOT_EXIST("Committee does not exist"),
        DEPARTMENT_NOT_EXIST("Department does not exist"),
        LECTURER_EXISTS("Lecturer already exists"),
        COMMITTEE_EXISTS("Committee already exists"),
        DEPARTMENT_EXISTS("Department already exists"),
        INVALID_CHAIR_DEGREE("Lecturer isn't an acceptable chair"),
        CHAIR_CANT_BE_MEMBER("The chair can not be a member of the committee"),
        LECTURER_ALREADY_IN_COMMITTEE("Lecturer already in committee"),
        LECTURER_NOT_IN_COMMITTEE("Lecturer is not in the committee"),
        INVALID_STUDENT_COUNT("Student count has to be a positive number"),
        INVALID_SALARY("Salary has to be a positive number"),
        INVALID_DEGREE_LEVEL("Invalid degree level"),
        LECTURER_HAS_DEPARTMENT("Lecturer can only have one department");

        private final String message;

        errorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

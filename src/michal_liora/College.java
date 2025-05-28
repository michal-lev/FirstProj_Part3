package michal_liora;

import javax.print.Doc;

public class College {
    private String name;
    private Lecturer[] lecturers;
    private Committee[] committees;
    private Department[] departments;

    private int lecturerCount;
    private int committeeCount;
    private int departmentCount;

    final int EXPAND_ARR_MULTI = 2;

    public College(String name) {
        this.name = name;
        this.lecturers = new Lecturer[1];
        this.committees = new Committee[1];
        this.departments = new Department[1];

        this.lecturerCount = 0;
        this.committeeCount = 0;
        this.departmentCount = 0;
    }

    public void createCommitteeClone(String committeeName) throws CollegeException {
        Committee committeeToClone = getCommitteeByName(committeeName);
        if (committeeToClone == null){
            throw new NotExistException(Enums.errorMessage.COMMITTEE_NOT_EXIST.getMessage());
        }
        Committee newCommittee = new Committee(committeeToClone);
        addCommittee(newCommittee);
    }

    public String createLecturer(String name, String id, String degreeLevel, String degreeTitle, double salary, String departmentName) throws CollegeException{
        Department department = getDepartmentByName(departmentName);
        boolean departmentNameEmpty = departmentName.isEmpty();
        boolean validDepartmentName = (departmentNameEmpty || department != null);
        String validDegreeLevel = getValidDegreeLevel(degreeLevel); //could throw
        Lecturer newLecturer;
        if (!validDepartmentName){
            throw new NotExistException(Enums.errorMessage.DEPARTMENT_NOT_EXIST.getMessage());
        }
        checkValidSalary(salary);
        if(validDegreeLevel.equals(Enums.degreeLevel.PROFESSOR.toString())) {
            return validDegreeLevel;
            //create professor
        }else if(validDegreeLevel.equals(Enums.degreeLevel.DOCTORATE.toString())) {
            return validDegreeLevel;
            //create doctor
        }
        else{
            newLecturer = new Lecturer(name, id, degreeLevel, degreeTitle, salary, department);
        }

        addLecturer(newLecturer);
        if (!departmentNameEmpty) {
            addLecturerToDepartmentInCollege(newLecturer, department);
        }
        return "regular";
    }

    public void createDoctor(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department, int numArticles , String[] articles){
        Lecturer newLecturer = new Doctor(name, id, degreeLevel, degreeTitle, salary, department, numArticles,articles);
        // see how to use original lecturer, put in array...
    }

    public void createProfessor(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department, int numArticles , String[] articles, String grantingInstitution){
        Lecturer newLecturer = new Professor(name, id, degreeLevel, degreeTitle, salary, department, numArticles,articles,grantingInstitution);
        // see how to use original lecturer, put in array...
    }

    public void createDepartment(String name, int studentCount) throws CollegeException{
        if (!checkValidStudentCount(studentCount))
            throw new InvalidUserInputException(Enums.errorMessage.INVALID_STUDENT_COUNT.getMessage());
        Department newDepartment = new Department(name,studentCount);
        addDepartment(newDepartment);
    }

    public static String getValidDegreeLevel(String degreeLevel) throws CollegeException{
        for (Enums.degreeLevel degLvl : Enums.degreeLevel.values()){
            if (degLvl.toString().equalsIgnoreCase(degreeLevel)){
                return degLvl.toString();
            }
        }
        throw new InvalidUserInputException(Enums.errorMessage.INVALID_DEGREE_LEVEL.getMessage());
    }

    public static void checkValidSalary(double salary) throws InvalidUserInputException {
        if (salary < 0) {
            throw new InvalidUserInputException(Enums.errorMessage.INVALID_SALARY.getMessage());
        }
    }

    public static boolean checkValidStudentCount(int studentCount){
        if (studentCount >= 0) {
            return true;
        }
        return false;
    }

    public void createCommittee(String name, String chairName) throws CollegeException{
        Committee existingCommittee = getCommitteeByName(name);
        Lecturer chair = getLecturerByName(chairName);
        if (existingCommittee != null){
            throw new NoDuplicatesException(Enums.errorMessage.COMMITTEE_EXISTS.getMessage());
        }
        if (chair == null){
            throw new NotExistException(Enums.errorMessage.LECTURER_NOT_EXIST.getMessage());
        }
        if(!(chair instanceof Doctor)){
            throw new InvalidOperationValueException(Enums.errorMessage.INVALID_CHAIR_DEGREE.getMessage());
        }
        Committee newCommittee = new Committee(name, chair);
        addCommittee(newCommittee);
    }

    public Lecturer[] addLecturerToArray(Lecturer[] lecturerArr,int arrCount, Lecturer newLecturer){
        if (lecturerArr.length == arrCount) {
            Lecturer[] newArray = new Lecturer[lecturerArr.length * EXPAND_ARR_MULTI];
            for (int i = 0; i < arrCount; i++) {
                newArray[i] = lecturerArr[i];
            }
            lecturerArr = newArray;
        }
        lecturerArr[arrCount] = newLecturer;
        return lecturerArr;
    }

    public void addLecturer(Lecturer lecturer) {
        lecturers = addLecturerToArray(lecturers,lecturerCount,lecturer);
        lecturerCount++;
    }

    public Committee[] addCommitteeToArray(Committee[] committeeArr,int arrCount, Committee newCommittee){
        if (committeeArr.length == arrCount) {
            Committee[] newArray = new Committee[committeeArr.length * EXPAND_ARR_MULTI];
            for (int i = 0; i < arrCount; i++) {
                newArray[i] = committeeArr[i];
            }
            committeeArr = newArray;
        }
        committeeArr[arrCount] = newCommittee;
        return committeeArr;
    }

    public void addCommittee(Committee committee) {
        committees = addCommitteeToArray(committees,committeeCount,committee);
        committeeCount++;
    }

    public Committee getCommitteeByName(String committeeName){
        for(int i = 0; i < committeeCount; i++){
            if (committees[i].getName().equalsIgnoreCase(committeeName)){
                return committees[i];
            }
        }
        return null;
    }

    public void updateCommitteeHead(String committeeName, String chairName) throws CollegeException {
        Committee committee = getCommitteeByName(committeeName);
        Lecturer chair = getLecturerByName(chairName);
        if (committee == null){
            throw new NotExistException(Enums.errorMessage.COMMITTEE_NOT_EXIST.getMessage());
        }
        if (chair == null){
            throw new NotExistException(Enums.errorMessage.LECTURER_NOT_EXIST.getMessage());
        }
        if (!(chair instanceof Doctor)){
            throw new InvalidOperationValueException(Enums.errorMessage.INVALID_CHAIR_DEGREE.getMessage());
        }
        if (checkIfLecturerInCommittee(chair,committee)){
            throw new InvalidOperationValueException(Enums.errorMessage.CHAIR_CANT_BE_MEMBER.getMessage());
        }
        committee.setChair(chair);
    }


    public Lecturer getLecturerByName(String lecturerName){
        for(int i = 0; i < lecturerCount; i++){
            if (lecturers[i].getName().equalsIgnoreCase(lecturerName)){
                return lecturers[i];
            }
        }
        return null;
    }

    public Department getDepartmentByName(String departmentName){
        for(int i = 0; i < departmentCount; i++){
            if (departments[i].getName().equalsIgnoreCase(departmentName)){
                return departments[i];
            }
        }
        return null;
    }

    public void addDepartment(Department department) {
        if (departments.length == departmentCount) {
            Department[] newArray = new Department[departments.length * EXPAND_ARR_MULTI];
            for (int i = 0; i < departmentCount; i++) {
                newArray[i] = departments[i];
            }
            departments = newArray;
        }
        departments[departmentCount++] = department;
    }

    public void addLecturerToCommittee(String lecturerName, String committeeName) throws CollegeException {
        Committee committee = getCommitteeByName(committeeName);
        Lecturer lecturer = getLecturerByName(lecturerName);
        if (committee == null){
            throw new NotExistException(Enums.errorMessage.COMMITTEE_NOT_EXIST.getMessage());
        }
        if (lecturer == null){
            throw new NotExistException(Enums.errorMessage.LECTURER_NOT_EXIST.getMessage());
        }
        if (checkIfLecturerInCommittee(lecturer,committee)){
            throw new NoDuplicatesException(Enums.errorMessage.LECTURER_ALREADY_IN_COMMITTEE.name());
        }
        if (committee.getChair().getName().equals(lecturer.getName())){
            throw new InvalidOperationValueException(Enums.errorMessage.CHAIR_CANT_BE_MEMBER.getMessage());
        }
        int currentMemberCount = committee.getMemberCount();
        Lecturer[] updatedMembers = addLecturerToArray(committee.getMembers(),currentMemberCount, lecturer);
        committee.setMembers(updatedMembers);
        committee.setMemberCount(currentMemberCount + 1);
        updateLecturerNewCommittee(lecturer,committee);
    }

    public void updateLecturerNewCommittee(Lecturer lecturer, Committee committee){
        int currentCommitteesCount = lecturer.getCommitteesCount();
        Committee[] updatedCommittees = addCommitteeToArray(lecturer.getCommittees(),currentCommitteesCount, committee);
        lecturer.setCommittees(updatedCommittees);
        lecturer.setCommitteesCount(currentCommitteesCount + 1);
    }

    public void removeLecturerFromCommittee(String lecturerName, String committeeName) throws CollegeException{
        Committee committee = getCommitteeByName(committeeName);
        Lecturer lecturer = getLecturerByName(lecturerName);
        if (committee == null){
            throw new NotExistException(Enums.errorMessage.COMMITTEE_NOT_EXIST.getMessage());
        }
        if (lecturer == null){
            throw new NotExistException(Enums.errorMessage.LECTURER_NOT_EXIST.getMessage());
        }
        if (!checkIfLecturerInCommittee(lecturer,committee)){
            throw new InvalidOperationValueException(Enums.errorMessage.LECTURER_NOT_IN_COMMITTEE.getMessage());
        }
        committee.removeMember(lecturer);
        lecturer.removeFromCommittee(committee);
    }

    public boolean checkIfLecturerInCommittee(Lecturer lecturer, Committee committee){
        Lecturer[] members = committee.getMembers();
        for( int i = 0; i < committee.getMemberCount(); i++) {
            if (members[i].equals(lecturer)){
                return true;
            }
        }
        return false;
    }

    public double getSalaryAvg(Lecturer[] lecturersArr, int lecturersArrCount){
        double salarySum = 0, avg;
        for (int i = 0; i < lecturersArrCount; i++) {
            salarySum += lecturersArr[i].getSalary();
        }
        // make it .2f :)
        avg = salarySum / lecturersArrCount;
        avg = (double) ((int) (avg * 100)) / 100;
        return avg;
    }

    public double getLecturersSalaryAvg(){
        return getSalaryAvg(lecturers, lecturerCount);
    }

    public double getDepartmentMembersSalaryAvg(String departmentName) throws CollegeException{
        Department department = getDepartmentByName(departmentName);
        if(department == null){
            throw new NotExistException(Enums.errorMessage.DEPARTMENT_NOT_EXIST.getMessage());
        }
        Lecturer[] departmentLecturers = department.getLecturers();
        int departmentLecturersCount = department.getLecturerCount();
        return getSalaryAvg(departmentLecturers, departmentLecturersCount);
    }

    public void addLecturerToDepartmentInCollege(Lecturer lecturer,Department department){
        int currentLecturerCount = department.getLecturerCount();
        Lecturer[] updatedLecturers = addLecturerToArray(department.getLecturers(),currentLecturerCount, lecturer);
        department.setLecturers(updatedLecturers);
        department.setLecturerCount(currentLecturerCount + 1);
    }

    public boolean addLecturerToDepartment(String lecturerName, String departmentName) throws CollegeException {
        Department department = getDepartmentByName(departmentName);
        Lecturer lecturer = getLecturerByName(lecturerName);
        if(department == null){
            throw new NotExistException(Enums.errorMessage.DEPARTMENT_NOT_EXIST.getMessage());
        }
        if(lecturer == null){
            throw new NotExistException(Enums.errorMessage.LECTURER_NOT_EXIST.getMessage());
        }
        if(lecturer.getDepartment() != null){
            throw new NoDuplicatesException(Enums.errorMessage.LECTURER_HAS_DEPARTMENT.getMessage());
        }
        addLecturerToDepartmentInCollege(lecturer,department);
        lecturer.setDepartment(department);
        return true;
    }

    public String lecturerToString(Lecturer lecturer) {
        // to delete
        Department department = lecturer.getDepartment();
        return "{" +
                "name=" + lecturer.getName() +
                ", id=" + lecturer.getId() +
                ", degreeLevel=" + lecturer.getDegreeLevel().toLowerCase() +
                ", degreeTitle=" + lecturer.getDegreeTitle() +
                ", salary=" + lecturer.getSalary() +
                ", department=" + ((department != null) ? department.getName() : "(None)") +
                ", committees=" + lecturer.committeesNamesToString() +
                "}";
    }

    public String lecturerArrToString(Lecturer[] lecturerArr, int lecturerArrCount){
        String toReturn = "";
        for(int i = 0; i < lecturerArrCount; i++){
            toReturn += lecturerToString(lecturerArr[i]) + "\n";
        }
        return toReturn;
    }

    public String committeeToString(Committee committee) {
        // to delete
        return "{" +
                "name=" + committee.getName() +
                ", members=" + committee.membersNamesToString() +
                ", chair=" + committee.getChair().getName() +
                "}";
    }

    public String committeesArrToString(Committee[] committeesArr, int committeesArrCount){
        String toReturn = "";
        for(int i = 0; i < committeesArrCount; i++){
            toReturn += committeeToString(committeesArr[i]) + "\n";
        }
        return toReturn;
    }

    public String lecturersToString(){
        return lecturerArrToString(lecturers,lecturerCount);
    }

    public String committeesToString(){
        return committeesArrToString(committees,committeeCount);
    }

    public String compareDoctorsAndProfessors(String lecturerName1, String lecturerName2) throws CollegeException {
        Lecturer lecturer1 = getLecturerByName(lecturerName1);
        Lecturer lecturer2 = getLecturerByName(lecturerName2);
        int compareResult;
        if (lecturer1 == null || lecturer2 == null){
            throw new NotExistException(Enums.errorMessage.LECTURER_NOT_EXIST.getMessage());
        }
        if (!(lecturer1 instanceof Doctor && lecturer2 instanceof Doctor)){
            throw new InvalidOperationValueException(Enums.errorMessage.NOT_DOCTOR.getMessage());
        }
        compareResult = ((Doctor) lecturer1).compareTo((Doctor) lecturer2);
        return getCompareString(compareResult, Lecturer.class.getSimpleName());
    }
    public String compareCommittees(String committeeName1, String committeeName2, Enums.CommitteeSortOption sortBy) throws CollegeException{
        Committee committee1 = getCommitteeByName(committeeName1);
        Committee committee2 = getCommitteeByName(committeeName2);
        int compareResult;
        SortCommitteeByNumMembers numMembersComparator = new SortCommitteeByNumMembers();
        SortCommitteeByTotalNumArticles totalNumArticles = new SortCommitteeByTotalNumArticles();
        if (committee1 == null || committee2 == null){
            throw new NotExistException(Enums.errorMessage.COMMITTEE_NOT_EXIST.getMessage());
        }
        if (sortBy == Enums.CommitteeSortOption.BY_NUM_MEMBERS){
            compareResult = numMembersComparator.compare(committee1,committee2);
        }
        else{
            compareResult = totalNumArticles.compare(committee1,committee2);// no other option
        }
        return getCompareString(compareResult,Committee.class.getSimpleName());
    }

    public String getCompareString(int compareResult, String className){
        if (compareResult == 0){
            return "Both have the same amount.";
        }
        if (compareResult < 0){
            return "The first " + className + "has more";
        }
        return "The second " + className + " has more";
    }
}
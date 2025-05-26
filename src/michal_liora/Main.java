package michal_liora;

import java.util.Scanner;

public class Main {
    /*
    Michal Lev 322812421
    Liora Grinshpul 211666557
    */
    private static final Scanner scan = new Scanner(System.in);
    public static String getNameFromUser(String title){
        return getStringFromUser("Enter " + title.toLowerCase() + " name: ");
    }

    public static String getStringFromUser(String message) {
        System.out.print(message);
        return scan.nextLine();
    }

    public static double getDoubleFromUser(String message) {
        System.out.print(message);
        double toReturn = scan.nextDouble();
        scan.nextLine();
        return toReturn;
    }

    public static int getIntFromUser(String message) {
        System.out.print(message);
        int toReturn = scan.nextInt();
        scan.nextLine();
        return toReturn;
    }

    public static void printInvalidInputMessage(){
        printMessage("Bad input! Please try again.");
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void addLecturerToCollege(College college){
        String name;
        boolean nameExists;
        do{
            name = getNameFromUser(Lecturer.class.getSimpleName());
            nameExists = (college.getLecturerByName(name) != null);
            if(nameExists){
                printInvalidInputMessage();
            }
        }while(nameExists);
        String id = getStringFromUser("Enter ID number: ");
        String degreeLevel = getStringFromUser("Enter degree (Bachelor/Master/Doctorate/Professor): ");
        String degreeTitle = getStringFromUser("Enter degree Title: ");
        double salary = getDoubleFromUser("Enter Salary: ");
        String departmentName = getStringFromUser("Enter department name (or press Enter to skip): ");
        boolean success = college.createLecturer(name, id, degreeLevel, degreeTitle, salary, departmentName);
        if (!success) {
            printInvalidInputMessage();
        }
    }

    public static void addCommitteeToCollege(College college){
        String committeeName = getNameFromUser(Committee.class.getSimpleName());
        String chairName = getStringFromUser("Enter chair name: ");
        boolean success = college.createCommittee(committeeName, chairName);
        if(!success){
            printInvalidInputMessage();
        }
    }

    public static void addLecturerToCommittee(College college) {
        String committeeName = getStringFromUser("Enter committee name: ");
        String lecturerName = getStringFromUser("Enter lecturer name: ");
        boolean success = college.addLecturerToCommittee(lecturerName,committeeName);
        if (!success) {
            printInvalidInputMessage();
        }
    }

    public static void changeCommitteeHead(College college) {
        String committeeName = getNameFromUser(Committee.class.getSimpleName());
        String chairName = getStringFromUser("Enter chair name: ");
        boolean success = college.updateCommitteeHead(committeeName,chairName);
        if(!success){
            printInvalidInputMessage();
        }
    }

    public static void removeMemberFromCommittee(College college) {
        String committeeName = getStringFromUser("Enter committee name: ");
        String lecturerName = getStringFromUser("Enter lecturer name: ");
        boolean success = college.removeLecturerFromCommittee(lecturerName,committeeName);
        if (!success) {
            printInvalidInputMessage();
        }
    }

    public static void addDepartmentToCollege(College college) {
        String name;
        boolean nameExists;
        do{
            name = getNameFromUser(Department.class.getSimpleName());
            nameExists = (college.getDepartmentByName(name) != null);
            if(nameExists){
                printInvalidInputMessage();
            }
        }while(nameExists);
        int studentCount = getIntFromUser("Enter number of students in department: ");
        boolean success = college.createDepartment(name, studentCount);
        if (!success) {
            printInvalidInputMessage();
        }
    }

    public static void showLecturersSalaryAvg(College college) {
        double salaryAvg = college.getLecturersSalaryAvg();
        printMessage("The salary average is : " + salaryAvg);
    }

    public static void showDepartmentMembersSalaryAvg(College college) {
        String departmentName = getStringFromUser("Enter department Name: ");
        double salaryAvg = college.getDepartmentMembersSalaryAvg(departmentName);
        if(salaryAvg == -1) {
            printInvalidInputMessage();
        }
        else {
            printMessage("The salary average is : " + salaryAvg);
        }
    }

    public static void showDetailsOfAllLecturers(College college) {
        System.out.println(college.lecturersToString());
    }

    public static void showDetailsOfAllCommittees(College college) {
        System.out.println(college.committeesToString());
    }

    public static void addLecturerToDepartment(College college) {
        String departmentName = getStringFromUser("Enter department name: ");
        String lecturerName = getStringFromUser("Enter lecturer name: ");
        boolean success = college.addLecturerToDepartment(lecturerName,departmentName);
        if (!success) {
            printInvalidInputMessage();
        }
    }
    public static void createCommitteeClone(College college){
        String committeeName = getStringFromUser("Enter committee name: ");
        college.createCommitteeClone(committeeName);
        //to finish
    }

    public static void main(String[] args){
        String collegeName = getNameFromUser(College.class.getSimpleName());
        College college = new College(collegeName);
        boolean showMenu = true;
        do {
            System.out.println("0 - exit\n" +
                    "1 - Add lecturer to college\n" +
                    "2 - Add committee to college\n" +
                    "3 - Add committee member\n" +
                    "4 - Update committee Chair\n" +
                    "5 - Remove committee member\n" +
                    "6 - Add new department\n" +
                    "7 - Show average salary of all lecturers\n" +
                    "8 - Show average salary of lecturers in department\n" +
                    "9 - Show full details of all lecturers\n" +
                    "10 - Show full details of all committees\n" +
                    "11 - Add lecturer to department\n" +
                    "12 - Compare Professors/Doctors (by number of articles)\n" +
                    "13 - Compare committees\n" +
                    "14 - Create a committee clone");

            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 0:
                    showMenu = false;
                    break;
                case 1:
                    addLecturerToCollege(college);
                    break;
                case 2:
                    addCommitteeToCollege(college);
                    break;
                case 3:
                    addLecturerToCommittee(college);
                    break;
                case 4:
                    changeCommitteeHead(college);
                    break;
                case 5:
                    removeMemberFromCommittee(college);
                    break;
                case 6:
                    addDepartmentToCollege(college);
                    break;
                case 7:
                    showLecturersSalaryAvg(college);
                    break;
                case 8:
                    showDepartmentMembersSalaryAvg(college);
                    break;
                case 9:
                    showDetailsOfAllLecturers(college);
                    break;
                case 10:
                    showDetailsOfAllCommittees(college);
                    break;
                case 11:
                    addLecturerToDepartment(college);
                    break;
                case 12:
                    System.out.println(college.compareHighRankLecturers());
                    break;
                case 13:
                    break;
                case 14:
                    createCommitteeClone(college);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while (showMenu);
        scan.close();
    }
}

import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class provides a user interface for managing student records.
 * It supports operations like searching, adding, editing, and deleting students, as well as showing students by academic level.
 */

public class StudentManagementUI {

    private final MultiIndexStudentManager multiIndexStudentManager;
    private Scanner scanner;

    public StudentManagementUI(MultiIndexStudentManager multiIndexStudentManager) {
        this.scanner = new Scanner(System.in);
        this.multiIndexStudentManager = multiIndexStudentManager;
    }
    /**
     * Starts the main menu loop for user interaction.
     */
    public void start() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Search Student");
            System.out.println("2. Add New Student");
            System.out.println("3. Show Students in an Academic Level");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    searchStudentMenu();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    showStudentsInLevel();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    /**
     * Menu to search for students by ID, last name, or first name.
     */
    private void searchStudentMenu() {
        System.out.println("\nSearch Student by:");
        System.out.println("1. Student ID");
        System.out.println("2. Last Name");
        System.out.println("3. First Name");
        System.out.print("Enter your choice: ");

        int choice = getValidIntInput();

        Student student = null;
        while(student == null) {
            student = getStudentMenu(choice);// Retrieves a student or prompts again if not found.
        }

        int actionChoice = getValidIntInput();
        switch (actionChoice) {
            case 1:
                editStudent(student); // Edit the student.
                break;
            case 2:
                multiIndexStudentManager.delete(student); // Delete the student.
                System.out.println("The Following Student has been Deleted: "+student.toString());
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }
    /**
     * Retrieves a student based on the search choice (ID, last name, or first name).
     */
    private Student getStudentMenu(int choice){
        ArrayList<Student> foundStudents = new ArrayList<>();

        switch (choice) {
            case 1:
                System.out.print("Enter ID: ");
                int id = getValidIntInput();
                foundStudents.add(multiIndexStudentManager.getAvlTreeID().get(id));
                break;
            case 2:
                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();
                foundStudents = multiIndexStudentManager.getAvlTreeLastName().getbyName(lastName);
                break;
            case 3:
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();
                foundStudents = multiIndexStudentManager.getAvlTreeFirstName().getbyName(firstName);
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                searchStudentMenu();
        }
        Student student = null;
        if(!foundStudents.isEmpty()){

            if(foundStudents.size() == 1){
                student = foundStudents.get(0);
                System.out.println("\nStudent Found: " + student.toString());
            }
            else {
                int counter = 1;
                System.out.println("\nStudents Found:");
                for (Student s : foundStudents) {
                    System.out.println(counter + " " + s.toString());
                    counter++;
                }
                student = getValidIndex(foundStudents);
                System.out.println("Student Chosen: " + student.toString());
            }
            System.out.println("1. Edit Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");
        }else{
            System.out.println("There is no student with this information!");
        }
        return student;
    }
    /**
     * Adds a new student to the system with a unique ID.
     */
    private void addNewStudent() {
        System.out.println("\nAdding a New Student:");
        int id = generateUniqueId();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Academic Level (FR, SO, JR, SR): ");
        String level = getValidUniversityLevel();

        MyDate birthDate = getValidDateInput();;


        Student newStudent = new Student(id, lastName, firstName, level, birthDate);
        // TODO: Add logic to insert the new student into the data structure
        multiIndexStudentManager.insert(newStudent);
        System.out.println("Student added successfully: " + newStudent);
    }


    /**
     * Shows all students in a specified academic level.
     */
    private void showStudentsInLevel() {
        System.out.print("\nEnter Academic Level (FR, SO, JR, SR): ");
        String level = getValidUniversityLevel();

        // TODO: Implement logic to show students in a level✔
        ArrayList<Student> students = multiIndexStudentManager.getUniLevels().getList(level);
        System.out.print("Students in level " + level + ": \n"+ students);
    }



    /**
     * Edits the details of an existing student.
     */
    private void editStudent(Student student) {

        System.out.println("\nEdit Student:");
        System.out.println("1. Edit First Name");
        System.out.println("2. Edit Last Name");
        System.out.println("3. Edit University Level");
        System.out.println("4. Edit Birth Date");
        System.out.print("Enter your choice: ");

        int editChoice = getValidIntInput();

        switch (editChoice) {
            case 1:
                System.out.print("Enter New First Name: ");
                String newFirstName = scanner.nextLine();
                Student tmp = new Student(student.getId(), newFirstName, student.getLastName(), student.getUniLevel(), student.getBirthDate());
                multiIndexStudentManager.delete(student);
                multiIndexStudentManager.insert(tmp);
                System.out.println("First Name updated successfully.");
                break;
            case 2:
                System.out.print("Enter New Last Name: ");
                String newLastName = scanner.nextLine();
                Student tmp1 = new Student(student.getId(), student.getFirstName(),newLastName, student.getUniLevel(), student.getBirthDate());
                multiIndexStudentManager.delete(student);
                multiIndexStudentManager.insert(tmp1);

                multiIndexStudentManager.insert(tmp1);
                System.out.println("Last Name updated successfully.");
                break;
            case 3:
                System.out.print("Enter New University Level (FR, SO, JR, SR): ");
                String newUniLevel = getValidUniversityLevel();
                student.setUniLevel(newUniLevel);
                System.out.println("University Level updated successfully.");
                break;
            case 4:
                MyDate newBirthDate = getValidDateInput();
                student.setBirthDate(newBirthDate);
                System.out.println("Birth Date updated successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return;

    }
    /**
     * Validates and retrieves a valid integer input from the user.
     */
    private int getValidIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }
    /**
     * Validates and retrieves a valid date input from the user.
     */
    private MyDate getValidDateInput() {
        while (true) {
            System.out.print("Enter Date (DD/MM/YYYY): ");
            String dateInput = scanner.nextLine();
            try {
                return new MyDate(dateInput); // Uses the existing MyDate constructor
            } catch (Exception e) {
                System.out.println("Invalid date format or value. Please ensure the format is DD/MM/YYYY.");
            }
        }
    }

    /**
     * Validates and retrieves a valid university level from the user.
     */
    private String getValidUniversityLevel() {
        while (true) {
            System.out.print("Enter Academic Level (FR, SO, JR, SR): ");
            String level = scanner.nextLine().toUpperCase();
            if (level.equals("FR") || level.equals("SO") || level.equals("JR") || level.equals("SR")) {
                return level;
            } else {
                System.out.println("Invalid academic level. Please enter one of the following: FR, SO, JR, SR.");
            }
        }
    }

    /**
     * Retrieves a valid student from a list based on the user's choice.
     */
    private Student getValidIndex(ArrayList<Student> foundStudents){
        while(true) {
            try {
                System.out.print("\nSelect student based on index: ");//getValidIndex
                int nestedChoice = getValidIntInput();
                return foundStudents.get(nestedChoice - 1);
            }catch (IndexOutOfBoundsException e){
                System.out.println("There is only "+ foundStudents.size() + " students with this feature");
            }

        }
    }

    /**
     * Generates a unique ID for a new student.
     */
    private int generateUniqueId() {
        int newId;
        System.out.print("generating id");
        do {
            System.out.print(".");
            newId = 10000 + (int) (Math.random() * 100000); // Generate random ID in range 10000-99999
        } while (multiIndexStudentManager.getAvlTreeID().get(newId) != null);
        System.out.println();
        System.out.println("id generated successfully");
        return newId;
    }
}

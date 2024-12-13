import java.io.File;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        File file = new File("src/students-trial.csv");
        try (Scanner input = new Scanner(file)) {
            int id, age;
            String lastName, firstName, uniLevel, dateOfBirth;
            input.nextLine();
            while (input.hasNext()) {
                String[] row = input.nextLine().split(",");
                id = Integer.parseInt(row[0]);
                lastName = row[1];
                firstName = row[2];
                dateOfBirth = row[3];
                uniLevel = row[4].replace("\n", "");

                Student student = new Student(id, firstName, lastName, uniLevel, dateOfBirth);
                students.add(student);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        AVLTreeLastName avlTreeLastName = new AVLTreeLastName(students);
        AVLTreeFirstName avlTreeFirstName = new AVLTreeFirstName(students);
        AVLTreeID avlTreeID = new AVLTreeID(students);



        System.out.println(" /$$   /$$ /$$$$$$$$ /$$   /$$ /$$$$$$$  /$$      /$$\n" +
                "| $$  /$$/| $$_____/| $$  | $$| $$__  $$| $$$    /$$$\n" +
                "| $$ /$$/ | $$      | $$  | $$| $$  \\ $$| $$$$  /$$$$\n" +
                "| $$$$$/  | $$$$$   | $$  | $$| $$$$$$$/| $$ $$/$$ $$\n" +
                "| $$  $$  | $$__/   | $$  | $$| $$____/ | $$  $$$| $$\n" +
                "| $$\\  $$ | $$      | $$  | $$| $$      | $$\\  $ | $$\n" +
                "| $$ \\  $$| $$      |  $$$$$$/| $$      | $$ \\/  | $$\n" +
                "|__/  \\__/|__/       \\______/ |__/      |__/     |__/\n" +
                "                                                     \n" +
                "                                                     \n" +
                "                                                     ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hey there! Welcome to the KFUPM Data Structure Project");
        String userinput = "";
        while (!userinput.equals("Wubba Lubba Dub Dub"))  {

            System.out.println("What would you like to do?" +
                    "\n1- Search for a student" +
                    "\n2- Add a student" +
                    "\n3- Delete a student" +
                    "\n4- Get me out of here\nEnter:");
            userinput = scanner.nextLine();
            if (userinput.equals("1")) {

                ArrayList<Student> foundStudents = new ArrayList<>();
                System.out.println("Enter any credential about the student (ID, first name, or last name), please note this is case sensitive\nEnter:");
                String input1 = scanner.nextLine();
                try {
                    int id = Integer.parseInt(input1);
                    boolean foundid = avlTreeID.isInTree(id);
                    if (foundid) {
                        System.out.println("Student info:");
                        System.out.println((avlTreeID.get(id)).toString());
                    }
                    else{
                        System.out.println("No students matching this ID");
                    }
                } catch (NumberFormatException e) {
                    String name = input1;
                    boolean foundLastName = avlTreeLastName.isInTree(name);
                    boolean foundFirstName = avlTreeFirstName.isInTree(name);
                    if (foundFirstName) {
                        foundStudents.add(avlTreeFirstName.get(name));
                    }
                    if (foundLastName) {
                        foundStudents.add(avlTreeLastName.get(name));
                    }
                    if (foundFirstName | foundLastName) {
                        System.out.println("Student/s Info:");
                        for (Student student : foundStudents) {
                            System.out.println((student.toString()));
                        }
                    } else {
                        System.out.println("No students matching information");
                    }
                }
            }
            else if(userinput.equals("2")){
                System.out.println("In order to add a student to the system, we require the following");
                Boolean isValid = false;
                while(!isValid){
                    try{
                        System.out.println("Enter ID:");
                        String input = scanner.nextLine();
                        int id2 = Integer.parseInt(input);
                        if(avlTreeID.isInTree(id2)){
                            throw new IllegalArgumentException("That ID already exists!\n");
                        }
                        System.out.println("Enter first name:");
                        input = scanner.nextLine();
                        String firstName = input;
                        System.out.println("Enter last name:");
                        input = scanner.nextLine();
                        String lastName = input;
                        System.out.println("Enter academic level:");
                        input = scanner.nextLine();
                        String academiclevel = input;
                        System.out.println("Enter last the date of birth (DD/MM/YYYY):");
                        input = scanner.nextLine();
                        String birthDay = input;
                        Student std = new Student(id2, firstName, lastName, academiclevel, birthDay);
                        System.out.print("Processing data: " + std.toString());
                        avlTreeID.insertAVL(id2, std);
                        System.out.println("Student added to ID database");
                        avlTreeFirstName.insertAVL(firstName, std);
                        avlTreeLastName.insertAVL(lastName, std);
                        System.out.println("Student added to name database");
                        isValid = true;
                    }
                    catch(Exception e){
                        System.out.println("\n");
                        System.out.print(e.getMessage() + ", try again? (y/n)\nEnter: ");
                        String tryAgain = scanner.nextLine();
                        if (tryAgain.equals("n")) {
                            isValid = true;
                        }
                    }

                }
            }
            else if(userinput.equals("3")){
                System.out.println("In order to delete a student off the system, we require their ID:");
                Boolean isValid = false;
                while(!isValid){
                    try {
                        System.out.println("Enter ID:");
                        String input3 = scanner.nextLine();
                        int id3 = Integer.parseInt(input3);
                        if (avlTreeID.isInTree(id3)) {
                            Student std3 = avlTreeID.get(id3);
                            avlTreeID.deleteAVL(std3.getId());
                            System.out.println("Student deleted from ID database");
                            avlTreeFirstName.deleteAVL(std3.getFirstName());
                            avlTreeLastName.deleteAVL(std3.getLastName());
                            System.out.println("Student deleted from name database");
                            System.out.println("Student " + std3.toString() + "deleted");
                            isValid = true;
                        } else {
                            System.out.println("No student matching information.");
                        }
                    }
                    catch(Exception e){
                        System.out.print(e.getMessage());
                    }
                }

            }
            else if(userinput.equals("4")){
                System.out.println("Shutting Down.....");
                System.exit(0);
            }

        }
    }
}

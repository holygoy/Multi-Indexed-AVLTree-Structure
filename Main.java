public class Main {
    public static void main(String[] args) {

        MultiIndexStudentManager students = new MultiIndexStudentManager();
        students.addRecordsFromCSV("src/students-details.csv");

        System.out.println(students);

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
        StudentManagementUI ui = new StudentManagementUI(students);
        ui.start();
    }

}
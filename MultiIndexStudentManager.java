import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MultiIndexStudentManager {
    AVLTreeID avlTreeID;
    AVLTreeLastName avlTreeLastName;
    AVLTreeFirstName avlTreeFirstName;
    UniLevels uniLevels;
    ArrayList<Student> test;

    public AVLTreeID getAvlTreeID() {
        return avlTreeID;
    }

    public AVLTreeLastName getAvlTreeLastName() {
        return avlTreeLastName;
    }

    public AVLTreeFirstName getAvlTreeFirstName() {
        return avlTreeFirstName;
    }

    public UniLevels getUniLevels() {
        return uniLevels;
    }

    public MultiIndexStudentManager() {
        avlTreeID = new AVLTreeID();
        avlTreeLastName = new AVLTreeLastName();
        avlTreeFirstName = new AVLTreeFirstName();
        uniLevels = new UniLevels();
        test = new ArrayList<>();//Todo Remove
    }

    public void insert(Student student){
        avlTreeID.insertAVL(student.getId(),student);//Todo: remove extra step student.info?
        avlTreeLastName.insert(student.getLastName(),student);
        avlTreeFirstName.insert(student.getFirstName(),student);
        uniLevels.insert(student);
        test.add(student);
    }
    public void delete(Student student){
        avlTreeFirstName.delete(student.getFirstName(), student.getId());
        avlTreeLastName.delete(student.getLastName(), student.getId());
        avlTreeID.deleteAVL(student.getId());//add a delete method for avlTree based on the object
        uniLevels.delete(student);
    }
    public void addRecordsFromCSV(String fileName){

        File file = new File(fileName);
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
                insert(student);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return test.toString();
    }
}

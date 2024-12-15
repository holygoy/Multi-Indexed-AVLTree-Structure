import java.util.ArrayList;

public class AVLTreeLastName extends AVLTree<String> {

    public AVLTreeLastName(ArrayList<Student> students) {
        for (Student student : students) {
            this.insertAVL(student.getLastName(), student);
            //System.out.println(student.getLastName() + " Inserted into AVL Tree");
        }
    }

    public AVLTreeLastName() {

    }

    @Override
    public void breadthPrint() {
        super.breadthPrint();
    }
    public ArrayList<Student> getbyName(String name){
        ArrayList<Student> students = new ArrayList<>();//While there are instances that have the same last name, keep deleting and adding the deleted students to the array list
        while(super.isInTree(name)){
            Student currStudent = super.deleteAVL(name);
            students.add(currStudent);
        }
        for(Student student : students){ //After filling the array list with all students matching the last name, we reinsert all the students back into the AVL Tree
            this.insertAVL(student.getFirstName(), student);
        }
        return students;
    }
    public void delete(String name, int id){
        ArrayList<Student> students = new ArrayList<>();
        while(super.isInTree(name)){ //While there are instances that have the same last name, keep deleting and adding the deleted students to the array list
            Student currStudent = super.deleteAVL(name);
            students.add(currStudent);
        }
        for(Student student : students){
            if(student.getId() != id) {//After filling the array list with all students matching the last name except the one with the ID matching the ID in the argument
                this.insertAVL(student.getFirstName(), student);
            }
        }

    }
}

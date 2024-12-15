import java.util.ArrayList;

public class AVLTreeFirstName extends AVLTree<String> {

    public AVLTreeFirstName(ArrayList<Student> students) {
        for (Student student : students) {
            this.insertAVL(student.getFirstName(), student);
            //System.out.println(student.getLastName() + " Inserted into AVL Tree");
        }
    }

    public AVLTreeFirstName() {

    }

    @Override
    public void breadthPrint() {
        super.breadthPrint();
    }


    public ArrayList<Student> getbyName(String name){
        ArrayList<Student> students = new ArrayList<>();
        while(super.isInTree(name)){
            Student currStudent = super.deleteAVL(name); //While there are instances that have the same first name, keep deleting and adding the deleted students to the array list
            students.add(currStudent);
        }
        for(Student student : students){ //After filling the array list with all students matching the first name, we reinsert all the students back into the AVL Tree
            this.insertAVL(student.getFirstName(), student);
        }
        return students;
    }
    public void delete(String name, int id){
        ArrayList<Student> students = new ArrayList<>();
        while(super.isInTree(name)){
            Student currStudent = super.deleteAVL(name);//While there are instances that have the same first name, keep deleting and adding the deleted students to the array list
            students.add(currStudent);
        }
        for(Student student : students){
            if(student.getId() != id) { //After filling the array list with all students matching the first name except the one with the ID matching the ID in the argument
                this.insertAVL(student.getFirstName(), student);
            }
        }

    }
}
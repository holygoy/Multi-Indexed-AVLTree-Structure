import java.util.ArrayList;

public class AVLTreeLastName extends AVLTree<String> {

    public AVLTreeLastName(ArrayList<Student> students) {
        for (Student student : students) {
            this.insertAVL(student.getLastName(), student);
            //System.out.println(student.getLastName() + " Inserted into AVL Tree");
        }
    }

    @Override
    public void breadthPrint() {
        super.breadthPrint();
    }
    @Override
    public void deleteAVL(String studentName) {
        super.deleteAVL(studentName);
    }
    public boolean isInTree(String name){
        return super.isInTree(name);
    }
    @Override
    public Student get(String name){
        return super.get(name);
    }
    @Override
    public void insertAVL(String name, Student studentInfo){
        super.insertAVL(name, studentInfo);
    }

}

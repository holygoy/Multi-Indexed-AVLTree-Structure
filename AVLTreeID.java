import java.util.ArrayList;

public class AVLTreeID extends AVLTree<Integer> {

    public AVLTreeID(ArrayList<Student> students) {
        for (Student student : students) {
            this.insertAVL(student.getId(), student);
            //System.out.println(student.getLastName() + " Inserted into AVL Tree");
        }
    }

    public AVLTreeID() {

    }

    @Override
    public void breadthPrint() {
        super.breadthPrint();
    }
    @Override
    public Student deleteAVL(Integer id) {
        return super.deleteAVL(id);
    }
}

import java.util.ArrayList;

public class AVLTreeID extends AVLTree<Integer> {

    public AVLTreeID(ArrayList<Student> students) {
        for (Student student : students) {
            this.insertAVL(student.getId(), student);
            //System.out.println(student.getLastName() + " Inserted into AVL Tree");
        }
    }

    @Override
    public void breadthPrint() {
        super.breadthPrint();
    }
    @Override
    public void deleteAVL(Integer id) {
        super.deleteAVL(id);
    }
    public boolean isInTree(Integer id){
        return super.isInTree(id);
    }
    @Override
    public Student get(Integer id){
        return super.get(id);
    }
    @Override
    public void insertAVL(Integer id, Student studentInfo){
        super.insertAVL(id, studentInfo);
    }
}

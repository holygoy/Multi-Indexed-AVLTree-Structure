import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {

    protected int height;

    public AVLTree() {
        super();
        height = -1;
    }

    public AVLTree(BSTNode<T> root) {
        super(root);
        height = -1;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BSTNode<T> node) {
        if(node == null)
            return -1;
        else
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private AVLTree<T> getLeftAVL() {
        AVLTree<T> leftsubtree = new AVLTree<T>(root.left);
        return leftsubtree;
    }

    private AVLTree<T> getRightAVL() {
        AVLTree<T> rightsubtree = new AVLTree<T>(root.right);
        return rightsubtree;
    }

    protected int getBalanceFactor() {
        if(isEmpty())
            return 0;
        else
            return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }

    public void insertAVL(T el, Student studentInfo)  {
        super.insert(el, studentInfo);
        this.balance();
    }

    public void deleteAVL(T el) {
        //Q1
        super.deleteByCopying(el); // You can use deleteByCopying if preferred
        this.balance();
    }



    protected void balance()
    {
        if(!isEmpty())
        {
            getLeftAVL().balance();
            getRightAVL().balance();

            adjustHeight();

            int balanceFactor = getBalanceFactor();

            if(balanceFactor == -2) {
                //System.out.println("Balancing node with el: "+root.el);
                if(getLeftAVL().getBalanceFactor() < 0)
                    rotateRight();
                else
                    rotateLeftRight();
            }

            else if(balanceFactor == 2) {
                //System.out.println("Balancing node with el: "+root.el);
                if(getRightAVL().getBalanceFactor() > 0)
                    rotateLeft();
                else
                    rotateRightLeft();
            }
        }
    }

    protected void adjustHeight()
    {
        if(isEmpty())
            height = -1;
        else
            height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());
    }

    protected void rotateRight() {
        BSTNode<T> tempNode = root.right;
        root.right = root.left;
        root.left = root.right.left;
        root.right.left = root.right.right;
        root.right.right = tempNode;

        T val = (T) root.el;
        Student tempStd = root.studentInfo;
        root.el = root.right.el;
        root.studentInfo = root.right.studentInfo;
        root.right.el = val;
        root.right.studentInfo = tempStd;
        getRightAVL().adjustHeight();
        adjustHeight();
    }


    protected void rotateLeft() {
        BSTNode<T> tempNode = root.left;

        root.left = root.right;
        root.right = root.left.right;
        root.left.right = root.left.left;
        root.left.left = tempNode;

        T val = (T) root.el;
        Student tempStd = root.studentInfo;
        root.el = root.left.el;
        root.studentInfo = root.left.studentInfo;
        root.left.el = val;
        root.left.studentInfo = tempStd;

        getLeftAVL().adjustHeight();
        adjustHeight();
    }

    public Student get(T el){
        return super.get(el);
    }

    protected void rotateLeftRight()
    {

        //Q1
        //Double Rotation...
        getLeftAVL().rotateLeft();
        getLeftAVL().adjustHeight();
        this.rotateRight();
        this.adjustHeight();
    }

    protected void rotateRightLeft()
    {
        //Double Rotation...
        getRightAVL().rotateRight();
        getRightAVL().adjustHeight();
        this.rotateLeft();
        this.adjustHeight();
    }

    @Override
    public boolean isInTree(T el){
        return super.isInTree(el);
    }



    public void breadthPrint() {
        breadthPrint(root);
    }
    protected void breadthPrint(BSTNode<T> p) {
        if(p == null){
            return;
        }

        Queue<BSTNode<T>> q = new LinkedList<BSTNode<T>>();
        q.add(p);
        while(!q.isEmpty()){
            BSTNode<T> temp = q.remove();
            System.out.println(temp.el);
            if(temp.right != null){ // Changed from p.right to temp.right
                q.add(temp.right);
            }
            if(temp.left != null){ // Changed from p.left to temp.left
                q.add(temp.left);
            }
        }
    }
    }


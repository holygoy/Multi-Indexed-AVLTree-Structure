/************************  BSTNode.java  **************************
 *             node of a generic binary search tree
 */

public class BSTNode<T extends Comparable<? super T>> {
    protected T el;
    protected Student studentInfo; //New attribute studentInfo, to have a unique identifier since names can be duplicates
    protected BSTNode<T> left, right;
    public BSTNode() {
        left = right = null;
    }
    public BSTNode(T el, Student studentInfo) {
        this(el, studentInfo, null,null);
    }
    public BSTNode(T el, Student studentInfo, BSTNode<T> lt, BSTNode<T> rt) {
        this.el = el; this.studentInfo = studentInfo; left = lt; right = rt;
    }
}
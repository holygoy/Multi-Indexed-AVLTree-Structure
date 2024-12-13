/************************  BSTNode.java  **************************
 *             node of a generic binary search tree
 */

public class BSTNode<T extends Comparable<? super T>> {
    protected T el;
    protected Student studentInfo;
    protected BSTNode<T> left, right;
    protected int height;
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
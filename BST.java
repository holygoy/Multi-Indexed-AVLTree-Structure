import java.util.LinkedList;
import java.util.Queue;

/************************  BST.java  **************************
 *                 generic binary search tree
 */

public class BST<T extends Comparable<? super T>> {
    protected BSTNode<T> root;

    public BST() {
        root = null;
    }

    //ADDED METHOD FOR AVL TREES
    public BST(BSTNode<T> node) {
        root = node;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T el, Student studentInfo) {
        BSTNode<T> p = root, prev = null;
        while (p != null) {  // find a place for inserting new node;
            prev = p;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        }
        if (root == null)    // tree is empty;
            root = new BSTNode<T>(el, studentInfo);
        else if (el.compareTo(prev.el) < 0)
            prev.left  = new BSTNode<T>(el, studentInfo);
        else prev.right = new BSTNode<T>(el, studentInfo);
    }

    protected BSTNode<T> recInsert(BSTNode<T> p, T el, Student studentInfo) {
        if (p == null)
            p = new BSTNode<T>(el, studentInfo);
        else if (el.compareTo(p.el) < 0)
            p.left  = recInsert(p.left, el, studentInfo);
        else
            p.right = recInsert(p.right, el, studentInfo);
        return p;
    }


    public boolean isInTree(T el) {
        return search(el, root);
    }

    protected Boolean search(T el, BSTNode<T> p) {
        if( p == null){
            return false;
        }
        int comparisonResult = el.compareTo(p.el);
        if(comparisonResult == 0){
            return true;
        }
        return (comparisonResult > 0) ? search(el, p.right) : search(el, p.left);
    }
    public BSTNode<T> getbyID(int id) {
        return getbyID(id, root);
    }
    protected BSTNode<T> getbyID(int id, BSTNode<T> p) {
        if( p == null){
            return null;
        }
        int comparisonResult = Integer.valueOf(id).compareTo(Integer.valueOf(p.studentInfo.getId()));
        if(comparisonResult == 0){

            return p;

        }

        return (comparisonResult > 0) ? getbyID(id, p.right) : getbyID(id, p.left);
    }



    public Student get(T el) { //a recursive search that returns the studentInfo of the student matching the key
        return get(el, root);
    }
    protected Student get(T el, BSTNode<T> p) {
        if( p == null){
            return null;
        }
        int comparisonResult = el.compareTo(p.el);
        if(comparisonResult == 0){

            return p.studentInfo;

        }

        return (comparisonResult > 0) ? get(el, p.right) : get(el, p.left);
    }

    public void preorder() {
        preorder(root);
    }

    public void inorder() {
        inorder(root);
    }

    public void postorder() {
        postorder(root);
    }

    protected void visit(BSTNode<T> p) {
        System.out.print(p.el + " ");
    }

    protected void inorder(BSTNode<T> p) {
        if (p != null) {
            inorder(p.left);
            visit(p);
            System.out.print(" " + p.studentInfo);
            inorder(p.right);
        }
    }

    protected void preorder(BSTNode<T> p) {
        if (p != null) {
            visit(p);
            preorder(p.left);
            preorder(p.right);
        }
    }

    protected void postorder(BSTNode<T> p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            visit(p);
        }
    }

//    public void deleteByCopying(T el) {
//        BSTNode<T> node, p = root, prev = null;
//        while (p != null && !p.el.equals(el)) {  // find the node p
//            prev = p;                           // with element el;
//            if (el.compareTo(p.el) < 0)
//                p = p.left;
//            else p = p.right;
//        }
//        node = p;
//        if (p != null && p.el.equals(el)) {
//            if (node.right == null)             // node has no right child;
//                node = node.left;
//            else if (node.left == null)         // no left child for node;
//                node = node.right;
//            else {
//                BSTNode<T> tmp = node.left;    // node has both children;
//                BSTNode<T> previous = node;    // 1.
//                while (tmp.right != null) {    // 2. find the rightmost
//                    previous = tmp;            //    position in the
//                    tmp = tmp.right;           //    left subtree of node;
//                }
//                node.el = tmp.el;              // 3. overwrite the reference
//                //    to the element being deleted;
//                if (previous == node)          // if node's left child's
//                    previous.left  = tmp.left; // right subtree is null;
//                else previous.right = tmp.left; // 4.
//            }
//            if (p == root)
//                root = node;
//            else if (prev.left == p)
//                prev.left = node;
//            else prev.right = node;
//        }
//        else if (root != null)
//            System.out.println("el " + el + " is not in the tree");
//        else System.out.println("the tree is empty");
//    }

    public Student deleteByCopying(T el) { //Same as the old method, but when switching the key we switch the studentInfo simultaneously
        BSTNode<T> node, p = root, prev = null;
        while (p != null && !p.el.equals(el)) { // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else
                p = p.right;
        }

        if (p == null) {
            System.out.println("el " + el + " is not in the tree");
            return null;
        }


        Student deletedStudent = p.studentInfo; //After we find the matching student, we save it

        node = p;

        if (node.right == null) {               // node has no right child;
            node = node.left;
        } else if (node.left == null) {
            node = node.right;
        } else {

            BSTNode<T> tmp = node.left;
            BSTNode<T> previous = node;
            while (tmp.right != null) {
                previous = tmp;
                tmp = tmp.right;
            }

            node.el = tmp.el;
            node.studentInfo = tmp.studentInfo; //notice how we switch the studentInfo as well to avoid mismatch

            // Remove tmp
            if (previous == node) {
                previous.left = tmp.left;
            } else {
                previous.right = tmp.left; // 4.
            }
        }


        if (p == root) {
            root = node;
        } else if (prev.left == p) {
            prev.left = node;
        } else {
            prev.right = node;
        }


        return deletedStudent;
    }



    public void deleteByMerging(T el) {
        BSTNode<T> tmp, node, p = root, prev = null;
        while (p != null && !p.el.equals(el)) {  // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.el) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;
        if (p != null && p.el.equals(el)) {
            if (node.right == null) // node has no right child: its left
                node = node.left;  // child (if any) is attached to its parent;
            else if (node.left == null) // node has no left child: its right
                node = node.right; // child is attached to its parent;
            else {                  // be ready for merging subtrees;
                tmp = node.left;   // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                    tmp = tmp.right;      //    possible;
                tmp.right =        // 3. establish the link between
                        node.right;    //    the rightmost node of the left
                //    subtree and the right subtree;
                node = node.left;  // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node; // 5.
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }



    public void breadthFirst() {
        BSTNode<T> p = root;
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        if (p != null) {
            queue.add(p);
            while (!queue.isEmpty()) {
                p = queue.remove();
                visit(p);
                if (p.left != null)
                    queue.add(p.left);
                if (p.right != null)
                    queue.add(p.right);
            }
        }
    }

    public void MorrisInorder() {
        BSTNode<T> p = root, tmp;
        while (p != null)
            if (p.left == null) {
                visit(p);
                p = p.right;
            }
            else {
                tmp = p.left;
                while (tmp.right != null && // go to the rightmost node of
                        tmp.right != p)  // the left subtree or
                    tmp = tmp.right;   // to the temporary parent of p;
                if (tmp.right == null) {// if 'true' rightmost node was
                    tmp.right = p;     // reached, make it a temporary
                    p = p.left;        // parent of the current root,
                }
                else {                  // else a temporary parent has been
                    visit(p);          // found; visit node p and then cut
                    tmp.right = null;  // the right pointer of the current
                    p = p.right;       // parent, whereby it ceases to be
                }                       // a parent;
            }
    }

    public void MorrisPreorder() {
        BSTNode<T> p = root, tmp;
        while (p != null) {
            if (p.left == null) {
                visit(p);
                p = p.right;
            }
            else {
                tmp = p.left;
                while (tmp.right != null && // go to the rightmost node of
                        tmp.right != p)  // the left subtree or
                    tmp = tmp.right;   // to the temporary parent of p;
                if (tmp.right == null) {// if 'true' rightmost node was
                    visit(p);          // reached, visit the root and
                    tmp.right = p;     // make the rightmost node a temporary
                    p = p.left;        // parent of the current root,
                }
                else {                  // else a temporary parent has been
                    tmp.right = null;  // found; cut the right pointer of
                    p = p.right;       // the current parent, whereby it ceases
                }                       // to be a parent;
            }
        }
    }

    public void MorrisPostorder() {
        BSTNode<T> p = new BSTNode<T>(), tmp, q, r, s;
        p.left = root;
        while (p != null)
            if (p.left == null)
                p = p.right;
            else {
                tmp = p.left;
                while (tmp.right != null &&  // go to the rightmost node of
                        tmp.right != p)  // the left subtree or
                    tmp = tmp.right;   // to the temporary parent of p;
                if (tmp.right == null) {// if 'true' rightmost node was
                    tmp.right = p;     // reached, make it a temporary
                    p = p.left;        // parent of the current root,
                }
                else {           // else a temporary parent has been found;
                    // process nodes between p.left (included) and p (excluded)
                    // extended to the right in modified tree in reverse order;
                    // the first loop descends this chain of nodes and reverses
                    // right pointers; the second loop goes back, visits nodes,
                    // and reverses right pointers again to restore the pointers
                    // to their original setting;
                    for (q = p.left, r = q.right, s = r.right;
                         r != p; q = r, r = s, s = s.right)
                        r.right = q;
                    for (s = q.right; q != p.left;
                         q.right = r, r = q, q = s, s = s.right)
                        visit(q);
                    visit(p.left);     // visit node p.left and then cut
                    tmp.right = null;  // the right pointer of the current
                    p = p.right;       // parent, whereby it ceases to be
                }                       // a parent;
            }
    }



    public T findPred(BSTNode<T> t) {
        return null;
    }


    public int searchRecursiveLength(BSTNode<T> p) {
        return searchRecursiveLength(root, p, 0);
    }
    protected int searchRecursiveLength(BSTNode<T> p, BSTNode<T> key, int length) {
        if(p==null){
            return -1;
        }
        int comparisonResult = (key.el.compareTo(p.el));
        if(comparisonResult==0){
            return length;
        }
        return((comparisonResult>1)? searchRecursiveLength(p.right, key, length+1): searchRecursiveLength(p.left, key,length+1));
    }
}
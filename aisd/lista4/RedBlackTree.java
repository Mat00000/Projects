import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RedBlackTree extends Functionality {

    static int moreNumberOfInsert = 0;
    static int maxSize = 0;
    static int reduceSize = 0;

    private Node root;
    private final int BLACK = 1;
    private final int RED = 0;
    private static final int NEGATIVE_RED = -1;
    private static final int DOUBLE_BLACK = 2;

    public RedBlackTree() {
        root = null;
    }

    public class Node {
        String data;
        Node left;
        Node right;
        Node parent;
        int color;

        private Node() {}

        private void addNode(Node newNode) {
            int comp = newNode.data.compareTo(data);
            if (comp <= 0) {
                if (left == null)
                {
                    left = newNode;
                    left.parent = this;
                }
                else {
                    left.addNode(newNode);
                }
            }
            else if (comp > 0) {
                if (right == null) {
                    right = newNode;
                    right.parent = this;
                }
                else {
                    right.addNode(newNode);
                }
            }
        }

        private void setLeftChild(Node child) {
            left = child;
            if (child != null) {
                child.parent = this;
            }
        }

        private void setRightChild(Node child) {
            right = child;
            if (child != null) {
                child.parent = this;
            }
        }

    }



    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    private void fixAfterAdd(Node newNode) {
        if (newNode.parent == null) {
            newNode.color = BLACK;
        }
        else {
            newNode.color = RED;
            if (newNode.parent.color == RED) {
                fixDoubleRed(newNode);
            }
        }
    }

    private void fixDoubleRed(Node child) {
        Node parent = child.parent;
        Node grandParent = parent.parent;
        if (grandParent == null) {
            parent.color = BLACK; return;
        }
        Node n1, n2, n3, t1, t2, t3, t4;
        if (parent == grandParent.left) {
            n3 = grandParent; t4 = grandParent.right;
            if (child == parent.left) {
                n1 = child; n2 = parent;
                t1 = child.left; t2 = child.right; t3 = parent.right;
            }
            else {
                n1 = parent; n2 = child;
                t1 = parent.left; t2 = child.left; t3 = child.right;
            }
        }
        else {
            n1 = grandParent; t1 = grandParent.left;
            if (child == parent.left) {
                n2 = child; n3 = parent;
                t2 = child.left; t3 = child.right; t4 = parent.right;
            }
            else {
                n2 = parent; n3 = child;
                t2 = parent.left; t3 = child.left; t4 = child.right;
            }
        }
        replaceWith(grandParent, n2);
        n1.setLeftChild(t1);
        n1.setRightChild(t2);
        n2.setLeftChild(n1);
        n2.setRightChild(n3);
        n3.setLeftChild(t3);
        n3.setRightChild(t4);
        n2.color = grandParent.color - 1;
        n1.color = BLACK;
        n3.color = BLACK;

        if (n2 == root) {
            root.color = BLACK;
        }
        else if (n2.color == RED && n2.parent.color == RED) {
            fixDoubleRed(n2);
        }
    }

    private void replaceWith(Node toBeReplaced, Node replacement) {
        if (toBeReplaced.parent == null) {
            replacement.parent = null;
            root = replacement;
        }
        else if (toBeReplaced == toBeReplaced.parent.left) {
            toBeReplaced.parent.setLeftChild(replacement);
        }
        else {
            toBeReplaced.parent.setRightChild(replacement);
        }
    }

    private void fixBeforeRemove(Node toBeRemoved) {
        if (toBeRemoved.color == RED) {
            return; }

        // it isn't a leaf
        if (toBeRemoved.left != null || toBeRemoved.right != null) {
            // color the child is black
            if (toBeRemoved.left == null) {
                toBeRemoved.right.color = BLACK;
            }
            else { toBeRemoved.left.color = BLACK; }
        }
        else { bubbleUp(toBeRemoved.parent); }
    }

    private void bubbleUp(Node parent) {
        if (parent == null) {
            return;
        }
        parent.color++;
        parent.left.color--;
        parent.right.color--;

        if (bubbleUpFix(parent.left)) {
            return;
        }
        if (bubbleUpFix(parent.right)) {
            return;
        }

        if (parent.color == DOUBLE_BLACK) {
            if (parent.parent == null) {
                parent.color = BLACK;
            }
            else {
                bubbleUp(parent.parent);
            }
        }
    }

    private boolean bubbleUpFix(Node child) {
        if (child.color == NEGATIVE_RED) {
            fixNegativeRed(child);
            return true;
        }
        else if (child.color == RED) {
            if (child.left != null && child.left.color == RED) {
                fixDoubleRed(child.left);
                return true;
            }
            if (child.right != null && child.right.color == RED) {
                fixDoubleRed(child.right);
                return true;
            }
        }
        return false;
    }

    private void fixNegativeRed(Node negRed) {
        Node parent = negRed.parent;
        Node child;
        if (parent.left == negRed) {
            Node n1 = negRed.left;
            Node n2 = negRed;
            Node n3 = negRed.right;
            Node n4 = parent;
            Node t1 = n3.left;
            Node t2 = n3.right;
            Node t3 = n4.right;
            n1.color = RED;
            n2.color = BLACK;
            n4.color = BLACK;

            replaceWith(n4, n3);
            n3.setLeftChild(n2);
            n3.setRightChild(n4);
            n2.setLeftChild(n1);
            n2.setRightChild(t1);
            n4.setLeftChild(t2);
            n4.setRightChild(t3);

            child = n1;
        }
        else // Mirror image
        {
            Node n4 = negRed.right;
            Node n3 = negRed;
            Node n2 = negRed.left;
            Node n1 = parent;
            Node t3 = n2.right;
            Node t2 = n2.left;
            Node t1 = n1.left;
            n4.color = RED;
            n3.color = BLACK;
            n1.color = BLACK;

            replaceWith(n1, n2);
            n2.setRightChild(n3);
            n2.setLeftChild(n1);
            n3.setRightChild(n4);
            n3.setLeftChild(t3);
            n1.setRightChild(t2);
            n1.setLeftChild(t1);

            child = n4;
        }

        if (child.left != null && child.left.color == RED) {
            fixDoubleRed(child.left);
        }
        else if (child.right != null && child.right.color == RED) {
            fixDoubleRed(child.right);
        }
    }

    private String extremeTree(Node node, boolean extreme) {
        if(root == null) {
            String empty = "";
            return empty;
        }
        else {
            if(extreme) {
                if(node.left == null) {
                    return node.data;
                }
                else {
                    return extremeTree(node.left, extreme);
                }
            }
            else {
                if(node.right == null) {
                    return node.data;
                }
                else {
                    return extremeTree(node.right, extreme);
                }
            }
        }
    }

    private Node searchTree(Node node, String s) {
        if(node == null) {
            return null;
        }
        // value 0 if the argument is a string lexicographically equal to this string
        if(node.data.compareTo(s) == 0) {
            return node;
        }
        // value less than 0 if the argument is a string lexicographically greater than this string
        else if(node.data.compareTo(s) < 0) {
            return searchTree(node.right, s);
        }
        // value greater than 0 if the argument is a string lexicographically less than this string
        else {
            return searchTree(node.left, s);
        }
    }

    private Node minNode(Node node) {
        Node temp = node;
        while(temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    private Node successorNode(String key) {
        Node node = this.searchTree(root, key);
        if(node != null) {
            // when have right son
            if(node.right != null) {
                node = node.right;
                node = minNode(node);
                return node;
            }
            //when haven't right son
            Node parent = node.parent;
            while(parent != null && node == parent.right) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
        else {
            return node;
        }
    }

    private void loadFile(File file) {
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringTokenizer token;
        while(scan.hasNextLine()) {
            token = new StringTokenizer(scan.nextLine(), " ");
            while (token.hasMoreElements()) {
                insert(token.nextToken());
                moreNumberOfInsert++;
            }
        }
    }


    @Override
    public void find(String s) {
        if(searchTree(root, s) == null) {
            System.out.print(0);
        }
        else {
            System.out.print(1);
        }
    }

    @Override
    public void load(String f) {
        File file = new File(f);
        loadFile(file);
    }

    @Override
    public void successor(String k) {
        Node node = successorNode(k);
        if(node!=null) {
            System.out.print(node.data);
        }
        else {
            System.out.print("");
        }
    }

    @Override
    public void min() {
        String min = extremeTree(root, true);
        System.out.print(min);
    }

    @Override
    public void max() {
        String max = extremeTree(root, false);
        System.out.print(max);
    }

    @Override
    public void insert(String s) {
        maxSize++;
        Node newNode = new Node();
        newNode.data = s;
        newNode.left = null;
        newNode.right = null;
        if (root == null) {
            root = newNode;
        }
        else {
            root.addNode(newNode);
        }
        fixAfterAdd(newNode);
    }

    @Override
    public void inorder() {
        inOrder(root);
    }

    @Override
    public void delete(String s) {
        Node toBeRemoved = root;
        boolean found = false;
        while (!found && toBeRemoved != null)
        {
            int d = toBeRemoved.data.compareTo(s);
            if (d == 0) {
                found = true;
            }
            else
            {
                if (d > 0) {
                    toBeRemoved = toBeRemoved.left;
                }
                else {
                    toBeRemoved = toBeRemoved.right;
                }
            }
        }

        if (!found) {
            return;
        }

        // If one of the children is empty, use the other
        reduceSize++;
        if (toBeRemoved.left == null || toBeRemoved.right == null)
        {
            Node newChild;
            if (toBeRemoved.left == null) {
                newChild = toBeRemoved.right;
            }
            else {
                newChild = toBeRemoved.left;
            }

            fixBeforeRemove(toBeRemoved);
            replaceWith(toBeRemoved, newChild);
            return;
        }

        // Neither subtree is empty

        // Find smallest element of the right subtree

        Node smallest = toBeRemoved.right;
        while (smallest.left != null)
        {
            smallest = smallest.left;
        }

        // smallest contains smallest child in right subtree

        // Move contents, unlink child

        toBeRemoved.data = smallest.data;
        fixBeforeRemove(smallest);
        replaceWith(smallest, smallest.right);
    }

}

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BinarySearchTree extends Functionality{

    static int moreNumberOfInsert = 0;
    static int maxSize = 0;
    static int reduceSize = 0;

    class Node {
        String data;
        Node left, right, parent;

        public Node(String data, Node parent) {
            this.data = data;
            left = null;
            right = null;
            this.parent = parent;
        }
    }

    private Node root;

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

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.data);
        inOrder(node.right);
    }

    private String extremeTree(Node node, boolean extreme) {
        if(root == null) {
            String empty = "";
            return empty;
        }
        else {
            if(extreme == true) {
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

    private Node minNode(Node node) {
        Node temp = node;
        while(temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public Node successorNode(String key) {
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


    private Node deleteNode(Node node, String s) {
        if(searchTree(node, s) == null) {
            System.out.println(" ");
            return null;
        }

        int cmp = node.data.compareTo(s);

        if(node == null) {
            return null;
        }
        if(cmp > 0) {
            node.left = deleteNode(node.left, s);
        }
        else if(cmp < 0) {
            node.right = deleteNode(node.right, s);
        }
        else {
            // Case 1: No child
            if(node.left == null && node.right == null) {
                node = null;
                reduceSize++;
                return node;
            }
            // Case 2: One child
            else if(node.right == null) {
                Node temp = node;
                node = node.left;
                reduceSize++;
                return node;
            }
            else if(node.left == null) {
                Node temp = node;
                node = node.right;
                reduceSize++;
                return node;
            }
            // Case 3: Two children
            else {
                Node temp = minNode(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }
        return node;
    }

    @Override
    public void insert(String s) {
        maxSize++;
        if(root == null) {
            root = new Node(s, null);
            return;
        }

        Node parent = root;

        while(true) {
            int cmp = parent.data.compareTo(s);
            if(cmp >= 0) {
                if(parent.left == null) {
                    parent.left = new Node(s, parent);
                    return;
                }
                else {
                    parent = parent.left;
                }
            }
            else if(cmp < 0) {
                if(parent.right == null) {
                    parent.right = new Node(s, parent);
                    return;
                }
                else {
                    parent = parent.right;
                }
            }
        }
    }

    @Override
    public void delete(String s) {
        deleteNode(root, s);
    }

    public void load(String f) {
        File file = new File(f);
        loadFile(file);
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
    public void inorder() {
        inOrder(root);
    }
}

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SplayTree extends Functionality
 {	
 
	static int moreNumberOfInsert = 0;
    static int maxSize = 0;
    static int reduceSize = 0;
	  /** Class Node **/
	 class SplayNode
	 {    
		 SplayNode left, right, parent;
		 String element;
	 
		 /** Constructor **/
		 public SplayNode()
		 {
			 this("0", null, null, null);
		 }          
		 /** Constructor **/
		 public SplayNode(String ele)
		 {
			 this(ele, null, null, null);
		 } 
		 /** Constructor **/
		 public SplayNode(String ele, SplayNode left, SplayNode right, SplayNode parent)
		 {
			 this.left = left;
			 this.right = right;
			 this.parent = parent;
			 this.element = ele;         
		 }    
	 }
	 
     private SplayNode root;
     private int count = 0;
 
     /** Constructor **/
     public SplayTree()
     {
         root = null;
     }
 
     /** Function to check if tree is empty **/
     public boolean isEmpty()
     {
         return root == null;
     }
 
     /** clear tree **/
     public void clear()
     {
         root = null;
         count = 0;
     }
 
     /** function to insert element */
	 @Override
     public void insert(String s)
     {
         SplayNode z = root;
         SplayNode p = null;
         while (z != null)
         {
             p = z;
             if (p.element.compareTo(s) < 0)
                 z = z.right;
             else
                 z = z.left;
         }
         z = new SplayNode();
         z.element = s;
         z.parent = p;
         if (p == null)
             root = z;
         else if (p.element.compareTo(s) < 0)
             p.right = z;
         else
             p.left = z;
         Splay(z);
         count++;
     }
	 
     /** rotate **/
     public void makeLeftChildParent(SplayNode c, SplayNode p)
     {
         if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
             throw new RuntimeException("WRONG");
 
         if (p.parent != null)
         {
             if (p == p.parent.left)
                 p.parent.left = c;
             else 
                 p.parent.right = c;
         }
         if (c.right != null)
             c.right.parent = p;
 
         c.parent = p.parent;
         p.parent = c;
         p.left = c.right;
         c.right = p;
     }
 
     /** rotate **/
     public void makeRightChildParent(SplayNode c, SplayNode p)
     {
         if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
             throw new RuntimeException("WRONG");
         if (p.parent != null)
         {
             if (p == p.parent.left)
                 p.parent.left = c;
             else
                 p.parent.right = c;
         }
         if (c.left != null)
             c.left.parent = p;
         c.parent = p.parent;
         p.parent = c;
         p.right = c.left;
         c.left = p;
     }
 
     /** function splay **/
     private void Splay(SplayNode x)
     {
         while (x.parent != null)
         {
             SplayNode Parent = x.parent;
             SplayNode GrandParent = Parent.parent;
             if (GrandParent == null)
             {
                 if (x == Parent.left)
                     makeLeftChildParent(x, Parent);
                 else
                     makeRightChildParent(x, Parent);                 
             } 
             else
             {
                 if (x == Parent.left)
                 {
                     if (Parent == GrandParent.left)
                     {
                         makeLeftChildParent(Parent, GrandParent);
                         makeLeftChildParent(x, Parent);
                     }
                     else 
                     {
                         makeLeftChildParent(x, x.parent);
                         makeRightChildParent(x, x.parent);
                     }
                 }
                 else 
                 {
                     if (Parent == GrandParent.left)
                     {
                         makeRightChildParent(x, x.parent);
                         makeLeftChildParent(x, x.parent);
                     } 
                     else 
                     {
                         makeRightChildParent(Parent, GrandParent);
                         makeRightChildParent(x, Parent);
                     }
                 }
             }
         }
         root = x;
     }
 
     /** function to remove element **/
	 @Override
     public void delete(String s)
     {
         SplayNode node = findNode(s);
         remove(node);
     }
 
     /** function to remove node **/
     private void remove(SplayNode node)
     {
         if (node == null)
             return;
 
         Splay(node);
         if( (node.left != null) && (node.right !=null))
         { 
             SplayNode min = node.left;
             while(min.right!=null)
                 min = min.right;
 
             min.right = node.right;
             node.right.parent = min;
             node.left.parent = null;
             root = node.left;
         }
         else if (node.right != null)
         {
             node.right.parent = null;
             root = node.right;
         } 
         else if( node.left !=null)
         {
             node.left.parent = null;
             root = node.left;
         }
         else
         {
             root = null;
         }
         node.parent = null;
         node.left = null;
         node.right = null;
         node = null;
         count--;
     }
 
     /** Functions to count number of nodes **/
     public int countNodes()
     {
         return count;
     }
 
     /** Functions to search for an element **/
	 @Override
     public void find(String s)
     {
        if(findNode(s) != null) {
			System.out.print(1);
		}else {
			System.out.print(0);
		}
     }
 
     private SplayNode findNode(String ele)
     {
    	 SplayNode PrevNode = null;
         SplayNode z = root;
         while (z != null)
         {
        	 PrevNode = z;
             if (z.element.compareTo(ele) < 0)
                 z = z.right;
             else if (z.element.compareTo(ele) > 0)
                 z = z.left;
             else if(z.element.compareTo(ele) == 0) {
                 Splay(z);
                 return z;
             }
 
         }
         if(PrevNode != null)
         {
             Splay(PrevNode);
             return null;
         }
         return null;
     }
 
	 /** Function for inorder traversal **/ 
	 @Override
	 public void inorder(){
		 inorder(root);
	 }
	 
	 private void inorder(SplayNode r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.element +" ");
             inorder(r.right);
         }
     }
	 
	 @Override
	 public void load(String f) {
		 File file = new File(f);
         loadFile(file);
	 }
	 
	 @Override
	 public void min() {
		 // nothing
	 }
	 
	 @Override
	 public void max() {
		 //nothing
	 }
	 
	 @Override
	 public void successor(String k) {
		 //nothing
     }
 
     /** Function for preorder traversal **/
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(SplayNode r)
     {
         if (r != null)
         {
             System.out.print(r.element +" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
 
     /** Function for postorder traversal **/
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(SplayNode r)
     {
         if (r != null)
         {
             postorder(r.left);             
             postorder(r.right);
             System.out.print(r.element +" ");
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
 }

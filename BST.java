import java.util.ArrayList;

/**
 * Binary search tree implementation
 * @author bhills
 *
 */
public class BST {
	Node root;
	public static class Node {
		int data, height;
		Node left, right;
		Node(int data){
			this.data = data;
		}
		public String toString(){
			return data+"";
		}
	}
	public BST(int[] initialValues){
		root = new Node(initialValues[0]);
		for(int i=0; i<initialValues.length; i++){
			insert(root, new Node(initialValues[i]));
		}
	}
	
	/**
	 * Inserts from a particular starting origin node
	 * 
	 * Inserting a node at a subtree that does not have the particular value, but has no way of knowing that, may result in duplicate entries in the overall BST.
	 * @param origin
	 * @param n
	 */
	private void insert(Node origin, Node n){
		if(origin.data > n.data){ // new value belongs in left subtree
			if(origin.left==null){
				origin.left = n;
			} else {
				insert(origin.left, n);
			}
		} else if(origin.data < n.data) { // new value belongs in right subtree
			if(origin.right==null){
				origin.right = n;
			} else {
				insert(origin.right, n);
			}
		} else {
			// do nothing. value already exists in the BST.
		}
		
	}
	
	public void printInOrder(){
		printInOrder(root);
	}
	
	public void printInOrder(Node n){
		if(n.left != null){
			printInOrder(n.left);
		}
		System.out.println(n);
		if(n.right != null){
			printInOrder(n.right);
		}
	}
	
	public Node findNode(Node subroot, int data){
		if(subroot.data<data){
			return findNode(subroot.right, data);
		} else if (subroot.data>data){
			return findNode(subroot.left, data);
		} else if (subroot.data == data){
			return subroot;
		} else {
			return null;
		}
	}
	
	public Node findNode(int data){
		if(root.data<data){
			return findNode(root.right, data);
		} else if (root.data>data){
			return findNode(root.left, data);
		} else if (root.data==data){
			return root;
		} else {
			return null;
		}
	}
	
	public void displayBST(){
		// This is the base case to get the recursion started. ONLY 1 ROOT
		ArrayList<Node> nextLayer = new ArrayList<Node>();
		nextLayer.add(this.root);
		while((nextLayer=this.printLayer(nextLayer)).size()>0){
			System.out.println();
		}
	}
	
	private ArrayList<Node> printLayer(ArrayList<Node> nodeLayer){
		ArrayList<Node> next = new ArrayList<Node>();
		for(Node n: nodeLayer){
			if(n.left!=null)
				next.add(n.left);
			if(n.right!=null)
				next.add(n.right);
			System.out.print(" "+n.data);
		}
		return next;
	}
	public static void main(String[] args){
		int[] input0 = new int[]{5,4,7,10,3,1,2,-4,-2,8,9};
		BST bst = new BST(input0);
		bst.printInOrder();
		System.out.println("-------");
		bst.displayBST();
		System.out.println("-------");
		Node n = bst.findNode(2);
		bst.printInOrder();
		
		System.out.println("-------");
		bst.displayBST();
		
	}
}

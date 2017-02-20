


public class AVLTree extends BST {

	public AVLTree(int[] initialValues) {
		super(initialValues);
	}
	
	/**
	 * The return value must be pointed to be the original parent of the subroot argument
	 * This method should only be called on non-null nodes with a non-null left subtree (the subtree can be just a single node)
	 * @param subroot
	 * @return
	 */
	public Node rightRotate(Node subroot){
		Node temp = new Node(-100);
		boolean tempneeded = false;
		Node nextSubroot = subroot.left;
		
		if(nextSubroot.right != null){
			temp = nextSubroot.right;
			tempneeded = true;
		}
		nextSubroot.right  = subroot;
		if(tempneeded){
			subroot.left = temp;
		}
		
		// Which heights should be updated and where?
		nextSubroot.height = Math.max((nextSubroot.left!=null ? nextSubroot.left.height : 0),
									 (nextSubroot.right!=null ? nextSubroot.right.height : 0));
		
		subroot.height = Math.max(subroot.left.height, (subroot.right!=null ? subroot.right.height : 0));
		
		// TODO: Any other heights to manage?
		// return value must be attached into to the rest of the structure
		return nextSubroot;
	}
	
	/**
	 * The return value must be pointed to be the original parent of the subroot argument
	 * This method should only be called on non-null nodes with a non-null right subtree (the subtree can be just a single node)
	 * @param subroot
	 * @return
	 */
	public Node leftRotate(Node subroot){
		Node temp = new Node(-100);
		boolean tempneeded = false;
		Node nextSubroot = subroot.right;
		
		if(nextSubroot.left!=null){
			temp = nextSubroot.left;
			tempneeded = true;
		}
		nextSubroot.left  = subroot;
		if(tempneeded){
			subroot.right = temp;
		}
		// return value must be attached into to the rest of the structure
		return nextSubroot;
	}
	/**
	 * May cause a rebalancing of the AVLTree
	 * @param n
	 */
	public void insert(Node n){
		root = insert(root, n);
	}
	
	public int loadBalance(Node n){
		return (n.left!=null ? n.left.height : 0) - (n.right!=null ? n.right.height : 0);
	}
	
	/**
	 * Inserts from a particular starting origin node
	 * 
	 * @param origin
	 * @param n
	 */
	private Node insert(Node origin, Node n){
		if(origin.data > n.data){ // new value belongs in left subtree
			if(origin.left==null){
				origin.left = n;
			} else {
				
				//// INSERTION DOWN LEFT EDGE  ~ Traverse Left Subtree ////
				
				Node resultNode = insert(origin.left, n);
				// since insertion may have caused a rebalance, we must link to the returned node
				origin.left = resultNode;
				// update height of origin node
				origin.height = Math.max(resultNode.height, (origin.right!=null ? origin.right.height : 0));
				int originLoadBalance = loadBalance(origin);
				// Find the particular case, if too unbalanced.
				
				if(originLoadBalance > 1){ // left-heavy
					int leftLoadBalance = loadBalance(origin.left);
					if(leftLoadBalance < 0){ 
						// resultnode is right heavy
						// *** LR case *** //
						origin.left = leftRotate(resultNode);
						origin = rightRotate(origin);
						// TODO: Adjust heights??
					} else {
						// *** LL case *** //
						origin = rightRotate(origin);
						// TODO: aDjust heights?
					}
					
				} else if(originLoadBalance < -1){ // right-heavy
					int rightLoadBalance = loadBalance(origin.right);
					if(rightLoadBalance > 0){
						// *** RL case *** //
						origin.right = rightRotate(resultNode);
						origin = leftRotate(origin);
						// TODO: height fixes?
					} else {
						// *** RR case *** //
						origin = leftRotate(origin);
						// TODO: height fixes?
					}
					
				}
				
			}
		} else if(origin.data < n.data) { // new value belongs in right subtree
			if(origin.right==null){
				origin.right = n;
			} else {
				
				//// INSERTION DOWN RIGHT EDGE  ~ Traverse Right Subtree ////
				// Once a location is found for the
				
				Node resultNode = insert(origin.right, n);
				// since insertion may have caused a rebalance, we must link to the returned node
				origin.right = resultNode;
				// update height of origin node
				origin.height = Math.max(resultNode.height, (origin.left!=null ? origin.left.height : 0));
				int originLoadBalance = loadBalance(origin);
				
				if(originLoadBalance > 1){ // left-heavy
					int leftLoadBalance = loadBalance(origin.left);
					if(leftLoadBalance < 0){ 
						// resultnode is right heavy
						// *** LR case *** //
						origin.left = leftRotate(resultNode);
						origin = rightRotate(origin);
						// TODO: Adjust heights??
					} else {
						// *** LL case *** //
						origin = rightRotate(origin);
						// TODO: aDjust heights?
					}
					
				} else if(originLoadBalance < -1){ // right-heavy
					int rightLoadBalance = loadBalance(origin.right);
					if(rightLoadBalance > 0){
						// *** RL case *** //
						origin.right = rightRotate(resultNode);
						origin = leftRotate(origin);
						// TODO: height fixes?
					} else {
						// *** RR case *** //
						origin = leftRotate(origin);
						// TODO: height fixes?
					}
					
				}
				
			}
		} else {
			// do nothing. value already exists in the BST.
		}

		return root;
	}
	
	public static void main(String[] args){
		int[] input0 = new int[]{7,3,11,1,5,9,13,0,2,4,6,8,10,12,14};
		AVLTree avlTree = new AVLTree(input0);
		avlTree.printInOrder();
		
		System.out.println("-------");
		Node n = avlTree.findNode(3);
		Node returnNode = avlTree.rightRotate(n);
		avlTree.findNode(7).left = returnNode;
		
		avlTree.printInOrder();
		avlTree.displayBST();
		
		Node n2 = avlTree.findNode(3);
		avlTree.leftRotate(n2);
		avlTree.displayBST();
		
		avlTree.leftRotate(avlTree.root);
		avlTree.displayBST();
	}
}

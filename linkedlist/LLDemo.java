package linkedlist;

import linkedlist.LL.EmptyLLException;

public class LLDemo {
	public static void main(String[] args){

		int[] nodeValues = new int[]{7,5,9,2,10,40,1};
		LLNode<Integer> head = new LLNode<Integer>(nodeValues[0]);
		LLNode<Integer> ptr = head;

		// Create the starting Linked list that is just a LLNode with a few connections
		for(int i=1; i<nodeValues.length; i++){
			int val = nodeValues[i];
			ptr.next = new LLNode<Integer>(val);
			ptr = ptr.next;
		}

		// Create the linked list object
		LL<Integer> intLL = new LL<Integer>(head);
		System.out.println(intLL);
		LLNode<Integer> i = null;
		try {
			i = intLL.removeHead();
			System.out.println("*removed head* (value: "+i+")");
		} catch (EmptyLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(intLL);

		i = intLL.removeTail();
		System.out.println("*removed tail* (value: "+i+")");

		System.out.println(intLL);


	}
}

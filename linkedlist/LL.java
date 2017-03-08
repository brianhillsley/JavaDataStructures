package linkedlist;

/**
 * A generic Linked List (LL) implemented in Java
 * @author Brian Hillsley
 *
 * @param <T>
 */
public class LL<T> {
	public static class EmptyLLException extends Exception {}

	LLNode<T> head;
	LLNode<T> tail;
	int size = 0;
	
	public LL(LLNode head){
		this.head = head;
		LLNode<T> ptr = head;
		size = 1;
		while(ptr.next!=null){ // counts number of elements
			ptr=ptr.next;
			size++;
		}
		this.tail = ptr; // set tail 
	}

	/**
	 * Adding a single element by its data value
	 * @param data
	 */
	public void add(T data){
		LLNode newTail = new LLNode(data);
		if(tail!=null){
			tail.next = newTail;
			tail = newTail;
		} else { 
			// tail==null and so must head
			tail = newTail;
			head = newTail;
		}
		size++;
	}

	public LLNode<T> removeTail(){
		LLNode<T> ptr = head;
		while(ptr.next!=null&&ptr.next.next!=null){
			ptr=ptr.next;
		}
		// Save copy of old tail
		LLNode<T> oldTail = ptr.next;
		// Delete the old tail link
		ptr.next = null;
		this.tail = ptr;
		return oldTail;
	}

	public LLNode<T> removeHead() throws EmptyLLException{
		if(head!=null){
			LLNode<T> newHead = (head.next!=null?head.next:null);
			LLNode<T> oldHead = head;
			head = newHead;
			size--;
			return oldHead;
		} else {
			// head is null
			throw new EmptyLLException();
		}
	}
	public String toString(){
		return "Head:"+head+", Tail:"+tail+", Size:"+size;
	}
}

package linkedlist;

import java.util.Iterator;

/**
 * A generic Linked List (LL) implemented in Java
 * @author Brian Hillsley
 *
 * @param <T>
 */
public class LL<T> implements Iterable<T> {
	public static class EmptyLLException extends Exception {}

	private class LLIterator<A> implements Iterator<A>{
	LLNode<A> head;
	LLNode<A> ptr;
	public LLIterator(LLNode<A> head) {
		this.head = head;
		ptr = this.head;
	}

	@Override
	public boolean hasNext() {
		if(ptr!=null){
			return true;
		}
		return false;
	}

	@Override
	public A next() {
		LLNode<A> retNode = ptr;
		if(ptr.next!=null)
			ptr = ptr.next;
		else 
			ptr = null;
		return retNode.data;
	}
	
}

	LLNode<T> head;
	LLNode<T> tail;
	int size = 0;
	
	public LL(LLNode<T> head) {
		this.head = head;
		LLNode<T> ptr = head;
		size = 1;
		while(ptr.next!=null){ // counts number of elements
			ptr=ptr.next;
			size++;
		}
		this.tail = ptr; // set tail 
	}
	public LL(){
		this.head = null;
		this.tail = null;
	}

	/**
	 * Adding a single element by its data value
	 * @param data
	 */
	public void add(T data){
		LLNode<T> newTail = new LLNode<T>(data);
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
	
	@Override
	public Iterator<T> iterator() {
		return new LLIterator<T>(head);
	}
}

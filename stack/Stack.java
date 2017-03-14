package stack;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A generic stack implementation
 * 
 * @author Brian Hillsley
 *
 * @param <T> whatever generic type you like
 */
public class Stack<T>{

	private ArrayList<T> values;
	private int size;
	
	public Stack(){
		values = new ArrayList<T>();
		size = 0;
	}
	
	public T peek(){
		return values.get(size-1);
	}
	
	public void push(T t){
		values.add(t);
		size++;
	}
	
	public T pop() {
		int lastIndex = size-1;
		T retT = values.get(lastIndex);
		values.remove(lastIndex);
		size--;
		return retT;
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		} else {
			return false;
		}
	}
	
	public int size(){
		return size;
	}
	 
	public String toString(){
		 StringBuilder sb = new StringBuilder();
		 Collections.reverse(this.values); // reverse order temp for printing
		 
		 for(T t : this.values){
			 sb.append(t.toString() + "\n");
		 } 
		 Collections.reverse(this.values); // correct the order
		 return sb.toString();
		 
	 }
	
}

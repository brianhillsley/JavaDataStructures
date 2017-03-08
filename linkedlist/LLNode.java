package linkedlist;

public class LLNode<T> {
	LLNode(T data){
		this.data=data;
	}
	LLNode next;
	T data;
	public String toString(){
		return ""+data.toString();
	}
}

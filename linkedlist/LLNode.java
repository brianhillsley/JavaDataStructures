package linkedlist;

public class LLNode<T> {
	public LLNode(T data){
		this.data=data;
	}
	LLNode<T> next;
	T data;
	public String toString(){
		return ""+data.toString();
	}
}

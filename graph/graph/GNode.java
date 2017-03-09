package graph;

import linkedlist.LL;

public class GNode<J> {
	public int id;
	public J data;
	LL<GNode<J>> edges = new LL<GNode<J>>();
	public GNode(int id, J data){
		this.id = id;
		this.data = data;
	}
	public void addEdge(GNode<J> gnode){
		this.edges.add(gnode); 
	}
	public String toString(){
		return data.toString();
	}
	
	public boolean equals(GNode<J> other){
		if(other.data.equals(this.data) && other.id == this.id){
			return true;
		}
		return false;
	}
	public int hashCode(){
		return (id+""+data).hashCode();	
	}
}
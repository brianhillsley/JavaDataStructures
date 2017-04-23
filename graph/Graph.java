package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

import graph.GNode;

/**
 * Generic Directed Graph
 * @author bhills
 *
 * @param <K>
 */
public class Graph<K> {
	
	/**
	 * Generic Queue
	 * @author bhills
	 *
	 * @param <T>
	 */
	private class Q<T> {
		private ArrayList<T> list = new ArrayList<T>();
		private int size = 0;
		public void addItem(T item){
			list.add(item);
			size++;
		}
		
		public T removeItem(){
			T removed = list.remove(size-1);
			size--;
			return removed;
		}
		
		public int size(){
			return size;
		}
		
		public ArrayList<T> asList(){
			return list;
		}
		
	}
	
	private HashMap<Integer, GNode<K>> nodeID = new HashMap<Integer, GNode<K>>();
	
	public GNode<K> getNode(int id){
		return nodeID.get(id);
	}
	
	public void addNode(int id, K data){
		nodeID.put(id, (new GNode<K>(id, data)));
	}
	
	public void addEdge(int srcID, int destID){
		getNode(srcID).addEdge(getNode(destID));
	}
	
	/**
	 * Tells if you can travel to graph node destination starting from graph node source.
	 * Bootstrap for isPathDFS( GNode, GNode)
	 * @param srcID ID of starting graph node (the source)
	 * @param destID ID of the ending graph node (the destination)
	 * @return true if path exists, false otherwise
	 */
	public boolean hasPathToDFS(int srcID, int destID){
		GNode<K> start = getNode(srcID), end = getNode(destID);
		HashSet<Integer> visitedIDs = new HashSet<Integer>(); // new hashset for each call
		return hasPathDFS(start, end, visitedIDs);
	}
	
	private boolean hasPathDFS(GNode<K> start, GNode<K> end, HashSet<Integer> visitedIDs){
//		System.out.println("start: "+start + ", dest: "+end);
//		for(int id: visitedIDs){
//			System.out.println(id);
//		}
		Objects.requireNonNull(start);
		Objects.requireNonNull(end);
		
		// If an ID is being revisited, then we have encountered a loop
		if(visitedIDs.contains(start.id)){
			return false;
		}
		visitedIDs.add(start.id);
		if(start.equals(end)){
			return true;
		}
		
		// check all children that haven't been visited
		for(GNode<K> child : start.edges){
			if(hasPathDFS(child, end, visitedIDs)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasPathToBFS(int srcID, int destID){
		// uses a queue
		Q<GNode<K>> queue = new Q<GNode<K>>();
		
		// Locate start and end node objects
		GNode<K> start = getNode(srcID);
		GNode<K> end = getNode(destID);
		
		// Keep track of which ID's have been on the queue
		HashSet<Integer> queuedIDs = new HashSet<Integer>();
		
		queue.addItem(start); // queue size starts with 1 item, the start
		
		while(queue.size>0){
			GNode<K> current = queue.removeItem();
			
			for(GNode<K> neighbor : current.edges){
				if(neighbor.equals(end)){
					return true;
				} else if(!queuedIDs.contains(neighbor.id)){
					queue.addItem(neighbor);
					queuedIDs.add(neighbor.id);
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns null if the path is not found
	 * @param src
	 * @param dest
	 * @return
	 */
	public ArrayList<GNode<K>> path(GNode<K> src, GNode<K> dest){
		Objects.requireNonNull(src, "Path function call: Source node cannot be null.");
		Objects.requireNonNull(dest, "Path function call: Destination node cannot be null.");
		
		
		ArrayList<GNode<K>> initPath = new ArrayList<GNode<K>>();
		initPath.add(src);
		return path(src, dest, new HashSet<Integer>(), initPath);
	}
	
	private ArrayList<GNode<K>> path(GNode<K> src, GNode<K> dest, HashSet<Integer> visitedIDs, ArrayList<GNode<K>> path){
		Objects.requireNonNull(src); Objects.requireNonNull(dest);
		
		if(src.id == dest.id) { 
			return path; // End of the search!
		}
		
		Q<GNode<K>> locals = new Q<GNode<K>>();
		
		for(GNode<K> neighbor : src.edges){
			if(neighbor.id==dest.id){
				path.add(neighbor);
				return path; // End of search!
			} else if(!(visitedIDs.contains(neighbor.id))){
				locals.addItem(neighbor);
			}
		} // No neighbors were the destination node
		
		for(GNode<K> neighbor : locals.asList()){
			visitedIDs.add(neighbor.id);
			path.add(neighbor);
			if(path(neighbor, dest, visitedIDs,path)!=null)
				return path; // Solution returned
		}
		return null;
	}
}

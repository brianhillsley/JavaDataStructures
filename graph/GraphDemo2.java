package graph;

import java.util.ArrayList;

/**
 * Demonstrates the getting the path between two nodes for a given graph.
 * @author bhills
 *
 */
public class GraphDemo2 {
	
	public static void parseEdges(String s, Graph g){
		String[] edges = s.split(", ");
		for(String edge: edges){
			String[] pair = edge.split("->");
			char ch_src = pair[0].charAt(0);
			char ch_dest = pair[1].charAt(0);
			int srcID = ch_src - 'A';
			int destID = ch_dest - 'A';
			g.addEdge(srcID, destID);
		}
	}
	
	public static void main(String[] args){
		String edges = "A->F, A->B, A->J, B->A, B->B, B->C, C->A,"
				+ " C->H, C->D, D->H, D->J, E->G, F->E, F->Q, H->B,"
				+ " H->C, Q->E, G->K, K->Z, Z->D";

		// Create graph nodes (A-Z) 26 nodes
		Graph<Character> g = new Graph<Character>();
		char startWith = 'A';
		for( int i = 0; i<26; i++)
			g.addNode(i, new Character((char) ('A'+i)));
		parseEdges(edges, g);
		
		ArrayList<GNode<Character>> p = g.path(g.getNode(0), g.getNode(3)); // path('A', 'D')
		System.out.println("path("+g.getNode(0)+", "+g.getNode(3)+") = "+p);
	
		p = g.path(g.getNode(0), g.getNode(1)); // path('A', 'B')
		System.out.println("path("+g.getNode(0)+", "+g.getNode(1)+") = "+p);
		
		p = g.path(g.getNode(0), g.getNode(0)); // path('A', 'A')
		System.out.println("path("+g.getNode(0)+", "+g.getNode(0)+") = "+p);
		
	}
}

package graph;

public class GraphDemo {
	public static void main(String[] args){
		int alex = 0;
		int jack = 1;
		int nicole = 2;
		int brian = 3;
		int suzy = 4;
		int sam = 5;
		int tom = 6;
		int nate = 7;
		int jean = 8;
		int beth = 9;
		int tj = 10;
		
		// Create graph nodes
		Graph<String> g = new Graph<String>();
		g.addNode(alex,"Alex");
		g.addNode(jack,"Jack");
		g.addNode(nicole,"Nicole");
		g.addNode(brian,"Brian");
		g.addNode(suzy,"Suzy");
		g.addNode(sam,"Sam");
		g.addNode(tom,"Tom");
		g.addNode(nate,"Nate");
		g.addNode(jean,"Jean");
		g.addNode(beth,"Beth");
		g.addNode(tj,"TJ");
		
		// Form edges of graph
		g.addEdge(alex, tom);
		g.addEdge(jack, nicole);
		g.addEdge(nicole, jack);
		g.addEdge(brian, sam);
		g.addEdge(brian, nate);
		g.addEdge(suzy, jean);
		g.addEdge(sam, suzy);
		g.addEdge(sam, tom);
		g.addEdge(sam, jean);
		g.addEdge(tom, brian);
		g.addEdge(tom, suzy);
		g.addEdge(nate, alex);
		g.addEdge(jean, jack);
		g.addEdge(jean, brian);
		g.addEdge(beth, tj);
		g.addEdge(tj, nicole);
		g.addEdge(tj, beth);

		System.out.println(" ---- DFS SEARCH TESTS ---- ");
		if(g.hasPathToDFS(brian, jack)){
			System.out.println("Brian has a path to Jack");
		}
		
		if(g.hasPathToDFS(jack, nicole)){
			System.out.println("Jack has a path to Nicole");
		}
		
		if(g.hasPathToDFS(tj, tj)){
			System.out.println("TJ has a path to TJ");
		}
		
		if(g.hasPathToDFS(brian, alex)){
			System.out.println("Brian has a path to Brian");
		}
		
		if(g.hasPathToDFS(jack, alex)){
			System.out.println("Jack has a path to Alex");
		} else {
			System.out.println("Jack does not have a path to Alex");
		}
		
		if(g.hasPathToDFS(nicole, sam)){
			System.out.println("Nicole has a path to Sam");
		} else {
			System.out.println("Nicole does not have a path to Sam");
		}
		
		System.out.println("---- BFS SEARCH TESTS ----");
		// BFS tests 
		if(g.hasPathToBFS(brian, jack)){
			System.out.println("Brian has a path to Jack");
		}
		
		if(g.hasPathToBFS(jack, nicole)){
			System.out.println("Jack has a path to Nicole");
		} else {
			System.out.println("Jack does not have a path to Nicole");
		}
		
		if(g.hasPathToBFS(tj, tj)){
			System.out.println("TJ has a path to TJ");
		} else {
			System.out.println("TJ does not have a path to TJ");
		}
		
		if(g.hasPathToBFS(brian, alex)){
			System.out.println("Brian has a path to Brian");
		} else {
			System.out.println("Brian does not have a path to Brian");
		}
		
		if(g.hasPathToBFS(jack, alex)){
			System.out.println("Jack has a path to Alex");
		} else {
			System.out.println("Jack does not have a path to Alex");
		}
		
		if(g.hasPathToBFS(sam, nicole)){
			System.out.println("Sam has a path to Nicole");
		} else {
			System.out.println("Sam does not have a path to Nicole");
		}
		
		if(g.hasPathToBFS(nicole, sam)){
			System.out.println("Nicole has a path to Sam");
		} else {
			System.out.println("Nicole does not have a path to Sam");
		}
		
	}
}

//DFS Algorithm reference: https://www.programiz.com/dsa/graph-dfs

import java.util.*;

public class DFS {
    private Graph graph;
    private ArrayList<Boolean> isVisited;
    private int depth;

    public DFS(Graph graph){
        this(graph, 4);
    }

    public DFS(Graph graph, int depth){
        this.graph = graph;
        this.depth = depth;
        this.isVisited = graph.getVisitedNodes();
    }

    public void traverse(int vertex){
        LinkedList<Integer> adjLists[];
        Iterator<Integer> ite;
        
        isVisited.set(vertex, true); // Mark the current vertex as visited
        System.out.print(vertex + " "); // Print the visited vertex

        adjLists = graph.getAdjacencyLists();
        ite = adjLists[vertex].listIterator(); // Get an iterator to traverse the adjacency list of the current vertex
        while (ite.hasNext()) {
        int adj = ite.next();
        if (!isVisited.get(adj))
            traverse(adj); // Recursive call to DFS on the unvisited adjacent vertices
        }
    }
}

//DFS Algorithm reference: https://www.programiz.com/dsa/graph-dfs

import java.util.*;

public class DFS {
    public Graph graph;
    private ArrayList<Boolean> isVisited;
    private int depth;
    protected boolean goalFound;

    public DFS(Graph graph) {
        this(graph, 4);
    }

    public DFS(Graph graph, int depth) {
        this.graph = graph;
        this.depth = depth;
        this.isVisited = graph.getVisitedNodes();
        this.goalFound = false;
    }

    public void traverse(int vertex, int goal) {
        if (goalFound) {
            return; // Goal state is already found, so stop the traversal
        }

        LinkedList<Integer>[] adjLists;
        Iterator<Integer> ite;

        isVisited.set(vertex, true); // Mark the current vertex as visited
        System.out.print(vertex + " "); // Print the visited vertex

        if (vertex == goal) {
            System.out.println("\nGoal state found!");
            goalFound = true; // Set the flag to true to indicate the goal state is found
            return;
        }

        adjLists = graph.getAdjacencyLists();
        ite = adjLists[vertex].listIterator(); // Get an iterator to traverse the adjacency list of the current vertex
        while (ite.hasNext()) {
            int adj = ite.next();
            if (!isVisited.get(adj))
                traverse(adj, goal); // Recursive call to DFS on the unvisited adjacent vertices
        }
    }
}

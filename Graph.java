//DFS Algorithm reference: https://www.programiz.com/dsa/graph-dfs

import java.util.*;

public class Graph {
    private LinkedList<Integer> adjLists[];
    private ArrayList<Boolean> isVisited;

    /**
     * The Graph constructor intializes the adjacent list to create an adjacency 
     * list for each vertex and visited nodes to false.
     * @param vertices
     */
    public Graph(int vertices){
        int i;
        
        adjLists = new LinkedList[vertices];
        isVisited = new ArrayList<>(vertices);

        for(i = 0; i < vertices; i++){
            adjLists[i] = new LinkedList<Integer>();
            isVisited.add(false);
        }
    }

    public void addEdge(int source, int destination){
        adjLists[source].add(destination);
    }

    public ArrayList<Boolean> getVisitedNodes(){
        return isVisited;
    }

    public LinkedList<Integer>[] getAdjacencyLists(){
        return adjLists;
    }
}

//DFS Algorithm reference: https://www.programiz.com/dsa/graph-dfs

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String args[]) {
    String edge, numOfVertices;
    String[] input;
    int src, dest;
    Graph g = null;
      

    try{
      File myFile = new File("test.txt");
      Scanner scan = new Scanner(myFile);

        if(scan.hasNextLine()){
          numOfVertices = scan.next();
          g = new Graph(Integer.parseInt(numOfVertices)); // Create a graph with 13 vertices
        }

        while(scan.hasNextLine()){
          edge = scan.nextLine();
          input = edge.split(" ");

          if(input.length > 1){
            src = Integer.parseInt(input[0]);
            dest = Integer.parseInt(input[1]);
            g.addEdge(src, dest);
          }
        }
        
          scan.close();
        }catch(FileNotFoundException e){
          System.out.println("Error");          
      e.printStackTrace();
    }

    System.out.println("Following is Depth First Traversal");

    DFS dfs = new DFS(g);
    dfs.traverse(1); // Start DFS from vertex 1
  }
}

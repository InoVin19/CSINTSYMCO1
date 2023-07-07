import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.*;

public class Driver extends Application {

    private Map<String, List<String>> createGraph() {
        // Create your graph here
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("Oradea", Arrays.asList("Zerind", "Sibiu"));
        graph.put("Zerind", Collections.singletonList("Arad"));
        graph.put("Sibiu", Arrays.asList("Arad", "Fagaras", "Rimnicu Vilcea"));
        graph.put("Arad", Arrays.asList("Timisoara"));
        graph.put("Fagaras", Arrays.asList("Bucharest"));
        graph.put("Rimnicu Vilcea", Arrays.asList("Pitesti", "Craiova"));
        graph.put("Timisoara", Arrays.asList("Lugoj"));
        graph.put("Lugoj", Arrays.asList("Mehadia"));
        graph.put("Mehadia", Arrays.asList("Dobreta"));
        graph.put("Dobreta", Arrays.asList("Craiova"));
        graph.put("Craiova", Arrays.asList("Pitesti"));
        graph.put("Pitesti", Arrays.asList("Bucharest"));
        graph.put("Bucharest", Arrays.asList("Giurgiu", "Urziceni"));
        graph.put("Giurgiu", Arrays.asList());
        graph.put("Urziceni", Arrays.asList("Vaslui", "Hirsova"));
        graph.put("Vaslui", Arrays.asList("Lasi"));
        graph.put("Lasi", Arrays.asList("Neamt"));
        graph.put("Neamt", Arrays.asList());
        graph.put("Hirsova", Arrays.asList("Eforie"));
        graph.put("Eforie", Arrays.asList());


        return graph;
    }

    private TreeItem<String> buildTree(String vertex, Map<String, List<String>> graph, Set<String> explored) {
        TreeItem<String> node = new TreeItem<>(vertex);

        explored.add(vertex);

        List<String> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!explored.contains(neighbor)) {
                    TreeItem<String> childNode = buildTree(neighbor, graph, explored);
                    node.getChildren().add(childNode);
                }
            }
        }

        return node;
    }

    private boolean dfsTraversal(TreeItem<String> currentNode, String goal, List<String> path) {
        currentNode.setExpanded(true);

        String nodeValue = currentNode.getValue();
        path.add(nodeValue);

        if (nodeValue.equals(goal)) {
            return true;
        }

        List<TreeItem<String>> children = currentNode.getChildren();
        for (TreeItem<String> child : children) {
            if (dfsTraversal(child, goal, path)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, List<String>> graph = createGraph();
        String start = "Sibiu";
        String goal = "Animo";

        TreeItem<String> root = buildTree(start, graph, new HashSet<>());
        TreeView<String> treeView = new TreeView<>(root);

        Label pathLabel = new Label();
        pathLabel.setWrapText(true);
        BorderPane.setAlignment(pathLabel, Pos.CENTER);
        BorderPane.setMargin(pathLabel, new Insets(10));

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(treeView);
        rootPane.setBottom(pathLabel);
   
        Scene scene = new Scene(rootPane, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Depth-First-Search");
        primaryStage.show();

        List<String> path = new ArrayList<>();
        if (!dfsTraversal(root, goal, path)){
            pathLabel.setText("Start state: " + start + "\nGoal State: " + goal + "\n\npath: " + String.join(" -> ", path) + ". \n\nGoal State not found.");
        } else{
            pathLabel.setText("Start state: " + start + "\nGoal State: " + goal + "\n\npath: " + String.join(" -> ", path) + ". \n\nGoal State reached.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.util.*;

//code

public class Driver extends Application {

    private Map<String, List<String>> createGraph(File file) {
        // Create your graph here
        Map<String, List<String>> graph = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split("->");
                String vertex = values[0].trim();
                String[] neighbors = values[1].split(",");
                graph.put(vertex, Arrays.asList(neighbors));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
    	FileChooser choose = new FileChooser();
        choose.setTitle("Select Input File");
        choose.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));

        File input = choose.showOpenDialog(primaryStage);
        if (input == null) {
            return;
        }
    	
        Map<String, List<String>> graph = createGraph(input);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routingtables;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arefj
 */
public class RoutingTables {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            List<Node> resultSet = algorytm(i);
            printReslultSet(resultSet);
        }
    }

    private static void printReslultSet(List<Node> resultSet) {
        System.out.println("Routing table for " + resultSet.get(0).name + ":");
        System.out.println("------------------------");
        for (Node node : resultSet) {
            System.out.println("Distance to " + node.name + ": " + node.distance);
            System.out.print("Path: ");
            
            System.out.print(node.name);
            if (node.parent != null) {
                Node parent = node.parent.from;
                Boolean bool = true;
                while (bool) {
                    System.out.print(" - " + parent.name);
                    if (parent.parent != null){
                    parent = parent.parent.from;
                    } else {
                        bool = false;
                    }
                }
            }
            System.out.println();
            System.out.println("------------------------");
        }
        System.out.println();
    }

    private static List<Node> algorytm(int index) {
        List<Node> nodes = createNodes();
        createEdges(nodes);
        List<Node> resultSet = new ArrayList();

        nodes.get(index).distance = 0;

        while (!nodes.isEmpty()) {
            Node bestNode = findBestNode(nodes);
            addToResultSet(resultSet, bestNode);
            nodes.remove(bestNode);
        }

        return resultSet;
    }

    private static void addToResultSet(List<Node> resultSet, Node node) {
        resultSet.add(node);
        node.done = true;
        for (Edge edge : node.edges) {
            if (!edge.to.done && (edge.distance + node.distance < edge.to.distance)) {
                edge.to.distance = edge.distance + node.distance;
                edge.to.parent = edge;
            }
        }
    }

    private static Node findBestNode(List<Node> nodes) {
        Node bestNode = nodes.get(0);
        for (Node node : nodes) {
            if (node.distance < bestNode.distance) {
                bestNode = node;
            }
        }

        return bestNode;
    }

    private static List<Node> createNodes() {
        List<Node> nodes = new ArrayList();

        nodes.add(new Node("A"));
        nodes.add(new Node("B"));
        nodes.add(new Node("C"));
        nodes.add(new Node("D"));
        nodes.add(new Node("E"));
        nodes.add(new Node("F"));

        return nodes;
    }

    private static void createEdges(List<Node> nodes) {
        createEdge(nodes.get(0), nodes.get(1), 10);
        createEdge(nodes.get(0), nodes.get(2), 5);
        createEdge(nodes.get(0), nodes.get(3), 9999);
        createEdge(nodes.get(0), nodes.get(4), 3);
        createEdge(nodes.get(0), nodes.get(5), 12);
        createEdge(nodes.get(1), nodes.get(2), 17);
        createEdge(nodes.get(1), nodes.get(3), 9);
        createEdge(nodes.get(1), nodes.get(4), 17);
        createEdge(nodes.get(1), nodes.get(5), 12);
        createEdge(nodes.get(2), nodes.get(3), 35);
        createEdge(nodes.get(2), nodes.get(4), 3);
        createEdge(nodes.get(2), nodes.get(5), 12);
        createEdge(nodes.get(3), nodes.get(4), 9999);
        createEdge(nodes.get(3), nodes.get(5), 12);
        createEdge(nodes.get(4), nodes.get(5), 12);
    }

    private static void createEdge(Node node1, Node node2, int distance) {
        node1.edges.add(new Edge(node1, node2, distance));
        node2.edges.add(new Edge(node2, node1, distance));
    }
}

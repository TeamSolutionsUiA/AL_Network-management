/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al_network.management;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arefj
 */
public class AL_NetworkManagement_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Node> nodes = createNodes();
        createEdges(nodes);
        
        List<Node> nodesMST = primsMST(nodes);
        printMST(nodesMST);
    }
    
    private static void printMST(List<Node> nodesMST){
        System.out.println("Minimal cost needed connections:");
        int totalCost = 0;
        for (int i = 1; i < nodesMST.size(); i++){
            System.out.println("[" + nodesMST.get(i).shortestEdge.from.name + 
                    " " + nodesMST.get(i).shortestEdge.to.name + "]");
            totalCost += nodesMST.get(i).shortestDistance;
        }
        System.out.println("Total cost: " + totalCost);
    }

    private static List<Node> primsMST(List<Node> nodes) {
        List<Node> nodesMST = new ArrayList();
        
        nodes.get(0).shortestDistance = 0;

        while (!nodes.isEmpty()) {
            Node bestNode = findBestNode(nodes);
            addToMST(nodesMST, bestNode);
            nodes.remove(bestNode);
        }

        return nodesMST;
    }

    private static void addToMST(List<Node> nodesMST, Node node) {
        nodesMST.add(node);
        node.done = true;
        for (Edge edge : node.edges) {
            if (!edge.to.done && (edge.distance < edge.to.shortestDistance)) {
                edge.to.shortestDistance = edge.distance;
                edge.to.shortestEdge = edge;
            }
        }
    }

    private static Node findBestNode(List<Node> nodes) {
        Node bestNode = nodes.get(0);
        for (Node node : nodes) {
            if (node.shortestDistance < bestNode.shortestDistance) {
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
    
    private static void createEdge(Node node1, Node node2, int distance){
        node1.edges.add(new Edge(node1, node2, distance));
        node2.edges.add(new Edge(node2, node1, distance));
    }
}

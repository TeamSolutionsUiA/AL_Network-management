/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al_network.management;

/**
 *
 * @author arefj
 */
public class Edge {
    Node from;
    Node to;
    int distance;

    public Edge(Node from, Node to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}

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
public class Node {
    String name;
    List<Edge> edges;
    boolean done;
    int distance;
    Edge parent;

    public Node(String name) {
        this.name = name;
        edges = new ArrayList();
        done = false;
        distance = 10000;
        parent = null;
    }
}

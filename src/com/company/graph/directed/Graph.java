package com.company.graph.directed;
        ;

import java.util.*;
import com.company.disjoinedset.DisjointSetForest;
import com.company.exceptions.FullStackException;
import com.company.exceptions.ItemOutOfRangeException;
public class Graph<T> {

    private int[][] matrix;
    private ArrayList<T> vertices;
    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp.
        this.vertices = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (!vertices.contains(edge.getSource())) {
                vertices.add(edge.getSource());
            }
            if (!vertices.contains(edge.getDestination())) {
                vertices.add(edge.getDestination());
            }

        }
        this.matrix = new int[vertices.size()][vertices.size()];
        for (Edge<T> edge : edges) {
            int srcIndex = this.vertices.indexOf(edge.getSource());
            int destIndex = this.vertices.indexOf(edge.getDestination());
            this.matrix[srcIndex][destIndex] = edge.getWeight();
        }
    }

    private String depthFirstVisit(T node, int[] colors) {
        StringBuilder result = new StringBuilder();
        int index = vertices.indexOf(node);
        colors[index] = 1;
        result.append(node.toString()).append(", ");
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[index][j] != 0) {
                if (colors[j] == 0) {
                    result.append(depthFirstVisit(vertices.get(j), colors));
                }
            }
        }
        return result.toString();
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        StringBuilder result = new StringBuilder();
        if (!vertices.contains(startNode)) {
            throw new NoSuchElementException();
        }
        int[] colors = new int[this.vertices.size()];
        if (colors[vertices.indexOf(startNode)] == 0) {
            result.append(depthFirstVisit(startNode, colors));
        }

        return result.substring(0, result.length()-2);
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wszerz od podanego wierzchołka
        if (!vertices.contains(startNode)) {
            throw new NoSuchElementException();
        }
        StringBuilder result = new StringBuilder();
        Queue<T> queue = new LinkedList<>();
        int[] colors = new int[vertices.size()];
        queue.add(startNode);
        colors[vertices.indexOf(startNode)] = 1;
        while (!queue.isEmpty()) {
            result.append(queue.peek()).append(", ");
            int index = vertices.indexOf(queue.peek());
            queue.remove();
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[index][j] != 0) {
                    if (colors[j] == 0) {
                        colors[j] = 1;
                        queue.add(vertices.get(j));
                    }
                }
            }
        }
        return result.substring(0, result.length()-2);
    }

    public int connectedComponents() throws ItemOutOfRangeException {
        // TODO: Liczba składowych spójnych grafu
        DisjointSetForest forest = new DisjointSetForest(this.vertices.size());
        for (int i = 0; i < this.vertices.size(); i++) {
            for (int j = 0; j < this.vertices.size(); j++) {
                if (matrix[i][j] != 0) {
                    if (forest.findSet(i) != forest.findSet(j)) {
                        forest.union(i, j);
                    }
                }
            }
        }
        return forest.getNumberOfSets();
    }
}

package com.company.graph.nondirected;

import java.util.*;
public class Graph<T> {
    private class listNode {
        private T value;
        private int distance;

        public listNode(T value, int distance) {
            this.value = value;
            this.distance = distance;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
    private Map<T, LinkedList<listNode>> graphMap;
    public Graph(List<Edge<T>> edges) {
        // TODO: Konstruktor
        graphMap = new HashMap<>();
        for (Edge<T> edge : edges) {
            if (!graphMap.containsKey(edge.getNode1())) {
                graphMap.put(edge.getNode1(), new LinkedList<>());
            }
            if (!graphMap.containsKey(edge.getNode2())) {
                graphMap.put(edge.getNode2(), new LinkedList<>());
            }

            graphMap.get(edge.getNode1()).add(new listNode(edge.getNode2(), edge.getDistance()));
            graphMap.get(edge.getNode2()).add(new listNode(edge.getNode1(), edge.getDistance()));
        }
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótsze ścieżki do każdego wierzchołka w grafie (Dijkstra)
        if (!this.graphMap.containsKey(startNode)) {
            throw new NoSuchElementException();
        }
        T currentNode = startNode;
        Map<T, Integer> pathsMap = new HashMap<>();
        Set<T> nodeSet = this.graphMap.keySet();
        for (T node : nodeSet) {
            if (node.equals(startNode)) {
                pathsMap.put(node, 0);
            }
            else {
                pathsMap.put(node, null);
            }
        }
        while (!nodeSet.isEmpty()) {
            for (listNode node : graphMap.get(currentNode)) {
                int distance = pathsMap.get(currentNode) + node.getDistance();
                if (pathsMap.get(node.getValue()) == null || pathsMap.get(node.getValue()) > distance) {
                    pathsMap.put(node.getValue(), distance);
                }
            }
            nodeSet.remove(currentNode);
            T min = null;
            for (T node : nodeSet) {
                if (pathsMap.get(node) != null) {
                    if (min == null || pathsMap.get(node) < pathsMap.get(min)) {
                        min = node;
                    }
                }
            }
            currentNode = min;
        }
        pathsMap.remove(startNode);
        return pathsMap;
    }
}

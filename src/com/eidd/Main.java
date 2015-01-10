package com.eidd;

public class Main {

    public static void main(String[] args) {

        int graph[][] = new int[][]{
                {0, 1, 3, 4, 5},
                {1, 0, 1, 4, 8},
                {3, 1, 0, 5, 1},
                {4, 4, 5, 0, 2},
                {5, 8, 1, 2, 0}};

        Backtracking b = new Backtracking(graph);
        b.genererTousLesCircuits(0, 5);
    }
}

package com.eidd;

public class Main {

    public static void main(String[] args) {

        int graph[][] = new int[][]{
                {0, 1, 3, 4, 5, 8},
                {1, 0, 1, 4, 8, 8},
                {3, 1, 0, 5, 1, 7},
                {4, 4, 5, 0, 2, 3},
                {5, 8, 1, 2, 0, 2},
                {8, 8, 7, 3, 2, 0}};

        Backtracking b = new Backtracking(graph);
        long temps_debut = System.currentTimeMillis();
        b.genererTousLesCircuits(0, graph.length);
        System.out.println("Temps d'execution Backtracking : " + (System.currentTimeMillis() - temps_debut) + "ms");
    }
}

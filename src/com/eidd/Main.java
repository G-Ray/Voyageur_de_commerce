package com.eidd;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*int graph[][] = new int[][]{
                {0, 1, 3, 4, 5, 8, 8, 8},
                {1, 0, 1, 4, 8, 8, 8, 7},
                {3, 1, 0, 5, 1, 7, 7, 7},
                {4, 4, 5, 0, 2, 3, 3, 3},
                {5, 8, 1, 2, 0, 2, 2, 2},
                {8, 8, 7, 3, 2, 1, 1, 1},
                {8, 8, 7, 3, 2, 0, 0, 1},
                {8, 8, 7, 3, 2, 1, 1, 0}};*/

        Random r = new Random();

        //Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de villes : ");
        //int n = sc.nextInt();
        System.out.println("Couts des transports : ");
        //int m = sc.nextInt();

        int n = 18;
        int m = 8;

        int graph[][] = new int[n][n];

        for(int i=0; i<n; i++) {
            System.out.print("|");
            for(int j=0; j<n; j++) {
                if(j==i)
                    graph[i][j] = 0;
                else
                    graph[i][j] = r.nextInt(m+1) + 1;
                System.out.print(graph[i][j] + "|");
            }
            System.out.println("");
        }

        Backtracking b = new Backtracking(graph);
        long temps_debut = System.currentTimeMillis();
        b.genererTousLesCircuits(0, graph.length);
        System.out.println("Temps d'execution Backtracking : " + (System.currentTimeMillis() - temps_debut) + "ms");

        Prog_dynamique p = new Prog_dynamique(graph);
        temps_debut = System.currentTimeMillis();
        p.coutCircuitMin(0, graph.length);
        System.out.println("Temps d'execution Prog_dynamique : " + (System.currentTimeMillis() - temps_debut) + "ms");
    }
}

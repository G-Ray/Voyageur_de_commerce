package com.eidd;

import java.util.LinkedList;

public class Backtracking {

    /**
     * Le graphe a parcourir, contient le poids de chaque arc
     */
    int graph[][];
    int nb_operations;

    Backtracking(int [][] graph) {
        this.graph = graph;
        nb_operations = 0;
    }

    private LinkedList<Integer> meilleurChemin;

    /**
     * Determine si un arc existe entre 2 sommets
     * @param i le premier arc
     * @param j le second arc
     * @return true si un arc existe, false sinon
     */
    public boolean arcExiste(int i, int j) {
        return (graph[i][j] != 0);
    }

    /**
     * Determine le poids d'un arc defini par 2 sommets
     * @param i le premier sommet
     * @param j le second sommet
     * @return le poids de l'arc
     */
    public int poids(int i, int j) {
        return graph[i][j];
    }

    /**
     * Calcule la distance totale d'un chemin
     * @param chemin le chemin a analyser
     * @return la distance totale du chemin
     */
    public int distance(LinkedList<Integer> chemin) {
        int dist = 0;
        for(int i=0; i<chemin.size()-1; i++) {
            dist += poids(chemin.get(i), chemin.get(i+1));
        }
        return dist;
    }

    /**
     * Genere les circuits
     * @param chemin Le chemin qu'on cherche a etendre
     * @param n nombre de sommets
     */
    public void genererCircuits(LinkedList<Integer> chemin, int n) {
        // Branch and bound
        if(meilleurChemin!=null && distance(chemin) > distance(meilleurChemin)) return;

        nb_operations++;

        if(chemin.size() == n) { // Tous les sommets sont visites
            if(arcExiste(chemin.peekFirst(), chemin.peekLast())) { // On a un cycle hamiltonien
                chemin.addLast(chemin.peekFirst());
                if(meilleurChemin == null) meilleurChemin = chemin;
                else if(distance(chemin) < distance(meilleurChemin)) {
                    meilleurChemin = (LinkedList<Integer>) chemin.clone();
                    //System.out.println(chemin + ":" + distance(chemin));
                }
            }
        }
        else { // Tous les sommets n'ont pas ete visistes
            for(int s=0; s<n; s++) {
                if(arcExiste(chemin.peekLast(), s) && !chemin.contains(s)) {
                    LinkedList<Integer> c = (LinkedList<Integer>) chemin.clone();
                    c.addLast(s);
                    genererCircuits(c, n);
                }
            }
        }
    }

    /**
     * Genere tous les circuits possibles
     * @param depart le sommet de depart
     * @param n le nombre de sommets
     */
    public void genererTousLesCircuits(int depart, int n) {
        LinkedList<Integer> chemin = new LinkedList<Integer>();
        chemin.addLast(depart);
        genererCircuits(chemin, n);
        System.out.println("Meilleur chemin : " + meilleurChemin + " : " + distance(meilleurChemin));
        System.out.println("Nombre de circuits partiels generes : " + this.nb_operations);
    }
}

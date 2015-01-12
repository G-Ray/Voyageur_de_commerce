package com.eidd;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.LinkedList;

public class Prog_dynamique {

    /**
     * Le graphe a parcourir, contient le poids de chaque arc
     */
    private int graph[][];
    private int nb_operations;
    //public static HashMap<Couple, Integer> cache = new HashMap<Couple, Integer>();
    Table<Integer, LinkedList<Integer>, Integer> cache = HashBasedTable.create();
    Table<Integer, LinkedList<Integer>, Integer> chemin = HashBasedTable.create();

    Prog_dynamique(int[][] graph) {
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

    public int coutSousCircuitMin(int v1, int vi, LinkedList<Integer> circuit) {
        int c = 99;

        if(cache.contains(vi, circuit)) {
            //System.out.println("CACHE");
            c = cache.get(vi, circuit);
            return c;
        }

        //System.out.println("Cout ss-circuit " + depart + " " + dernier);
        nb_operations++;

        if(circuit.isEmpty()) {
            //System.out.println("VIDE");
            c = poids(vi, v1);
        }
        else {
            for(int i : circuit) {
                if(arcExiste(vi, i)) {
                    LinkedList<Integer> l = (LinkedList<Integer>) circuit.clone();
                    l.removeFirstOccurrence(i);
                    int cout = poids(vi, i) + coutSousCircuitMin(v1, i, l);
                    c = Math.min(c, cout);
                    if(c < cout) chemin.put(vi, circuit, i);
                }
            }
        }
        cache.put(vi, circuit, c);
        return c;
    }

    public void coutCircuitMin(int depart, int n) {
        LinkedList<Integer> circuit = new LinkedList<Integer>();
        for(int i=0; i<n; i++) {
            circuit.addLast(i);
        }

        circuit.removeFirstOccurrence(depart);

        System.out.println(circuit);
        int cout = coutSousCircuitMin(depart, depart, circuit);
        System.out.println("meilleur cout " + cout);
        System.out.println("operations " + nb_operations);
    }
}

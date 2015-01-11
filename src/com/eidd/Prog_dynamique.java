package com.eidd;

import java.util.LinkedList;

public class Prog_dynamique {

    /**
     * Le graphe a parcourir, contient le poids de chaque arc
     */
    int graph[][];
    int nb_operations;

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
     * Calcule de la distance totale d'un chemin
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

    public int coutSousCircuitMin(int depart, int dernier, LinkedList<Integer> circuit) {
        int ret = 9999;
        System.out.println(circuit + " " + depart + dernier);
        if(!circuit.contains(depart) || !circuit.contains(dernier)) return ret;

        if(circuit.isEmpty()) {
            System.out.print("empty");
            ret = poids(depart, dernier);
        }
        else {
            ret = 9999;
            for(int i=0; i<circuit.size(); i++) {
                if(circuit.contains(i) && arcExiste(dernier, i)) {
                    circuit.removeLast();
                    int cout = poids(dernier, i) + coutSousCircuitMin(depart, i, circuit);
                    System.out.println(cout);
                    ret = Math.min(ret, cout);
                }
            }
        }
        //System.out.println(ret);
        return ret;
    }

    public void coutCircuitMin(int depart, int n) {
        LinkedList<Integer> circuit = new LinkedList<Integer>();
        for(int i=0; i<n; i++) {
            circuit.addLast(i);
        }
        //System.out.println(circuit);
        //circuit.removeLast();
        int cout = coutSousCircuitMin(depart, depart, circuit);
        System.out.println(cout);
    }
}

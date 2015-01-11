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
        nb_operations = 0;;
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

    public int coutSousCircuitMin(int depart, int dernier, LinkedList<Integer> circuit) {
        System.out.println(circuit + " depart " + depart + " arrivee " + dernier);

        int c = -1;
        if(circuit.isEmpty()) {
            c = poids(depart, dernier);
        }
        else {
            for(int i=0; i<circuit.size(); i++) {
                if(circuit.contains(i) && arcExiste(dernier, i)) {
                    LinkedList<Integer> tmp = (LinkedList<Integer>) circuit.clone();
                    tmp.removeFirstOccurrence(i);
                    int cout = poids(dernier, i) + coutSousCircuitMin(depart, i, tmp);
                    //System.out.println("noeud " + i + " cout: " + cout);
                    if (c!=-1 && depart != dernier){
                        c = Math.min(c, cout);
                    }else {
                        c = cout;
                    }
                    
                }
            }
        }

        return c;
    }

    public void coutCircuitMin(int depart, int n) {
        LinkedList<Integer> circuit = new LinkedList<Integer>();
        for(int i=0; i<n; i++) {
            circuit.addLast(i);
        }
        //System.out.println(circuit);
        //circuit.removeFirstOccurrence(depart);
        int cout = coutSousCircuitMin(depart, depart, circuit);
        System.out.println("meilleur cout " + cout);
    }
}

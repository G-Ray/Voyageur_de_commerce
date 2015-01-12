package com.eidd;

import java.util.LinkedList;

public class Prog_dynamique {

    /**
     * Le graphe a parcourir, contient le poids de chaque arc
     */
    int graph[][];
    int couts[][];
    int nb_operations;

    Prog_dynamique(int[][] graph) {
        this.graph = graph;
        couts = graph.clone();
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
        int c = 99;

        //System.out.println(circuit + " depart " + depart + " arrivee " + dernier);
        if(circuit.contains(depart) || circuit.contains(dernier)) return 99;
        //System.out.println("#####" + circuit.size());

        if(circuit.isEmpty()) {
            //System.out.println("VIDDEE");
            c = poids(dernier, depart);
        }
        else {
            for(int i=0; i<circuit.size(); i++) {
                int j = circuit.get(i);
                if(circuit.contains(j) && arcExiste(dernier, j)) {
                    LinkedList<Integer> tmp = (LinkedList<Integer>) circuit.clone();
                    tmp.removeFirstOccurrence(j);
                    int cout = poids(dernier, j) + coutSousCircuitMin(depart, j, tmp);
                    c = Math.min(c, cout);
                }
            }
        }
        
        //if(c == 0)  c = 9999;

        //couts[depart][dernier] = c;
        return c;
    }

    public void coutCircuitMin(int depart, int n) {
        LinkedList<Integer> circuit = new LinkedList<Integer>();
        for(int i=0; i<n; i++) {
            circuit.addLast(i);
        }
        
        circuit.removeLastOccurrence(depart);
        System.out.println(circuit);
        int cout = coutSousCircuitMin(depart, depart, circuit);
        System.out.println("meilleur cout " + cout);
        /*for(int i=0; i<couts.length; i++) {
            System.out.print("|");
            for(int j=0; j<couts.length; j++) {
                System.out.print(graph[i][j] + "|");
            }
            System.out.println("");
        }*/
    }
}

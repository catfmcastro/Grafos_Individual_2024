/*
 * Graph representation using adjacency list
 * Representação de um grafo utilizando lista de adjacência
 */

package Ex03;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

class Vertex {
    // Atributes
    private ArrayList<Integer> successors;
    private int td;
    private int tt;
    private int parent;
    private boolean visited;

    // Constructor
    public Vertex() {
        this.successors = new ArrayList<Integer>();
        this.td = -1;
        this.tt = -1;
        this.parent = -1;
        this.visited = false;
    }

    // Getters & Setters
    public ArrayList<Integer> getSuccessors() {
        return successors;
    }

    public int getTd() {
        return td;
    }

    public void setTd(int td) {
        this.td = td;
    }

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // Methods
    public void addSuccessor(int value) {
        successors.add(value);
    }
}

public class Graph {

    // Atributes --------------------------------------------------
    protected int n; // number of vertexes
    protected int m; // number of edges
    protected ArrayList<Vertex> adj; // adjacency list
    protected Scanner file;

    // Constructor ------------------------------------------------
    public Graph() {
    }

    public Graph(String fileName) {
        try {
            // open graph .txt file
            String dir = "./Ex02/Tests/" + fileName + ".txt";
            this.file = new Scanner(new File(dir));

            // vertexes number and edges number input
            this.n = file.nextInt();
            this.m = file.nextInt();

            // initiates adjacency list, begining with zero
            adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adj.add(new Vertex());
            }

            for (int i = 0; i < m; i++) {
                int v = file.nextInt();
                int w = file.nextInt();
                addEdge(v, w);
            }

        } catch (Exception e) {
            System.out.println();
            System.err.println(e);
            e.printStackTrace();
        }
    }

    // adds edge to graph
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj.get(v - 1).addSuccessor(w); // each vertex v is indexed by (v - 1)
    }

    // checks if vertex is valid
    private void validateVertex(int v) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException("Vertex " + v + " is invalid: it is not between 0 and " + n);
        }
    }

    // Getters & Setters -----------------------------------------------
    public int GetN() {
        return n;
    }

    public int GetM() {
        return m;
    }

    // Graph information ------------------------------------------------
    // Indegree
    public int getIndegree(int i) {
        int indegree = 0;
        for (int j = 0; j < adj.size(); j++) {
            if (adj.get(j).getSuccessors().contains(i + 1)) {
                indegree++;
            }
        }
        return indegree;
    }

    public void printIndegree(int i) {
        int indegree = getIndegree(i);
        System.out.println("Grau de entrada: " + indegree);
    }

    // Outdegree
    public int getOutdegree(int i) {
        return adj.get(i).getSuccessors().size();
    }

    public void printOutdegree(int i) {
        int outdegree = getOutdegree(i);
        System.out.println("Grau de saída: " + outdegree);
    }

    // Successors
    public void printSuccessors(int i) {
        validateVertex(i);
        Vertex tmp = adj.get(i);
        System.out.println("Conjunto de sucessores: " + tmp.getSuccessors().toString());
    }

    // Predecessors
    public ArrayList<Integer> getPredecessors(int i) {
        ArrayList<Integer> predecessors = new ArrayList<Integer>();
        for (int j = 0; j < adj.size(); j++) {
            if (adj.get(j).getSuccessors().contains(i + 1)) {
                predecessors.add(j + 1);
            }
        }
        return predecessors;
    }

    public void printPredecessors(int i) {
        validateVertex(i);
        ArrayList<Integer> predecessors = getPredecessors(i);
        System.out.println("Conjunto de Predecessores: " + predecessors.toString());
    }
}

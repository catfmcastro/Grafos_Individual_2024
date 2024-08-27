/*
 * Graph representation using adjacency list
 * Representação de um grafo utilizando lista de adjacência
 */

package Ex01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Graph {

    // Atributes --------------------------------------------------
    private int n; // number of vertexes
    private int m; // number of edges
    
    private int adj[]; // adjacency list
    private int indegree[]; // indegree array
    private int outdegree[]; // outdegree array
    
    private Scanner file;

    // Constructor ------------------------------------------------
    public Graph(String fileName) {
        try {
            // open graph .txt file
            String dir = "./Ex01/Tests/" + fileName + ".txt";
            this.file = new Scanner(new File(dir));

            // vertexes number and edges number input
            this.n = file.nextInt();
            this.m = file.nextInt();

            System.out.println("n = " + n + " and m = " + m);

            indegree = new int[n + 1];
            outdegree = new int[n + 1];
            adj = new int[n + 1];

            for(int i = 0; i < m; i++) {
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

    // Getters & Setters -----------------------------------------------
    public int GetN() {
        return n;
    }

    public int GetM() {
        return m;
    }

    // Methods ----------------------------------------------------------

    private void validateVertex (int v) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException("Vertex " + v + " is invalid: it is not between 0 and" + n);
        }
    }

    public void addEdge (int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v] = w;
        indegree[w]++;
        outdegree[v]++;
        System.out.println("testing: adj[" + v + "] = " + adj[v]);
    }

}

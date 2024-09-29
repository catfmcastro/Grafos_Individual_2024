package Ex02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DFS extends Graph {
    private int time;
    private ArrayList<String> treeEdges; // tree edges of root vertex

    // Constructor ---------------------------------------------------
    public DFS(String fileName) {
        super(fileName);
    }

    // Depth Search --------------------------------------------------
    public void depthFirstSearch(int rootV) {
        time = 0; // global time counter init
        treeEdges = new ArrayList<>();

        if (!adj.get(rootV).getVisited()) {
            DFSVisit(rootV);
            printTreeEdges();
            classifyEdges(rootV);
        }
    }

    public void DFSVisit(int v) {
        time = time++; // increment counter
        Vertex vertex = adj.get(v);
        vertex.setTd(time); // set discovery timestamp
        vertex.setVisited(true); // sets vertex as visited

        for (Integer k : vertex.getSuccessors()) {
            Vertex w = adj.get(k - 1); // successor vertex
            if (!w.getVisited()) {
                treeEdges.add((v + 1) + " " + k); // add tree edge
                w.setParent(v); // set parent
                DFSVisit(k - 1); // recursive call
            }
        }

        time = time++; // increment counter
        vertex.setTt(time); // set finish timestamp
    }

    // Edge Classification -------------------------------------------
    public void classifyEdges(int v) {
        Vertex vertex = adj.get(v);
        Collections.sort(vertex.getSuccessors());

        System.out.println();
        System.out.println();
        System.out.println("Classificação das arestas do vértice " + (v + 1) + ":");
        for (Integer k : vertex.getSuccessors()) {
            Vertex w = adj.get(k - 1); // successor vertex
            if (w.getTd() == -1) {
                System.out.println("A aresta (" + (v + 1) + ", " + k + ") é de árvore.");
            } else if (w.getTd() > vertex.getTd() && w.getTt() == -1) {
                System.out.println("A aresta (" + (v + 1) + ", " + k + ") é de avanço.");
            } else if (w.getTd() < vertex.getTd() && w.getTt() > vertex.getTt()) {
                System.out.println("A aresta (" + (v + 1) + ", " + k + ") é de retorno.");
            } else {
                System.out.println("A aresta (" + (v + 1) + ", " + k + ") é de cruzamento.");
            }
        }
    }

    public void printTreeEdges() {
        System.out.println("As arestas de árvore do grafo são:");
        System.out.print("[ ");
        for (String edge : treeEdges) {
            System.out.print("(" + edge + "), ");
        }
        System.out.print("] ");
    }
}

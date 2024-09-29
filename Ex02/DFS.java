package Ex02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            Vertex vertex = adj.get(current);

            if (!vertex.getVisited()) {
                time++; // increment counter
                vertex.setTd(time); // set discovery timestamp
                vertex.setVisited(true); // sets vertex as visited

                // Push all unvisited successors onto the stack
                for (Integer k : vertex.getSuccessors()) {
                    Vertex w = adj.get(k - 1); // successor vertex
                    if (!w.getVisited()) {
                        treeEdges.add((current + 1) + " " + k); // add tree edge
                        w.setParent(current); // set parent
                        stack.push(k - 1); // push successor onto the stack
                    }
                }

                time++; // increment counter
                vertex.setTt(time); // set finish timestamp
            }
        }
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

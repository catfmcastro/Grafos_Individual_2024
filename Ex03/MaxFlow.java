package Ex03;

// Java program for implementation of Ford Fulkerson
// algorithm
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;

public class MaxFlow {
    private int V; // Number of vertices in graph

    public MaxFlow(int V) {
        this.V = V;
    }

    // retorna true se existe um caminho de s para t no grafo residual rGraph.
    boolean bfs(int rGraph[][], int s, int t, int parent[] ) {
        // array de vértices visitados
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // cria uma fila, marca o vértice de origem e o adiciona à fila
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // bfs loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true; // caminho encontrado
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return false; // não há caminho
    }

    // retorna o maxFlow do grafo
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // cria grafo residual e preenche com capacidades do grafo original
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];

        int max_flow = 0; // inicializa flow

        // aumenta o fluxo enquanto houver um caminho de s para t
        while (bfs(rGraph, s, t, parent)) {
            // encontra a capacidade residual mínima das arestas ao longo do caminho
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update na capacidade residual das arestas e arestas reversas
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // adiciona o fluxo do caminho ao fluxo total
            max_flow += path_flow;
        }

        return max_flow;
    }
}

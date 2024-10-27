package Ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
    public int numVertices;
    public int numArestas;
    public int[][] graph;

    public Graph(String fileName) {
        String dir = "./Ex03/Tests/" + fileName + ".txt";
        lerArquivo(dir);
    }

    private void lerArquivo(String dir) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dir))) {
            String linha = reader.readLine();

            // Primeira linha: número de vértices e arestas
            String[] partes = linha.split(" ");
            numVertices = Integer.parseInt(partes[0]);
            numArestas = Integer.parseInt(partes[1]);

            // Inicializando a matriz de adjacência com 0
            graph = new int[numVertices][numVertices];

            // Lendo as arestas e preenchendo a matriz de adjacência
            while ((linha = reader.readLine()) != null) {
                partes = linha.split(" ");
                int origem = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);

                // Definindo o fluxo da aresta como 1
                graph[origem][destino] = 1;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Método para exibir o grafo como matriz de adjacência
    public void printGrafo() {
        System.out.println("Matriz de adjacência:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}

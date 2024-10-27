package Ex03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinearGraphGenerator {
    private int numVertices;
    private List<int[]> arestas;

    public LinearGraphGenerator(int numVertices) {
        this.numVertices = numVertices;
        this.arestas = new ArrayList<>();

        // Cria um grafo linear conectando cada vértice ao próximo
        gerarArestasLineares();
    }

    private void gerarArestasLineares() {
        for (int i = 0; i < numVertices - 1; i++) {
            arestas.add(new int[] { i, i + 1 });
        }
    }

    public void salvarEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(numVertices + " " + (numVertices - 1) + "\n"); // n e m
            for (int[] aresta : arestas) {
                writer.write(aresta[0] + " " + aresta[1] + "\n");
            }
            System.out.println("Grafo salvo em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o grafo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int numVertices = 10000; // Número de vértices fixo para o grafo linear

        LinearGraphGenerator grafo = new LinearGraphGenerator(numVertices);
        grafo.salvarEmArquivo("linearGraph10000.txt");
    }
}

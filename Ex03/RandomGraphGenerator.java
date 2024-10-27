package Ex03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGraphGenerator {
    private int numVertices;
    private int numArestas;
    private List<int[]> arestas;

    public RandomGraphGenerator(int numVertices, int minArestas, int maxArestas) {
        this.numVertices = numVertices;
        this.arestas = new ArrayList<>();
        this.numArestas = new Random().nextInt(maxArestas - minArestas + 1) + minArestas;

        // checks if graph is connected
        gerarArestasConexas();
        gerarArestasAleatorias();
    }

    private void gerarArestasConexas() {
        Random random = new Random();
        for (int i = 1; i < numVertices; i++) {
            int origem = random.nextInt(i);
            int[] aresta = { origem, i };
            arestas.add(aresta);
        }
    }

    private void gerarArestasAleatorias() {
        Random random = new Random();
        Set<String> arestasExistentes = new HashSet<>();

        // Adiciona as arestas conexas ao set para evitar duplicatas
        for (int[] aresta : arestas) {
            arestasExistentes.add(aresta[0] + "-" + aresta[1]);
        }

        while (arestas.size() < numArestas) {
            int origem = random.nextInt(numVertices);
            int destino = random.nextInt(numVertices);

            if (origem != destino && arestasExistentes.add(origem + "-" + destino)) {
                arestas.add(new int[] { origem, destino });
            }
        }
    }

    public void salvarEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(numVertices + " " + numArestas + "\n");
            for (int[] aresta : arestas) {
                writer.write(aresta[0] + " " + aresta[1] + "\n");
            }
            System.out.println("Grafo salvo em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o grafo: " + e.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     int numVertices = 10000; // Número fixo de vértices
    //     int minArestas = 15000;
    //     int maxArestas = 25000;

    //     RandomGraphGenerator grafo = new RandomGraphGenerator(numVertices, minArestas, maxArestas);
    //     grafo.salvarEmArquivo("randomGraph10000.txt");
    // }
}

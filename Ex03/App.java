/*
 * Implementação 03 - Fluxo Máximo
 * Implementation 03 - Max Flow
 *
 * @author: @catfmcastro
 * Catarina F. M. Castro
 *
 * Teoria dos Grafos e Computabilidade - PUC Minas
 */

package Ex03;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Atributos
        Scanner sc = new Scanner(System.in);
        Graph graph;
        int s; // origin vertex
        int t; // terminal vertex
        String fileName = ""; // file name for test graph

        // User Input --------------------------------
        System.out.print("Insira o nome do arquivo: ");
        fileName = sc.nextLine();

        System.out.print("Insira o vértice de origem: ");
        s = sc.nextInt();

        System.out.print("Insira o vértice de destino: ");
        t = sc.nextInt();

        // Metodos ------------------------------------
        graph = new Graph(fileName);

        System.out.println("------------------------------------");
        MaxFlow maxFlow = new MaxFlow(graph.numVertices);

        long startTime = System.nanoTime();
        int result = maxFlow.fordFulkerson(graph.graph, s, t);
        long endTime = System.nanoTime();

        System.out.println("O fluxo máximo é: " + result);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
        System.out.println("------------------------------------");

        sc.close();
    }
}

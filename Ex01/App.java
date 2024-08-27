/*
 * Implementação 01 - Representação de Grafos
 * Implementation 01 - Graph Representation
 *
 * @catfmcastro Catarina F. M. Castro
 *
 * Teoria dos Grafos e Computabilidade - PUC Minas
 */

package Ex01;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Atributes
        Scanner sc = new Scanner(System.in);
        Graph graph;
        int selected = 0; // selected vertex
        String fileName = ""; // file name for test graph

        // User Input --------------------------------
        System.out.print("Insira o nome do arquivo: ");
        fileName = sc.nextLine();
        
        System.out.print("Insira o número do vértice: ");
        selected = sc.nextInt();

        // Methods ------------------------------------
        graph = new Graph(fileName);
        // todo grau de saída
        // todo grau de entrada
        graph.printSuccessors(selected - 1);
        graph.printPredecessors(selected - 1);

        sc.close();
    }
}

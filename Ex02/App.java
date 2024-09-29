/*
 * Implementação 02 - Busca em Grafos
 * Implementation 02 - Graph Search
 *
 * @author: @catfmcastro
 * Catarina F. M. Castro
 *
 * Teoria dos Grafos e Computabilidade - PUC Minas
 */

package Ex02;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Atributes
        Scanner sc = new Scanner(System.in);
        DFS graph;
        int selected = 0; // selected vertex
        String fileName = ""; // file name for test graph

        // User Input --------------------------------
        System.out.print("Insira o nome do arquivo: ");
        fileName = sc.nextLine();

        System.out.print("Insira o número do vértice: ");
        selected = sc.nextInt();

        // Methods ------------------------------------
        graph = new DFS(fileName);

        System.out.println("------------------------------------");
        // graph.printOutdegree(selected - 1); // each vertex v is indexed by (v-1)
        // graph.printIndegree(selected - 1);
        // graph.printSuccessors(selected - 1);
        // graph.printPredecessors(selected - 1);
        graph.depthFirstSearch(selected - 1);
        System.out.println("------------------------------------");

        sc.close();
    }
}

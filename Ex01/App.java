/*
 * Implementação 01 - Representação de Grafos
 * Implementation 01 - Graph Representation
 *
 * @catfmcastro Catarina F. M. Castro
 *
 * Teoria dos Grafos e Computabilidade - PUC Minas
 */

package Ex01;

import java.util.Scanner;

public class App {

    public static boolean isOptionValid(int selected) {
        boolean awnser;
        if (selected > -1 && selected <= 1) {
            awnser = true;
        } else {
            System.out.println("Opção selecionada inválida! Tente novamente");
            awnser = false;
        }
        return awnser;
    }

    public static void main(String[] args) {
        // Atributes
        Scanner sc = new Scanner(System.in);
        Graph graph;
        int selected = 0; // selected vertex
        String fileName = ""; // file name for test graph

        // User Input --------------------------------
        System.out.println("Insira o nome do arquivo: ");
        fileName = sc.nextLine();

        System.out.println("Insira o número do vértice: ");
        selected = sc.nextInt();

        graph = new Graph(fileName);
        // todo grau de saída
        // todo grau de entrada
        // todo conjundo de sucessores
        // todo conjunto de predecessores

        sc.close();
    }
}

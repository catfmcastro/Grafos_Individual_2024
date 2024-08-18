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

  private Graph graph;
  private String fileName = ""; // file name for test graph

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
    int selected = 0;
    boolean exit = false;

    while (!exit) {
      System.out.println("\n\nOlá! Seja bem-vindo. -------------------\nO que deseja fazer?");
      System.out.println("[ 0 ]: Sair");
      System.out.println("[ 1 ]: Representação de Grafo");

      System.out.print("\nDigite a opção que deseja: ");
      selected = Integer.parseInt(sc.nextLine());

      switch (selected) {
        case 0:
          exit = true;
          System.out.println("Obrigada! Saindo...");
          sc.close();
          break;
        case 1:
          System.out.println("\nRepresentação de Grafo Selecionada.");
          break;
      }
    }
  }
}

/*
 * Graph representation using adjacency list
 * Representação de um grafo utilizando lista de adjacência
 */

package Ex01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Graph {

    // Atributes
    private int n;
    private int m;

    protected int origin[];
    protected int destiny[];

    protected int forwardStar[];
    protected int reverseStar[];

    private Scanner file;

    // Constructor
    public Graph(String fileName) {
        int pos = 1; // begin position with 1, so it aligns with vertexes number

        try {
            // open graph .txt file
            String dir = "./Ex01/Tests/" + fileName + ".txt";
            this.file = new Scanner(new File(dir));

            // vertexes number and edges number
            this.n = file.nextInt();
            this.m = file.nextInt();

            // origin and destiny vertexes of each edge
            this.origin = new int[m + 1]; 
            this.destiny = new int[m + 1];

            // forward and reverse star arrays
            this.forwardStar = new int[m + 2];
            this.reverseStar = new int[m + 2];

            while(file.hasNextInt()) {
                origin[pos] = file.nextInt();
                destiny[pos] = file.nextInt();
                pos++;
            }

            // indicates end of list
            this.forwardStar[n+1] = m + 1;
            this.reverseStar[n+1] = m + 1;
            
        } catch (Exception e) {
            System.out.println();
            System.err.println(e);
            e.printStackTrace();
        }
    }

    // Getters & Setters
    public int GetN() {
        return n;
    }

    public int GetM() {
        return m;
    }
}

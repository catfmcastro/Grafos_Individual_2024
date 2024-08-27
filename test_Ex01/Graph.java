/*
 * Graph representation using adjacency list
 * Representação de um grafo utilizando lista de adjacência
 */

package test_Ex01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

    // Atributes
    private int n; // number of vertexes
    private int m; // number of edges

    protected int origin[];
    protected int destiny[];

    protected int forwardPtr[];
    protected int reversePtr[];

    private Scanner file;

    // Constructor
    public Graph(String fileName) {
        int pos = 1; // begin position with 1, so it aligns with vertexes number

        try {
            // open graph .txt file
            String dir = "./test_Ex01/Tests/" + fileName + ".txt";
            this.file = new Scanner(new File(dir));

            // vertexes number and edges number
            this.n = file.nextInt();
            this.m = file.nextInt();

            // origin and destiny vertexes of each edge
            this.origin = new int[m + 1]; 
            this.destiny = new int[m + 1];

            // forward and reverse star arrays
            this.forwardPtr = new int[m + 2];
            this.reversePtr = new int[m + 2];

            while(file.hasNextInt()) {
                origin[pos] = file.nextInt();
                destiny[pos] = file.nextInt();
                pos++;
            }

            // indicates end of list
            this.forwardPtr[n+1] = m + 1;
            this.reversePtr[n+1] = m + 1;
            
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

    public int[] GetSuccessors (int vertex) {
        int initialPos = forwardPtr[vertex];
        int lastPos = forwardPtr[vertex + 1];

        int[] successors = new int[initialPos - lastPos];
        
        for (int i = 0; i < successors.length; i++) {
            successors[i] = destiny[initialPos + i];
        }

        return successors;
    }
}

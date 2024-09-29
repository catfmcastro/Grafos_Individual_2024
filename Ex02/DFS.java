package Ex02;

public class DFS extends Graph {
    int time;

    // Depth Search --------------------------------------------------
    public void depthSearcInit(int v) {
        time = 0; // global time counter init

        // setting initial values
        for (Vertex vertex : adj) {
            vertex.setTd(0);
            vertex.setTt(0);
            vertex.setParent(-1);
        }

        for (int i = 0; i < adj.size() - 1; i++) {
            if (adj.get(i).getTd() == 0) {
                depthSearch(i);
            }
        }
    }

    public void depthSearch(int v) {
        time = time++; // increment counter
        adj.get(v).setTd(time); // set discovery timestamp

        for (Integer w : adj.get(v).getSuccessors()) {
            if (adj.get(w - 1).getTd() == 0) {
                adj.get(w - 1).setParent(v);
                // !! visitar aresta de ÁRVORE
                depthSearch(w - 1);
            } else {
                if (adj.get(w - 1).getTt() == 0) {
                    // !! visitar aresta de RETORNO
                } else if (adj.get(v).getTd() < adj.get(w - 1).getTd()) {
                    // !! visitar aresta de AVANÇO
                } else {
                    // !! visitar aresta de CRUZAMENTO
                }
            }
        }

        time = time++; // increment counter
        adj.get(v).setTt(time); // set finish timestamp
    }
}

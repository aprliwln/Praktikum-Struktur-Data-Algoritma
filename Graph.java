import java.util.*;

public class Graph {
    private int vertex;
    private ArrayList<ArrayList<Edge>> adj; //menyimpan semua edge dari tiap node

    //konstruktor utk inisialisasi graf dgn jmlh simpul tertentu
    public Graph (int vertex) {
        this.vertex = vertex;
        adj = new ArrayList<>();

        //tiap node memiliki list edge kosong saat awal
        for (int i = 0; i < vertex; i++) {
            adj.add(new ArrayList<>());
        }
    }

    //menambahkan edge antara dua node (undirected
    public void addEdge (int from, int to, int weight) {
        //tambah edge dari from -> to
        adj.get(from).add(new Edge(from, to, weight));
        //tambah edge dari to -> from
        adj.get(to).add(new Edge(to, from, weight));
    }

    //mengembalikan jumlah vertex dalam graf
    public int getVertex() {
        return vertex;
    }

    //mengembalikan adjacency list dri graf
    public ArrayList<ArrayList<Edge>> getAdj() {
        return adj;
    }

    //memperluas kapasitas graf agar bisa menampung node baru
    public void expand (int newSize) {
        //jika ukuran baru lebih kecil/sm, tidak perlu perluas
        if (newSize <= vertex) {
            return;
        }
        //membuat array adjacency baru
        for (int i = vertex; i < newSize; i++) {
            adj.add(new ArrayList<>());
        }
        //mengganti referensi ke array yg baru
        vertex = newSize;
    }
}
public class DisjointSet {
    int[] parent;

    //konstruktor untuk menginisialisasi dgn ukuran tertentu
    public DisjointSet (int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    //mencari root dari node u dgn path compression
    public int find (int u) {
        //jika parent dari u bukan dirinya sendiri, cari rootnya scr rekursif
        if (parent[u] != u) {
            //langsung hubungkan u ke rootnya
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    //menggabungkan dua himpunan
    public void union (int u, int v) {
        //temukan root dari masing-masing elemen
        int rootU = find(u);
        int rootV = find(v);
        //jika berbeda root, sambungkan v ke u
        if (rootU != rootV) {
            parent[rootV] = rootU;
        }
    }

    //mengecek apakah u dan v berada dalam satu himpunan
    public boolean connected (int u, int v) {
        //jika rootnya sm, artinya terhubung
        return find(u) == find(v);
    }
}

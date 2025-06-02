//untuk menyimpan film dalam struktur binary tree
public class BinaryTree {
    //untuk menyimpan satu film
    class Node {
        FilmItem data;
        Node left, right;

        Node (FilmItem data) {
            this.data = data;
        }
    }
    Node root;

    //menambahkan film ke binary tree
    public void insert (FilmItem item) {
        root = insertRecursive (root, item);
    }

    //fungsi rekursif untuk memasukkan data ke posisi yang benar
    public Node insertRecursive (Node node, FilmItem item) {
        if (node == null) {
            return new Node (item); //jika node kosong
        }
        if (item.title.compareToIgnoreCase (node.data.title) < 0) {
            node.left = insertRecursive (node.left, item); //jika lebih kecil, ke kiri
        }
        else {
            node.right = insertRecursive(node.right, item); //jika lebih besar, ke kanan
        }
        return node;
    }

    //mencari film berdasarkan judul
    public boolean search (String title) {
        return searchRecursive(root, title);
    }

    //fungsi rekursif untuk pencarian
    public boolean searchRecursive (Node node, String title) {
        if (node == null) {
            return false; //jika node kosong, film tdk ditemukan
        }
        if (node.data.title.equalsIgnoreCase (title)) {
            return true; //film ditemukan
        }
        if (title.compareToIgnoreCase (node.data.title) < 0) {
            return searchRecursive (node.left, title); //pencarian di subtree kiri
        }
        else {
            return searchRecursive (node.right, title); //pencarian di subtree kanan
        }
    }
}

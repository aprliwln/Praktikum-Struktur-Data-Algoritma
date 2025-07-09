/*
    Aprillia Wulandari
    L0124040
 */

import java.util.*;

// kelas node untuk kelas BST-nya
class Node {
    int num;
    Node left, right;

    public Node (int num) {
        this.num = num;
        this.left = this.right = null;
    }
}

class BST {
    Node root;

    // methode untuk menyisipkan angka tebakan ke BST
    private Node insertRecursive (Node node, int num) {
        if (node == null) {
            return new Node(num); // jika kosong, buat node baru
        }
        //jika angka tebakan lebih kecil, masuk ke node sebelah kiri
        if (num < node.num) {
            node.left = insertRecursive(node.left, num);
        }
        //jika angka tebakan lebih besar, masuk ke node sebelah kanan
        else if (num > node.num) {
            node.right = insertRecursive(node.right, num);
        }
        return node;
    }

    // methode untuk menyisipkan tebakan user
    public void insert (int num) {
        root = insertRecursive(root, num);
    }

    // methode untuk pencarian angka
    private boolean searchRecursive (Node node, int num) {
        // jika kosong, angka tidak ditemukan
        if (node == null){ 
            return false;
        }
        // jika angka sm dgn node saat ini, angka ditemukan
        if (num == node.num) {
            return true;
        }
        // jika angka lebih kecil, maka dilakukan pencarian ke kiri
        else if (num < node.num) {
            return searchRecursive(node.left, num);
        }
        // jika angka lebih besar, maka dilakukan pencarian ke kanan
        else {
            return searchRecursive(node.right, num);
        }
    }

    // methode untuk mencari angka yang diinput user
    public boolean search (int num) {
        return searchRecursive(root, num);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BST bst = new BST();
        Scanner sc = new Scanner(System.in);
        int start = 1; // dimulai dari 1
        int end = 100; // diakhiri dengan 100

        Random random = new Random();
        int comp = random.nextInt(start, end); // tebak angka acak dari 1 - 100
        int count = 0; // jumlah tebakan
        System.out.println("====================================");
        System.out.println("           GUESS THE NUMBER         ");
        System.out.println("====================================");
        System.out.println("\u001B[34mGuess the number from " + start + " to " + end + "\u001B[0m");
        System.out.println("------------------------------------");

        // pengulangan selama tebakannya salah
        while (true) {
            System.out.print("Guess: ");
            int guess;
            
            // validasi inputnya harus angka
            try {
                guess = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001BInput Number!\u001B[0m");
                continue;
            }

            // validasi user harus input dari 1 sampai 100
            if (guess < 1 || guess > 100) {
                System.out.println("\u001B[31mGuessing numbers must be 1 to 100\u001B[0m");
                continue;
            }

            // mengecek apakah angkanya sudah pernah ditebak
            if (bst.search(guess)) {
                System.out.println("Duplicate guess!");
                continue;
            }

            //tebakan user dimasukkan ke dalam BST
            bst.insert(guess);
            count++;

            // jika tebakan terlalu kecil
            if (guess < comp) {
                System.out.println("\u001B[31mToo low!\u001B[0m");
            }
            // jika tebakan terlalu besar
            else if (guess > comp) {
                System.out.println("\u001B[31mToo high!\u001B[0m");
            }
            //jika tebakan benar
            else {
                System.out.println("\u001B[32mYeay correct!\u001B[0m");
                System.out.println("\u001B[33mThe number is: " + comp + "\u001B[0m");
                System.out.println("Total guesses: " + count);
                break;
            }
        }
        sc.close();
    }
}

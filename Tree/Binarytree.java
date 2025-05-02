import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node (int value) {
        data = value;
        left = right = null;
    }
}

public class Binarytree {
    Node root;

    public Node insert (Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.data) {
            node.left = insert(node.left, value);
        }
        else if (value > node.data) {
            node.right = insert(node.right, value);
        }
        else {
            System.out.println("Cant duplicat");
        }
        return node;
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public Node delete (Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.data) {
            node.left = delete (node.left, key);
        }
        else if (key > node.data) {
            node.right = delete (node.right, key);
        }
        else {
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }

            node.data = minValue (node.right);
            node.right = delete (node.right, node.data);
        }
        return node;
    }

    public int minValue (Node node) {
        int min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    public boolean search (Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key == node.data) {
            return true;
        }
        if (key < node.data) {
            return search(node.left, key);
        }
        else {
            return search(node.right, key);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Binarytree tree = new Binarytree();

        int option;
        do {
            System.out.println("1. Tambah data");
            System.out.println("2. Tampilkan (in-order)");
            System.out.println("3. Cari data");
            System.out.println("4. Hapus data");
            System.out.println("5. Keluar");
            System.out.print("Input option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Input nilai: ");
                    int val = sc.nextInt();
                    tree.root = tree.insert(tree.root, val);
                    break;
                case 2:
                    tree.inOrder(tree.root);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Input nilai: ");
                    int find = sc.nextInt();
                    boolean found = tree.search(tree.root, find);
                    if (found) {
                        System.out.println(find + " Found!");
                    }
                    else {
                        System.out.println(find + " Not found");
                    }
                    break;
                case 4:
                    System.out.print("Input nilai: ");
                    int del = sc.nextInt();
                    tree.root = tree.delete(tree.root, del);
                    System.out.println(del + " delete");
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
            }   
        } 
        while (option != 5);
        sc.close();
    }
}

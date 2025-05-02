import java.util.*;

class mySet {
    int[] data;
    int size;

    public mySet(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return true;  
            }
        }
        return false;
    }

    public void add(int value) {
        if (size == data.length) {
            System.out.println("Set full!");
        }
        else {
            if (!contains(value)) {
                data[size] = value;
                size++;
            }
        }
    }

    public void remove (int value) {
        if (size == 0) {
            System.out.println("set empty!");
        }
        else {
            for (int i = 0; i < size; i++){
                if (data[i] == value) {
                    for (int j = i; j < size - 1; j++) {
                        data[j] = data[j+1];
                    }
                    size--;
                    break;
                }
            }
        }
    }

    public void display() {
        if (size == 0) {
            System.out.println("Set empty!");
        }
        else {
            for (int i = 0; i < size; i++) {
                System.out.println(data[i] + " ");
            }
        }
    }
}

public class Set {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input size: ");
        int size = sc.nextInt();

        mySet set = new mySet(size);

        int option;
        do {
            System.out.println("\n1. Tambah data");
            System.out.println("2. Hapus data");
            System.out.println("3. Cek data");
            System.out.println("4. Tampilkan isi");
            System.out.println("5. Keluar");
            System.out.print("Input option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Input nilai: ");
                    int val = sc.nextInt();
                    if (set.contains(val)) {
                        System.out.println("Data sudah ada.");
                    }
                    else {
                        set.add(val);
                    }
                    break;
                case 2:
                    System.out.print("Input nilai: ");
                    int del = sc.nextInt();
                    if (set.contains(del)) {
                        set.remove(del);
                    }
                    else {
                        System.out.println("Data tidak ditemukan.");
                    }
                    break;
                case 3:
                    System.out.print("Input nilai: ");
                    int search = sc.nextInt();
                    if (set.contains(search)) {
                        System.out.println("Data ditemukan.");
                    }
                    else {
                        System.out.println("Data tidak ada.");
                    }
                    break;
                case 4:
                    set.display();
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid option");
            }

        } 
        while (option != 5);
        sc.close();
    }
}

import java.util.*;

class stud{
    String[] keys;
    String[] values;
    int size;

    public stud (int capacity) {
        keys = new String[capacity];
        values = new String[capacity];
        size = 0;
    }

    public void put(String key, String value) {
        if (size == keys.length) {
            System.out.println("Map full");
            return;
        }
        
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                System.out.println("Nim dah ada lah");
                return;
            }
        }
        keys[size] = key;
        values[size] = value;   
        size++;
    }

    public void remove(String key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key))  {
                for (int j = i; j < size -1; j++){
                    keys[j] = keys[j+1];
                    values[j] = values[j+1];
                }
                size--;
                return;
            }
        }
        System.out.println("Nim cant found");
    }

    public void get(String key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                System.out.println("Nama: " + values[i]);
                return;
            }
        }
        System.out.println("Nim cant found");
    }

    public void display() {
        if (size == 0) {
            System.out.println("Map empty");
        }
        else {
            int no = 1;
            for(int i = 0; i < size; i++) {
                System.out.println(no + "  " + values[i] + " (" + keys[i] + ")");
                no++;
            }

        }
    }
}


public class Map {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kapasitas map: ");
        int capacity = sc.nextInt();

        stud map = new stud(capacity);
        int option;
        do{
            System.out.println("\n1. Tambah data");
            System.out.println("2. Hapus data");
            System.out.println("3. Cari nama berdasarkan NIM");
            System.out.println("4. Tampilkan semua");
            System.out.println("5. Keluar");
            System.out.print("Input option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Input NIM: ");
                    String nim = sc.nextLine();
                    System.out.print("Input Nama: ");
                    String nama = sc.nextLine();
                    map.put(nim, nama);
                    break;
                case 2:
                    System.out.println("Input NIM: ");
                    String del = sc.nextLine();
                    map.remove(del);
                    break;
                case 3:
                    System.out.println("Input NIM: ");
                    String search = sc.nextLine();
                    map.get(search);
                    break;
                case 4:
                    map.display();
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("invalid option");
            }
        }
        while (option != 5);
        sc.close();
    }
}

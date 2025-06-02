import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Circle circle = new Circle();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Mahasiswa + Teman");
            System.out.println("2. Tambah Pertemanan");
            System.out.println("3. Hapus Mahasiswa");
            System.out.println("4. Cari Teman Bersama");
            System.out.println("5. Tampilkan Teman");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.println("Jumlah: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    List<String> listFr = new ArrayList<>();
                    for (int i = 0; i < num; i++) {
                        System.out.println((i+1) + ". ");
                        listFr.add(sc.nextLine());
                    }
                    circle.addMhs(name, listFr);
                    break;
                case 2:
                    System.out.print("First student: ");
                    String m1 = sc.nextLine();
                    System.out.print("Second student: ");
                    String m2 = sc.nextLine();
                    circle.addCircle(m1, m2);
                    break;
                case 3:
                    System.out.print("Name: ");
                    String nm = sc.nextLine();
                    circle.removeMhs(nm);
                    break;
                case 4:
                    System.out.print("First student: ");
                    String nm1 = sc.nextLine();
                    System.out.print("Second student: ");
                    String nm2 = sc.nextLine();
                    circle.search(nm1, nm2);
                    break;
                case 5:
                    System.out.print("Name: ");
                    String nme = sc.nextLine();
                    circle.display(nme);
                    break;
                case 6:
                    System.out.println("Thanks");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}


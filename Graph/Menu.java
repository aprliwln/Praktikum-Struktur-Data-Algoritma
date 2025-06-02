import java.util.*;

//untuk menjalankan program menu sesuai input user
public class Menu {
    Circle circle; //objek circle utk menyimpan graph
    Scanner sc; //untuk input

    //konstruktor
    public Menu (Circle circle, Scanner sc) {
        this.circle = circle;
        this.sc = sc;
    }

    //methode untuk menambahkan mahasiswa dan teman-temannya
    public void AddStudent() {
        System.out.println("------------------------------------");
        System.out.print("Name\t\t : ");
        String name = sc.nextLine();
        System.out.print("Number of friends: ");
        int num = sc.nextInt();
        sc.nextLine();

        //list untuk menyimpan nama teman
        List<String> listFriends = new ArrayList<>();

        System.out.println("====================================");
        System.out.println("          Her/His Friends           ");
        System.out.println("====================================");
        
        //loop ini untuk menambahkan daftar nama teman
        for (int i = 1; i <= num; i++) {
            System.out.print("[" + i + "] ");
            String friends = sc.nextLine().trim();
            
            //jika tidak kosong, maka akan ditambahkan
            if (!friends.isEmpty()) {
                listFriends.add(friends);
            }
        }
        System.out.println("------------------------------------");
        //memanggil fungsi addStudent
        circle.addStudent (name, listFriends);
    }

    //methode untuk menambahkan pertemanan antara dua mahasiswa
    public void addFriendship() {
        System.out.println("------------------------------------");
        System.out.print("First student\t: ");
        String std1 = sc.nextLine();
        System.out.print("Second student\t: ");
        String std2 = sc.nextLine();

        //jika berhasil ditambahkan
        if (circle.addFriendship(std1, std2)) {
            System.out.println("\033[0;32mSuccessfully added\033[0m");
        }
        //jika salah satu nama mahasiswa tidak ada
        else {
            System.out.println("\033[0;33mOne/both of the students are not found\033[0m");
        }
    }

    //methode untuk menghapus mahasiswa
    public void remove() {
        System.out.println("------------------------------------");
        System.out.print("Name: ");
        String name = sc.nextLine();

        //jika namanya ditemukan, maka berhasil dihapus
        if (circle.remove(name)) {
           System.out.println("\033[0;32mSuccessfully removed\033[0m"); 
        }
        //jika namanya tidak ditemukan
        else {
            System.out.println("\033[0;31mNot found\033[0m");
        }
    }

    //methode untuk mencari teman bersama antara dua mahasiswa
    public void findMutual() {
        System.out.println("------------------------------------");
        System.out.print("First student\t: ");
        String std1 = sc.nextLine();
        System.out.print("Second student\t: ");
        String std2 = sc.nextLine();
        
        //untuk mendapatkan teman bersama
        Set<String> mutual = circle.mutualFriends(std1, std2);

        //jika salah satu namanya tidak ditemukan
        if (mutual == null) {
            System.out.println("\033[0;33mOne of the students is not found\033[0m");
            return;
        }
        
        //jika tidak punya teman bersama
        if(mutual.isEmpty()) {
            System.out.println("\033[0;31mNo mutual friends\033[0m");
        }
        //jika punya teman bersama
        else {
            System.out.println("====================================");
            System.out.println("           Mutual Friends           ");
            System.out.println("====================================");
            for (String nm: mutual) {
                System.out.println("\033[0;35m-> " + nm + "\033[0m");
            }
            System.out.println("------------------------------------");
        }
    }

    //methode untuk menampilkan daftar teman dari seorang mahasiswa
    public void display() {
        System.out.println("------------------------------------");
        System.out.print("Name: ");
        String name = sc.nextLine();

        //untuk mengambil daftar temannya
        Set<String> friends = circle.getFriends(name);

        //jika tidak terdapat daftar temannya
        if (friends.isEmpty()) {
            System.out.println("\033[0;31mNot found\033[0m");
        }

        //jika terdapat daftar temannya
        else {
            System.out.println("====================================");
            System.out.println("          Friends of " + name          );
            System.out.println("====================================");

            //loop ini untuk menampilkan daftar temannya
            for (String f: friends) {
                System.out.println("\033[0;36m-> " + f + "\033[0m");
            }
            System.out.println("------------------------------------");
        }
    }
}

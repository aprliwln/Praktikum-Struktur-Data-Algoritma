import java.util.Scanner;

//untuk program utama
public class Menu {
    public static void user (Scanner sc, FilmLibrary lib) {
    String currentUser = null; //menyimpan user yg sedang login

        //menampilkan menu
        while (true) {
            System.out.println("====================================");
            System.out.println("       WELCOME TO FILM LIBRARY      ");
            System.out.println("====================================");
            System.out.println("            [1] Register            ");
            System.out.println("            [2] Login               ");
            System.out.println("            [3] Exit                ");
            System.out.println("------------------------------------");
            System.out.print("Input Option: ");
            int choose = sc.nextInt();
            sc.nextLine();

            switch (choose) {
                case 1:
                    System.out.print("Enter new username: ");
                    String name = sc.nextLine();
                    lib.register (name);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String nm = sc.nextLine();
                    //cek apakah user sudah registrasi
                    if (lib.isRegistered (nm)) {
                        currentUser = nm;
                        System.out.println("Logged in as: " + currentUser);
                        ShowMenu (sc, lib, nm);
                    }
                    else {
                        System.out.println("User not found, please register first");
                    }
                    break;
                case 3:
                    System.out.println("THANK YOU!");
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    //menampilkan fitur setelah login
    private static void ShowMenu (Scanner sc, FilmLibrary lib, String userName) {
        int option;
        //fitur setelah melakukan login
        do {
            System.out.println("====================================");
            System.out.println("                MENU                ");
            System.out.println("====================================");
            System.out.println("            [1] Show Film           ");
            System.out.println("            [2] Sorted Film         ");
            System.out.println("            [3] Search Film         ");
            System.out.println("            [4] Borrow Film         ");
            System.out.println("            [5] Return Film         ");
            System.out.println("            [6] Show Borrowed       ");
            System.out.println("            [7] Waiting List        ");
            System.out.println("            [8] History             ");
            System.out.println("            [9] Logout              ");
            System.out.println("------------------------------------");
            System.out.print("Input Option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    lib.display();
                    break;
                case 2:
                    lib.showSort();
                    break;
                case 3:
                    System.out.print("Enter film title: ");
                    String srch = sc.nextLine();
                    lib.search (srch);
                    break;
                case 4:
                    System.out.print("Enter film title: ");
                    String borrow = sc.nextLine();
                    lib.borrowFilm (userName, borrow);
                    break;
                case 5:
                    System.out.print("Enter film title: ");
                    String title = sc.nextLine();
                    lib.returnFilm (userName, title);
                    break;
                case 6:
                    lib.showBorrowed();
                    break;
                case 7:
                    lib.showWaitingList();
                    break;
                case 8:
                    lib.showHistory();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 0);
        sc.close();
    }
}

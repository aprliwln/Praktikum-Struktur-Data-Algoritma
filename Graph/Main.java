import java.util.*;

//untuk menjalankan program
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); //untuk input
        Circle circle = new Circle(); //untuk graphnya
        Menu menu = new Menu(circle, sc); //untuk menu dengan graph dan scanner

        while (true) {
        System.out.println("====================================");
        System.out.println("                MENU                ");
        System.out.println("====================================");
        System.out.println("        [1] Add Student & Friends   ");
        System.out.println("        [2] Add Circle              ");
        System.out.println("        [3] Remove Student          ");
        System.out.println("        [4] Find Mutual Friends     ");
        System.out.println("        [5] Show List Friends       ");
        System.out.println("        [6] Exit                    ");
        System.out.println("------------------------------------");
        System.out.print("Input option: ");
        int choice = sc.nextInt();
        sc.nextLine();

            switch (choice) {
                case 1:
                    menu.AddStudent();
                    break;
                case 2:
                    menu.addFriendship();
                    break;
                case 3:
                    menu.remove();
                    break;
                case 4:
                    menu.findMutual();
                    break;
                case 5:
                    menu.display();
                    break;
                case 6:
                    System.out.println("\033[0;34mTHANK YOU!^_^\033[0m");
                    return;
                default:
                    System.out.println("\033[0;31mInvalid option\033[0m");
                    break;
            }
        }
    }
}

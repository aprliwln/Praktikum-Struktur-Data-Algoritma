import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //untuk input user
        //membuat objek theme park yg menangani data wahana & jalur
        ThemePark park = new ThemePark();
        park.loadRidesFromFile("Rides.txt"); //memuat data wahana dari file
        park.loadEdgesFromFile("Edges.txt"); //memuat data jalur dari file

        //menu utama
        while (true) {
            System.out.println("====================================");
            System.out.println("           FUNLAND SIMULATOR        ");
            System.out.println("====================================");
            System.out.println("            [1]  Admin              ");
            System.out.println("            [2]  Visitor            ");
            System.err.println("            [3]  Exit               ");
            System.out.println("------------------------------------");
            System.out.print("Input option: ");
            int option = sc.nextInt(); sc.nextLine();

            switch (option) {
                case 1:
                    park.menuAdmin(sc);
                    break;
                case 2:
                    park.menuUser(sc);
                    break;
                case 3:
                    System.out.println("Thanks For Playing! Come Back Anytime^^");
                    return;
                default:
                    System.out.println("Invalid option. Please try again");
                    break;
            }
        }
    }
}

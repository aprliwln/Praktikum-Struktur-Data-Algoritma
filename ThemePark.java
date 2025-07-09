import java.io.*;
import java.util.*;

public class ThemePark {
    ArrayList<rides> ridesList = new ArrayList<>(); //menyimpam semua wahana
    Graph graph; //representasi graph utk menyimpan koneksi antarwahana
    int currentPosition = 0; //menyimpan posisi pengunjung saat ini, 0 = gerbang masuk

    //membaca data wahana dari file
    public void loadRidesFromFile (String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\s*\\|\\s*");
                int id = Integer.parseInt(p[0].trim());
                String name = p[1].trim();
                int guest = Integer.parseInt(p[2].trim());
                int price = Integer.parseInt(p[3].trim());
                double rating = Double.parseDouble(p[4].trim());
                ridesList.add(new rides(id, name, guest, price, rating));
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //membaca data koneksi dari file
    public void loadEdgesFromFile (String filename) {
        //menentukan ukuran graf
        int size = ridesList.size() + 1; //untuk gerbang masuk
        graph = new Graph(size);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\s*\\|\\s*");
                int from = Integer.parseInt(p[0].trim());
                int to = Integer.parseInt(p[1].trim());
                int weight = Integer.parseInt(p[2].trim());
                graph.addEdge(from, to, weight);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //menyimpan data wahana ke file
    public void writeRidesToFile (String filename) {
        try (PrintWriter write = new PrintWriter(new FileWriter(filename))){
            for (rides ride: ridesList) {
                write.printf("%03d | %s | %d | %d | %.1f%n", ride.id, ride.name, 
                ride.guest, ride.price, ride.rate);
            }
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    //menyimpan data koneksi ke file
    public void writeEdgesToFile (String filename) {
        try (PrintWriter write = new PrintWriter(new FileWriter(filename))) {
            Set<String> written = new HashSet<>(); //utk menghindari duplikat
            for (int i = 0; i < graph.getVertex(); i++) {
                for (Edge edge: graph.getAdj().get(i)) {
                    String key = i + "|" + edge.to + "|" + edge.weight;
                    if (!written.contains(key)) {
                        write.printf("%d | %d | %d%n", i, edge.to, edge.weight);
                        written.add(key);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    //menampilkan semua daftar wahana
    public void displayRides() {
        System.out.println("==================== RIDES LIST =====================");
        System.out.println("-----------------------------------------------------");
        System.out.printf("| %-3s | %-16s | %5s | %7s | %6s |\n", "ID", "Nama", "Guest", "Harga", "Rating");
        System.out.println("-----------------------------------------------------");
        for (rides ride: ridesList) {
            System.out.printf("| %03d | %-16s | %5d | %7d | %6.1f |\n", ride.id, ride.name, ride.guest, ride.price, ride.rate);
        }
        System.out.println("-----------------------------------------------------");
    }

    //mencari nama wahanan menggunakan binary search
    public void searchByName (String name) {
        rides result = BinarySearch.searchByName(ridesList.toArray(new rides[0]), name);
        //jika hasilnya namanya ditemukan
        if (result != null) {
            System.out.println("------------------------------------");
            System.out.println("           Ride Information         ");
            System.out.println("------------------------------------");
            System.out.println("Ride   : " + result.name);
            System.out.printf("ID     : %03d\n", result.id);
            System.out.println("Guest  : " + result.guest);
            System.out.println("Price  : Rp" + result.price);
            System.out.println("Rating : " + result.rate);
        }
        else {
            System.out.println("Not found");
        }
    }

    //mengembalikan id wahana dari nama
    public int getRidesIDByName (String name) {
        for (rides ride: ridesList) {
            //jika namanya sama akan mengembalikan id
            if (ride.name.equalsIgnoreCase(name)) {
                return ride.id;
            }
        }
        return -1;
    }

    //mengembalikan nama wahana berdasarkan id
    public String getRidesNamebyId (int id) {
        //jika id nya 0, artinya di gerbang masuk
        if (id == 0) {
            return "Entrance";
        }
        for (rides ride: ridesList) {
            //jika id nya sama akan mengembalikan nama
            if (ride.id == id) {
                return ride.name;
            }
        }
        return "Not found";
    }

    //sorting dengan merge sort berdasarkan pilihan user
    public void sortRides (int num) {
        //jika input user bukan dari 1 - 4
        if (num < 1 || num > 4) {
            System.out.println("Invalid option");
            return;
        }
        //jika input user benar
        rides[] arr = ridesList.toArray(new rides[0]);
        MergeSort.sort(arr, num); //urutkan dengan merge sort
        ridesList = new ArrayList<>(Arrays.asList(arr));
        displayRides();
    }

    //menemukan jalur terpendek dari posisi skrng ke wahana tujuan dgn djikstra shortest
    public boolean findShortestRute (int from, int to) {
       return ShortestPath.dijkstra(graph, from, to, ridesList.toArray(new rides[0]));
    }

    //menambahkan wahana baru ke list
    public void addRide (Scanner sc) {
        System.out.println("------------------------------------");
        System.out.println("             New Ride               ");
        System.out.println("------------------------------------");
        System.out.printf("Ride Name\t: ");
        String name = sc.nextLine();
        System.out.printf("Guest\t\t: ");
        int guest = sc.nextInt(); sc.nextLine();
        System.out.printf("Price\t\t: ");
        int price = sc.nextInt(); sc.nextLine();
        System.out.printf("Rate\t\t: ");
        double rating = sc.nextDouble(); sc.nextLine();

        //menentukan id baru berdasarkan id terakhir di list wahana
        int newID;
        if (ridesList.isEmpty()) {
            newID = 1;
        }
        else {
            newID = ridesList.get(ridesList.size() - 1).id + 1;
        }
        rides newRide = new rides(newID, name, guest, price, rating);
        ridesList.add(newRide);

        //menambah kapasitas graph jika perlu
        graph.expand(newID + 1);
        writeRidesToFile("Rides.txt");
    }

    //menambah koneksi antar ride (undirected)
    public void addEdge (Scanner sc) {
        System.out.println("------------------------------------");
        System.out.println("           New Connection           ");
        System.out.println("------------------------------------");
        System.out.printf("From ID\t\t: ");
        int from = sc.nextInt(); sc.nextLine();
        System.out.printf("To ID\t\t: ");
        int to = sc.nextInt(); sc.nextLine();
        System.out.printf("Distance\t: ");
        int weight = sc.nextInt(); sc.nextLine();

        //jika id yg diinput tidak sesuai atau tidak berada dalam list
        if (from < 0 || from >= graph.getVertex() || to < 0 || to >= graph.getVertex()) {
            System.out.println("Invalid ID");
            return;
        }

        //menambah edge ke graf dua arah
        graph.addEdge(from, to, weight);
        graph.addEdge(to, from, weight);
        writeEdgesToFile("Edges.txt");
    }

    //mengecek apakah dua wahana terhubung dalam satu komponen dgn disjoint set
    public boolean isConnected (String ride1, String ride2) {
        int id1;
        //jika nama wahana pertama adalah gerbang masuk, maka gunakan id 0
        if (ride1.equalsIgnoreCase("Entrance")) {
            id1 = 0;
        }
        //jika bukan gerbang masuk, cari id dari namanya
        else {
            id1 = getRidesIDByName(ride1);
        }

        int id2;
        //jika nama wahana kedua adalah gerbang masuk, maka gunakan id 0
        if (ride2.equalsIgnoreCase("Entrance")) {
            id2 = 0;
        }
        else {
            //jika bukan gerbang masuk, cari id dari namanya
            id2 = getRidesIDByName(ride2);
        }

        //jika salah satu id tidak ditemukan
        if (id1 == -1 || id2 == -1) {
            return false;
        }

        //inisialisasi disjoinset, ditambah 1 krn ada entrance (gerbang masuk)
        DisjointSet set = new DisjointSet(ridesList.size() + 1);
        //melakukan union pada semua edge yg ada di graph
        for (List<Edge> edges: graph.getAdj()) {
            for (Edge edge: edges) {
                set.union(edge.from, edge.to);
            }
        }
        //mengecek apakah dalam satu komponen
        return set.connected(id1, id2);
    }

    //untuk menu user (visitor)
    public void menuUser(Scanner sc) {
        while (true) {
            System.out.println("====================================");
            System.out.println("              VISITORS              ");
            System.out.println("====================================");
            System.out.println("        [1] View All the Rides      ");
            System.out.println("        [2] Check Current Location  ");
            System.out.println("        [3] Search For A Ride       ");
            System.out.println("        [4] Go To A Ride            ");
            System.out.println("        [5] Sort Rides              ");
            System.out.println("        [6] Check Ride Connections  ");
            System.out.println("        [7] Exit                    ");
            System.out.println("------------------------------------");
            System.out.print("Input option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    displayRides();
                    break;
                case 2:
                    System.out.print("Your current location: ");
                    System.out.println(getRidesNamebyId(currentPosition));
                    break;
                case 3:
                    System.out.print("Enter ride name: ");
                    String name = sc.nextLine();
                    searchByName(name);
                    break;
                case 4:
                    System.out.print("Enter ride name: ");
                    String ride = sc.nextLine();
                    //jika user ingin kembali ke gerbang masuk
                    if (ride.equalsIgnoreCase("Entrance")) {
                        currentPosition = 0;
                        System.out.println("You are back at the entrance");
                    }
                    else {
                    //jika user ingin ke wahana lain
                    int id = getRidesIDByName(ride);
                    if (id != -1) {
                        //cari jalur tercepatnya
                        boolean success = findShortestRute(currentPosition, id);
                        if (success) {
                            currentPosition = id;
                        }
                        else {
                            System.out.println("No path found");
                        }
                    }
                    //jika tidak ditemukan wahananya
                    else {
                        System.out.println("Ride not found. Please try again");
                    }
                    }
                    break;
                case 5:
                    System.out.println("------------------------------------");
                    System.out.println("     Choose A Sorting Criterion     ");
                    System.out.println("------------------------------------");
                    System.out.println("         [1] Name                   ");
                    System.out.println("         [2] Most Popular           ");
                    System.out.println("         [3] Lowest Ticket Price    ");
                    System.out.println("         [4] Highest Rating         ");
                    System.out.println("------------------------------------");
                    System.out.print("Input Option: ");
                    int opti = sc.nextInt();
                    sc.nextLine();
                    sortRides(opti);
                    break;
                case 6:
                    System.out.print("From ride: ");
                    String r1 = sc.nextLine();
                    System.out.print("To ride\t: ");
                    String r2 = sc.nextLine();
                    //jika wahana saling terhubung
                    if (isConnected(r1, r2)) {
                        System.out.println("The two rides are connected");
                    }
                    //jika tidak terhubung
                    else {
                        System.out.println("There is no path between the two rides");
                    }
                    break;
                case 7:
                    return; //keluar dari menu
                default:
                    System.out.println("Invalid option. Please try again");
                    break;
            }
        }
    }

    //menu utk admin
    public void menuAdmin (Scanner sc) {
        while (true) {
            System.out.println("====================================");
            System.out.println("                ADMIN               ");
            System.out.println("====================================");
            System.out.println("            [1] Add Ride            ");
            System.out.println("            [2] Add Connection      ");
            System.out.println("            [3] Exit                ");
            System.out.println("------------------------------------");
            System.out.print("Input option: ");
            int option = sc.nextInt(); sc.nextLine();

            switch (option) {
                case 1:
                    addRide(sc);
                    break;
                case 2:
                    addEdge(sc);
                    break;
                case 3:
                    return;//keluar dari menu
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}

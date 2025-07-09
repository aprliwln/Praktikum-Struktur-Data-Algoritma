import java.util.*;

public class ShortestPath {
    //utk menyimpan simpul dan jarak totalnya
    static class Node implements Comparable<Node> {
    int id;
    int distance;

    Node (int id, int distance) {
        this.id = id;
        this.distance = distance;
    }
    //bandingkan node berdasarkan jarak terkecil
    public int compareTo (Node node) {
        return (distance-node.distance);
    }   
    }

    //fungsi utama utk mencari jalan tercepat
    public static boolean dijkstra (Graph graph, int start, int end, rides[] ridesList) {
        int n = graph.getVertex();
        int[] distance = new int[n];
        int[] previously = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE); //inisialisasi jarak tak hingga
        Arrays.fill(previously, -1); //inisialisasi parent node
        distance[start] = 0; //jarak titik awal

        PriorityQueue<Node> que = new PriorityQueue<>(); //min-heap utk mencari jarak minimum berikutnya
        que.add(new Node(start, 0)); //tambah titik awal

        //proses algoritmanya
        while (!que.isEmpty()) {
            Node current = que.poll(); //ambil node dgn jarak terpendek
            for (Edge edge: graph.getAdj().get(current.id)) {
                //jika ditemukan jalaur yg lebih pendek
                if (distance[current.id] + edge.weight < distance[edge.to]) {
                    distance[edge.to] = distance[current.id] + edge.weight;
                    previously[edge.to] = current.id; //update parent
                    que.add(new Node(edge.to, distance[edge.to]));
                }
            }
        }

        //jika tdk ditemukan jalur ke tujuan
        if (distance[end] == Integer.MAX_VALUE) {
            System.out.println("The path is empty");
            return false;
        }
        else {
            //tampilkan jalur terpendek
            displayRute(previously, end, ridesList, graph);
            return true;
        }
    }

    //utk menampilkan jalur dan nama wahana brdsrkan id yg ditemukan
    private static void displayRute (int[] previously, int target, rides[] ridesList, Graph graph) {
        List<Integer> path = new ArrayList<>();
        for (int i = target; i != -1; i = previously[i]){
            path.add(i); //telusuri rute mundur dri target ke start
        }
        Collections.reverse(path); //dari start ke target

        List<String> rute = new ArrayList<>();
        for (int id: path) {
            if (id == 0) {
                rute.add("Entrance"); //id 0 selalu dianggap pintu masuk
            }
            else {
                for (rides r: ridesList) {
                    if (r.id == id) {
                        rute.add(r.name); //tambahkan nama wahana sesuai id
                        break;
                    }
                }
            }
        }

        int total = 0; //inisialisasi total jarak
        System.out.println("------------------------------------");
        System.out.println("Shortest Path:");
        for (int i = 0; i < path.size() - 1; i++) {
            int from = path.get(i);
            int to = path.get(i + 1);
            int weight = -1;

            //cari weight dri edge lgsng antara from -> to
            for (Edge edge: graph.getAdj().get(from)) {
                if (edge.to == to) {
                    weight = edge.weight;
                    break;
                }
            }
            total += weight; //totalnya akan ditambah weight (jarak) yg dilalui
            System.out.println(rute.get(i) + " -> " + rute.get(i + 1) + " : " + weight);
        }
        System.out.println("Total\t: " + total);
        System.out.println("Rute\t: " + String.join(" -> ", rute));
    }
}

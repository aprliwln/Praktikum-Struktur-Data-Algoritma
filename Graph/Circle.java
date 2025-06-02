import java.util.*;

//untuk jaringan pertemananannya
public class Circle {
    //graph untuk merepresentasikan mahasiswa dan daftar temannya
    private Map<String, Set<String>> graph;

    public Circle() {
        //inisialisasi graph
        graph = new HashMap<>();
    }

    //methode untuk menambahkan mahasiswa dan temannya
    public void addStudent (String name, List<String> friends) {
        //jika nama mahasiswa tidak ada, tambahkan mahasiswa ke graph
        graph.putIfAbsent (name, new HashSet<>());

        //loop ini untuk menambahkan setiap nama teman ke dlm daftar teman mahasiswa
        for (String friendName: friends) {
            //menambahkan teman ke graph, jika tidak ada
            graph.putIfAbsent (friendName, new HashSet<>());
            //menambahkan teman ke daftar teman mahasiswa
            graph.get(name).add(friendName);
            //menambahkan mahasiswa ke daftar temannya
            graph.get(friendName).add(name);
        }
    }

    //methode untuk menambahkan hubungan pertemanan dua mahasiswa
    public boolean addFriendship (String std1, String std2) {
        //jika salah satu tidak ditemukan, akan mengembalikan nilai false
        if (!graph.containsKey(std1) || !graph.containsKey(std2)) {
            return false;
        }
        
        //jika ditemukan, maka akan dibuat hubungan pertemanan
        graph.get(std1).add(std2);
        graph.get(std2).add(std1);
        return true;
    }

    //methode untuk menghapus mahasiswa dari semua daftar teman
    public boolean remove (String name) {
        //jika nama mahasiswa tidak ditemukan
        if (!graph.containsKey(name)) {
            return false;
        }

        //jika nama mahasiswa ditemukan 
        for (String friendName: graph.get(name)) {
            //menghapus mahasiswa dari daftar temannya
            graph.get(friendName).remove(name);
        }
        //menghapus mahasiswa dari graph
        graph.remove(name);
        return true;
    }

    //methode untuk mencari teman bersama antara dua mahasiswa
    public Set<String> mutualFriends (String std1, String std2) {
        //jika salah satu tidak ditemukan, maka mengembalikan nilai null
        if (!graph.containsKey(std1) || !graph.containsKey(std2)) {
            return null;
        }

        //jika ditemukan, maka mengambil daftar teman dari masing-masing mahasiswa
        Set<String> f1 = graph.get(std1);
        Set<String> f2 = graph.get(std2);

        //untuk membuat salinan dari daftar teman std1 agar tidak mengubah aslinya
        Set<String> mutual = new HashSet<>(f1);
        
        //menyisakan teman yg hanya dimiliki keduanya (irisan antara f1 & f2)
        mutual.retainAll(f2);
        return mutual;
    }

    //methode untuk mendapatkan daftar teman dari mahasiswa
    public Set<String> getFriends (String name) {
        //jika tidak ada, maka akan mengembalikan set kosong
        return graph.getOrDefault (name, Collections.emptySet());
    }

    //methode untuk mengecek apakah mahasiswa ada atau tidak
    public boolean exist (String name) {
        return graph.containsKey(name);
    }

    //methode untuk mendapatkan semua mahasiswa yg terdaftar
    public Set<String> allStudents() {
        return graph.keySet();
    }
}

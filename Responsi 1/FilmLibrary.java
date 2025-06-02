import java.util.*;

//kelas utama sistem perpustakaan film
public class FilmLibrary {
    List <FilmItem> listFilm = new ArrayList<>(); //menyimpan daftar semua film
    Stack <String> history = new Stack<>(); //riwayat peminjaman/pengembalian
    Queue <Waiting> waiting = new LinkedList<>(); //untuk user menunggu antrian peminjaman
    Set <String> registered = new HashSet<>(); //untuk user yang sudah mendaftar
    Map <String, List <FilmItem>> userItems = new HashMap<>(); //daftar film yang dipinjam
    TreeSet <FilmItem> sortedFilm; //film yang disort berdasarkan judul
    BinaryTree filmTree = new BinaryTree(); //untuk pencarian

    //konstruktor
    FilmLibrary() {
        sortedFilm = new TreeSet<> (new FilmCompare());
        addListFilm();
    }

    //menambahkan daftar film
    private void addListFilm() {
        addFilm (new FilmItem ("Howl's Moving Castle", 2004));
        addFilm (new FilmItem ("Spirited Away", 2001));
        addFilm (new FilmItem ("Kiki's Delivery",  1989));
        addFilm (new FilmItem ("Your Name", 2016));
        addFilm (new FilmItem ("Wheathering With You", 2019));
        addFilm (new FilmItem ("Suzume", 2022));
        addFilm (new FilmItem ("My Neighboor Totoro", 1988));
        addFilm (new FilmItem ("Arrietty", 2010));
        addFilm (new FilmItem ("A Silent Voice", 2016));
        addFilm (new FilmItem ("Ponyo", 2008));
        addFilm (new FilmItem ("Sherlock Holmes", 2009));
        addFilm (new FilmItem ("Interstellar", 2014));
        addFilm (new FilmItem ("Little Women", 2019));
        addFilm (new FilmItem ("Harry Potter", 2001));
        addFilm (new FilmItem ("The Chronicles of Narnia", 2005));
        addFilm (new FilmItem ("The Little Rascals", 1994));
        addFilm (new FilmItem ("Enola Holmes", 2020));
        addFilm (new FilmItem ("Flipped", 2010));
        addFilm (new FilmItem ("Pride and Prejudice", 2005));
        addFilm (new FilmItem ("The Parent Trap", 1998));
    }

    //menambahkan film baru
    public void addFilm (FilmItem item) {
        listFilm.add (item);
        sortedFilm.add (item);
        filmTree.insert (item);
    }

    //untuk mengecek jika film telah dipinjam oleh user lain
    private boolean isBorrowed (FilmItem item) {
        for (List <FilmItem> borrowedList: userItems.values()) {
            if (borrowedList.contains(item)) {
                return true;
            }
        }
        return false;
    }

    //untuk peminjaman film
    public void borrowFilm (String userName, String title) {
        
        //jika judul yg dimasukkan tidak sesuai
        if (!filmTree.search (title.toLowerCase())) {
            System.out.println("Film title not found in the library");
            return;
        }

        //jika judulnya sesuai
        FilmItem foundItem = null;
        for (FilmItem m: listFilm) {
            if (m.title.equalsIgnoreCase (title)) { //membandingkan judul film
                foundItem = m; //film ditemukan
                break;
            }
        }

        //jika filmnya tersedia
        if (foundItem != null) {
            //Jika film sudah dipinjam, user yg lain masuk antrian
            if (isBorrowed (foundItem)) {
                //tambahkan user ke waiting list
                waiting.add(new Waiting(userName, title));
                System.out.println("Film has been borrowed. " + userName + " added to waiting list");
            }
            else {
            //jika user belum memiliki daftar peminjaman
                if (!userItems.containsKey (userName)) {
                    userItems.put (userName, new ArrayList<>());
                }
                userItems.get (userName).add (foundItem); //tambah film ke daftar pinjaman user
                System.out.println(userName + " borrowed " + foundItem);
                history.push ("Borrow: " + foundItem.title + " by " + userName); //tambah ke riwayat
            }
        }
    }

    //untuk pengembalian film
    public void returnFilm (String userName, String title) {
        //mengambil daftar film yg dipinjam, jika belum pernah ke list kosong
        List<FilmItem> borrowed = userItems.getOrDefault (userName, new ArrayList<>());
        FilmItem foundItem = null;
        
        //cari film berdasarkan judul dari yg user pinjam
        for (FilmItem m: borrowed) {
            if (m.title.equalsIgnoreCase (title)) {
                foundItem = m; //film ditemukan
                break;
            }
        }

        //jika ditemukan
        if (foundItem != null) {
            borrowed.remove (foundItem); //hapus dr daftar pinjam
            System.out.println(userName + " returned " + foundItem);
            //simpan ke riwayat
            history.push ("Return: " + foundItem.title + " by " + userName);

            //jika user dlm antrian meminjam
            if (!waiting.isEmpty()) {
                Waiting nextUser = waiting.poll();
                borrowFilm (nextUser.userName, nextUser.filmTitle);
            }
        }

        //jika film tdk ditemukan
        else {
            System.out.println("Film not found in borrowed list");
        }
    }

    //untuk pendaftaran user baru
    public void register (String userName) {
        //jika user sudah pernah register
        if (registered.contains (userName)) {
            System.out.println("Alredy registered");
        }
        //jika belum
        else {
            registered.add (userName);
            System.out.println(userName + " registered successfull");
        }
    }

    //untuk menampilkan film yg tersedia
    public void display() {
        System.out.println("====================================");
        System.out.println("             LIST FILM              ");
        System.out.println("====================================");
        for (FilmItem m: listFilm) {
            System.out.println(m.title + " (" + m.year + ")");
        }
    }

    //untuk menampilkan film yg sudah diurutkan
    public void showSort() {
        System.out.println("====================================");
        System.out.println("            SORT BY TITLE           ");
        System.out.println("====================================");
        for (FilmItem m: sortedFilm) {
            System.out.println(m.title + " (" + m.year + ")");
        }
    }

    //untuk menampilkan film yg sedang dipinjam
    public void showBorrowed() {
        System.out.println("====================================");
        System.out.println("           BORROWED FILM            ");
        System.out.println("====================================");

        //jika blm ada yg meminjam
        if (userItems.isEmpty()) {
            System.out.println("No film is currently borrowed");
            return;
        }

        //iterasi utk user dan daftar film yang dipinjam
        for (Map.Entry <String, List<FilmItem>> borrow: userItems.entrySet()) {
            String user = borrow.getKey();
            List <FilmItem> listBorrow = borrow.getValue();

            //menampilkan  jika user meminjam
            if (!listBorrow.isEmpty()) {
                System.out.println(user + " has borrowed:");
                for (FilmItem take: listBorrow){
                    System.out.println("-> " + take);
                }
            }
        }
    }

    //untuk menampilkan riwayat aktivitas
    public void showHistory() {
        System.out.println("====================================");
        System.out.println("               HISTORY              ");
        System.out.println("------------------------------------");

        //jika blm ada aktivitas
        if (history.isEmpty()) {
            System.out.println("No Film has been borrowed/returned yet");
        }
        //jika sudah ada aktivitas
        else {
            for (String act: history) {
                System.out.println (act);
            }
        }
    }

    //untuk mencari film berdasarkan judul
    public void search (String title) {
        //jika judul ditemukan
        if (filmTree.search (title.toLowerCase())) {
            System.out.println("Film available");
        }
        //jika judul tdk ditemukan
        else {
            System.out.println("Film not available");
        }
    }

    //mengecek jika user sudah terdaftar
    public boolean isRegistered (String userName) {
        return registered.contains (userName);
    }

    //untuk menampilkan antrian film yg akan dipinjam
    public void showWaitingList() {
        System.out.println("====================================");
        System.out.println("           WAITING LIST             ");
        System.out.println("====================================");

        //jika blm ada yg antri
        if (waiting.isEmpty()) {
            System.out.println("No one is currently waiting");
        }
        
        //jika sudah ada yg mengantri
        else {
            for (Waiting user: waiting) {
                System.out.println(user.userName + " waiting for " + user.filmTitle + " ");
            }
        }
    }
}

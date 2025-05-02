import java.util.*;

//class untuk tiap satu bangsawan
class NobleHierarchy{
    String name; 
    String title; //gelar
    List <NobleHierarchy> subordinates; //untuk menyimpan daftar bawahan langsung

    //konstruktor
    public NobleHierarchy(String name, String title){
        this.name = name;
        this.title = title;
        this.subordinates = new ArrayList<>();
    }

    //method ini digunakan untuk menambahkan daftar bawahan langsung
    public void addSubordinate(NobleHierarchy subordinate){
        subordinates.add(subordinate);
    }

    //method ini digunakan untuk menghapus bangsawan berdasarkan nama
    public boolean removeNoble(String name){
        //cek apakah nama tersebut ada di salah satu bawahan langsung
        for(int i = 0; i < subordinates.size(); i++){
            //jika nama tersebut ditemukan
            if(subordinates.get(i).name.equals(name)) {
                subordinates.remove(i); //hapus dari list
                return true; //berhasil 
            }
        }
        //jika nama tersebut tidak ditemukan di bawahan langsung, maka akan rekursif ke bawahannya lagi
        for(NobleHierarchy sub: subordinates){
            //hapus dari list jika nama tersebut ditemukan
            if(sub.removeNoble(name)){
                return true; //berhasil
            }
        }
        //jika tidak ditemukan
        return false;
    }

    //Method ini digunakan untuk menampilkan hasil susunan dengan metode pre-order (dari paling atas)
    public void printHierarchyPreorder(int depth){
        //Loop di bawah digunakan untuk menambahkan garis agar mudah dilihat/dibaca susunananya bagaimana
        for(int i=0; i < depth; i++){
            System.out.print("│   ");
        }
        System.out.println("├── "+ name + " (" + title + ")");
        //Loop di bawah digunakan untuk menampilkan hasilnya secara urut dengan rekursif
        for(NobleHierarchy sub: subordinates){
            sub.printHierarchyPreorder(depth + 1); //depth naik tiap turun level
        }
    }

    //Method ini digunakan untuk menampilkan hasil susunan dengan metode post-order (dari paling bawah)
    public void printHierarchyPostorder(){
        //Loop di bawah digunakan untuk menampilkan hasilnya secara urut
        for(NobleHierarchy sub: subordinates){
            sub.printHierarchyPostorder();
        }
        System.out.println(name + " (" + title + ")");
    }

    //Method ini digunakan untuk mencari seorang bangsawan berdasarkan namanya
    public NobleHierarchy searchByName(String name){
        if(this.name.equals(name)){
            return this; //jika sudah sama, maka akan dikembalikan
        }
        //jika tidak sama, maka akan di cari semua bawahan secara rekursif
        for(NobleHierarchy sub: subordinates){
            NobleHierarchy found = sub.searchByName(name);
            if (found != null) {
                return found; //jika ditemukan akan dikembalikan
            }
        }
        //jika tidak ditemukan, maka mengembalikan null
        return null;
    }
}

public class EnglandHierarchy {
    public static void main(String[] args) throws Exception {
        //memanggil fungsi addSubordinate untuk menambahkan bangsawan
        NobleHierarchy king = new NobleHierarchy("Charles III", "King");
        
        NobleHierarchy william = new NobleHierarchy("William", "Prince of Wales");
        king.addSubordinate(william);
        william.addSubordinate(new NobleHierarchy("George", "Prince"));
        william.addSubordinate(new NobleHierarchy("Charlotte", "Princess"));
        william.addSubordinate(new NobleHierarchy("Louis", "Prince"));

        NobleHierarchy harry = new NobleHierarchy("Harry", "Duke of Sussex");
        king.addSubordinate(harry);
        harry.addSubordinate(new NobleHierarchy("Archie", "Prince"));
        harry.addSubordinate(new NobleHierarchy("Lilibet", "Princess"));

        NobleHierarchy andrew = new NobleHierarchy("Andrew","Duke of York");
        king.addSubordinate(andrew);
        andrew.addSubordinate(new NobleHierarchy("Beatrice", "Princess"));
        andrew.addSubordinate(new NobleHierarchy("Eugenie", "Princess"));

        NobleHierarchy edward = new NobleHierarchy("Edward", "Duke of Edinburgh");
        king.addSubordinate(edward);
        edward.addSubordinate(new NobleHierarchy("James", "Earl of Wessex"));
        edward.addSubordinate(new NobleHierarchy("Louise", "Lady"));

        NobleHierarchy anne = new NobleHierarchy("Anne", "Princess Royal");
        king.addSubordinate(anne);

        System.out.println("----- PRE-ORDER -----");
        king.printHierarchyPreorder(0);

        System.out.println("===== SEARCH BY NAME =====");
        NobleHierarchy foundName = king.searchByName("William");
        //jika nama tersebut ditemukan, maka akan ditampilkan
        if (foundName != null) {
            System.out.println(foundName.name + " (" + foundName.title + ")");
        }
        //jika tidak ditemukan
        else {
            System.out.println("Not Found!");
        }

        System.out.println("===== REMOVE BY NAME =====");
        boolean removed = king.removeNoble("Harry");
        //jika nama sesuai
        if(removed){
            System.out.println("Succesfull Remove");
        }
        //jika nama tidak sesuai
        else {
            System.out.println("Not Found!");
        }

        System.out.println("----- POST-ORDER -----");
        king.printHierarchyPostorder();
    }
}

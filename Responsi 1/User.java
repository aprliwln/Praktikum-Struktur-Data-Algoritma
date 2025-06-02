//untuk pengguna
public class User {
    String name;

    //konstruktor
    User (String name) {
        this.name = name;
    }

    //untuk menampilkan user
    @Override
    public String toString() {
        return name;
    }
}

// untuk menyimpan data film
public class FilmItem {
    String title;
    int year;

    //konstruktor
    public FilmItem (String title, int year) {
        this.title = title;
        this.year = year;
    }

    //untuk menampilkan filmnya
    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}
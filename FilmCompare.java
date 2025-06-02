import java.util.*;

//untuk membandingkan film berdasarkan judul
public class FilmCompare implements Comparator<FilmItem> {
    @Override
    public int compare(FilmItem one, FilmItem two) {
        return one.title.compareToIgnoreCase(two.title);
    }
}

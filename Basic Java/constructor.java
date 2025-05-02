class song {
    String name;
    int year;

    //constructor
    song (String name, int year) {
        this.name = name;
        this.year = year;
    }   
}

public class constructor {
    public static void main (String[] args) {
        song one = new song ("Blue", 2024);
        song two = new song ("Nina" ,2024);
        song three = new song ("What Makes You Beautiful",2011);

        System.out.println("Song: " + one.name + " -> release: " + one.year);
        System.out.println("Song: " + two.name + " -> release: " + two.year);
        System.out.println("Song: " + three.name + " -> release: " + three.year);
    }
}

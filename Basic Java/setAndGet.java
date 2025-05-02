class movie {
    private String name;
    private int year;

    //Setter
    public void setName (String newName) {
        this.name = newName;
    }
    //Getter
    public String getName() {
        return name;
    }

    //Setter
    public void setNum (int num) {
        if (num <= 0){
            System.out.println("Invalid year");
        }
        else{
            this.year = num;
        }
    }
    //Getter
    public int getNum() {
        return year;
    }
}

public class setAndGet {
    public static void main (String[] args){
        movie one = new movie();
        movie two = new movie();
        movie three = new movie();

        one.setName ("Sherlock Holmes");
        one.setNum (2009);
        two.setName ("Interstellar");
        two.setNum (2014);
        three.setName ("Little Women");
        three.setNum (2019);

        System.out.println("Title: " + one.getName() + " -> release: " + one.getNum());
        System.out.println("Title: " + two.getName() + " -> release: " + two.getNum());
        System.out.println("Title: " + three.getName() + " -> release: " + three.getNum());
    }
}
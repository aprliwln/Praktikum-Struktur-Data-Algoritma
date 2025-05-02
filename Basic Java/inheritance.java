//Super Class/Parent Class
class animation {
    //Atribute/fields
    String title;
    double rate;

    //Constructor
    public animation (String title, double rate) {
        this.title = title;
        this.rate = rate;
    }
    
    //Methode
    public String show() {
        return ("Title: " + title + "\nRate: " + rate);
    }
}

//Sub Class
class movie extends animation {
    int duration; //atribute

    //constructor
    public movie (String title, double rate, int duration) {
        super (title, rate);
        this.duration = duration;
    }

    //Methode
    @Override
    public String show() {
        return (super.show() + "\nDuration: " + duration + " minutes\n");
    }
}

//Subclass
class series extends animation {
    int episodes; //atribute
    
    //constructor
    public series (String title, double rate, int episodes) {
        super (title, rate);
        this.episodes = episodes;
    }

    //methode
    @Override
    public String show() {
        return (super.show() + "\nEpisodes: " + episodes + "\n");
    }
}

//driver class
public class inheritance {
    public static void main (String[] args) {
        movie one = new movie ("Your Name" ,9.3 ,110);
        movie two = new movie ("Spirited Away" ,9.7 ,125);
        series three = new series ("Haikyuu" ,9.5 ,85);
        series four = new series ("Attack on Titan" ,9.1 ,94);

        System.out.println(one.show());
        System.out.println(two.show());
        System.out.println(three.show());
        System.out.println(four.show());
    }
}
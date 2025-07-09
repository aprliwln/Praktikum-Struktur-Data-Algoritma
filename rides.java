public class rides {
    int id;
    String name;
    int guest;
    int price;
    Double rate;

    //konstruktor utk membuat objek rides baru
    public rides (int id, String name, int guest, int price, Double rate) {
        this.id = id;
        this.name = name;
        this.guest = guest;
        this.price = price;
        this.rate = rate;
    }
}

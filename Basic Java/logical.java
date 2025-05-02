public class logical {
    public static void main (String[] args) {
        int n = 9;
        
        System.out.println("Logical: ");
        System.out.println(n > 1 && n < 10);
        System.out.println(n < 1 || n > 10);
        System.out.println (!(n > 1 && n < 10));
    }
}
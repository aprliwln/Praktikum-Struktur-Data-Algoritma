public class bitwise {
    public static void main (String[] args) {
        //Bitwise: menggunakan binary
        int x = 2; //binary: 0010
        int y = 4; //binary: 0100
        
        System.out.println("Bitwise: ");
        System.out.println(x + " & " + y + " = " + (x & y));
        System.out.println(x + " | " + y + " = " + (x | y));
        System.out.println(x + " ^ " + y + " = " + (x ^ y));
        System.out.println(x + " << " + y + " = " + (x << y));
        System.out.println(x + " >> " + y + " = " + (x >> y));
        System.out.println("~ " + x + " = " + (~x));
        System.out.println("~ " + y + " = " + (~y));
    }
}
public class For {
    public static void main (String[] args) {
       int baris = 7;
       int angka = 3;
       char huruf = 'a';

       for(int i = 1; i <= baris; i++){
            for(int j = 1; j <= baris; j++){
                if(i == j || j == (baris - i + 1)){
                    System.out.print(angka);
                }
                else{
                    System.out.print(huruf);
                }
            }
            System.out.println();
       }
    }
}
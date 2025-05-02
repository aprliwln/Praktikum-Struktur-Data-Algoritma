public class While {
    public static void main (String[] args) {
       int y = 5;
       int i = 1;
       int sum = 0;
       
       while(i <= y){
            System.out.print(i);
            sum = sum + i;
            
            if(i < y){
                System.out.print("+");
            }
            i++;
        }
        System.out.print(" = " + sum);
    }
}
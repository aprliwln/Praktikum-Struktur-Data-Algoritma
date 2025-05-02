public class Switchh{
    public static void main (String[] args){
        int month = 9;
        System.out.println("The " + month + " month");

        switch(month) {
            case 1:
            case 2:
            case 12:
                System.out.println("It's winter time");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("It's spring time");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("It's summer time");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("It's autumn time");
                break;
            default:
                System.out.println("Invalid");
                break;
        }
    }
}
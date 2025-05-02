public class IfElse {
    public static void main (String[] args){
        int num = 90;
        
        if(num <= 100 && num >= 85){
            System.out.println(num+ " is A");
        }
        else if(num < 85 && num >= 80){
            System.out.println(num+ " is A-");
        }
        else if(num < 80 && num >= 75){
            System.out.println(num+ " is B+");
        }
        else if(num < 75 && num >= 70){
            System.out.println(num+ " is B");
        }
        else if(num < 70 && num >= 65){
            System.out.println(num+ " is C+");
        }
        else if(num < 65 && num >= 60){
            System.out.println(num+ " is C");
        }
        else if(num < 60 && num >= 55){
            System.out.println(num+ " is D");
        }
        else if(num < 55 && num >= 0){
            System.out.println(num+ " is E");
        }
        else{
            System.out.println(num+ " is invalid grade");
        }
    }
}
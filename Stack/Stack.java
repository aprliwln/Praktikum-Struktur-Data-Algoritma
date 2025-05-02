import java.util.*;

public class Stack {
    private int max;
    private int[] element;
    private int top;

    public Stack (int size) {
        max = size;
        element = new int[size];
        top = -1; //stack kosong
    }

    public void push (int value) {
        if (top == max - 1) {
            System.out.println("Stack full!");
        }
        else {
            element[++top] = value;
        }
    }
 
    public void pop() {
        if (top == -1) {
            System.out.println("Stack empty");
        }
        else {
            int value = element[top--];
            System.out.println(value);
        }
    }

    public void peek() {
        if (top == -1) {
            System.out.println("Stack empty");
        }
        else {
            System.out.println(element[top]);
        }
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack empty");
        }
        else {
            for (int i = 0; i <= top; i++) {
                System.out.println(element[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Max size of stack: ");
        int size = sc.nextInt();

        Stack stack = new Stack(size);

        int option;
        do {
            System.out.println("\n=== Menu Stack ===");
            System.out.println("1. Push (tambah)");
            System.out.println("2. Pop (ambil)");
            System.out.println("3. Peek (lihat atas)");
            System.out.println("4. Tampilkan isi stack");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Input value: ");
                    int num = sc.nextInt();
                    stack.push(num);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    stack.display();
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } 
        while (option != 5);
        sc.close();
    }
}

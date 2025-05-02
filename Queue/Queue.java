import java.util.*;

public class Queue {
    private int max;
    private int[] array;
    private int front, rear;

    public Queue (int size) {
        max = size;
        array = new int[size];
        front = 0;
        rear = -1;
    }

    public void enque (int value) {
        if (rear == max -1) {
            System.out.println("Queue full!");
        }
        else {
            array[++rear] = value;
        }
    }

    public void deque() {
        if (front > rear) {
            System.out.println("Queue empty");
        }
        else {
            System.out.println(array[front]);
            front++;
        }
    }

    public void peek() {
        if (front > rear) {
            System.out.println("Queue empty");
        }
        else {
            System.out.println(array[front]);
        }
    }

    public void display() {
        if (front > rear) {
            System.out.println("Queue empty");
        }
        else {
            for (int i = front; i <= rear; i++){
                System.out.println(array[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input size: ");
        int size = sc.nextInt();

        Queue que = new Queue(size);

        int option;
        do {
            System.out.println("\n=== Menu Stack ===");
            System.out.println("1. Enqueue (tambah)");
            System.out.println("2. Dequeue (ambil)");
            System.out.println("3. Peek (lihat atas)");
            System.out.println("4. Tampilkan isi Queue");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Input value: ");
                    int num = sc.nextInt();
                    que.enque(num);
                    break;
                case 2:
                    que.deque();
                    break;
                case 3:
                    que.peek();
                    break;
                case 4:
                    que.display();
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
 

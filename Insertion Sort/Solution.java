import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sc.nextLine();
        }
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j;
            for (j = i; j > 0 && arr[j-1] > key; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = key;
        }
        
        for (int a: arr) {
            System.out.println(a);
        }
        sc.close();
    }
}



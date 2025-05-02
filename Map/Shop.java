/*
    Aprillia Wulandari
    L0124040
 */

import java.util.*;
import java.io.*;

// untuk menyimpan informasi produk
class Product {
    String name;
    int stock;
    int price;

    Product (String name, int stock, int price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}

// untuk menyimpan saldo
class Saldo {
    private int value;

    // saldo awal
    public Saldo (int begin) {
        this.value = begin;
    }

    // tambah saldo
    public void add (int amount) {
        this.value += amount;
    }

    // mengurangi saldo waktu membeli produk
    public void minus (int amount) {
        if (amount <= this.value) {
            this.value -= amount;
        }
        else {
            System.out.println("Insufficient balance");
        }
    }

    public int getValue() {
        return this.value;
    }
}

public class Shop {
    public static void main(String[] args) throws Exception {

       // membaca data produk dari file
       Map<String, Product> listProduct = readFile("data_product.txt");
       Saldo saldo = new Saldo(0); // saldo awal Rp0
       Scanner input = new Scanner(System.in);

       // menu utama
       while (true) {
        System.out.println("====================================");
        System.out.println("                MENU                ");
        System.out.println("====================================");
        System.out.println("            [1] Show Product        ");
        System.out.println("            [2] Buy Product         ");
        System.out.println("            [3] Show Saldo          ");
        System.out.println("            [4] Top Up Saldo        ");
        System.out.println("            [5] Exit                ");
        System.out.println("------------------------------------");
        System.out.print("Input option: ");
        int option = input.nextInt();
        clearScreen();

        switch (option) {
            case 1:
                showProduct(listProduct);
                break;
            case 2:
                showProduct(listProduct);
                buyProduct(listProduct, saldo, input);
                break;
            case 3:
                System.out.println("Current balance: \033[0;31mRp" + saldo.getValue() + "\033[0m");
                break;
            case 4:
                System.out.println("Current balance\t: \033[0;31mRp" + saldo.getValue() + "\033[0m");
                System.out.printf("Enter an amount\t: Rp");
                int topUp = input.nextInt();
                saldo.add(topUp);
                System.out.println("\033[0;34mTop up success...\033[0m");
                break;
            case 5:
                System.out.println("\033[0;31mExit...\033[0m");
                return;
            default:
                System.out.println("\033[0;31mInvalid option\033[0m");
                break;
            }
        }
    }

    // untuk clear screen
    static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    // membaca file dan memasukkannya ke dalam map
    static Map<String, Product> readFile(String fileName) {
        Map<String, Product> list = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] part = line.split(",");
                if (part.length == 4) {
                    String name = part[0];
                    int stock = Integer.parseInt(part[1]);
                    int price = Integer.parseInt(part[2]);
                    String code = part[3];

                    list.put(code, new Product(name, stock, price));
                }
            }
        }
        catch(IOException e) {
            System.out.println("Eror opening file. " + e.getMessage());
        }
        return list;
    }
    
    // method untuk menampilkan produk
    static void showProduct(Map<String, Product> list) {
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-3s| %-25s| %-6s| %-11s| %-6s\n", "No", "Product", "Stock", "Price", "Code");
        System.out.println("------------------------------------------------------------");

        int no = 1;
        for (Map.Entry<String, Product> entry : list.entrySet()) {
            String code = entry.getKey();
            Product product = entry.getValue();
            System.out.printf("%-3d| %-25s| %-6d| Rp%-9d| %-5s\n", no, product.name, product.stock, product.price, code);
            no++;
        }
        System.out.println("-----------------------------------------------------------");
    }

    // method untuk membeli produk (hanya bisa 1 produk saja)
    static void buyProduct(Map<String, Product> list, Saldo saldo, Scanner input) {
        System.out.print("Enter code: ");
        String code = input.next().toUpperCase();

        // cek kode produk
        if(!list.containsKey(code)) {
            System.out.println("\033[0;31mCode not found\033[0m");
            return;
        }
        clearScreen();

        Product product = list.get(code);
        System.out.println("====================================");
        System.out.println("Product name\t: " + product.name);
        System.out.println("Price\t\t: Rp" + product.price);
        System.out.println("Stock available\t: " + product.stock);
        System.out.println("====================================");
        System.out.print("Enter quantity\t: ");
        int quantity = input.nextInt();

        // cek stok
        if (quantity > product.stock){
            System.out.println("\033[0;31mInsufficient stock\033[0m");
            return;
        }
        int total = product.price * quantity;

        // cek saldo
        if(total > saldo.getValue()) {
            System.out.println("\033[0;31mInsufficient balance\033[0m");
            return;
        }
        product.stock -= quantity;
        saldo.minus(total);
        System.out.println("\033[0;35mSuccessful purchase\033[0m");

        // menampilkan struk
        printReceipt(product, quantity, total);
    }

    // menampilkan struk pembelian
    static void printReceipt(Product product, int quantity, int total) {
        System.out.println("================== RECEIPT OF PURCHASE ==================");
        System.out.println("Product\t\t: " + product.name);
        System.out.println("Quantity\t: " + quantity);
        System.out.println("Price per item\t: Rp" + product.price);
        System.out.println("Total cost\t: Rp" + total);
        System.out.println("=========================================================");
        System.out.println("                        THANK YOU!                       ");
        System.out.println("============================== ===========================");
    }
}
 

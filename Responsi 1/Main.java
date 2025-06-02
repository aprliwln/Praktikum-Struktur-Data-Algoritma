/*
 Aprillia Wulandari
 L0124040
 Kelas C Informatika
 */

import java.util.Scanner;

//kelas utama untuk menjalankan program
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); //untuk input user
        FilmLibrary lib = new FilmLibrary();
        Menu.user(sc, lib);
        sc.close();
    }
}

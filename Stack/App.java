/*
 Aprillia Wulandari
 L0124040
 Kelas C
*/

import java.util.Scanner;
import java.util.Stack;

public class App
{
    public static void main(String[] args) throws Exception 
    {
        //Stack utama
        Stack <String> movie = new Stack <>();
        Stack <Integer> release = new Stack <>();
       
        //Mengembalikan stack yg dihapus
        Stack <String> UndoMovie = new Stack <>();
        Stack <Integer> UndoRelease = new Stack <>();
        Scanner scan = new Scanner(System.in);
        
        do {
            System.out.println("====================================");
            System.out.println("                MENU                ");
            System.out.println("====================================");
            System.out.println("            [1] Add Movie           ");
            System.out.println("            [2] Last Movie          ");
            System.out.println("            [3] Remove Last Movie   ");
            System.out.println("            [4] Undo Remove         ");
            System.out.println("            [5] List Movie          ");
            System.out.println("            [6] Exit                ");
            System.out.println("------------------------------------");
            System.out.print("Input option: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.print("Movie title: ");
                    String title = scan.nextLine(); 
                    
                    System.out.print("Release (year): ");
                    int year = scan.nextInt();
                    
                    scan.nextLine();
                    movie.push(title);
                    release.push(year);
                    break;
                
                case 2:
                    if (movie.isEmpty())
                    {
                        System.out.println("\033[0;31mNo movies found\033[0m");
                    }
                    else
                    {
                        System.out.println("\033[0;34m" + movie.peek() + "\033[0m (\033[0;35m" + release.peek() + "\033[0m)");
                    }
                    break;
                
                case 3:
                    if(movie.isEmpty())
                    {
                        System.out.println("\033[0;31mNo movies found\033[0m");
                    }
                    else
                    {
                        //menghapus movie terakhir dan akan disimpan
                        String removeMovie = movie.pop();
                        int removeRelease = release.pop();

                        //lalu disimpan di undo
                        UndoMovie.push(removeMovie);
                        UndoRelease.push(removeRelease);

                        System.out.println("\033[0;32mRemoving movie...\033[0m");
                        System.out.println("\033[0;34m" + removeMovie + "\033[0m (\033[0;35m" + removeRelease + "\033[0m)");
                    }
                    break;

                case 4:
                    if (UndoMovie.isEmpty()) 
                    {
                        System.out.println("\033[0;31mNo undo's movies found\033[0m");
                    }
                    else
                    {
                        //movie yg disimpan di undo dihapus dan akan dikembalikan ke stack utama
                        String restoreMovie = UndoMovie.pop();
                        int restoreRelease = UndoRelease.pop();
                        
                        //movie dari undo dimasukkan kembali ke stack utama
                        movie.push(restoreMovie);
                        release.push(restoreRelease);

                        System.out.println("\033[0;32mUndo remove movie...\033[0m");
                        System.out.println("\033[0;34m" + restoreMovie + "\033[0m (\033[0;35m" + restoreRelease + "\033[0m)");
                    }
                    break;

                case 5:
                    if(movie.isEmpty())
                    {
                        System.out.println("\033[0;31mNo movies found\033[0m");
                    }
                    else
                    {
                        System.out.println("====================================");
                        System.out.println("             LIST MOVIE             ");
                        System.out.println("====================================");
                        //Movie yg ditambah terakhir akan jadi urutan pertama
                        for(int i = movie.size() - 1; i >= 0; i--)
                        {
                            System.out.println((movie.size() - i) + ". \033[0;34m" + movie.get(i) + "\033[0m (\033[0;35m" + release.get(i) + "\033[0m)");
                        }
                        System.out.println("------------------------------------");
                    }
                    break;
                
                case 6:
                    scan.close();
                    return;
                
                default:
                    System.out.println("\033[0;31mInvalid option!\033[0m");
                    break;
            }
        } 
        while (true);
    }
}

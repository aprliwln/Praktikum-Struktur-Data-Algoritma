import java.util.*;

public class KataAneh {
    public static void main(String[] args) throws Exception {
        String[] kata = {
            "halo", "dunia", "haha", "halo", "abcd", "abcde", "abcc", "abcde"
        };

        // melacak kata
        Set<String> kataDilihat = new HashSet<>();

        // melacak huruf
        Set<Character> huruf = new HashSet<>();
        int jumlah = 0;

        for(String k: kata) {
            //cek kata sdh pernah muncul (Set kataDilihat)
            if (kataDilihat.contains(k)) {
                continue;
            }
            kataDilihat.add(k);

            //cek semua huruf dlm kata unik (Set<Character> huruf)
            huruf.clear();
            boolean unik = true;
            for(char a: k.toCharArray()){
                if(!huruf.add(a)){
                    unik = false;
                    break;
                }
            }
            if(unik) {
                jumlah++;
            }
            //jika lolos, tambah ke jumlah
        }
        System.out.println("Jumlah kata aneh: " + jumlah);
    }
}


public class MergeSort {
    //untuk mengurutkan array rides[] berdasarkan kriteria tertentu
    public static void sort(rides[] arr, int option) {
        //jika pnjng array 1 atau kurang, maka sudah terurut
        if (arr.length <= 1) {
            return;
        }
        //menentukan titik tengah & membagi array menjadi 2 bagian
        int middle = arr.length / 2;
        rides[] left = new rides[middle];
        rides[] right = new rides[arr.length - middle];

        //menyalin data ke array kiri & kanan
        System.arraycopy(arr, 0, left, 0, middle);
        System.arraycopy(arr, middle, right, 0, arr.length - middle);

        //rekursif utk mengurutkan bagian kiri dan kanan
        sort(left, option);
        sort(right, option);
        //mengabungkan kembali 2 array yg udh terurut
        merge(arr, left, right, option);
    }
    
    //menggabungkan dua array yg udah terurut
    private static void merge (rides[] arr, rides[] left, rides[] right, int option) {
        int i = 0, j = 0, k = 0;
        //proses penggabungan elemen dari kiri & kanan berdasarkan kriteria
        while (i < left.length && j < right.length) {
            boolean takeLeft = false;

            switch (option) {
                case 1:
                    //urutkan berdasarkan nama
                    takeLeft = left[i].name.compareToIgnoreCase(right[j].name) <= 0;
                    break;
                case 2:
                    //urutkan berdasarkan jumlah pengunjung terbanyak -> terkecil
                    takeLeft = left[i].guest >= right[j].guest;
                    break;
                case 3:
                    //urutkan berdasarkan harga tiket termurah -> termahal
                    takeLeft = left[i].price <= right[j].price;
                    break;
                case 4:
                    //urutkan berdasarkan rating tertingg -> terendah
                    takeLeft = left[i].rate >= right[j].rate;
                    break;
                default:
                    System.out.println("Invalid option");
                    return;
            }
            //menambahkan elemen ke array utama dari array kiri/kanan
            if (takeLeft) {
                arr[k++] = left[i++];
            }
            else {
                arr[k++] = right[j++];
            }
        }
        //menyalin sisa elemen dari kiri jika ada
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        //menyalin sisa elemen dari kanan jika ada
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}

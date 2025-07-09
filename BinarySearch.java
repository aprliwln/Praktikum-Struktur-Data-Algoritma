//fungsi untuk mencari wahana berdasarkan nama
public class BinarySearch {
    //urutukan array data rides (wahana) secara alfabet
    public static rides searchByName(rides[] data, String target) {
        MergeSort.sort(data, 1);

        //untuk mencari nama wahana setelah diurutkan
        int low = 0;
        int high = data.length - 1;
        //selama batas bawah tdk melewati batas atas, maka dilakukan pencarian
        while (low <= high) {
            int middle = (low + high) / 2;
            //bandingkan nama ride di tengah dgn target
            int compare = data[middle].name.compareToIgnoreCase(target);
            //jika sama, kembalikan ride tadi
            if (compare == 0) return data[middle];
            //jika nama tengah < target, cari di sebelah kanan
            else if (compare < 0) low = middle + 1;
            //jika nama tengah > target, cari di sebelah kiri
            else high = middle - 1;
        }
        //jika tidak ditemukan
        return null;
    }
}

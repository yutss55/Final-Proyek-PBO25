package final_proyek_pbo.data;

import java.util.ArrayList;

import final_proyek_pbo.model.CollabPost;
import final_proyek_pbo.model.Ruangan;

public class DataStore {
    public static ArrayList<Ruangan> daftarRuangan = new ArrayList<>();
    public static ArrayList<CollabPost> daftarPostingan = new ArrayList<>();

    public static void initData() {   
        System.out.println("Data awal berhasil dimuat ke dalam Array!");
    }
}
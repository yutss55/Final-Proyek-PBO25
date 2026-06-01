package final_proyek_pbo.controller;

import final_proyek_pbo.model.CollabPost;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommunityController {
    private static final ObservableList<CollabPost> kumpulanChatMaster = FXCollections.observableArrayList();
    private int idCounter = 1;

    public CommunityController() {
        if (kumpulanChatMaster.isEmpty()) {
            kirimPesanKolaborasi("Dedi Prasetyo", "Mencari rekan untuk membuat arsitektur backend sistem informasi cerdas berbasis Java.", "[Cari_Tim]");
            kirimPesanKolaborasi("Tika Sasmita", "Proyek desain dashboard Creative Hub Navigator menggunakan CSS styling modern.", "[Cari_Proyek]");
            kirimPesanKolaborasi("Andi Firdaus", "Butuh tim ahli Cyber Security untuk pengujian celah keamanan penetrasi server.", "[Cari_Tim]");
            kirimPesanKolaborasi("Suci Ramadani", "Mencari front-end developer untuk mengintegrasikan layout halaman Community menggunakan JavaFX.", "[Cari_Tim]");
            kirimPesanKolaborasi("Rian Setiawan", "Pengembangan modul AI / Data Science untuk sistem prediksi stok inventaris cerdas.", "[Cari_Proyek]");
            kirimPesanKolaborasi("Asep Supriyadi", "Butuh UI/UX Designer untuk membuat wireframe dan mockup aplikasi mobile pencarian magang.", "[Cari_Tim]");
            kirimPesanKolaborasi("Tomi Azhari", "Proyek implementasi IoT Smart Gate menggunakan micro-controller terintegrasi database server.", "[Cari_Proyek]");
        }
    }

    public void kirimPesanKolaborasi(String namaPembuat, String isiPesan, String tagKategori) {
        if (isiPesan == null || isiPesan.trim().isEmpty() || tagKategori == null) {
            return;
        }
        CollabPost postBaru = new CollabPost(
            "Postingan Kolaborasi", 
            idCounter++,            
            null,                   
            namaPembuat,            
            tagKategori,            
            isiPesan                
        );
        kumpulanChatMaster.add(postBaru);
    }

    public ObservableList<CollabPost> getKumpulanChatMaster() {
        return kumpulanChatMaster;
    }

    public ObservableList<CollabPost> filterChatBerdasarkanTag(String tagKategori) {
        ObservableList<CollabPost> hasilFilter = FXCollections.observableArrayList();
        for (CollabPost post : kumpulanChatMaster) {
            if (post.getJudulPostingan() != null && post.getJudulPostingan().equalsIgnoreCase(tagKategori)) {
                hasilFilter.add(post);
            }
        }
        return hasilFilter;
    }
}
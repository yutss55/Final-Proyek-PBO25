package final_proyek_pbo.model; 

import java.util.ArrayList;
import java.util.List;

public class JalurKarir {
    private String nama;
    private List<Tahapan> daftarTahapan;

    public JalurKarir(String nama) {
        this.nama = nama;
        this.daftarTahapan = new ArrayList<>();
    }

    public void tambahTahapan(Tahapan t) {
        this.daftarTahapan.add(t);
    }

    public String getNama() { return nama; }
    public List<Tahapan> getDaftarTahapan() { return daftarTahapan; }

    @Override
    public String toString() { return nama; }
}
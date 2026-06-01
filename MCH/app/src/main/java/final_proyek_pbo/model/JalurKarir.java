package final_proyek_pbo.model;

import java.util.ArrayList;
import java.util.List;

public class JalurKarir {
    private String namaKarir;
    private List<Tahapan> daftarTahapan;

    public JalurKarir(String namaKarir) {
        this.namaKarir = namaKarir;
        this.daftarTahapan = new ArrayList<>();
    }
    public void tambahTahapan(Tahapan tahapan) { this.daftarTahapan.add(tahapan); }
    public String getNamaKarir() { return namaKarir; }
    public List<Tahapan> getDaftarTahapan() { return daftarTahapan; }
    @Override public String toString() { return namaKarir; }
}
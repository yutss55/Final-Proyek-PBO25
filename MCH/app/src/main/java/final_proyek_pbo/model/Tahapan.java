package final_proyek_pbo.model;

import java.util.ArrayList;
import java.util.List;

public class Tahapan {
    private int nomor;
    private String judul;
    private String deskripsi;
    private String infoAksi;
    private String textTombol;
    private String namaFileVideo;
    private List<Soal> daftarSoal;

    public Tahapan(int nomor, String judul, String deskripsi, String infoAksi, String textTombol, String namaFileVideo) {
        this.nomor = nomor;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.infoAksi = infoAksi;
        this.textTombol = textTombol;
        this.namaFileVideo = namaFileVideo;
        this.daftarSoal = new ArrayList<>();
    }

    public void tambahSoal(Soal soal) {
        this.daftarSoal.add(soal);
    }

    public int getNomor() { return nomor; }
    public String getJudul() { return judul; }
    public String getDeskripsi() { return deskripsi; }
    public String getInfoAksi() { return infoAksi; }
    public String getTextTombol() { return textTombol; }
    public String getNamaFileVideo() { return namaFileVideo; }
    public List<Soal> getDaftarSoal() { return daftarSoal; }
}
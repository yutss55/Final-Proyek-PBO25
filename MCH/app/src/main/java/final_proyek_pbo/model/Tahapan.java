package final_proyek_pbo.model; 
import java.util.ArrayList;
import java.util.List;

public class Tahapan {
    private int nomor;
    private String judul;
    private String deskripsi;
    private String infoAksi;
    private String textTombol;
    private String urlVideo;
    private List<Soal> daftarSoal; 

    public Tahapan(int nomor, String judul, String deskripsi, String infoAksi, String textTombol, String urlVideo) {
        this.nomor = nomor;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.infoAksi = infoAksi;
        this.textTombol = textTombol;
        this.urlVideo = urlVideo;
        this.daftarSoal = new ArrayList<>(); 
    }

    public void tambahSoal(Soal s) { this.daftarSoal.add(s); }
    public List<Soal> getDaftarSoal() { return daftarSoal; }

    public int getNomor() { return nomor; }
    public String getJudul() { return judul; }
    public String getDeskripsi() { return deskripsi; }
    public String getInfoAksi() { return infoAksi; }
    public String getTextTombol() { return textTombol; }
    public String getNamaFileVideo() { return urlVideo; } 
}
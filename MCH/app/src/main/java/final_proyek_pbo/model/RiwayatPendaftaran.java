package final_proyek_pbo.model;

public class RiwayatPendaftaran {

    private String idDaftar;
    private String namaWorkshop;
    private String kategori;
    private String mentor;
    private String status;

    public RiwayatPendaftaran(
            String idDaftar,
            String namaWorkshop,
            String kategori,
            String mentor,
            String status
    ) {
        this.idDaftar = idDaftar;
        this.namaWorkshop = namaWorkshop;
        this.kategori = kategori;
        this.mentor = mentor;
        this.status = status;
    }

    public String getIdDaftar() {
        return idDaftar;
    }

    public String getNamaWorkshop() {
        return namaWorkshop;
    }

    public String getKategori() {
        return kategori;
    }

    public String getMentor() {
        return mentor;
    }

    public String getStatus() {
        return status;
    }
}
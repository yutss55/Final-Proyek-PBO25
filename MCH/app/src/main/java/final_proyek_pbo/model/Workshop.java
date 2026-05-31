package final_proyek_pbo.model;

public class Workshop extends EntitasKreatif {

    private String kategori;
    private String mentor;
    private String kuota;
    private String deskripsi;

    public Workshop(
            String nama,
            String kategori,
            String mentor,
            String kuota,
            String deskripsi
    ) {
        super(nama);

        this.kategori = kategori;
        this.mentor = mentor;
        this.kuota = kuota;
        this.deskripsi = deskripsi;
    }

    @Override
    public String getDeskripsiPeran() {
        return "Workshop Kreatif";
    }

    public String getKategori() {
        return kategori;
    }

    public String getMentor() {
        return mentor;
    }

    public String getKuota() {
        return kuota;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
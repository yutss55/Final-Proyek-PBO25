package final_proyek_pbo.model;

public class Event extends EntitasKreatif {

    private String kategori;
    private String deskripsi;
    private String mentor;
    private String kuota;

    private boolean penuh;
    private boolean terdaftar;

    public Event(
            String nama,
            String kategori,
            String deskripsi,
            String mentor,
            String kuota,
            boolean penuh,
            boolean terdaftar) {

        super(nama);

        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.mentor = mentor;
        this.kuota = kuota;
        this.penuh = penuh;
        this.terdaftar = terdaftar;
    }

    public String getKategori() {
        return kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getMentor() {
        return mentor;
    }

    public String getKuota() {
        return kuota;
    }

    public boolean isPenuh() {
        return penuh;
    }

    public boolean isTerdaftar() {
        return terdaftar;
    }

    @Override
    public String getDeskripsiPeran() {
        return "Event Kreatif";
    }
    public void setTerdaftar(boolean terdaftar) {
        this.terdaftar = terdaftar;
    }

    public void setPenuh(boolean penuh) {
        this.penuh = penuh;
    }
}
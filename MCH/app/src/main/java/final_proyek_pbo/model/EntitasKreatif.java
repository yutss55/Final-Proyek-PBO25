package final_proyek_pbo.model;

public abstract class EntitasKreatif {
    protected String nama;

    public EntitasKreatif(String nama) {
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public abstract String getDeskripsiPeran();
}
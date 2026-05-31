package final_proyek_pbo.model;

import java.time.LocalDateTime;

public class CollabPost extends EntitasKreatif {
    private int idPostingan;
    private Ruangan ruangan;
    private String namaPembuat;
    private String judulPostingan;
    private String isiKonten;
    private LocalDateTime waktuDibuat;

    public CollabPost() {
        super("Postingan Kolaborasi");
        this.waktuDibuat = LocalDateTime.now();
    }

    public CollabPost(String nama, int idPostingan, Ruangan ruangan, String namaPembuat, String judulPostingan, String isiKonten) {
        super(nama);
        this.idPostingan = idPostingan;
        this.ruangan = ruangan;
        this.namaPembuat = namaPembuat;
        this.judulPostingan = judulPostingan;
        this.isiKonten = isiKonten;
        this.waktuDibuat = LocalDateTime.now();
    }

    @Override
    public String getDeskripsiPeran() {
        return "Postingan kolaborasi oleh " + namaPembuat + " untuk ruangan " + (ruangan != null ? ruangan.getNamaRuangan() : "-");
    }

    public int getIdPostingan() {
        return idPostingan;
    }

    public void setIdPostingan(int idPostingan) {
        this.idPostingan = idPostingan;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    public String getNamaPembuat() {
        return namaPembuat;
    }

    public void setNamaPembuat(String namaPembuat) {
        this.namaPembuat = namaPembuat;
    }

    public String getJudulPostingan() {
        return judulPostingan;
    }

    public void setJudulPostingan(String judulPostingan) {
        this.judulPostingan = judulPostingan;
    }

    public String getIsiKonten() {
        return isiKonten;
    }

    public void setIsiKonten(String isiKonten) {
        this.isiKonten = isiKonten;
    }

    public LocalDateTime getWaktuDibuat() {
        return waktuDibuat;
    }

    public void setWaktuDibuat(LocalDateTime waktuDibuat) {
        this.waktuDibuat = waktuDibuat;
    }

    @Override
    public String toString() {
        return "CollabPost{" +
                "id=" + idPostingan +
                ", judul='" + judulPostingan + '\'' +
                ", oleh='" + namaPembuat + '\'' +
                '}';
    }
}
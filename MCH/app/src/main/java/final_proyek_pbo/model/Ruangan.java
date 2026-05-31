package final_proyek_pbo.model;

public class Ruangan extends EntitasKreatif {
    private String tanggalPinjam;
    private String status;

    public Ruangan(String namaRuangan, String tanggalPinjam, String status) {
        super(namaRuangan); 
        this.tanggalPinjam = tanggalPinjam;
        this.status = status;
    }

    public String getNamaRuangan() {
        return super.getNama(); 
    }

    public void setNamaRuangan(String namaRuangan) {
        super.setNama(namaRuangan);
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getDeskripsiPeran() {
        return "Infrastruktur fisik/ruangan untuk mendukung kolaborasi komunitas.";
    }
}
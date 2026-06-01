package final_proyek_pbo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingSession {
    
    private final StringProperty idBooking;
    private final StringProperty namaPeminjam;
    private final StringProperty namaRuangan;
    private final StringProperty tanggalSewa;
    private final StringProperty jamSpesifik;
    private final StringProperty keperluan;
    private final StringProperty statusApproval;

    public BookingSession(String idBooking, String namaPeminjam, String namaRuangan, 
                          String tanggalSewa, String jamSpesifik, String keperluan, String statusApproval) {
        this.idBooking = new SimpleStringProperty(idBooking);
        this.namaPeminjam = new SimpleStringProperty(namaPeminjam);
        this.namaRuangan = new SimpleStringProperty(namaRuangan);
        this.tanggalSewa = new SimpleStringProperty(tanggalSewa);
        this.jamSpesifik = new SimpleStringProperty(jamSpesifik);
        this.keperluan = new SimpleStringProperty(keperluan);
        this.statusApproval = new SimpleStringProperty(statusApproval);
    }
    public void setStatusApproval(String status) {
    this.statusApproval.set(status);
    }

    public String getIdBooking() { 
        return idBooking.get(); 
    }

    public String getNamaPeminjam() { 
        return namaPeminjam.get(); 
    }

    public String getNamaRuangan() { 
        return namaRuangan.get(); 
    }

    public String getTanggalSewa() { 
        return tanggalSewa.get(); 
    }

    public String getJamSpesifik() { 
        return jamSpesifik.get(); 
    }

    public String getKeperluan() { 
        return keperluan.get(); 
    }

    public String getStatusApproval() { 
        return statusApproval.get(); 
    }

    public StringProperty idBookingProperty() { 
        return idBooking; 
    }

    public StringProperty namaPeminjamProperty() { 
        return namaPeminjam; 
    }

    public StringProperty namaRuanganProperty() { 
        return namaRuangan; 
    }

    public StringProperty tanggalSewaProperty() { 
        return tanggalSewa; 
    }

    public StringProperty jamSpesifikProperty() { 
        return jamSpesifik; 
    }

    public StringProperty keperluanProperty() { 
        return keperluan; 
    }

    public StringProperty statusApprovalProperty() { 
        return statusApproval; 
    }
}
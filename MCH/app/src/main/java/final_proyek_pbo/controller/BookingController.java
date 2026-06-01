package final_proyek_pbo.controller;

import java.time.LocalDate;
import java.util.UUID;

import final_proyek_pbo.model.BookingSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookingController {
    
    private static final ObservableList<BookingSession> riwayatBookingMaster = FXCollections.observableArrayList();

    public BookingController() {
    }

    public ObservableList<String> getDaftarRuangan() {
        return FXCollections.observableArrayList(
            "Cybersecurity",
            "Front-End Development",
            "Back-End Development",
            "Data Science",
            "Mobile App Development",
            "Artificial Intelligence",
            "UI/UX Design"
        );
    }

    public ObservableList<String> getDaftarJamSpesifik() {
        return FXCollections.observableArrayList(
            "08:00 - 09:40 (Sesi 1)",
            "09:45 - 11:25 (Sesi 2)",
            "11:30 - 13:10 (Sesi 3)",
            "13:30 - 15:10 (Sesi 4)",
            "15:15 - 16:55 (Sesi 5)"
        );
    }

    public boolean cekJadwalBentrok(String ruangan, LocalDate tanggal, String jamSpesifik) {
        if (ruangan == null || tanggal == null || jamSpesifik == null) {
            return false;
        }
        
        String tanggalStr = tanggal.toString();

        for (BookingSession b : riwayatBookingMaster) {
            if (b.namaRuanganProperty().get().equalsIgnoreCase(ruangan) &&
                b.tanggalSewaProperty().get().equals(tanggalStr) &&
                b.jamSpesifikProperty().get().equalsIgnoreCase(jamSpesifik)) {
                return true;
            }
        }
        return false;
    }

    public boolean buatPemesanan(String namaPeminjam, String ruangan, LocalDate tanggal, String jamSpesifik) {
        if (namaPeminjam == null || namaPeminjam.trim().isEmpty() || 
            ruangan == null || tanggal == null || jamSpesifik == null) {
            return false;
        }

        String idBooking = "BKG-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String tanggalStr = tanggal.toString();
        String keperluan = "Eksperimen & Kolaborasi Pengembangan Skill IT";
        String statusDefault = "Menunggu"; 

        BookingSession dataBaru = new BookingSession(
            idBooking, namaPeminjam, ruangan, tanggalStr, jamSpesifik, keperluan, statusDefault
        );

        return riwayatBookingMaster.add(dataBaru);
    }

    public ObservableList<BookingSession> getRiwayatBookingMaster() {
        return riwayatBookingMaster;
    }
}
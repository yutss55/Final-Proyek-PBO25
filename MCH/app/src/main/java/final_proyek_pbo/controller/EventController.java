package final_proyek_pbo.controller;

import final_proyek_pbo.model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventController {

    private ObservableList<Event> daftarEvent;
    private ObservableList<Event> riwayatPendaftaran;

    public EventController() {

        daftarEvent = FXCollections.observableArrayList();
        riwayatPendaftaran = FXCollections.observableArrayList();

        loadDataDummy();
    }

    private void loadDataDummy() {

        daftarEvent.add(
                new Event(
                        "UI/UX Design Masterclass 2026",
                        "DESAIN",
                        "Belajar wireframe dan prototype interaktif.",
                        "Khoeril",
                        "12/15",
                        false,
                        false
                )
        );

        daftarEvent.add(
                new Event(
                        "Flutter Mobile Development",
                        "CODING",
                        "Belajar membuat aplikasi Android dan IOS.",
                        "Deng",
                        "3/10",
                        false,
                        false
                )
        );

        daftarEvent.add(
                new Event(
                        "Digital Marketing Basic",
                        "BISNIS",
                        "Strategi pemasaran digital untuk pemula.",
                        "Akbar",
                        "8/10",
                        false,
                        false
                )
        );

        daftarEvent.add(
                new Event(
                        "Advanced UI Design",
                        "DESAIN",
                        "Pelajari design system dan prototyping.",
                        "Naufal",
                        "15/15",
                        true,
                        false
                )
        );
    }

    public ObservableList<Event> getDaftarEvent() {
        return daftarEvent;
    }

    public ObservableList<Event> getRiwayatPendaftaran() {
        return riwayatPendaftaran;
    }

    public boolean daftarWorkshop(Event event) {

        if (event.isPenuh()) {
            return false;
        }

        if (event.isTerdaftar()) {
            return false;
        }

        event.setTerdaftar(true);

        if (!riwayatPendaftaran.contains(event)) {
            riwayatPendaftaran.add(event);
        }

        return true;
    }

    public ObservableList<Event> cariEvent(String keyword) {

        ObservableList<Event> hasil =
                FXCollections.observableArrayList();

        if (keyword == null || keyword.isBlank()) {
            hasil.addAll(daftarEvent);
            return hasil;
        }

        String cari = keyword.toLowerCase();

        for (Event event : daftarEvent) {

            if (event.getNama().toLowerCase().contains(cari)
                    || event.getKategori().toLowerCase().contains(cari)
                    || event.getMentor().toLowerCase().contains(cari)) {

                hasil.add(event);
            }
        }

        return hasil;
    }

    public ObservableList<Event> filterKategori(String kategori) {

        ObservableList<Event> hasil =
                FXCollections.observableArrayList();

        if (kategori == null
                || kategori.equalsIgnoreCase("SEMUA")
                || kategori.equalsIgnoreCase("ROADMAP")) {

            hasil.addAll(daftarEvent);
            return hasil;
        }

        for (Event event : daftarEvent) {

            if (event.getKategori()
                    .equalsIgnoreCase(kategori)) {

                hasil.add(event);
            }
        }

        return hasil;
    }
}
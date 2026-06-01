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
                    "AI Prompt Engineering untuk Produktivitas",
                    "CODING",
                    "Memanfaatkan AI generatif untuk meningkatkan produktivitas kerja dan belajar.",
                    "Rizky Saputra",
                    "10/15",
                    false,
                    false
            )
    );

    daftarEvent.add(
            new Event(
                    "Git & GitHub untuk Kolaborasi Tim",
                    "CODING",
                    "Mempelajari workflow GitHub yang digunakan dalam industri teknologi.",
                    "Khoeril Anwar",
                    "15/15",
                    false,
                    false
            )
    );

    daftarEvent.add(
            new Event(
                    "UI/UX Design dengan Figma",
                    "DESAIN",
                    "Belajar merancang antarmuka aplikasi yang modern dan user-friendly.",
                    "Dimas Pratama",
                    "12/15",
                    false,
                    false
            )
    );

    daftarEvent.add(
            new Event(
                    "Data Analytics dengan Power BI",
                    "BISNIS",
                    "Mengolah data dan membuat dashboard interaktif untuk pengambilan keputusan.",
                    "Akbar Hidayat",
                    "8/10",
                    false,
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
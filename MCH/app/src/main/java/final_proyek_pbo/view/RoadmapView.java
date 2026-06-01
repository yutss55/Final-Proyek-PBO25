package final_proyek_pbo.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// Mengimport Model dari folder terpisah
import final_proyek_pbo.model.JalurKarir;
import final_proyek_pbo.model.Tahapan;
import final_proyek_pbo.model.Soal;


import java.util.ArrayList;
import java.util.List;

// 1. UBAH DARI extends VBox MENJADI extends BorderPane
public class RoadmapView extends BorderPane {

    private VBox roadmapContainer;
    private String cssPath;

    public RoadmapView() {
        // Mengambil file CSS dari resources
        cssPath = getClass().getResource("/css/Roadmap.css").toExternalForm();

        // 2. HAPUS ATAU KOMENTARI BARIS INI:
        // BorderPane root = new BorderPane();
        
        // 3. Ganti "root.getStyleClass()" menjadi "this.getStyleClass()"
        this.getStyleClass().add("root-bg"); 

        // --- TOMBOL KEMBALI DI BAGIAN ATAS ---
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15, 20, 10, 20));
        topBar.getStyleClass().add("top-bar");

        Button btnBack = new Button("← Kembali ke Menu Utama");
        btnBack.getStyleClass().add("btn-back");
        btnBack.setOnMouseClicked(e -> {
            btnBack.getScene().setRoot(new HomeView());
        });

        topBar.getChildren().add(btnBack);
        
        // 4. GANTI root.setTop MENJADI this.setTop
        this.setTop(topBar);

        // --- KONTEN UTAMA ---
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(30));

        Label titleLabel = new Label("📖 Skill Roadmap & Pengembangan Kompetensi");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.WHITE);

        Label subtitleLabel = new Label("Rencanakan jalur karier kalian, kuasai keahlian baru melalui materi pilihan, dan uji pemahaman kalian dengan kuis interaktif yang tersedia.");
        subtitleLabel.setTextFill(Color.WHITE);
        subtitleLabel.setWrapText(true);

        // --- DROPDOWN CONTAINER ---
        VBox comboContainer = new VBox(10);
        comboContainer.setPadding(new Insets(15));
        comboContainer.getStyleClass().add("card-panel");

        Label comboLabel = new Label("PILIH MINAT KARIR KAMU\nPeta kurikulum belajar akan berubah menyesuaikan industri yang kamu tuju:");
        comboLabel.setTextFill(Color.WHITE);
        comboLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        ComboBox<JalurKarir> karirComboBox = new ComboBox<>();
        karirComboBox.getStyleClass().add("combo-box-custom");
        karirComboBox.setPrefWidth(350);
        karirComboBox.getItems().addAll(siapkanDataKarir());

        comboContainer.getChildren().addAll(comboLabel, karirComboBox);

        // Container untuk menampung list tahapan
        roadmapContainer = new VBox(0);

        karirComboBox.setOnAction(e -> {
            JalurKarir pilihan = karirComboBox.getValue();
            if (pilihan != null) {
                tampilkanTahapan(pilihan.getDaftarTahapan());
            }
        });

        mainContent.getChildren().addAll(titleLabel, subtitleLabel, comboContainer, roadmapContainer);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setStyle("-fx-background: #12003D; -fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.setBorder(Border.EMPTY);
        
        // 5. GANTI root.setCenter MENJADI this.setCenter
        this.setCenter(scrollPane);
        
        // 6. HAPUS BARIS INI (karena kita sudah tidak memakai root di dalam root):
        // this.getChildren().add(root);

        this.getStylesheets().add(cssPath);

        karirComboBox.getSelectionModel().selectFirst();
    }
    // ... Sisa kode ke bawah (tampilkanTahapan, buatKartuTahapan, dll) tetap sama tidak perlu diubah

    private void tampilkanTahapan(List<Tahapan> tahapanList) {
        roadmapContainer.getChildren().clear();

        for (int i = 0; i < tahapanList.size(); i++) {
            Tahapan t = tahapanList.get(i);
            roadmapContainer.getChildren().add(buatKartuTahapan(t));

            if (i < tahapanList.size() - 1) {
                HBox arrowContainer = new HBox();
                arrowContainer.setPadding(new Insets(5, 0, 5, 55));

                Polygon arrow = new Polygon(0.0, 0.0, 12.0, 0.0, 6.0, 8.0);
                arrow.setFill(Color.web("#301665"));

                arrowContainer.getChildren().add(arrow);
                roadmapContainer.getChildren().add(arrowContainer);
            }
        }
    }

    private VBox buatKartuTahapan(Tahapan tahapan) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.getStyleClass().add("card-panel");

        HBox headerBox = new HBox(15);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        StackPane numberPane = new StackPane();
        Circle circle = new Circle(16, Color.web("#301665"));
        circle.setStroke(Color.web("#9D7BEA"));
        circle.setStrokeWidth(1.5);
        Label numberLabel = new Label(String.valueOf(tahapan.getNomor()));
        numberLabel.setTextFill(Color.WHITE);
        numberLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        numberPane.getChildren().addAll(circle, numberLabel);

        Label titleLabel = new Label("Tahap " + tahapan.getNomor() + ": " + tahapan.getJudul());
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.WHITE);

        headerBox.getChildren().addAll(numberPane, titleLabel);

        Label descLabel = new Label(tahapan.getDeskripsi());
        descLabel.setTextFill(Color.WHITE);
        descLabel.setPadding(new Insets(0, 0, 0, 48));
        descLabel.setWrapText(true);

        HBox actionBox = new HBox(15);
        actionBox.setAlignment(Pos.CENTER_LEFT);
        actionBox.setPadding(new Insets(5, 0, 0, 48));

        Label actionInfo = new Label("Aksi: " + tahapan.getInfoAksi());
        actionInfo.setPadding(new Insets(6, 12, 6, 12));
        actionInfo.getStyleClass().add("action-info-label");
        actionInfo.setTextFill(Color.WHITE);

        Button actionBtn = new Button(tahapan.getTextTombol());
        actionBtn.setPadding(new Insets(6, 12, 6, 12));
        actionBtn.getStyleClass().add("btn-primary");

        actionBtn.setOnAction(e -> {
            String urlLengkap = tahapan.getNamaFileVideo();
            if (urlLengkap != null && !urlLengkap.trim().isEmpty()) {
                bukaVideoPlayer(urlLengkap);
            }
        });

        Button btnKuis = new Button("📝 Mulai Kuis");
        btnKuis.setPadding(new Insets(6, 12, 6, 12));
        btnKuis.getStyleClass().add("btn-secondary");

        btnKuis.setOnAction(e -> {
            if (tahapan.getDaftarSoal() != null && !tahapan.getDaftarSoal().isEmpty()) {
                bukaJendelaKuis(tahapan.getJudul(), tahapan.getDaftarSoal());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Belum ada kuis untuk tahapan ini.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        actionBox.getChildren().addAll(actionInfo, actionBtn, btnKuis);
        card.getChildren().addAll(headerBox, descLabel, actionBox);

        return card;
    }

    private void bukaVideoPlayer(String urlVideoLengkap) {
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(urlVideoLengkap));
            } else {
                new Alert(Alert.AlertType.ERROR, "Sistem tidak mendukung pembukaan browser otomatis.", ButtonType.OK).showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Gagal membuka tautan video.", ButtonType.OK).showAndWait();
        }
    }

    private void bukaJendelaKuis(String judulTahapan, List<Soal> daftarSoal) {
        Stage kuisStage = new Stage();
        kuisStage.setTitle("Kuis Pemahaman: " + judulTahapan);

        VBox layoutKuis = new VBox(20);
        layoutKuis.setPadding(new Insets(25));
        layoutKuis.getStyleClass().add("root-bg");
        layoutKuis.setAlignment(Pos.TOP_LEFT);

        Label titleKuis = new Label("📝 Uji Kompetensi: " + judulTahapan);
        titleKuis.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleKuis.setTextFill(Color.WHITE);
        layoutKuis.getChildren().add(titleKuis);

        List<ToggleGroup> kumpulanGrupPilihan = new ArrayList<>();

        for (int i = 0; i < daftarSoal.size(); i++) {
            Soal soal = daftarSoal.get(i);
            VBox boxSoal = new VBox(10);
            
            Label teksSoal = new Label((i + 1) + ". " + soal.getPertanyaan());
            teksSoal.setTextFill(Color.WHITE);
            teksSoal.setWrapText(true);
            boxSoal.getChildren().add(teksSoal);

            ToggleGroup grupPilihan = new ToggleGroup();
            kumpulanGrupPilihan.add(grupPilihan);

            String[] opsi = soal.getPilihan();
            for (int j = 0; j < opsi.length; j++) {
                RadioButton rb = new RadioButton(opsi[j]);
                rb.setTextFill(Color.WHITE);
                rb.setUserData(j);
                rb.setToggleGroup(grupPilihan);
                boxSoal.getChildren().add(rb);
            }
            layoutKuis.getChildren().add(boxSoal);
        }

        Button btnSubmit = new Button("Kirim Jawaban");
        btnSubmit.setPadding(new Insets(10, 20, 10, 20));
        btnSubmit.getStyleClass().add("btn-submit");

        btnSubmit.setOnAction(e -> {
            int skorBenar = 0;
            boolean semuaDiisi = true;

            for (int i = 0; i < daftarSoal.size(); i++) {
                ToggleGroup grup = kumpulanGrupPilihan.get(i);
                if (grup.getSelectedToggle() == null) {
                    semuaDiisi = false;
                    break;
                } else {
                    int jawabanUser = (int) grup.getSelectedToggle().getUserData();
                    if (jawabanUser == daftarSoal.get(i).getIndeksJawabanBenar()) {
                        skorBenar++;
                    }
                }
            }

            if (!semuaDiisi) {
                new Alert(Alert.AlertType.WARNING, "Harap jawab semua pertanyaan terlebih dahulu!", ButtonType.OK).showAndWait();
            } else {
                double nilaiAkhir = ((double) skorBenar / daftarSoal.size()) * 100;
                Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
                alertInfo.setTitle("Hasil Kuis");
                alertInfo.setHeaderText("Kuis Selesai!");
                alertInfo.setContentText("Anda menjawab benar " + skorBenar + " dari " + daftarSoal.size() + " soal.\nNilai Anda: " + nilaiAkhir);
                alertInfo.showAndWait();
                kuisStage.close();
            }
        });

        layoutKuis.getChildren().add(btnSubmit);

        ScrollPane scrollKuis = new ScrollPane(layoutKuis);
        scrollKuis.setStyle("-fx-background: #12003D; -fx-background-color: transparent;");
        scrollKuis.setFitToWidth(true);

        Scene sceneKuis = new Scene(scrollKuis, 600, 500);
        sceneKuis.getStylesheets().add(cssPath); // Menyambungkan CSS yang sama
        kuisStage.setScene(sceneKuis);
        kuisStage.show();
    }

    private List<JalurKarir> siapkanDataKarir() {
        List<JalurKarir> daftarKarir = new ArrayList<>();

        // 1. FRONTEND DEVELOPER
        JalurKarir frontendDev = new JalurKarir("Frontend Developer (Web & UI)");
        Tahapan ft1 = new Tahapan(1, "Dasar HTML, CSS, & JavaScript", "Pelajari struktur web dasar untuk membangun antarmuka.", "Belajar Mandiri", "🎬 Tonton Video Dasar", "https://www.youtube.com/watch?v=3U1AhjEf7DM");
        ft1.tambahSoal(new Soal("Apa kepanjangan resmi dari HTML?", new String[]{"Hyper Text Markup Language", "High Tech Modern Language", "Hyperlink and Text Management"}, 0));
        Tahapan ft2 = new Tahapan(2, "Framework Modern (React/Vue)", "Kuasai komponen UI modern untuk web yang interaktif.", "Belajar Mandiri", "🎬 Tonton Video Framework", "https://www.youtube.com/watch?v=97WRJwkfenI");
        ft2.tambahSoal(new Soal("Apa fungsi utama dari Framework Frontend seperti React?", new String[]{"Mengelola database server", "Mempermudah pembuatan UI berbasis komponen", "Mengamankan jaringan website"}, 1));
        Tahapan ft3 = new Tahapan(3, "State Management & Deployment", "Mengelola data aplikasi skala besar dan mengunggah website ke internet.", "Belajar Mandiri", "🎬 Tonton Video Deployment", "https://youtu.be/cFVSCzmC4P8?si=HvkytrVCWAz-Ph73");
        ft3.tambahSoal(new Soal("Platform mana yang populer untuk hosting proyek Frontend secara gratis dan cepat?", new String[]{"Vercel / Netlify", "MySQL Server", "XAMPP"}, 0));
        frontendDev.tambahTahapan(ft1);
        frontendDev.tambahTahapan(ft2);
        frontendDev.tambahTahapan(ft3);
        daftarKarir.add(frontendDev);

        // 2. BACKEND DEVELOPER
        JalurKarir backendDev = new JalurKarir("Backend Developer (Server & Database)");
        Tahapan bt1 = new Tahapan(1, "Dasar Node.js Runtime", "Memahami arsitektur server side menggunakan Javascript lingkungan Node.", "Belajar Mandiri", "🎬 Tonton Video Node.js", "https://www.youtube.com/watch?v=sSLJx5t4OJ4");
        bt1.tambahSoal(new Soal("Node.js memungkinkan kita menjalankan JavaScript di mana?", new String[]{"Di dalam browser", "Di lingkungan Server side", "Di dalam mesin database"}, 1));
        Tahapan bt2 = new Tahapan(2, "Manajemen Database (MySQL)", "Kuasai query dasar tabel menggunakan MySQL Database.", "Belajar Mandiri", "🎬 Tonton Video SQL", "https://www.youtube.com/watch?v=HXV3zeQKqGY");
        bt2.tambahSoal(new Soal("Perintah SQL manakah yang dipakai untuk mengambil data dari tabel?", new String[]{"SELECT", "INSERT", "DELETE"}, 0));
        Tahapan bt3 = new Tahapan(3, "Pembuatan RESTful API & Keamanan", "Membangun sistem API secure menggunakan ExpressJS dan JWT Token.", "Belajar Mandiri", "🎬 Tonton Video API Backend", "https://www.youtube.com/watch?v=7YcW25PHnAA");
        bt3.tambahSoal(new Soal("Teknologi apa yang sering digunakan untuk mengamankan sesi login user pada REST API?", new String[]{"JWT (JSON Web Token)", "CSS Grid", "HTML Form"}, 0));
        backendDev.tambahTahapan(bt1);
        backendDev.tambahTahapan(bt2);
        backendDev.tambahTahapan(bt3);
        daftarKarir.add(backendDev);

        // 3. MOBILE DEVELOPER
        JalurKarir mobileDev = new JalurKarir("Mobile Developer (Android & iOS)");
        Tahapan mt1 = new Tahapan(1, "Dasar Pemrograman Dart & Flutter", "Membuat aplikasi mobile cross-platform dengan UI menawan.", "Belajar Mandiri", "🎬 Tonton Video Flutter", "https://www.youtube.com/watch?v=VPvVD8t02U8");
        mt1.tambahSoal(new Soal("Apa keunggulan utama dari framework Flutter?", new String[]{"Hanya bisa untuk Android", "Satu kode (codebase) untuk Android dan iOS", "Tidak membutuhkan bahasa pemrograman"}, 1));
        Tahapan mt2 = new Tahapan(2, "State Management & Integrasi API", "Menghubungkan aplikasi mobile dengan data dinamis dari internet.", "Belajar Mandiri", "🎬 Tonton Video API Mobile", "https://www.youtube.com/watch?v=XvFmUE-36Kc");
        mt2.tambahSoal(new Soal("Format data apa yang paling sering digunakan saat mengambil data dari API?", new String[]{"XML", "JSON", "HTML"}, 1));
        Tahapan mt3 = new Tahapan(3, "Arsitektur Aplikasi & Google Play Store", "Menyusun struktur kode bersih (Clean Architecture) dan merilis aplikasi.", "Belajar Mandiri", "🎬 Tonton Video Play Store", "https://youtu.be/RIX4ufelA58?si=bMyy1HUGjsf65CX4");
        mt3.tambahSoal(new Soal("File format hasil build Flutter apa yang diunggah ke Google Play Console modern?", new String[]{" .apk", " .aab (Android App Bundle)", " .exe"}, 1));
        mobileDev.tambahTahapan(mt1);
        mobileDev.tambahTahapan(mt2);
        mobileDev.tambahTahapan(mt3);
        daftarKarir.add(mobileDev);

        // 4. DATA SCIENTIST
        JalurKarir dataScientist = new JalurKarir("Data Scientist (Analisis & AI)");
        Tahapan ts1 = new Tahapan(1, "Dasar Pemrograman Python", "Sintaksis dasar variabel, perulangan, dan koleksi data Python.", "Belajar Mandiri", "🎬 Tonton Video Python", "https://www.youtube.com/watch?v=kqtD5dpn9C8");
        ts1.tambahSoal(new Soal("Bagaimana cara menulis komentar di bahasa pemrograman Python?", new String[]{"// Ini komentar", "# Ini komentar", "/* Ini komentar */"}, 1));
        Tahapan ts2 = new Tahapan(2, "Visualisasi Data Grafis", "Membuat insight data menarik menggunakan library Matplotlib & Pandas.", "Belajar Mandiri", "🎬 Tonton Video Matplotlib", "https://www.youtube.com/watch?v=DAQNHzOcO5A");
        ts2.tambahSoal(new Soal("Library Python apa yang populer digunakan khusus untuk visualisasi grafis?", new String[]{"Matplotlib", "Django", "Flask"}, 0));
        Tahapan ts3 = new Tahapan(3, "Machine Learning Dasar", "Mengenal algoritma dasar kecerdasan buatan.", "Belajar Mandiri", "🎬 Tonton Video ML", "https://youtu.be/E0Hmnixke2g?si=3FsdzopEb7BKJExy");
        ts3.tambahSoal(new Soal("Apa yang membedakan Supervised Learning dengan Unsupervised Learning?", new String[]{"Penggunaan database SQL", "Adanya data berlabel sebagai target belajar", "Kecepatan prosesor"}, 1));
        dataScientist.tambahTahapan(ts1);
        dataScientist.tambahTahapan(ts2);
        dataScientist.tambahTahapan(ts3);
        daftarKarir.add(dataScientist);

        return daftarKarir;
    }

}